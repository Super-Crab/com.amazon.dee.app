package com.amazon.alexa.routing;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteApiUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteMatch;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutePath;
import com.amazon.alexa.routing.api.RoutingObserver;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.api.constants.Events;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.routing.metrics.RoutingMetricsConstants;
import com.amazon.dee.app.elements.ElementsUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricTimeout;
import com.dee.app.metrics.MetricsService;
import com.google.common.base.Preconditions;
import com.google.gson.JsonParser;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
/* loaded from: classes10.dex */
public class DefaultRoutingService implements RoutingService {
    public static final String ALEXA_BILOBA_ANDROID_MENU_INGRESS = "ALEXA_BILOBA_ANDROID_MENU_INGRESS";
    private static final String CURRENT_ROUTE = "current-route";
    private static final String ELEMENTS_KEY_NAME = "name";
    static final String JASPER_DEV = "JASPER_DEV";
    @VisibleForTesting
    static final String JASPER_HOME_AND_NAV_MENU = "JASPER_BRAVEHEARTS_ANDROID";
    static final String JASPER_ONE_ANDROID = "JASPER_ONE_ANDROID";
    private static final int LONG_LOADING_TIME = 180;
    private static final String ROUTE_BACKSTACK = "route-backstack";
    private static final String ROUTE_LOADING_TIMEOUT = "route_loading_timeout";
    private static final int SHORT_LOADING_TIME = 60;
    private static final String TAG = "DefaultRoutingService";
    private static final List<String> overrideParentRoute = new ArrayList(2);
    private final RoutingRegistryAdapter adapters;
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener carePlusFeatureUpdateListener;
    private final Context context;
    private final CrashMetadata crashMetadata;
    RouteContext currentRouteContext;
    @VisibleForTesting
    boolean delegatedProfileState;
    private final Lazy<EventBus> eventBus;
    private final FeatureServiceV2 featureServiceV2;
    @VisibleForTesting
    MultiFilterSubscriber identityDelegationSubscriber;
    private final Lazy<IdentityService> identityService;
    MetricTimeout loadingTimeout;
    private final MetricsService metricsService;
    private final RoutingRegistry routingRegistry;
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid identityDelegationInactiveUuid = null;
    LinkedList<RoutingObserver> observers = new LinkedList<>();
    Stack<RouteContext> backStack = new Stack<>();
    Set<RoutingService.RouteInterceptor> routeInterceptors = new HashSet();
    PublishSubject<RoutePath> onRouteChanged = PublishSubject.create();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class DefaultRoutingBuilder implements RoutingService.RoutingBuilder {
        boolean addToBackStack;
        boolean clearBackStack;
        boolean fromMainMenu;
        final Bundle parameters;
        String requestID;
        final Route route;

        DefaultRoutingBuilder(Route route) {
            this.route = route;
            this.parameters = new Bundle();
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder addToBackStack() {
            this.addToBackStack = true;
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder clearBackStack() {
            this.clearBackStack = true;
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RouteContext create() {
            return new RouteContext(this.route, this.parameters, this.requestID);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void forceNavigate() {
            DefaultRoutingService.this.navigate(create(), this.addToBackStack, this.clearBackStack, true, this.fromMainMenu);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder fromMainMenu() {
            this.fromMainMenu = true;
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void navigate() {
            DefaultRoutingService.this.navigate(create(), this.addToBackStack, this.clearBackStack, false, this.fromMainMenu);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void navigateReplaceTop() {
            DefaultRoutingService.this.navigateReplaceTop(create());
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void notifyNavigated() {
            DefaultRoutingService.this.notifyRouteNavigated(create(), this.addToBackStack, this.clearBackStack);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void popUntil() {
            DefaultRoutingService.this.popUntil(new RouteContext(this.route, this.parameters, this.requestID).toUri(), false, Bundle.EMPTY);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder with(@NonNull String str, @Nullable String str2) {
            this.parameters.putString(str, str2);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder withAll(@NonNull Bundle bundle) {
            this.parameters.putAll(bundle);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder withRequestId(@NonNull String str) {
            this.requestID = str;
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public void popUntil(boolean z) {
            Bundle bundle = this.parameters;
            DefaultRoutingService.this.popUntil(z ? this.route.getName() : new RouteContext(this.route, bundle, this.requestID).toUri(), z, bundle);
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder with(@NonNull String str, @Nullable Bundle bundle) {
            this.parameters.putBundle(str, bundle);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder with(@NonNull String str, @Nullable Parcelable parcelable) {
            this.parameters.putParcelable(str, parcelable);
            return this;
        }

        DefaultRoutingBuilder(Route route, Bundle bundle) {
            this.route = route;
            this.parameters = bundle;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder with(@NonNull String str, int i) {
            this.parameters.putInt(str, i);
            return this;
        }

        @Override // com.amazon.alexa.routing.api.RoutingService.RoutingBuilder
        public RoutingService.RoutingBuilder with(@NonNull String str, boolean z) {
            this.parameters.putBoolean(str, z);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface RouteMatcher {
        boolean match(RouteContext routeContext, @NonNull String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface UriMatcherFactory<T> {
        T create(@NonNull Route route, @NonNull Bundle bundle);
    }

    public DefaultRoutingService(Context context, RoutingRegistry routingRegistry, MetricsService metricsService, RoutingRegistryAdapter routingRegistryAdapter, CrashMetadata crashMetadata, FeatureServiceV2 featureServiceV2, Lazy<IdentityService> lazy, Lazy<EventBus> lazy2) {
        this.context = context;
        this.routingRegistry = routingRegistry;
        this.metricsService = metricsService;
        this.adapters = routingRegistryAdapter;
        this.crashMetadata = crashMetadata;
        this.featureServiceV2 = featureServiceV2;
        this.identityService = lazy;
        this.eventBus = lazy2;
        this.loadingTimeout = new MetricTimeout(metricsService);
        overrideParentRoute.add(RouteName.FLASH_BRIEFING_SKILLS);
        overrideParentRoute.add(RouteName.SMART_HOME_SKILLS);
        overrideParentRoute.add(RouteName.AMAZON_MY_MUSIC);
        this.identityDelegationSubscriber = lazy2.mo358get().getNewSubscriber();
        initialize();
    }

    private void fireRouteChanged(RouteContext routeContext, RouteContext routeContext2, int i) {
        updateCrashMetadataOnRouteChanged(routeContext, routeContext2);
        this.onRouteChanged.onNext(new RoutePath(routeContext, routeContext2, i));
        publishRouteChangedEvent(routeContext, routeContext2);
    }

    private List<RouteContext> getRoutesUpTo(String str, RouteMatcher routeMatcher) {
        ArrayList arrayList = new ArrayList();
        int size = this.backStack.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            RouteContext routeContext = this.backStack.get(size);
            if (routeMatcher.match(routeContext, str)) {
                arrayList.add(routeContext);
                break;
            }
            arrayList.add(routeContext);
            size--;
        }
        return arrayList;
    }

    private Map<String, Object> getTimeoutEntries(String str, String str2, int i) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("startPoint", str, "endPoint", str2);
        outline134.put("loadingTimeout", Integer.valueOf(i));
        return outline134;
    }

    private void initialize() {
        subscribeToIdentityDelegationInactiveEvent();
        this.delegatedProfileState = false;
        this.carePlusFeatureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.routing.DefaultRoutingService.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                DefaultRoutingService.this.setDelegatedProfileState();
            }
        };
    }

    private boolean isMenuRoute(String str) {
        if (!this.featureServiceV2.hasAccess("JASPER_BRAVEHEARTS_ANDROID", false) && !this.featureServiceV2.hasAccess("JASPER_DEV", false) && !this.featureServiceV2.hasAccess("JASPER_ONE_ANDROID", false)) {
            return Arrays.asList(RouteName.MUSIC_ELEMENTS, RouteName.SMART_HOME, "v2/behaviors", "v2/device-settings", RouteName.ELEMENTS_SETTINGS, "v2/homefeed", RouteName.NOW_PLAYING_ELEMENTS, "v2/reminders-alarms-timers", "v2/lists", RouteName.ELEMENTS_A2S_FRONT_PAGE, RouteName.CHANNELS_DEVICES, RouteName.THINGS_TO_TRY_ELEMENTS, RouteName.CHANNELS_ENTERTAINMENT, "v2/guided-discovery/add-devices", "v2/comms/conversation-list", RouteName.HOME).contains(str);
        }
        return Arrays.asList(RouteName.CHANNELS_DEVICES, RouteName.CHANNELS_ENTERTAINMENT, "v2/comms/conversation-list", RouteName.HOME).contains(str);
    }

    private boolean matchesElementsRouteByName(RouteContext routeContext, String str) {
        return str.equalsIgnoreCase(routeContext.getString("name"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean matchesName(RouteContext routeContext, String str) {
        return str.equalsIgnoreCase(routeContext.getRoute().getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean matchesUri(RouteContext routeContext, String str) {
        Preconditions.checkNotNull(routeContext, "routeContext was null");
        Preconditions.checkNotNull(str, "uri was null");
        return str.equalsIgnoreCase(routeContext.toUri()) || matchesElementsRouteByName(routeContext, str);
    }

    private void navigateToNewRoot(String str, Bundle bundle) {
        navigateToNewRoot(str, bundle, false);
    }

    private String removeQueryString(String str) {
        return str.split("\\?")[0];
    }

    private void updateCrashMetadataOnRouteChanged(RouteContext routeContext, RouteContext routeContext2) {
        this.crashMetadata.put("last_route", "ERROR_PARSING");
        this.crashMetadata.put("next_route", "ERROR_PARSING");
        try {
            if (routeContext != null) {
                this.crashMetadata.put("last_route", getRootSegment(routeContext));
            } else {
                this.crashMetadata.put("last_route", "none");
            }
            if (routeContext2 != null) {
                this.crashMetadata.put("next_route", getRootSegment(routeContext2));
            } else {
                this.crashMetadata.put("next_route", "none");
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void addHomeToBackStackIfEmpty() {
        if (getBackstack().length == 1) {
            Route route = this.routingRegistry.get(RouteName.HOME);
            route.setDoNotKeepInBackStack(false);
            saveRouteContextInBackStack(new RouteContext(route));
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean canNavigateBackward() {
        RouteContext currentRoute = getCurrentRoute();
        if (currentRoute != null) {
            return !this.backStack.empty() && !isMenuRoute(currentRoute.getRoute().getName()) && !currentRoute.isFromMainMenu();
        }
        return !this.backStack.empty();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void clearBackStack() {
        this.backStack.clear();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void clearCurrentRoute() {
        this.currentRouteContext = null;
    }

    public void clearDelegatedProfileRoutesFromBackStack() {
        if (!this.backStack.empty()) {
            while (this.backStack.peek().getRoute().supportsDelegatedProfile()) {
                removeTopFromBackStack();
            }
            this.currentRouteContext = this.backStack.peek();
        }
    }

    @NonNull
    RouteContext createWebRoute(@NonNull String str, @Nullable String str2) {
        return new RouteContext(this.routingRegistry.get("web"), GeneratedOutlineSupport1.outline12(RouteParameter.ROUTE, str, RouteParameter.PARENT, str2));
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public RouteContext findRouteByRequestId(@NonNull String str) {
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext != null && routeContext.getRequestId().equals(str)) {
            return this.currentRouteContext;
        }
        return getRouteFromBackStack(str);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public RouteContext[] getBackstack() {
        return (RouteContext[]) this.backStack.toArray(new RouteContext[this.backStack.size()]);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public RouteContext getCurrentRoute() {
        return this.currentRouteContext;
    }

    @VisibleForTesting
    boolean getDelegatedProfileState() {
        this.featureServiceV2.observeFeature("ALEXA_BILOBA_ANDROID_MENU_INGRESS", this.carePlusFeatureUpdateListener);
        return this.delegatedProfileState;
    }

    String getDelegationStatus(Message message) {
        JsonParser jsonParser = new JsonParser();
        if (message.getPayloadAsString().length() > 0) {
            try {
                return jsonParser.parse(message.getPayloadAsString()).getAsJsonObject().get("status").getAsString();
            } catch (IllegalStateException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected data type encountered in identity delegation changed event payload: ");
                outline107.append(message.getPayloadAsString());
                Log.e(str, outline107.toString(), e);
                return "";
            }
        }
        return "";
    }

    @VisibleForTesting
    String getRootSegment(@NonNull RouteContext routeContext) {
        String name = routeContext.getRoute().getName();
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        if ("rn".equals(name) || "web".equals(name)) {
            name = routeContext.getString(RouteParameter.ROUTE);
        }
        String[] split = name.split("/");
        if (split.length <= 1 || !"v2".equals(split[0])) {
            return split.length == 0 ? "CANT_PARSE" : removeQueryString(split[0]);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ElementsUtils.V2_PREFIX);
        outline107.append(removeQueryString(split[1]));
        return outline107.toString();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public RouteContext getRouteFromBackStack(@NonNull String str) {
        Iterator<RouteContext> it2 = this.backStack.iterator();
        while (it2.hasNext()) {
            RouteContext next = it2.next();
            if (next.getRequestId().equals(str)) {
                return next;
            }
        }
        return null;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean isRegisteredRouteInterceptor(RoutingService.RouteInterceptor routeInterceptor) {
        return this.routeInterceptors.contains(routeInterceptor);
    }

    public /* synthetic */ RoutingService.RoutingBuilder lambda$match$2$DefaultRoutingService(Route route, Bundle bundle) {
        Route defaultChild = route.getDefaultChild();
        if (defaultChild != null) {
            route = defaultChild;
        }
        return new DefaultRoutingBuilder(route, bundle).with("name", route.getName());
    }

    public /* synthetic */ boolean lambda$subscribeToIdentityDelegationInactiveEvent$0$DefaultRoutingService(Message message) {
        return IdentityEvent.IDENTITY_PROFILE_DELEGATION_CHANGED.equals(message.getEventType()) && getDelegationStatus(message).equals("INACTIVE");
    }

    public /* synthetic */ void lambda$subscribeToIdentityDelegationInactiveEvent$1$DefaultRoutingService(Message message) {
        clearDelegatedProfileRoutesFromBackStack();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public RoutingService.RoutingBuilder match(@NonNull String str) {
        return (RoutingService.RoutingBuilder) matchUriRoute(str, new UriMatcherFactory() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingService$geoHwda7FQpBmwMCFOehTyFGzmE
            @Override // com.amazon.alexa.routing.DefaultRoutingService.UriMatcherFactory
            public final Object create(Route route, Bundle bundle) {
                return DefaultRoutingService.this.lambda$match$2$DefaultRoutingService(route, bundle);
            }
        });
    }

    @Nullable
    <T> T matchUriRoute(@NonNull String str, @NonNull UriMatcherFactory<T> uriMatcherFactory) {
        RouteMatch byUrl = this.routingRegistry.getByUrl(str);
        if (byUrl == null) {
            GeneratedOutlineSupport1.outline158("No match found for ", str);
            return null;
        }
        Bundle params = byUrl.getParams();
        Route route = byUrl.getRoute();
        String str2 = "Matched " + str + " to " + route;
        return uriMatcherFactory.create(route, params);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigate(String str) {
        navigate(str, false);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigateBackward() {
        Route route = null;
        RouteContext pop = this.backStack.isEmpty() ? null : this.backStack.pop();
        Route route2 = pop != null ? pop.getRoute() : null;
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext != null) {
            route = routeContext.getRoute();
        }
        String str = "Attempting to navigate from " + route + " to " + route2 + " with common parent " + RouteApiUtils.findCommonParent(route, route2, this.routingRegistry);
        fireRouteChanged(this.currentRouteContext, pop, 2);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigateReplaceTop(@NonNull RouteContext routeContext) {
        RouteContext routeContext2 = this.currentRouteContext;
        Route route = routeContext2 != null ? routeContext2.getRoute() : null;
        Route route2 = routeContext.getRoute();
        String str = "Attempting to navigate by replacing " + route + " with " + route2 + " with common parent " + RouteApiUtils.findCommonParent(route, route2, this.routingRegistry);
        fireRouteChanged(this.currentRouteContext, routeContext, 5);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigateToExit() {
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext == null) {
            routeContext = new RouteContext(this.routingRegistry.get(RouteName.MAIN));
        } else {
            this.backStack.clear();
            String str = "Attempting to navigate from " + routeContext.getRoute() + " to empty route...";
            this.currentRouteContext = null;
        }
        fireRouteChanged(routeContext, null, 4);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigateUpward() {
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext == null) {
            Log.w(TAG, "There is no current route to navigate upward. Ignoring");
            return;
        }
        Route route = routeContext.getRoute();
        RouteContext routeContext2 = null;
        if (route.is("web")) {
            String string = this.currentRouteContext.getString(RouteParameter.PARENT);
            if (!TextUtils.isEmpty(string)) {
                RouteContext routeContext3 = (RouteContext) matchUriRoute(string, $$Lambda$Iybf9Spgnf6brLyzELWYEFNoc.INSTANCE);
                routeContext2 = routeContext3 == null ? createWebRoute(string, null) : routeContext3;
            }
        } else {
            Route route2 = this.routingRegistry.get(route.getParent());
            if (route2 != null) {
                Route defaultChild = route2.getDefaultChild();
                if (defaultChild != null) {
                    route2 = defaultChild;
                }
                routeContext2 = new RouteContext(route2);
            }
        }
        if (routeContext2 == null) {
            Log.w(TAG, "There is no parent route to navigate to. Ignoring");
            return;
        }
        if (!this.backStack.isEmpty()) {
            RouteContext peek = this.backStack.peek();
            if (peek.getRoute().is("web") && routeContext2.getRoute().is("web")) {
                String string2 = peek.getString(RouteParameter.ROUTE);
                String string3 = routeContext2.getString(RouteParameter.ROUTE);
                if (Objects.equals(string2, string3)) {
                    String str = "Detected parent route " + string3 + " in back stack";
                    navigateBackward();
                    return;
                }
                String string4 = peek.getString(RouteParameter.ROUTE);
                if (overrideParentRoute.contains(string4)) {
                    String str2 = "Override parent route with " + string4 + " from back stack";
                    navigateBackward();
                    return;
                }
            } else if (Objects.equals(peek.getRoute(), routeContext2.getRoute())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Detected parent route ");
                outline107.append(routeContext2.getRoute());
                outline107.append(" in back stack");
                outline107.toString();
                navigateBackward();
                return;
            } else {
                String string5 = peek.getString(RouteParameter.ROUTE);
                if (overrideParentRoute.contains(string5)) {
                    String str3 = "Override parent route with " + string5 + " from back stack";
                    navigateBackward();
                    return;
                }
            }
        }
        String str4 = "Attempting to navigate from " + route + " back to " + routeContext2.getRoute();
        fireRouteChanged(this.currentRouteContext, routeContext2, 3);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void notifyRouteChanged(@NonNull RouteContext routeContext) {
        Iterator it2 = new ArrayList(this.observers).iterator();
        while (it2.hasNext()) {
            ((RoutingObserver) it2.next()).onRouteChanged(routeContext);
        }
    }

    void notifyRouteNavigated(@NonNull RouteContext routeContext, boolean z, boolean z2) {
        if (routeContext.isSameRouteAs(this.currentRouteContext)) {
            String str = "Same route context is current: " + routeContext;
            return;
        }
        if (z2) {
            this.backStack.clear();
        }
        Route route = routeContext.getRoute();
        String str2 = "Navigation state changed by foreign force to " + route;
        RouteContext routeContext2 = this.currentRouteContext;
        if (routeContext2 != null) {
            Route route2 = routeContext2.getRoute();
            Route findCommonParent = RouteApiUtils.findCommonParent(route2, route, this.routingRegistry);
            if (z && !z2) {
                if (findCommonParent != null && findCommonParent.isSingleChildRouteInBackStack()) {
                    String str3 = route2 + " and " + route + " sharing same parent and only single route is allowed to be in stack. " + route2 + " is not saved on back stack.";
                } else {
                    saveRouteContextInBackStack(this.currentRouteContext);
                }
            }
            String str4 = "Will need to leave current route " + route2;
            fireRouteChanged(this.currentRouteContext, routeContext, 6);
        }
        this.currentRouteContext = routeContext;
        notifyRouteChanged(routeContext);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public Observable<RoutePath> onRouteChanged() {
        return this.onRouteChanged.hide();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean popFromBackStack(String str) {
        Route route = this.routingRegistry.get(str);
        Route defaultChild = route.getDefaultChild();
        if (defaultChild != null) {
            route = defaultChild;
        }
        if (!this.backStack.empty() && matchesName(this.backStack.peek(), route.getName())) {
            navigateBackward();
            return true;
        }
        ArrayList<RouteContext> removeRoutesFromBackStack = removeRoutesFromBackStack(route);
        if (removeRoutesFromBackStack.isEmpty()) {
            return false;
        }
        int size = removeRoutesFromBackStack.size() - 1;
        RouteContext routeContext = removeRoutesFromBackStack.get(size);
        removeRoutesFromBackStack.remove(size);
        String str2 = "Rearranging back stack for " + str + ". Found " + routeContext + " and in back stack " + removeRoutesFromBackStack;
        RouteContext routeContext2 = this.currentRouteContext;
        if (routeContext2 != null) {
            saveRouteContextInBackStack(routeContext2);
        }
        this.backStack.addAll(removeRoutesFromBackStack);
        navigate(routeContext, false, false, false);
        return true;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean popUntil(String str) {
        return popUntil(str, false, Bundle.EMPTY);
    }

    @VisibleForTesting
    void publishRouteChangedEvent(RouteContext routeContext, RouteContext routeContext2) {
        HashMap hashMap = new HashMap();
        hashMap.put("RouterService::CurrentInstance", routeContext2);
        hashMap.put("RouterService::PreviousInstance", routeContext);
        this.eventBus.mo358get().publish(new Message.Builder().setEventType(Events.ROUTE_CHANGED_EVENT).setPayload(hashMap.toString()).build());
    }

    @VisibleForTesting
    void recordRouteNotAvailableMetric(RouteContext routeContext, RouteContext routeContext2) {
        String.format("Care giver attempting to navigate to blocked route. Source: %s to Destination: %s ", routeContext.getRoute().getName(), routeContext2.getRoute().getName());
        HashMap hashMap = new HashMap();
        hashMap.put("RouterService::CurrentInstance", routeContext2.toString());
        hashMap.put("RouterService::PreviousInstance", routeContext.toString());
        this.metricsService.recordEvent(RoutingMetricsConstants.ROUTE_NOT_AVAILABLE, RoutingMetricsConstants.ROUTE_NOT_AVAILABLE, hashMap);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void refresh() {
        navigate(getCurrentRoute(), false, false, true);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void registerObserver(@NonNull RoutingObserver routingObserver) {
        this.observers.add(routingObserver);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void registerRouteInterceptor(RoutingService.RouteInterceptor routeInterceptor) {
        this.routeInterceptors.add(routeInterceptor);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public ArrayList<RouteContext> removeRoutesFromBackStack(@NonNull Route route) {
        ArrayList<RouteContext> arrayList = new ArrayList<>();
        Iterator<RouteContext> it2 = this.backStack.iterator();
        while (it2.hasNext()) {
            RouteContext next = it2.next();
            if (RouteApiUtils.isDependentOn(next.getRoute(), route.getName(), this.routingRegistry)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Removing ");
                outline107.append(next.getRoute());
                outline107.append(" from back stack dependent on ");
                outline107.append(route);
                outline107.toString();
                it2.remove();
                arrayList.add(next);
            } else if (!arrayList.isEmpty()) {
                break;
            }
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void removeTopFromBackStack() {
        if (this.backStack.isEmpty()) {
            Log.e(TAG, "Back stack is empty, nothing to pop from");
        } else {
            this.backStack.pop();
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public RouteContext restoreState(@Nullable Bundle bundle) {
        RouteContext routeContext = null;
        if (bundle == null) {
            return null;
        }
        Log.i(TAG, "Restoring routing state from earlier saved state");
        Parcelable[] parcelableArray = bundle.getParcelableArray(ROUTE_BACKSTACK);
        Stack<RouteContext> stack = new Stack<>();
        if (parcelableArray != null) {
            for (Parcelable parcelable : parcelableArray) {
                stack.push((RouteContext) parcelable);
            }
        }
        Parcelable parcelable2 = bundle.getParcelable(CURRENT_ROUTE);
        if (parcelable2 == null) {
            if (!stack.isEmpty()) {
                routeContext = stack.pop();
            }
        } else {
            routeContext = (RouteContext) parcelable2;
        }
        this.backStack = stack;
        return routeContext;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @NonNull
    public RoutingService.RoutingBuilder route(String str) {
        String.format("route(\"%s\")", str);
        Route route = this.routingRegistry.get(str);
        Route defaultChild = route.getDefaultChild();
        if (defaultChild != null) {
            route = defaultChild;
        }
        return new DefaultRoutingBuilder(route);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void saveContentModeToRouteContext(int i) {
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext != null) {
            routeContext.setContentMode(i);
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void saveRouteContextInBackStack(@NonNull RouteContext routeContext) {
        if (!routeContext.getRoute().doNotKeepInBackStack()) {
            this.backStack.push(routeContext);
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    @Nullable
    public Bundle saveState() {
        Log.i(TAG, "Saving routing state for later restoration");
        Bundle bundle = new Bundle();
        if (!this.backStack.empty()) {
            bundle.putParcelableArray(ROUTE_BACKSTACK, (Parcelable[]) this.backStack.toArray(new Parcelable[0]));
        }
        RouteContext routeContext = this.currentRouteContext;
        if (routeContext != null && !routeContext.getRoute().doNotKeepInBackStack()) {
            bundle.putParcelable(CURRENT_ROUTE, this.currentRouteContext);
        }
        return bundle;
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void setCurrentRouteContext(@NonNull RouteContext routeContext, boolean z) {
        RouteContext routeContext2 = this.currentRouteContext;
        if (routeContext2 != null && z) {
            saveRouteContextInBackStack(routeContext2);
        }
        this.currentRouteContext = routeContext;
    }

    @VisibleForTesting
    void setDelegatedProfileState() {
        UserIdentity user = this.identityService.mo358get().getUser("RoutingService");
        boolean z = false;
        if (this.featureServiceV2.hasAccess("ALEXA_BILOBA_ANDROID_MENU_INGRESS", false) && user != null && user.getDelegatedProfile() != null) {
            z = true;
        }
        this.delegatedProfileState = z;
        this.featureServiceV2.unsubscribe(this.carePlusFeatureUpdateListener);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void startLoadingTimeout(Route route) {
        this.loadingTimeout.stop();
        RouteContext routeContext = this.currentRouteContext;
        String upperCase = routeContext == null ? "UNKNOWN" : routeContext.getRoute().getName().toUpperCase();
        String name = route.getName();
        Map<String, Object> timeoutEntries = getTimeoutEntries(upperCase, name, 60);
        Map<String, Object> timeoutEntries2 = getTimeoutEntries(upperCase, name, 180);
        this.loadingTimeout.setEventData(MetricTimeout.Timeout.FIRST, ROUTE_LOADING_TIMEOUT, timeoutEntries, 60);
        this.loadingTimeout.setEventData(MetricTimeout.Timeout.SECOND, ROUTE_LOADING_TIMEOUT, timeoutEntries2, 180);
        this.loadingTimeout.start();
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void stopLoadingTimeout() {
        this.loadingTimeout.stop();
    }

    @VisibleForTesting
    void subscribeToIdentityDelegationInactiveEvent() {
        this.identityDelegationInactiveUuid = this.identityDelegationSubscriber.subscribeFilter(new MessageFilter() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingService$UAZEFLhfN4gAD2FZb0n62Ry6H_I
            @Override // com.amazon.alexa.eventbus.api.MessageFilter
            public final boolean isMatch(Message message) {
                return DefaultRoutingService.this.lambda$subscribeToIdentityDelegationInactiveEvent$0$DefaultRoutingService(message);
            }
        }, new MessageHandler() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingService$k2QuL66yVRg8SsCEBV6u8Rjf1g0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultRoutingService.this.lambda$subscribeToIdentityDelegationInactiveEvent$1$DefaultRoutingService(message);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void unregisterObserver(@NonNull RoutingObserver routingObserver) {
        this.observers.remove(routingObserver);
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void unregisterRouteInterceptor(RoutingService.RouteInterceptor routeInterceptor) {
        this.routeInterceptors.remove(routeInterceptor);
    }

    @VisibleForTesting
    void unsubscribeToIdentityDelegationInactiveEvent() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.identityDelegationInactiveUuid;
        if (filterUuid != null) {
            this.identityDelegationSubscriber.unsubscribeFilter(filterUuid);
            this.identityDelegationInactiveUuid = null;
        }
    }

    private void navigateToNewRoot(String str, @NonNull Bundle bundle, Boolean bool) {
        RoutingService.RoutingBuilder match;
        if (bool.booleanValue()) {
            match = route(str);
        } else {
            match = match(str);
        }
        if (match != null) {
            if (bundle.size() > 0) {
                match.withAll(bundle);
            }
            match.clearBackStack().addToBackStack().navigate();
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigate(String str, boolean z) {
        if (z) {
            route(str).addToBackStack().fromMainMenu().navigate();
        } else {
            route(str).addToBackStack().navigate();
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean popUntil(String str, boolean z, @NonNull Bundle bundle) {
        RouteMatcher routeMatcher = z ? new RouteMatcher() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingService$BaldJWnv0EYep4GtjFVcKTTmZ7s
            @Override // com.amazon.alexa.routing.DefaultRoutingService.RouteMatcher
            public final boolean match(RouteContext routeContext, String str2) {
                boolean matchesName;
                matchesName = DefaultRoutingService.this.matchesName(routeContext, str2);
                return matchesName;
            }
        } : new RouteMatcher() { // from class: com.amazon.alexa.routing.-$$Lambda$DefaultRoutingService$p6AJzFCGb9zn4p1MOgN0csQNHLQ
            @Override // com.amazon.alexa.routing.DefaultRoutingService.RouteMatcher
            public final boolean match(RouteContext routeContext, String str2) {
                boolean matchesUri;
                matchesUri = DefaultRoutingService.this.matchesUri(routeContext, str2);
                return matchesUri;
            }
        };
        List<RouteContext> routesUpTo = getRoutesUpTo(str, routeMatcher);
        int size = this.backStack.size() - routesUpTo.size();
        if (!(size > 0 || (!this.backStack.isEmpty() && routeMatcher.match(this.backStack.get(0), str)))) {
            navigateToNewRoot(str, bundle, Boolean.valueOf(z));
            return false;
        } else if (routesUpTo.size() == 1 && bundle.isEmpty()) {
            navigateBackward();
            return true;
        } else {
            RouteContext routeContext = this.backStack.get(size);
            if (!bundle.isEmpty()) {
                routeContext = new RouteContext(routeContext, bundle);
            }
            ArrayList arrayList = new ArrayList(this.backStack.subList(0, size));
            this.backStack.removeAllElements();
            this.backStack.addAll(arrayList);
            fireRouteChanged(this.currentRouteContext, routeContext, 2);
            notifyRouteChanged(routeContext);
            return true;
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public void navigate(RouteContext routeContext) {
        RouteContext routeContext2 = this.currentRouteContext;
        if (routeContext2 != null && routeContext2.isSameRouteAs(routeContext)) {
            refresh();
        } else {
            navigate(routeContext, true, false, false);
        }
    }

    void navigate(@NonNull RouteContext routeContext, boolean z, boolean z2, boolean z3) {
        navigate(routeContext, z, z2, z3, routeContext.isFromMainMenu());
    }

    @Override // com.amazon.alexa.routing.api.RoutingService
    public boolean isMenuRoute(RouteContext routeContext) {
        return isMenuRoute(routeContext.getRoute().getName());
    }

    void navigate(@NonNull RouteContext routeContext, boolean z, boolean z2, boolean z3, boolean z4) {
        String str;
        if (getDelegatedProfileState() && !routeContext.getRoute().supportsDelegatedProfile()) {
            recordRouteNotAvailableMetric(this.currentRouteContext, routeContext);
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("In delegated profile, but route is not supported ");
            outline107.append(routeContext.getRoute().getName());
            Log.w(str2, outline107.toString());
            return;
        }
        routeContext.setFromMainMenu(z4);
        if (!this.routeInterceptors.isEmpty()) {
            for (RoutingService.RouteInterceptor routeInterceptor : this.routeInterceptors) {
                if (!routeInterceptor.onRouteChanging(routeContext)) {
                    String str3 = "Route was blocked by the interceptor: " + routeContext;
                    return;
                }
            }
        }
        Route route = routeContext.getRoute();
        if (!z3 && routeContext.isSameRouteAs(this.currentRouteContext)) {
            str = "Same route context is current: " + routeContext;
            return;
        }
        RouteContext routeContext2 = this.currentRouteContext;
        Route route2 = routeContext2 != null ? routeContext2.getRoute() : null;
        Route route3 = routeContext.getRoute();
        if (!RouteApiUtils.hasParent(route3, "conversations", this.routingRegistry)) {
            startLoadingTimeout(route3);
        }
        RouteContext routeContext3 = this.currentRouteContext;
        if (z2) {
            this.backStack.clear();
            this.currentRouteContext = null;
        }
        Route findCommonParent = RouteApiUtils.findCommonParent(route2, route, this.routingRegistry);
        Log.i(TAG, "Attempting to navigate from " + route2 + " to " + route + " with common parent " + findCommonParent);
        fireRouteChanged(routeContext3, routeContext, z ? 1 : 0);
    }
}
