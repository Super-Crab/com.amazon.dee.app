package com.amazon.photos.discovery.internal.worker;

import amazon.speech.simclient.settings.Settings;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"recordAbortErrorAndFailWorker", "Landroidx/work/ListenableWorker$Result;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "componentName", "", Settings.ListeningSettings.EXTRA_REASON, "recordAndRetry", "getComponent", "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "Landroidx/work/Worker;", "AndroidPhotosDiscovery_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WorkerUtilKt {
    @NotNull
    public static final DiscoveryComponent getComponent(@NotNull Worker getComponent) {
        Intrinsics.checkParameterIsNotNull(getComponent, "$this$getComponent");
        String string = getComponent.getInputData().getString("account_id");
        if (string != null) {
            Intrinsics.checkExpressionValueIsNotNull(string, "inputData.getString(PARA…Id parameter is missing\")");
            DiscoveryComponent discoveryComponentForAccount$AndroidPhotosDiscovery_release = Discovery.Companion.discoveryComponentForAccount$AndroidPhotosDiscovery_release(string);
            if (discoveryComponentForAccount$AndroidPhotosDiscovery_release == null) {
                throw new IllegalStateException("No component for this worker's account id");
            }
            return discoveryComponentForAccount$AndroidPhotosDiscovery_release;
        }
        throw new IllegalArgumentException("Account Id parameter is missing");
    }

    @NotNull
    public static final ListenableWorker.Result recordAbortErrorAndFailWorker(@NotNull Metrics metrics, @NotNull String componentName, @NotNull final String reason) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        metrics.recordCustomMetric(componentName, new ClientMetric().withTagName(componentName).addCounter(new MetricName() { // from class: com.amazon.photos.discovery.internal.worker.WorkerUtilKt$recordAbortErrorAndFailWorker$abortMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return DiscoveryMetrics.DiscoveryAbortError.name() + reason;
            }
        }, 1), MetricRecordingType.STANDARD);
        ListenableWorker.Result failure = ListenableWorker.Result.failure();
        Intrinsics.checkExpressionValueIsNotNull(failure, "Result.failure()");
        return failure;
    }

    @NotNull
    public static final ListenableWorker.Result recordAndRetry(@NotNull Metrics metrics, @NotNull String componentName) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        metrics.recordSimpleEvent(componentName, DiscoveryMetrics.WorkerRetryOnError, MetricRecordingType.STANDARD);
        ListenableWorker.Result retry = ListenableWorker.Result.retry();
        Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
        return retry;
    }
}
