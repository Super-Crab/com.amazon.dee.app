package com.amazon.dee.app.elements.bridges;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.RouteFeatureGroupRegistry;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingObserver;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.elements.ElementsRouteHolder;
import com.amazon.dee.app.elements.ReactFeatureManager;
import com.amazon.dee.app.elements.ReactRoute;
import com.amazon.dee.app.elements.ReactRouteRegistry;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.main.MainActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
@ReactModule(name = "NativeNavigation")
/* loaded from: classes12.dex */
public class NativeNavigationModule extends ReactContextBaseJavaModule implements RoutingObserver, LifecycleEventListener {
    static final String ADD = "add";
    static final String ARGS_KEY = "args";
    static final String BACK = "back";
    static final String BACK_BY_NAME = "backByName";
    static final String CAN_ROUTE_BACK = "canRouteBack";
    static final String CURRENT = "current";
    static final String HISTORY = "history";
    static final String HISTORY_KEY = "history";
    static final String IS_ELEMENTS_ACTIVE = "isElementsActive";
    static final String MODULE_NAME = "NativeNavigationModule";
    static final String NAVIGATION_STATE_CHANGED = "navigationStateChanged";
    static final String NONE = "none";
    static final String OPTIONS_KEY = "options";
    static final String REPLACE_ALL = "replaceAll";
    static final String REPLACE_TOP = "replaceTop";
    static final String REQUEST_ID_KEY = "requestId";
    static final String ROUTE_KEY = "route";
    static final String SUCCESS_KEY = "success";
    private static final String TAG = Log.tag(NativeNavigationModule.class);
    static final String URI_KEY = "uri";
    static final String V2_PREFIX = "v2/";
    final CollectionsFactory collectionsFactory;
    DeviceEventManagerModule.RCTDeviceEventEmitter emitter;
    final RouteFeatureGroupRegistry groupRoutes;
    private final Handler mainHandler;
    final HashMap<String, Promise> promises;
    final Lazy<ReactFeatureManager> reactFeatureManagerRef;
    final ReactRouteRegistry reactRoutes;
    final RoutingRegistry routes;
    final RoutingService routingService;

