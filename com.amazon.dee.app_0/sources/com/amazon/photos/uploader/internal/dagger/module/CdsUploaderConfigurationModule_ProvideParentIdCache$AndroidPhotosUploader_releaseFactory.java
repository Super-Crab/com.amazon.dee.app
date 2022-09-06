package com.amazon.photos.uploader.internal.dagger.module;

import com.amazon.photos.uploader.cds.ParentIdCache;
import com.amazon.photos.uploader.cds.nodecache.ParentIdDao;
import com.amazon.photos.uploader.cds.observer.CdsUploadObservable;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory implements Factory<ParentIdCache> {
    private final Provider<CdsUploadObservable> cdsUploadObservableProvider;
    private final CdsUploaderConfigurationModule module;
    private final Provider<DestroyableDatabaseWrapper> nodeCacheDBWrapperProvider;
    private final Provider<ParentIdDao> parentIdDaoProvider;

    public CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<ParentIdDao> provider, Provider<DestroyableDatabaseWrapper> provider2, Provider<CdsUploadObservable> provider3) {
        this.module = cdsUploaderConfigurationModule;
        this.parentIdDaoProvider = provider;
        this.nodeCacheDBWrapperProvider = provider2;
        this.cdsUploadObservableProvider = provider3;
    }

    public static CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory create(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, Provider<ParentIdDao> provider, Provider<DestroyableDatabaseWrapper> provider2, Provider<CdsUploadObservable> provider3) {
        return new CdsUploaderConfigurationModule_ProvideParentIdCache$AndroidPhotosUploader_releaseFactory(cdsUploaderConfigurationModule, provider, provider2, provider3);
    }

    public static ParentIdCache provideParentIdCache$AndroidPhotosUploader_release(CdsUploaderConfigurationModule cdsUploaderConfigurationModule, ParentIdDao parentIdDao, DestroyableDatabaseWrapper destroyableDatabaseWrapper, CdsUploadObservable cdsUploadObservable) {
        return (ParentIdCache) Preconditions.checkNotNull(cdsUploaderConfigurationModule.provideParentIdCache$AndroidPhotosUploader_release(parentIdDao, destroyableDatabaseWrapper, cdsUploadObservable), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ParentIdCache mo10268get() {
        return provideParentIdCache$AndroidPhotosUploader_release(this.module, this.parentIdDaoProvider.mo10268get(), this.nodeCacheDBWrapperProvider.mo10268get(), this.cdsUploadObservableProvider.mo10268get());
    }
}
