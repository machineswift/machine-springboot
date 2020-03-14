package com.machine.weixin.repository.bean.weixin.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserInfo {
    private Integer subscribe;
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private Long subscribeTime;
    private String unionid;
    private String remark;
    private Integer groupid;
    private List<Integer> tagidList;
    private String subscribeScene;
    private Integer qrScene;
    private String qrSceneStr;
}
