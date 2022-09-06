package com.amazon.photos.discovery.internal.worker;

import android.content.Context;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.internal.ConstantsKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MediaStoreChangeWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\"H\u0014R\u001e\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u00138\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00198\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006#"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MediaStoreChangeWorker;", "Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "getDiscovery$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/Discovery;", "setDiscovery$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/Discovery;)V", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "getLogger$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "setLogger$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Logger;)V", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getMetrics$AndroidPhotosDiscovery_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "setMetrics$AndroidPhotosDiscovery_release", "(Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "getMetricsObj", "getTag", "", "mainTask", "Landroidx/work/ListenableWorker$Result;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaStoreChangeWorker extends BaseWorker {
    @Inject
    @NotNull
    public Discovery discovery;
    @NotNull
    private final Function0<Unit> injectMethod;
    @Inject
    @NotNull
    public Logger logger;
    @Inject
    @NotNull
    public Metrics metrics;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaStoreChangeWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.injectMethod = new MediaStoreChangeWorker$injectMethod$1(this);
    }

    @NotNull
    public final Discovery getDiscovery$AndroidPhotosDiscovery_release() {
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        return discovery;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected Function0<Unit> getInjectMethod() {
        return this.injectMethod;
    }

    @NotNull
    public final Logger getLogger$AndroidPhotosDiscovery_release() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return logger;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosDiscovery_release() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected Metrics getMetricsObj() {
        Metrics metrics = this.metrics;
        if (metrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("metrics");
        }
        return metrics;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected String getTag() {
        return ConstantsKt.MEDIA_STORE_CHANGE;
    }

    @Override // com.amazon.photos.discovery.internal.worker.BaseWorker
    @NotNull
    protected ListenableWorker.Result mainTask() {
        Logger logger = this.logger;
        if (logger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        logger.i(ConstantsKt.MEDIA_STORE_CHANGE, "MediaStore content changed, running discovery flow");
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        discovery.getOperations().scan();
        Discovery discovery2 = this.discovery;
        if (discovery2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        discovery2.getOperations().scheduleMediaStoreWorkerIfNeeded$AndroidPhotosDiscovery_release(ExistingWorkPolicy.REPLACE);
        ListenableWorker.Result success = ListenableWorker.Result.success();
        Intrinsics.checkExpressionValueIsNotNull(success, "Result.success()");
        return success;
    }

    public final void setDiscovery$AndroidPhotosDiscovery_release(@NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(discovery, "<set-?>");
        this.discovery = discovery;
    }

    public final void setLogger$AndroidPhotosDiscovery_release(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "<set-?>");
        this.logger = logger;
    }

    public final void setMetrics$AndroidPhotosDiscovery_release(@NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "<set-?>");
        this.metrics = metrics;
    }
}
