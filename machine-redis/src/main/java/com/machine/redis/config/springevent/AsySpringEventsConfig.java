package com.machine.redis.config.springevent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ApplicationEvent异步配置
 */
@Configuration
public class AsySpringEventsConfig {
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 16,
                300, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4 * 1000 * 1000));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        eventMulticaster.setTaskExecutor(executor);
        return eventMulticaster;
    }
}