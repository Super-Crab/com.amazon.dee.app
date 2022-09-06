package com.amazon.alexa.routing;

import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import com.amazon.alexa.routing.api.RouteFeatureGroupFactory;
import java.util.Set;
/* loaded from: classes10.dex */
public class DefaultRouteFeatureGroup implements RouteFeatureGroup {
    private final String[] features;
    private final String name;
    private final RouteFeatureGroupFactory routeFactory;

    public DefaultRouteFeatureGroup(String str, String[] strArr, RouteFeatureGroupFactory routeFeatureGroupFactory) {
        this.name = str;
        this.features = strArr;
        this.routeFactory = routeFeatureGroupFactory;
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public String[] getFeatures() {
        return this.features;
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.routing.api.RouteFeatureGroup
    public Route[] getRoutes(Set<String> set) {
        return this.routeFactory.getRoutes(set);
    }
}
