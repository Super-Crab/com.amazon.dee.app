package com.amazon.alexa.routing.api;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/* loaded from: classes10.dex */
public class RouteApiUtils {
    private static final String TAG = "RouteApiUtils";

    public static ArrayList<Route> buildPath(Route route, RoutingRegistry routingRegistry) {
        ArrayList<Route> arrayList = new ArrayList<>();
        while (route != null) {
            arrayList.add(route);
            String parent = route.getParent();
            if (parent != null) {
                if (route.getName().equals(parent)) {
                    GeneratedOutlineSupport1.outline162("buildPath() detected a route with itself as its parent: ", parent, TAG);
                } else {
                    route = routingRegistry.get(parent);
                }
            }
            route = null;
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static Route findCommonParent(Route route, Route route2, RoutingRegistry routingRegistry) {
        ArrayList<Route> buildPath = buildPath(route, routingRegistry);
        ArrayList<Route> buildPath2 = buildPath(route2, routingRegistry);
        int min = Math.min(buildPath.size(), buildPath2.size());
        Route route3 = null;
        for (int i = 0; i < min && Objects.equals(buildPath.get(i), buildPath2.get(i)); i++) {
            route3 = buildPath.get(i);
        }
        return route3;
    }

    private static Route getNextRoute(RoutingRegistry routingRegistry, Route route) {
        String name = route.getName();
        String parent = route.getParent();
        if (parent == null) {
            return null;
        }
        if (name.equals(parent)) {
            GeneratedOutlineSupport1.outline162("The route has detected a route with itself as its parent: ", parent, TAG);
            return null;
        }
        return routingRegistry.get(parent);
    }

    @Nullable
    public static Route getParentRouteWithContentMode(@NonNull Route route, @NonNull RoutingRegistry routingRegistry) {
        while (route != null) {
            if (route.getContentMode() != 0) {
                return route;
            }
            String parent = route.getParent();
            if (parent == null) {
                route = null;
            } else {
                try {
                    route = routingRegistry.get(parent);
                } catch (IllegalArgumentException e) {
                    Log.w(TAG, "Fetch route failed. Error: " + e);
                }
            }
        }
        return null;
    }

    public static boolean hasParent(Route route, String str, RoutingRegistry routingRegistry) {
        while (route != null) {
            if (route.getName().equals(str)) {
                return true;
            }
            route = getNextRoute(routingRegistry, route);
        }
        return false;
    }

    public static boolean isDependentOn(Route route, String str, RoutingRegistry routingRegistry) {
        return hasParent(route, str, routingRegistry);
    }

    public static boolean isDependentOnAny(Route route, ArrayList<String> arrayList, RoutingRegistry routingRegistry) {
        while (route != null) {
            if (arrayList.contains(route.getName())) {
                return true;
            }
            route = getNextRoute(routingRegistry, route);
        }
        return false;
    }
}
