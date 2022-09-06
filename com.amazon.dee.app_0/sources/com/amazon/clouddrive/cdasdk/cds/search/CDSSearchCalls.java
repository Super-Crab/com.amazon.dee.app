package com.amazon.clouddrive.cdasdk.cds.search;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSSearchCalls {
    @NonNull
    Single<AggregationResponse> aggregation(@NonNull AggregationRequest aggregationRequest);

    @NonNull
    Single<SearchKeyResponse> searchGroupNodes(@NonNull SearchGroupNodesRequest searchGroupNodesRequest);

    @NonNull
    Single<SearchKeyResponse> searchKey(@NonNull SearchKeyRequest searchKeyRequest);
}
