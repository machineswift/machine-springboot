package com.machine.weixin.repository.bean.weixin.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumeCardCodeRequest {
    private String cardId;
    private String code;

}
