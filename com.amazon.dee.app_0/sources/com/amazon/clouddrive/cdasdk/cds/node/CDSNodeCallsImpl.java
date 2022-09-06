package com.amazon.clouddrive.cdasdk.cds.node;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.clouddrive.cdasdk.cds.common.NodeRequest;
import com.amazon.photos.discovery.metrics.DiscoveryMetricsKt;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSNodeCallsImpl implements CDSNodeCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> cloudDriveRequestCallUtil;
    @NonNull
    private final CDSCallUtil<NodeRequest> nodeRequestCallUtil;
    @NonNull
    private final CDSNodeRetrofitBinding nodeRetrofitBinding;

    public CDSNodeCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull CDSCallUtil<NodeRequest> cDSCallUtil2, @NonNull Retrofit retrofit) {
        this.cloudDriveRequestCallUtil = cDSCallUtil;
        this.nodeRequestCallUtil = cDSCallUtil2;
        this.nodeRetrofitBinding = (CDSNodeRetrofitBinding) retrofit.create(CDSNodeRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<NodeInfoResponse> getNode(@NonNull final GetNodeRequest getNodeRequest) {
        return this.cloudDriveRequestCallUtil.createCallWithQueryParameters("getNode", getNodeRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$CDSNodeCallsImpl$7a5DEl-tp3pN5XIKvy7T6FT9r14
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeCallsImpl.this.lambda$getNode$0$CDSNodeCallsImpl(getNodeRequest, (Map) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$getNode$0$CDSNodeCallsImpl(GetNodeRequest getNodeRequest, Map map) throws Throwable {
        return this.nodeRetrofitBinding.getNode(getNodeRequest.getId(), map);
    }

    public /* synthetic */ Single lambda$postNode$1$CDSNodeCallsImpl(PostNodeRequest postNodeRequest, String str, PostNodeRequest postNodeRequest2) throws Throwable {
        return this.nodeRetrofitBinding.postNode(postNodeRequest.getLocalId(), str, postNodeRequest);
    }

    public /* synthetic */ Single lambda$putNode$2$CDSNodeCallsImpl(PutNodeRequest putNodeRequest, String str, PutNodeRequest putNodeRequest2) throws Throwable {
        return this.nodeRetrofitBinding.putNode(putNodeRequest.getId(), str, putNodeRequest);
    }

    public /* synthetic */ Single lambda$trashNode$4$CDSNodeCallsImpl(TrashRequest trashRequest, Map map) throws Throwable {
        return this.nodeRetrofitBinding.trashNode(trashRequest.getId(), map);
    }

    public /* synthetic */ Single lambda$updateNode$3$CDSNodeCallsImpl(UpdateNodeRequest updateNodeRequest, String str, UpdateNodeRequest updateNodeRequest2) throws Throwable {
        return this.nodeRetrofitBinding.updateNode(updateNodeRequest.getId(), str, updateNodeRequest);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<ListNodeResponse> listNodes(@NonNull ListNodeRequest listNodeRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.cloudDriveRequestCallUtil;
        final CDSNodeRetrofitBinding cDSNodeRetrofitBinding = this.nodeRetrofitBinding;
        cDSNodeRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters(DiscoveryMetricsKt.METRICS_SERVICE_API_LIST_NODES, listNodeRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$MGiYP2iTJbCnsCv1Qw7AK4xO3ow
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeRetrofitBinding.this.listNodes((Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<NodeInfoResponse> postNode(@NonNull final PostNodeRequest postNodeRequest) {
        final String name = postNodeRequest.getResourceVersion() != null ? postNodeRequest.getResourceVersion().name() : null;
        return this.nodeRequestCallUtil.createCall("postNode", (String) postNodeRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$CDSNodeCallsImpl$QUUrMC_UiET4ke3w20vSuW-tYIQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeCallsImpl.this.lambda$postNode$1$CDSNodeCallsImpl(postNodeRequest, name, (PostNodeRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<NodeInfoResponse> putNode(@NonNull final PutNodeRequest putNodeRequest) {
        final String name = putNodeRequest.getResourceVersion() != null ? putNodeRequest.getResourceVersion().name() : null;
        return this.nodeRequestCallUtil.createCall("putNode", (String) putNodeRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$CDSNodeCallsImpl$N53u8KH91yN6GSA9DrNn-hVakho
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeCallsImpl.this.lambda$putNode$2$CDSNodeCallsImpl(putNodeRequest, name, (PutNodeRequest) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<NodeInfoResponse> trashNode(@NonNull final TrashRequest trashRequest) {
        return this.cloudDriveRequestCallUtil.createCallWithQueryParameters("trashNode", trashRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$CDSNodeCallsImpl$Ed6g3359Yf7sHEgiWyEVbPNgow0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeCallsImpl.this.lambda$trashNode$4$CDSNodeCallsImpl(trashRequest, (Map) obj);
            }
        });
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls
    @NonNull
    public Single<NodeInfoResponse> updateNode(@NonNull final UpdateNodeRequest updateNodeRequest) {
        final String name = updateNodeRequest.getResourceVersion() != null ? updateNodeRequest.getResourceVersion().name() : null;
        return this.nodeRequestCallUtil.createCall("updateNode", (String) updateNodeRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.node.-$$Lambda$CDSNodeCallsImpl$gDDiMNqV2bSp04BCVyNWT-5dQcM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSNodeCallsImpl.this.lambda$updateNode$3$CDSNodeCallsImpl(updateNodeRequest, name, (UpdateNodeRequest) obj);
            }
        });
    }
}
