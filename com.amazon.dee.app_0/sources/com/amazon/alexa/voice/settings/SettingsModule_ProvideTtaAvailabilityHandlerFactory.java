package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideTtaAvailabilityHandlerFactory implements Factory<TtaAvailabilityHandler> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<TypeToAlexaFeatureEnabler> ttaFeatureEnablerProvider;

    public SettingsModule_ProvideTtaAvailabilityHandlerFactory(Provider<EventBus> provider, Provider<TypeToAlexaFeatureEnabler> provider2) {
        this.eventBusProvider = provider;
        this.ttaFeatureEnablerProvider = provider2;
    }

    public static SettingsModule_ProvideTtaAvailabilityHandlerFactory create(Provider<EventBus> provider, Provider<TypeToAlexaFeatureEnabler> provider2) {
        return new SettingsModule_ProvideTtaAvailabilityHandlerFactory(provider, provider2);
    }

    public static TtaAvailabilityHandler provideInstance(Provider<EventBus> provider, Provider<TypeToAlexaFeatureEnabler> provider2) {
        return proxyProvideTtaAvailabilityHandler(provider.mo10268get(), provider2.mo10268get());
    }

    public static TtaAvailabilityHandler proxyProvideTtaAvailabilityHandler(EventBus eventBus, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        return (TtaAvailabilityHandler) Preconditions.checkNotNull(SettingsModule.provideTtaAvailabilityHandler(eventBus, typeToAlexaFeatureEnabler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TtaAvailabilityHandler mo10268get() {
        return provideInstance(this.eventBusProvider, this.ttaFeatureEnablerProvider);
    }
}
