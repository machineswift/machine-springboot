package com.machine.weixin.repository;

import com.machine.weixin.repository.bean.spectrum.SpectrumAttributes;
import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.*;

public interface SpectrumRepository {

    @FormUrlEncoded
    @POST("/login")
    Single<Response<Void>> login(@Field("userName") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("/attributes/{key}")
    Completable addAttribute(@Path("key") String key, @Field("value") String value);

    @FormUrlEncoded
    @PUT("/attributes/{key}")
    Completable updateAttribute(@Path("key") String key, @Field("value") String value);

    @DELETE("/attributes/{key}")
    Completable deleteAttribute(@Path("key") String key, @Query("dir") Boolean dir);

    @GET("/attributes/{key}")
    Single<SpectrumAttributes> getAttribute(@Path("key") String key);

}
