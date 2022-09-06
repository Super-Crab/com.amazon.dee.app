package com.amazon.clouddrive.cdasdk.aps.account;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface APSAccountCallsRetrofitBinding {
    @GET("{resourceVersion}/account/state/{devicePlatform}")
    Single<AccountFeaturesOutput> getAccountFeatures(@Path("resourceVersion") String str, @Path("devicePlatform") String str2, @QueryMap Map<String, String> map);
}