    /* renamed from: com.amazon.dee.app.elements.bridges.NativeNavigationModule$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public NativeNavigationModule(ReactApplicationContext reactApplicationContext, CollectionsFactory collectionsFactory, RoutingService routingService, Lazy<ReactFeatureManager> lazy, RoutingRegistry routingRegistry, RouteFeatureGroupRegistry routeFeatureGroupRegistry, ReactRouteRegistry reactRouteRegistry) {
        super(reactApplicationContext);
        this.collectionsFactory = collectionsFactory;
        this.routingService = routingService;
        this.reactFeatureManagerRef = lazy;
        this.promises = new HashMap<>();
        this.routes = routingRegistry;
        this.groupRoutes = routeFeatureGroupRegistry;
        this.reactRoutes = reactRouteRegistry;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    private void didFailRouteTo(String str, String str2, String str3) {
        Promise promise = this.promises.get(str);
        if (promise != null) {
            this.promises.remove(str);
            promise.reject(str2, str3);
        }
    }

    private void didRouteTo(String str) {
        Promise promise = this.promises.get(str);
        if (promise != null) {
            this.promises.remove(str);
            promise.resolve(true);
        }
    }

    private WritableMap extractParameters(RouteContext routeContext) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        for (String str : routeContext.getNames()) {
            Object obj = routeContext.get(str);
            if (obj instanceof String) {
                writableNativeMap.putString(str, (String) obj);
            }
        }
        return writableNativeMap;
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter() {
        if (this.emitter == null) {
            this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        }
        return this.emitter;
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    private void onUIThread(Runnable runnable, boolean z) {
        if (getCurrentActivity() != null) {
            getCurrentActivity().runOnUiThread(runnable);
        } else if (!z) {
            Log.e(TAG, "Activity was null. Posting as a message instead.");
            this.mainHandler.post(runnable);
        } else {
            Log.e(TAG, "Activity was null. Not doing anything.");
        }
    }

    private WritableMap serializeRouteContext(RouteContext routeContext) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("name", routeContext.getRoute().getName());
        writableNativeMap2.putString("uri", routeContext.toUri());
        writableNativeMap.putMap("route", writableNativeMap2);
        writableNativeMap.putMap("data", extractParameters(routeContext));
        writableNativeMap.putString("options", "add");
        return writableNativeMap;
    }

    private WritableArray serializeRouteContexts(RouteContext[] routeContextArr) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (RouteContext routeContext : routeContextArr) {
            writableNativeArray.pushMap(serializeRouteContext(routeContext));
        }
        return writableNativeArray;
    }

    @ReactMethod
    public void addRoutes(@NonNull final ReadableArray readableArray, @NonNull final Promise promise) {
        onUIThread(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$NativeNavigationModule$bO4z-IB12286CnCjL1XXTy4cEZo
            @Override // java.lang.Runnable
            public final void run() {
                NativeNavigationModule.this.lambda$addRoutes$2$NativeNavigationModule(readableArray, promise);
            }
        }, false);
    }

    @VisibleForTesting
    Bundle extractArgs(ReadableMap readableMap, String str) {
        if (readableMap.getType("args") != ReadableType.Map) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ERROR: Invalid type (");
            outline107.append(readableMap.getType("args"));
            outline107.append(") for route args from react for route: ");
            outline107.append(str);
            Log.i(str2, outline107.toString());
            return new Bundle();
        }
        ReadableMap mo6945getMap = readableMap.mo6945getMap("args");
        Bundle bundle = new Bundle();
        if (mo6945getMap != null) {
            ReadableMapKeySetIterator keySetIterator = mo6945getMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                int ordinal = mo6945getMap.getType(nextKey).ordinal();
                if (ordinal == 1) {
                    bundle.putString(nextKey, String.valueOf(mo6945getMap.getBoolean(nextKey)));
                } else if (ordinal == 2) {
                    bundle.putString(nextKey, String.valueOf(mo6945getMap.getInt(nextKey)));
                } else if (ordinal != 3) {
                    Log.i(TAG, "route arg was not a primitive");
                } else {
                    bundle.putString(nextKey, mo6945getMap.getString(nextKey));
                }
            }
        }
        return bundle;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @NonNull
    @VisibleForTesting
    WritableMap getNavigationState() {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putBoolean(CAN_ROUTE_BACK, this.routingService.canNavigateBackward());
        createMap.putArray("history", serializeRouteContexts(this.routingService.getBackstack()));
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        if (currentRoute != null) {
            Route route = currentRoute.getRoute();
            createMap.putMap(CURRENT, serializeRouteContext(currentRoute));
            int adapterId = route.getAdapterId();
            boolean z = true;
            if (adapterId != 1) {
                z = false;
            }
            createMap.putBoolean(IS_ELEMENTS_ACTIVE, z);
        }
        return createMap;
    }

    public /* synthetic */ void lambda$addRoutes$2$NativeNavigationModule(ReadableArray readableArray, Promise promise) {
        try {
            Iterator<Object> it2 = readableArray.toArrayList().iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                if (next instanceof Map) {
                    this.reactRoutes.add(new ReactRoute((Map) next));
                } else {
                    promise.reject("Cannot parse route array", "InvalidArgument");
                    return;
                }
            }
            this.reactRoutes.update();
            promise.resolve(true);
        } catch (IllegalArgumentException e) {
            promise.reject(e);
        }
    }

    public /* synthetic */ void lambda$routeBack$1$NativeNavigationModule(Promise promise) {
        this.routingService.navigateBackward();
        promise.resolve(true);
    }

    public /* synthetic */ void lambda$routeTo$0$NativeNavigationModule(ReadableMap readableMap, Promise promise) {
        RoutingService.RoutingBuilder match;
        if (!readableMap.hasKey("route")) {
            Log.e(TAG, "[ERROR] Cannot route to unspecified route");
            promise.reject("paramErr", "route not found");
            return;
        }
        ReadableMap mo6945getMap = readableMap.mo6945getMap("route");
        ReadableMap readableMap2 = null;
        if (readableMap.hasKey("options")) {
            readableMap2 = readableMap.mo6945getMap("options");
        }
        String string = (readableMap2 == null || !readableMap2.hasKey("history")) ? "add" : readableMap2.getString("history");
        boolean equals = string.equals(BACK_BY_NAME);
        String str = equals ? "name" : "uri";
        if (!mo6945getMap.hasKey(str)) {
            Log.e(TAG, "[ERROR] Cannot route to route without uri");
            promise.reject("paramErr", "route uri not found");
            return;
        }
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[ROUTE] routeTo: ");
        outline107.append(mo6945getMap.getString(str));
        Log.i(str2, outline107.toString());
        String uuid = UUID.randomUUID().toString();
        if (this.promises.containsKey(uuid)) {
            Log.w(TAG, "Already servicing requestID " + uuid + "! Duplicate bridge call?");
        }
        this.promises.put(uuid, promise);
        String decode = Uri.decode(mo6945getMap.getString(str));
        if (decode.equals(RouteName.MENU)) {
            final MainActivity mainActivity = (MainActivity) getCurrentActivity();
            if (mainActivity == null) {
                return;
            }
            mainActivity.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$KjR3UFf5KBeJZWE0_PutJ8LlHBc
                @Override // java.lang.Runnable
                public final void run() {
                    MainActivity.this.onMenuClicked();
                }
            });
            return;
        }
        if (equals) {
            match = this.routingService.route(decode);
        } else {
            match = this.routingService.match(decode);
        }
        if (match == null) {
            if (decode.startsWith("v2/")) {
                match = this.routingService.route("rn");
                new ElementsRouteHolder(decode).configure(match);
            } else {
                match = this.routingService.route("web").with(RouteParameter.ROUTE, decode);
            }
        }
        if (match == null) {
            Log.e(TAG, String.format("[ERROR] Unrecognized url %s", decode));
            didFailRouteTo(uuid, "failed route to", "unrecognized uri");
            return;
        }
        match.withRequestId(uuid);
        if (mo6945getMap.hasKey("args")) {
            match.withAll(extractArgs(mo6945getMap, decode));
        }
        String.format("HISTORY_KEY : %s", string);
        char c = 65535;
        switch (string.hashCode()) {
            case -1233067443:
                if (string.equals(REPLACE_ALL)) {
                    c = 1;
                    break;
                }
                break;
            case -1233049087:
                if (string.equals(REPLACE_TOP)) {
                    c = 2;
                    break;
                }
                break;
            case 96417:
                if (string.equals("add")) {
                    c = 0;
                    break;
                }
                break;
            case 3015911:
                if (string.equals(BACK)) {
                    c = 3;
                    break;
                }
                break;
            case 3387192:
                if (string.equals("none")) {
                    c = 5;
                    break;
                }
                break;
            case 1908957001:
                if (string.equals(BACK_BY_NAME)) {
                    c = 4;
                    break;
                }
                break;
        }
        if (c == 0) {
            match.addToBackStack();
            match.navigate();
        } else if (c == 1) {
            match.clearBackStack();
            match.addToBackStack();
            match.navigate();
        } else if (c == 2) {
            match.navigateReplaceTop();
        } else if (c == 3) {
            match.popUntil();
        } else if (c == 4) {
            match.popUntil(true);
        } else if (c != 5) {
            match.addToBackStack();
            match.navigate();
        } else {
            match.navigate();
        }
        didRouteTo(uuid);
    }

    @ReactMethod
    public void navigationState(Promise promise) {
        promise.resolve(getNavigationState());
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.routingService.unregisterObserver(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.routingService.registerObserver(this);
    }

    @Override // com.amazon.alexa.routing.api.RoutingObserver
    public void onRouteChanged(@NonNull RouteContext routeContext) {
        if (!getReactApplicationContext().hasActiveCatalystInstance()) {
            Log.w(TAG, "React context not fully initialized. Not doing anything.");
            return;
        }
        getEventEmitter().emit(NAVIGATION_STATE_CHANGED, getNavigationState());
    }

    @ReactMethod
    public void routeBack(final Promise promise) {
        onUIThread(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$NativeNavigationModule$f2sMrCMnZ6cwu6akjHqnltcKRCs
            @Override // java.lang.Runnable
            public final void run() {
                NativeNavigationModule.this.lambda$routeBack$1$NativeNavigationModule(promise);
            }
        }, true);
    }

    @ReactMethod
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void routeNotify(ReadableMap readableMap) {
        String str;
        if (!readableMap.hasKey("success")) {
            Log.e(TAG, "[ERROR] routeNotify called without success result");
            return;
        }
        boolean z = readableMap.getBoolean("success");
        if (!readableMap.hasKey("requestId")) {
            Log.e(TAG, "[ERROR] routeNotify called without requestID");
            return;
        }
        String string = readableMap.getString("requestId");
        if (z) {
            didRouteTo(string);
        } else {
            didFailRouteTo(string, "route not found", "could not route to location");
            if (readableMap.hasKey("route")) {
                ReadableMap mo6945getMap = readableMap.mo6945getMap("route");
                if (mo6945getMap.hasKey("uri")) {
                    str = mo6945getMap.getString("uri");
                    String str2 = TAG;
                    Log.e(str2, "[ERROR] Error occurred loading react native route (route not found): " + str);
                }
            }
            str = "";
            String str22 = TAG;
            Log.e(str22, "[ERROR] Error occurred loading react native route (route not found): " + str);
        }
        ReactFeatureManager mo358get = this.reactFeatureManagerRef.mo358get();
        if (mo358get == null) {
            return;
        }
        mo358get.onFeatureRendered(string);
    }

    @ReactMethod
    public void routeTo(final ReadableMap readableMap, final Promise promise) {
        onUIThread(new Runnable() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$NativeNavigationModule$elUZhyTytr9JaOucyb61DLWJASI
            @Override // java.lang.Runnable
            public final void run() {
                NativeNavigationModule.this.lambda$routeTo$0$NativeNavigationModule(readableMap, promise);
            }
        }, true);
    }

    @ReactMethod
    public void setRouteState(@Nullable String str, ReadableMap readableMap, Promise promise) {
        if (str == null) {
            promise.resolve(null);
            return;
        }
        RouteContext findRouteByRequestId = this.routingService.findRouteByRequestId(str);
        if (findRouteByRequestId != null) {
            for (Map.Entry<String, Object> entry : readableMap.toHashMap().entrySet()) {
                findRouteByRequestId.setStatePair(entry.getKey(), entry.getValue());
            }
        } else {
            String str2 = TAG;
            Log.w(str2, "setRouteState() could not find route with request ID " + str);
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void unregisterRoute(String str, Promise promise) {
        try {
            this.routes.unregister(this.routes.get(str));
            promise.resolve(true);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Error: Route is not registered");
            promise.reject(e);
        }
    }
}
