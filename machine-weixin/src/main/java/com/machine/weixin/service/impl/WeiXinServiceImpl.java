package com.machine.weixin.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.machine.weixin.repository.BossAdapterRepository;
import com.machine.weixin.repository.PlatAdapterRepository;
import com.machine.weixin.repository.SpectrumRepository;
import com.machine.weixin.repository.WeixinRepository;
import com.machine.weixin.repository.bean.weixin.OfficialAccountWeiXinResponse;
import com.machine.weixin.repository.bean.boss.OfficialAccountBossResponse;
import com.machine.weixin.repository.bean.weixin.template.TemplateInfos;
import com.machine.weixin.service.WeiXinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WeiXinServiceImpl implements WeiXinService {


    /*第三方托管平台appId，主要是实现微信托管，运营平台提供*/
    @Value("${system.weixin.thirdParty.app.key}")
    private String thirdPartyAppId;

    @Autowired
    private BossAdapterRepository bossAdapterRepository;

    @Autowired
    private WeixinRepository weixinRepository;

    @Autowired
    private PlatAdapterRepository platAdapterRepository;

    @Autowired
    private SpectrumRepository spectrumRepository;


    @Override
    public String boss() {
        List<OfficialAccountBossResponse> responses = bossAdapterRepository
                .getOfficialAccounts(thirdPartyAppId, "ironman").blockingGet().getData();
        return responses.toString();
    }


    @Override
    public String weixin() {
        TemplateInfos result = weixinRepository.getTemplateInfos("YXBwSWQ9d3gzMjdlMDRhMDhkMDgwMjAyJmNvbXBvbmVudEFwcElkPXd4MmM1MTNkNzVkNzI4ODMzZg==").blockingGet();
        return result.toString();
    }

    @Override
    public String plat() {
        JsonNode node = platAdapterRepository.getAutherInfo("wxd3f305d0c5d85b96", "wx2c513d75d728833f").blockingGet();
        OfficialAccountWeiXinResponse accountResponse = platAdapterRepository.getOfficialAccountByAccountId("wxd3f305d0c5d85b96", "wx2c513d75d728833f").blockingGet();
        System.out.println("===================");
        System.out.println(accountResponse.toString());
        return node.toString();
    }

    @Override
    public String spectrum() {
        String key = convertKey("epassport.register.token");
        String value = spectrumRepository.getAttribute(key).map(x -> x.getNodes().get(0).getValue())
                .blockingGet();
        return value;
    }

    private String convertKey(String key) {
        String ENV = "yanshan.chen.marketing";
        String SERVICE_NAME = "marketing-program-service";
        return String.format("/%s/%s/%s", ENV, SERVICE_NAME, key);
    }
}
