package com.amazon.clouddrive.cdasdk.prompto.groups;

import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import io.reactivex.rxjava3.core.Single;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
/* loaded from: classes11.dex */
public interface PromptoGroupsRetrofitBinding {
    @POST(FeatureName.GROUPS)
    Single<GroupResponse> createGroup(@Body CreateGroupRequest createGroupRequest);

    @GET(FeatureName.GROUPS)
    Single<ListGroupsResponse> listGroups(@QueryMap Map<String, String> map);
}
