package com.amazon.clouddrive.cdasdk.dps.settings;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
/* loaded from: classes11.dex */
public interface DPSSettingsRetrofitBinding {
    @GET("{principalType}/{principalId}/settings/screensaver/providerCollections")
    Single<GetSettingsResponse> getSettings(@Path("principalType") String str, @Path("principalId") String str2);
}
