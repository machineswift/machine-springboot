package com.machine.weixin.retrofit2.interceptor;

import com.machine.weixin.util.EncryptUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiGatewayInterceptor implements Interceptor {

    private static DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Pattern pattern = Pattern.compile(".*/([^/]+)/(v\\d+|\\d\\.\\d)(/[^?]+)(\\?.*)?");

    private String caller;
    private String secret;

    public ApiGatewayInterceptor(String caller, String secret) {
        this.caller = caller;
        this.secret = secret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        String timestamp = timeFormat.format(System.currentTimeMillis());

        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder()
                .header("X-Caller-Service", caller)
                .header("X-Caller-Timestamp", timestamp)
                .header("X-Caller-Sign", getSign(caller, timestamp,
                        originalRequest.url().uri().toString(), secret))
                .build();
        return chain.proceed(request);
    }


    public static String getSign(String service, String timestamp, String url, String secret) {
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            String contextPath = matcher.group(1);
            String version = matcher.group(2);
            String requestPath = matcher.group(3);

            String params = secret + "callerService" + service + "contextPath" + contextPath +
                    "requestPath" + requestPath + "timestamp" + timestamp + "v" + version + secret;
            return EncryptUtil.encrypt_MD5(params).toUpperCase();
        } else {
            throw new IllegalArgumentException(String.format("url %s is valid", url));
        }
    }
}
