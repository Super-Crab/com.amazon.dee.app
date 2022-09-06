package com.amazon.photos.uploader.cds.observer;

import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsUploadNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016J\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ%\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/uploader/cds/observer/CdsUploadNotifier;", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadObservable;", "()V", "requestObservers", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadObserver;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "addObserver", "", "observer", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosUploader_release", "onUploadFailed", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadFailed$AndroidPhotosUploader_release", "removeObserver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsUploadNotifier implements CdsUploadObservable {
    private final ConcurrentHashMap<CdsUploadObserver, Scheduler.Worker> requestObservers = new ConcurrentHashMap<>();

    @Override // com.amazon.photos.uploader.cds.observer.CdsUploadObservable
    public boolean addObserver(@NotNull CdsUploadObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        return this.requestObservers.putIfAbsent(observer, scheduler.createWorker()) == null;
    }

    public final void clear$AndroidPhotosUploader_release() {
        this.requestObservers.clear();
    }

    public final void onUploadFailed$AndroidPhotosUploader_release(@NotNull final UploadRequest uploadRequest, @NotNull final Throwable ex, @NotNull final UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        for (final Map.Entry<CdsUploadObserver, Scheduler.Worker> entry : this.requestObservers.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.uploader.cds.observer.CdsUploadNotifier$onUploadFailed$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((CdsUploadObserver) entry.getKey()).onUploadFailed(uploadRequest, ex, errorCategory);
                }
            });
        }
    }

    @Override // com.amazon.photos.uploader.cds.observer.CdsUploadObservable
    public boolean removeObserver(@NotNull CdsUploadObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Scheduler.Worker remove = this.requestObservers.remove(observer);
        if (remove != null) {
            remove.dispose();
        }
        return remove != null;
    }
}
