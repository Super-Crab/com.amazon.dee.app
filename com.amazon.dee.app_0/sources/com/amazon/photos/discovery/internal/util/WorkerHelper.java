package com.amazon.photos.discovery.internal.util;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.work.WorkContinuation;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.internal.OpenForTesting;
import com.amazon.photos.discovery.metrics.DiscoveryMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkerHelper.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0007J,\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010J\u001d\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/WorkerHelper;", "", "systemUtil", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "combineWorkContinuations", "Landroidx/work/WorkContinuation;", "continuations", "", "getTimeDifferenceForNextAvailableTarget", "", "hourValue", "", "minuteValue", "secondValue", "millisecondValue", "isPermissionGranted", "", "context", "Landroid/content/Context;", "permission", "", "isPermissionGranted$AndroidPhotosDiscovery_release", "recordWorkerLifetimeMetric", "", "enqueuedTime", "componentName", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WorkerHelper {
    private final Logger logger;
    private final Metrics metrics;
    private final SystemUtil systemUtil;

    public WorkerHelper(@NotNull SystemUtil systemUtil, @NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.systemUtil = systemUtil;
        this.logger = logger;
        this.metrics = metrics;
    }

    public static /* synthetic */ long getTimeDifferenceForNextAvailableTarget$default(WorkerHelper workerHelper, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return workerHelper.getTimeDifferenceForNextAvailableTarget(i, i2, i3, i4);
    }

    @SuppressLint({"EnqueueWork"})
    @NotNull
    public final WorkContinuation combineWorkContinuations(@NotNull List<? extends WorkContinuation> continuations) {
        Intrinsics.checkParameterIsNotNull(continuations, "continuations");
        WorkContinuation combine = WorkContinuation.combine(continuations);
        Intrinsics.checkExpressionValueIsNotNull(combine, "WorkContinuation.combine(continuations)");
        return combine;
    }

    public final long getTimeDifferenceForNextAvailableTarget(int i, int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 0);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance().a…llisecondValue)\n        }");
        long timeInMillis = calendar.getTimeInMillis() - this.systemUtil.currentTimeMillis();
        if (timeInMillis < 0) {
            timeInMillis += TimeUnit.DAYS.toMillis(1L);
        }
        Logger logger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Time until next target is ");
        outline107.append(TimeUnit.MILLISECONDS.toMinutes(timeInMillis));
        outline107.append(" minutes");
        logger.i("WorkerHelper", outline107.toString());
        return timeInMillis;
    }

    public final boolean isPermissionGranted$AndroidPhotosDiscovery_release(@NotNull Context context, @NotNull String permission) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(permission, "permission");
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }

    public final void recordWorkerLifetimeMetric(long j, @NotNull String componentName) {
        Intrinsics.checkParameterIsNotNull(componentName, "componentName");
        if (j > 0) {
            this.metrics.recordSimpleDuration(componentName, DiscoveryMetrics.WorkerLifetimeComplete, this.systemUtil.elapsedRealTimeMillis() - j);
        }
    }
}
