package com.amazon.alexa.voice.ftue;

import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceFtueActivity_MembersInjector implements MembersInjector<VoiceFtueActivity> {
    private final Provider<AlexaLocaleAuthority> alexaLocaleAuthorityProvider;
    private final Provider<FeatureAvailability> featureAvailabilityProvider;
    private final Provider<FtuePreference> ftuePreferenceProvider;
    private final Provider<MetricsBridge> metricsBridgeProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;
    private final Provider<WakewordPreference> wakewordPreferenceProvider;

    public VoiceFtueActivity_MembersInjector(Provider<AlexaLocaleAuthority> provider, Provider<FtuePreference> provider2, Provider<MetricsService> provider3, Provider<VoxMetricEventProcessingService> provider4, Provider<MetricsBridge> provider5, Provider<VoicePermissionsAuthority> provider6, Provider<WakewordPreference> provider7, Provider<FeatureAvailability> provider8, Provider<RoutingService> provider9) {
        this.alexaLocaleAuthorityProvider = provider;
        this.ftuePreferenceProvider = provider2;
        this.metricsServiceProvider = provider3;
        this.voxMetricEventProcessingServiceProvider = provider4;
        this.metricsBridgeProvider = provider5;
        this.voicePermissionsAuthorityProvider = provider6;
        this.wakewordPreferenceProvider = provider7;
        this.featureAvailabilityProvider = provider8;
        this.routingServiceProvider = provider9;
    }

    public static MembersInjector<VoiceFtueActivity> create(Provider<AlexaLocaleAuthority> provider, Provider<FtuePreference> provider2, Provider<MetricsService> provider3, Provider<VoxMetricEventProcessingService> provider4, Provider<MetricsBridge> provider5, Provider<VoicePermissionsAuthority> provider6, Provider<WakewordPreference> provider7, Provider<FeatureAvailability> provider8, Provider<RoutingService> provider9) {
        return new VoiceFtueActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectAlexaLocaleAuthority(VoiceFtueActivity voiceFtueActivity, AlexaLocaleAuthority alexaLocaleAuthority) {
        voiceFtueActivity.alexaLocaleAuthority = alexaLocaleAuthority;
    }

    public static void injectFeatureAvailability(VoiceFtueActivity voiceFtueActivity, FeatureAvailability featureAvailability) {
        voiceFtueActivity.featureAvailability = featureAvailability;
    }

    public static void injectFtuePreference(VoiceFtueActivity voiceFtueActivity, FtuePreference ftuePreference) {
        voiceFtueActivity.ftuePreference = ftuePreference;
    }

    public static void injectMetricsBridge(VoiceFtueActivity voiceFtueActivity, MetricsBridge metricsBridge) {
        voiceFtueActivity.metricsBridge = metricsBridge;
    }

    public static void injectMetricsService(VoiceFtueActivity voiceFtueActivity, MetricsService metricsService) {
        voiceFtueActivity.metricsService = metricsService;
    }

    public static void injectRoutingService(VoiceFtueActivity voiceFtueActivity, RoutingService routingService) {
        voiceFtueActivity.routingService = routingService;
    }

    public static void injectVoicePermissionsAuthority(VoiceFtueActivity voiceFtueActivity, VoicePermissionsAuthority voicePermissionsAuthority) {
        voiceFtueActivity.voicePermissionsAuthority = voicePermissionsAuthority;
    }

    public static void injectVoxMetricEventProcessingService(VoiceFtueActivity voiceFtueActivity, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        voiceFtueActivity.voxMetricEventProcessingService = voxMetricEventProcessingService;
    }

    public static void injectWakewordPreference(VoiceFtueActivity voiceFtueActivity, WakewordPreference wakewordPreference) {
        voiceFtueActivity.wakewordPreference = wakewordPreference;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceFtueActivity voiceFtueActivity) {
        injectAlexaLocaleAuthority(voiceFtueActivity, this.alexaLocaleAuthorityProvider.mo10268get());
        injectFtuePreference(voiceFtueActivity, this.ftuePreferenceProvider.mo10268get());
        injectMetricsService(voiceFtueActivity, this.metricsServiceProvider.mo10268get());
        injectVoxMetricEventProcessingService(voiceFtueActivity, this.voxMetricEventProcessingServiceProvider.mo10268get());
        injectMetricsBridge(voiceFtueActivity, this.metricsBridgeProvider.mo10268get());
        injectVoicePermissionsAuthority(voiceFtueActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        injectWakewordPreference(voiceFtueActivity, this.wakewordPreferenceProvider.mo10268get());
        injectFeatureAvailability(voiceFtueActivity, this.featureAvailabilityProvider.mo10268get());
        injectRoutingService(voiceFtueActivity, this.routingServiceProvider.mo10268get());
    }
}
