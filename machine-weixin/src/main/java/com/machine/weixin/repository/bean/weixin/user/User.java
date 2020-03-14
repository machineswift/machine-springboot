package com.machine.weixin.repository.bean.weixin.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String openid;
    private String lang;

    public User(String openid,
                String lang) {
        this.openid = openid;
        this.lang = lang;
    }

}
