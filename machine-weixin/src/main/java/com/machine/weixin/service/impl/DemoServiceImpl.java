package com.machine.weixin.service.impl;

import com.machine.weixin.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String get() {
        log.info("hello  demo !");
        return "hello demo !";
    }

}
