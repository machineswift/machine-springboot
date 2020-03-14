package com.machine.weixin.retrofit2.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class CookieInterceptor implements Interceptor {
    private String cookie;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request req = chain.request();

        if (Objects.nonNull(cookie)) {
             req = chain.request().newBuilder()
                    .header("Cookie", cookie)
                    .build();
        }

        return chain.proceed(req);
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
