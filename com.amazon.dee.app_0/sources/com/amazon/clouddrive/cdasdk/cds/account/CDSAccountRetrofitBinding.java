package com.amazon.clouddrive.cdasdk.cds.account;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSAccountRetrofitBinding {
    @GET("account/info")
    Single<GetAccountInfoResponse> getAccountInfo(@QueryMap Map<String, String> map);

    @GET("account/benefits")
    Single<GetAvailableBenefitsResponse> getAvailableBenefits(@QueryMap Map<String, String> map);

    @GET("account/endpoint")
    Single<GetEndpointResponse> getEndpoint(@QueryMap Map<String, String> map);

    @GET("account/quota")
    Single<GetQuotaResponse> getQuota(@QueryMap Map<String, String> map);

    @GET("account/taggingStatus")
    Single<GetTaggingStatusResponse> getTaggingStatus(@QueryMap Map<String, String> map);

    @GET("account/usage")
    Single<GetUsageResponse> getUsage(@QueryMap Map<String, String> map);

    @PUT("account/preferences/{preferenceName}")
    Single<SetPersonalPreferenceResponse> setPersonalPreference(@Path("preferenceName") String str, @Body SetPersonalPreferenceRequest setPersonalPreferenceRequest);

    @POST("account")
    Single<SetupAccountResponse> setupAccount(@Body SetupAccountRequest setupAccountRequest);
}
