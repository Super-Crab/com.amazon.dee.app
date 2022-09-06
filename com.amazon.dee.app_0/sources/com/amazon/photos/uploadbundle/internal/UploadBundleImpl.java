package com.amazon.photos.uploadbundle.internal;

import androidx.annotation.WorkerThread;
import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploadbundle.UploadBundle;
import com.amazon.photos.uploader.UploadManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadBundleImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0002R\u0014\u0010\t\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lcom/amazon/photos/uploadbundle/internal/UploadBundleImpl;", "Lcom/amazon/photos/uploadbundle/UploadBundle;", "bundleUploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "bundleDiscovery", "Lcom/amazon/photos/discovery/Discovery;", "bundleAutosaveManager", "Lcom/amazon/photos/autosave/AutosaveManager;", "(Lcom/amazon/photos/uploader/UploadManager;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/autosave/AutosaveManager;)V", "autosaveManager", "getAutosaveManager", "()Lcom/amazon/photos/autosave/AutosaveManager;", "destroyed", "", "discovery", "getDiscovery", "()Lcom/amazon/photos/discovery/Discovery;", "uploadManager", "getUploadManager", "()Lcom/amazon/photos/uploader/UploadManager;", "destroy", "", "isDestroyed", "pauseAllWork", "resumeAllWork", "throwDestroyedIfNeeded", "AndroidPhotosUploadBundle_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadBundleImpl implements UploadBundle {
    private final AutosaveManager bundleAutosaveManager;
    private final Discovery bundleDiscovery;
    private final UploadManager bundleUploadManager;
    private boolean destroyed;

    public UploadBundleImpl(@NotNull UploadManager bundleUploadManager, @NotNull Discovery bundleDiscovery, @NotNull AutosaveManager bundleAutosaveManager) {
        Intrinsics.checkParameterIsNotNull(bundleUploadManager, "bundleUploadManager");
        Intrinsics.checkParameterIsNotNull(bundleDiscovery, "bundleDiscovery");
        Intrinsics.checkParameterIsNotNull(bundleAutosaveManager, "bundleAutosaveManager");
        this.bundleUploadManager = bundleUploadManager;
        this.bundleDiscovery = bundleDiscovery;
        this.bundleAutosaveManager = bundleAutosaveManager;
    }

    private final void throwDestroyedIfNeeded() {
        if (!this.destroyed) {
            return;
        }
        throw new IllegalStateException("UploadBundle was destroyed.".toString());
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        getAutosaveManager().destroy();
        getDiscovery().destroy();
        getUploadManager().destroy();
        this.destroyed = true;
    }

    @Override // com.amazon.photos.uploadbundle.UploadBundle
    @NotNull
    public AutosaveManager getAutosaveManager() {
        throwDestroyedIfNeeded();
        return this.bundleAutosaveManager;
    }

    @Override // com.amazon.photos.uploadbundle.UploadBundle
    @NotNull
    public Discovery getDiscovery() {
        throwDestroyedIfNeeded();
        return this.bundleDiscovery;
    }

    @Override // com.amazon.photos.uploadbundle.UploadBundle
    @NotNull
    public UploadManager getUploadManager() {
        throwDestroyedIfNeeded();
        return this.bundleUploadManager;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // com.amazon.photos.uploadbundle.UploadBundle
    public void pauseAllWork() {
        throwDestroyedIfNeeded();
        getDiscovery().getOperations().disable();
        getUploadManager().getOperations().pauseAllUploads().waitForResult();
    }

    @Override // com.amazon.photos.uploadbundle.UploadBundle
    public void resumeAllWork() {
        throwDestroyedIfNeeded();
        getDiscovery().getOperations().enable();
        getUploadManager().getOperations().resumeAllUploads().waitForResult();
    }
}
