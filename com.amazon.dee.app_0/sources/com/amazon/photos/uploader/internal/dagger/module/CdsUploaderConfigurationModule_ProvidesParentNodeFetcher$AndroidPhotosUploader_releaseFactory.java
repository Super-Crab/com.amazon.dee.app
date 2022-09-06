package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import com.amazon.photos.uploader.cds.ParentIdCache;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory implements Factory<ParentNodeFetcher> {
    private final Provider<CdsCallClientWrapper> cdsCallClientWrapperProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<ParentIdCache> parentIdCacheProvider;

    public CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CdsCallClientWrapper> provider, Provider<ParentIdCache> provider2) {
        this.module = cdsUploaderConfigurationModule;
        this.cdsCallClientWrapperProvider = provider;
        this.parentIdCacheProvider = provider2;
    }

    public static CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<CdsCallClientWrapper> provider, Provider<ParentIdCache> provider2) {
        return new CdsUploaderConfigurationModule_ProvidesParentNodeFetcher$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2);
    }

    public static ParentNodeFetcher providesParentNodeFetcher$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, CdsCallClientWrapper cdsCallClientWrapper, ParentIdCache parentIdCache) {
        return (ParentNodeFetcher) Preconditions.checkNotNull(cdsUploaderConfigurationModule.providesParentNodeFetcher$AndroidPhotosUploader_release(cdsCallClientWrapper, parentIdCache), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ParentNodeFetcher mo10268get() {
        return providesParentNodeFetcher$AndroidPhotosUploader_release(this.module, this.cdsCallClientWrapperProvider.mo10268get(), this.parentIdCacheProvider.mo10268get());
    }
}
