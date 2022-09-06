package com.amazon.alexa.routing;

import android.os.Bundle;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteMatch;
import com.amazon.alexa.routing.api.RouteMatcher;
/* loaded from: classes10.dex */
public class DefaultRouteMatcher implements RouteMatcher {
    private final Route target;

    public DefaultRouteMatcher(Route route) {
        this.target = route;
    }

    @Override // com.amazon.alexa.routing.api.RouteMatcher
    public int getPriority() {
        return this.target.getPriority();
    }

    @Override // com.amazon.alexa.routing.api.RouteMatcher
    public Route getRoute() {
        return this.target;
    }

    @Override // com.amazon.alexa.routing.api.RouteMatcher
    public RouteMatch match(String str) {
        Bundle parseParameters;
        if (this.target.getTemplate() == null || (parseParameters = this.target.getTemplate().parseParameters(str)) == null) {
            return null;
        }
        return new RouteMatch(this.target, parseParameters);
    }
}
