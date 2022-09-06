package com.amazon.alexa.voice.settings;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SettingsModule_ProvideHandsfreeSettingsHandlerFactory implements Factory<HandsfreeSettingsHandler> {
    private final Provider<AbiCompatibilityInterface> compatibilityInterfaceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<FtuePreference> ftuePreferenceProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;
    private final Provider<WakewordPreference> wakewordPreferenceProvider;

    public SettingsModule_ProvideHandsfreeSettingsHandlerFactory(Provider<EventBus> provider, Provider<Context> provider2, Provider<WakewordPreference> provider3, Provider<FtuePreference> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<VoxMetricEventProcessingService> provider6, Provider<AbiCompatibilityInterface> provider7) {
        this.eventBusProvider = provider;
        this.contextProvider = provider2;
        this.wakewordPreferenceProvider = provider3;
        this.ftuePreferenceProvider = provider4;
        this.voicePermissionsAuthorityProvider = provider5;
        this.voxMetricEventProcessingServiceProvider = provider6;
        this.compatibilityInterfaceProvider = provider7;
    }

    public static SettingsModule_ProvideHandsfreeSettingsHandlerFactory create(Provider<EventBus> provider, Provider<Context> provider2, Provider<WakewordPreference> provider3, Provider<FtuePreference> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<VoxMetricEventProcessingService> provider6, Provider<AbiCompatibilityInterface> provider7) {
        return new SettingsModule_ProvideHandsfreeSettingsHandlerFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static HandsfreeSettingsHandler provideInstance(Provider<EventBus> provider, Provider<Context> provider2, Provider<WakewordPreference> provider3, Provider<FtuePreference> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<VoxMetricEventProcessingService> provider6, Provider<AbiCompatibilityInterface> provider7) {
        return proxyProvideHandsfreeSettingsHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static HandsfreeSettingsHandler proxyProvideHandsfreeSettingsHandler(EventBus eventBus, Context context, WakewordPreference wakewordPreference, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, VoxMetricEventProcessingService voxMetricEventProcessingService, AbiCompatibilityInterface abiCompatibilityInterface) {
        return (HandsfreeSettingsHandler) Preconditions.checkNotNull(SettingsModule.provideHandsfreeSettingsHandler(eventBus, context, wakewordPreference, ftuePreference, voicePermissionsAuthority, voxMetricEventProcessingService, abiCompatibilityInterface), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsfreeSettingsHandler mo10268get() {
        return provideInstance(this.eventBusProvider, this.contextProvider, this.wakewordPreferenceProvider, this.ftuePreferenceProvider, this.voicePermissionsAuthorityProvider, this.voxMetricEventProcessingServiceProvider, this.compatibilityInterfaceProvider);
    }
}
