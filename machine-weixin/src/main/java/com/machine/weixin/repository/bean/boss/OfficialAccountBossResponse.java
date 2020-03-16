package com.machine.weixin.repository.bean.boss;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OfficialAccountBossResponse {
    private String appId;
    private String bossId;
    private String nickName;
    private String headImg;
    private Integer serviceTypeInfo;
    private Integer verifyTypeInfo;
    private String userName;
    private String principalName;
    private String alias;
    private String qrcodeUrl;
    private String businessInfo;
    private String funcInfo;
    private Date createTime;
    private Date updateTime;
}
