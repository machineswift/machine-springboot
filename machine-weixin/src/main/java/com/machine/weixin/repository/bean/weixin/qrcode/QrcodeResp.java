package com.machine.weixin.repository.bean.weixin.qrcode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QrcodeResp {
    private String ticket;
    private Integer expireSeconds;
    private String url;
}