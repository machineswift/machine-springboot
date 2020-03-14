package com.machine.weixin.repository.bean.weixin.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardCodeRequest {
    private String cardId;
    private String code;
    private Boolean checkConsume;
}
