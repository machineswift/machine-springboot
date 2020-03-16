package com.machine.weixin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.machine.weixin.repository.BossAdapterRepository;
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

    /*运营管理系统配置*/
    @Value("${system.shuyun.boss.api.address}")
    private String bossAddress;
    @Value("${system.shuyun.boss.api.caller}")
    private String bossCaller;
    @Value("${system.shuyun.boss.api.secret}")
    private String bossSecret;

    /*微信基础服务配置*/
    @Value("${system.shuyun.gateway.api.address}")
    private String apiGatewayAddress;
    @Value("${system.shuyun.gateway.api.caller}")
    private String apiGatewayCaller;
    @Value("${system.shuyun.gateway.api.secret}")
    private String apiGatewaySecret;

    /*配置中心配置*/
    @Value("${system.config.address}")
    private String spectrumAddress;
    @Value("${spectrum.key}")
    private String spectrumUser;
    @Value("${spectrum.secret}")
    private String spectrumPassword;


    @Bean
    public BossAdapterRepository bossAdapterRepository() {
        ObjectMapper objectMapper = RetrofitHelper.objectMapper(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        return RetrofitHelper.createService("http://" + bossAddress, BossAdapterRepository.class, objectMapper, () -> {
            Interceptor interceptor = new ApiGatewayInterceptor(bossCaller, bossSecret);

            return RetrofitHelper.clientBuilder()
                    .addInterceptor(interceptor)
                    .build();
        });
    }

    @Bean
    public PlatAdapterRepository platAdapterRepository() {
        ObjectMapper objectMapper = RetrofitHelper.objectMapper(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        return RetrofitHelper.createService("http://" + apiGatewayAddress, PlatAdapterRepository.class, objectMapper, () -> {
            Interceptor interceptor = new ApiGatewayInterceptor(apiGatewayCaller, apiGatewaySecret);

            return RetrofitHelper.clientBuilder()
                    .addInterceptor(interceptor)
                    .build();
        });
    }

    @Bean
    public WeixinRepository weixinRepository() {
        return RetrofitHelper.createService("http://" + apiGatewayAddress, WeixinRepository.class, () -> {
            Interceptor interceptor = new ApiGatewayInterceptor(apiGatewayCaller, apiGatewaySecret);

            return RetrofitHelper.clientBuilder()
                    .addInterceptor(interceptor)
                    .build();
        });
    }

    @Bean
    public SpectrumRepository spectrumRepository() {
        String host = "http://" + spectrumAddress;
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
