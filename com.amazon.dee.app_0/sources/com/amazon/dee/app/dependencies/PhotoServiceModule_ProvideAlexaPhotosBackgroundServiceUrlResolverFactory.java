package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.services.photos.AlexaPhotosBackgroundServiceUrlResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceUrlResolverFactory implements Factory<AlexaPhotosBackgroundServiceUrlResolver> {
    private final Provider<IdentityService> identityServiceProvider;
    private final PhotoServiceModule module;

    public PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceUrlResolverFactory(PhotoServiceModule photoServiceModule, Provider<IdentityService> provider) {
        this.module = photoServiceModule;
        this.identityServiceProvider = provider;
    }

    public static PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceUrlResolverFactory create(PhotoServiceModule photoServiceModule, Provider<IdentityService> provider) {
        return new PhotoServiceModule_ProvideAlexaPhotosBackgroundServiceUrlResolverFactory(photoServiceModule, provider);
    }

    public static AlexaPhotosBackgroundServiceUrlResolver provideInstance(PhotoServiceModule photoServiceModule, Provider<IdentityService> provider) {
        return proxyProvideAlexaPhotosBackgroundServiceUrlResolver(photoServiceModule, provider.mo10268get());
    }

    public static AlexaPhotosBackgroundServiceUrlResolver proxyProvideAlexaPhotosBackgroundServiceUrlResolver(PhotoServiceModule photoServiceModule, IdentityService identityService) {
        return (AlexaPhotosBackgroundServiceUrlResolver) Preconditions.checkNotNull(photoServiceModule.provideAlexaPhotosBackgroundServiceUrlResolver(identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaPhotosBackgroundServiceUrlResolver mo10268get() {
        return provideInstance(this.module, this.identityServiceProvider);
    }
}
