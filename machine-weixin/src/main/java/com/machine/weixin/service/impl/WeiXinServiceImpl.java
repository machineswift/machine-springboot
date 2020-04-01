package com.machine.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.machine.weixin.repository.BossAdapterRepository;
import com.machine.weixin.repository.PlatAdapterRepository;
import com.machine.weixin.repository.SpectrumRepository;
import com.machine.weixin.repository.WeixinRepository;
import com.machine.weixin.repository.bean.weixin.OfficialAccountWeiXinResponse;
import com.machine.weixin.repository.bean.boss.OfficialAccountBossResponse;
import com.machine.weixin.repository.bean.weixin.PostResp;
import com.machine.weixin.repository.bean.weixin.media.*;
import com.machine.weixin.repository.bean.weixin.message.MpnewsMessage;
import com.machine.weixin.repository.bean.weixin.qrcode.QrStrSceneCreate;
import com.machine.weixin.repository.bean.weixin.qrcode.QrcodeCreate;
import com.machine.weixin.repository.bean.weixin.qrcode.QrcodeResp;
import com.machine.weixin.repository.bean.weixin.template.TemplateInfos;
import com.machine.weixin.service.WeiXinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static com.machine.weixin.service.impl.SceneMessage.Type.WEIXIN_TEMPLATE;

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
        TemplateInfos result = weixinRepository.getTemplateInfos(convert2Token("wxd3f305d0c5d85b96")).blockingGet();
        BatchGetMaterialResponse response = weixinRepository.getMedias(convert2Token("wxd3f305d0c5d85b96"), new BatchGetMaterialRequest("news", 0, 10)).blockingGet();
        log.info("素材列表,batchGetMaterialResponse:{}",JSON.toJSON(response));

        MediaCountResponse mediaCountResponse = weixinRepository.getMediaCount(convert2Token("wxca5d29b7eef2419a")).blockingGet();
        log.info("素材总数,mediaCountResponse:{}",JSON.toJSON(mediaCountResponse));

        MpnewsMessage customMessage = new MpnewsMessage();
        customMessage.setTouser("o1y3n1VUiB1hpiQ75bQelU9v2yFY");
        customMessage.setMpnews("yGPSiV7K5HFGOsJgG0-2-OA7ovhpp5s2bI5Wt5TJDYU");
        PostResp postResp = weixinRepository.sendCustomMessage(convert2Token("wxd3f305d0c5d85b96"), customMessage).blockingGet();
        log.info("发送图文消息:postResp{}",JSON.toJSON(postResp));

        MediaTypeNewsContent mediaTypeNewsContent=  weixinRepository.getMedia(convert2Token("wxd3f305d0c5d85b96"),new GetMaterialRequest("yGPSiV7K5HFGOsJgG0-2-OA7ovhpp5s2bI5Wt5TJDYU")).blockingGet();
        log.info("永久素材,mediaTypeNewsContent:{}",JSON.toJSON(mediaTypeNewsContent));

        weixinRepository.get


        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("模板参数对应redis的uuid:{}", uuid);
        QrcodeCreate qrcodeCreate = new QrcodeCreate();
        String msg = SceneMessage.of(WEIXIN_TEMPLATE).pack(uuid);
        qrcodeCreate.setActionName("QR_STR_SCENE");
        qrcodeCreate.getActionInfo().setSceneStr(msg);
        qrcodeCreate.setExpireSeconds(2592000);
        //https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=
        QrcodeResp qrcodeResp = weixinRepository.createQrcode(convert2Token("wxd3f305d0c5d85b96"), qrcodeCreate).blockingGet();
        log.info("微信模板预览二维码,qrcodeResp:{}",JSON.toJSON(qrcodeResp));
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

    private String convert2Token(String appId) {
        return Base64.getEncoder()
                .encodeToString(String.format("appId=%s&componentAppId=%s", appId, thirdPartyAppId).getBytes());
    }
}
