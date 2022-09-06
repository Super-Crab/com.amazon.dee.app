package com.amazon.alexa.routing;

import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteApiUtils;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import com.amazon.alexa.routing.api.RouteFeatureGroupFactory;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.google.common.base.Optional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class RouteUtils extends RouteApiUtils {
    static final Route[] EMPTY_ROUTE_ARRAY = new Route[0];

    private RouteUtils() {
    }

    public static RouteFeatureGroup createFeatureGroup(String str, final String str2, final Route[] routeArr) {
        return new DefaultRouteFeatureGroup(str, new String[]{str2}, new RouteFeatureGroupFactory() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteUtils$lYfVoqwnsIUnXSLwohImy5HbtYU
            @Override // com.amazon.alexa.routing.api.RouteFeatureGroupFactory
            public final Route[] getRoutes(Set set) {
                return RouteUtils.lambda$createFeatureGroup$0(str2, routeArr, set);
            }
        });
    }

    @Deprecated
    public static Optional<Route> getRouteWithNonInheritedContentMode(@NonNull Route route, @NonNull RoutingRegistry routingRegistry) {
        Route parentRouteWithContentMode = RouteApiUtils.getParentRouteWithContentMode(route, routingRegistry);
        if (parentRouteWithContentMode == null) {
            return Optional.absent();
        }
        return Optional.of(parentRouteWithContentMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Route[] lambda$createFeatureGroup$0(String str, Route[] routeArr, Set set) {
        return set.contains(str) ? routeArr : new Route[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Route[] lambda$createFeatureGroup$1(String str, Route[] routeArr, Route[] routeArr2, Set set) {
        return set.contains(str) ? routeArr : routeArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Route[] lambda$createFeatureGroup$2(Set set, Route[] routeArr, Set set2) {
        return set2.containsAll(set) ? routeArr : EMPTY_ROUTE_ARRAY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Route[] lambda$createFeatureGroup$3(Set set, Route[] routeArr, Route[] routeArr2, Set set2) {
        return set2.containsAll(set) ? routeArr : routeArr2;
    }

    public static RouteFeatureGroup createFeatureGroup(String str, final String str2, final Route[] routeArr, final Route[] routeArr2) {
        return new DefaultRouteFeatureGroup(str, new String[]{str2}, new RouteFeatureGroupFactory() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteUtils$crTcxQLKH8cOju2g6m5CG1Bduz8
            @Override // com.amazon.alexa.routing.api.RouteFeatureGroupFactory
            public final Route[] getRoutes(Set set) {
                return RouteUtils.lambda$createFeatureGroup$1(str2, routeArr, routeArr2, set);
            }
        });
    }

    public static RouteFeatureGroup createFeatureGroup(String str, String[] strArr, final Route[] routeArr) {
        final HashSet hashSet = new HashSet(Arrays.asList(strArr));
        return new DefaultRouteFeatureGroup(str, strArr, new RouteFeatureGroupFactory() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteUtils$9sGOV2GCWBRwL5sbyFplkGWTrLE
            @Override // com.amazon.alexa.routing.api.RouteFeatureGroupFactory
            public final Route[] getRoutes(Set set) {
                return RouteUtils.lambda$createFeatureGroup$2(hashSet, routeArr, set);
            }
        });
    }

    public static RouteFeatureGroup createFeatureGroup(String str, String[] strArr, final Route[] routeArr, final Route[] routeArr2) {
        final HashSet hashSet = new HashSet(Arrays.asList(strArr));
        return new DefaultRouteFeatureGroup(str, strArr, new RouteFeatureGroupFactory() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteUtils$CGpXwsi3Qwshy6baPHeEt_Mu2mI
            @Override // com.amazon.alexa.routing.api.RouteFeatureGroupFactory
            public final Route[] getRoutes(Set set) {
                return RouteUtils.lambda$createFeatureGroup$3(hashSet, routeArr, routeArr2, set);
            }
        });
    }
}
