package com.amazon.dee.app.elements;

import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.RouteWatcher;
/* loaded from: classes12.dex */
public class ReactRouteRegistry {
    RouteFeatureGroupRegistry groupRoutes;
    DynamicRouteFeatureGroup reactFeatureGroup = new DynamicRouteFeatureGroup();
    RouteWatcher routeWatcher;

    public ReactRouteRegistry(RouteFeatureGroupRegistry routeFeatureGroupRegistry, RouteWatcher routeWatcher) {
        this.groupRoutes = routeFeatureGroupRegistry;
        this.routeWatcher = routeWatcher;
    }

    public void add(ReactRoute reactRoute) {
        registerGroup();
        this.reactFeatureGroup.add(reactRoute);
    }

    public void registerGroup() {
        if (this.groupRoutes.getGroup("elements-dynamic") == null) {
            this.groupRoutes.register(this.reactFeatureGroup);
        }
    }

    public void remove(String str) {
        registerGroup();
        this.reactFeatureGroup.remove(str);
    }

    public void update() {
        this.routeWatcher.update();
    }
}
