package com.amazon.clouddrive.cdasdk.cds.bulk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSBulkCallsImpl implements CDSBulkCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;
    @NonNull
    private final CDSBulkRetrofitBinding cdsBulkRetrofitBinding;

    public CDSBulkCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.cdsBulkRetrofitBinding = (CDSBulkRetrofitBinding) retrofit.create(CDSBulkRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.bulk.CDSBulkCalls
    @NonNull
    public Single<BulkGetNodesByDigestResponse> bulkGetNodesByDigest(@NonNull BulkGetNodesByDigestRequest bulkGetNodesByDigestRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSBulkRetrofitBinding cDSBulkRetrofitBinding = this.cdsBulkRetrofitBinding;
        cDSBulkRetrofitBinding.getClass();
        return cDSCallUtil.createCall("bulkGetNodesByDigest", (String) bulkGetNodesByDigestRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.bulk.-$$Lambda$RCvduhlBXwyVUxoLdtNo2Fh3bqc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSBulkRetrofitBinding.this.bulkGetNodesByDigest((BulkGetNodesByDigestRequest) obj);
            }
        });
    }
}
