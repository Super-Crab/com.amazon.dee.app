package com.amazon.photos.uploader.internal;

import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import com.amazon.photos.uploader.observables.UploadSummaryObserver;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadSummaryNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0006H\u0016J\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\u0015\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u0015\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0017J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;", "Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "()V", "summaryObservers", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/amazon/photos/uploader/observables/UploadSummaryObserver;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "addObserver", "", "observer", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "worker", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosUploader_release", "onChanged", "uploadSummary", "Lcom/amazon/photos/uploader/observables/UploadSummary;", "onChanged$AndroidPhotosUploader_release", "onUploaderStarted", "onUploaderStarted$AndroidPhotosUploader_release", "onUploaderStopped", "onUploaderStopped$AndroidPhotosUploader_release", "removeObserver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadSummaryNotifier implements UploadSummaryObservable {
    private final ConcurrentHashMap<UploadSummaryObserver, Scheduler.Worker> summaryObservers = new ConcurrentHashMap<>();

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObservable
    public boolean addObserver(@NotNull UploadSummaryObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Scheduler.Worker createWorker = scheduler.createWorker();
        Intrinsics.checkExpressionValueIsNotNull(createWorker, "scheduler.createWorker()");
        return addObserver(observer, createWorker);
    }

    public final void clear$AndroidPhotosUploader_release() {
        this.summaryObservers.clear();
    }

    public final void onChanged$AndroidPhotosUploader_release(@NotNull final UploadSummary uploadSummary) {
        Intrinsics.checkParameterIsNotNull(uploadSummary, "uploadSummary");
        for (final Map.Entry<UploadSummaryObserver, Scheduler.Worker> entry : this.summaryObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadSummaryNotifier$onChanged$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadSummaryObserver) entry.getKey()).onChanged(uploadSummary);
                }
            });
        }
    }

    public final void onUploaderStarted$AndroidPhotosUploader_release(@NotNull final UploadSummary uploadSummary) {
        Intrinsics.checkParameterIsNotNull(uploadSummary, "uploadSummary");
        for (final Map.Entry<UploadSummaryObserver, Scheduler.Worker> entry : this.summaryObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadSummaryNotifier$onUploaderStarted$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadSummaryObserver) entry.getKey()).onUploaderStarted(uploadSummary);
                }
            });
        }
    }

    public final void onUploaderStopped$AndroidPhotosUploader_release(@NotNull final UploadSummary uploadSummary) {
        Intrinsics.checkParameterIsNotNull(uploadSummary, "uploadSummary");
        for (final Map.Entry<UploadSummaryObserver, Scheduler.Worker> entry : this.summaryObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.internal.UploadSummaryNotifier$onUploaderStopped$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((UploadSummaryObserver) entry.getKey()).onUploaderStopped(uploadSummary);
                }
            });
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObservable
    public boolean removeObserver(@NotNull UploadSummaryObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Scheduler.Worker remove = this.summaryObservers.remove(observer);
        if (remove != null) {
            remove.dispose();
        }
        return remove != null;
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObservable
    public boolean addObserver(@NotNull UploadSummaryObserver observer, @NotNull Scheduler.Worker worker) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(worker, "worker");
        return this.summaryObservers.putIfAbsent(observer, worker) == null;
    }
}
