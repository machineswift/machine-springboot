package com.machine.rocketmq.controller;

import com.machine.rocketmq.service.RocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocket")
public class RocketController {

    @Autowired
    private RocketService rocketService;

    @GetMapping("get")
    public String get() {
        return rocketService.get();
    }

}
