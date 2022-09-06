package com.amazon.photos.uploader;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.dao.RequestDao;
import com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploaderDaos.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u000eJ\u0006\u0010\u001f\u001a\u00020\u0014J\u001d\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!2\b\u0010\"\u001a\u0004\u0018\u0001H!H\u0002¢\u0006\u0002\u0010#R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000e@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0014@AX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006$"}, d2 = {"Lcom/amazon/photos/uploader/UploaderDaos;", "", JsonFields.COMPONENT, "Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;", "(Lcom/amazon/photos/uploader/internal/dagger/component/UploadFrameworkComponent;)V", "<set-?>", "Lcom/amazon/photos/uploader/dao/BlockerDao;", "blockerDao", "getBlockerDao$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/dao/BlockerDao;", "setBlockerDao$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/dao/BlockerDao;)V", "destroyed", "", "Lcom/amazon/photos/uploader/dao/EventDao;", "eventDao", "getEventDao$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/dao/EventDao;", "setEventDao$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/dao/EventDao;)V", "Lcom/amazon/photos/uploader/dao/RequestDao;", "requestDao", "getRequestDao$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/dao/RequestDao;", "setRequestDao$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/dao/RequestDao;)V", "destroy", "", "destroy$AndroidPhotosUploader_release", "getBlockerDao", "getEventDao", "getRequestDao", "throwDestroyedIfNeeded", ExifInterface.GPS_DIRECTION_TRUE, "reference", "(Ljava/lang/Object;)Ljava/lang/Object;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploaderDaos {
    @NotNull
    public BlockerDao blockerDao;
    private boolean destroyed;
    @NotNull
    public EventDao eventDao;
    @NotNull
    public RequestDao requestDao;

    public UploaderDaos(@NotNull UploadFrameworkComponent component) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        component.inject(this);
    }

    private final <T> T throwDestroyedIfNeeded(T t) {
        if (this.destroyed || t == null) {
            throw new IllegalStateException("Uploader instance already destroyed for this account.");
        }
        return t;
    }

    public final void destroy$AndroidPhotosUploader_release() {
        this.destroyed = true;
    }

    @NotNull
    public final BlockerDao getBlockerDao() {
        BlockerDao blockerDao = this.blockerDao;
        if (blockerDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockerDao");
        }
        return (BlockerDao) throwDestroyedIfNeeded(blockerDao);
    }

    @NotNull
    public final BlockerDao getBlockerDao$AndroidPhotosUploader_release() {
        BlockerDao blockerDao = this.blockerDao;
        if (blockerDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockerDao");
        }
        return blockerDao;
    }

    @NotNull
    public final EventDao getEventDao() {
        EventDao eventDao = this.eventDao;
        if (eventDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventDao");
        }
        return (EventDao) throwDestroyedIfNeeded(eventDao);
    }

    @NotNull
    public final EventDao getEventDao$AndroidPhotosUploader_release() {
        EventDao eventDao = this.eventDao;
        if (eventDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventDao");
        }
        return eventDao;
    }

    @NotNull
    public final RequestDao getRequestDao() {
        RequestDao requestDao = this.requestDao;
        if (requestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return (RequestDao) throwDestroyedIfNeeded(requestDao);
    }

    @NotNull
    public final RequestDao getRequestDao$AndroidPhotosUploader_release() {
        RequestDao requestDao = this.requestDao;
        if (requestDao == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestDao");
        }
        return requestDao;
    }

    @Inject
    public final void setBlockerDao$AndroidPhotosUploader_release(@NotNull BlockerDao blockerDao) {
        Intrinsics.checkParameterIsNotNull(blockerDao, "<set-?>");
        this.blockerDao = blockerDao;
    }

    @Inject
    public final void setEventDao$AndroidPhotosUploader_release(@NotNull EventDao eventDao) {
        Intrinsics.checkParameterIsNotNull(eventDao, "<set-?>");
        this.eventDao = eventDao;
    }

    @Inject
    public final void setRequestDao$AndroidPhotosUploader_release(@NotNull RequestDao requestDao) {
        Intrinsics.checkParameterIsNotNull(requestDao, "<set-?>");
        this.requestDao = requestDao;
    }
}
