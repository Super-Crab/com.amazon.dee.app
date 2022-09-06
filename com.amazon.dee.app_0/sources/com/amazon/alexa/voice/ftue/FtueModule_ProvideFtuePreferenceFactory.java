package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.protocols.storage.PersistentStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FtueModule_ProvideFtuePreferenceFactory implements Factory<FtuePreference> {
    private final Provider<PersistentStorage.Factory> factoryProvider;

    public FtueModule_ProvideFtuePreferenceFactory(Provider<PersistentStorage.Factory> provider) {
        this.factoryProvider = provider;
    }

    public static FtueModule_ProvideFtuePreferenceFactory create(Provider<PersistentStorage.Factory> provider) {
        return new FtueModule_ProvideFtuePreferenceFactory(provider);
    }

    public static FtuePreference provideInstance(Provider<PersistentStorage.Factory> provider) {
        return proxyProvideFtuePreference(provider.mo10268get());
    }

    public static FtuePreference proxyProvideFtuePreference(PersistentStorage.Factory factory) {
        return (FtuePreference) Preconditions.checkNotNull(FtueModule.provideFtuePreference(factory), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FtuePreference mo10268get() {
        return provideInstance(this.factoryProvider);
    }
}
