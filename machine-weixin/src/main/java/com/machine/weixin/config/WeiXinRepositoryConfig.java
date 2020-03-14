package com.machine.weixin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.machine.weixin.repository.PlatAdapterRepository;
import com.machine.weixin.repository.SpectrumRepository;
import com.machine.weixin.repository.WeixinRepository;
import com.machine.weixin.retrofit2.RetrofitHelper;
import com.machine.weixin.retrofit2.authenticator.SpectrumAuthenticator;
import com.machine.weixin.retrofit2.interceptor.ApiGatewayInterceptor;
import com.machine.weixin.retrofit2.interceptor.CookieInterceptor;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeiXinRepositoryConfig {

    @Value("${app.apigateway.host}")
    private String apiGatewayHost;

    @Value("${app.apigateway.caller}")
    private String apiGatewayCaller;

    @Value("${app.apigateway.secret}")
    private String apiGatewaySecret;

    @Value("${system.config.address}")
    private String spectrumHost;

    @Value("${spectrum.key}")
    private String spectrumUser;

    @Value("${spectrum.secret}")
    private String spectrumPassword;

    @Bean
    public PlatAdapterRepository platAdapterRepository() {
        ObjectMapper objectMapper = RetrofitHelper.objectMapper(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        return RetrofitHelper.createService("http://" + apiGatewayHost, PlatAdapterRepository.class, objectMapper, () -> {
            Interceptor interceptor = new ApiGatewayInterceptor(apiGatewayCaller, apiGatewaySecret);

            return RetrofitHelper.clientBuilder()
                    .addInterceptor(interceptor)
                    .build();
        });
    }

    @Bean
    public WeixinRepository weixinRepository() {
        return RetrofitHelper.createService("http://" + apiGatewayHost, WeixinRepository.class, () -> {
            Interceptor interceptor = new ApiGatewayInterceptor(apiGatewayCaller, apiGatewaySecret);

            return RetrofitHelper.clientBuilder()
                    .addInterceptor(interceptor)
                    .build();
        });
    }

    @Bean
    public SpectrumRepository spectrumRepository() {
        String host = "http://" + spectrumHost;
        return RetrofitHelper.createService(host, SpectrumRepository.class, () -> {
            SpectrumRepository lr = RetrofitHelper.createService(host, SpectrumRepository.class);
            CookieInterceptor i = new CookieInterceptor();
            Authenticator auth = new SpectrumAuthenticator(i, lr, spectrumUser, spectrumPassword);

            return RetrofitHelper.clientBuilder()
                    .addNetworkInterceptor(i)
                    .authenticator(auth)
                    .build();
        });
    }
}
