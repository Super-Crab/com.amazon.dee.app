package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.orientation.LandscapeErrorProvider;
import com.amazon.alexa.mode.ModeService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideLandscapeErrorProviderFactory implements Factory<LandscapeErrorProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<ModeService> modeServiceProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideLandscapeErrorProviderFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<ModeService> provider2) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.modeServiceProvider = provider2;
    }

    public static RepositoryModule_ProvideLandscapeErrorProviderFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<ModeService> provider2) {
        return new RepositoryModule_ProvideLandscapeErrorProviderFactory(repositoryModule, provider, provider2);
    }

    public static LandscapeErrorProvider provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<ModeService> provider2) {
        return proxyProvideLandscapeErrorProvider(repositoryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static LandscapeErrorProvider proxyProvideLandscapeErrorProvider(RepositoryModule repositoryModule, Context context, ModeService modeService) {
        return (LandscapeErrorProvider) Preconditions.checkNotNull(repositoryModule.provideLandscapeErrorProvider(context, modeService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LandscapeErrorProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.modeServiceProvider);
    }
}
