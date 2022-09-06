package com.amazon.photos.autosave.internal.observers;

import android.content.SharedPreferences;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.AutosaveEventObserver;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.metrics.AutosaveLatencyRecorder;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.DiscoveryOperations;
import com.amazon.photos.uploader.UploadManager;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveObserverCoordinator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u0018J\u001d\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0002\b$J\u0016\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\u0006\u0010%\u001a\u00020&J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u000e\u0010*\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/photos/autosave/internal/observers/AutosaveObserverCoordinator;", "Ljavax/security/auth/Destroyable;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "autosaveEventNotifier", "Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;", "operations", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "autosaveItemDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "transactionRunner", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "autosaveBucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "autosavePreferences", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "sharedPreferences", "Landroid/content/SharedPreferences;", "autosaveLatencyRecorder", "Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;", "(Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/uploader/UploadManager;Lcom/amazon/photos/autosave/internal/observers/AutosaveEventNotifier;Lcom/amazon/photos/autosave/internal/AutosaveOperations;Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;Landroid/content/SharedPreferences;Lcom/amazon/photos/autosave/internal/metrics/AutosaveLatencyRecorder;)V", "autosaveContentChangeObserver", "Lcom/amazon/photos/autosave/internal/observers/AutosaveContentChangeObserver;", "destroyed", "", "uploadRequestObserver", "Lcom/amazon/photos/autosave/internal/observers/AutosaveUploadRequestObserver;", "addAutosaveEventObserver", "observer", "Lcom/amazon/photos/autosave/AutosaveEventObserver;", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "addAutosaveEventObserver$AndroidPhotosAutosave_release", "worker", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "destroy", "", "isDestroyed", "removeAutosaveEventObserver", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveObserverCoordinator implements Destroyable {
    private final AutosaveContentChangeObserver autosaveContentChangeObserver;
    private final AutosaveEventNotifier autosaveEventNotifier;
    private boolean destroyed;
    private final Discovery discovery;
    private final UploadManager uploadManager;
    private final AutosaveUploadRequestObserver uploadRequestObserver;

    public AutosaveObserverCoordinator(@NotNull Discovery discovery, @NotNull UploadManager uploadManager, @NotNull AutosaveEventNotifier autosaveEventNotifier, @NotNull AutosaveOperations operations, @NotNull AutosaveItemDao autosaveItemDao, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull Metrics metrics, @NotNull AutosaveBucketDao autosaveBucketDao, @NotNull InternalPreferences autosavePreferences, @NotNull SharedPreferences sharedPreferences, @NotNull AutosaveLatencyRecorder autosaveLatencyRecorder) {
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        Intrinsics.checkParameterIsNotNull(autosaveEventNotifier, "autosaveEventNotifier");
        Intrinsics.checkParameterIsNotNull(operations, "operations");
        Intrinsics.checkParameterIsNotNull(autosaveItemDao, "autosaveItemDao");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(autosaveBucketDao, "autosaveBucketDao");
        Intrinsics.checkParameterIsNotNull(autosavePreferences, "autosavePreferences");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(autosaveLatencyRecorder, "autosaveLatencyRecorder");
        this.discovery = discovery;
        this.uploadManager = uploadManager;
        this.autosaveEventNotifier = autosaveEventNotifier;
        this.autosaveContentChangeObserver = new AutosaveContentChangeObserver(operations, autosavePreferences, this.discovery, autosaveBucketDao, transactionRunner, sharedPreferences, autosaveLatencyRecorder);
        this.uploadRequestObserver = new AutosaveUploadRequestObserver(autosaveItemDao, metrics);
        DiscoveryOperations operations2 = this.discovery.getOperations();
        AutosaveContentChangeObserver autosaveContentChangeObserver = this.autosaveContentChangeObserver;
        Scheduler computation = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation, "Schedulers.computation()");
        operations2.addContentChangeObserver(autosaveContentChangeObserver, computation);
        UploadRequestObservable uploadRequestObservable = this.uploadManager.getUploadRequestObservable();
        AutosaveUploadRequestObserver autosaveUploadRequestObserver = this.uploadRequestObserver;
        Scheduler computation2 = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation2, "Schedulers.computation()");
        uploadRequestObservable.addObserver(autosaveUploadRequestObserver, computation2);
    }

    public final boolean addAutosaveEventObserver(@NotNull AutosaveEventObserver observer, @NotNull Scheduler.Worker worker) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(worker, "worker");
        return this.autosaveEventNotifier.addObserver(observer, worker);
    }

    public final boolean addAutosaveEventObserver$AndroidPhotosAutosave_release(@NotNull AutosaveEventObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        return this.autosaveEventNotifier.addObserver(observer, scheduler);
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.discovery.getOperations().removeContentChangeObserver(this.autosaveContentChangeObserver);
        this.uploadManager.getUploadRequestObservable().removeObserver(this.uploadRequestObserver);
        this.autosaveEventNotifier.clear$AndroidPhotosAutosave_release();
        this.destroyed = true;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    public final boolean removeAutosaveEventObserver(@NotNull AutosaveEventObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        return this.autosaveEventNotifier.removeObserver(observer);
    }
}
