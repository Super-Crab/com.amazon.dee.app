package com.amazon.alexa.handsfree.metrics;

import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.metrics.FirstStartupMetricJobService;
import com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsInitializer;
import com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider;
import com.amazon.alexa.handsfree.metrics.utils.IdentityServiceManager;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.handsfree.storage.initialization.AppInitializationStatusStore;
import com.amazon.alexa.handsfree.storage.initialization.DspApkVersionCodeStore;
import com.amazon.alexa.handsfree.storage.initialization.SdkVersionCodeStore;
import com.amazon.alexa.handsfree.storage.metrics.MetricsEnabledStatusStore;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsModule_Factory implements Factory<MetricsModule> {
    private final Provider<AppInitializationStatusStore> appInitializationStatusStoreProvider;
    private final Provider<ApplicationInformationProvider> applicationInformationProviderLazyProvider;
    private final Provider<AttributionTagProvider> attributionTagProvider;
    private final Provider<DeviceTypeInformationProvider> deviceTypeInformationProvider;
    private final Provider<DspApkVersionCodeStore> dspApkVersionCodeStoreProvider;
    private final Provider<FirstStartupMetricJobService.Helper> firstStartupMetricJobServiceHelperProvider;
    private final Provider<IdentityServiceManager> identityServiceManagerProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<MetricsEnabledStatusStore> metricsEnabledStatusStoreProvider;
    private final Provider<SdkVersionCodeStore> sdkVersionCodeStoreProvider;
    private final Provider<VoiceAppMetricsInitializer> voiceAppMetricsInitializerProvider;

    public MetricsModule_Factory(Provider<AppInitializationStatusStore> provider, Provider<MetricsEnabledStatusStore> provider2, Provider<DspApkVersionCodeStore> provider3, Provider<SdkVersionCodeStore> provider4, Provider<MetricsBuilderProvider> provider5, Provider<IdentityServiceManager> provider6, Provider<AttributionTagProvider> provider7, Provider<FirstStartupMetricJobService.Helper> provider8, Provider<VoiceAppMetricsInitializer> provider9, Provider<DeviceTypeInformationProvider> provider10, Provider<ApplicationInformationProvider> provider11) {
        this.appInitializationStatusStoreProvider = provider;
        this.metricsEnabledStatusStoreProvider = provider2;
        this.dspApkVersionCodeStoreProvider = provider3;
        this.sdkVersionCodeStoreProvider = provider4;
        this.metricsBuilderProvider = provider5;
        this.identityServiceManagerProvider = provider6;
        this.attributionTagProvider = provider7;
        this.firstStartupMetricJobServiceHelperProvider = provider8;
        this.voiceAppMetricsInitializerProvider = provider9;
        this.deviceTypeInformationProvider = provider10;
        this.applicationInformationProviderLazyProvider = provider11;
    }

    public static MetricsModule_Factory create(Provider<AppInitializationStatusStore> provider, Provider<MetricsEnabledStatusStore> provider2, Provider<DspApkVersionCodeStore> provider3, Provider<SdkVersionCodeStore> provider4, Provider<MetricsBuilderProvider> provider5, Provider<IdentityServiceManager> provider6, Provider<AttributionTagProvider> provider7, Provider<FirstStartupMetricJobService.Helper> provider8, Provider<VoiceAppMetricsInitializer> provider9, Provider<DeviceTypeInformationProvider> provider10, Provider<ApplicationInformationProvider> provider11) {
        return new MetricsModule_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static MetricsModule newMetricsModule(AppInitializationStatusStore appInitializationStatusStore, MetricsEnabledStatusStore metricsEnabledStatusStore, DspApkVersionCodeStore dspApkVersionCodeStore, SdkVersionCodeStore sdkVersionCodeStore, MetricsBuilderProvider metricsBuilderProvider, IdentityServiceManager identityServiceManager, AttributionTagProvider attributionTagProvider, Object obj, VoiceAppMetricsInitializer voiceAppMetricsInitializer, DeviceTypeInformationProvider deviceTypeInformationProvider, Lazy<ApplicationInformationProvider> lazy) {
        return new MetricsModule(appInitializationStatusStore, metricsEnabledStatusStore, dspApkVersionCodeStore, sdkVersionCodeStore, metricsBuilderProvider, identityServiceManager, attributionTagProvider, (FirstStartupMetricJobService.Helper) obj, voiceAppMetricsInitializer, deviceTypeInformationProvider, lazy);
    }

    public static MetricsModule provideInstance(Provider<AppInitializationStatusStore> provider, Provider<MetricsEnabledStatusStore> provider2, Provider<DspApkVersionCodeStore> provider3, Provider<SdkVersionCodeStore> provider4, Provider<MetricsBuilderProvider> provider5, Provider<IdentityServiceManager> provider6, Provider<AttributionTagProvider> provider7, Provider<FirstStartupMetricJobService.Helper> provider8, Provider<VoiceAppMetricsInitializer> provider9, Provider<DeviceTypeInformationProvider> provider10, Provider<ApplicationInformationProvider> provider11) {
        return new MetricsModule(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), DoubleCheck.lazy(provider11));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsModule mo10268get() {
        return provideInstance(this.appInitializationStatusStoreProvider, this.metricsEnabledStatusStoreProvider, this.dspApkVersionCodeStoreProvider, this.sdkVersionCodeStoreProvider, this.metricsBuilderProvider, this.identityServiceManagerProvider, this.attributionTagProvider, this.firstStartupMetricJobServiceHelperProvider, this.voiceAppMetricsInitializerProvider, this.deviceTypeInformationProvider, this.applicationInformationProviderLazyProvider);
    }
}
