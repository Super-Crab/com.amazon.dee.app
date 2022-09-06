package com.amazon.photos.uploader.blockers;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NetworkGlobalBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0013H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkGlobalBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/NetworkListener;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "(Lcom/amazon/photos/uploader/internal/utils/SystemUtil;Lcom/amazon/photos/uploader/SchedulingCallback;)V", "mostRecentNetworkState", "Lcom/amazon/photos/uploader/blockers/NetworkState;", "getMostRecentNetworkState", "()Lcom/amazon/photos/uploader/blockers/NetworkState;", "setMostRecentNetworkState", "(Lcom/amazon/photos/uploader/blockers/NetworkState;)V", "getSchedulingCallback", "()Lcom/amazon/photos/uploader/SchedulingCallback;", "getSystemUtil", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "getNetworkState", "onNetworkChange", "", "networkState", "onNetworkLoss", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NetworkGlobalBlockerEvaluator implements GlobalBlockerEvaluator, NetworkListener {
    @Nullable
    private NetworkState mostRecentNetworkState;
    @NotNull
    private final SchedulingCallback schedulingCallback;
    @NotNull
    private final SystemUtil systemUtil;

    public NetworkGlobalBlockerEvaluator(@NotNull SystemUtil systemUtil, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        this.systemUtil = systemUtil;
        this.schedulingCallback = schedulingCallback;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker getBlocker() {
        Blocker queryBlocker = queryBlocker();
        if (Intrinsics.areEqual(queryBlocker, NoNetworkBlocker.INSTANCE)) {
            this.schedulingCallback.reevaluateBlockers$AndroidPhotosUploader_release(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build());
        }
        return queryBlocker;
    }

    @Nullable
    public final NetworkState getMostRecentNetworkState() {
        return this.mostRecentNetworkState;
    }

    @NotNull
    public final NetworkState getNetworkState() {
        return this.systemUtil.getNetworkState();
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
        Intrinsics.checkParameterIsNotNull(networkState, "networkState");
        if (this.mostRecentNetworkState == null || !networkState.isInternetConnected()) {
            this.schedulingCallback.reevaluateBlockers();
        }
        this.mostRecentNetworkState = networkState;
    }

    @Override // com.amazon.photos.uploader.blockers.NetworkListener
    public void onNetworkLoss() {
        this.schedulingCallback.reevaluateBlockers();
        this.mostRecentNetworkState = null;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker queryBlocker() {
        NetworkState networkState = getNetworkState();
        this.mostRecentNetworkState = networkState;
        if (networkState.isInternetConnected()) {
            return null;
        }
        return NoNetworkBlocker.INSTANCE;
    }

    public final void setMostRecentNetworkState(@Nullable NetworkState networkState) {
        this.mostRecentNetworkState = networkState;
    }
}
