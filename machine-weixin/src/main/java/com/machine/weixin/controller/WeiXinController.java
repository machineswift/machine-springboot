package com.machine.weixin.controller;

import com.machine.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weixin")
public class WeiXinController {

    @Autowired
    private WeiXinService weiXinService;

    @GetMapping("weixin")
    public String weixin() {
        return weiXinService.weixin();
    }


    @GetMapping("plat")
    public String plat() {
        return weiXinService.plat();
    }

    @GetMapping("spectrum")
    public String spectrum() {
        return weiXinService.spectrum();
    }

}
