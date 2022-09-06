package com.amazon.photos.uploader.cds.nodecache;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory implements Factory<ParentIdDao> {
    private final NodeCacheDatabaseModule module;

    public NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        this.module = nodeCacheDatabaseModule;
    }

    public static NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory create(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        return new NodeCacheDatabaseModule_ProvideParentIdDao$AndroidPhotosUploader_releaseFactory(nodeCacheDatabaseModule);
    }

    public static ParentIdDao provideParentIdDao$AndroidPhotosUploader_release(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        return (ParentIdDao) Preconditions.checkNotNull(nodeCacheDatabaseModule.provideParentIdDao$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ParentIdDao mo10268get() {
        return provideParentIdDao$AndroidPhotosUploader_release(this.module);
    }
}
