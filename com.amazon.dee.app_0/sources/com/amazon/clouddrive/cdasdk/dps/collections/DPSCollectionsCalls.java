package com.amazon.clouddrive.cdasdk.dps.collections;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface DPSCollectionsCalls {
    @NonNull
    Single<CollectionContentsResponse> getCollectionContents(@NonNull GetCollectionContentsRequest getCollectionContentsRequest);

    @NonNull
    Single<ListCollectionsResponse> getCollectionsById(@NonNull GetCollectionsByIdRequest getCollectionsByIdRequest);

    @NonNull
    Single<ListCollectionsResponse> listCollections(@NonNull ListCollectionsRequest listCollectionsRequest);
}
