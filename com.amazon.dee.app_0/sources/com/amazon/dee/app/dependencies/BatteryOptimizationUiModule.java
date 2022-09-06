package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.presence.battery.BatteryOptimizationRoutingAdapter;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class BatteryOptimizationUiModule {
    @Provides
    @MainScope
    public BatteryOptimizationRoutingAdapter provideBatteryOptimizationRoutingAdapter(Activity activity) {
        return new BatteryOptimizationRoutingAdapter(activity);
    }
}
