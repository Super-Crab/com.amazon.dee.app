package com.amazon.clouddrive.cdasdk.dps.collections;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.dps.DPSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class DPSCollectionsCallsImpl implements DPSCollectionsCalls {
    @NonNull
    private final DPSCallUtil callUtil;
    @NonNull
    private final DPSCollectionsRetrofitBinding retrofitBinding;

    public DPSCollectionsCallsImpl(@NonNull DPSCallUtil dPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = dPSCallUtil;
        this.retrofitBinding = (DPSCollectionsRetrofitBinding) retrofit.create(DPSCollectionsRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCalls
    @NonNull
    public Single<CollectionContentsResponse> getCollectionContents(@NonNull GetCollectionContentsRequest getCollectionContentsRequest) {
        DPSCallUtil dPSCallUtil = this.callUtil;
        final DPSCollectionsRetrofitBinding dPSCollectionsRetrofitBinding = this.retrofitBinding;
        dPSCollectionsRetrofitBinding.getClass();
        return dPSCallUtil.createCall("getCollectionContents", (String) getCollectionContentsRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.collections.-$$Lambda$CwsjVNafIDieQDDClYJcw4uUVzI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSCollectionsRetrofitBinding.this.getCollectionContents((GetCollectionContentsRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCalls
    @NonNull
    public Single<ListCollectionsResponse> getCollectionsById(@NonNull GetCollectionsByIdRequest getCollectionsByIdRequest) {
        DPSCallUtil dPSCallUtil = this.callUtil;
        final DPSCollectionsRetrofitBinding dPSCollectionsRetrofitBinding = this.retrofitBinding;
        dPSCollectionsRetrofitBinding.getClass();
        return dPSCallUtil.createCall("getCollectionsById", (String) getCollectionsByIdRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.collections.-$$Lambda$9hbxkg4kWKniNmJJNp0aQW5xTs0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSCollectionsRetrofitBinding.this.getCollectionsById((GetCollectionsByIdRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.collections.DPSCollectionsCalls
    @NonNull
    public Single<ListCollectionsResponse> listCollections(@NonNull ListCollectionsRequest listCollectionsRequest) {
        DPSCallUtil dPSCallUtil = this.callUtil;
        final DPSCollectionsRetrofitBinding dPSCollectionsRetrofitBinding = this.retrofitBinding;
        dPSCollectionsRetrofitBinding.getClass();
        return dPSCallUtil.createCallWithQueryParameters("listCollections", listCollectionsRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.dps.collections.-$$Lambda$W12QTmxtZHVSDWIjQ1KYVThTf28
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DPSCollectionsRetrofitBinding.this.listCollections((Map) obj);
            }
        });
    }
}
