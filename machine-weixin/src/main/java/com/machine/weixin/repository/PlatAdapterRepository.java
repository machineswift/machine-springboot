package com.machine.weixin.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.machine.weixin.repository.bean.weixin.OfficialAccountWeiXinResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface PlatAdapterRepository {
    /**
     * 获取授权公众号基本信息
     */
    @GET("/plat-adapter/v2/weixin/apps/{appId}/authinfo")
    Single<JsonNode> getAutherInfo(@Path("appId") String appId,
                                   @Query("thirdPartyAppId") String thirdPartyAppId);

    /**
     * 获取公众号基本信息
     */
    @GET("/plat-adapter/v2/weixin/apps/{appId}")
    Single<OfficialAccountWeiXinResponse> getOfficialAccountByAccountId(@Path("appId") String appId,
                                                                        @Query("thirdPartyAppId") String thirdPartyAppId);


    /**
     * 获取租户下公众号信息
     */
    @GET("/plat-adapter/v2/tenants/{tenantId}/weixin/apps")
    Single<List<OfficialAccountWeiXinResponse>> getOfficialAccounts(@Path("tenantId") String tenantId,
                                                                    @Query("thirdPartyAppId") String thirdPartyAppId,
                                                                    @Query("containBusinessInfo") Boolean containBusinessInfo,
                                                                    @Query("containAuthInfo") Boolean containAuthInfo,
                                                                    @Query("onlyAuthorized") Boolean onlyAuthorized);
}
