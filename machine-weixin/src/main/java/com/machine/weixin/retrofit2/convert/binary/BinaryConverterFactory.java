package com.machine.weixin.retrofit2.convert.binary;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class BinaryConverterFactory extends Converter.Factory {
    public static BinaryConverterFactory create() {
        return new BinaryConverterFactory();
    }

    private BinaryConverterFactory() {

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == byte[].class) {
            return ByteArrayResponseBodyConverter.INSTANCE;
        }

        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (type == byte[].class) {
            return ByteArrayRequestBodyConverter.INSTANCE;
        }

        return null;
    }

    static final class ByteArrayResponseBodyConverter implements Converter<ResponseBody, byte[]> {
        public static final ByteArrayResponseBodyConverter INSTANCE = new ByteArrayResponseBodyConverter();

        @Override
        public byte[] convert(ResponseBody value) throws IOException {
            return value.bytes();
        }
    }

    static final class ByteArrayRequestBodyConverter implements Converter<byte[], RequestBody> {
        public static final ByteArrayRequestBodyConverter INSTANCE = new ByteArrayRequestBodyConverter();

        @Override
        public RequestBody convert(byte[] value) throws IOException {
            return RequestBody.create(MediaType.parse("multipart/form-data"), value);
        }
    }
}
