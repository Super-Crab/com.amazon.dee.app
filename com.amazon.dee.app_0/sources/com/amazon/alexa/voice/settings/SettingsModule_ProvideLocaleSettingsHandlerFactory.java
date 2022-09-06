package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideLocaleSettingsHandlerFactory implements Factory<LocaleSettingHandler> {
    private final Provider<EventBus> eventBusProvider;
    private final Provider<LocaleInteractor> localeInteractorProvider;

    public SettingsModule_ProvideLocaleSettingsHandlerFactory(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        this.localeInteractorProvider = provider;
        this.eventBusProvider = provider2;
    }

    public static SettingsModule_ProvideLocaleSettingsHandlerFactory create(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        return new SettingsModule_ProvideLocaleSettingsHandlerFactory(provider, provider2);
    }

    public static LocaleSettingHandler provideInstance(Provider<LocaleInteractor> provider, Provider<EventBus> provider2) {
        return proxyProvideLocaleSettingsHandler(provider.mo10268get(), provider2.mo10268get());
    }

    public static LocaleSettingHandler proxyProvideLocaleSettingsHandler(LocaleInteractor localeInteractor, EventBus eventBus) {
        return (LocaleSettingHandler) Preconditions.checkNotNull(SettingsModule.provideLocaleSettingsHandler(localeInteractor, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocaleSettingHandler mo10268get() {
        return provideInstance(this.localeInteractorProvider, this.eventBusProvider);
    }
}
