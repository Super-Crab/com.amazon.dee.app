package com.amazon.alexa.routing;

import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class RouteFeatureGroupCache {
    public final String[] features;
    public final RouteFeatureGroup group;
    public final List<Route> routes = new ArrayList();

    public RouteFeatureGroupCache(RouteFeatureGroup routeFeatureGroup) {
        this.group = routeFeatureGroup;
        this.features = routeFeatureGroup.getFeatures();
    }
}
