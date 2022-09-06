package com.amazon.alexa.biloba.routing;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class DeferredRoutingHelper {
    public static final String DEFERRED_ROUTE_CONTEXT = "deferred_route_context";
    private static final String TAG = "DeferredRoutingHelper";
    private RouteContext deferredRouteContext;
    @Inject
    Lazy<RoutingService> routingService;

    @Inject
    public DeferredRoutingHelper() {
    }

    public void clearDeferredRouteContext() {
        LogUtils.d(TAG, "Clearing deferred route context.");
        this.deferredRouteContext = null;
    }

    public RouteContext getDeferredRouteContext() {
        return this.deferredRouteContext;
    }

    public void processDeferredRouteContext() {
        RouteContext routeContext = this.deferredRouteContext;
        if (routeContext != null) {
            String name = routeContext.getRoute().getName();
            String str = TAG;
            LogUtils.d(str, "Processing deferred route context; navigating to " + name);
            this.routingService.mo358get().route(name).with(DEFERRED_ROUTE_CONTEXT, this.deferredRouteContext).navigate();
            clearDeferredRouteContext();
            return;
        }
        LogUtils.d(TAG, "No deferred route to navigate to.");
    }

    public void setDeferredRouteContext(RouteContext routeContext) {
        this.deferredRouteContext = routeContext;
    }

    @VisibleForTesting
    DeferredRoutingHelper(Lazy<RoutingService> lazy) {
        this.routingService = lazy;
    }
}
