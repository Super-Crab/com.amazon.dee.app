package com.amazon.photos.uploader.cds.multipart;

import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.cdus.CompleteMultipartRequest;
import com.amazon.clouddrive.cdasdk.cdus.CompleteMultipartResponse;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MultipartUploadCompleter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJf\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u00132!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00180\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "uploadScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;)V", "checkAndCompleteMultiPartUpload", "Lio/reactivex/rxjava3/disposables/Disposable;", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "", "serviceUploadId", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "onSuccess", "Lkotlin/Function1;", "Lcom/amazon/clouddrive/cdasdk/cdus/CompleteMultipartResponse;", "Lkotlin/ParameterName;", "name", "response", "", "onError", "Lcom/amazon/photos/uploader/UploadResponse;", "error", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MultipartUploadCompleter {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MultipartUploadCompleter";
    private final CDClient cdClient;
    private final UploadLogger logger;
    private final PartInfoDao partInfoDao;
    private final Scheduler uploadScheduler;

    /* compiled from: MultipartUploadCompleter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MultipartUploadCompleter(@NotNull CDClient cdClient, @NotNull Scheduler uploadScheduler, @NotNull UploadLogger logger, @NotNull PartInfoDao partInfoDao) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(uploadScheduler, "uploadScheduler");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        this.cdClient = cdClient;
        this.uploadScheduler = uploadScheduler;
        this.logger = logger;
        this.partInfoDao = partInfoDao;
    }

    @Nullable
    public final Disposable checkAndCompleteMultiPartUpload(@NotNull String nodeId, @NotNull String serviceUploadId, @NotNull UploadRequest uploadRequest, @NotNull final Function1<? super CompleteMultipartResponse, Unit> onSuccess, @NotNull final Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        Intrinsics.checkParameterIsNotNull(serviceUploadId, "serviceUploadId");
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(onSuccess, "onSuccess");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        List<PartInfo> allByStateForRequestId = this.partInfoDao.getAllByStateForRequestId(PartUploadState.FAILED, uploadRequest.getId());
        if (!allByStateForRequestId.isEmpty()) {
            UploadLogger uploadLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No of parts failed : ");
            outline107.append(allByStateForRequestId.size());
            uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
            for (PartInfo partInfo : allByStateForRequestId) {
                this.partInfoDao.updatePartInfoState(partInfo.getPartId(), uploadRequest.getId(), PartUploadState.ENQUEUED);
            }
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, new Throwable("Complete called on failed parts"), UploadErrorCategory.UPLOAD_PARTS_MISSED, null, false, 24, null));
            return null;
        }
        return this.cdClient.getCDUSCalls().completeMultipartUpload(new CompleteMultipartRequest(serviceUploadId), nodeId).subscribeOn(this.uploadScheduler).observeOn(this.uploadScheduler).subscribe(new Consumer<CompleteMultipartResponse>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter$checkAndCompleteMultiPartUpload$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(CompleteMultipartResponse it2) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                function1.mo12165invoke(it2);
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter$checkAndCompleteMultiPartUpload$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Function1 function1 = Function1.this;
                Intrinsics.checkExpressionValueIsNotNull(throwable, "throwable");
                function1.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, throwable, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
            }
        });
    }
}
