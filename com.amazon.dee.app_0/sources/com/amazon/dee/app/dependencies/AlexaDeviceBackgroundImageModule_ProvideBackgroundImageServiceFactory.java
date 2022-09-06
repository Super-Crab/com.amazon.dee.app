package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AlexaDeviceBackgroundImageModule_ProvideBackgroundImageServiceFactory implements Factory<BackgroundImageService> {
    private final Provider<CoralService> coralServiceProvider;
    private final AlexaDeviceBackgroundImageModule module;

    public AlexaDeviceBackgroundImageModule_ProvideBackgroundImageServiceFactory(AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule, Provider<CoralService> provider) {
        this.module = alexaDeviceBackgroundImageModule;
        this.coralServiceProvider = provider;
    }

    public static AlexaDeviceBackgroundImageModule_ProvideBackgroundImageServiceFactory create(AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule, Provider<CoralService> provider) {
        return new AlexaDeviceBackgroundImageModule_ProvideBackgroundImageServiceFactory(alexaDeviceBackgroundImageModule, provider);
    }

    public static BackgroundImageService provideInstance(AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule, Provider<CoralService> provider) {
        return proxyProvideBackgroundImageService(alexaDeviceBackgroundImageModule, provider.mo10268get());
    }

    public static BackgroundImageService proxyProvideBackgroundImageService(AlexaDeviceBackgroundImageModule alexaDeviceBackgroundImageModule, CoralService coralService) {
        return (BackgroundImageService) Preconditions.checkNotNull(alexaDeviceBackgroundImageModule.provideBackgroundImageService(coralService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BackgroundImageService mo10268get() {
        return provideInstance(this.module, this.coralServiceProvider);
    }
}
