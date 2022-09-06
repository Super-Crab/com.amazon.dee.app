package com.amazon.alexa.drive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.regulator.Router;
/* loaded from: classes7.dex */
public class DriveModeRoutingAdapter implements RoutingAdapter {
    private static final String TAG = "DriveModeRoutingAdapter";
    private SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    private Router mainAppRouter;

    public DriveModeRoutingAdapter(Router router) {
        this.mainAppRouter = router;
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
        String str = "Leaving route: " + route + " for " + route2;
        if (this.mainAppRouter.getBackStackSize() > 0) {
            this.mainAppRouter.popControllerWithTag("alexa-oobe/drive-mode/main");
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        String str = "Navigating: " + routeContext;
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
