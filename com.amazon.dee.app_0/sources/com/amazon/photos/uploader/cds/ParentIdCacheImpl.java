package com.amazon.photos.uploader.cds;

import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.cds.nodecache.ParentId;
import com.amazon.photos.uploader.cds.nodecache.ParentIdDao;
import com.amazon.photos.uploader.cds.observer.CdsUploadObservable;
import com.amazon.photos.uploader.cds.observer.CdsUploadObserver;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ParentIdCacheImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\"\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0014H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/amazon/photos/uploader/cds/ParentIdCacheImpl;", "Lcom/amazon/photos/uploader/cds/ParentIdCache;", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadObserver;", "parentIdDao", "Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", "nodeCacheDBWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "cdsUploadObservable", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadObservable;", "(Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;Lcom/amazon/photos/uploader/cds/observer/CdsUploadObservable;)V", "getCdsUploadObservable", "()Lcom/amazon/photos/uploader/cds/observer/CdsUploadObservable;", "getNodeCacheDBWrapper", "()Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "getParentIdDao", "()Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", MetricsConstants.Method.CACHE_CLEAR, "", "destroy", MetricsConstants.Method.CACHE_GET, "", RouteParameter.PATH, "onUploadFailed", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", MetricsConstants.Method.CACHE_PUT, AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ParentIdCacheImpl implements ParentIdCache, CdsUploadObserver {
    @NotNull
    private final CdsUploadObservable cdsUploadObservable;
    @NotNull
    private final DestroyableDatabaseWrapper nodeCacheDBWrapper;
    @NotNull
    private final ParentIdDao parentIdDao;

    public ParentIdCacheImpl(@NotNull ParentIdDao parentIdDao, @NotNull DestroyableDatabaseWrapper nodeCacheDBWrapper, @NotNull CdsUploadObservable cdsUploadObservable) {
        Intrinsics.checkParameterIsNotNull(parentIdDao, "parentIdDao");
        Intrinsics.checkParameterIsNotNull(nodeCacheDBWrapper, "nodeCacheDBWrapper");
        Intrinsics.checkParameterIsNotNull(cdsUploadObservable, "cdsUploadObservable");
        this.parentIdDao = parentIdDao;
        this.nodeCacheDBWrapper = nodeCacheDBWrapper;
        this.cdsUploadObservable = cdsUploadObservable;
        CdsUploadObservable.DefaultImpls.addObserver$default(this.cdsUploadObservable, this, null, 2, null);
    }

    @Override // com.amazon.photos.uploader.cds.ParentIdCache
    public void clear() {
        this.parentIdDao.deleteAll();
    }

    @Override // com.amazon.photos.uploader.cds.ParentIdCache
    public void destroy() {
        this.nodeCacheDBWrapper.destroy();
    }

    @Override // com.amazon.photos.uploader.cds.ParentIdCache
    @Nullable
    public String get(@NotNull String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return this.parentIdDao.get(path);
    }

    @NotNull
    public final CdsUploadObservable getCdsUploadObservable() {
        return this.cdsUploadObservable;
    }

    @NotNull
    public final DestroyableDatabaseWrapper getNodeCacheDBWrapper() {
        return this.nodeCacheDBWrapper;
    }

    @NotNull
    public final ParentIdDao getParentIdDao() {
        return this.parentIdDao;
    }

    @Override // com.amazon.photos.uploader.cds.observer.CdsUploadObserver
    public void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        if (errorCategory == UploadErrorCategory.PARENT_ERROR) {
            clear();
        }
    }

    @Override // com.amazon.photos.uploader.cds.ParentIdCache
    public void put(@NotNull String path, @NotNull String nodeId) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(nodeId, "nodeId");
        this.parentIdDao.insert(new ParentId(path, nodeId));
    }
}
