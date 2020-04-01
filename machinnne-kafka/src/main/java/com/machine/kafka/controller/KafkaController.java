package com.machine.kafka.controller;

import com.machine.kafka.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    private KafkaService rocketService;

    @GetMapping("sendMessage")
    public String sendMessage() {
        rocketService.sendMessage("machine-test");
        return "machine-test¬";
    }
}
