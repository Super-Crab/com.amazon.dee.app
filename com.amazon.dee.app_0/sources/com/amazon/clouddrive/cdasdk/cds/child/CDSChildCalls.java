package com.amazon.clouddrive.cdasdk.cds.child;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSChildCalls {
    @NonNull
    Single<NodeInfoResponse> addChild(@NonNull AddChildRequest addChildRequest);

    @NonNull
    Single<ListNodeResponse> listChildren(@NonNull ListChildrenRequest listChildrenRequest);

    @NonNull
    Single<NodeInfoResponse> removeChild(@NonNull RemoveChildRequest removeChildRequest);
}
