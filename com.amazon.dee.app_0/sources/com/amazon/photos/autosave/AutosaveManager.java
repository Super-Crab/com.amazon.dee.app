package com.amazon.photos.autosave;

import androidx.annotation.AnyThread;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.dagger.AutosaveManagerMap;
import com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent;
import com.amazon.photos.autosave.internal.db.DestroyableDatabaseWrapper;
import com.amazon.photos.autosave.internal.observers.AutosaveObserverCoordinator;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import io.reactivex.rxjava3.core.Scheduler;
import javax.inject.Inject;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 F2\u00020\u0001:\u0001FB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0007J\u0018\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\u0006\u0010?\u001a\u00020@H\u0007J\b\u0010A\u001a\u00020BH\u0017J\b\u0010C\u001a\u00020 H\u0017J\u0010\u0010D\u001a\u00020 2\u0006\u0010;\u001a\u00020<H\u0007J\b\u0010E\u001a\u00020BH\u0002R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u001a@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020 @BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R$\u0010%\u001a\u00020$2\u0006\u0010\u0005\u001a\u00020$@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010+\u001a\u00020*2\u0006\u0010\u0005\u001a\u00020*@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0011\u00100\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b2\u00103R$\u00105\u001a\u0002042\u0006\u0010\u0005\u001a\u000204@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006G"}, d2 = {"Lcom/amazon/photos/autosave/AutosaveManager;", "Ljavax/security/auth/Destroyable;", JsonFields.COMPONENT, "Lcom/amazon/photos/autosave/internal/dagger/component/AutosaveComponent;", "(Lcom/amazon/photos/autosave/internal/dagger/component/AutosaveComponent;)V", "<set-?>", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "autosaveFrameworkContext", "getAutosaveFrameworkContext$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "setAutosaveFrameworkContext$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/autosave/AutosaveFrameworkContext;)V", "Lcom/amazon/photos/autosave/internal/observers/AutosaveObserverCoordinator;", "autosaveObserverCoordinator", "getAutosaveObserverCoordinator$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/internal/observers/AutosaveObserverCoordinator;", "setAutosaveObserverCoordinator$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/autosave/internal/observers/AutosaveObserverCoordinator;)V", "Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "autosavePreferences", "getAutosavePreferences$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;", "setAutosavePreferences$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/autosave/internal/preferences/InternalPreferences;)V", "getComponent$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/internal/dagger/component/AutosaveComponent;", "Lcom/amazon/photos/autosave/internal/db/DestroyableDatabaseWrapper;", "databaseWrapper", "getDatabaseWrapper$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/internal/db/DestroyableDatabaseWrapper;", "setDatabaseWrapper$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/autosave/internal/db/DestroyableDatabaseWrapper;)V", "", "destroyed", "getDestroyed", "()Z", "Lcom/amazon/photos/discovery/Discovery;", "discovery", "getDiscovery$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/discovery/Discovery;", "setDiscovery$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/discovery/Discovery;)V", "Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "operations", "getOperations$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/autosave/internal/AutosaveOperations;", "setOperations$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/autosave/internal/AutosaveOperations;)V", NetworkConstants.PREFERENCES_KEY, "Lcom/amazon/photos/autosave/AutosavePreferences;", "getPreferences", "()Lcom/amazon/photos/autosave/AutosavePreferences;", "Lcom/amazon/photos/uploader/UploadManager;", "uploadManager", "getUploadManager$AndroidPhotosAutosave_release", "()Lcom/amazon/photos/uploader/UploadManager;", "setUploadManager$AndroidPhotosAutosave_release", "(Lcom/amazon/photos/uploader/UploadManager;)V", "addAutosaveEventObserver", "observer", "Lcom/amazon/photos/autosave/AutosaveEventObserver;", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "worker", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "destroy", "", "isDestroyed", "removeAutosaveEventObserver", "throwDestroyedIfNeeded", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveManager implements Destroyable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "AutosaveManager";
    @NotNull
    public static final String WORK_MANAGER_TAG = "AndroidPhotosAutosave_All";
    @NotNull
    public AutosaveFrameworkContext autosaveFrameworkContext;
    @NotNull
    public AutosaveObserverCoordinator autosaveObserverCoordinator;
    @NotNull
    public InternalPreferences autosavePreferences;
    @NotNull
    private final AutosaveComponent component;
    @NotNull
    public DestroyableDatabaseWrapper databaseWrapper;
    private boolean destroyed;
    @NotNull
    public Discovery discovery;
    @NotNull
    public AutosaveOperations operations;
    @NotNull
    public UploadManager uploadManager;

    /* compiled from: AutosaveManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/photos/autosave/AutosaveManager$Companion;", "", "()V", "TAG", "", "WORK_MANAGER_TAG", "shutdown", "", "directedId", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @WorkerThread
        public final void shutdown(@NotNull String directedId) {
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            AutosaveManagerMap.INSTANCE.getAutosaveManagerForAccount$AndroidPhotosAutosave_release(String.valueOf(directedId.hashCode())).destroy();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AutosaveManager(@NotNull AutosaveComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        this.component = component;
        this.component.inject(this);
        AutosaveManagerMap autosaveManagerMap = AutosaveManagerMap.INSTANCE;
        AutosaveFrameworkContext autosaveFrameworkContext = this.autosaveFrameworkContext;
        if (autosaveFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveFrameworkContext");
        }
        autosaveManagerMap.setAutosaveManagerForAccount$AndroidPhotosAutosave_release(autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release(), this);
    }

    private final void throwDestroyedIfNeeded() {
        if (!this.destroyed) {
            return;
        }
        throw new IllegalStateException("AutosaveManager was destroyed.".toString());
    }

    @AnyThread
    public final boolean addAutosaveEventObserver(@NotNull AutosaveEventObserver observer, @NotNull Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        AutosaveObserverCoordinator autosaveObserverCoordinator = this.autosaveObserverCoordinator;
        if (autosaveObserverCoordinator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveObserverCoordinator");
        }
        return autosaveObserverCoordinator.addAutosaveEventObserver$AndroidPhotosAutosave_release(observer, scheduler);
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        AutosaveObserverCoordinator autosaveObserverCoordinator = this.autosaveObserverCoordinator;
        if (autosaveObserverCoordinator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveObserverCoordinator");
        }
        autosaveObserverCoordinator.destroy();
        AutosaveManagerMap autosaveManagerMap = AutosaveManagerMap.INSTANCE;
        AutosaveFrameworkContext autosaveFrameworkContext = this.autosaveFrameworkContext;
        if (autosaveFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveFrameworkContext");
        }
        autosaveManagerMap.removeAutosaveManager$AndroidPhotosAutosave_release(autosaveFrameworkContext.getHashedDirectedId$AndroidPhotosAutosave_release());
        AutosaveOperations autosaveOperations = this.operations;
        if (autosaveOperations == null) {
            Intrinsics.throwUninitializedPropertyAccessException("operations");
        }
        autosaveOperations.destroy$AndroidPhotosAutosave_release();
        DestroyableDatabaseWrapper destroyableDatabaseWrapper = this.databaseWrapper;
        if (destroyableDatabaseWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseWrapper");
        }
        destroyableDatabaseWrapper.destroy();
        InternalPreferences internalPreferences = this.autosavePreferences;
        if (internalPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosavePreferences");
        }
        internalPreferences.destroy();
        this.destroyed = true;
    }

    @NotNull
    public final AutosaveFrameworkContext getAutosaveFrameworkContext$AndroidPhotosAutosave_release() {
        AutosaveFrameworkContext autosaveFrameworkContext = this.autosaveFrameworkContext;
        if (autosaveFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveFrameworkContext");
        }
        return autosaveFrameworkContext;
    }

    @NotNull
    public final AutosaveObserverCoordinator getAutosaveObserverCoordinator$AndroidPhotosAutosave_release() {
        AutosaveObserverCoordinator autosaveObserverCoordinator = this.autosaveObserverCoordinator;
        if (autosaveObserverCoordinator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveObserverCoordinator");
        }
        return autosaveObserverCoordinator;
    }

    @NotNull
    public final InternalPreferences getAutosavePreferences$AndroidPhotosAutosave_release() {
        InternalPreferences internalPreferences = this.autosavePreferences;
        if (internalPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosavePreferences");
        }
        return internalPreferences;
    }

    @NotNull
    public final AutosaveComponent getComponent$AndroidPhotosAutosave_release() {
        return this.component;
    }

    @NotNull
    public final DestroyableDatabaseWrapper getDatabaseWrapper$AndroidPhotosAutosave_release() {
        DestroyableDatabaseWrapper destroyableDatabaseWrapper = this.databaseWrapper;
        if (destroyableDatabaseWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseWrapper");
        }
        return destroyableDatabaseWrapper;
    }

    public final boolean getDestroyed() {
        return this.destroyed;
    }

    @NotNull
    public final Discovery getDiscovery$AndroidPhotosAutosave_release() {
        Discovery discovery = this.discovery;
        if (discovery == null) {
            Intrinsics.throwUninitializedPropertyAccessException("discovery");
        }
        return discovery;
    }

    @NotNull
    public final AutosaveOperations getOperations$AndroidPhotosAutosave_release() {
        AutosaveOperations autosaveOperations = this.operations;
        if (autosaveOperations == null) {
            Intrinsics.throwUninitializedPropertyAccessException("operations");
        }
        return autosaveOperations;
    }

    @NotNull
    public final AutosavePreferences getPreferences() {
        throwDestroyedIfNeeded();
        InternalPreferences internalPreferences = this.autosavePreferences;
        if (internalPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosavePreferences");
        }
        return internalPreferences;
    }

    @NotNull
    public final UploadManager getUploadManager$AndroidPhotosAutosave_release() {
        UploadManager uploadManager = this.uploadManager;
        if (uploadManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadManager");
        }
        return uploadManager;
    }

    @Override // javax.security.auth.Destroyable
    @AnyThread
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @AnyThread
    public final boolean removeAutosaveEventObserver(@NotNull AutosaveEventObserver observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        AutosaveObserverCoordinator autosaveObserverCoordinator = this.autosaveObserverCoordinator;
        if (autosaveObserverCoordinator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveObserverCoordinator");
        }
        return autosaveObserverCoordinator.removeAutosaveEventObserver(observer);
    }

    @Inject
    public final void setAutosaveFrameworkContext$AndroidPhotosAutosave_release(@NotNull AutosaveFrameworkContext autosaveFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(autosaveFrameworkContext, "<set-?>");
        this.autosaveFrameworkContext = autosaveFrameworkContext;
    }

    @Inject
    public final void setAutosaveObserverCoordinator$AndroidPhotosAutosave_release(@NotNull AutosaveObserverCoordinator autosaveObserverCoordinator) {
        Intrinsics.checkParameterIsNotNull(autosaveObserverCoordinator, "<set-?>");
        this.autosaveObserverCoordinator = autosaveObserverCoordinator;
    }

    @Inject
    public final void setAutosavePreferences$AndroidPhotosAutosave_release(@NotNull InternalPreferences internalPreferences) {
        Intrinsics.checkParameterIsNotNull(internalPreferences, "<set-?>");
        this.autosavePreferences = internalPreferences;
    }

    @Inject
    public final void setDatabaseWrapper$AndroidPhotosAutosave_release(@NotNull DestroyableDatabaseWrapper destroyableDatabaseWrapper) {
        Intrinsics.checkParameterIsNotNull(destroyableDatabaseWrapper, "<set-?>");
        this.databaseWrapper = destroyableDatabaseWrapper;
    }

    @Inject
    public final void setDiscovery$AndroidPhotosAutosave_release(@NotNull Discovery discovery) {
        Intrinsics.checkParameterIsNotNull(discovery, "<set-?>");
        this.discovery = discovery;
    }

    @Inject
    public final void setOperations$AndroidPhotosAutosave_release(@NotNull AutosaveOperations autosaveOperations) {
        Intrinsics.checkParameterIsNotNull(autosaveOperations, "<set-?>");
        this.operations = autosaveOperations;
    }

    @Inject
    public final void setUploadManager$AndroidPhotosAutosave_release(@NotNull UploadManager uploadManager) {
        Intrinsics.checkParameterIsNotNull(uploadManager, "<set-?>");
        this.uploadManager = uploadManager;
    }

    @AnyThread
    public final boolean addAutosaveEventObserver(@NotNull AutosaveEventObserver observer, @NotNull Scheduler.Worker worker) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Intrinsics.checkParameterIsNotNull(worker, "worker");
        AutosaveObserverCoordinator autosaveObserverCoordinator = this.autosaveObserverCoordinator;
        if (autosaveObserverCoordinator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("autosaveObserverCoordinator");
        }
        return autosaveObserverCoordinator.addAutosaveEventObserver(observer, worker);
    }
}
