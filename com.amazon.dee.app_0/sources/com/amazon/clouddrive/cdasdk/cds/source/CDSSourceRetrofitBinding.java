package com.amazon.clouddrive.cdasdk.cds.source;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSSourceRetrofitBinding {
    @GET("account/source")
    Single<ListSourcesResponse> listSources(@QueryMap Map<String, String> map);

    @POST("account/source")
    Single<SourceInfoResponse> setupAccount(@Body SetupSourceRequest setupSourceRequest);
}
