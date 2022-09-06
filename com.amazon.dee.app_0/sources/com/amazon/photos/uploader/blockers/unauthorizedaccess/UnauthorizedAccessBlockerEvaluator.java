package com.amazon.photos.uploader.blockers.unauthorizedaccess;

import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.CacheableBlocker;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.TokenUnavailableBlocker;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import java.util.List;
import java.util.Set;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UnauthorizedAccessBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001)B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0017J\b\u0010\u0010\u001a\u00020\rH\u0017J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\"\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J \u0010 \u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0010\u0010$\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010%\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'H\u0016J\n\u0010(\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "Lcom/amazon/photos/uploader/blockers/CacheableBlocker;", "Ljavax/security/auth/Destroyable;", "persistence", "Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessPersistence;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessPersistence;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "destroyed", "", "destroy", "", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "invalidateCacheBlocking", "isDestroyed", "onRequestAdded", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "onRequestsAbandoned", "abandonedRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadBlocked", "blocker", "onUploadFailed", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadProgressUpdate", "currentProgress", "", "maxProgress", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "queryBlocker", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UnauthorizedAccessBlockerEvaluator implements GlobalBlockerEvaluator, UploadRequestObserver, CacheableBlocker, Destroyable {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "UnauthorizedAccessBlockerEvaluator";
    private boolean destroyed;
    private final Metrics metrics;
    private final UnauthorizedAccessPersistence persistence;

    /* compiled from: UnauthorizedAccessBlockerEvaluator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessBlockerEvaluator$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UnauthorizedAccessBlockerEvaluator(@NotNull UnauthorizedAccessPersistence persistence, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(persistence, "persistence");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.persistence = persistence;
        this.metrics = metrics;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.persistence.clearAuthState$AndroidPhotosUploader_release();
        this.destroyed = true;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @WorkerThread
    @Nullable
    public synchronized Blocker getBlocker() {
        return queryBlocker();
    }

    @Override // com.amazon.photos.uploader.blockers.CacheableBlocker
    @WorkerThread
    public synchronized void invalidateCacheBlocking() {
        this.metrics.recordSimpleEvent(TAG, UnauthorizedAccessBlockerEvaluator$invalidateCacheBlocking$1.INSTANCE, new MetricRecordingType[0]);
        this.persistence.clearAuthState$AndroidPhotosUploader_release();
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NotNull List<AbandonedRequestInfo> abandonedRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(abandonedRequestInfoList, "abandonedRequestInfoList");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        UploadRequestObserver.DefaultImpls.onUploadBlocked(this, uploadRequest, blockers);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    @WorkerThread
    public synchronized void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        if (errorCategory == UploadErrorCategory.AUTH_ERROR && !isDestroyed()) {
            this.persistence.persistAuthState$AndroidPhotosUploader_release(AuthState.UNAUTHENTICATED);
            this.metrics.recordSimpleEvent(TAG, UnauthorizedAccessBlockerEvaluator$onUploadFailed$1.INSTANCE, new MetricRecordingType[0]);
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NotNull UploadRequest uploadRequest, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker queryBlocker() {
        if (this.persistence.getAuthStateWithDefaultFallback$AndroidPhotosUploader_release(AuthState.AUTHENTICATED) == AuthState.AUTHENTICATED || isDestroyed()) {
            return null;
        }
        this.metrics.recordSimpleEvent(TAG, UnauthorizedAccessBlockerEvaluator$queryBlocker$1.INSTANCE, new MetricRecordingType[0]);
        return TokenUnavailableBlocker.INSTANCE;
    }
}
