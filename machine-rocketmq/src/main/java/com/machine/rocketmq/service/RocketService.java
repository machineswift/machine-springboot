package com.machine.rocketmq.service;

public interface RocketService {
    String get();

    void syncProducer() throws Exception;
}
