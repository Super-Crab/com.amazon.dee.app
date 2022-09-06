package com.amazon.clouddrive.cdasdk.cds.child;

import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface CDSChildRetrofitBinding {
    @PUT("nodes/{parentId}/children/{childId}")
    Single<NodeInfoResponse> addChild(@Path("parentId") String str, @Path("childId") String str2, @Body AddChildRequest addChildRequest);

    @GET("nodes/{id}/children")
    Single<ListNodeResponse> listChildren(@Path("id") String str, @QueryMap Map<String, String> map);

    @HTTP(hasBody = true, method = Constants.REQUEST_METHOD_DELETE, path = "nodes/{parentId}/children/{childId}")
    Single<NodeInfoResponse> removeChild(@Path("parentId") String str, @Path("childId") String str2, @Body RemoveChildRequest removeChildRequest);
}
