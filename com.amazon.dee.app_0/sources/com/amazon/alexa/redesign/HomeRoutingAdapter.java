package com.amazon.alexa.redesign;

import android.os.Handler;
import android.os.Looper;
import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.redesign.repository.EarlyEventBusMessageRepository;
import com.amazon.alexa.redesign.subscriber.EarlyEventBusMessageSubscriber;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.LatencyInteractor;
import com.amazon.alexa.redesign.view.HomeRedesignViewController;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class HomeRoutingAdapter implements RoutingAdapter, ViewControllerToRoutingInterface {
    private static final String TAG = "HomeRoutingAdapter";
    private final ArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new ArrayMap<>();
    private EarlyEventBusMessageRepository earlyEventBusMessageRepository;
    private EarlyEventBusMessageSubscriber earlyEventBusMessageSubscriber;
    private EventBus eventBus;
    private ViewController homeViewController;
    private final HomeViewDelegate homeViewDelegate;
    private final LatencyInfra latencyInfra;
    private final Router router;
    private Subscriber.SubscriberUuid subscriberUuid;

    public HomeRoutingAdapter(HomeViewDelegate homeViewDelegate, Router router, boolean z) {
        this.router = router;
        this.homeViewDelegate = homeViewDelegate;
        this.configurations.put(RouteName.HOME, RoutingAdapter.RouteConfiguration.all());
        Constants.AutomationConstants.isQABuild = z;
        this.eventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
        this.latencyInfra = (LatencyInfra) GeneratedOutlineSupport1.outline20(LatencyInfra.class);
        this.earlyEventBusMessageRepository = new EarlyEventBusMessageRepository();
        this.earlyEventBusMessageSubscriber = new EarlyEventBusMessageSubscriber(this.eventBus, this.earlyEventBusMessageRepository);
    }

    private boolean getLogoutFlagFromController(ViewController viewController) {
        if (viewController instanceof HomeRedesignViewController) {
            return ((HomeRedesignViewController) viewController).getLogoutFlag();
        }
        return false;
    }

    private void handleLogOut() {
        ViewController viewController = this.homeViewController;
        if (viewController == null || !getLogoutFlagFromController(viewController)) {
            return;
        }
        Router router = this.router;
        if (router != null && router.getBackStackSize() > 0) {
            this.router.popController(this.homeViewController);
        }
        this.homeViewController = null;
    }

    private void onUserChanged() {
        if (((IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class)).getUser("HomeRoutingAdapter") == null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.-$$Lambda$HomeRoutingAdapter$kssAtJIYWRt2h2-Qs9w23G4aAgY
                @Override // java.lang.Runnable
                public final void run() {
                    HomeRoutingAdapter.this.lambda$onUserChanged$2$HomeRoutingAdapter();
                }
            });
        }
    }

    @VisibleForTesting
    void createUserChangedEventBus() {
        if (this.subscriberUuid == null) {
            MultiFilterSubscriber newSubscriber = this.eventBus.getNewSubscriber();
            this.subscriberUuid = newSubscriber.getSubscriberUuid();
            newSubscriber.subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.redesign.-$$Lambda$HomeRoutingAdapter$exNsbsBIK6jU-dKqXfTspa_b_1k
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    HomeRoutingAdapter.this.lambda$createUserChangedEventBus$1$HomeRoutingAdapter(message);
                }
            });
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
        LatencyInfra latencyInfra = this.latencyInfra;
        Router router = this.router;
        LatencyInteractor.initProfileTimeline(latencyInfra, router != null && router.getBackStackSize() == 0);
        handleLogOut();
        if (this.homeViewController == null) {
            this.homeViewController = getViewController();
        }
        Router router2 = this.router;
        if (router2 != null && router2.getBackStackSize() == 0) {
            try {
                this.router.pushController(new ControllerTransaction(this.homeViewController));
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        Router router3 = this.router;
        if (router3 != null && router3.getCurrentController() != null) {
            ViewController currentController = this.router.getCurrentController();
            if ((currentController instanceof HomeRedesignViewController) && !currentController.isAttached()) {
                ((HomeRedesignViewController) currentController).updatePreviousRoute(route2 == null ? "" : route2.getName());
                this.router.attachCurrentController();
            }
        }
        Log.w("HomeRoutingAdapter", "Setting homeActive to true");
        HomeViewDelegate homeViewDelegate = this.homeViewDelegate;
        if (homeViewDelegate != null) {
            homeViewDelegate.setHomeActive(true);
        }
        createUserChangedEventBus();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
        if (this.homeViewController instanceof HomeRedesignViewController) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.-$$Lambda$HomeRoutingAdapter$WU7dD5nD7PJn7KKGhvKDqzHDZss
                @Override // java.lang.Runnable
                public final void run() {
                    HomeRoutingAdapter.this.lambda$exit$0$HomeRoutingAdapter();
                }
            });
        }
        Log.w("HomeRoutingAdapter", "Setting homeActive to false");
        HomeViewDelegate homeViewDelegate = this.homeViewDelegate;
        if (homeViewDelegate != null) {
            homeViewDelegate.setHomeActive(false);
        }
        unsubscribeUserChangedEventBus();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 6;
    }

    @VisibleForTesting
    public ViewController getViewController() {
        return new HomeRedesignViewController().setRoutingInterface(this).setEarlyEventBusMessageRepository(this.earlyEventBusMessageRepository);
    }

    public /* synthetic */ void lambda$createUserChangedEventBus$1$HomeRoutingAdapter(Message message) {
        onUserChanged();
    }

    public /* synthetic */ void lambda$exit$0$HomeRoutingAdapter() {
        Router router = this.router;
        if (router != null) {
            router.detachCurrentController();
        }
    }

    public /* synthetic */ void lambda$onUserChanged$2$HomeRoutingAdapter() {
        if (this.homeViewController != null) {
            Router router = this.router;
            if (router != null && router.getBackStackSize() > 0) {
                this.router.popController(this.homeViewController);
            }
            this.homeViewController = null;
        }
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        handleLogOut();
        if (this.homeViewController == null) {
            this.homeViewController = getViewController();
        }
        runnable.run();
        Router router = this.router;
        if (router != null && router.getBackStackSize() == 0) {
            try {
                this.router.pushController(new ControllerTransaction(this.homeViewController));
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        Router router2 = this.router;
        if (router2 != null && router2.getCurrentController() != null) {
            ViewController currentController = this.router.getCurrentController();
            if ((currentController instanceof HomeRedesignViewController) && !currentController.isAttached()) {
                this.router.attachCurrentController();
            }
        }
        HomeViewDelegate homeViewDelegate = this.homeViewDelegate;
        if (homeViewDelegate != null) {
            homeViewDelegate.setHomeActive(true);
        }
        createUserChangedEventBus();
    }

    @Override // com.amazon.alexa.redesign.ViewControllerToRoutingInterface
    public void notifyEarlyEventBusMessagesProcessed() {
        this.earlyEventBusMessageSubscriber.unsubscribe();
        this.earlyEventBusMessageRepository.clear();
    }

    @Override // com.amazon.alexa.redesign.ViewControllerToRoutingInterface
    public void notifyRoutingAdapterOnPause() {
        unsubscribeUserChangedEventBus();
    }

    @Override // com.amazon.alexa.redesign.ViewControllerToRoutingInterface
    public void notifyRoutingAdapterOnResume() {
        createUserChangedEventBus();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }

    @VisibleForTesting
    void unsubscribeUserChangedEventBus() {
        Subscriber.SubscriberUuid subscriberUuid = this.subscriberUuid;
        if (subscriberUuid != null) {
            this.eventBus.unsubscribe(subscriberUuid);
            this.subscriberUuid = null;
        }
    }
}
