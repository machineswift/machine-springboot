package com.machine.weixin.retrofit2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.machine.weixin.retrofit2.convert.binary.BinaryConverterFactory;
import com.machine.weixin.retrofit2.convert.json.JacksonConverterFactory;
import com.machine.weixin.retrofit2.interceptor.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.HttpException;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


public class RetrofitHelper {
    private static final Logger log = LoggerFactory.getLogger(RetrofitHelper.class);
    private static OkHttpClient defaultClient;

    public static Throwable logHttpException(Throwable t) {
        if (null == t) {
            throw new IllegalArgumentException();
        }

        HttpException e;
        if (t instanceof HttpException) {
            e = (HttpException) t;
        } else if (Objects.nonNull(t.getCause()) && t.getCause() instanceof HttpException) {
            e = (HttpException) t.getCause();
        } else {
            return t;
        }

        Response resp = e.response();
        Request req = resp.raw().request();
        String body = null;
        try {
            body = Objects.nonNull(resp.errorBody()) ? resp.errorBody().string() : "";
        } catch (IOException e1) {
            body = "";
        }
        log.error("request:\n  url: {}\n  method: {}\nresponse:\n  status code: {}\n  body: {}",
                req.url().toString(), req.method(), resp.code(), body);

        return t;
    }

    public static boolean is404Exception(Throwable t) {
        HttpException e;
        if (t instanceof HttpException) {
            e = (HttpException) t;
        } else if (Objects.nonNull(t.getCause()) && t.getCause() instanceof HttpException) {
            e = (HttpException) t.getCause();
        } else {
            return false;
        }

        return e.response().code() == 404;
    }

    public static ObjectMapper objectMapper() {
        return objectMapper(PropertyNamingStrategy.SNAKE_CASE);
    }

    public static ObjectMapper objectMapper(PropertyNamingStrategy s) {
        ObjectMapper objectMapper = new ObjectMapper();
        // deserialization
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // serialization
        objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);

        objectMapper.setPropertyNamingStrategy(s);

        objectMapper.setTimeZone(TimeZone.getTimeZone("CTT"));

        defaultClient = decorateClient(OkHttpClient::new);

        return objectMapper;
    }

    public static OkHttpClient.Builder clientBuilder() {
        return defaultClient.newBuilder();
    }

    public static <T> T createService(String address, Class<T> clazz) {
        return createService(address, clazz, () -> defaultClient);
    }

    public static <T> T createService(String address, Class<T> clazz, Supplier<OkHttpClient> client) {
        return createService(address, clazz, RetrofitHelper.objectMapper(), client);
    }

    public static <T> T createService(String address, Class<T> clazz, ObjectMapper objectMapper, Supplier<OkHttpClient> client) {
        String url = address.endsWith("/") ? address : address + "/";

        Retrofit retrofit = new Retrofit.Builder()
                .client(client.get())
                .baseUrl(url)
                .addConverterFactory(BinaryConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();

        return retrofit.create(clazz);

    }

    private static OkHttpClient decorateClient(Supplier<OkHttpClient> client) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //获取web-client日志级别的配置
        String logLevel = System.getProperty("web-client.log.level");
        if (Objects.isNull(logLevel)) {
            logLevel = System.getenv("web-client.log.level");
        }
        if (Objects.nonNull(logLevel)) {
            //设置查看日志的等级
            logging.setLevel(HttpLoggingInterceptor.Level.valueOf(logLevel.toUpperCase()));
        } else {
            //设置查看日志的等级
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        return client.get().newBuilder()
                .retryOnConnectionFailure(true)
                .addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
