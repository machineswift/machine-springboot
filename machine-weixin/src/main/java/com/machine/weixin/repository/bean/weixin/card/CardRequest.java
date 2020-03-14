package com.machine.weixin.repository.bean.weixin.card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardRequest {

    private String cardId;

    public CardRequest(String cardId) {
        this.cardId = cardId;
    }
}
