package com.amazon.alexa.routing;

import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteAlias;
import com.amazon.alexa.routing.api.RouteMatch;
import com.amazon.alexa.routing.api.RouteMatcher;
import com.amazon.alexa.routing.api.RouteTemplate;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class DefaultRoutingRegistry implements RoutingRegistry {
    private static final String TAG = DefaultRoutingRegistry.class.getSimpleName();
    private boolean requiresSorting = false;
    private final Map<String, Route> routes = new ArrayMap();
    private final List<RouteMatcher> matchers = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class RoutingRegister implements RoutingRegistry.Register {
        Route route;

        RoutingRegister(Route route) {
            this.route = route;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register asRoot() {
            this.route.setRoot(true);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register doNotKeepInBackStack() {
            this.route.setDoNotKeepInBackStack(true);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        @Deprecated
        public RoutingRegistry.Register withHandleHeaderTitle() {
            this.route.setHandlesHeaderTitle(true);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withHeaderTitle(@StringRes Integer num) {
            this.route.setHeaderTitle(num);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withParent(String str) {
            this.route.setParent(str);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withParentDefault(String str) {
            Route route = (Route) DefaultRoutingRegistry.this.routes.get(str);
            if (route != null) {
                route.setDefaultChild(this.route);
                this.route.setParent(str);
                return this;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Route '", str, "' is not registered."));
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withSingleChildRouteInBackStack() {
            this.route.setSingleChildRouteInBackStack(true);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withTemplate(@NonNull String str) {
            this.route.setTemplate(new RouteTemplate(str));
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingRegistry.Register
        public RoutingRegistry.Register withTheme(Route.Theme theme) {
            this.route.setTheme(theme);
            return this;
        }
    }

    private synchronized void ensureSorted() {
        if (!this.requiresSorting) {
            return;
        }
        Collections.sort(this.matchers, $$Lambda$DefaultRoutingRegistry$cKv6Rp8Yx9InKbWja87MDrW4PLI.INSTANCE);
        this.requiresSorting = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$ensureSorted$0(RouteMatcher routeMatcher, RouteMatcher routeMatcher2) {
        int priority = routeMatcher.getPriority();
        int priority2 = routeMatcher2.getPriority();
        if (priority > priority2) {
            return 1;
        }
        return priority == priority2 ? 0 : -1;
    }

    private void removeMatchers(@NonNull Route route) {
        ArrayList arrayList = new ArrayList();
        for (RouteMatcher routeMatcher : this.matchers) {
            if (routeMatcher.getRoute().equals(route)) {
                arrayList.add(routeMatcher);
            }
        }
        this.matchers.removeAll(arrayList);
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistry
    @NonNull
    public synchronized Route get(String str) {
        Route route;
        route = this.routes.get(str);
        if (route == null) {
            throw new IllegalArgumentException("Route '" + str + "' is not registered.");
        }
        return route;
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistry
    @Nullable
    public synchronized RouteMatch getByUrl(String str) {
        ensureSorted();
        for (RouteMatcher routeMatcher : this.matchers) {
            RouteMatch match = routeMatcher.match(str);
            if (match != null) {
                String str2 = TAG;
                Log.i(str2, "[ROUTE] FOUND: " + match.getRoute().getName());
                return match;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public synchronized Iterator<Route> iterator() {
        return Collections.unmodifiableCollection(new ArrayList(this.routes.values())).iterator();
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistry
    @NonNull
    public RoutingRegistry.Register register(String str, int i) {
        return register(new Route(str, i));
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistry
    public synchronized void unregister(@NonNull Route route) {
        String str = "[ROUTE] Remove " + route.getName();
        this.routes.remove(route.getName());
        removeMatchers(route);
    }

    @Override // com.amazon.alexa.routing.api.RoutingRegistry
    public synchronized RoutingRegistry.Register register(@NonNull Route route) {
        String str = "[ROUTE] Add " + route.getName();
        if (this.routes.containsKey(route.getName())) {
            String str2 = "Route '" + route.getName() + "' is already registered. Updating.";
        }
        this.routes.put(route.getName(), route);
        this.matchers.add(new DefaultRouteMatcher(route));
        for (RouteAlias routeAlias : route.getAliases()) {
            this.matchers.add(new DefaultRouteAliasMatcher(routeAlias));
        }
        this.requiresSorting = true;
        return new RoutingRegister(route);
    }
}
