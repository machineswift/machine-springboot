package com.machine.weixin.repository.bean.weixin.qrcode;

import lombok.Data;

@Data
public class QrStrSceneCreate extends QrcodeCreate {
    private Integer expireSeconds;

    public QrStrSceneCreate() {
        setActionName("QR_STR_SCENE");
    }

    public void setSceneStr(String sceneStr) {
        super.actionInfo.setSceneStr(sceneStr);
    }
}
