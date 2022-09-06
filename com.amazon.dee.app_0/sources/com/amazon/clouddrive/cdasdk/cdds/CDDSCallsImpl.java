package com.amazon.clouddrive.cdasdk.cdds;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.io.InputStream;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDDSCallsImpl implements CDDSCalls {
    @NonNull
    private final CDDSCallsUtil callUtil;
    @NonNull
    private final CDDSRetrofitBinding retrofitBinding;

    public CDDSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.retrofitBinding = (CDDSRetrofitBinding) retrofit.create(CDDSRetrofitBinding.class);
        this.callUtil = new CDDSCallsUtil(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdds.CDDSCalls
    @NonNull
    public Single<InputStream> downloadNode(@NonNull final DownloadNodeRequest downloadNodeRequest) {
        return this.callUtil.createCallWithQueryParameters("downloadNode", downloadNodeRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cdds.-$$Lambda$CDDSCallsImpl$ZTUJrQc226O8b1iloPQgmE_L9ns
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDDSCallsImpl.this.lambda$downloadNode$0$CDDSCallsImpl(downloadNodeRequest, (Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$downloadNode$0$CDDSCallsImpl(DownloadNodeRequest downloadNodeRequest, Map map) throws Throwable {
        return this.retrofitBinding.downloadNode(downloadNodeRequest.getId(), map).map($$Lambda$UJJIKEFczutnvzizVh3kRkgDwg.INSTANCE);
    }
}
