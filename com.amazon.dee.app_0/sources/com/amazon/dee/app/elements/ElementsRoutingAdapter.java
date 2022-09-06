package com.amazon.dee.app.elements;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.UserPerceivedLatencyService;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Collections;
/* loaded from: classes12.dex */
public class ElementsRoutingAdapter implements RoutingAdapter {
    private static final String LOG_TAG = "com.amazon.dee.app.elements.ElementsRoutingAdapter";
    SimpleArrayMap<String, RoutingAdapter.RouteConfiguration> configurations = new SimpleArrayMap<>();
    Context context;
    IdentityService identityService;
    Handler mainHandler;
    Disposable onReactBridgeStopped;
    ReactFeatureManager reactFeatureManager;
    Router router;
    ReactNativeViewDelegate viewDelegate;

    public ElementsRoutingAdapter(Context context, ReactNativeViewDelegate reactNativeViewDelegate, Router router, ReactFeatureManager reactFeatureManager, IdentityService identityService, ReactBridgeService reactBridgeService) {
        this.context = context;
        this.viewDelegate = reactNativeViewDelegate;
        this.router = router;
        this.reactFeatureManager = reactFeatureManager;
        this.identityService = identityService;
        this.configurations.put(RouteName.SMART_HOME, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ACCESSORY_SETUP, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.HANDSFREE_SETTINGS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.LANGUAGE_SETTINGS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("rn", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.MUSIC_ELEMENTS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_DISCOVER, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_DEVICE_CONTROL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_DEVICE_SETTING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_CMB_SETUP, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_NROOP_COMPLETE, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_FFS_WIFI_CONNECTING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_FFS_LANDING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_FFS_NEW_DEVICE_FOUND, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_RED_ROCK_HISTORY, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.SMART_HOME_RED_ROCK_SETUP, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/smart-home/lemur-redirect/list", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/smart-home/lemur-redirect/create", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/behaviors", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/hunches", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/device-settings", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_DEVICE_SETTINGS_DEEP_LINK, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_SETTINGS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_SETTINGS_DEEP_LINK, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_SETTINGS_3P_ACCOUNT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.THINGS_TO_TRY_ELEMENTS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/guided-discovery/add-devices", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/homefeed", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.NOW_PLAYING_ELEMENTS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.REACT_NATIVE_COMMS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.COMMUNICATIONS_SETTING, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/reminders-alarms-timers", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ALARMS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.REMINDERS, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("timers", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_REMINDERS_DETAIL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_ALARM_EDIT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_TIMER_DETAIL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_A2S, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/lists", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/lists/archived", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_DEFAULT_LISTS_DETAIL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.ELEMENTS_NAMED_LISTS_DETAIL, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CHANNELS_DEVICES, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.CHANNELS_ENTERTAINMENT, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe-speaker-pairing", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/setupSpeaker", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/setupSpeakerBluetooth", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/speakerOptions", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/forcedWaitVideo", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/ftue-startup", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put(RouteName.VIDEO, RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/postOobeFtue", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/devices-channel/control-panel/space", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("locations/index", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/alexa-oobe/drivemode-ftue-startup", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("v2/settings/notifications/drive-mode", RoutingAdapter.RouteConfiguration.all());
        this.configurations.put("elements-accessory-echo-auto/oobe/driver-interaction-warning", RoutingAdapter.RouteConfiguration.all());
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.onReactBridgeStopped = reactBridgeService.onStateChanged().doOnComplete(new Action() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$FKY9I5mjzB25RZWeZq7OAHVU2UI
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                ElementsRoutingAdapter.this.lambda$new$0$ElementsRoutingAdapter();
            }
        }).subscribe(new Consumer() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$RMuI8YK8NLuLI_YEHQUmMUX0bwM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ElementsRoutingAdapter.this.lambda$new$1$ElementsRoutingAdapter((Integer) obj);
            }
        }, $$Lambda$ElementsRoutingAdapter$2My4Tft0QN5gkI2OW50G2A78Oik.INSTANCE);
    }

    private ControllerTransaction generateControllerTransaction(RouteContext routeContext, Runnable runnable) {
        ReactFeature reactFeatureFromRouteContext = ElementsUtils.getReactFeatureFromRouteContext(routeContext);
        Route.Theme loggedOnUsersEffectiveTheme = ElementsUtils.getLoggedOnUsersEffectiveTheme(this.context, this.identityService.getUser(LOG_TAG), routeContext.getRoute());
        if (loggedOnUsersEffectiveTheme != null && reactFeatureFromRouteContext.getLaunchOptions() != null) {
            reactFeatureFromRouteContext.getLaunchOptions().putString(ElementsRouteKeys.THEME, loggedOnUsersEffectiveTheme.toString());
        }
        ReactFeatureViewController create = ReactFeatureViewController.create(reactFeatureFromRouteContext);
        ReactFeatureControllerTransition reactFeatureControllerTransition = new ReactFeatureControllerTransition(reactFeatureFromRouteContext, runnable);
        reactFeatureControllerTransition.setReactNativeViewDelegate(this.viewDelegate);
        return new ControllerTransaction(create, reactFeatureControllerTransition, routeContext.getRequestId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$2(Throwable th) throws Throwable {
        Log.e(LOG_TAG, "Error occurred when receiving react bridge state", th);
        throw new Exception("Error occurred when receiving react bridge state", th);
    }

    public void clearBackstack() {
        this.router.setBackstack(Collections.EMPTY_LIST);
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull final Route route, final Route route2) {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$sANBfzFpPwIQSCwcuVg4SkSrcP4
            @Override // java.lang.Runnable
            public final void run() {
                ElementsRoutingAdapter.this.lambda$enter$5$ElementsRoutingAdapter(route2, route);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$i4CDz3AEfaToQIoeXBOkiX_7DWM
            @Override // java.lang.Runnable
            public final void run() {
                ElementsRoutingAdapter.this.lambda$exit$7$ElementsRoutingAdapter();
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return this.configurations.get(route.getName());
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 1;
    }

    public /* synthetic */ void lambda$enter$5$ElementsRoutingAdapter(Route route, Route route2) {
        if (route != null && !route.isReactNative()) {
            this.viewDelegate.setPendingViewIsReactNative(true);
        } else {
            this.viewDelegate.setReactNativeActive(true);
        }
        new Object[1][0] = route2;
    }

    public /* synthetic */ void lambda$exit$7$ElementsRoutingAdapter() {
        this.router.popCurrentController();
        ReactFeatureViewController reactFeatureViewController = (ReactFeatureViewController) this.router.getCurrentController();
        if (reactFeatureViewController != null) {
            this.reactFeatureManager.onFeatureBackgrounded(reactFeatureViewController.reactFeature);
        }
    }

    public /* synthetic */ void lambda$leave$6$ElementsRoutingAdapter(Route route, Route route2) {
        if (route != null && !route.isReactNative()) {
            this.viewDelegate.setReactNativeActive(false);
        }
        UserPerceivedLatencyService.cancelColdStartTimer();
        new Object[1][0] = route2;
    }

    public /* synthetic */ void lambda$navigate$3$ElementsRoutingAdapter(RouteContext routeContext, Runnable runnable) {
        this.router.replaceCurrentController(generateControllerTransaction(routeContext, runnable));
    }

    public /* synthetic */ void lambda$new$0$ElementsRoutingAdapter() throws Throwable {
        this.onReactBridgeStopped.dispose();
    }

    public /* synthetic */ void lambda$new$1$ElementsRoutingAdapter(Integer num) throws Throwable {
        if (num.intValue() == 2) {
            clearBackstack();
        }
    }

    public /* synthetic */ void lambda$reenter$4$ElementsRoutingAdapter() {
        ReactFeature reactFeature;
        ReactFeatureViewController reactFeatureViewController = (ReactFeatureViewController) this.router.getCurrentController();
        if (reactFeatureViewController != null && (reactFeature = reactFeatureViewController.reactFeature) != null) {
            this.reactFeatureManager.onFeatureForegrounded(reactFeature);
        }
        this.viewDelegate.updateIsReactNative();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull final Route route, final Route route2) {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$Rysf6MvInDI0ZPz48gxa2A6rNI0
            @Override // java.lang.Runnable
            public final void run() {
                ElementsRoutingAdapter.this.lambda$leave$6$ElementsRoutingAdapter(route2, route);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull final RouteContext routeContext, final Runnable runnable) {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$mJU2ijMpzjG5QvmEOiZs7T8yw2g
            @Override // java.lang.Runnable
            public final void run() {
                ElementsRoutingAdapter.this.lambda$navigate$3$ElementsRoutingAdapter(routeContext, runnable);
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ElementsRoutingAdapter$Ip3GmYcWQ8EEO_PtTTHtd7NdYTA
            @Override // java.lang.Runnable
            public final void run() {
                ElementsRoutingAdapter.this.lambda$reenter$4$ElementsRoutingAdapter();
            }
        });
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
