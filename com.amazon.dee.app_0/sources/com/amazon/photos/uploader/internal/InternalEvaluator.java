package com.amazon.photos.uploader.internal;

import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.QueueManager;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.BlockerEvaluatorProvider;
import com.amazon.photos.uploader.blockers.CacheableBlocker;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.RequestBlockerEvaluator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: InternalEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\u0010\rJ\u0006\u0010\u0016\u001a\u00020\u0017J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001c\u001a\u00020\u001dJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u0017J\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0%J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001a0%2\u0006\u0010!\u001a\u00020\"R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "", "queueManager", "Lcom/amazon/photos/uploader/QueueManager;", "blockerEvaluatorProvider", "Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;", "globalBlockerEvaluatorSet", "", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "queueBlockerEvaluatorSet", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "requestBlockerEvaluatorSet", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "(Lcom/amazon/photos/uploader/QueueManager;Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;)V", "cacheableBlockers", "", "Lcom/amazon/photos/uploader/blockers/CacheableBlocker;", "destroyableBlockers", "Ljavax/security/auth/Destroyable;", "globalBlockerEvaluators", "queueBlockerEvaluators", "requestBlockerEvaluators", "destroy", "", "getGlobalBlockers", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "getQueueBlockers", "queue", "Lcom/amazon/photos/uploader/Queue;", "queueName", "", "getRequestBlockers", "request", "Lcom/amazon/photos/uploader/UploadRequest;", "invalidateCacheBlocking", "queryGlobalBlockers", "", "queryQueueBlockers", "queryRequestBlockers", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class InternalEvaluator {
    private final List<CacheableBlocker> cacheableBlockers;
    private final List<Destroyable> destroyableBlockers;
    private final List<GlobalBlockerEvaluator> globalBlockerEvaluators;
    private final List<QueueBlockerEvaluator> queueBlockerEvaluators;
    private final QueueManager queueManager;
    private final List<RequestBlockerEvaluator> requestBlockerEvaluators;

    public InternalEvaluator(@NotNull QueueManager queueManager, @NotNull BlockerEvaluatorProvider blockerEvaluatorProvider, @NotNull Collection<? extends GlobalBlockerEvaluator> globalBlockerEvaluatorSet, @NotNull Collection<? extends QueueBlockerEvaluator> queueBlockerEvaluatorSet, @NotNull Collection<? extends RequestBlockerEvaluator> requestBlockerEvaluatorSet) {
        Set union;
        Set union2;
        Set union3;
        Set union4;
        Set union5;
        Intrinsics.checkParameterIsNotNull(queueManager, "queueManager");
        Intrinsics.checkParameterIsNotNull(blockerEvaluatorProvider, "blockerEvaluatorProvider");
        Intrinsics.checkParameterIsNotNull(globalBlockerEvaluatorSet, "globalBlockerEvaluatorSet");
        Intrinsics.checkParameterIsNotNull(queueBlockerEvaluatorSet, "queueBlockerEvaluatorSet");
        Intrinsics.checkParameterIsNotNull(requestBlockerEvaluatorSet, "requestBlockerEvaluatorSet");
        this.queueManager = queueManager;
        this.globalBlockerEvaluators = new ArrayList();
        this.queueBlockerEvaluators = new ArrayList();
        this.requestBlockerEvaluators = new ArrayList();
        this.cacheableBlockers = new ArrayList();
        this.destroyableBlockers = new ArrayList();
        List<GlobalBlockerEvaluator> list = this.globalBlockerEvaluators;
        union = CollectionsKt___CollectionsKt.union(blockerEvaluatorProvider.getGlobalEvaluators(), globalBlockerEvaluatorSet);
        list.addAll(union);
        List<QueueBlockerEvaluator> list2 = this.queueBlockerEvaluators;
        union2 = CollectionsKt___CollectionsKt.union(blockerEvaluatorProvider.getQueueEvaluators(), queueBlockerEvaluatorSet);
        list2.addAll(union2);
        List<RequestBlockerEvaluator> list3 = this.requestBlockerEvaluators;
        union3 = CollectionsKt___CollectionsKt.union(blockerEvaluatorProvider.getRequestEvaluators(), requestBlockerEvaluatorSet);
        list3.addAll(union3);
        List<GlobalBlockerEvaluator> list4 = this.globalBlockerEvaluators;
        union4 = CollectionsKt___CollectionsKt.union(this.queueBlockerEvaluators, this.requestBlockerEvaluators);
        union5 = CollectionsKt___CollectionsKt.union(list4, union4);
        ArrayList<CacheableBlocker> arrayList = new ArrayList();
        for (Object obj : union5) {
            if (obj instanceof CacheableBlocker) {
                arrayList.add(obj);
            }
        }
        for (CacheableBlocker cacheableBlocker : arrayList) {
            this.cacheableBlockers.add(cacheableBlocker);
        }
        ArrayList<Destroyable> arrayList2 = new ArrayList();
        for (Object obj2 : union5) {
            if (obj2 instanceof Destroyable) {
                arrayList2.add(obj2);
            }
        }
        for (Destroyable destroyable : arrayList2) {
            this.destroyableBlockers.add(destroyable);
        }
    }

    public final void destroy() {
        for (Destroyable destroyable : this.destroyableBlockers) {
            destroyable.destroy();
        }
    }

    @NotNull
    public final List<Blocker> getGlobalBlockers() {
        List<Blocker> list;
        List<GlobalBlockerEvaluator> list2 = this.globalBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (GlobalBlockerEvaluator globalBlockerEvaluator : list2) {
            Blocker blocker = globalBlockerEvaluator.getBlocker();
            if (blocker != null) {
                arrayList.add(blocker);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }

    @NotNull
    public final List<Blocker> getQueueBlockers(@NotNull Queue queue) {
        List<Blocker> list;
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        List<QueueBlockerEvaluator> list2 = this.queueBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (QueueBlockerEvaluator queueBlockerEvaluator : list2) {
            Blocker blocker = queueBlockerEvaluator.getBlocker(queue);
            if (blocker != null) {
                arrayList.add(blocker);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }

    @NotNull
    public final List<Blocker> getRequestBlockers(@NotNull UploadRequest request) {
        List<Blocker> list;
        Intrinsics.checkParameterIsNotNull(request, "request");
        List<RequestBlockerEvaluator> list2 = this.requestBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (RequestBlockerEvaluator requestBlockerEvaluator : list2) {
            Blocker blocker = requestBlockerEvaluator.getBlocker(request);
            if (blocker != null) {
                arrayList.add(blocker);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }

    public final void invalidateCacheBlocking() {
        for (CacheableBlocker cacheableBlocker : this.cacheableBlockers) {
            cacheableBlocker.invalidateCacheBlocking();
        }
    }

    @NotNull
    public final Set<Blocker> queryGlobalBlockers() {
        HashSet hashSet;
        List<GlobalBlockerEvaluator> list = this.globalBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (GlobalBlockerEvaluator globalBlockerEvaluator : list) {
            Blocker queryBlocker = globalBlockerEvaluator.queryBlocker();
            if (queryBlocker != null) {
                arrayList.add(queryBlocker);
            }
        }
        hashSet = CollectionsKt___CollectionsKt.toHashSet(arrayList);
        return hashSet;
    }

    @NotNull
    public final Set<Blocker> queryQueueBlockers(@NotNull Queue queue) {
        HashSet hashSet;
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        List<QueueBlockerEvaluator> list = this.queueBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (QueueBlockerEvaluator queueBlockerEvaluator : list) {
            Blocker queryBlocker = queueBlockerEvaluator.queryBlocker(queue);
            if (queryBlocker != null) {
                arrayList.add(queryBlocker);
            }
        }
        hashSet = CollectionsKt___CollectionsKt.toHashSet(arrayList);
        return hashSet;
    }

    @NotNull
    public final Set<Blocker> queryRequestBlockers(@NotNull UploadRequest request) {
        HashSet hashSet;
        Intrinsics.checkParameterIsNotNull(request, "request");
        List<RequestBlockerEvaluator> list = this.requestBlockerEvaluators;
        ArrayList arrayList = new ArrayList();
        for (RequestBlockerEvaluator requestBlockerEvaluator : list) {
            Blocker queryBlocker = requestBlockerEvaluator.queryBlocker(request);
            if (queryBlocker != null) {
                arrayList.add(queryBlocker);
            }
        }
        hashSet = CollectionsKt___CollectionsKt.toHashSet(arrayList);
        return hashSet;
    }

    @NotNull
    public final List<Blocker> getQueueBlockers(@NotNull String queueName) {
        List<Blocker> emptyList;
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        Queue queue = this.queueManager.getQueue(queueName);
        if (queue != null) {
            return getQueueBlockers(queue);
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }
}
