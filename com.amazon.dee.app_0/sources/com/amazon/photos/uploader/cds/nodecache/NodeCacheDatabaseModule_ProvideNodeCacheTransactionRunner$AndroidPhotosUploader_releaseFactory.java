package com.amazon.photos.uploader.cds.nodecache;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class NodeCacheDatabaseModule_ProvideNodeCacheTransactionRunner$AndroidPhotosUploader_releaseFactory implements Factory<NodeCacheTransactionRunner> {
    private final NodeCacheDatabaseModule module;

    public NodeCacheDatabaseModule_ProvideNodeCacheTransactionRunner$AndroidPhotosUploader_releaseFactory(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        this.module = nodeCacheDatabaseModule;
    }

    public static NodeCacheDatabaseModule_ProvideNodeCacheTransactionRunner$AndroidPhotosUploader_releaseFactory create(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        return new NodeCacheDatabaseModule_ProvideNodeCacheTransactionRunner$AndroidPhotosUploader_releaseFactory(nodeCacheDatabaseModule);
    }

    public static NodeCacheTransactionRunner provideNodeCacheTransactionRunner$AndroidPhotosUploader_release(NodeCacheDatabaseModule nodeCacheDatabaseModule) {
        return (NodeCacheTransactionRunner) Preconditions.checkNotNull(nodeCacheDatabaseModule.provideNodeCacheTransactionRunner$AndroidPhotosUploader_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NodeCacheTransactionRunner mo10268get() {
        return provideNodeCacheTransactionRunner$AndroidPhotosUploader_release(this.module);
    }
}
