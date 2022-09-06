package com.amazon.photos.uploader.internal;

import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.dao.UploadRequestDao;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.QueueSummary;
import com.amazon.photos.uploader.observables.UploadSummary;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadSummaryTracker.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u00011B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u001b\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ \u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!J\u0016\u0010'\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010(\u001a\u00020)JH\u0010*\u001a\u00020\u00132\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0018\u0010,\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0-2\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0-J\u0010\u00100\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "", "requestDao", "Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;", "summaryNotifier", "Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;", "queueManager", "Lcom/amazon/photos/uploader/QueueManager;", "(Lcom/amazon/photos/uploader/internal/dao/UploadRequestDao;Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;Lcom/amazon/photos/uploader/QueueManager;)V", "currentGlobalBlockers", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "isUploaderRunning", "", "queueSummaryMap", "", "", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker$MutableQueueSummary;", "broadcastUpdate", "", "generateSummaryCopy", "", "Lcom/amazon/photos/uploader/observables/QueueSummary;", "getQueueSummary", "queueName", "getUploadSummary", "Lcom/amazon/photos/uploader/observables/UploadSummary;", "onUploadAbandoned", "uploadRequests", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadFailed", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "setBlockerSummary", "globalBlockers", "queueBlockerMap", "", "Lcom/amazon/photos/uploader/Queue;", "requestBlockerMap", "updateQueueCounts", "MutableQueueSummary", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadSummaryTracker {
    private Collection<? extends Blocker> currentGlobalBlockers;
    private boolean isUploaderRunning;
    private final QueueManager queueManager;
    private final Map<String, MutableQueueSummary> queueSummaryMap;
    private final UploadRequestDao requestDao;
    private final UploadSummaryNotifier summaryNotifier;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: UploadSummaryTracker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0017\b\u0002\u0018\u00002\u00020\u0001BW\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rR\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001a\u0010\f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011¨\u0006 "}, d2 = {"Lcom/amazon/photos/uploader/internal/UploadSummaryTracker$MutableQueueSummary;", "", "queue", "Lcom/amazon/photos/uploader/Queue;", "queueBlockers", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "requestBlockers", "queuedRequestCount", "", "completedRequestCount", "blockedRequestCount", "runningRequestCount", "(Lcom/amazon/photos/uploader/Queue;Ljava/util/Collection;Ljava/util/Collection;IIII)V", "getBlockedRequestCount", "()I", "setBlockedRequestCount", "(I)V", "getCompletedRequestCount", "setCompletedRequestCount", "getQueue", "()Lcom/amazon/photos/uploader/Queue;", "getQueueBlockers", "()Ljava/util/Collection;", "setQueueBlockers", "(Ljava/util/Collection;)V", "getQueuedRequestCount", "setQueuedRequestCount", "getRequestBlockers", "setRequestBlockers", "getRunningRequestCount", "setRunningRequestCount", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class MutableQueueSummary {
        private int blockedRequestCount;
        private int completedRequestCount;
        @NotNull
        private final Queue queue;
        @NotNull
        private Collection<? extends Blocker> queueBlockers;
        private int queuedRequestCount;
        @NotNull
        private Collection<? extends Blocker> requestBlockers;
        private int runningRequestCount;

        public MutableQueueSummary(@NotNull Queue queue, @NotNull Collection<? extends Blocker> queueBlockers, @NotNull Collection<? extends Blocker> requestBlockers, int i, int i2, int i3, int i4) {
            Intrinsics.checkParameterIsNotNull(queue, "queue");
            Intrinsics.checkParameterIsNotNull(queueBlockers, "queueBlockers");
            Intrinsics.checkParameterIsNotNull(requestBlockers, "requestBlockers");
            this.queue = queue;
            this.queueBlockers = queueBlockers;
            this.requestBlockers = requestBlockers;
            this.queuedRequestCount = i;
            this.completedRequestCount = i2;
            this.blockedRequestCount = i3;
            this.runningRequestCount = i4;
        }

        public final int getBlockedRequestCount() {
            return this.blockedRequestCount;
        }

        public final int getCompletedRequestCount() {
            return this.completedRequestCount;
        }

        @NotNull
        public final Queue getQueue() {
            return this.queue;
        }

        @NotNull
        public final Collection<Blocker> getQueueBlockers() {
            return this.queueBlockers;
        }

        public final int getQueuedRequestCount() {
            return this.queuedRequestCount;
        }

        @NotNull
        public final Collection<Blocker> getRequestBlockers() {
            return this.requestBlockers;
        }

        public final int getRunningRequestCount() {
            return this.runningRequestCount;
        }

        public final void setBlockedRequestCount(int i) {
            this.blockedRequestCount = i;
        }

        public final void setCompletedRequestCount(int i) {
            this.completedRequestCount = i;
        }

        public final void setQueueBlockers(@NotNull Collection<? extends Blocker> collection) {
            Intrinsics.checkParameterIsNotNull(collection, "<set-?>");
            this.queueBlockers = collection;
        }

        public final void setQueuedRequestCount(int i) {
            this.queuedRequestCount = i;
        }

        public final void setRequestBlockers(@NotNull Collection<? extends Blocker> collection) {
            Intrinsics.checkParameterIsNotNull(collection, "<set-?>");
            this.requestBlockers = collection;
        }

        public final void setRunningRequestCount(int i) {
            this.runningRequestCount = i;
        }

        public /* synthetic */ MutableQueueSummary(Queue queue, Collection collection, Collection collection2, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this(queue, (i5 & 2) != 0 ? SetsKt__SetsKt.emptySet() : collection, (i5 & 4) != 0 ? SetsKt__SetsKt.emptySet() : collection2, (i5 & 8) != 0 ? 0 : i, (i5 & 16) != 0 ? 0 : i2, (i5 & 32) != 0 ? 0 : i3, (i5 & 64) == 0 ? i4 : 0);
        }
    }

    public UploadSummaryTracker(@NotNull UploadRequestDao requestDao, @NotNull UploadSummaryNotifier summaryNotifier, @NotNull QueueManager queueManager) {
        Set emptySet;
        Intrinsics.checkParameterIsNotNull(requestDao, "requestDao");
        Intrinsics.checkParameterIsNotNull(summaryNotifier, "summaryNotifier");
        Intrinsics.checkParameterIsNotNull(queueManager, "queueManager");
        this.requestDao = requestDao;
        this.summaryNotifier = summaryNotifier;
        this.queueManager = queueManager;
        emptySet = SetsKt__SetsKt.emptySet();
        this.currentGlobalBlockers = emptySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Queue queue : this.queueManager.getQueues()) {
            linkedHashMap.put(queue.getName(), new MutableQueueSummary(queue, null, null, 0, 0, 0, 0, 126, null));
        }
        this.queueSummaryMap = linkedHashMap;
    }

    private final void broadcastUpdate() {
        UploadSummary uploadSummary = new UploadSummary(this.currentGlobalBlockers, generateSummaryCopy());
        boolean isUploaderRunning = isUploaderRunning();
        if (isUploaderRunning && !this.isUploaderRunning) {
            this.summaryNotifier.onUploaderStarted$AndroidPhotosUploader_release(uploadSummary);
        }
        this.summaryNotifier.onChanged$AndroidPhotosUploader_release(uploadSummary);
        if (!isUploaderRunning && this.isUploaderRunning) {
            this.summaryNotifier.onUploaderStopped$AndroidPhotosUploader_release(uploadSummary);
        }
        this.isUploaderRunning = isUploaderRunning;
    }

    private final MutableQueueSummary getQueueSummary(String str) {
        MutableQueueSummary mutableQueueSummary = this.queueSummaryMap.get(str);
        if (mutableQueueSummary == null) {
            Queue queue = this.queueManager.getQueue(str);
            if (queue != null) {
                mutableQueueSummary = new MutableQueueSummary(queue, null, null, 0, 0, 0, 0, 126, null);
                this.queueSummaryMap.put(str, mutableQueueSummary);
            } else {
                mutableQueueSummary = null;
            }
        }
        if (mutableQueueSummary != null) {
            return mutableQueueSummary;
        }
        throw new NoSuchElementException("Queue Summary does not exist.");
    }

    private final boolean isUploaderRunning() {
        if (!this.currentGlobalBlockers.isEmpty()) {
            return false;
        }
        for (Queue queue : this.queueManager.getQueues()) {
            MutableQueueSummary mutableQueueSummary = (MutableQueueSummary) MapsKt.getValue(this.queueSummaryMap, queue.getName());
            if (mutableQueueSummary.getQueueBlockers().isEmpty() && (mutableQueueSummary.getQueuedRequestCount() > 0 || mutableQueueSummary.getRunningRequestCount() > 0)) {
                return true;
            }
        }
        return false;
    }

    private final void updateQueueCounts(String str) {
        MutableQueueSummary queueSummary = getQueueSummary(str);
        queueSummary.setRunningRequestCount(this.requestDao.getCountForState(str, UploadState.RUNNING));
        queueSummary.setQueuedRequestCount(this.requestDao.getCountForState(str, UploadState.ENQUEUED));
        queueSummary.setBlockedRequestCount(this.requestDao.getCountForState(str, UploadState.BLOCKED));
        queueSummary.setCompletedRequestCount(this.requestDao.getCountForState(str, UploadState.SUCCEEDED));
    }

    @NotNull
    public final Set<QueueSummary> generateSummaryCopy() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Queue queue : this.queueManager.getQueues()) {
            MutableQueueSummary queueSummary = getQueueSummary(queue.getName());
            if (this.currentGlobalBlockers.isEmpty() && queueSummary.getQueueBlockers().isEmpty()) {
                linkedHashSet.add(new QueueSummary(queueSummary.getQueue(), queueSummary.getQueueBlockers(), queueSummary.getRequestBlockers(), queueSummary.getQueuedRequestCount(), queueSummary.getCompletedRequestCount(), queueSummary.getBlockedRequestCount(), queueSummary.getRunningRequestCount()));
            } else {
                linkedHashSet.add(new QueueSummary(queueSummary.getQueue(), queueSummary.getQueueBlockers(), queueSummary.getRequestBlockers(), 0, queueSummary.getCompletedRequestCount(), queueSummary.getBlockedRequestCount() + queueSummary.getRunningRequestCount() + queueSummary.getQueuedRequestCount(), 0));
            }
        }
        return linkedHashSet;
    }

    @NotNull
    public final UploadSummary getUploadSummary() {
        return new UploadSummary(this.currentGlobalBlockers, generateSummaryCopy());
    }

    public final void onUploadAbandoned(@NotNull List<AbandonedRequestInfo> uploadRequests) {
        Intrinsics.checkParameterIsNotNull(uploadRequests, "uploadRequests");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        for (AbandonedRequestInfo abandonedRequestInfo : uploadRequests) {
            linkedHashSet.add(abandonedRequestInfo.getUploadRequest().getQueue());
        }
        for (String str : linkedHashSet) {
            updateQueueCounts(str);
        }
        broadcastUpdate();
    }

    public final void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        updateQueueCounts(uploadRequest.getQueue());
        broadcastUpdate();
    }

    public final void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        updateQueueCounts(uploadRequest.getQueue());
        broadcastUpdate();
    }

    public final void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
        updateQueueCounts(uploadRequest.getQueue());
        broadcastUpdate();
    }

    public final void setBlockerSummary(@NotNull Collection<? extends Blocker> globalBlockers, @NotNull Map<Queue, ? extends Collection<? extends Blocker>> queueBlockerMap, @NotNull Map<Queue, ? extends Collection<? extends Blocker>> requestBlockerMap) {
        Intrinsics.checkParameterIsNotNull(globalBlockers, "globalBlockers");
        Intrinsics.checkParameterIsNotNull(queueBlockerMap, "queueBlockerMap");
        Intrinsics.checkParameterIsNotNull(requestBlockerMap, "requestBlockerMap");
        this.currentGlobalBlockers = globalBlockers;
        for (Queue queue : this.queueManager.getQueues()) {
            updateQueueCounts(queue.getName());
            MutableQueueSummary queueSummary = getQueueSummary(queue.getName());
            Collection<? extends Blocker> collection = queueBlockerMap.get(queue);
            if (collection == null) {
                collection = SetsKt__SetsKt.emptySet();
            }
            queueSummary.setQueueBlockers(collection);
            MutableQueueSummary queueSummary2 = getQueueSummary(queue.getName());
            Collection<? extends Blocker> collection2 = requestBlockerMap.get(queue);
            if (collection2 == null) {
                collection2 = SetsKt__SetsKt.emptySet();
            }
            queueSummary2.setRequestBlockers(collection2);
        }
        broadcastUpdate();
    }
}
