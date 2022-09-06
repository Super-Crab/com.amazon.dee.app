package com.amazon.clouddrive.cdasdk.cds.source;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSSourceCallsImpl implements CDSSourceCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;
    @NonNull
    private final CDSSourceRetrofitBinding sourceRetrofitBinding;

    public CDSSourceCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.sourceRetrofitBinding = (CDSSourceRetrofitBinding) retrofit.create(CDSSourceRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.CDSSourceCalls
    @NonNull
    public Single<ListSourcesResponse> listSources(@NonNull ListSourcesRequest listSourcesRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSSourceRetrofitBinding cDSSourceRetrofitBinding = this.sourceRetrofitBinding;
        cDSSourceRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("listSources", listSourcesRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.source.-$$Lambda$-DdEdSzaUTsISQ5LqLN-0yNCnhg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSSourceRetrofitBinding.this.listSources((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.CDSSourceCalls
    @NonNull
    public Single<SourceInfoResponse> setupSource(@NonNull SetupSourceRequest setupSourceRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSSourceRetrofitBinding cDSSourceRetrofitBinding = this.sourceRetrofitBinding;
        cDSSourceRetrofitBinding.getClass();
        return cDSCallUtil.createCall("setupSource", (String) setupSourceRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.source.-$$Lambda$WMzdvRLRxd7SwnYo4C13RUq9ifE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSSourceRetrofitBinding.this.setupAccount((SetupSourceRequest) obj);
            }
        });
    }
}
