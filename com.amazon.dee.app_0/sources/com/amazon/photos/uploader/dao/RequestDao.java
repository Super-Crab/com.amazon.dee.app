package com.amazon.photos.uploader.dao;

import androidx.annotation.AnyThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import com.amazon.photos.uploader.UploadProgress;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.QuotaExceededBlocker;
import com.amazon.photos.uploader.internal.dao.LiveRequestDao;
import com.amazon.photos.uploader.internal.dao.SnapshotRequestDao;
import com.amazon.photos.uploader.internal.livedata.RunningRequestProvider;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RequestDao.kt */
@AnyThread
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u0017\u001a\u00020\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\u001a\u001a\u00020\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u000eJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u001e0!2\u0006\u0010\u0017\u001a\u00020\u0018J*\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u001e0!2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u000e2\u0006\u0010\u0017\u001a\u00020\u0018J(\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J(\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J\"\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u001e0!2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J0\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0006\u0012\u0004\u0018\u00010\u001e0!2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J \u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J.\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010J\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\f0\u0010J\"\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00130\u000e2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020,J8\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u000e2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020,2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00180/J\b\u00101\u001a\u00020\nH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/photos/uploader/dao/RequestDao;", "", "liveRequestDao", "Lcom/amazon/photos/uploader/internal/dao/LiveRequestDao;", "snapshotRequestDao", "Lcom/amazon/photos/uploader/internal/dao/SnapshotRequestDao;", "runningRequestProvider", "Lcom/amazon/photos/uploader/internal/livedata/RunningRequestProvider;", "(Lcom/amazon/photos/uploader/internal/dao/LiveRequestDao;Lcom/amazon/photos/uploader/internal/dao/SnapshotRequestDao;Lcom/amazon/photos/uploader/internal/livedata/RunningRequestProvider;)V", "getPendingRequestSummary", "Lcom/amazon/photos/uploader/dao/RequestSummary;", "queue", "", "getPendingRequestSummaryLiveData", "Landroidx/lifecycle/LiveData;", "getQuotaBlockedQueues", "", "Lcom/amazon/photos/uploader/dao/QuotaBlockedQueueInfo;", "getRequestCountByStates", "", "Lcom/amazon/photos/uploader/dao/StateWithCount;", "getRequestSummary", "getRequestSummaryForState", "state", "Lcom/amazon/photos/uploader/UploadState;", "getRequestSummaryForStateLiveData", "getRequestSummaryForStates", "states", "getRequestSummaryLiveData", "getRequestsForState", "Lcom/amazon/photos/uploader/UploadRequest;", "queues", "getRequestsForStateDataSource", "Landroidx/paging/DataSource$Factory;", "", "getRequestsForStateLiveData", "getRequestsForStates", "getRequestsForStatesDataSource", "getRequestsForStatesLiveData", "getRequestsWithBlockers", "blockers", "getRunningRequestsProgress", "updatesPer", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "getTotalProgress", "Lcom/amazon/photos/uploader/UploadProgress;", "", "requestStates", "getUploadedToFamilyVaultSummary", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RequestDao {
    private final LiveRequestDao liveRequestDao;
    private final RunningRequestProvider runningRequestProvider;
    private final SnapshotRequestDao snapshotRequestDao;

    public RequestDao(@NotNull LiveRequestDao liveRequestDao, @NotNull SnapshotRequestDao snapshotRequestDao, @NotNull RunningRequestProvider runningRequestProvider) {
        Intrinsics.checkParameterIsNotNull(liveRequestDao, "liveRequestDao");
        Intrinsics.checkParameterIsNotNull(snapshotRequestDao, "snapshotRequestDao");
        Intrinsics.checkParameterIsNotNull(runningRequestProvider, "runningRequestProvider");
        this.liveRequestDao = liveRequestDao;
        this.snapshotRequestDao = snapshotRequestDao;
        this.runningRequestProvider = runningRequestProvider;
    }

    @NotNull
    public final RequestSummary getPendingRequestSummary() {
        return this.snapshotRequestDao.getPendingRequestSummary();
    }

    @NotNull
    public final LiveData<RequestSummary> getPendingRequestSummaryLiveData() {
        return this.liveRequestDao.getPendingRequestSummary();
    }

    @Deprecated(message = "This was added for a Gallery use case, but should not be required for general use.")
    @NotNull
    public final Set<QuotaBlockedQueueInfo> getQuotaBlockedQueues() {
        Set<String> of;
        of = SetsKt__SetsJVMKt.setOf(QuotaExceededBlocker.INSTANCE.getName());
        List<UploadRequest> requestsWithBlockers = getRequestsWithBlockers(of);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (UploadRequest uploadRequest : requestsWithBlockers) {
            linkedHashSet.add(new QuotaBlockedQueueInfo(uploadRequest.getQueue(), uploadRequest.getUploadCategory()));
        }
        return linkedHashSet;
    }

    @NotNull
    public final List<StateWithCount> getRequestCountByStates() {
        return this.snapshotRequestDao.getRequestCountByStates();
    }

    @NotNull
    public final RequestSummary getRequestSummary() {
        return this.snapshotRequestDao.getRequestSummary();
    }

    @NotNull
    public final RequestSummary getRequestSummaryForState(@NotNull UploadState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return this.snapshotRequestDao.getRequestSummaryForState(state);
    }

    @NotNull
    public final LiveData<RequestSummary> getRequestSummaryForStateLiveData(@NotNull UploadState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return this.liveRequestDao.getRequestSummaryForState(state);
    }

    @NotNull
    public final RequestSummary getRequestSummaryForStates(@NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.snapshotRequestDao.getRequestSummaryForStates(states);
    }

    @NotNull
    public final LiveData<RequestSummary> getRequestSummaryLiveData() {
        return this.liveRequestDao.getRequestSummary();
    }

    @NotNull
    public final List<UploadRequest> getRequestsForState(@NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStates(of);
    }

    @NotNull
    public final DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(@NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStatesDataSource(of);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getRequestsForStateLiveData(@NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStatesLiveData(of);
    }

    @NotNull
    public final List<UploadRequest> getRequestsForStates(@NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.snapshotRequestDao.getRequestsForStates(states);
    }

    @NotNull
    public final DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(@NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.liveRequestDao.getRequestsForStatesDataSource(states);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getRequestsForStatesLiveData(@NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.liveRequestDao.getRequestsForStatesLiveData(states);
    }

    @NotNull
    public final List<UploadRequest> getRequestsWithBlockers(@NotNull Set<String> blockers) {
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        return this.snapshotRequestDao.getRequestsWithBlockers(blockers);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getRunningRequestsProgress(int i, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        return this.runningRequestProvider.getLiveData(i, timeUnit);
    }

    @NotNull
    public final LiveData<UploadProgress> getTotalProgress(int i, @NotNull TimeUnit timeUnit, @NotNull Collection<String> queues, @NotNull Collection<? extends UploadState> requestStates) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(requestStates, "requestStates");
        return this.runningRequestProvider.getTotalProgressLiveData(i, timeUnit, queues, requestStates);
    }

    @Deprecated(message = "This API was added to assist with the Gallery integration, but should not be required\n     * for general use.")
    @NotNull
    public final RequestSummary getUploadedToFamilyVaultSummary() {
        return this.snapshotRequestDao.getUploadedToFamilyVaultSummary();
    }

    @NotNull
    public final RequestSummary getPendingRequestSummary(@NotNull String queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        return this.snapshotRequestDao.getPendingRequestSummary(queue);
    }

    @NotNull
    public final LiveData<RequestSummary> getPendingRequestSummaryLiveData(@NotNull String queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        return this.liveRequestDao.getPendingRequestSummary(queue);
    }

    @NotNull
    public final RequestSummary getRequestSummaryForState(@NotNull UploadState state, @NotNull String queue) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        return this.snapshotRequestDao.getRequestSummaryForState(state, queue);
    }

    @NotNull
    public final LiveData<RequestSummary> getRequestSummaryForStateLiveData(@NotNull UploadState state, @NotNull String queue) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        return this.liveRequestDao.getRequestSummaryForState(state, queue);
    }

    @NotNull
    public final List<UploadRequest> getRequestsForState(@NotNull Set<String> queues, @NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStates(queues, of);
    }

    @NotNull
    public final DataSource.Factory<Integer, UploadRequest> getRequestsForStateDataSource(@NotNull Set<String> queues, @NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStatesDataSource(queues, of);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getRequestsForStateLiveData(@NotNull Set<String> queues, @NotNull UploadState state) {
        Set<? extends UploadState> of;
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(state, "state");
        of = SetsKt__SetsJVMKt.setOf(state);
        return getRequestsForStatesLiveData(queues, of);
    }

    @NotNull
    public final List<UploadRequest> getRequestsForStates(@NotNull Set<String> queues, @NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.snapshotRequestDao.getRequestsForStates(queues, states);
    }

    @NotNull
    public final DataSource.Factory<Integer, UploadRequest> getRequestsForStatesDataSource(@NotNull Set<String> queues, @NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.liveRequestDao.getRequestsForStatesDataSource(queues, states);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getRequestsForStatesLiveData(@NotNull Set<String> queues, @NotNull Set<? extends UploadState> states) {
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(states, "states");
        return this.liveRequestDao.getRequestsForStatesLiveData(queues, states);
    }
}
