package com.amazon.clouddrive.cdasdk.cds.child;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSChildCallsImpl implements CDSChildCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;
    @NonNull
    private final CDSChildRetrofitBinding cdsChildRetrofitBinding;

    public CDSChildCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.cdsChildRetrofitBinding = (CDSChildRetrofitBinding) retrofit.create(CDSChildRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.child.CDSChildCalls
    @NonNull
    public Single<NodeInfoResponse> addChild(@NonNull final AddChildRequest addChildRequest) {
        return this.callUtil.createCall("addChild", (String) addChildRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.child.-$$Lambda$CDSChildCallsImpl$StLjwPucFNJDk7Ql1m_5S6J9_oI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSChildCallsImpl.this.lambda$addChild$0$CDSChildCallsImpl(addChildRequest, (AddChildRequest) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$addChild$0$CDSChildCallsImpl(AddChildRequest addChildRequest, AddChildRequest addChildRequest2) throws Throwable {
        return this.cdsChildRetrofitBinding.addChild(addChildRequest.getParentId(), addChildRequest.getChildId(), addChildRequest);
    }

    public /* synthetic */ Single lambda$listChildren$1$CDSChildCallsImpl(ListChildrenRequest listChildrenRequest, Map map) throws Throwable {
        return this.cdsChildRetrofitBinding.listChildren(listChildrenRequest.getId(), map);
    }

    public /* synthetic */ Single lambda$removeChild$2$CDSChildCallsImpl(RemoveChildRequest removeChildRequest, RemoveChildRequest removeChildRequest2) throws Throwable {
        return this.cdsChildRetrofitBinding.removeChild(removeChildRequest.getParentId(), removeChildRequest.getChildId(), removeChildRequest);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.child.CDSChildCalls
    @NonNull
    public Single<ListNodeResponse> listChildren(@NonNull final ListChildrenRequest listChildrenRequest) {
        return this.callUtil.createCallWithQueryParameters("listChildren", listChildrenRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.child.-$$Lambda$CDSChildCallsImpl$y98ncwzhQu_wZIiKIsEIQmv4UGY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSChildCallsImpl.this.lambda$listChildren$1$CDSChildCallsImpl(listChildrenRequest, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.child.CDSChildCalls
    @NonNull
    public Single<NodeInfoResponse> removeChild(@NonNull final RemoveChildRequest removeChildRequest) {
        return this.callUtil.createCall("removeChild", (String) removeChildRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.child.-$$Lambda$CDSChildCallsImpl$dMsV8CbPlE8lYiwaUovXv2OgaYM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSChildCallsImpl.this.lambda$removeChild$2$CDSChildCallsImpl(removeChildRequest, (RemoveChildRequest) obj);
            }
        });
    }
}
