package com.amazon.photos.uploader.blockers;

import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BlockerEvaluatorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/blockers/BlockerEvaluatorProvider;", "", "globalEvaluators", "", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "queueEvaluators", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "requestEvaluators", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "requestNotifier", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "(Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lcom/amazon/photos/uploader/SchedulingCallback;Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;)V", "cacheableEvaluatorList", "", "Lcom/amazon/photos/uploader/blockers/CacheableBlocker;", "getCacheableEvaluatorList", "()Ljava/util/List;", "getGlobalEvaluators", "()Ljava/util/Set;", "getQueueEvaluators", "getRequestEvaluators", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BlockerEvaluatorProvider {
    @NotNull
    private final List<CacheableBlocker> cacheableEvaluatorList;
    @NotNull
    private final Set<GlobalBlockerEvaluator> globalEvaluators;
    @NotNull
    private final Set<QueueBlockerEvaluator> queueEvaluators;
    @NotNull
    private final Set<RequestBlockerEvaluator> requestEvaluators;
    private final UploadRequestUpdatesNotifier requestNotifier;
    private final SchedulingCallback schedulingCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public BlockerEvaluatorProvider(@NotNull Set<? extends GlobalBlockerEvaluator> globalEvaluators, @NotNull Set<? extends QueueBlockerEvaluator> queueEvaluators, @NotNull Set<? extends RequestBlockerEvaluator> requestEvaluators, @NotNull SchedulingCallback schedulingCallback, @NotNull UploadRequestUpdatesNotifier requestNotifier) {
        Set<Set> of;
        Intrinsics.checkParameterIsNotNull(globalEvaluators, "globalEvaluators");
        Intrinsics.checkParameterIsNotNull(queueEvaluators, "queueEvaluators");
        Intrinsics.checkParameterIsNotNull(requestEvaluators, "requestEvaluators");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        Intrinsics.checkParameterIsNotNull(requestNotifier, "requestNotifier");
        this.globalEvaluators = globalEvaluators;
        this.queueEvaluators = queueEvaluators;
        this.requestEvaluators = requestEvaluators;
        this.schedulingCallback = schedulingCallback;
        this.requestNotifier = requestNotifier;
        this.cacheableEvaluatorList = new ArrayList();
        of = SetsKt__SetsKt.setOf((Object[]) new Set[]{this.globalEvaluators, this.queueEvaluators, this.requestEvaluators});
        for (Set set : of) {
            for (Object obj : set) {
                if (obj instanceof RequiresSchedulingCallback) {
                    ((RequiresSchedulingCallback) obj).initializeSchedulerCallback(this.schedulingCallback);
                }
                if (obj instanceof UploadRequestObserver) {
                    UploadRequestObservable.DefaultImpls.addObserver$default(this.requestNotifier, (UploadRequestObserver) obj, null, 2, null);
                }
                if (obj instanceof CacheableBlocker) {
                    this.cacheableEvaluatorList.add(obj);
                }
            }
        }
    }

    @NotNull
    public final List<CacheableBlocker> getCacheableEvaluatorList() {
        return this.cacheableEvaluatorList;
    }

    @NotNull
    public final Set<GlobalBlockerEvaluator> getGlobalEvaluators() {
        return this.globalEvaluators;
    }

    @NotNull
    public final Set<QueueBlockerEvaluator> getQueueEvaluators() {
        return this.queueEvaluators;
    }

    @NotNull
    public final Set<RequestBlockerEvaluator> getRequestEvaluators() {
        return this.requestEvaluators;
    }
}
