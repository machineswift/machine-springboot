package com.machine.weixin.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.machine.weixin.repository.bean.weixin.PostResp;
import com.machine.weixin.repository.bean.weixin.card.*;
import com.machine.weixin.repository.bean.weixin.media.BatchGetMaterialRequest;
import com.machine.weixin.repository.bean.weixin.media.BatchGetMaterialResponse;
import com.machine.weixin.repository.bean.weixin.media.MediaCountResponse;
import com.machine.weixin.repository.bean.weixin.message.CustomMessage;
import com.machine.weixin.repository.bean.weixin.qrcode.QrcodeCreate;
import com.machine.weixin.repository.bean.weixin.template.SendTemplateMessageResp;
import com.machine.weixin.repository.bean.weixin.template.TemplateInfos;
import com.machine.weixin.repository.bean.weixin.template.TemplateMessage;
import com.machine.weixin.repository.bean.weixin.ticket.ApiTicket;
import com.machine.weixin.repository.bean.weixin.user.UserInfo;
import com.machine.weixin.repository.bean.weixin.user.UserInfos;
import com.machine.weixin.repository.bean.weixin.user.UserList;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.*;

public interface WeixinRepository {
    // 消息管理 --> 模板消息接口
    // ===========================================

    /**
     * 获取模板列表
     */
    @GET("/plat-gateway/v1/weixin/weixin.cgi-bin.template.get_all_private_template/{token}")
    Single<TemplateInfos> getTemplateInfos(@Path("token") String token);

    /**
     * 发送模板消息
     */
    @POST("/plat-gateway/v1/weixin/weixin.cgi-bin.message.template.send/{token}")
    Single<SendTemplateMessageResp> sendTemplateMessage(@Path("token") String token,
                                                        @Body TemplateMessage msg);


    // 消息管理 --> 客服消息
    // ===========================================

    /**
     * 客服接口-发消息
     */
    @POST("/plat-gateway/v1/weixin/weixin.cgi-bin.message.custom.send/{token}")
    Single<PostResp> sendCustomMessage(@Path("token") String token,
                                       @Body CustomMessage msg);

    // 素材管理 --> 获取临时素材
    // ===========================================

    /**
     * 获取临时素材
     */
    @GET("/plat-gateway/v1/weixin/weixin.cgi-bin.media.get/{token}")
    Single<Response<byte[]>> getMedia(@Path("token") String token,
                                      @Query("media_id") String mediaId);


    /**
     * 获取素材总数
     */
    @GET("/plat-gateway/v1/weixin/weixin.cgi-bin.material.get_materialcount/{token}")
    Single<MediaCountResponse> getMediaCount(@Path("token") String token);

    /**
     * 获取素材列表
     */
    @POST("/plat-gateway/v1/weixin/weixin.cgi-bin.material.batchget_material/{token}")
    Single<BatchGetMaterialResponse> getMedias(@Path("token") String token,
                                               @Body BatchGetMaterialRequest request);

    // 用户管理接口 --> 获取用户基本信息(UnionID机制)
    // ===========================================

    /**
     * 获取用户基本信息（包括UnionID机制）
     */
    @GET("/plat-gateway/v1/weixin/weixin.cgi-bin.user.info/{token}")
    Single<UserInfo> getUserInfo(@Path("token") String token,
                                 @Query("openid") String openid);

    /**
     * 批量获取用户基本信息
     */
    @POST("/plat-gateway/v1/weixin/weixin.cgi-bin.user.info.batchget/{token}")
    Single<UserInfos> getUserInfos(@Path("token") String token,
                                   @Body UserList userList);


    // 账号管理 --> 生成带参数的二维码
    // ===========================================

    /**
     * 创建二维码
     */
    @POST("/plat-gateway/v1/weixin/weixin.cgi-bin.qrcode.create/{token}")
    Single<JsonNode> createQrcode(@Path("token") String token,
                                  @Body QrcodeCreate body);


    // 微信网页开发 --> 微信JS-SDK说明文档
    // ===========================================

    /**
     * 获取api_ticket
     */
    @GET("/plat-gateway/v1/weixin/weixin.cgi-bin.ticket.getticket/{token}")
    Single<ApiTicket> getApiTicket(@Path("token") String token);

    // 微信卡券
    // ===========================================

    /**
     * 查询微信卡券详情
     */
    @POST("/plat-gateway/v1/weixin/weixin.card.get/{token}")
    Single<String> getCard(@Path("token") String token,
                           @Body CardRequest request);

    /**
     * 查询code状态
     */
    @POST("/plat-gateway/v1/weixin/weixin.card.code.get/{token}")
    Single<CheckCardCodeResp> getCardCode(@Path("token") String token,
                                          @Body CardCodeRequest request);


    /**
     * 核销卡券
     */
    @POST("/plat-gateway/v1/weixin/weixin.card.code.consume/{token}")
    Single<ConsumeCardCodeResp> consumeCardCode(@Path("token") String token,
                                                @Body ConsumeCardCodeRequest request);
}
