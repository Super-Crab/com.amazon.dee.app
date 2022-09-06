package com.amazon.alexa.routing.api;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes10.dex */
public interface RoutingViewService {
    Observable<RouteContext> onViewChanged();

    void start();

    void stop();
}
