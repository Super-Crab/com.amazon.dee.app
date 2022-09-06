package com.amazon.dee.app.dependencies;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundService;
import com.amazon.dee.app.services.photos.PhotoServiceFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PhotoServiceModule_ProvidePhotoServiceFactoryFactory implements Factory<PhotoServiceFactory> {
    private final Provider<CloudDriveService> cloudDriveServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final PhotoServiceModule module;
    private final Provider<AlexaPhotosBackgroundService> photosBackgroundServiceProvider;

    public PhotoServiceModule_ProvidePhotoServiceFactoryFactory(PhotoServiceModule photoServiceModule, Provider<AlexaPhotosBackgroundService> provider, Provider<CloudDriveService> provider2, Provider<IdentityService> provider3, Provider<EventBus> provider4) {
        this.module = photoServiceModule;
        this.photosBackgroundServiceProvider = provider;
        this.cloudDriveServiceProvider = provider2;
        this.identityServiceProvider = provider3;
        this.eventBusProvider = provider4;
    }

    public static PhotoServiceModule_ProvidePhotoServiceFactoryFactory create(PhotoServiceModule photoServiceModule, Provider<AlexaPhotosBackgroundService> provider, Provider<CloudDriveService> provider2, Provider<IdentityService> provider3, Provider<EventBus> provider4) {
        return new PhotoServiceModule_ProvidePhotoServiceFactoryFactory(photoServiceModule, provider, provider2, provider3, provider4);
    }

    public static PhotoServiceFactory provideInstance(PhotoServiceModule photoServiceModule, Provider<AlexaPhotosBackgroundService> provider, Provider<CloudDriveService> provider2, Provider<IdentityService> provider3, Provider<EventBus> provider4) {
        return proxyProvidePhotoServiceFactory(photoServiceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static PhotoServiceFactory proxyProvidePhotoServiceFactory(PhotoServiceModule photoServiceModule, AlexaPhotosBackgroundService alexaPhotosBackgroundService, CloudDriveService cloudDriveService, IdentityService identityService, EventBus eventBus) {
        return (PhotoServiceFactory) Preconditions.checkNotNull(photoServiceModule.providePhotoServiceFactory(alexaPhotosBackgroundService, cloudDriveService, identityService, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PhotoServiceFactory mo10268get() {
        return provideInstance(this.module, this.photosBackgroundServiceProvider, this.cloudDriveServiceProvider, this.identityServiceProvider, this.eventBusProvider);
    }
}
