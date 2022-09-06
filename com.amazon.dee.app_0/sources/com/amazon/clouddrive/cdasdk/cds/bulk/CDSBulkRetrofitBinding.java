package com.amazon.clouddrive.cdasdk.cds.bulk;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
/* loaded from: classes11.dex */
public interface CDSBulkRetrofitBinding {
    @POST("bulk/nodeByDigest")
    Single<BulkGetNodesByDigestResponse> bulkGetNodesByDigest(@Body BulkGetNodesByDigestRequest bulkGetNodesByDigestRequest);
}
