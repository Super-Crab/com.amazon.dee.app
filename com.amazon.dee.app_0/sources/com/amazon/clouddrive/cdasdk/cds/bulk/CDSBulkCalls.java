package com.amazon.clouddrive.cdasdk.cds.bulk;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface CDSBulkCalls {
    @NonNull
    Single<BulkGetNodesByDigestResponse> bulkGetNodesByDigest(@NonNull BulkGetNodesByDigestRequest bulkGetNodesByDigestRequest);
}
