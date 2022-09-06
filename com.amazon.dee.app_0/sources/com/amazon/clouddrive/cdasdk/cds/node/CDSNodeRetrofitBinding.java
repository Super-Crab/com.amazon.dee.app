package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSNodeRetrofitBinding {
    @GET("nodes/{id}")
    Single<NodeInfoResponse> getNode(@Path("id") String str, @QueryMap Map<String, String> map);

    @GET("nodes")
    Single<ListNodeResponse> listNodes(@QueryMap Map<String, String> map);

    @POST("nodes")
    Single<NodeInfoResponse> postNode(@Query("localId") String str, @Query("resourceVersion") String str2, @Body PostNodeRequest postNodeRequest);

    @PUT("nodes/{id}")
    Single<NodeInfoResponse> putNode(@Path("id") String str, @Query("resourceVersion") String str2, @Body PutNodeRequest putNodeRequest);

    @PUT("trash/{id}")
    Single<NodeInfoResponse> trashNode(@Path("id") String str, @QueryMap Map<String, String> map);

    @PATCH("nodes/{id}")
    Single<NodeInfoResponse> updateNode(@Path("id") String str, @Query("resourceVersion") String str2, @Body UpdateNodeRequest updateNodeRequest);
}
