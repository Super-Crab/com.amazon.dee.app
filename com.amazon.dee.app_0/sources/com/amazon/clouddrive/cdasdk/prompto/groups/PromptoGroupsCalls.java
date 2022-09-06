package com.amazon.clouddrive.cdasdk.prompto.groups;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface PromptoGroupsCalls {
    @NonNull
    Single<GroupResponse> createGroup(@NonNull CreateGroupRequest createGroupRequest);

    @NonNull
    Single<ListGroupsResponse> listGroups(@NonNull ListGroupsRequest listGroupsRequest);
}
