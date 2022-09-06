package com.amazon.dee.app.elements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.elements.api.InitializationPhase;
import com.amazon.dee.app.elements.bridges.PingHandlerModule;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.tabLayout.TabLayoutStatusService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import rx.Subscription;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class SharedInstanceReactFeatureManager implements ReactFeatureManager {
    public static final String APP_STATE_CHANGE_EVENT = "appStateDidChange";
    public static final String APP_STATE_CHANGE_KEY = "app_state";
    private static final String EVENT_NAME = "foregroundStateChanged";
    private static final String IN_FOREGROUND = "inForeground";
    private static final String LOG_TAG = Log.tag(SharedInstanceReactFeatureManager.class);
    private static final String REQUEST_ID = "requestId";
    Activity activity;
    DefaultHardwareBackBtnHandler backBtnHandler;
    BridgeStatusService bridgeStatusService;
    CollectionsFactory collectionsFactory;
    DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    Handler mainHandler;
    MetricsServiceV2 metricsServiceV2;
    private final ReactBridgeService reactBridgeService;
    boolean reactDeveloperSupportEnabled;
    WeakHashMap<ReactFeature, WeakReference<OnRenderedListener>> reactFeatureRenderedCallbacks;
    Map<ReactFeature, ReactRootView> reactFeatureViews;
    Multimap<ReactFeatureState, ReactFeature> reactFeatures;
    ReactInstanceManager reactInstanceManager;
    Subscription subscription;
    TabLayoutStatusService tabLayoutStatusService;
    private final Object lock = new Object();
    PingHandlerModule pingHandlerModule = null;
    ReactInstanceManager.ReactInstanceEventListener listener = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public enum ReactFeatureState {
        RESUMED,
        BACKGROUNDED
    }

    public SharedInstanceReactFeatureManager(@NonNull Activity activity, @NonNull DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, @NonNull CollectionsFactory collectionsFactory, @NonNull ReactInstanceManager reactInstanceManager, @NonNull BridgeStatusService bridgeStatusService, @NonNull MetricsServiceV2 metricsServiceV2, boolean z, @NonNull TabLayoutStatusService tabLayoutStatusService, @NonNull ReactBridgeService reactBridgeService) {
        this.bridgeStatusService = null;
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(defaultHardwareBackBtnHandler);
        Preconditions.checkNotNull(collectionsFactory);
        Preconditions.checkNotNull(reactInstanceManager);
        this.activity = activity;
        this.backBtnHandler = defaultHardwareBackBtnHandler;
        this.collectionsFactory = collectionsFactory;
        this.reactInstanceManager = reactInstanceManager;
        this.bridgeStatusService = bridgeStatusService;
        this.reactFeatures = HashMultimap.create();
        this.reactFeatureViews = new WeakHashMap();
        this.reactFeatureRenderedCallbacks = new WeakHashMap<>();
        this.reactDeveloperSupportEnabled = z;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.metricsServiceV2 = metricsServiceV2;
        this.tabLayoutStatusService = tabLayoutStatusService;
        this.reactBridgeService = reactBridgeService;
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    private void changeFeatureForegroundStatus(@NonNull ReactFeature reactFeature, boolean z) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putBoolean(IN_FOREGROUND, z);
        createMap.putString("requestId", reactFeature.getInstanceId());
        if (this.reactInstanceManager.getCurrentReactContext() != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT_NAME, createMap);
        }
    }

    private void emitReactNativeEvent(@NonNull ReactContext reactContext, String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    @Nullable
    private ReactRootView getViewForFeature(ReactFeature reactFeature) {
        return this.reactFeatureViews.get(reactFeature);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onFeatureRendered$0(ReactFeature reactFeature, OnRenderedListener onRenderedListener) {
        String str = "Invoking onRendered listener for feature " + reactFeature;
        try {
            onRenderedListener.onDidRender();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error occurred while invoking onDidRender listener for " + reactFeature, e);
        }
    }

    private void moveToNewState(ReactFeatureState reactFeatureState, ReactFeature reactFeature) {
        ReactFeatureState[] values;
        for (ReactFeatureState reactFeatureState2 : ReactFeatureState.values()) {
            if (reactFeatureState2 != reactFeatureState) {
                this.reactFeatures.remove(reactFeatureState2, reactFeature);
            }
        }
        this.reactFeatures.put(reactFeatureState, reactFeature);
    }

    private void removeFeature(ReactFeature reactFeature) {
        for (ReactFeatureState reactFeatureState : ReactFeatureState.values()) {
            this.reactFeatures.remove(reactFeatureState, reactFeature);
        }
        ReactRootView reactRootView = this.reactFeatureViews.get(reactFeature);
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.reactFeatureViews.remove(reactFeature);
        }
        this.reactFeatureRenderedCallbacks.remove(reactFeature);
    }

    private boolean resumeInstanceIfActiveFeatures() {
        Collection<ReactFeature> mo8104get = this.reactFeatures.mo8104get(ReactFeatureState.RESUMED);
        if (mo8104get != null && !mo8104get.isEmpty()) {
            Log.i(LOG_TAG, "Resuming react instance.");
            this.reactInstanceManager.onHostResume(this.activity, this.backBtnHandler);
            startTimerAndSubscribe();
            return true;
        }
        Log.i(LOG_TAG, "Not resuming React instance.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReactApplication(ReactRootView reactRootView, String str, Bundle bundle) {
        reactRootView.startReactApplication(this.reactInstanceManager, str, bundle);
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    private synchronized void startTimerAndSubscribe() {
        if (this.reactInstanceManager.getCurrentReactContext() == null) {
            Log.i(LOG_TAG, "Current react context is null, no need to start ping timer");
        } else if (this.pingHandlerModule != null) {
            Log.i(LOG_TAG, "Ping handler module already active");
        } else {
            this.pingHandlerModule = (PingHandlerModule) this.reactInstanceManager.getCurrentReactContext().getNativeModule(PingHandlerModule.class);
            this.pingHandlerModule.startPingTimer();
            this.subscription = this.pingHandlerModule.onBridgePingTimeout().subscribe(new Action1() { // from class: com.amazon.dee.app.elements.-$$Lambda$SharedInstanceReactFeatureManager$xrkOqI3Jw5QHuwLz5szzRqju4oM
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    SharedInstanceReactFeatureManager.this.lambda$startTimerAndSubscribe$1$SharedInstanceReactFeatureManager((Void) obj);
                }
            });
            Log.i(LOG_TAG, "Subscribed to ping handler");
        }
    }

    private synchronized void stopTimerAndUnsubscribe() {
        if (this.pingHandlerModule == null) {
            Log.i(LOG_TAG, "Ping handler module not active, timer is already stopped");
            return;
        }
        this.pingHandlerModule.stopPingTimer();
        if (this.subscription != null && !this.subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        this.pingHandlerModule = null;
    }

    @VisibleForTesting
    void emitAppStateChangedEvent(@Nullable ReactContext reactContext, String str) {
        if (reactContext == null) {
            Log.w(LOG_TAG, "Cannot trigger emitAppStateChangedEvent because reactContext is null.");
            return;
        }
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putString(APP_STATE_CHANGE_KEY, str);
        emitReactNativeEvent(reactContext, APP_STATE_CHANGE_EVENT, createMap);
    }

    public /* synthetic */ void lambda$startTimerAndSubscribe$1$SharedInstanceReactFeatureManager(Void r2) {
        Log.e(LOG_TAG, "Stopping ping timer");
        stopTimerAndUnsubscribe();
    }

    @Override // com.amazon.dee.app.elements.ReactHostLifecycle
    public void onActivityResult(int i, int i2, Intent intent) {
        this.reactInstanceManager.onActivityResult(this.activity, i, i2, intent);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0025 -> B:22:0x0030). Please submit an issue!!! */
    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onClearReactData() {
        try {
            ReactContext currentReactContext = this.reactInstanceManager.getCurrentReactContext();
            if (currentReactContext != null) {
                CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
                if (catalystInstance != null) {
                    ModuleDataCleaner.cleanDataFromModules(catalystInstance);
                } else {
                    Log.e(LOG_TAG, "Catalyst instance was null when clearing react data.");
                }
            } else {
                Log.e(LOG_TAG, "Context was null when clearing react data");
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error occurred while clearing react data.", e);
        }
        try {
            for (ReactFeature reactFeature : this.reactFeatureViews.keySet()) {
                removeFeature(reactFeature);
            }
            this.bridgeStatusService.setIsReady(false);
            this.bridgeStatusService.setPhaseReady(InitializationPhase.UNKNOWN);
            this.reactBridgeService.restart();
        } catch (Exception e2) {
            Log.e(LOG_TAG, "Error occurred while recreating the React Native Context.", e2);
        }
    }

    @Override // com.amazon.dee.app.elements.ReactHostLifecycle
    public void onDestroy() {
        Log.i(LOG_TAG, "Destroying React instance (host onDestroy).");
        this.reactInstanceManager.onHostDestroy(this.activity);
        this.reactInstanceManager.destroy();
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onFeatureBackgrounded(@NonNull ReactFeature reactFeature) {
        Preconditions.checkNotNull(reactFeature, "React Feature is null.");
        synchronized (this.lock) {
            if (!this.reactFeatures.containsValue(reactFeature)) {
                Log.e(LOG_TAG, "Feature not found. Was it resumed or already destroyed?");
            }
            moveToNewState(ReactFeatureState.BACKGROUNDED, reactFeature);
            changeFeatureForegroundStatus(reactFeature, false);
        }
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onFeatureDestroyed(@NonNull String str) {
        ReactFeature reactFeature;
        Preconditions.checkNotNull(str, "featureInstanceId cannot be null.");
        synchronized (this.lock) {
            Iterator<ReactFeature> it2 = this.reactFeatures.mo7876values().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    reactFeature = null;
                    break;
                }
                reactFeature = it2.next();
                if (str.equals(reactFeature.getInstanceId())) {
                    break;
                }
            }
        }
        if (reactFeature != null) {
            onFeatureDestroyed(reactFeature);
            return;
        }
        String str2 = "ReactFeature " + str + " doesn't exist (onFeatureDestroy)";
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onFeatureForegrounded(@NonNull ReactFeature reactFeature) {
        Preconditions.checkNotNull(reactFeature, "React Feature is null.");
        synchronized (this.lock) {
            if (!this.reactFeatures.containsValue(reactFeature)) {
                Log.e(LOG_TAG, "Feature not found. Was it already destroyed?");
            }
            moveToNewState(ReactFeatureState.RESUMED, reactFeature);
            changeFeatureForegroundStatus(reactFeature, true);
        }
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onFeatureRendered(@NonNull String str) {
        Preconditions.checkNotNull(str, "featureInstanceId must not be null.");
        synchronized (this.lock) {
            final OnRenderedListener onRenderedListener = null;
            final ReactFeature reactFeature = null;
            for (ReactFeature reactFeature2 : this.reactFeatureViews.keySet()) {
                if (reactFeature2.getInstanceId().equals(str)) {
                    reactFeature = reactFeature2;
                }
            }
            if (reactFeature == null) {
                Log.w(LOG_TAG, "No feature ID: " + str + " found for onFeatureRendered.");
                return;
            }
            WeakReference<OnRenderedListener> weakReference = this.reactFeatureRenderedCallbacks.get(reactFeature);
            if (weakReference != null) {
                onRenderedListener = weakReference.get();
            }
            this.reactFeatureRenderedCallbacks.remove(reactFeature);
            if (onRenderedListener != null) {
                this.mainHandler.post(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$SharedInstanceReactFeatureManager$sCtNQlkAeRqMbpGnJqKUOdNsQZo
                    @Override // java.lang.Runnable
                    public final void run() {
                        SharedInstanceReactFeatureManager.lambda$onFeatureRendered$0(ReactFeature.this, onRenderedListener);
                    }
                });
            } else {
                String str2 = "No rendered listener to invoke for " + reactFeature;
            }
            startTimerAndSubscribe();
        }
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public ReactRootView onFeatureResumed(@NonNull final ReactFeature reactFeature) {
        final ReactRootView viewForFeature;
        Preconditions.checkNotNull(reactFeature, "ReactFeature is null.");
        synchronized (this.lock) {
            viewForFeature = getViewForFeature(reactFeature);
            if (viewForFeature == null) {
                viewForFeature = new ReactRootViewCorrectMeasure(this.activity, this.tabLayoutStatusService);
                this.reactFeatureViews.put(reactFeature, viewForFeature);
            }
            try {
                this.reactInstanceManager.onHostResume(this.activity, this.backBtnHandler);
                viewForFeature.setVisibility(0);
                viewForFeature.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.dee.app.elements.SharedInstanceReactFeatureManager.1
                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public boolean onPreDraw() {
                        viewForFeature.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (viewForFeature.getParent() == null) {
                            return true;
                        }
                        try {
                            SharedInstanceReactFeatureManager.this.startReactApplication(viewForFeature, reactFeature.getAppName(), reactFeature.getLaunchOptions());
                            return false;
                        } catch (AssertionError unused) {
                            return false;
                        }
                    }
                });
                String str = "Mounted ReactFeature: " + reactFeature.getAppName();
            } catch (Exception unused) {
                viewForFeature = new ReactRootViewCorrectMeasure(this.activity, this.tabLayoutStatusService);
                this.reactFeatureViews.put(reactFeature, viewForFeature);
                startReactApplication(viewForFeature, reactFeature.getAppName(), reactFeature.getLaunchOptions());
            }
            startTimerAndSubscribe();
            moveToNewState(ReactFeatureState.RESUMED, reactFeature);
            changeFeatureForegroundStatus(reactFeature, true);
        }
        return viewForFeature;
    }

    @Override // com.amazon.dee.app.elements.ReactHostLifecycle
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Activity currentActivity;
        if (this.reactDeveloperSupportEnabled) {
            if (i == 82) {
                this.reactInstanceManager.showDevOptionsDialog();
                return true;
            }
            ReactContext currentReactContext = this.reactInstanceManager.getCurrentReactContext();
            if (currentReactContext == null || (currentActivity = currentReactContext.getCurrentActivity()) == null || !this.mDoubleTapReloadRecognizer.didDoubleTapR(i, currentActivity.getCurrentFocus())) {
                return false;
            }
            this.reactInstanceManager.getDevSupportManager().handleReloadJS();
            return true;
        }
        return false;
    }

    @Override // com.amazon.dee.app.elements.ReactHostLifecycle
    public void onPause() {
        boolean isEmpty;
        synchronized (this.lock) {
            Log.i(LOG_TAG, "Pausing React Instance (host onPause).");
            if (!this.reactFeatures.mo8104get(ReactFeatureState.RESUMED).isEmpty()) {
                this.reactInstanceManager.onHostPause(this.activity);
            }
            isEmpty = this.reactFeatures.isEmpty();
            stopTimerAndUnsubscribe();
        }
        if (isEmpty) {
            emitAppStateChangedEvent(this.reactInstanceManager.getCurrentReactContext(), AppStateModule.APP_STATE_BACKGROUND);
        }
    }

    @Override // com.amazon.dee.app.elements.ReactHostLifecycle
    public void onResume() {
        boolean isEmpty;
        synchronized (this.lock) {
            Log.i(LOG_TAG, "Resuming React features, if any (host onResume)");
            resumeInstanceIfActiveFeatures();
            isEmpty = this.reactFeatures.isEmpty();
        }
        if (isEmpty) {
            emitAppStateChangedEvent(this.reactInstanceManager.getCurrentReactContext(), "active");
        }
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void setOnFeatureRenderedListener(@NonNull ReactFeature reactFeature, @NonNull OnRenderedListener onRenderedListener) {
        Preconditions.checkNotNull(reactFeature, "reactFeature cannot be null");
        Preconditions.checkNotNull("onRenderedListener cannot be null");
        this.reactFeatureRenderedCallbacks.put(reactFeature, new WeakReference<>(onRenderedListener));
    }

    @VisibleForTesting
    void setReactFeatures(Multimap<ReactFeatureState, ReactFeature> multimap) {
        this.reactFeatures = multimap;
    }

    @Override // com.amazon.dee.app.elements.ReactFeatureManager
    public void onFeatureDestroyed(@NonNull ReactFeature reactFeature) {
        Preconditions.checkNotNull(reactFeature, "React Feature is null.");
        synchronized (this.lock) {
            if (!this.reactFeatures.containsValue(reactFeature)) {
                Log.e(LOG_TAG, "Feature not found. Was it resumed or already destroyed?");
            } else {
                removeFeature(reactFeature);
            }
            Collection<ReactFeature> mo8104get = this.reactFeatures.mo8104get(ReactFeatureState.RESUMED);
            if (mo8104get == null || mo8104get.isEmpty()) {
                stopTimerAndUnsubscribe();
            }
        }
    }
}
