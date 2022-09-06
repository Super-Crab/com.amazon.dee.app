package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.imageloader.api.ImageLoader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideImageLoaderFactory implements Factory<ImageLoader> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideImageLoaderFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideImageLoaderFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideImageLoaderFactory(applicationModule);
    }

    public static ImageLoader provideInstance(ApplicationModule applicationModule) {
        return proxyProvideImageLoader(applicationModule);
    }

    public static ImageLoader proxyProvideImageLoader(ApplicationModule applicationModule) {
        return (ImageLoader) Preconditions.checkNotNull(applicationModule.provideImageLoader(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ImageLoader mo10268get() {
        return provideInstance(this.module);
    }
}
