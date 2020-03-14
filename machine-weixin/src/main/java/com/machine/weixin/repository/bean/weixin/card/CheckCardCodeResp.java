package com.machine.weixin.repository.bean.weixin.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckCardCodeResp {
    private int errcode;
    private String errmsg;
    private boolean canConsume;
    private String userCardStatus;
    private String openid;
    private Card card;

    @Data
    @NoArgsConstructor
    public static class Card {
        private String cardId;
        private long beginTime;
        private long endTime;
    }
}
