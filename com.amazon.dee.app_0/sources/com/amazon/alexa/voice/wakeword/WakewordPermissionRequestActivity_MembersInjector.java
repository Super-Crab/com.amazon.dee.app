package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class WakewordPermissionRequestActivity_MembersInjector implements MembersInjector<WakewordPermissionRequestActivity> {
    private final Provider<AlexaLocaleAuthority> alexaLocaleAuthorityProvider;
    private final Provider<FeatureAvailability> featureAvailabilityProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<WakewordPreference> wakewordPreferenceProvider;

    public WakewordPermissionRequestActivity_MembersInjector(Provider<AlexaLocaleAuthority> provider, Provider<MetricsService> provider2, Provider<WakewordPreference> provider3, Provider<VoicePermissionsAuthority> provider4, Provider<FeatureAvailability> provider5) {
        this.alexaLocaleAuthorityProvider = provider;
        this.metricsServiceProvider = provider2;
        this.wakewordPreferenceProvider = provider3;
        this.voicePermissionsAuthorityProvider = provider4;
        this.featureAvailabilityProvider = provider5;
    }

    public static MembersInjector<WakewordPermissionRequestActivity> create(Provider<AlexaLocaleAuthority> provider, Provider<MetricsService> provider2, Provider<WakewordPreference> provider3, Provider<VoicePermissionsAuthority> provider4, Provider<FeatureAvailability> provider5) {
        return new WakewordPermissionRequestActivity_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAlexaLocaleAuthority(WakewordPermissionRequestActivity wakewordPermissionRequestActivity, AlexaLocaleAuthority alexaLocaleAuthority) {
        wakewordPermissionRequestActivity.alexaLocaleAuthority = alexaLocaleAuthority;
    }

    public static void injectFeatureAvailability(WakewordPermissionRequestActivity wakewordPermissionRequestActivity, FeatureAvailability featureAvailability) {
        wakewordPermissionRequestActivity.featureAvailability = featureAvailability;
    }

    public static void injectMetricsService(WakewordPermissionRequestActivity wakewordPermissionRequestActivity, MetricsService metricsService) {
        wakewordPermissionRequestActivity.metricsService = metricsService;
    }

    public static void injectVoicePermissionsAuthority(WakewordPermissionRequestActivity wakewordPermissionRequestActivity, VoicePermissionsAuthority voicePermissionsAuthority) {
        wakewordPermissionRequestActivity.voicePermissionsAuthority = voicePermissionsAuthority;
    }

    public static void injectWakewordPreference(WakewordPermissionRequestActivity wakewordPermissionRequestActivity, WakewordPreference wakewordPreference) {
        wakewordPermissionRequestActivity.wakewordPreference = wakewordPreference;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WakewordPermissionRequestActivity wakewordPermissionRequestActivity) {
        injectAlexaLocaleAuthority(wakewordPermissionRequestActivity, this.alexaLocaleAuthorityProvider.mo10268get());
        injectMetricsService(wakewordPermissionRequestActivity, this.metricsServiceProvider.mo10268get());
        injectWakewordPreference(wakewordPermissionRequestActivity, this.wakewordPreferenceProvider.mo10268get());
        injectVoicePermissionsAuthority(wakewordPermissionRequestActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        injectFeatureAvailability(wakewordPermissionRequestActivity, this.featureAvailabilityProvider.mo10268get());
    }
}
