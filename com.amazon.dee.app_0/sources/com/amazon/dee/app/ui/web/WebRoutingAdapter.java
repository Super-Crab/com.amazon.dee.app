package com.amazon.dee.app.ui.web;

import android.app.Activity;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.ui.main.MainActivity;
/* loaded from: classes12.dex */
public class WebRoutingAdapter implements RoutingAdapter {
    MainActivity activity;
    SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();

    public WebRoutingAdapter(Activity activity) {
        this.activity = (MainActivity) activity;
        this.configurations.put("web", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.NOW_PLAYING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.PLAYER_CURRENT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.PLAYER_QUEUE, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.PLAYER_HISTORY, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.HELP, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("thingstotry", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.LISTS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.LIST_SHOPPING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.LIST_TODO, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.LISTS_NL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.MUSIC_BOOKS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.TIMERS_ALARMS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ALARMS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("timers", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.REMINDERS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("settings", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_SETTINGS_SHOPPING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.STARTUP, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.FIRST_RUN_TUTORIAL, RoutingAdapter.RouteConfiguration.all());
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
        return 0;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        AlexaWebView webview = this.activity.getWebview();
        if (routeContext.getRoute().is("web")) {
            String string = routeContext.getString(RouteParameter.ROUTE);
            if (!TextUtils.isEmpty(string)) {
                webview.getWebNavigator().navigate(string);
            }
        } else {
            webview.getWebNavigator().navigate(routeContext.toUri());
        }
        runnable.run();
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

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reset() {
        ((EnvironmentWebNavigator) this.activity.getWebview().getWebNavigator()).resetUrl();
    }
}
