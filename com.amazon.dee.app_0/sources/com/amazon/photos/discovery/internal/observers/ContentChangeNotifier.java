package com.amazon.photos.discovery.internal.observers;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.photos.discovery.observers.LocalContentChangeObserver;
import com.amazon.photos.discovery.observers.LocalContentType;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContentChangeNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bJ\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ#\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0018J\u001b\u0010\u0019\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0000¢\u0006\u0002\b\u001aJ#\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0000¢\u0006\u0002\b\u001cJ\u000e\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/photos/discovery/internal/observers/ContentChangeNotifier;", "", "()V", "contentListeners", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/amazon/photos/discovery/observers/LocalContentChangeObserver;", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "addObserver", "", "observer", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosDiscovery_release", "onContentAdded", "contentType", "Lcom/amazon/photos/discovery/observers/LocalContentType;", ContactsModuleConstants.CONTACT_IDS, "", "", "onContentAdded$AndroidPhotosDiscovery_release", "onContentDedupeComplete", "wasContentDeduped", "onContentDedupeComplete$AndroidPhotosDiscovery_release", "onContentDeduped", "onContentDeduped$AndroidPhotosDiscovery_release", "onContentDeleted", "onContentDeleted$AndroidPhotosDiscovery_release", "removeObserver", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ContentChangeNotifier {
    private final ConcurrentHashMap<LocalContentChangeObserver, Scheduler.Worker> contentListeners = new ConcurrentHashMap<>();

    public final boolean addObserver(@NotNull LocalContentChangeObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        return this.contentListeners.putIfAbsent(observer, scheduler.createWorker()) == null;
    }

    public final void clear$AndroidPhotosDiscovery_release() {
        this.contentListeners.clear();
    }

    public final void onContentAdded$AndroidPhotosDiscovery_release(@NotNull final LocalContentType contentType, @NotNull final List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        for (final Map.Entry<LocalContentChangeObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.discovery.internal.observers.ContentChangeNotifier$onContentAdded$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((LocalContentChangeObserver) entry.getKey()).onContentAdded(contentType, ids);
                }
            });
        }
    }

    public final void onContentDedupeComplete$AndroidPhotosDiscovery_release(final boolean z) {
        for (final Map.Entry<LocalContentChangeObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.discovery.internal.observers.ContentChangeNotifier$onContentDedupeComplete$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((LocalContentChangeObserver) entry.getKey()).onContentDedupeComplete(z);
                }
            });
        }
    }

    public final void onContentDeduped$AndroidPhotosDiscovery_release(@NotNull final List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        for (final Map.Entry<LocalContentChangeObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.discovery.internal.observers.ContentChangeNotifier$onContentDeduped$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((LocalContentChangeObserver) entry.getKey()).onContentDeduped(ids);
                }
            });
        }
    }

    public final void onContentDeleted$AndroidPhotosDiscovery_release(@NotNull final LocalContentType contentType, @NotNull final List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        for (final Map.Entry<LocalContentChangeObserver, Scheduler.Worker> entry : this.contentListeners.entrySet()) {
            entry.getValue().schedule(new Runnable() { // from class: com.amazon.photos.discovery.internal.observers.ContentChangeNotifier$onContentDeleted$$inlined$forEach$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((LocalContentChangeObserver) entry.getKey()).onContentDeleted(contentType, ids);
                }
            });
        }
    }

    public final boolean removeObserver(@NotNull LocalContentChangeObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Scheduler.Worker remove = this.contentListeners.remove(observer);
        if (remove != null) {
            remove.dispose();
        }
        return remove != null;
    }
}
