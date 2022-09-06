package com.amazon.photos.uploader.dao;

import androidx.annotation.AnyThread;
import androidx.lifecycle.LiveData;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.UploadSummaryTracker;
import com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import com.amazon.photos.uploader.observables.QueueSummary;
import com.amazon.photos.uploader.observables.UploadSummary;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BlockerDao.kt */
@AnyThread
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000f2\u0006\u0010\f\u001a\u00020\rJ&\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J,\u0010\u0014\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00110\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/photos/uploader/dao/BlockerDao;", "", "globalBlockerProvider", "Lcom/amazon/photos/uploader/internal/livedata/GlobalBlockerLiveDataProvider;", "queueBlockerProvider", "Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;", "uploadSummaryTracker", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "(Lcom/amazon/photos/uploader/internal/livedata/GlobalBlockerLiveDataProvider;Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;)V", "getBlockersForQueue", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queueName", "", "getBlockersForQueueLiveData", "Landroidx/lifecycle/LiveData;", "getBlockersForQueues", "", "queueNames", "", "getBlockersForQueuesLiveData", "getGlobalBlockers", "getGlobalBlockersLiveData", "getQueueSummary", "Lcom/amazon/photos/uploader/observables/QueueSummary;", "getUploadSummary", "Lcom/amazon/photos/uploader/observables/UploadSummary;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BlockerDao {
    private final GlobalBlockerLiveDataProvider globalBlockerProvider;
    private final QueueBlockerLiveDataProvider queueBlockerProvider;
    private final UploadSummaryTracker uploadSummaryTracker;

    public BlockerDao(@NotNull GlobalBlockerLiveDataProvider globalBlockerProvider, @NotNull QueueBlockerLiveDataProvider queueBlockerProvider, @NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(globalBlockerProvider, "globalBlockerProvider");
        Intrinsics.checkParameterIsNotNull(queueBlockerProvider, "queueBlockerProvider");
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "uploadSummaryTracker");
        this.globalBlockerProvider = globalBlockerProvider;
        this.queueBlockerProvider = queueBlockerProvider;
        this.uploadSummaryTracker = uploadSummaryTracker;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
        r4 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r4);
     */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.amazon.photos.uploader.blockers.Blocker> getBlockersForQueue(@org.jetbrains.annotations.NotNull java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "queueName"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            com.amazon.photos.uploader.internal.UploadSummaryTracker r0 = r3.uploadSummaryTracker
            com.amazon.photos.uploader.observables.UploadSummary r0 = r0.getUploadSummary()
            java.util.Collection r0 = r0.getQueueSummaries()
            java.util.Iterator r0 = r0.iterator()
        L13:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2f
            java.lang.Object r1 = r0.next()
            r2 = r1
            com.amazon.photos.uploader.observables.QueueSummary r2 = (com.amazon.photos.uploader.observables.QueueSummary) r2
            com.amazon.photos.uploader.Queue r2 = r2.getQueue()
            java.lang.String r2 = r2.getName()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
            if (r2 == 0) goto L13
            goto L30
        L2f:
            r1 = 0
        L30:
            com.amazon.photos.uploader.observables.QueueSummary r1 = (com.amazon.photos.uploader.observables.QueueSummary) r1
            if (r1 == 0) goto L41
            java.util.Collection r4 = r1.getQueueBlockers()
            if (r4 == 0) goto L41
            java.util.List r4 = kotlin.collections.CollectionsKt.toList(r4)
            if (r4 == 0) goto L41
            goto L45
        L41:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
        L45:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.dao.BlockerDao.getBlockersForQueue(java.lang.String):java.util.List");
    }

    @NotNull
    public final LiveData<List<Blocker>> getBlockersForQueueLiveData(@NotNull String queueName) {
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        return this.queueBlockerProvider.getLiveData(queueName);
    }

    @NotNull
    public final Map<String, List<Blocker>> getBlockersForQueues(@NotNull Set<String> queueNames) {
        List list;
        Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (QueueSummary queueSummary : this.uploadSummaryTracker.getUploadSummary().getQueueSummaries()) {
            if (queueNames.contains(queueSummary.getQueue().getName())) {
                String name = queueSummary.getQueue().getName();
                list = CollectionsKt___CollectionsKt.toList(queueSummary.getQueueBlockers());
                linkedHashMap.put(name, list);
            }
        }
        return linkedHashMap;
    }

    @NotNull
    public final LiveData<Map<String, List<Blocker>>> getBlockersForQueuesLiveData(@NotNull Set<String> queueNames) {
        Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
        return this.queueBlockerProvider.getLiveData(queueNames);
    }

    @NotNull
    public final List<Blocker> getGlobalBlockers() {
        List<Blocker> list;
        list = CollectionsKt___CollectionsKt.toList(this.uploadSummaryTracker.getUploadSummary().getGlobalBlockers());
        return list;
    }

    @NotNull
    public final LiveData<List<Blocker>> getGlobalBlockersLiveData() {
        return this.globalBlockerProvider.getLiveData();
    }

    @Nullable
    public final QueueSummary getQueueSummary(@NotNull String queueName) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Iterator<T> it2 = this.uploadSummaryTracker.getUploadSummary().getQueueSummaries().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (Intrinsics.areEqual(((QueueSummary) obj).getQueue().getName(), queueName)) {
                break;
            }
        }
        return (QueueSummary) obj;
    }

    @NotNull
    public final UploadSummary getUploadSummary() {
        return this.uploadSummaryTracker.getUploadSummary();
    }
}
