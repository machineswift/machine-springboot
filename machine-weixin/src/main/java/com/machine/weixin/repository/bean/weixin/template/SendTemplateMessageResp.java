package com.machine.weixin.repository.bean.weixin.template;

import com.machine.weixin.repository.bean.weixin.PostResp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendTemplateMessageResp extends PostResp {
    private String msgid;
}
