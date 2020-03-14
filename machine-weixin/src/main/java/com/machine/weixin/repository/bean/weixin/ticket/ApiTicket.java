package com.machine.weixin.repository.bean.weixin.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiTicket {
    private Integer errcode;
    private String errmsg;
    private String ticket;
    private Integer expiresIn;
}
