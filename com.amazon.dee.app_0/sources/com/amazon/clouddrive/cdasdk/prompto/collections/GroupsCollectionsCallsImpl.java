package com.amazon.clouddrive.cdasdk.prompto.collections;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.prompto.PromptoCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class GroupsCollectionsCallsImpl implements GroupsCollectionsCalls {
    @NonNull
    private final PromptoCallUtil callUtil;
    @NonNull
    private final GroupsCollectionsRetrofitBinding collectionsRetrofitBinding;

    public GroupsCollectionsCallsImpl(@NonNull PromptoCallUtil promptoCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = promptoCallUtil;
        this.collectionsRetrofitBinding = (GroupsCollectionsRetrofitBinding) retrofit.create(GroupsCollectionsRetrofitBinding.class);
    }

    public /* synthetic */ Single lambda$listGroupCollections$0$GroupsCollectionsCallsImpl(ListGroupCollectionsRequest listGroupCollectionsRequest, Map map) throws Throwable {
        return this.collectionsRetrofitBinding.listGroupCollections(listGroupCollectionsRequest.getGroupId(), map);
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.collections.GroupsCollectionsCalls
    @NonNull
    public Single<ListGroupCollectionsResponse> listGroupCollections(@NonNull final ListGroupCollectionsRequest listGroupCollectionsRequest) {
        return this.callUtil.createCallWithQueryParameters("listGroupCollections", listGroupCollectionsRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.prompto.collections.-$$Lambda$GroupsCollectionsCallsImpl$BOqQRv0hh1WHphASV361TFy467I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return GroupsCollectionsCallsImpl.this.lambda$listGroupCollections$0$GroupsCollectionsCallsImpl(listGroupCollectionsRequest, (Map) obj);
            }
        });
    }
}
