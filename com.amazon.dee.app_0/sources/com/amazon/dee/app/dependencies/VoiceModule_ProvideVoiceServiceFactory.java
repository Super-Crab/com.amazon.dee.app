package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceModule_ProvideVoiceServiceFactory implements Factory<VoiceService> {
    private final Provider<AccountUpgradeService> accountUpgradeServiceProvider;
    private final Provider<DefaultApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<LatencyReportingDelegate> latencyReportingDelegateProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final VoiceModule module;
    private final Provider<NetworkService> networkServiceProvider;
    private final Provider<PersistentStorage.Factory> persistentStorageFactoryProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public VoiceModule_ProvideVoiceServiceFactory(VoiceModule voiceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<IdentityService> provider3, Provider<AccountUpgradeService> provider4, Provider<NetworkService> provider5, Provider<PersistentStorage.Factory> provider6, Provider<LatencyReportingDelegate> provider7, Provider<MetricsService> provider8, Provider<RoutingService> provider9, Provider<DefaultApplicationLifecycleService> provider10) {
        this.module = voiceModule;
        this.contextProvider = provider;
        this.deviceInformationProvider = provider2;
        this.identityServiceProvider = provider3;
        this.accountUpgradeServiceProvider = provider4;
        this.networkServiceProvider = provider5;
        this.persistentStorageFactoryProvider = provider6;
        this.latencyReportingDelegateProvider = provider7;
        this.metricsServiceProvider = provider8;
        this.routingServiceProvider = provider9;
        this.applicationLifecycleServiceProvider = provider10;
    }

    public static VoiceModule_ProvideVoiceServiceFactory create(VoiceModule voiceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<IdentityService> provider3, Provider<AccountUpgradeService> provider4, Provider<NetworkService> provider5, Provider<PersistentStorage.Factory> provider6, Provider<LatencyReportingDelegate> provider7, Provider<MetricsService> provider8, Provider<RoutingService> provider9, Provider<DefaultApplicationLifecycleService> provider10) {
        return new VoiceModule_ProvideVoiceServiceFactory(voiceModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static VoiceService provideInstance(VoiceModule voiceModule, Provider<Context> provider, Provider<DeviceInformation> provider2, Provider<IdentityService> provider3, Provider<AccountUpgradeService> provider4, Provider<NetworkService> provider5, Provider<PersistentStorage.Factory> provider6, Provider<LatencyReportingDelegate> provider7, Provider<MetricsService> provider8, Provider<RoutingService> provider9, Provider<DefaultApplicationLifecycleService> provider10) {
        return proxyProvideVoiceService(voiceModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6), DoubleCheck.lazy(provider7), DoubleCheck.lazy(provider8), DoubleCheck.lazy(provider9), DoubleCheck.lazy(provider10));
    }

    public static VoiceService proxyProvideVoiceService(VoiceModule voiceModule, Lazy<Context> lazy, Lazy<DeviceInformation> lazy2, Lazy<IdentityService> lazy3, Lazy<AccountUpgradeService> lazy4, Lazy<NetworkService> lazy5, Lazy<PersistentStorage.Factory> lazy6, Lazy<LatencyReportingDelegate> lazy7, Lazy<MetricsService> lazy8, Lazy<RoutingService> lazy9, Lazy<DefaultApplicationLifecycleService> lazy10) {
        return (VoiceService) Preconditions.checkNotNull(voiceModule.provideVoiceService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceService mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.deviceInformationProvider, this.identityServiceProvider, this.accountUpgradeServiceProvider, this.networkServiceProvider, this.persistentStorageFactoryProvider, this.latencyReportingDelegateProvider, this.metricsServiceProvider, this.routingServiceProvider, this.applicationLifecycleServiceProvider);
    }
}
