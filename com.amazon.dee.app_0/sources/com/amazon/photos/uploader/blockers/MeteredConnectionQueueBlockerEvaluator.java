package com.amazon.photos.uploader.blockers;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueConstraint;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MeteredConnectionQueueBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/uploader/blockers/MeteredConnectionQueueBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/NetworkListener;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "(Lcom/amazon/photos/uploader/internal/utils/SystemUtil;Lcom/amazon/photos/uploader/SchedulingCallback;)V", "mostRecentNetworkState", "Lcom/amazon/photos/uploader/blockers/NetworkState;", "getSchedulingCallback", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "getSystemUtil", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queue", "Lcom/amazon/photos/uploader/Queue;", "getNetworkState", "onNetworkChange", "", "networkState", "onNetworkLoss", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MeteredConnectionQueueBlockerEvaluator implements QueueBlockerEvaluator, NetworkListener {
    private NetworkState mostRecentNetworkState;
    @NotNull
    private final SchedulingCallback schedulingCallback;
    @NotNull
    private final SystemUtil systemUtil;

    public MeteredConnectionQueueBlockerEvaluator(@NotNull SystemUtil systemUtil, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        this.systemUtil = systemUtil;
        this.schedulingCallback = schedulingCallback;
    }

    private final NetworkState getNetworkState() {
        return this.systemUtil.getNetworkState();
    }

    @Override // com.amazon.photos.uploader.blockers.QueueBlockerEvaluator
    @Nullable
    public Blocker getBlocker(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        Blocker queryBlocker = queryBlocker(queue);
        if (Intrinsics.areEqual(queryBlocker, MeteredConnectionBlocker.INSTANCE)) {
            this.schedulingCallback.reevaluateBlockers$AndroidPhotosUploader_release(new Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build());
        }
        return queryBlocker;
    }

    @NotNull
    public final SchedulingCallback getSchedulingCallback() {
        return this.schedulingCallback;
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        return this.systemUtil;
    }

    @Override // com.amazon.photos.uploader.blockers.NetworkListener
    public void onNetworkChange(@NotNull NetworkState networkState) {
        NetworkState networkState2;
        Intrinsics.checkParameterIsNotNull(networkState, "networkState");
        if (this.mostRecentNetworkState == null || (!networkState.isMetered() && (networkState2 = this.mostRecentNetworkState) != null && networkState2.isMetered())) {
            this.schedulingCallback.reevaluateBlockers();
        }
        this.mostRecentNetworkState = networkState;
    }

    @Override // com.amazon.photos.uploader.blockers.NetworkListener
    public void onNetworkLoss() {
        this.schedulingCallback.reevaluateBlockers();
        this.mostRecentNetworkState = null;
    }

    @Override // com.amazon.photos.uploader.blockers.QueueBlockerEvaluator
    @Nullable
    public Blocker queryBlocker(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        NetworkState networkState = getNetworkState();
        this.mostRecentNetworkState = networkState;
        if (!queue.getConstraints().contains(QueueConstraint.RequiresUnmeteredConnection.INSTANCE) || !networkState.isMetered()) {
            return null;
        }
        return MeteredConnectionBlocker.INSTANCE;
    }
}
