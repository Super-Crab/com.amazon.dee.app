package com.amazon.alexa.drive.main.v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
/* loaded from: classes7.dex */
public class DriveModeWebLabIngressRoutingAdapter implements RoutingAdapter {
    private DriveModeService driveModeService;
    private RoutingAdapter legacyAdapter;
    private RoutingAdapter newAdapter;

    public DriveModeWebLabIngressRoutingAdapter(DriveModeService driveModeService, RoutingAdapter routingAdapter, RoutingAdapter routingAdapter2) {
        this.driveModeService = driveModeService;
        this.legacyAdapter = routingAdapter;
        this.newAdapter = routingAdapter2;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
        this.newAdapter.enter(route, route2);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
        this.newAdapter.exit();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.newAdapter.getConfiguration(route);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return this.newAdapter.getId();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
        this.newAdapter.leave(route, route2);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        this.newAdapter.navigate(routeContext, runnable);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
        this.newAdapter.push(routeContext);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
        this.newAdapter.reenter();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
        this.newAdapter.replace(routeContext);
    }
}
