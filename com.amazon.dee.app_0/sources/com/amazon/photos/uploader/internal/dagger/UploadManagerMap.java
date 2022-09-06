package com.amazon.photos.uploader.internal.dagger;

import com.amazon.photos.uploader.UploadManager;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadManagerMap.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u0000¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000fJ\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J\u001d\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0017R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/UploadManagerMap;", "", "()V", "uploadManagerMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/amazon/photos/uploader/UploadManager;", "getRegisteredHashedDirectedIds", "", "getRegisteredHashedDirectedIds$AndroidPhotosUploader_release", "getUploadManagerForAccount", "hashedDirectedId", "getUploadManagerForAccount$AndroidPhotosUploader_release", "isAccountRegistered", "", "isAccountRegistered$AndroidPhotosUploader_release", "removeUploadManager", "", "removeUploadManager$AndroidPhotosUploader_release", "reset", "reset$AndroidPhotosUploader_release", "setUploadManagerForAccount", "uploadManager", "setUploadManagerForAccount$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadManagerMap {
    public static final UploadManagerMap INSTANCE = new UploadManagerMap();
    private static final ConcurrentHashMap<String, UploadManager> uploadManagerMap = new ConcurrentHashMap<>();

    private UploadManagerMap() {
    }

    @NotNull
    public final Set<String> getRegisteredHashedDirectedIds$AndroidPhotosUploader_release() {
        Set<String> keySet = uploadManagerMap.keySet();
        Intrinsics.checkExpressionValueIsNotNull(keySet, "uploadManagerMap.keys");
        return keySet;
    }

    @NotNull
    public final UploadManager getUploadManagerForAccount$AndroidPhotosUploader_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        UploadManager uploadManager = uploadManagerMap.get(hashedDirectedId);
        if (uploadManager != null) {
            return uploadManager;
        }
        throw new IllegalArgumentException("No UploadManager registered for this account.");
    }

    public final boolean isAccountRegistered$AndroidPhotosUploader_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        return uploadManagerMap.containsKey(hashedDirectedId);
    }

    public final void removeUploadManager$AndroidPhotosUploader_release(@NotNull String hashedDirectedId) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        uploadManagerMap.remove(hashedDirectedId);
    }

    public final void reset$AndroidPhotosUploader_release() {
        uploadManagerMap.clear();
    }

    public final void setUploadManagerForAccount$AndroidPhotosUploader_release(@NotNull String hashedDirectedId, @NotNull UploadManager uploadManager) {
        Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        if (uploadManagerMap.get(hashedDirectedId) == null) {
            uploadManagerMap.put(hashedDirectedId, uploadManager);
            return;
        }
        throw new IllegalArgumentException("UploadManager instance already created for this account.");
    }
}
