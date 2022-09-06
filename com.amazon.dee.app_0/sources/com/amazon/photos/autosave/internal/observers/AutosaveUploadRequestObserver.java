package com.amazon.photos.autosave.internal.observers;

import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.AutosaveUtilKt;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.model.AutosaveState;
import com.amazon.photos.uploader.AbandonReason;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveUploadRequestObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\"\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J \u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/photos/autosave/internal/observers/AutosaveUploadRequestObserver;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "autosaveItemDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "onRequestAdded", "", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "onRequestsAbandoned", "abandonedRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadBlocked", "blocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "onUploadFailed", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadProgressUpdate", "currentProgress", "", "maxProgress", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveUploadRequestObserver implements UploadRequestObserver {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AutosaveUploadRequestObserver";
    private final AutosaveItemDao autosaveItemDao;
    private final Metrics metrics;

    /* compiled from: AutosaveUploadRequestObserver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/observers/AutosaveUploadRequestObserver$Companion;", "", "()V", "TAG", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AutosaveUploadRequestObserver(@NotNull AutosaveItemDao autosaveItemDao, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(autosaveItemDao, "autosaveItemDao");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.autosaveItemDao = autosaveItemDao;
        this.metrics = metrics;
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NotNull List<AbandonedRequestInfo> abandonedRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(abandonedRequestInfoList, "abandonedRequestInfoList");
        for (AbandonedRequestInfo abandonedRequestInfo : abandonedRequestInfoList) {
            if (AutosaveUtilKt.isFromAutosave(abandonedRequestInfo.getUploadRequest()) && abandonedRequestInfo.getReason() != AbandonReason.APPLICATION_CANCELLED) {
                this.autosaveItemDao.updateStateByFilePath(abandonedRequestInfo.getUploadRequest().getFilePath(), AutosaveState.FAILED);
                this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onRequestsAbandoned$1$1.INSTANCE, new MetricRecordingType[0]);
            }
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        UploadRequestObserver.DefaultImpls.onUploadBlocked(this, uploadRequest, blockers);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        if (AutosaveUtilKt.isFromAutosave(uploadRequest)) {
            this.autosaveItemDao.updateStateByFilePath(uploadRequest.getFilePath(), AutosaveState.FAILED);
            this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onUploadFailed$1.INSTANCE, new MetricRecordingType[0]);
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NotNull UploadRequest uploadRequest, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        if (AutosaveUtilKt.isFromAutosave(uploadRequest)) {
            this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onUploadStarted$1.INSTANCE, new MetricRecordingType[0]);
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
        if (AutosaveUtilKt.isFromAutosave(uploadRequest)) {
            this.autosaveItemDao.updateStateByFilePath(uploadRequest.getFilePath(), AutosaveState.SYNCED);
            this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onUploadSucceeded$1.INSTANCE, new MetricRecordingType[0]);
            if (!resultMetadata.getBoolean(CdsUploader.RESULT_SUCCESS_WITH_CONFLICT, false)) {
                return;
            }
            this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onUploadSucceeded$2.INSTANCE, new MetricRecordingType[0]);
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
        if (AutosaveUtilKt.isFromAutosave(uploadRequest)) {
            this.metrics.recordSimpleEvent(TAG, AutosaveUploadRequestObserver$onUploadBlocked$1.INSTANCE, new MetricRecordingType[0]);
        }
    }
}
