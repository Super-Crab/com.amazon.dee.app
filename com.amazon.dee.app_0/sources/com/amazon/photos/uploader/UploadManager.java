package com.amazon.photos.uploader;

import androidx.annotation.AnyThread;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.InternalEvaluator;
import com.amazon.photos.uploader.internal.UploadRequestUpdatesNotifier;
import com.amazon.photos.uploader.internal.UploadSummaryNotifier;
import com.amazon.photos.uploader.internal.dagger.UploadManagerMap;
import com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 U2\u00020\u0001:\u0001UB\u000f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010Q\u001a\u00020RH\u0017J\b\u0010S\u001a\u00020\u0015H\u0017J\b\u0010T\u001a\u00020RH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u00020\u000b8F¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u0019@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u001f@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0013\u0010%\u001a\u00020&8F¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010)\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R$\u0010-\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020*@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010,\"\u0004\b/\u00100R$\u00102\u001a\u0002012\u0006\u0010\u000e\u001a\u000201@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u0011\u00107\u001a\u0002088F¢\u0006\u0006\u001a\u0004\b9\u0010:R$\u0010<\u001a\u00020;2\u0006\u0010\u000e\u001a\u00020;@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010B\u001a\u00020A2\u0006\u0010\u000e\u001a\u00020A@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0011\u0010G\u001a\u00020H8F¢\u0006\u0006\u001a\u0004\bI\u0010JR$\u0010L\u001a\u00020K2\u0006\u0010\u000e\u001a\u00020K@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010P¨\u0006V"}, d2 = {"Lcom/amazon/photos/uploader/UploadManager;", "Ljavax/security/auth/Destroyable;", JsonFields.COMPONENT, "Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;", "(Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;)V", "operationsExecutor", "Ljava/util/concurrent/ExecutorService;", "(Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;Ljava/util/concurrent/ExecutorService;)V", "getComponent$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;", "daos", "Lcom/amazon/photos/uploader/UploaderDaos;", "getDaos", "()Lcom/amazon/photos/uploader/UploaderDaos;", "<set-?>", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "databaseWrapper", "getDatabaseWrapper$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "setDatabaseWrapper$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;)V", "", "destroyed", "getDestroyed", "()Z", "Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "internalEvaluator", "getInternalEvaluator$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/InternalEvaluator;", "setInternalEvaluator$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/InternalEvaluator;)V", "Lcom/amazon/photos/uploader/log/UploadLogger;", "logger", "getLogger$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "setLogger$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/log/UploadLogger;)V", "operations", "Lcom/amazon/photos/uploader/UploaderOperations;", "getOperations", "()Lcom/amazon/photos/uploader/UploaderOperations;", "queueManager", "Lcom/amazon/photos/uploader/QueueManager;", "getQueueManager", "()Lcom/amazon/photos/uploader/QueueManager;", "queueManagerInternal", "getQueueManagerInternal$AndroidPhotosUploader_release", "setQueueManagerInternal$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/QueueManager;)V", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "uploadFrameworkContext", "getUploadFrameworkContext$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/UploadFrameworkContext;", "setUploadFrameworkContext$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "uploadRequestObservable", "Lcom/amazon/photos/uploader/observables/UploadRequestObservable;", "getUploadRequestObservable", "()Lcom/amazon/photos/uploader/observables/UploadRequestObservable;", "Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "uploadRequestUpdatesNotifier", "getUploadRequestUpdatesNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;", "setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/UploadRequestUpdatesNotifier;)V", "Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;", "uploadSummaryNotifier", "getUploadSummaryNotifier$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;", "setUploadSummaryNotifier$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/internal/UploadSummaryNotifier;)V", "uploadSummaryObservable", "Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "getUploadSummaryObservable", "()Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "Lcom/amazon/photos/uploader/Uploader;", "uploader", "getUploader$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/Uploader;", "setUploader$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/Uploader;)V", "destroy", "", "isDestroyed", "throwDestroyedIfNeeded", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadManager implements Destroyable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "UploadManager";
    @NotNull
    public static final String WORK_MANAGER_TAG = "AndroidPhotosUploader_All";
    @NotNull
    private final UploadFrameworkComponent component;
    @NotNull
    private final UploaderDaos daos;
    @NotNull
    public DestroyableDatabaseWrapper databaseWrapper;
    private boolean destroyed;
    @NotNull
    public InternalEvaluator internalEvaluator;
    @NotNull
    public UploadLogger logger;
    @NotNull
    private final UploaderOperations operations;
    private final ExecutorService operationsExecutor;
    @NotNull
    public QueueManager queueManagerInternal;
    @NotNull
    public UploadFrameworkContext uploadFrameworkContext;
    @NotNull
    public UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier;
    @NotNull
    public UploadSummaryNotifier uploadSummaryNotifier;
    @NotNull
    public Uploader uploader;

    /* compiled from: UploadManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/UploadManager$Companion;", "", "()V", "TAG", "", "WORK_MANAGER_TAG", "shutdown", "", "directedId", "shutdownAllExcluding", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @WorkerThread
        public final void shutdown(@NotNull String directedId) {
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            UploadManagerMap.INSTANCE.getUploadManagerForAccount$AndroidPhotosUploader_release(String.valueOf(directedId.hashCode())).destroy();
        }

        @WorkerThread
        public final void shutdownAllExcluding(@NotNull String directedId) {
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            String valueOf = String.valueOf(directedId.hashCode());
            for (String str : UploadManagerMap.INSTANCE.getRegisteredHashedDirectedIds$AndroidPhotosUploader_release()) {
                if (!Intrinsics.areEqual(str, valueOf)) {
                    UploadManagerMap.INSTANCE.getUploadManagerForAccount$AndroidPhotosUploader_release(str).destroy();
                }
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UploadManager(@NotNull UploadFrameworkComponent component, @NotNull ExecutorService operationsExecutor) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(operationsExecutor, "operationsExecutor");
        this.component = component;
        this.operationsExecutor = operationsExecutor;
        this.operations = new UploaderOperations(this.component, this.operationsExecutor);
        this.component.inject(this);
        UploadManagerMap uploadManagerMap = UploadManagerMap.INSTANCE;
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        if (uploadFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadFrameworkContext");
        }
        uploadManagerMap.setUploadManagerForAccount$AndroidPhotosUploader_release(uploadFrameworkContext.getHashedDirectedId(), this);
        this.daos = new UploaderDaos(this.component);
    }

    private final void throwDestroyedIfNeeded() {
        if (!this.destroyed) {
            return;
        }
        throw new IllegalStateException("UploadManager was destroyed.".toString());
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        uploadLogger.i$AndroidPhotosUploader_release(TAG, "destroy Upload Manager Start");
        UploadManagerMap uploadManagerMap = UploadManagerMap.INSTANCE;
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        if (uploadFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadFrameworkContext");
        }
        uploadManagerMap.removeUploadManager$AndroidPhotosUploader_release(uploadFrameworkContext.getHashedDirectedId());
        getOperations().destroy$AndroidPhotosUploader_release();
        this.operationsExecutor.shutdown();
        UploadSummaryNotifier uploadSummaryNotifier = this.uploadSummaryNotifier;
        if (uploadSummaryNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryNotifier");
        }
        uploadSummaryNotifier.clear$AndroidPhotosUploader_release();
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        uploadRequestUpdatesNotifier.clear$AndroidPhotosUploader_release();
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        if (!(uploader instanceof Destroyable)) {
            uploader = null;
        }
        Destroyable destroyable = (Destroyable) uploader;
        if (destroyable != null) {
            destroyable.destroy();
        }
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        internalEvaluator.destroy();
        getDaos().destroy$AndroidPhotosUploader_release();
        DestroyableDatabaseWrapper destroyableDatabaseWrapper = this.databaseWrapper;
        if (destroyableDatabaseWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseWrapper");
        }
        destroyableDatabaseWrapper.destroy();
        this.destroyed = true;
        UploadLogger uploadLogger2 = this.logger;
        if (uploadLogger2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        uploadLogger2.i$AndroidPhotosUploader_release(TAG, "destroy Upload Manager End");
    }

    @NotNull
    public final UploadFrameworkComponent getComponent$AndroidPhotosUploader_release() {
        return this.component;
    }

    @NotNull
    public final UploaderDaos getDaos() {
        throwDestroyedIfNeeded();
        return this.daos;
    }

    @NotNull
    public final DestroyableDatabaseWrapper getDatabaseWrapper$AndroidPhotosUploader_release() {
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
    public final InternalEvaluator getInternalEvaluator$AndroidPhotosUploader_release() {
        InternalEvaluator internalEvaluator = this.internalEvaluator;
        if (internalEvaluator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEvaluator");
        }
        return internalEvaluator;
    }

    @NotNull
    public final UploadLogger getLogger$AndroidPhotosUploader_release() {
        UploadLogger uploadLogger = this.logger;
        if (uploadLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return uploadLogger;
    }

    @NotNull
    public final UploaderOperations getOperations() {
        throwDestroyedIfNeeded();
        return this.operations;
    }

    @NotNull
    public final QueueManager getQueueManager() {
        throwDestroyedIfNeeded();
        QueueManager queueManager = this.queueManagerInternal;
        if (queueManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueManagerInternal");
        }
        return queueManager;
    }

    @NotNull
    public final QueueManager getQueueManagerInternal$AndroidPhotosUploader_release() {
        QueueManager queueManager = this.queueManagerInternal;
        if (queueManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queueManagerInternal");
        }
        return queueManager;
    }

    @NotNull
    public final UploadFrameworkContext getUploadFrameworkContext$AndroidPhotosUploader_release() {
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        if (uploadFrameworkContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadFrameworkContext");
        }
        return uploadFrameworkContext;
    }

    @NotNull
    public final UploadRequestObservable getUploadRequestObservable() {
        throwDestroyedIfNeeded();
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        return uploadRequestUpdatesNotifier;
    }

    @NotNull
    public final UploadRequestUpdatesNotifier getUploadRequestUpdatesNotifier$AndroidPhotosUploader_release() {
        UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier = this.uploadRequestUpdatesNotifier;
        if (uploadRequestUpdatesNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadRequestUpdatesNotifier");
        }
        return uploadRequestUpdatesNotifier;
    }

    @NotNull
    public final UploadSummaryNotifier getUploadSummaryNotifier$AndroidPhotosUploader_release() {
        UploadSummaryNotifier uploadSummaryNotifier = this.uploadSummaryNotifier;
        if (uploadSummaryNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryNotifier");
        }
        return uploadSummaryNotifier;
    }

    @NotNull
    public final UploadSummaryObservable getUploadSummaryObservable() {
        throwDestroyedIfNeeded();
        UploadSummaryNotifier uploadSummaryNotifier = this.uploadSummaryNotifier;
        if (uploadSummaryNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploadSummaryNotifier");
        }
        return uploadSummaryNotifier;
    }

    @NotNull
    public final Uploader getUploader$AndroidPhotosUploader_release() {
        Uploader uploader = this.uploader;
        if (uploader == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uploader");
        }
        return uploader;
    }

    @Override // javax.security.auth.Destroyable
    @AnyThread
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Inject
    public final void setDatabaseWrapper$AndroidPhotosUploader_release(@NotNull DestroyableDatabaseWrapper destroyableDatabaseWrapper) {
        Intrinsics.checkParameterIsNotNull(destroyableDatabaseWrapper, "<set-?>");
        this.databaseWrapper = destroyableDatabaseWrapper;
    }

    @Inject
    public final void setInternalEvaluator$AndroidPhotosUploader_release(@NotNull InternalEvaluator internalEvaluator) {
        Intrinsics.checkParameterIsNotNull(internalEvaluator, "<set-?>");
        this.internalEvaluator = internalEvaluator;
    }

    @Inject
    public final void setLogger$AndroidPhotosUploader_release(@NotNull UploadLogger uploadLogger) {
        Intrinsics.checkParameterIsNotNull(uploadLogger, "<set-?>");
        this.logger = uploadLogger;
    }

    @Inject
    public final void setQueueManagerInternal$AndroidPhotosUploader_release(@NotNull QueueManager queueManager) {
        Intrinsics.checkParameterIsNotNull(queueManager, "<set-?>");
        this.queueManagerInternal = queueManager;
    }

    @Inject
    public final void setUploadFrameworkContext$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "<set-?>");
        this.uploadFrameworkContext = uploadFrameworkContext;
    }

    @Inject
    public final void setUploadRequestUpdatesNotifier$AndroidPhotosUploader_release(@NotNull UploadRequestUpdatesNotifier uploadRequestUpdatesNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadRequestUpdatesNotifier, "<set-?>");
        this.uploadRequestUpdatesNotifier = uploadRequestUpdatesNotifier;
    }

    @Inject
    public final void setUploadSummaryNotifier$AndroidPhotosUploader_release(@NotNull UploadSummaryNotifier uploadSummaryNotifier) {
        Intrinsics.checkParameterIsNotNull(uploadSummaryNotifier, "<set-?>");
        this.uploadSummaryNotifier = uploadSummaryNotifier;
    }

    @Inject
    public final void setUploader$AndroidPhotosUploader_release(@NotNull Uploader uploader) {
        Intrinsics.checkParameterIsNotNull(uploader, "<set-?>");
        this.uploader = uploader;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UploadManager(@org.jetbrains.annotations.NotNull com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent r3) {
        /*
            r2 = this;
            java.lang.String r0 = "component"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            com.amazon.photos.uploader.UploaderThreadFactory r0 = new com.amazon.photos.uploader.UploaderThreadFactory
            java.lang.String r1 = "UploadOperations"
            r0.<init>(r1)
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor(r0)
            java.lang.String r1 = "Executors.newSingleThrea…(UploaderOperations.TAG))"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r2.<init>(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.UploadManager.<init>(com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent):void");
    }
}
