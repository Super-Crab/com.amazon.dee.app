package com.amazon.clouddrive.cdasdk.prompto.groups;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.prompto.PromptoCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class PromptoGroupsCallsImpl implements PromptoGroupsCalls {
    @NonNull
    private final PromptoCallUtil callUtil;
    @NonNull
    private final PromptoGroupsRetrofitBinding retrofitBinding;

    public PromptoGroupsCallsImpl(@NonNull PromptoCallUtil promptoCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = promptoCallUtil;
        this.retrofitBinding = (PromptoGroupsRetrofitBinding) retrofit.create(PromptoGroupsRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.groups.PromptoGroupsCalls
    @NonNull
    public Single<GroupResponse> createGroup(@NonNull CreateGroupRequest createGroupRequest) {
        PromptoCallUtil promptoCallUtil = this.callUtil;
        final PromptoGroupsRetrofitBinding promptoGroupsRetrofitBinding = this.retrofitBinding;
        promptoGroupsRetrofitBinding.getClass();
        return promptoCallUtil.createCall("createGroup", (String) createGroupRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.prompto.groups.-$$Lambda$hSNeKJ4S6jtIw8TwtBIxA7hUIEY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return PromptoGroupsRetrofitBinding.this.createGroup((CreateGroupRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.groups.PromptoGroupsCalls
    @NonNull
    public Single<ListGroupsResponse> listGroups(@NonNull ListGroupsRequest listGroupsRequest) {
        PromptoCallUtil promptoCallUtil = this.callUtil;
        final PromptoGroupsRetrofitBinding promptoGroupsRetrofitBinding = this.retrofitBinding;
        promptoGroupsRetrofitBinding.getClass();
        return promptoCallUtil.createCallWithQueryParameters("listGroups", listGroupsRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.prompto.groups.-$$Lambda$2s_V6MzUnfw_w9omuDPWqiOWFNI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return PromptoGroupsRetrofitBinding.this.listGroups((Map) obj);
            }
        });
    }
}
