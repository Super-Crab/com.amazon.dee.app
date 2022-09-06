package com.amazon.alexa.routing.api;

import android.os.Bundle;
/* loaded from: classes10.dex */
public final class RouteMatch {
    private final Bundle params;
    private final Route route;

    public RouteMatch(Route route, Bundle bundle) {
        this.route = route;
        this.params = bundle;
    }

    public Bundle getParams() {
        return this.params;
    }

    public Route getRoute() {
        return this.route;
    }
}
