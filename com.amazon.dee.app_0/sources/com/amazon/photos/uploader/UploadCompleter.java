package com.amazon.photos.uploader;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.internal.metrics.UploadMetrics;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadCompleter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J \u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004J\u001d\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/photos/uploader/UploadCompleter;", "", "completer", "Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;", "Lcom/amazon/photos/uploader/UploadResponse;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "fileUtils", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "(Landroidx/concurrent/futures/CallbackToFutureAdapter$Completer;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/internal/utils/FileUtils;)V", "metricData", "Lcom/amazon/photos/uploader/MetricData;", "addCancellationListener", "", "cancellationListener", "Lcom/amazon/photos/uploader/CancellationListener;", "createFileForPath", "Ljava/io/File;", "filePath", "", "getMetricsSuffix", "file", "reportMetrics", "startTime", "", "metricName", "setResponse", "", "response", "startUploadMetric", "startUploadMetric$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadCompleter {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UploadCompleter";
    private final CallbackToFutureAdapter.Completer<UploadResponse> completer;
    private final FileUtils fileUtils;
    private MetricData metricData;
    private final Metrics metrics;

    /* compiled from: UploadCompleter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/UploadCompleter$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UploadCompleter(@NotNull CallbackToFutureAdapter.Completer<UploadResponse> completer, @NotNull Metrics metrics, @NotNull FileUtils fileUtils) {
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(fileUtils, "fileUtils");
        this.completer = completer;
        this.metrics = metrics;
        this.fileUtils = fileUtils;
    }

    private final File createFileForPath(String str) {
        return new File(str);
    }

    private final String getMetricsSuffix(File file) {
        return this.fileUtils.isSinglePartUploadAllowed(file) ? "SMALL" : "BIG";
    }

    private final void reportMetrics(long j, String str, File file) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder outline108 = GeneratedOutlineSupport1.outline108(str, '_');
        outline108.append(getMetricsSuffix(file));
        final String sb = outline108.toString();
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.UploadCompleter$reportMetrics$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return sb;
            }
        }, 1).addTimer(new MetricName() { // from class: com.amazon.photos.uploader.UploadCompleter$reportMetrics$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return sb;
            }
        }, currentTimeMillis - j).withTagName(TAG), new MetricRecordingType[0]);
    }

    public final void addCancellationListener(@NotNull final CancellationListener cancellationListener) {
        Intrinsics.checkParameterIsNotNull(cancellationListener, "cancellationListener");
        this.completer.addCancellationListener(new Runnable() { // from class: com.amazon.photos.uploader.UploadCompleter$addCancellationListener$1
            @Override // java.lang.Runnable
            public final void run() {
                CancellationListener.this.onCancelled();
            }
        }, Executors.newSingleThreadExecutor());
    }

    public final boolean setResponse(@NotNull UploadResponse response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        MetricData metricData = this.metricData;
        if (metricData != null) {
            if (response instanceof UploadResponse.Success) {
                reportMetrics(metricData.getStartTime(), "UPLOAD_SUCCEEDED", metricData.getFile());
            } else if ((response instanceof UploadResponse.NoRetryFailure) || (response instanceof UploadResponse.Failure)) {
                reportMetrics(metricData.getStartTime(), UploadMetrics.UPLOAD_ERROR, metricData.getFile());
            }
        }
        return this.completer.set(response);
    }

    public final void startUploadMetric$AndroidPhotosUploader_release(long j, @NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        this.metricData = new MetricData(j, createFileForPath(filePath));
    }
}
