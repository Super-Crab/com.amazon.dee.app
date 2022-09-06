package com.amazon.photos.uploader.internal.device;

import androidx.work.Constraints;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueConstraint;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.NotChargingBlocker;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ChargingBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/photos/uploader/internal/device/ChargingBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "batteryState", "Lcom/amazon/photos/uploader/internal/device/BatteryState;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "(Lcom/amazon/photos/uploader/internal/device/BatteryState;Lcom/amazon/photos/uploader/SchedulingCallback;)V", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queue", "Lcom/amazon/photos/uploader/Queue;", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ChargingBlockerEvaluator implements QueueBlockerEvaluator {
    private final BatteryState batteryState;
    private final SchedulingCallback schedulingCallback;

    public ChargingBlockerEvaluator(@NotNull BatteryState batteryState, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(batteryState, "batteryState");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        this.batteryState = batteryState;
        this.schedulingCallback = schedulingCallback;
    }

    @Override // com.amazon.photos.uploader.blockers.QueueBlockerEvaluator
    @Nullable
    public Blocker getBlocker(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Blocker queryBlocker = queryBlocker(queue);
        if (Intrinsics.areEqual(queryBlocker, NotChargingBlocker.INSTANCE)) {
            this.schedulingCallback.reevaluateBlockers$AndroidPhotosUploader_release(new Constraints.Builder().setRequiresCharging(true).build());
        }
        return queryBlocker;
    }

    @Override // com.amazon.photos.uploader.blockers.QueueBlockerEvaluator
    @Nullable
    public Blocker queryBlocker(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        if (!queue.getConstraints().contains(QueueConstraint.RequiresChargingConstraint.INSTANCE) || this.batteryState.isCharging()) {
            return null;
        }
        return NotChargingBlocker.INSTANCE;
    }
}
