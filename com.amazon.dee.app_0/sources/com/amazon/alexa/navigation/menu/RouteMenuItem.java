package com.amazon.alexa.navigation.menu;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.routing.api.RoutingService;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public class RouteMenuItem extends MenuItem {
    private static final String TAG = "RouteMenuItem";
    @VisibleForTesting
    Map<String, Object> customEntries;
    private MetricsComponents metricsComponents;
    private Provider<Mobilytics> mobilyticsProvider;
    private final String route;
    private Provider<RoutingService> routingService;

    public RouteMenuItem(int i, int i2, @NonNull String str, boolean z, @NonNull MetricsComponents metricsComponents, @NonNull TestId testId, Provider<Mobilytics> provider, Provider<RoutingService> provider2) {
        super(i, i2, z, testId);
        this.route = str;
        this.metricsComponents = metricsComponents;
        this.mobilyticsProvider = provider;
        this.routingService = provider2;
        this.customEntries = new HashMap();
        this.customEntries.put("subComponent", metricsComponents.subComponent);
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public int getMenuItemLayout() {
        return R.layout.navigation_menu_item;
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public void onClick(View view) {
        try {
            this.routingService.mo10268get().navigate(this.route, false);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
            this.mobilyticsProvider.mo10268get().recordOperationalEvent(this.mobilyticsProvider.mo10268get().createOperationalEvent("Error.MORE_ERROR_ROUTING", "error", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
        }
        this.mobilyticsProvider.mo10268get().recordUserInteractionEvent(this.mobilyticsProvider.mo10268get().createUserInteractionEvent(this.metricsComponents.metricName, "click", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }
}
