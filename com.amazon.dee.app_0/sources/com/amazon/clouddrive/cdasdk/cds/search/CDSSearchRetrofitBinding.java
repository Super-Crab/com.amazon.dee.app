package com.amazon.clouddrive.cdasdk.cds.search;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSSearchRetrofitBinding {
    @GET("search/aggregation")
    Single<AggregationResponse> aggregation(@QueryMap Map<String, String> map);

    @GET("search/groups/{groupId}")
    Single<SearchKeyResponse> searchGroupNodes(@Path("groupId") String str, @QueryMap Map<String, String> map);

    @GET("search")
    Single<SearchKeyResponse> searchKey(@QueryMap Map<String, String> map);
}
