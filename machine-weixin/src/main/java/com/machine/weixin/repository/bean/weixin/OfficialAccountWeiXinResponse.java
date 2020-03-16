package com.machine.weixin.repository.bean.weixin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OfficialAccountWeiXinResponse {
    private String appId;
    private String nickName;
    private String headImg;
    private Integer serviceTypeInfo;
    private Integer verifyTypeInfo;
    private String userName;
    private String principalName;
    private String alias;
    private String qrcodeUrl;
    private BusinessInfo businessInfo;
    private List<AuthInfo> authInfo = new ArrayList<>();
    private Long createTime;
    private Long updateTime;

    @Data
    @NoArgsConstructor
    public static class BusinessInfo {
        private Boolean openStore;
        private Boolean openScan;
        private Boolean openPay;
        private Boolean openCard;
        private Boolean openShake;
        private Long createTime;
        private Long updateTime;
    }

    @Data
    @NoArgsConstructor
    public static class AuthInfo {
        private String appId;
        private String componentAppId;
        private List<FuncscopeCategory> funcscopeCategories = new ArrayList<>();

        @Data
        @NoArgsConstructor
        public static class FuncscopeCategory {
            private Integer funcscopeCategoryId;
            private String name;
        }
    }
}
