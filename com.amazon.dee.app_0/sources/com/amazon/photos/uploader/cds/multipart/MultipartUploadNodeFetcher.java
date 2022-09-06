package com.amazon.photos.uploader.cds.multipart;

import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MultipartUploadNodeFetcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JT\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\f2!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00110\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;", "", "uploadScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "(Lio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;)V", "fetchNodeMetadata", "Lio/reactivex/rxjava3/disposables/Disposable;", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "onSuccess", "Lkotlin/Function1;", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "Lkotlin/ParameterName;", "name", "response", "", "onError", "Lcom/amazon/photos/uploader/UploadResponse;", "error", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadNodeFetcher {
    private final CdsCallClientWrapper cdsCallClientWrapper;
    private final Scheduler uploadScheduler;

    public MultipartUploadNodeFetcher(@NotNull Scheduler uploadScheduler, @NotNull CdsCallClientWrapper cdsCallClientWrapper) {
        Intrinsics.checkParameterIsNotNull(uploadScheduler, "uploadScheduler");
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        this.uploadScheduler = uploadScheduler;
        this.cdsCallClientWrapper = cdsCallClientWrapper;
    }

    @NotNull
    public final Disposable fetchNodeMetadata(@NotNull String nodeId, @NotNull final Function1<? super NodeInfo, Unit> onSuccess, @NotNull final Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        Disposable subscribe = this.cdsCallClientWrapper.getNodeObservable$AndroidPhotosUploader_release(nodeId).subscribeOn(this.uploadScheduler).observeOn(this.uploadScheduler).subscribe(new Consumer<NodeInfoResponse>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher$fetchNodeMetadata$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(NodeInfoResponse node) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(node, "node");
                function1.mo12165invoke(node);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher$fetchNodeMetadata$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it2) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                function1.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, it2, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "cdsCallClientWrapper.get…      }\n                )");
        return subscribe;
    }
}
