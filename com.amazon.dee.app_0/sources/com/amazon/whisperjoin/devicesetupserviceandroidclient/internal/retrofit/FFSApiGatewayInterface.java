package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/* loaded from: classes13.dex */
public interface FFSApiGatewayInterface {
    @GET("fetchConfig")
    Call<FFSWhiteListPolicyResponse> getFFSWhiteListPolicy(@Query("deviceModel") String str, @Query("manufacturer") String str2, @Query("firmwareVersion") String str3, @Query("platform") String str4, @Query("application") String str5, @Query("applicationVersion") String str6, @Query("marketplace") String str7, @Query("customerId") String str8);
}
