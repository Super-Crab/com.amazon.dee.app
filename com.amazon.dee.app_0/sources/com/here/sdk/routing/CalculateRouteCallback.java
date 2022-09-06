package com.here.sdk.routing;

import androidx.annotation.Nullable;
import java.util.List;
@FunctionalInterface
/* loaded from: classes3.dex */
public interface CalculateRouteCallback {
    void onRouteCalculated(@Nullable RoutingError routingError, @Nullable List<Route> list);
}
