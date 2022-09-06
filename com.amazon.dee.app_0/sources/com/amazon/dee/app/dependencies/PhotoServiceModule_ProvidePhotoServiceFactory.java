package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.services.photos.PhotoServiceFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PhotoServiceModule_ProvidePhotoServiceFactory implements Factory<PhotoService> {
    private final PhotoServiceModule module;
    private final Provider<PhotoServiceFactory> photoServiceFactoryProvider;

    public PhotoServiceModule_ProvidePhotoServiceFactory(PhotoServiceModule photoServiceModule, Provider<PhotoServiceFactory> provider) {
        this.module = photoServiceModule;
        this.photoServiceFactoryProvider = provider;
    }

    public static PhotoServiceModule_ProvidePhotoServiceFactory create(PhotoServiceModule photoServiceModule, Provider<PhotoServiceFactory> provider) {
        return new PhotoServiceModule_ProvidePhotoServiceFactory(photoServiceModule, provider);
    }

    public static PhotoService provideInstance(PhotoServiceModule photoServiceModule, Provider<PhotoServiceFactory> provider) {
        return proxyProvidePhotoService(photoServiceModule, provider.mo10268get());
    }

    public static PhotoService proxyProvidePhotoService(PhotoServiceModule photoServiceModule, PhotoServiceFactory photoServiceFactory) {
        return (PhotoService) Preconditions.checkNotNull(photoServiceModule.providePhotoService(photoServiceFactory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotoService mo10268get() {
        return provideInstance(this.module, this.photoServiceFactoryProvider);
    }
}
