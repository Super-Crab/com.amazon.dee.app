package com.amazon.alexa.routing.api;
/* loaded from: classes10.dex */
public interface RouteMatcher {
    int getPriority();

    Route getRoute();

    RouteMatch match(String str);
}
