package com.amazon.photos.uploader.cds.nodecache;

import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory implements Factory<DestroyableDatabaseWrapper> {
    private final Provider<UploadFrameworkContext> frameworkContextProvider;
    private final NodeCacheDatabaseModule module;

    public NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(NodeCacheDatabaseModule nodeCacheDatabaseModule, Provider<UploadFrameworkContext> provider) {
        this.module = nodeCacheDatabaseModule;
        this.frameworkContextProvider = provider;
    }

    public static NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory create(NodeCacheDatabaseModule nodeCacheDatabaseModule, Provider<UploadFrameworkContext> provider) {
        return new NodeCacheDatabaseModule_ProvideDestroyableDatabaseWrapper$AndroidPhotosUploader_releaseFactory(nodeCacheDatabaseModule, provider);
    }

    public static DestroyableDatabaseWrapper provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(NodeCacheDatabaseModule nodeCacheDatabaseModule, UploadFrameworkContext uploadFrameworkContext) {
        return (DestroyableDatabaseWrapper) Preconditions.checkNotNull(nodeCacheDatabaseModule.provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(uploadFrameworkContext), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DestroyableDatabaseWrapper mo10268get() {
        return provideDestroyableDatabaseWrapper$AndroidPhotosUploader_release(this.module, this.frameworkContextProvider.mo10268get());
    }
}
