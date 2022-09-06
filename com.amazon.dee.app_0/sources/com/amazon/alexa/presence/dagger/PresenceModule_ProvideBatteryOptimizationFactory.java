package com.amazon.alexa.presence.dagger;

import android.os.PowerManager;
import com.amazon.alexa.presence.battery.BatteryOptimization;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideBatteryOptimizationFactory implements Factory<BatteryOptimization> {
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final PresenceModule module;
    private final Provider<PowerManager> powerManagerProvider;

    public PresenceModule_ProvideBatteryOptimizationFactory(PresenceModule presenceModule, Provider<PowerManager> provider, Provider<MetricsServiceV2> provider2) {
        this.module = presenceModule;
        this.powerManagerProvider = provider;
        this.metricsServiceV2Provider = provider2;
    }

    public static PresenceModule_ProvideBatteryOptimizationFactory create(PresenceModule presenceModule, Provider<PowerManager> provider, Provider<MetricsServiceV2> provider2) {
        return new PresenceModule_ProvideBatteryOptimizationFactory(presenceModule, provider, provider2);
    }

    public static BatteryOptimization provideInstance(PresenceModule presenceModule, Provider<PowerManager> provider, Provider<MetricsServiceV2> provider2) {
        return proxyProvideBatteryOptimization(presenceModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static BatteryOptimization proxyProvideBatteryOptimization(PresenceModule presenceModule, PowerManager powerManager, MetricsServiceV2 metricsServiceV2) {
        return (BatteryOptimization) Preconditions.checkNotNull(presenceModule.provideBatteryOptimization(powerManager, metricsServiceV2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryOptimization mo10268get() {
        return provideInstance(this.module, this.powerManagerProvider, this.metricsServiceV2Provider);
    }
}
