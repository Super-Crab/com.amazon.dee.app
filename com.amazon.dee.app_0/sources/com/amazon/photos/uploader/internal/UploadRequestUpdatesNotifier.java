package com.amazon.photos.uploader.internal;

import amazon.speech.simclient.settings.Settings;
import com.amazon.photos.uploader.AbandonReason;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadRequestUpdatesNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\bH\u0016J\r\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J/\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001fJ\u001b\u0010 \u001a\u00020\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0000¢\u0006\u0002\b$J\u001d\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'H\u0000¢\u0006\u0002\b(J#\u0010%\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010)\u001a\b\u0012\u0004\u0012\u00020'0*H\u0000¢\u0006\u0002\b(J%\u0010+\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\b,J%\u0010-\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0000¢\u0006\u0002\b1J\u0015\u00102\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b3J\u001d\u00104\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u00105\u001a\u000206H\u0000¢\u0006\u0002\b7J\u0010\u00108\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u00069"}, d2 = {"Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "Lcom/amazon/photos/uploader/observables/UploadRequestObservable;", "uploadSummaryTracker", "Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "(Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;)V", "requestObservers", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "getUploadSummaryTracker", "()Lcom/amazon/photos/uploader/internal/UploadSummaryTracker;", "addObserver", "", "observer", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "worker", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosUploader_release", "onRequestAbandoned", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", Settings.ListeningSettings.EXTRA_REASON, "Lcom/amazon/photos/uploader/AbandonReason;", "onRequestAbandoned$AndroidPhotosUploader_release", "onRequestAdded", "onRequestAdded$AndroidPhotosUploader_release", "onRequestsAbandoned", "uploadRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onRequestsAbandoned$AndroidPhotosUploader_release", "onUploadBlocked", "blocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "onUploadBlocked$AndroidPhotosUploader_release", "blockers", "", "onUploadFailed", "onUploadFailed$AndroidPhotosUploader_release", "onUploadProgressUpdate", "currentProgress", "", "maxProgress", "onUploadProgressUpdate$AndroidPhotosUploader_release", "onUploadStarted", "onUploadStarted$AndroidPhotosUploader_release", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "onUploadSucceeded$AndroidPhotosUploader_release", "removeObserver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadRequestUpdatesNotifier implements UploadRequestObservable {
    private final ConcurrentHashMap<UploadRequestObserver, Scheduler.Worker> requestObservers;
    @NotNull
    private final UploadSummaryTracker uploadSummaryTracker;

    public UploadRequestUpdatesNotifier(@NotNull UploadSummaryTracker uploadSummaryTracker) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryTracker, "uploadSummaryTracker");
        this.uploadSummaryTracker = uploadSummaryTracker;
        this.requestObservers = new ConcurrentHashMap<>();
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObservable
    public boolean addObserver(@NotNull UploadRequestObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Scheduler.Worker createWorker = scheduler.createWorker();
        Intrinsics.checkExpressionValueIsNotNull(createWorker, "scheduler.createWorker()");
        return addObserver(observer, createWorker);
    }

    public final void clear$AndroidPhotosUploader_release() {
        this.requestObservers.clear();
    }

    @NotNull
    public final UploadSummaryTracker getUploadSummaryTracker() {
        return this.uploadSummaryTracker;
    }

    public final void onRequestAbandoned$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory, @NotNull AbandonReason reason) {
        List<AbandonedRequestInfo> listOf;
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        Intrinsics.checkParameterIsNotNull(reason, "reason");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new AbandonedRequestInfo(uploadRequest, th, errorCategory, reason));
        onRequestsAbandoned$AndroidPhotosUploader_release(listOf);
    }

    public final void onRequestAdded$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onRequestAdded$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onRequestAdded(uploadRequest);
                }
            });
        }
    }

    public final void onRequestsAbandoned$AndroidPhotosUploader_release(@NotNull final List<AbandonedRequestInfo> uploadRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(uploadRequestInfoList, "uploadRequestInfoList");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onRequestsAbandoned$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onRequestsAbandoned(uploadRequestInfoList);
                }
            });
        }
        this.uploadSummaryTracker.onUploadAbandoned(uploadRequestInfoList);
    }

    public final void onUploadBlocked$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadBlocked$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadBlocked(uploadRequest, blocker);
                }
            });
        }
    }

    public final void onUploadFailed$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final Throwable ex, @NotNull final UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadFailed$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadFailed(uploadRequest, ex, errorCategory);
                }
            });
        }
        this.uploadSummaryTracker.onUploadFailed(uploadRequest, ex, errorCategory);
    }

    public final void onUploadProgressUpdate$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, final long j, final long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadProgressUpdate$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadProgressUpdate(uploadRequest, j, j2);
                }
            });
        }
    }

    public final void onUploadStarted$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        this.uploadSummaryTracker.onUploadStarted(uploadRequest);
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadStarted$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadStarted(uploadRequest);
                }
            });
        }
    }

    public final void onUploadSucceeded$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadSucceeded$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadSucceeded(uploadRequest, resultMetadata);
                }
            });
        }
        this.uploadSummaryTracker.onUploadSucceeded(uploadRequest, resultMetadata);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObservable
    public boolean removeObserver(@NotNull UploadRequestObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Scheduler.Worker remove = this.requestObservers.remove(observer);
        if (remove != null) {
            remove.dispose();
        }
        return remove != null;
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObservable
    public boolean addObserver(@NotNull UploadRequestObserver observer, @NotNull Scheduler.Worker worker) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(worker, "worker");
        return this.requestObservers.putIfAbsent(observer, worker) == null;
    }

    public final void onUploadBlocked$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        for (final Map.Entry<UploadRequestObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier$onUploadBlocked$$inlined$forEach$lambda$2
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadRequestObserver) entry.getKey()).onUploadBlocked(uploadRequest, blockers);
                }
            });
        }
    }
}
