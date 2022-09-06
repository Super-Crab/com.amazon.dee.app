package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakeWordModule_ProvideWakeWordAuthorityFactory implements Factory<WakeWordAuthority> {
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<AlexaServicesConnection> connectionProvider;
    private final Provider<VoiceOverUtility> voiceOverUtilityProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<WakeWordEventHandler> wakeWordEventHandlerProvider;
    private final Provider<WakeWordFeatureGate> wakeWordFeatureGateProvider;
    private final Provider<WakewordPreference> wakewordPreferenceProvider;

    public WakeWordModule_ProvideWakeWordAuthorityFactory(Provider<AlexaServicesConnection> provider, Provider<WakewordPreference> provider2, Provider<WakeWordFeatureGate> provider3, Provider<ApplicationLifecycleService> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<WakeWordEventHandler> provider6, Provider<VoiceOverUtility> provider7) {
        this.connectionProvider = provider;
        this.wakewordPreferenceProvider = provider2;
        this.wakeWordFeatureGateProvider = provider3;
        this.applicationLifecycleServiceProvider = provider4;
        this.voicePermissionsAuthorityProvider = provider5;
        this.wakeWordEventHandlerProvider = provider6;
        this.voiceOverUtilityProvider = provider7;
    }

    public static WakeWordModule_ProvideWakeWordAuthorityFactory create(Provider<AlexaServicesConnection> provider, Provider<WakewordPreference> provider2, Provider<WakeWordFeatureGate> provider3, Provider<ApplicationLifecycleService> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<WakeWordEventHandler> provider6, Provider<VoiceOverUtility> provider7) {
        return new WakeWordModule_ProvideWakeWordAuthorityFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static WakeWordAuthority provideInstance(Provider<AlexaServicesConnection> provider, Provider<WakewordPreference> provider2, Provider<WakeWordFeatureGate> provider3, Provider<ApplicationLifecycleService> provider4, Provider<VoicePermissionsAuthority> provider5, Provider<WakeWordEventHandler> provider6, Provider<VoiceOverUtility> provider7) {
        return proxyProvideWakeWordAuthority(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static WakeWordAuthority proxyProvideWakeWordAuthority(AlexaServicesConnection alexaServicesConnection, WakewordPreference wakewordPreference, Object obj, ApplicationLifecycleService applicationLifecycleService, VoicePermissionsAuthority voicePermissionsAuthority, WakeWordEventHandler wakeWordEventHandler, VoiceOverUtility voiceOverUtility) {
        return (WakeWordAuthority) Preconditions.checkNotNull(WakeWordModule.provideWakeWordAuthority(alexaServicesConnection, wakewordPreference, (WakeWordFeatureGate) obj, applicationLifecycleService, voicePermissionsAuthority, wakeWordEventHandler, voiceOverUtility), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WakeWordAuthority mo10268get() {
        return provideInstance(this.connectionProvider, this.wakewordPreferenceProvider, this.wakeWordFeatureGateProvider, this.applicationLifecycleServiceProvider, this.voicePermissionsAuthorityProvider, this.wakeWordEventHandlerProvider, this.voiceOverUtilityProvider);
    }
}
