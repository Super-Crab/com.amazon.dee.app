package com.amazon.alexa.voice.settings;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideVoiceSettingsFactory implements Factory<VoiceSettings> {
    private final Provider<EarconSettingsHandler> earconSettingsHandlerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<HandsfreeSettingsHandler> handsfreeSettingsHandlerProvider;
    private final Provider<HighPriorityActivityHandler> highPriorityActivityHandlerProvider;
    private final Provider<LocaleCombinationSettingHandler> localeCombinationSettingHandlerProvider;
    private final Provider<LocaleSettingHandler> localeSettingHandlerProvider;
    private final Provider<TtaAvailabilityHandler> ttaAvailabilityHandlerProvider;

    public SettingsModule_ProvideVoiceSettingsFactory(Provider<EventBus> provider, Provider<HandsfreeSettingsHandler> provider2, Provider<EarconSettingsHandler> provider3, Provider<LocaleSettingHandler> provider4, Provider<LocaleCombinationSettingHandler> provider5, Provider<HighPriorityActivityHandler> provider6, Provider<TtaAvailabilityHandler> provider7) {
        this.eventBusProvider = provider;
        this.handsfreeSettingsHandlerProvider = provider2;
        this.earconSettingsHandlerProvider = provider3;
        this.localeSettingHandlerProvider = provider4;
        this.localeCombinationSettingHandlerProvider = provider5;
        this.highPriorityActivityHandlerProvider = provider6;
        this.ttaAvailabilityHandlerProvider = provider7;
    }

    public static SettingsModule_ProvideVoiceSettingsFactory create(Provider<EventBus> provider, Provider<HandsfreeSettingsHandler> provider2, Provider<EarconSettingsHandler> provider3, Provider<LocaleSettingHandler> provider4, Provider<LocaleCombinationSettingHandler> provider5, Provider<HighPriorityActivityHandler> provider6, Provider<TtaAvailabilityHandler> provider7) {
        return new SettingsModule_ProvideVoiceSettingsFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static VoiceSettings provideInstance(Provider<EventBus> provider, Provider<HandsfreeSettingsHandler> provider2, Provider<EarconSettingsHandler> provider3, Provider<LocaleSettingHandler> provider4, Provider<LocaleCombinationSettingHandler> provider5, Provider<HighPriorityActivityHandler> provider6, Provider<TtaAvailabilityHandler> provider7) {
        return proxyProvideVoiceSettings(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static VoiceSettings proxyProvideVoiceSettings(EventBus eventBus, HandsfreeSettingsHandler handsfreeSettingsHandler, EarconSettingsHandler earconSettingsHandler, LocaleSettingHandler localeSettingHandler, LocaleCombinationSettingHandler localeCombinationSettingHandler, HighPriorityActivityHandler highPriorityActivityHandler, TtaAvailabilityHandler ttaAvailabilityHandler) {
        return (VoiceSettings) Preconditions.checkNotNull(SettingsModule.provideVoiceSettings(eventBus, handsfreeSettingsHandler, earconSettingsHandler, localeSettingHandler, localeCombinationSettingHandler, highPriorityActivityHandler, ttaAvailabilityHandler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceSettings mo10268get() {
        return provideInstance(this.eventBusProvider, this.handsfreeSettingsHandlerProvider, this.earconSettingsHandlerProvider, this.localeSettingHandlerProvider, this.localeCombinationSettingHandlerProvider, this.highPriorityActivityHandlerProvider, this.ttaAvailabilityHandlerProvider);
    }
}
