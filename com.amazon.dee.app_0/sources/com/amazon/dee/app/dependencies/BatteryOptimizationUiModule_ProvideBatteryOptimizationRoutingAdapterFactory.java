package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.presence.battery.BatteryOptimizationRoutingAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class BatteryOptimizationUiModule_ProvideBatteryOptimizationRoutingAdapterFactory implements Factory<BatteryOptimizationRoutingAdapter> {
    private final Provider<Activity> activityProvider;
    private final BatteryOptimizationUiModule module;

    public BatteryOptimizationUiModule_ProvideBatteryOptimizationRoutingAdapterFactory(BatteryOptimizationUiModule batteryOptimizationUiModule, Provider<Activity> provider) {
        this.module = batteryOptimizationUiModule;
        this.activityProvider = provider;
    }

    public static BatteryOptimizationUiModule_ProvideBatteryOptimizationRoutingAdapterFactory create(BatteryOptimizationUiModule batteryOptimizationUiModule, Provider<Activity> provider) {
        return new BatteryOptimizationUiModule_ProvideBatteryOptimizationRoutingAdapterFactory(batteryOptimizationUiModule, provider);
    }

    public static BatteryOptimizationRoutingAdapter provideInstance(BatteryOptimizationUiModule batteryOptimizationUiModule, Provider<Activity> provider) {
        return proxyProvideBatteryOptimizationRoutingAdapter(batteryOptimizationUiModule, provider.mo10268get());
    }

    public static BatteryOptimizationRoutingAdapter proxyProvideBatteryOptimizationRoutingAdapter(BatteryOptimizationUiModule batteryOptimizationUiModule, Activity activity) {
        return (BatteryOptimizationRoutingAdapter) Preconditions.checkNotNull(batteryOptimizationUiModule.provideBatteryOptimizationRoutingAdapter(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BatteryOptimizationRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
