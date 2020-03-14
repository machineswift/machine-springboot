package com.machine.pulsar.service.impl;

import com.machine.pulsar.service.PulsarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PulsarServiceImpl implements PulsarService {

    @Override
    public String get() {
        log.info("hello demo !");

        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }

        return "hello demo !";
    }
}
