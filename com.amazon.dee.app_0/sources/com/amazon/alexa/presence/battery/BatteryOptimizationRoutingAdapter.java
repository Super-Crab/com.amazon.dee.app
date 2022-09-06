package com.amazon.alexa.presence.battery;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.utils.DevicePlatformUtil;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class BatteryOptimizationRoutingAdapter implements RoutingAdapter {
    private static final String TAG = Presence.tag();
    private final Activity activity;
    private final Map<String, RoutingAdapter.RouteConfiguration> configurations = new HashMap();

    public BatteryOptimizationRoutingAdapter(Activity activity) {
        this.activity = activity;
        this.configurations.put("presence-battery-optimization", RoutingAdapter.RouteConfiguration.all());
    }

    @SuppressLint({"BatteryLife"})
    @TargetApi(23)
    private void requestDisableBatteryOptimization() {
        Intent intent = new Intent();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
        outline107.append(this.activity.getApplicationContext().getPackageName());
        intent.setData(Uri.parse(outline107.toString()));
        intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        this.activity.startActivity(intent);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 11;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        if (routeContext.getRoute().is("presence-battery-optimization")) {
            if (!DevicePlatformUtil.isAndroidMOrLater()) {
                Log.w(TAG, "Battery optimization route was invoked for Platform lower than Android M.");
                return;
            }
            try {
                requestDisableBatteryOptimization();
                runnable.run();
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "Failed to open android battery optimization setting disable pop-up", e);
            }
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
