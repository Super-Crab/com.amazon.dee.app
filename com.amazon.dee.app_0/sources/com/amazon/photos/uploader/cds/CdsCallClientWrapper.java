package com.amazon.photos.uploader.cds;

import androidx.annotation.WorkerThread;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.CDSCalls;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion;
import com.amazon.clouddrive.cdasdk.cds.node.GetNodeRequest;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeRequest;
import com.amazon.clouddrive.cdasdk.cds.node.ListNodeResponse;
import com.amazon.clouddrive.cdasdk.cds.node.NodeNameFilter;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsCallClientWrapper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000  2\u00020\u0001:\u0001 B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u001d\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u001f\u0010\u0015\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\u001aJ\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u001c2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\u001dJ\u000f\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "createGetNodeRequest", "Lcom/amazon/clouddrive/cdasdk/cds/node/GetNodeRequest;", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "createListNodesRequest", "Lcom/amazon/clouddrive/cdasdk/cds/node/ListNodeRequest;", MessagingControllerConstant.MESSAGING_CONTROLLER_FILTER_KEY, "createNode", Message.SERIALIZED_NAME_PARENT_ID, "folderName", "createNode$AndroidPhotosUploader_release", "getCdsNodeId", "getChildNodeId", "childName", "getChildNodeId$AndroidPhotosUploader_release", "getNode", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfoResponse;", "getNode$AndroidPhotosUploader_release", "getNodeObservable", "Lio/reactivex/rxjava3/core/Single;", "getNodeObservable$AndroidPhotosUploader_release", "getRootNodeId", "getRootNodeId$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@WorkerThread
/* loaded from: classes13.dex */
public final class CdsCallClientWrapper {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CdsCallClientWrapper";
    private final CDClient cdClient;
    private final UploadLogger logger;
    private final Metrics metrics;

