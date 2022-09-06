package com.amazon.clouddrive.cdasdk.prompto.collections;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface GroupsCollectionsCalls {
    @NonNull
    Single<ListGroupCollectionsResponse> listGroupCollections(@NonNull ListGroupCollectionsRequest listGroupCollectionsRequest);
}
