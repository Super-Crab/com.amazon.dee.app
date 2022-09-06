package com.amazon.alexa.routing;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.DefaultRoutingViewService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteApiUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutePath;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.RoutingViewService;
import com.amazon.alexa.routing.data.RouteUrls;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricTimeout;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
public class DefaultRoutingViewService implements RoutingViewService {
    private static final List SLOOP_OOBE_ROUTES = Arrays.asList(RouteUrls.IOTSOFTAP_PROGRESS, RouteUrls.SMART_HOME_SETUP_INSTRUCTION, RouteUrls.SMART_PLUG_LANDING_PAGE, RouteUrls.BARCODE_SCAN, RouteUrls.WIFI_CONNECTING);
    private static final String TAG = "DefaultRoutingViewService";
    private final RoutingRegistryAdapter adapters;
    private final CrashMetadata crashMetadata;
    FeatureServiceV2 featureServiceV2;
    private final Lazy<IdentityService> identityService;
    MetricTimeout loadingTimeout;
    private final MetricsService metricsService;
    Disposable onAdapterAddedSubscription;
    Disposable onRouteChangedSubscription;
    PublishSubject onViewChanged = PublishSubject.create();
    LinkedList<RouteHandler> pendingNavigationPath;
    private final RoutingRegistry routes;
    private final RoutingDelegate routingDelegate;
    private final RoutingService routingService;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class EnterRoute implements RouteHandler {
        private final Route oldRoute;
        @VisibleForTesting
        final Route route;

        EnterRoute(Route route, Route route2) {
            this.route = route;
            this.oldRoute = route2;
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(this.route.getAdapterId());
            if (routingAdapter == null || !DefaultRoutingViewService.this.adapters.canEnter(this.route)) {
                String unused = DefaultRoutingViewService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VS: Failed to find suitable adapter for ");
                outline107.append(this.route);
                outline107.toString();
                return false;
            }
            DefaultRoutingViewService.this.routingDelegate.routeDidFinish(this.route);
            String unused2 = DefaultRoutingViewService.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Entering ");
            outline1072.append(this.route);
            outline1072.append(" using ");
            outline1072.append(routingAdapter.getClass().getSimpleName());
            outline1072.toString();
            routingAdapter.enter(this.route, this.oldRoute);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class ExitRoute implements RouteHandler {
        @VisibleForTesting
        final Route route;

        ExitRoute(Route route) {
            this.route = route;
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(this.route.getAdapterId());
            if (routingAdapter != null) {
                String unused = DefaultRoutingViewService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exiting ");
                outline107.append(this.route);
                outline107.toString();
                routingAdapter.exit();
                return true;
            }
            String unused2 = DefaultRoutingViewService.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("VS: Failed to find suitable adapter for ");
            outline1072.append(this.route);
            outline1072.toString();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class LeaveRoute implements RouteHandler {
        @VisibleForTesting
        final Route newRoute;
        @VisibleForTesting
        final Route route;

        LeaveRoute(@NonNull DefaultRoutingViewService defaultRoutingViewService, Route route) {
            this(route, null);
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(this.route.getAdapterId());
            if (routingAdapter == null || !DefaultRoutingViewService.this.adapters.canLeave(this.route)) {
                String unused = DefaultRoutingViewService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VS: Failed to find suitable adapter for ");
                outline107.append(this.route);
                outline107.toString();
                return false;
            }
            String unused2 = DefaultRoutingViewService.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Leaving ");
            outline1072.append(this.route);
            outline1072.append(" using ");
            outline1072.append(routingAdapter.getClass().getSimpleName());
            outline1072.toString();
            routingAdapter.leave(this.route, this.newRoute);
            return true;
        }

        LeaveRoute(@NonNull Route route, @Nullable Route route2) {
            this.route = route;
            this.newRoute = route2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class ReEnterRoute implements RouteHandler {
        @VisibleForTesting
        final Route route;

        ReEnterRoute(Route route) {
            this.route = route;
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(this.route.getAdapterId());
            if (routingAdapter != null) {
                String unused = DefaultRoutingViewService.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Reentering ");
                outline107.append(this.route);
                outline107.toString();
                routingAdapter.reenter();
                return true;
            }
            String unused2 = DefaultRoutingViewService.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("VS: Failed to find suitable adapter for ");
            outline1072.append(this.route);
            outline1072.toString();
            return false;
        }
    }

    /* loaded from: classes10.dex */
    class ReplacedRoute implements RouteHandler {
        private final RouteContext routeContext;

        ReplacedRoute(RouteContext routeContext) {
            this.routeContext = routeContext;
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(this.routeContext.getRoute().getAdapterId());
            if (routingAdapter != null) {
                routingAdapter.replace(this.routeContext);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface RouteHandler {
        boolean handle();
    }

    /* loaded from: classes10.dex */
    static class RouteHolder {
        private final RoutingAdapter adapter;
        private final Route route;

        RouteHolder(Route route, RoutingAdapter routingAdapter) {
            this.route = route;
            this.adapter = routingAdapter;
        }
    }

    /* loaded from: classes10.dex */
    public interface RoutingDelegate {
        void routeDidFinish(Route route);

        void routeDidStart(Route route);
    }

    public DefaultRoutingViewService(RoutingService routingService, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, RoutingRegistry routingRegistry, Lazy<IdentityService> lazy, CrashMetadata crashMetadata, RoutingDelegate routingDelegate, FeatureServiceV2 featureServiceV2) {
        this.metricsService = metricsService;
        this.routingService = routingService;
        this.adapters = routingRegistryAdapter;
        this.routes = routingRegistry;
        this.identityService = lazy;
        this.crashMetadata = crashMetadata;
        this.routingDelegate = routingDelegate;
        this.loadingTimeout = new MetricTimeout(metricsService);
        this.featureServiceV2 = featureServiceV2;
    }

    @NonNull
    private boolean isSloopOobeRoute(RouteContext routeContext) {
        return SLOOP_OOBE_ROUTES.contains(routeContext.getString("name"));
    }

    private boolean shouldUseSloopFix(RouteContext routeContext) {
        if (routeContext == null) {
            return false;
        }
        return isSloopOobeRoute(routeContext) && routeContext.getRoute().getAdapterId() != 0 && isSloopOobeRoute(routeContext);
    }

    @VisibleForTesting
    void addExitAndReEnterRoutesToPath(LinkedList<RouteHandler> linkedList, Route route, Route route2) {
        int i = -1;
        int adapterId = route == null ? -1 : route.getAdapterId();
        if (route2 != null) {
            i = route2.getAdapterId();
        }
        if (adapterId != i) {
            if (route != null) {
                linkedList.add(new ExitRoute(route));
            }
            if (route2 == null) {
                return;
            }
            linkedList.add(new ReEnterRoute(route2));
        }
    }

    void attemptNavigatePendingPath() {
        LinkedList<RouteHandler> linkedList = this.pendingNavigationPath;
        if (linkedList != null) {
            navigate(linkedList);
        }
    }

    public /* synthetic */ void lambda$start$1$DefaultRoutingViewService(Integer num) throws Throwable {
        attemptNavigatePendingPath();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: navigate */
    public void lambda$start$0$DefaultRoutingViewService(RoutePath routePath) {
        RouteContext routeContext = routePath.to();
        if (routeContext != null) {
            this.routingDelegate.routeDidStart(routeContext.getRoute());
        }
        switch (routePath.getOptions()) {
            case 0:
                routeTo(routePath.from(), routePath.to(), false);
                break;
            case 1:
                routeTo(routePath.from(), routePath.to(), true);
                break;
            case 2:
                routeBack(routePath.from(), routePath.to());
                break;
            case 3:
                routeUp(routePath.from(), routePath.to());
                break;
            case 4:
                routeExit(routePath.from());
                break;
            case 5:
                routeReplace(routePath.from(), routePath.to());
                break;
            case 6:
                notifyOnly(routePath.from(), routePath.to());
                break;
        }
        if (shouldUseSloopFix(this.routingService.getCurrentRoute())) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Navigating ");
            outline107.append(this.routingService.getCurrentRoute());
            outline107.append(" with sloop fix");
            outline107.toString();
            RoutingAdapter routingAdapter = this.adapters.get(0);
            if (routingAdapter == null) {
                return;
            }
            routingAdapter.reset();
        }
    }

    void notifyOnly(RouteContext routeContext, RouteContext routeContext2) {
        if (routeContext != null) {
            Route route = routeContext.getRoute();
            Route route2 = routeContext2 != null ? routeContext2.getRoute() : null;
            Route findCommonParent = RouteApiUtils.findCommonParent(route, route2, this.routes);
            LinkedList<RouteHandler> linkedList = new LinkedList<>();
            recordLeaveRoutes(route, findCommonParent, linkedList, route2);
            navigate(linkedList);
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingViewService
    public Observable<RouteContext> onViewChanged() {
        return this.onViewChanged.hide();
    }

    void recordEnterRoutes(Route route, Route route2, LinkedList<RouteHandler> linkedList, Route route3) {
        LinkedList linkedList2 = new LinkedList();
        while (route != null && !Objects.equals(route, route2)) {
            linkedList2.add(route);
            route = route.getParent() != null ? this.routes.get(route.getParent()) : null;
        }
        while (true) {
            Route route4 = (Route) linkedList2.pollLast();
            if (route4 != null) {
                linkedList.add(new EnterRoute(route4, route3));
            } else {
                return;
            }
        }
    }

    void recordLeaveRoutes(Route route, Route route2, LinkedList<RouteHandler> linkedList) {
        while (route != null && !Objects.equals(route, route2)) {
            linkedList.add(new LeaveRoute(this, route));
            route = route.getParent() != null ? this.routes.get(route.getParent()) : null;
        }
    }

    void routeBack(RouteContext routeContext, RouteContext routeContext2) {
        Route route = routeContext != null ? routeContext.getRoute() : null;
        Route route2 = routeContext2 != null ? routeContext2.getRoute() : null;
        Route findCommonParent = RouteApiUtils.findCommonParent(route2, route, this.routes);
        LinkedList<RouteHandler> linkedList = new LinkedList<>();
        recordLeaveRoutes(route, findCommonParent, linkedList, route2);
        if (routeContext2 != null) {
            recordEnterRoutes(route2, findCommonParent, linkedList, route);
            linkedList.add(new NavigateRoute(routeContext2, false));
        } else {
            this.routingService.setCurrentRouteContext(null, false);
        }
        addExitAndReEnterRoutesToPath(linkedList, route, route2);
        navigate(linkedList);
    }

    void routeExit(RouteContext routeContext) {
        Route route = routeContext != null ? routeContext.getRoute() : null;
        LinkedList<RouteHandler> linkedList = new LinkedList<>();
        recordLeaveRoutes(route, null, linkedList);
        navigate(linkedList);
    }

    void routeReplace(RouteContext routeContext, RouteContext routeContext2) {
        Route route = null;
        Route route2 = routeContext != null ? routeContext.getRoute() : null;
        if (routeContext2 != null) {
            route = routeContext2.getRoute();
        }
        LinkedList<RouteHandler> linkedList = new LinkedList<>();
        if (route2 != null) {
            linkedList.add(new LeaveRoute(route2, route));
        }
        linkedList.add(new NavigateRoute(routeContext2, false));
        navigate(linkedList);
    }

    void routeTo(RouteContext routeContext, RouteContext routeContext2, boolean z) {
        Route route = null;
        Route route2 = routeContext != null ? routeContext.getRoute() : null;
        if (routeContext2 != null) {
            route = routeContext2.getRoute();
        }
        Route findCommonParent = RouteApiUtils.findCommonParent(route2, route, this.routes);
        Log.i(TAG, "Attempting to navigate from " + route2 + " to " + route + " with common parent " + findCommonParent);
        LinkedList<RouteHandler> linkedList = new LinkedList<>();
        boolean z2 = false;
        boolean isOverlay = route != null ? route.isOverlay() : false;
        if (!isOverlay) {
            addExitAndReEnterRoutesToPath(linkedList, route2, route);
            recordLeaveRoutes(route2, findCommonParent, linkedList, route);
        }
        recordEnterRoutes(route, findCommonParent, linkedList, route2);
        if (!isOverlay && z) {
            z2 = true;
        }
        linkedList.add(new NavigateRoute(routeContext2, z2, !isOverlay));
        navigate(linkedList);
    }

    void routeUp(RouteContext routeContext, RouteContext routeContext2) {
        Route route = null;
        Route route2 = routeContext != null ? routeContext.getRoute() : null;
        if (routeContext2 != null) {
            route = routeContext2.getRoute();
        }
        LinkedList<RouteHandler> linkedList = new LinkedList<>();
        if (route2 != null) {
            linkedList.add(new LeaveRoute(route2, route));
        }
        if (routeContext2 != null) {
            linkedList.add(new NavigateRoute(routeContext2, false));
        }
        navigate(linkedList);
    }

    @Override // com.amazon.alexa.routing.api.RoutingViewService
    public void start() {
        stop();
        this.onRouteChangedSubscription = this.routingService.onRouteChanged().subscribe(new Consumer() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingViewService$iOKLNblusOgVAL1Ba91cHhcA3-Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultRoutingViewService.this.lambda$start$0$DefaultRoutingViewService((RoutePath) obj);
            }
        });
        this.onAdapterAddedSubscription = this.adapters.onAdapterAdded().subscribe(new Consumer() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingViewService$oFnfwOL737f6Z1afVYAAOuPMyaw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultRoutingViewService.this.lambda$start$1$DefaultRoutingViewService((Integer) obj);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingViewService
    public void stop() {
        Disposable disposable = this.onRouteChangedSubscription;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.onAdapterAddedSubscription;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.onRouteChangedSubscription = null;
        this.onAdapterAddedSubscription = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class NavigateRoute implements RouteHandler {
        private final boolean addToBackStack;
        @VisibleForTesting
        final RouteContext routeContext;
        private final boolean updateCurrentRoute;

        NavigateRoute(RouteContext routeContext, boolean z) {
            this.routeContext = routeContext;
            this.addToBackStack = z;
            this.updateCurrentRoute = true;
        }

        @Override // com.amazon.alexa.routing.DefaultRoutingViewService.RouteHandler
        public boolean handle() {
            Route route = this.routeContext.getRoute();
            RoutingAdapter routingAdapter = DefaultRoutingViewService.this.adapters.get(route.getAdapterId());
            Boolean valueOf = Boolean.valueOf(DefaultRoutingViewService.this.adapters.canNavigate(route));
            boolean z = false;
            if (routingAdapter == null || !valueOf.booleanValue()) {
                String unused = DefaultRoutingViewService.TAG;
                String str = "VS: Failed to find suitable adapter for " + route;
                return false;
            }
            String unused2 = DefaultRoutingViewService.TAG;
            String str2 = "Navigating " + route + " using " + routingAdapter.getClass().getSimpleName();
            RouteContext currentRoute = DefaultRoutingViewService.this.routingService.getCurrentRoute();
            Route route2 = currentRoute != null ? currentRoute.getRoute() : null;
            if (this.addToBackStack && currentRoute != null) {
                Route findCommonParent = RouteApiUtils.findCommonParent(route, route2, DefaultRoutingViewService.this.routes);
                if (findCommonParent != null && findCommonParent.isSingleChildRouteInBackStack()) {
                    String unused3 = DefaultRoutingViewService.TAG;
                    String str3 = route2 + " and " + route + " sharing same parent and only single route is allowed to be in stack. " + route2 + " is not saved on back stack.";
                } else if (this.addToBackStack && route2 != null) {
                    z = true;
                }
            }
            if (this.updateCurrentRoute) {
                DefaultRoutingViewService.this.routingService.setCurrentRouteContext(this.routeContext, z);
            }
            DefaultRoutingViewService.this.crashMetadata.put("is_transitioning", true);
            routingAdapter.navigate(this.routeContext, new Runnable() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingViewService$NavigateRoute$swEKipnxZIb1VfI4t_bEF6eJMSE
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultRoutingViewService.NavigateRoute.this.lambda$handle$0$DefaultRoutingViewService$NavigateRoute();
                }
            });
            DefaultRoutingViewService.this.routingService.notifyRouteChanged(this.routeContext);
            return true;
        }

        public /* synthetic */ void lambda$handle$0$DefaultRoutingViewService$NavigateRoute() {
            DefaultRoutingViewService.this.crashMetadata.put("is_transitioning", false);
            DefaultRoutingViewService.this.onViewChanged.onNext(this.routeContext);
        }

        NavigateRoute(RouteContext routeContext, boolean z, boolean z2) {
            this.routeContext = routeContext;
            this.addToBackStack = z;
            this.updateCurrentRoute = z2;
        }
    }

    void recordLeaveRoutes(Route route, Route route2, LinkedList<RouteHandler> linkedList, Route route3) {
        while (route != null && !Objects.equals(route, route2)) {
            linkedList.add(new LeaveRoute(route, route3));
            route = route.getParent() != null ? this.routes.get(route.getParent()) : null;
        }
    }

    void navigate(LinkedList<RouteHandler> linkedList) {
        this.pendingNavigationPath = null;
        Iterator<RouteHandler> it2 = linkedList.iterator();
        while (it2.hasNext()) {
            if (it2.next().handle()) {
                it2.remove();
            } else {
                this.pendingNavigationPath = linkedList;
                return;
            }
        }
    }
}
