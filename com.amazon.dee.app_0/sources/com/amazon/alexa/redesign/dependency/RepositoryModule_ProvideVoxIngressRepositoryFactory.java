package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.repository.LocaleRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.voice.model.VoiceService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideVoxIngressRepositoryFactory implements Factory<VoxIngressRepository> {
    private final Provider<ActionFactory> actionFactoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LocaleRepository> localeRepositoryProvider;
    private final RepositoryModule module;
    private final Provider<VoiceService> voiceServiceProvider;

    public RepositoryModule_ProvideVoxIngressRepositoryFactory(RepositoryModule repositoryModule, Provider<ActionFactory> provider, Provider<VoiceService> provider2, Provider<LocaleRepository> provider3, Provider<Context> provider4) {
        this.module = repositoryModule;
        this.actionFactoryProvider = provider;
        this.voiceServiceProvider = provider2;
        this.localeRepositoryProvider = provider3;
        this.contextProvider = provider4;
    }

    public static RepositoryModule_ProvideVoxIngressRepositoryFactory create(RepositoryModule repositoryModule, Provider<ActionFactory> provider, Provider<VoiceService> provider2, Provider<LocaleRepository> provider3, Provider<Context> provider4) {
        return new RepositoryModule_ProvideVoxIngressRepositoryFactory(repositoryModule, provider, provider2, provider3, provider4);
    }

    public static VoxIngressRepository provideInstance(RepositoryModule repositoryModule, Provider<ActionFactory> provider, Provider<VoiceService> provider2, Provider<LocaleRepository> provider3, Provider<Context> provider4) {
        return proxyProvideVoxIngressRepository(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static VoxIngressRepository proxyProvideVoxIngressRepository(RepositoryModule repositoryModule, ActionFactory actionFactory, VoiceService voiceService, LocaleRepository localeRepository, Context context) {
        return (VoxIngressRepository) Preconditions.checkNotNull(repositoryModule.provideVoxIngressRepository(actionFactory, voiceService, localeRepository, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoxIngressRepository mo10268get() {
        return provideInstance(this.module, this.actionFactoryProvider, this.voiceServiceProvider, this.localeRepositoryProvider, this.contextProvider);
    }
}
