package com.machine.weixin.repository.bean.weixin.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumeCardCodeResp {
    private int errcode;
    private String errmsg;
    private String cardId;
    private String openid;
}
