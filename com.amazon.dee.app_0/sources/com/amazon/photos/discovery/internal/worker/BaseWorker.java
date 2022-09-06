package com.amazon.photos.discovery.internal.worker;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BaseWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H$J\b\u0010\u0014\u001a\u00020\u0015H$J\b\u0010\u0016\u001a\u00020\u0011H$R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/BaseWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "componentFailureHappened", "", "componentInjected", "injectMethod", "Lkotlin/Function0;", "", "getInjectMethod", "()Lkotlin/jvm/functions/Function0;", "componentsSetup", "doWork", "Landroidx/work/ListenableWorker$Result;", "getMetricsObj", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getTag", "", "mainTask", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class BaseWorker extends Worker {
    private boolean componentFailureHappened;
    private boolean componentInjected;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
    }

    private final void componentsSetup() {
        if (!this.componentInjected) {
            getInjectMethod().mo12560invoke();
            this.componentInjected = true;
            getMetricsObj().recordSimpleEvent(getTag(), DiscoveryMetrics.InjectSucceed, new MetricRecordingType[0]);
            if (!this.componentFailureHappened) {
                return;
            }
            getMetricsObj().recordSimpleEvent(getTag(), DiscoveryMetrics.InjectFailHappened, new MetricRecordingType[0]);
        }
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        try {
            componentsSetup();
            return mainTask();
        } catch (IllegalStateException unused) {
            this.componentFailureHappened = true;
            ListenableWorker.Result retry = ListenableWorker.Result.retry();
            Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
            return retry;
        }
    }

    @NotNull
    protected abstract Function0<Unit> getInjectMethod();

    @NotNull
    protected abstract Metrics getMetricsObj();

    @NotNull
    protected abstract String getTag();

    @NotNull
    protected abstract ListenableWorker.Result mainTask();
}
