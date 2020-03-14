package com.machine.weixin.retrofit2.authenticator;

import com.machine.weixin.repository.SpectrumRepository;
import com.machine.weixin.retrofit2.interceptor.CookieInterceptor;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class SpectrumAuthenticator implements Authenticator {

    public final Logger log = LoggerFactory.getLogger(SpectrumAuthenticator.class);

    private CookieInterceptor interceptor;
    private SpectrumRepository repository;

    private String username;
    private String password;

    public SpectrumAuthenticator(CookieInterceptor interceptor, SpectrumRepository repository,
                                 String username, String password) {
        this.interceptor = interceptor;
        this.repository = repository;
        this.username = username;
        this.password = password;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        String cookie = repository.login(username, password).blockingGet()
                .headers().get("Set-Cookie");
        Request request = response.request();

        if (Objects.nonNull(cookie)) {
            interceptor.setCookie(cookie);

            request = request.newBuilder()
                    .header("Cookie", cookie)
                    .build();
        } else {
            log.error("cookie is null");
        }
        return request;
    }
}
