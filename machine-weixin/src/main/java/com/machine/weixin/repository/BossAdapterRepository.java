package com.machine.weixin.repository;

import com.machine.weixin.repository.bean.boss.BossResponse;
import com.machine.weixin.repository.bean.boss.OfficialAccountBossResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BossAdapterRepository {

    /**
     * 获取租户下公众号信息
     */
    @GET("/order-management/v1/wechat/authlist/{thirdPartyAppId}/{tenantId}")
    Single<BossResponse<OfficialAccountBossResponse>> getOfficialAccounts(@Path("thirdPartyAppId") String thirdPartyAppId,
                                                                          @Path("tenantId") String tenantId);

}
