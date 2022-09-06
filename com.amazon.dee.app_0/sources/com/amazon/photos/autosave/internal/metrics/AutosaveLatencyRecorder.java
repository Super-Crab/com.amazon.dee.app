package com.amazon.photos.autosave.internal.metrics;

import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.AutosaveEventObserver;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.autosave.model.MediaType;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveLatencyRecorder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\r\u0010\u0011\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\fH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;", "Lcom/amazon/photos/autosave/AutosaveEventObserver;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "systemUtil", "Lcom/amazon/photos/autosave/internal/utils/SystemUtil;", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/autosave/internal/utils/SystemUtil;)V", "latencyTime", "", "recordingFlag", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onAutosaveStateChanged", "", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "enabled", "", "onContentDeduped", "onContentDeduped$AndroidPhotosAutosave_release", "onNoUploadsScanComplete", "startAutosaveLatencyTimer", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveLatencyRecorder implements AutosaveEventObserver {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AutosaveLatencyRecorder";
    private long latencyTime;
    private final Metrics metrics;
    private final AtomicBoolean recordingFlag;
    private final SystemUtil systemUtil;

    /* compiled from: AutosaveLatencyRecorder.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder$Companion;", "", "()V", "TAG", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AutosaveLatencyRecorder(@NotNull Metrics metrics, @NotNull SystemUtil systemUtil) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        this.metrics = metrics;
        this.systemUtil = systemUtil;
        this.recordingFlag = new AtomicBoolean(false);
    }

    private final void startAutosaveLatencyTimer() {
        if (!this.recordingFlag.getAndSet(true)) {
            this.latencyTime = this.systemUtil.elapsedRealTimeMillis$AndroidPhotosAutosave_release();
            this.metrics.recordSimpleEvent(TAG, AutosaveLatencyRecorder$startAutosaveLatencyTimer$1.INSTANCE, new MetricRecordingType[0]);
        }
    }

    @Override // com.amazon.photos.autosave.AutosaveEventObserver
    public void onAutosaveStateChanged(@NotNull MediaType mediaType, boolean z) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        if (z) {
            startAutosaveLatencyTimer();
        }
    }

    public final void onContentDeduped$AndroidPhotosAutosave_release() {
        if (this.recordingFlag.getAndSet(false)) {
            this.metrics.recordSimpleEvent(TAG, AutosaveLatencyRecorder$onContentDeduped$1.INSTANCE, new MetricRecordingType[0]);
            this.metrics.recordSimpleDuration(TAG, AutosaveLatencyRecorder$onContentDeduped$2.INSTANCE, this.systemUtil.elapsedRealTimeMillis$AndroidPhotosAutosave_release() - this.latencyTime);
        }
    }

    @Override // com.amazon.photos.autosave.AutosaveEventObserver
    public void onNoUploadsScanComplete() {
    }
}
