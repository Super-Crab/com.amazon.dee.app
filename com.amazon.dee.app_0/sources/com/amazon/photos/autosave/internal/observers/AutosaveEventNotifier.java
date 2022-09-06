package com.amazon.photos.autosave.internal.observers;

import com.amazon.photos.autosave.AutosaveEventObserver;
import com.amazon.photos.autosave.model.MediaType;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveEventNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0006J\r\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\u001d\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0016J\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "", "()V", "contentListeners", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/amazon/photos/autosave/AutosaveEventObserver;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "addObserver", "", "observer", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "worker", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosAutosave_release", "onAutosaveStateChanged", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "state", "onAutosaveStateChanged$AndroidPhotosAutosave_release", "onNoUploadsScanComplete", "onNoUploadsScanComplete$AndroidPhotosAutosave_release", "removeObserver", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveEventNotifier {
    private final ConcurrentHashMap<AutosaveEventObserver, Scheduler.Worker> contentListeners = new ConcurrentHashMap<>();

    public final boolean addObserver(@NotNull AutosaveEventObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        Scheduler.Worker createWorker = scheduler.createWorker();
        Intrinsics.checkExpressionValueIsNotNull(createWorker, "scheduler.createWorker()");
        return addObserver(observer, createWorker);
    }

    public final void clear$AndroidPhotosAutosave_release() {
        this.contentListeners.clear();
    }

    public final void onAutosaveStateChanged$AndroidPhotosAutosave_release(@NotNull final MediaType mediaType, final boolean z) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        for (final Map.Entry<AutosaveEventObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier$onAutosaveStateChanged$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((AutosaveEventObserver) entry.getKey()).onAutosaveStateChanged(mediaType, z);
                }
            });
        }
    }

    public final void onNoUploadsScanComplete$AndroidPhotosAutosave_release() {
        for (final Map.Entry<AutosaveEventObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.autosave.internal.observers.AutosaveEventNotifier$onNoUploadsScanComplete$1$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((AutosaveEventObserver) entry.getKey()).onNoUploadsScanComplete();
                }
            });
        }
    }

    public final boolean removeObserver(@NotNull AutosaveEventObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Scheduler.Worker remove = this.contentListeners.remove(observer);
        if (remove != null) {
            remove.dispose();
        }
        return remove != null;
    }

    public final boolean addObserver(@NotNull AutosaveEventObserver observer, @NotNull Scheduler.Worker worker) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(worker, "worker");
        return this.contentListeners.putIfAbsent(observer, worker) == null;
    }
}
