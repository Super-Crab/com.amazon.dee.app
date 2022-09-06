package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory implements Factory<LocaleCombinationSettingHandler> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<LocaleInteractor> localeInteractorProvider;

    public SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        this.localeInteractorProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory create(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        return new SettingsModule_ProvideLocaleCombinationSettingsHandlerFactory(provider, provider2);
    }

    public static LocaleCombinationSettingHandler provideInstance(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        return proxyProvideLocaleCombinationSettingsHandler(provider.mo10268get(), provider2.mo10268get());
    }

    public static LocaleCombinationSettingHandler proxyProvideLocaleCombinationSettingsHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
        return (LocaleCombinationSettingHandler) Preconditions.checkNotNull(SettingsModule.provideLocaleCombinationSettingsHandler(localeInteractor, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocaleCombinationSettingHandler mo10268get() {
        return provideInstance(this.localeInteractorProvider, this.eventBusProvider);
    }
}
