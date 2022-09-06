package com.amazon.clouddrive.cdasdk.dps.collections;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface DPSCollectionsRetrofitBinding {
    @POST("collectionContents")
    Single<CollectionContentsResponse> getCollectionContents(@Body GetCollectionContentsRequest getCollectionContentsRequest);

    @POST("collections")
    Single<ListCollectionsResponse> getCollectionsById(@Body GetCollectionsByIdRequest getCollectionsByIdRequest);

    @GET("collections")
    Single<ListCollectionsResponse> listCollections(@QueryMap Map<String, String> map);
}
