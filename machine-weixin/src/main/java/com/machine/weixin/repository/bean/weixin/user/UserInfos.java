package com.machine.weixin.repository.bean.weixin.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserInfos {
    private List<UserInfo> userInfoList;
}
