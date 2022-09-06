package com.amazon.clouddrive.cdasdk.cds.search;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSSearchCallsImpl implements CDSSearchCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;
    @NonNull
    private final CDSSearchRetrofitBinding searchRetrofitBinding;

    public CDSSearchCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.searchRetrofitBinding = (CDSSearchRetrofitBinding) retrofit.create(CDSSearchRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCalls
    @NonNull
    public Single<AggregationResponse> aggregation(@NonNull AggregationRequest aggregationRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSSearchRetrofitBinding cDSSearchRetrofitBinding = this.searchRetrofitBinding;
        cDSSearchRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("aggregation", aggregationRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.search.-$$Lambda$M44LmEjRU6X0h1TtsGYql83NKWY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSSearchRetrofitBinding.this.aggregation((Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$searchGroupNodes$0$CDSSearchCallsImpl(SearchGroupNodesRequest searchGroupNodesRequest, Map map) throws Throwable {
        return this.searchRetrofitBinding.searchGroupNodes(searchGroupNodesRequest.getGroupId(), map);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCalls
    @NonNull
    public Single<SearchKeyResponse> searchGroupNodes(@NonNull final SearchGroupNodesRequest searchGroupNodesRequest) {
        return this.callUtil.createCallWithQueryParameters("searchGroupNodes", searchGroupNodesRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.search.-$$Lambda$CDSSearchCallsImpl$1VCPeZIpLTgrNMQ-FXOrirCM9Zg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSSearchCallsImpl.this.lambda$searchGroupNodes$0$CDSSearchCallsImpl(searchGroupNodesRequest, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCalls
    @NonNull
    public Single<SearchKeyResponse> searchKey(@NonNull SearchKeyRequest searchKeyRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSSearchRetrofitBinding cDSSearchRetrofitBinding = this.searchRetrofitBinding;
        cDSSearchRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("searchKey", searchKeyRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.search.-$$Lambda$xSoDka6CFcKkAqwT9H-6sU568Rw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSSearchRetrofitBinding.this.searchKey((Map) obj);
            }
        });
    }
}
