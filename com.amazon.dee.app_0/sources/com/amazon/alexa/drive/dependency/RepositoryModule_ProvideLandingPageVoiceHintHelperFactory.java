package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.landing.LandingPageVoiceHintHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideLandingPageVoiceHintHelperFactory implements Factory<LandingPageVoiceHintHelper> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideLandingPageVoiceHintHelperFactory(RepositoryModule repositoryModule, Provider<Context> provider) {
        this.module = repositoryModule;
        this.contextProvider = provider;
    }

    public static RepositoryModule_ProvideLandingPageVoiceHintHelperFactory create(RepositoryModule repositoryModule, Provider<Context> provider) {
        return new RepositoryModule_ProvideLandingPageVoiceHintHelperFactory(repositoryModule, provider);
    }

    public static LandingPageVoiceHintHelper provideInstance(RepositoryModule repositoryModule, Provider<Context> provider) {
        return proxyProvideLandingPageVoiceHintHelper(repositoryModule, provider.mo10268get());
    }

    public static LandingPageVoiceHintHelper proxyProvideLandingPageVoiceHintHelper(RepositoryModule repositoryModule, Context context) {
        return (LandingPageVoiceHintHelper) Preconditions.checkNotNull(repositoryModule.provideLandingPageVoiceHintHelper(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LandingPageVoiceHintHelper mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
