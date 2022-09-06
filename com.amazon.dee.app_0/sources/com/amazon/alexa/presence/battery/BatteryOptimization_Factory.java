package com.amazon.alexa.presence.battery;

import android.content.Context;
import android.os.PowerManager;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class BatteryOptimization_Factory implements Factory<BatteryOptimization> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;
    private final Provider<PowerManager> powerManagerProvider;

    public BatteryOptimization_Factory(Provider<Context> provider, Provider<PowerManager> provider2, Provider<MetricsServiceV2> provider3) {
        this.contextProvider = provider;
        this.powerManagerProvider = provider2;
        this.metricsServiceV2Provider = provider3;
    }

    public static BatteryOptimization_Factory create(Provider<Context> provider, Provider<PowerManager> provider2, Provider<MetricsServiceV2> provider3) {
        return new BatteryOptimization_Factory(provider, provider2, provider3);
    }

    public static BatteryOptimization newBatteryOptimization(Context context, PowerManager powerManager, MetricsServiceV2 metricsServiceV2) {
        return new BatteryOptimization(context, powerManager, metricsServiceV2);
    }

    public static BatteryOptimization provideInstance(Provider<Context> provider, Provider<PowerManager> provider2, Provider<MetricsServiceV2> provider3) {
        return new BatteryOptimization(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryOptimization mo10268get() {
        return provideInstance(this.contextProvider, this.powerManagerProvider, this.metricsServiceV2Provider);
    }
}
