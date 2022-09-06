package com.amazon.alexa.biloba.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.emergencyHelpline.EmergencyHelplineRoutingHelper;
import com.amazon.alexa.biloba.view.startup.StartupView;
import com.amazon.alexa.biloba.view.webview.WebviewViewRoutingHelper;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import dagger.Lazy;
import java.util.Date;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class RootViewControllerFactory implements ViewControllerFactory<ViewController> {
    protected static final String PASSCODE_PARAMETER_KEY = "isPersonalPasscodeVerified";
    protected static final String RAW_QUERY_STRING = "rawQueryString";
    private static final String TAG = "RootViewControllerFactory";
    @VisibleForTesting
    Bundle args;
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<DeferredRoutingHelper> deferredRoutingHelper;
    @Inject
    Lazy<EmergencyHelplineRoutingHelper> emergencyHelplineRoutingHelper;
    @Inject
    Lazy<EventBus> eventBus;
    @Inject
    Lazy<IdentityService> identityService;
    @Inject
    Lazy<RoutingService> routingService;
    @Inject
    Lazy<PersistentStorage.Factory> storageFactory;
    @Inject
    Lazy<WebviewViewRoutingHelper> webviewViewRoutingHelper;

    public RootViewControllerFactory() {
    }

    private ViewController getDeferredController(Context context, String str, RouteContext routeContext) {
        if (this.emergencyHelplineRoutingHelper.mo358get().isEmergencyHelplineRoute(str)) {
            return this.emergencyHelplineRoutingHelper.mo358get().getEmergencyHelplineViewController(context, str, routeContext);
        }
        if (this.webviewViewRoutingHelper.mo358get().isWebviewRoute(str)) {
            return this.webviewViewRoutingHelper.mo358get().getWebviewViewController(context, routeContext);
        }
        LogUtils.d(TAG, String.format("No deferred ViewController corresponds to route %s; proceeding to StartupView", str));
        return null;
    }

    private void setPasscodeVerifiedFlag(RouteContext routeContext) {
        boolean parseBoolean = Boolean.parseBoolean(BilobaRouteUtil.getParameterValue(routeContext, PASSCODE_PARAMETER_KEY));
        this.args.putBoolean(StartupView.PASSCODE_VERIFIED_BOOL, parseBoolean);
        String str = TAG;
        LogUtils.d(str, "passcode verified?" + parseBoolean);
    }

    private void subscribeToSignout() {
        try {
            this.eventBus.mo358get().getSubscriber().subscribeFilter($$Lambda$RootViewControllerFactory$YWysHdD5TyFrpSHYgmcVU1anvA.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.biloba.view.-$$Lambda$RootViewControllerFactory$PTLO-Foes-LRJjuljdeURh6c4iQ
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    RootViewControllerFactory.this.lambda$subscribeToSignout$3$RootViewControllerFactory(message);
                }
            });
        } catch (IllegalStateException unused) {
            LogUtils.w(TAG, "failed to subscribe to sign out event.");
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        long time = new Date().getTime();
        this.args = new Bundle();
        BilobaDependencies.initialize();
        BilobaDependencies.inject(this);
        subscribeToSignout();
        this.bilobaMetricsService.mo358get().startRecordingTTCF(time);
        resetTTCFWhenAppBackgrounds();
        ThemeUtil.setTheme(context);
        viewManagerEventNotifier.onLoadingComplete();
        return getViewController(context, routeContext);
    }

    @VisibleForTesting
    ViewController getViewController(Context context, RouteContext routeContext) {
        String name = routeContext.getRoute().getName();
        String str = TAG;
        LogUtils.d(str, "Got route " + name);
        if (!name.equals("biloba")) {
            if (this.careActorsStore.mo358get().getCurrentActor().getValue() == null) {
                String str2 = TAG;
                LogUtils.d(str2, "CareActorsStore not yet populated; deferring navigation to route " + name);
                this.deferredRoutingHelper.mo358get().setDeferredRouteContext(routeContext);
            } else {
                String str3 = TAG;
                LogUtils.d(str3, "CareActorsStore populated; proceeding to route " + name);
                this.deferredRoutingHelper.mo358get().clearDeferredRouteContext();
                ViewController deferredController = getDeferredController(context, name, routeContext);
                if (deferredController != null) {
                    return deferredController;
                }
            }
        }
        setPasscodeVerifiedFlag(routeContext);
        LogUtils.d(TAG, "creating StartupView");
        return new StartupView(context, this.args);
    }

    public /* synthetic */ void lambda$resetTTCFWhenAppBackgrounds$1$RootViewControllerFactory(Message message) {
        LogUtils.d(TAG, "Received the APPLICATION_DID_BACKGROUND event; resetting TTCF timers!");
        this.bilobaMetricsService.mo358get().resetTTCFTimers();
    }

    public /* synthetic */ void lambda$subscribeToSignout$3$RootViewControllerFactory(Message message) {
        LogUtils.d(TAG, "Received the IDENTITY_SIGN_OUT_SUCCESS event; clearing data for biloba!");
        new IdentityLocalDataStore(this.storageFactory.mo358get(), this.identityService.mo358get()).clearAll();
        this.careActorsStore.mo358get().clear();
    }

    @VisibleForTesting
    void resetTTCFWhenAppBackgrounds() {
        this.eventBus.mo358get().getSubscriber().subscribeFilter($$Lambda$RootViewControllerFactory$GIsLycCTEwWO2XMoIHWprhCofOU.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.biloba.view.-$$Lambda$RootViewControllerFactory$aJk5hwrKelDMwd5X1A4LsrhpezA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                RootViewControllerFactory.this.lambda$resetTTCFWhenAppBackgrounds$1$RootViewControllerFactory(message);
            }
        });
    }

    @VisibleForTesting
    RootViewControllerFactory(Lazy<BilobaMetricsService> lazy, Lazy<CareActorsStore> lazy2, Lazy<RoutingService> lazy3, Lazy<EmergencyHelplineRoutingHelper> lazy4, Lazy<EventBus> lazy5, Lazy<PersistentStorage.Factory> lazy6, Lazy<IdentityService> lazy7, Lazy<DeferredRoutingHelper> lazy8) {
        this.bilobaMetricsService = lazy;
        this.careActorsStore = lazy2;
        this.routingService = lazy3;
        this.emergencyHelplineRoutingHelper = lazy4;
        this.eventBus = lazy5;
        this.storageFactory = lazy6;
        this.identityService = lazy7;
        this.deferredRoutingHelper = lazy8;
    }
}
