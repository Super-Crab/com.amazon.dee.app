package com.amazon.photos.discovery;

import androidx.annotation.AnyThread;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryDaos.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\b\u0010 \u001a\u00020\u0004H\u0007J\b\u0010!\u001a\u00020\nH\u0007J\b\u0010\"\u001a\u00020\u0010H\u0007J\b\u0010#\u001a\u00020\u0016H\u0007J\u001d\u0010$\u001a\u0002H%\"\u0004\b\u0000\u0010%2\b\u0010&\u001a\u0004\u0018\u0001H%H\u0002¢\u0006\u0002\u0010'R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryDaos;", "", "()V", "internalEditDao", "Lcom/amazon/photos/discovery/dao/EditDao;", "getInternalEditDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/dao/EditDao;", "setInternalEditDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/dao/EditDao;)V", "internalLocalFolderDao", "Lcom/amazon/photos/discovery/dao/LocalFolderDao;", "getInternalLocalFolderDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/dao/LocalFolderDao;", "setInternalLocalFolderDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/dao/LocalFolderDao;)V", "internalLocalItemDao", "Lcom/amazon/photos/discovery/dao/LocalItemDao;", "getInternalLocalItemDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/dao/LocalItemDao;", "setInternalLocalItemDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/dao/LocalItemDao;)V", "internalUnifiedItemDao", "Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "getInternalUnifiedItemDao$AndroidPhotosDiscovery_release", "()Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "setInternalUnifiedItemDao$AndroidPhotosDiscovery_release", "(Lcom/amazon/photos/discovery/dao/UnifiedItemDao;)V", "isDestroyed", "", "destroy", "", "destroy$AndroidPhotosDiscovery_release", "getEditDao", "getLocalFolderDao", "getLocalItemDao", "getUnifiedItemDao", "throwDestroyedIfNeeded", ExifInterface.GPS_DIRECTION_TRUE, "reference", "(Ljava/lang/Object;)Ljava/lang/Object;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryDaos {
    @Inject
    @NotNull
    public EditDao internalEditDao;
    @Inject
    @NotNull
    public LocalFolderDao internalLocalFolderDao;
    @Inject
    @NotNull
    public LocalItemDao internalLocalItemDao;
    @Inject
    @NotNull
    public UnifiedItemDao internalUnifiedItemDao;
    private boolean isDestroyed;

    private final <T> T throwDestroyedIfNeeded(T t) {
        if (!this.isDestroyed && t != null) {
            return t;
        }
        throw new IllegalStateException(DiscoveryKt.DISCOVERY_DESTROYED_MSG.toString());
    }

    public final void destroy$AndroidPhotosDiscovery_release() {
        this.isDestroyed = true;
    }

    @AnyThread
    @NotNull
    public final EditDao getEditDao() {
        EditDao editDao = this.internalEditDao;
        if (editDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEditDao");
        }
        return (EditDao) throwDestroyedIfNeeded(editDao);
    }

    @NotNull
    public final EditDao getInternalEditDao$AndroidPhotosDiscovery_release() {
        EditDao editDao = this.internalEditDao;
        if (editDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalEditDao");
        }
        return editDao;
    }

    @NotNull
    public final LocalFolderDao getInternalLocalFolderDao$AndroidPhotosDiscovery_release() {
        LocalFolderDao localFolderDao = this.internalLocalFolderDao;
        if (localFolderDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalLocalFolderDao");
        }
        return localFolderDao;
    }

    @NotNull
    public final LocalItemDao getInternalLocalItemDao$AndroidPhotosDiscovery_release() {
        LocalItemDao localItemDao = this.internalLocalItemDao;
        if (localItemDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalLocalItemDao");
        }
        return localItemDao;
    }

    @NotNull
    public final UnifiedItemDao getInternalUnifiedItemDao$AndroidPhotosDiscovery_release() {
        UnifiedItemDao unifiedItemDao = this.internalUnifiedItemDao;
        if (unifiedItemDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalUnifiedItemDao");
        }
        return unifiedItemDao;
    }

    @AnyThread
    @NotNull
    public final LocalFolderDao getLocalFolderDao() {
        LocalFolderDao localFolderDao = this.internalLocalFolderDao;
        if (localFolderDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalLocalFolderDao");
        }
        return (LocalFolderDao) throwDestroyedIfNeeded(localFolderDao);
    }

    @AnyThread
    @NotNull
    public final LocalItemDao getLocalItemDao() {
        LocalItemDao localItemDao = this.internalLocalItemDao;
        if (localItemDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalLocalItemDao");
        }
        return (LocalItemDao) throwDestroyedIfNeeded(localItemDao);
    }

    @AnyThread
    @NotNull
    public final UnifiedItemDao getUnifiedItemDao() {
        UnifiedItemDao unifiedItemDao = this.internalUnifiedItemDao;
        if (unifiedItemDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalUnifiedItemDao");
        }
        return (UnifiedItemDao) throwDestroyedIfNeeded(unifiedItemDao);
    }

    public final void setInternalEditDao$AndroidPhotosDiscovery_release(@NotNull EditDao editDao) {
        Intrinsics.checkParameterIsNotNull(editDao, "<set-?>");
        this.internalEditDao = editDao;
    }

    public final void setInternalLocalFolderDao$AndroidPhotosDiscovery_release(@NotNull LocalFolderDao localFolderDao) {
        Intrinsics.checkParameterIsNotNull(localFolderDao, "<set-?>");
        this.internalLocalFolderDao = localFolderDao;
    }

    public final void setInternalLocalItemDao$AndroidPhotosDiscovery_release(@NotNull LocalItemDao localItemDao) {
        Intrinsics.checkParameterIsNotNull(localItemDao, "<set-?>");
        this.internalLocalItemDao = localItemDao;
    }

    public final void setInternalUnifiedItemDao$AndroidPhotosDiscovery_release(@NotNull UnifiedItemDao unifiedItemDao) {
        Intrinsics.checkParameterIsNotNull(unifiedItemDao, "<set-?>");
        this.internalUnifiedItemDao = unifiedItemDao;
    }
}