    /* compiled from: CdsCallClientWrapper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CdsCallClientWrapper(@NotNull CDClient cdClient, @NotNull UploadLogger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.cdClient = cdClient;
        this.logger = logger;
        this.metrics = metrics;
    }

    private final GetNodeRequest createGetNodeRequest(String str) {
        GetNodeRequest getNodeRequest = new GetNodeRequest();
        getNodeRequest.setId(str);
        getNodeRequest.setResourceVersion(ResourceVersion.V2);
        return getNodeRequest;
    }

    private final ListNodeRequest createListNodesRequest(String str) {
        ListNodeRequest listNodeRequest = new ListNodeRequest();
        listNodeRequest.setFilters(str);
        listNodeRequest.setResourceVersion(ResourceVersion.V2);
        return listNodeRequest;
    }

    private final String getCdsNodeId(String str) {
        ListNodeRequest createListNodesRequest = createListNodesRequest(str);
        CDSCalls cDSCalls = this.cdClient.getCDSCalls();
        Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
        ListNodeResponse response = cDSCalls.getNodeCalls().listNodes(createListNodesRequest).blockingGet();
        Intrinsics.checkExpressionValueIsNotNull(response, "response");
        if (response.getData() != null) {
            List<NodeInfo> data = response.getData();
            Intrinsics.checkExpressionValueIsNotNull(data, "response.data");
            if (!(!data.isEmpty())) {
                return null;
            }
            if (response.getData().size() > 1) {
                this.logger.w$AndroidPhotosUploader_release(TAG, "More than 1 response for the filter");
                this.metrics.recordSimpleEvent(TAG, CdsCallClientWrapper$getCdsNodeId$1.INSTANCE, new MetricRecordingType[0]);
            }
            NodeInfo nodeInfo = response.getData().get(0);
            if (nodeInfo == null) {
                return null;
            }
            return nodeInfo.getId();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String createNode$AndroidPhotosUploader_release(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull java.lang.String r7) throws com.amazon.clouddrive.cdasdk.cds.CDSException, com.amazon.clouddrive.cdasdk.CloudDriveException, java.io.IOException, java.io.InterruptedIOException {
        /*
            r5 = this;
            java.lang.String r0 = "parentId"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "folderName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            com.amazon.clouddrive.cdasdk.cds.node.PostNodeRequest r0 = new com.amazon.clouddrive.cdasdk.cds.node.PostNodeRequest     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            r0.<init>()     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            r0.setName(r7)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.String r7 = "FOLDER"
            r0.setKind(r7)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            r0.setParents(r6)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion r6 = com.amazon.clouddrive.cdasdk.cds.common.ResourceVersion.V2     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            r0.setResourceVersion(r6)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            com.amazon.clouddrive.cdasdk.CDClient r6 = r5.cdClient     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            com.amazon.clouddrive.cdasdk.cds.CDSCalls r6 = r6.getCDSCalls()     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.String r7 = "cdClient.cdsCalls"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls r6 = r6.getNodeCalls()     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            io.reactivex.rxjava3.core.Single r6 = r6.postNode(r0)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.Object r6 = r6.blockingGet()     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse r6 = (com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse) r6     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.String r7 = "response"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.String r6 = r6.getId()     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            java.lang.String r7 = "response.id"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)     // Catch: com.amazon.clouddrive.cdasdk.cds.CDSException -> L4b
            return r6
        L4b:
            r6 = move-exception
            int r7 = r6.getCode()
            com.amazon.clouddrive.cdasdk.cds.CDSError r0 = r6.getCdsError()
            java.lang.String r1 = "ex.cdsError"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            com.amazon.clouddrive.cdasdk.cds.CDSErrorInfo r0 = r0.getInfo()
            if (r0 == 0) goto L64
            java.lang.String r0 = r0.getNodeId()
            goto L65
        L64:
            r0 = 0
        L65:
            r1 = 409(0x199, float:5.73E-43)
            java.lang.String r2 = "CdsCallClientWrapper"
            r3 = 0
            if (r7 != r1) goto L8e
            com.amazon.clouddrive.android.core.interfaces.Metrics r7 = r5.metrics
            com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$1 r1 = com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$1.INSTANCE
            com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[] r4 = new com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[r3]
            r7.recordSimpleEvent(r2, r1, r4)
            if (r0 == 0) goto L80
            boolean r7 = kotlin.text.StringsKt.isBlank(r0)
            if (r7 == 0) goto L7e
            goto L80
        L7e:
            r7 = r3
            goto L81
        L80:
            r7 = 1
        L81:
            if (r7 != 0) goto L84
            return r0
        L84:
            com.amazon.clouddrive.android.core.interfaces.Metrics r7 = r5.metrics
            com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$2 r0 = com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$2.INSTANCE
            com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[] r1 = new com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[r3]
            r7.recordSimpleEvent(r2, r0, r1)
            goto L97
        L8e:
            com.amazon.clouddrive.android.core.interfaces.Metrics r7 = r5.metrics
            com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$3 r0 = com.amazon.photos.uploader.cds.CdsCallClientWrapper$createNode$3.INSTANCE
            com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[] r1 = new com.amazon.clouddrive.android.core.interfaces.MetricRecordingType[r3]
            r7.recordSimpleEvent(r2, r0, r1)
        L97:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.cds.CdsCallClientWrapper.createNode$AndroidPhotosUploader_release(java.lang.String, java.lang.String):java.lang.String");
    }

    @Nullable
    public final String getChildNodeId$AndroidPhotosUploader_release(@NotNull String parentId, @NotNull String childName) throws CloudDriveException, IOException, InterruptedIOException {
        Intrinsics.checkParameterIsNotNull(parentId, "parentId");
        Intrinsics.checkParameterIsNotNull(childName, "childName");
        NodeNameFilter build = new NodeNameFilter.Builder(childName).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "NodeNameFilter.Builder(childName).build()");
        return getCdsNodeId(GeneratedOutlineSupport1.outline76(build.getFilter(), " AND parents:", parentId, " AND kind:FOLDER"));
    }

    @NotNull
    public final NodeInfoResponse getNode$AndroidPhotosUploader_release(@NotNull String nodeId) throws CloudDriveException, IOException, InterruptedIOException {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        NodeInfoResponse blockingGet = getNodeObservable$AndroidPhotosUploader_release(nodeId).blockingGet();
        Intrinsics.checkExpressionValueIsNotNull(blockingGet, "getNodeObservable(nodeId).blockingGet()");
        return blockingGet;
    }

    @NotNull
    public final Single<NodeInfoResponse> getNodeObservable$AndroidPhotosUploader_release(@NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        GetNodeRequest createGetNodeRequest = createGetNodeRequest(nodeId);
        CDSCalls cDSCalls = this.cdClient.getCDSCalls();
        Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
        Single<NodeInfoResponse> node = cDSCalls.getNodeCalls().getNode(createGetNodeRequest);
        Intrinsics.checkExpressionValueIsNotNull(node, "cdClient.cdsCalls.nodeCa…s.getNode(getNodeRequest)");
        return node;
    }

    @Nullable
    public final String getRootNodeId$AndroidPhotosUploader_release() throws CloudDriveException, IOException, InterruptedIOException {
        return getCdsNodeId("isRoot:true");
    }
}
