package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.enablement.VoiceIdentityAdapter;
import com.amazon.alexa.voice.events.VoxUiEventProcessingService;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.ftue.FtueManagerProvider;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.provisioning.FeatureProvisioner;
import com.amazon.alexa.voice.tta.TypeToAlexaFeatureEnabler;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceActivity_MembersInjector implements MembersInjector<VoiceActivity> {
    private final Provider<AlexaLocaleAuthority> alexaLocaleAuthorityProvider;
    private final Provider<FeatureAvailability> featureAvailabilityProvider;
    private final Provider<FtueManagerProvider> ftueManagerProvider;
    private final Provider<LatencyReportingDelegate> latencyReportingDelegateProvider;
    private final Provider<MetricsBridge> metricsBridgeProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<TypeToAlexaFeatureEnabler> typeToAlexaFeatureEnablerProvider;
    private final Provider<VoxUiEventProcessingService> uiEventProcessingServiceProvider;
    private final Provider<VoiceIdentityAdapter> voiceIdentityAdapterProvider;
    private final Provider<VoicePermissionsAuthority> voicePermissionsAuthorityProvider;
    private final Provider<FeatureProvisioner> voiceProvisionerProvider;
    private final Provider<VoiceService> voiceServiceProvider;
    private final Provider<VoxMetricEventProcessingService> voxMetricEventProcessingServiceProvider;

    public VoiceActivity_MembersInjector(Provider<VoiceIdentityAdapter> provider, Provider<VoiceService> provider2, Provider<FeatureProvisioner> provider3, Provider<AlexaLocaleAuthority> provider4, Provider<MetricsService> provider5, Provider<LatencyReportingDelegate> provider6, Provider<FtueManagerProvider> provider7, Provider<VoxMetricEventProcessingService> provider8, Provider<VoxUiEventProcessingService> provider9, Provider<MetricsBridge> provider10, Provider<VoicePermissionsAuthority> provider11, Provider<FeatureAvailability> provider12, Provider<TypeToAlexaFeatureEnabler> provider13) {
        this.voiceIdentityAdapterProvider = provider;
        this.voiceServiceProvider = provider2;
        this.voiceProvisionerProvider = provider3;
        this.alexaLocaleAuthorityProvider = provider4;
        this.metricsServiceProvider = provider5;
        this.latencyReportingDelegateProvider = provider6;
        this.ftueManagerProvider = provider7;
        this.voxMetricEventProcessingServiceProvider = provider8;
        this.uiEventProcessingServiceProvider = provider9;
        this.metricsBridgeProvider = provider10;
        this.voicePermissionsAuthorityProvider = provider11;
        this.featureAvailabilityProvider = provider12;
        this.typeToAlexaFeatureEnablerProvider = provider13;
    }

    public static MembersInjector<VoiceActivity> create(Provider<VoiceIdentityAdapter> provider, Provider<VoiceService> provider2, Provider<FeatureProvisioner> provider3, Provider<AlexaLocaleAuthority> provider4, Provider<MetricsService> provider5, Provider<LatencyReportingDelegate> provider6, Provider<FtueManagerProvider> provider7, Provider<VoxMetricEventProcessingService> provider8, Provider<VoxUiEventProcessingService> provider9, Provider<MetricsBridge> provider10, Provider<VoicePermissionsAuthority> provider11, Provider<FeatureAvailability> provider12, Provider<TypeToAlexaFeatureEnabler> provider13) {
        return new VoiceActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static void injectAlexaLocaleAuthority(VoiceActivity voiceActivity, AlexaLocaleAuthority alexaLocaleAuthority) {
        voiceActivity.alexaLocaleAuthority = alexaLocaleAuthority;
    }

    public static void injectFeatureAvailability(VoiceActivity voiceActivity, FeatureAvailability featureAvailability) {
        voiceActivity.featureAvailability = featureAvailability;
    }

    public static void injectFtueManagerProvider(VoiceActivity voiceActivity, FtueManagerProvider ftueManagerProvider) {
        voiceActivity.ftueManagerProvider = ftueManagerProvider;
    }

    public static void injectLatencyReportingDelegate(VoiceActivity voiceActivity, LatencyReportingDelegate latencyReportingDelegate) {
        voiceActivity.latencyReportingDelegate = latencyReportingDelegate;
    }

    public static void injectMetricsBridge(VoiceActivity voiceActivity, MetricsBridge metricsBridge) {
        voiceActivity.metricsBridge = metricsBridge;
    }

    public static void injectMetricsService(VoiceActivity voiceActivity, MetricsService metricsService) {
        voiceActivity.metricsService = metricsService;
    }

    public static void injectTypeToAlexaFeatureEnabler(VoiceActivity voiceActivity, TypeToAlexaFeatureEnabler typeToAlexaFeatureEnabler) {
        voiceActivity.typeToAlexaFeatureEnabler = typeToAlexaFeatureEnabler;
    }

    public static void injectUiEventProcessingService(VoiceActivity voiceActivity, VoxUiEventProcessingService voxUiEventProcessingService) {
        voiceActivity.uiEventProcessingService = voxUiEventProcessingService;
    }

    public static void injectVoiceIdentityAdapter(VoiceActivity voiceActivity, VoiceIdentityAdapter voiceIdentityAdapter) {
        voiceActivity.voiceIdentityAdapter = voiceIdentityAdapter;
    }

    public static void injectVoicePermissionsAuthority(VoiceActivity voiceActivity, VoicePermissionsAuthority voicePermissionsAuthority) {
        voiceActivity.voicePermissionsAuthority = voicePermissionsAuthority;
    }

    public static void injectVoiceProvisioner(VoiceActivity voiceActivity, FeatureProvisioner featureProvisioner) {
        voiceActivity.voiceProvisioner = featureProvisioner;
    }

    public static void injectVoiceService(VoiceActivity voiceActivity, VoiceService voiceService) {
        voiceActivity.voiceService = voiceService;
    }

    public static void injectVoxMetricEventProcessingService(VoiceActivity voiceActivity, VoxMetricEventProcessingService voxMetricEventProcessingService) {
        voiceActivity.voxMetricEventProcessingService = voxMetricEventProcessingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceActivity voiceActivity) {
        injectVoiceIdentityAdapter(voiceActivity, this.voiceIdentityAdapterProvider.mo10268get());
        injectVoiceService(voiceActivity, this.voiceServiceProvider.mo10268get());
        injectVoiceProvisioner(voiceActivity, this.voiceProvisionerProvider.mo10268get());
        injectAlexaLocaleAuthority(voiceActivity, this.alexaLocaleAuthorityProvider.mo10268get());
        injectMetricsService(voiceActivity, this.metricsServiceProvider.mo10268get());
        injectLatencyReportingDelegate(voiceActivity, this.latencyReportingDelegateProvider.mo10268get());
        injectFtueManagerProvider(voiceActivity, this.ftueManagerProvider.mo10268get());
        injectVoxMetricEventProcessingService(voiceActivity, this.voxMetricEventProcessingServiceProvider.mo10268get());
        injectUiEventProcessingService(voiceActivity, this.uiEventProcessingServiceProvider.mo10268get());
        injectMetricsBridge(voiceActivity, this.metricsBridgeProvider.mo10268get());
        injectVoicePermissionsAuthority(voiceActivity, this.voicePermissionsAuthorityProvider.mo10268get());
        injectFeatureAvailability(voiceActivity, this.featureAvailabilityProvider.mo10268get());
        injectTypeToAlexaFeatureEnabler(voiceActivity, this.typeToAlexaFeatureEnablerProvider.mo10268get());
    }
}
