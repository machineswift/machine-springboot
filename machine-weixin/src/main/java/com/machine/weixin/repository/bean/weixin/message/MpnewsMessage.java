package com.machine.weixin.repository.bean.weixin.message;

import lombok.Data;

import java.util.Objects;

/**
 * 图文消息, 点击跳转到图文消息页面
 */
@Data
public class MpnewsMessage extends CustomMessage {
    private Mpnews mpnews;

    public MpnewsMessage() {
        setMsgtype("mpnews");
    }

    public void setMpnews(String mediaId) {
        if (Objects.isNull(mpnews)) {
            this.mpnews = new MpnewsMessage.Mpnews();
        }
        mpnews.setMedia_id(mediaId);
    }

    @Data
    static class Mpnews {
        private String media_id;
    }
}
