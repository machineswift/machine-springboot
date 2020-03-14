package com.machine.weixin.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.machine.weixin.repository.bean.account.OfficialAccountResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface PlatAdapterRepository {
    /**
     * 获取授权公众号基本信息
     *
     * @return
     */
    @GET("/plat-adapter/v2/weixin/apps/{appId}/authinfo")
    Single<JsonNode> getAutherInfo(@Path("appId") String appId, @Query("componentAppId") String componentAppId);

    /**
     * 获取公众号基本信息
     *
     * @return
     */
    @GET("/plat-adapter/v2/weixin/apps/{appId}")
    Single<OfficialAccountResponse> getOfficialAccountByAccountId(@Path("appId") String appId, @Query("componentAppId") String componentAppId);


    /**
     * 获取租户下公众号信息
     *
     * @return
     */
    @GET("/plat-adapter/v2/tenants/{tenantId}/weixin/apps")
    Single<List<OfficialAccountResponse>> getOfficialAccounts(@Path("tenantId") String tenantId,
                                                              @Query("containBusinessInfo") Boolean containBusinessInfo,
                                                              @Query("containAuthInfo") Boolean containAuthInfo,
                                                              @Query("onlyAuthorized") Boolean onlyAuthorized,
                                                              @Query("componentAppId") String componentAppId);
}
