package com.amazon.alexa.routing;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventing.EventArgs;
import com.amazon.alexa.eventing.EventHandler;
import com.amazon.alexa.eventing.EventSubscription;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteFeatureGroup;
import com.amazon.alexa.routing.api.RoutingRegistry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class RouteWatcher {
    private static final String TAG = "RouteWatcher";
    private EventBus eventBus;
    private MultiFilterSubscriber eventBusSubscriber;
    private final RouteFeatureGroupRegistry groupRoutes;
    private final IdentityService identityService;
    private final RoutingRegistry routes;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    @VisibleForTesting
    public MultiFilterSubscriber.FilterUuid identitySubscription = null;
    private EventSubscription groupRouteSubscription = null;
    private final List<RouteFeatureGroupCache> groupRouteCache = new ArrayList();

    public RouteWatcher(IdentityService identityService, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, EventBus eventBus) {
        this.identityService = identityService;
        this.routes = routingRegistry;
        this.groupRoutes = routeFeatureGroupRegistry;
        this.eventBus = eventBus;
    }

    private void unregisterCachedRoutes() {
        for (RouteFeatureGroupCache routeFeatureGroupCache : this.groupRouteCache) {
            for (Route route : routeFeatureGroupCache.routes) {
                this.routes.unregister(route);
            }
        }
    }

    private void updateGroup(RouteFeatureGroupCache routeFeatureGroupCache, Set<String> set) {
        Route[] routes;
        for (Route route : routeFeatureGroupCache.routes) {
            this.routes.unregister(route);
        }
        routeFeatureGroupCache.routes.clear();
        for (Route route2 : routeFeatureGroupCache.group.getRoutes(set)) {
            this.routes.register(route2);
            routeFeatureGroupCache.routes.add(route2);
        }
    }

    public /* synthetic */ void lambda$null$0$RouteWatcher() {
        refresh(this.identityService.getUser(TAG));
    }

    public /* synthetic */ void lambda$start$1$RouteWatcher(Message message) {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteWatcher$5UUL2Lo-An3ja98rh1crjMB3sO8
            @Override // java.lang.Runnable
            public final void run() {
                RouteWatcher.this.lambda$null$0$RouteWatcher();
            }
        });
    }

    public /* synthetic */ void lambda$start$2$RouteWatcher(EventArgs eventArgs) {
        UserIdentity user = this.identityService.getUser(TAG);
        RouteFeatureGroupCache routeFeatureGroupCache = new RouteFeatureGroupCache((RouteFeatureGroup) eventArgs.get());
        this.groupRouteCache.add(routeFeatureGroupCache);
        if (user != null) {
            refreshGroup(routeFeatureGroupCache, user);
        }
    }

    void onFeaturesChanged() {
        onFeaturesChanged(this.identityService.getUser(TAG));
    }

    void refresh(UserIdentity userIdentity) {
        for (RouteFeatureGroupCache routeFeatureGroupCache : this.groupRouteCache) {
            refreshGroup(routeFeatureGroupCache, userIdentity);
        }
    }

    void refreshGroup(RouteFeatureGroupCache routeFeatureGroupCache, UserIdentity userIdentity) {
        String[] strArr;
        HashSet hashSet = new HashSet();
        if (userIdentity != null) {
            Set<String> features = userIdentity.getFeatures();
            for (String str : routeFeatureGroupCache.features) {
                if (features.contains(str)) {
                    hashSet.add(str);
                }
            }
        }
        updateGroup(routeFeatureGroupCache, hashSet);
    }

    public void start() {
        for (RouteFeatureGroup routeFeatureGroup : this.groupRoutes.getGroups()) {
            this.groupRouteCache.add(new RouteFeatureGroupCache(routeFeatureGroup));
        }
        onFeaturesChanged();
        this.eventBusSubscriber = this.eventBus.getSubscriber();
        this.identitySubscription = this.eventBusSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteWatcher$IbjuReVEhNslP5aaIdLcgKYU6bA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RouteWatcher.this.lambda$start$1$RouteWatcher(message);
            }
        });
        this.groupRouteSubscription = this.groupRoutes.onGroupRouteAdded().subscribe(new EventHandler() { // from class: com.amazon.alexa.routing.-$$Lambda$RouteWatcher$f9_LQlyrk6Tlz_JrdjVxFtX0IQc
            @Override // com.amazon.alexa.eventing.EventHandler
            public final void onEvent(EventArgs eventArgs) {
                RouteWatcher.this.lambda$start$2$RouteWatcher(eventArgs);
            }
        });
    }

    public void stop() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.identitySubscription;
        if (filterUuid != null) {
            this.eventBusSubscriber.unsubscribeFilter(filterUuid);
            this.identitySubscription = null;
        }
        EventSubscription eventSubscription = this.groupRouteSubscription;
        if (eventSubscription != null) {
            eventSubscription.unsubscribe();
            this.groupRouteSubscription = null;
        }
        unregisterCachedRoutes();
        this.groupRouteCache.clear();
    }

    public void update() {
        UserIdentity user = this.identityService.getUser(TAG);
        unregisterCachedRoutes();
        if (user != null) {
            refresh(user);
        }
    }

    private void onFeaturesChanged(UserIdentity userIdentity) {
        refresh(userIdentity);
    }
}
