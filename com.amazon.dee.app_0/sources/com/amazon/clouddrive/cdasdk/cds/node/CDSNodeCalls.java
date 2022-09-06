package com.amazon.clouddrive.cdasdk.cds.node;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSNodeCalls {
    @NonNull
    Single<NodeInfoResponse> getNode(@NonNull GetNodeRequest getNodeRequest);

    @NonNull
    Single<ListNodeResponse> listNodes(@NonNull ListNodeRequest listNodeRequest);

    @NonNull
    Single<NodeInfoResponse> postNode(@NonNull PostNodeRequest postNodeRequest);

    @NonNull
    Single<NodeInfoResponse> putNode(@NonNull PutNodeRequest putNodeRequest);

    @NonNull
    Single<NodeInfoResponse> trashNode(@NonNull TrashRequest trashRequest);

    @NonNull
    Single<NodeInfoResponse> updateNode(@NonNull UpdateNodeRequest updateNodeRequest);
}
