package com.amazon.clouddrive.cdasdk.prompto.collections;

import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface GroupsCollectionsRetrofitBinding {
    @GET("groups/{groupId}/collections")
    Single<ListGroupCollectionsResponse> listGroupCollections(@Path("groupId") String str, @QueryMap Map<String, String> map);
}
