package com.machine.pulsar.controller;

import com.machine.pulsar.service.PulsarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pulsar")
public class PulsarController {

    @Autowired
    private PulsarService pulsarService;

    @GetMapping("get")
    public String get() {
        return pulsarService.get();
    }

}
