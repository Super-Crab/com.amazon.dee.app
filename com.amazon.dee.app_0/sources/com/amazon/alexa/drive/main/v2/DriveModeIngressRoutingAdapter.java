package com.amazon.alexa.drive.main.v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public class DriveModeIngressRoutingAdapter implements RoutingAdapter {
    private static final String TAG = "DriveModeIngressRoutingAdapter";
    private SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    private Runnable ingressRunnable;

    public DriveModeIngressRoutingAdapter(@NonNull Runnable runnable) {
        this.ingressRunnable = runnable;
        this.configurations.put("alexa-oobe/drive-mode/main", RoutingAdapter.RouteConfiguration.all());
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
        return 13;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        String str = "Navigating: " + routeContext;
        if (routeContext.getRoute().is("alexa-oobe/drive-mode/main")) {
            Preconditions.checkNotNull(this.ingressRunnable);
            this.ingressRunnable.run();
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
}
