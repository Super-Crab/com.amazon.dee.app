package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerMonitor;
import com.amazon.whisperjoin.deviceprovisioningservice.service.power.DevicePowerStatusProvider;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ProvisioningModule_ProvidesDevicePowerMonitorFactory implements Factory<DevicePowerMonitor> {
    private final Provider<Context> contextProvider;
    private final Provider<DevicePowerStatusProvider> devicePowerStatusProvider;
    private final Provider<MetricsRecorderProvider> metricsRecorderProvider;
    private final ProvisioningModule module;
    private final Provider<ProvisionerClientData> provisionerClientDataProvider;

    public ProvisioningModule_ProvidesDevicePowerMonitorFactory(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<DevicePowerStatusProvider> provider2, Provider<MetricsRecorderProvider> provider3, Provider<ProvisionerClientData> provider4) {
        this.module = provisioningModule;
        this.contextProvider = provider;
        this.devicePowerStatusProvider = provider2;
        this.metricsRecorderProvider = provider3;
        this.provisionerClientDataProvider = provider4;
    }

    public static ProvisioningModule_ProvidesDevicePowerMonitorFactory create(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<DevicePowerStatusProvider> provider2, Provider<MetricsRecorderProvider> provider3, Provider<ProvisionerClientData> provider4) {
        return new ProvisioningModule_ProvidesDevicePowerMonitorFactory(provisioningModule, provider, provider2, provider3, provider4);
    }

    public static DevicePowerMonitor provideInstance(ProvisioningModule provisioningModule, Provider<Context> provider, Provider<DevicePowerStatusProvider> provider2, Provider<MetricsRecorderProvider> provider3, Provider<ProvisionerClientData> provider4) {
        return proxyProvidesDevicePowerMonitor(provisioningModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static DevicePowerMonitor proxyProvidesDevicePowerMonitor(ProvisioningModule provisioningModule, Context context, DevicePowerStatusProvider devicePowerStatusProvider, MetricsRecorderProvider metricsRecorderProvider, ProvisionerClientData provisionerClientData) {
        return (DevicePowerMonitor) Preconditions.checkNotNull(provisioningModule.providesDevicePowerMonitor(context, devicePowerStatusProvider, metricsRecorderProvider, provisionerClientData), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DevicePowerMonitor mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.devicePowerStatusProvider, this.metricsRecorderProvider, this.provisionerClientDataProvider);
    }
}
