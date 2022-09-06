package com.amazon.alexa.drive.main;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.main.routing.DriveModeRoutes;
import com.amazon.alexa.drive.main.routing.DriveModeRoutingConstants;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.refactor.CommsRoutes;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mode.ModeConstants;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteApiUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingObserver;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public class DriveModeMainPresenter implements DriveModeMainContract.Presenter, RoutingObserver {
    private static final String TAG = "DriveModeMainPresenter";
    private final DriveModeMetricsHelper driveModeMetricsHelper;
    private final RoutingRegistry routingRegistry;
    private final RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline21(RoutingService.class);
    private DriveModeMainContract.View view;
    private final WeblabManager weblabManager;

    public DriveModeMainPresenter(DriveModeMetricsHelper driveModeMetricsHelper, WeblabManager weblabManager) {
        this.driveModeMetricsHelper = driveModeMetricsHelper;
        this.weblabManager = weblabManager;
        Preconditions.checkNotNull(this.routingService);
        this.routingRegistry = (RoutingRegistry) ComponentRegistry.getInstance().get(RoutingRegistry.class).orNull();
        Preconditions.checkNotNull(this.routingRegistry);
    }

    private void applyRouteContentMode(Route route) {
        Route parentRouteWithContentMode = RouteApiUtils.getParentRouteWithContentMode(route, this.routingRegistry);
        if (parentRouteWithContentMode == null) {
            String str = TAG;
            Log.w(str, "Non terminating inheritance chain detected for route: " + route);
            return;
        }
        switch (parentRouteWithContentMode.getContentMode()) {
            case 8:
                this.view.setNavigationBarVisibility(true);
                this.view.setTabBarVisibility(true);
                return;
            case 9:
                this.view.setNavigationBarVisibility(false);
                this.view.setTabBarVisibility(false);
                return;
            case 10:
                this.view.setNavigationBarVisibility(false);
                this.view.setTabBarVisibility(true);
                return;
            case 11:
                this.view.setNavigationBarVisibility(true);
                this.view.setTabBarVisibility(false);
                return;
            default:
                return;
        }
    }

    private void initializeRoutingObserver() {
        this.routingService.registerObserver(this);
    }

    private void navigateChannelRouteWithBackStack(@NonNull String str, @NonNull String str2, String str3) {
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        if (currentRoute != null && currentRoute.getRoute().is(str)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Current route is already the intended route ");
            outline107.append(currentRoute.getRoute());
            outline107.toString();
            return;
        }
        RoutingService.RoutingBuilder route = this.routingService.route(str);
        String str4 = "";
        if (!str2.equals(str4)) {
            route.with(str2, str3);
        }
        Preconditions.checkNotNull(route);
        DriveModeMetricsHelper driveModeMetricsHelper = this.driveModeMetricsHelper;
        if (currentRoute != null) {
            str4 = currentRoute.getRoute().getName();
        }
        driveModeMetricsHelper.logChannelSwitchMetrics(str4, str);
        String str5 = "Navigating to " + str;
        if (this.routingService.popFromBackStack(str)) {
            return;
        }
        route.addToBackStack().navigate();
    }

    private void publishModeChangedEvent(String str) {
        Message build = new Message.Builder().setEventType(ModeConstants.MODE_SWITCHED_EVENT).setPayload(str).build();
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (eventBus != null) {
            eventBus.publish(build);
        }
    }

    private void publishWarningViewShownEvent() {
        Message build = new Message.Builder().setEventType("drive-mode::warningview::shown").setPayload("").build();
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        eventBus.publish(build);
    }

    private void uninitializeRoutingObserver() {
        this.routingService.unregisterObserver(this);
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void egress() {
        Log.i(TAG, "Drive mode egressing");
        ModeService modeService = (ModeService) ComponentRegistry.getInstance().get(ModeService.class).orNull();
        if (modeService != null) {
            modeService.exitDriveMode(0);
        } else {
            Log.w(TAG, "ModeService not found. Can't egress the drive Mode");
        }
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void initialize(DriveModeMainContract.View view) {
        Log.i(TAG, "Initializing the presenter");
        this.view = view;
        initializeRoutingObserver();
        publishModeChangedEvent(ModeName.DRIVE_MODE);
        this.driveModeMetricsHelper.startDriverDistraction();
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void navigateToCommunications() {
        if (this.weblabManager.isCommsNativeWeblabEnabled()) {
            navigateChannelRouteWithBackStack(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ROUTE_NAME, "", "");
        } else {
            navigateChannelRouteWithBackStack("v2/comms/drive-mode-landing-page", "autoModeType", DriveModeMetricsHelper.getAutoModeType());
        }
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void navigateToEntertainment() {
        navigateChannelRouteWithBackStack("drive-mode/entertainment/index", "", "");
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void navigateToHome() {
        navigateChannelRouteWithBackStack("drive-mode/home/index", "", "");
    }

    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    public void navigateToNavigation() {
        navigateChannelRouteWithBackStack("drive-mode/navigation/index", "", "");
    }

    @Override // com.amazon.alexa.routing.api.RoutingObserver
    public void onRouteChanged(@NonNull RouteContext routeContext) {
        if (this.view == null) {
            String str = "View is not present yet, dropping route change " + routeContext;
            return;
        }
        Route route = routeContext.getRoute();
        if (DriveModeRoutes.isHomeRoute(route)) {
            this.view.channelSwitched(DriveModeMainContract.View.ChannelName.HOME);
        } else if (DriveModeRoutes.isNavigationRoute(route)) {
            this.view.channelSwitched(DriveModeMainContract.View.ChannelName.NAVIGATION);
        } else if (!CommsRoutes.isCommsRoute(route) && !DriveModeRoutes.isCommsNativeRoute(route)) {
            if (DriveModeRoutes.isEntertainmentRoute(route)) {
                this.view.channelSwitched(DriveModeMainContract.View.ChannelName.ENTERTAINMENT);
            } else if (route.is("alexa-oobe/drive-mode/main")) {
                return;
            } else {
                this.view.exitingDriveModeRoutes();
                return;
            }
        } else {
            this.view.channelSwitched(DriveModeMainContract.View.ChannelName.COMMS);
        }
        applyRouteContentMode(routeContext.getRoute());
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x008b, code lost:
        if (r8.routingRegistry.get("drive-mode/driver-interaction-warning") != null) goto L25;
     */
    @Override // com.amazon.alexa.drive.main.DriveModeMainContract.Presenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void uninitialize(boolean r9) {
        /*
            r8 = this;
            java.lang.String r0 = "drive-mode/driver-interaction-warning"
            com.amazon.alexa.routing.api.RoutingService r1 = r8.routingService
            com.amazon.alexa.routing.api.RouteContext r1 = r1.getCurrentRoute()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L19
            com.amazon.alexa.routing.api.Route r4 = r1.getRoute()
            boolean r4 = com.amazon.alexa.drive.main.routing.DriveModeRoutes.isDriveModeRoute(r4)
            if (r4 != 0) goto L17
            goto L19
        L17:
            r4 = r3
            goto L1a
        L19:
            r4 = r2
        L1a:
            java.lang.String r5 = com.amazon.alexa.drive.main.DriveModeMainPresenter.TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unitializing the presenter. non-drive-mode-route:"
            r6.append(r7)
            r6.append(r4)
            java.lang.String r7 = " view-requested-tear-down:"
            r6.append(r7)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r5, r6)
            if (r4 != 0) goto L3c
            if (r9 == 0) goto L46
        L3c:
            java.lang.String r5 = "main_mode"
            r8.publishModeChangedEvent(r5)
            com.amazon.alexa.drive.metrics.DriveModeMetricsHelper r5 = r8.driveModeMetricsHelper
            r5.stopDriverDistraction()
        L46:
            if (r4 == 0) goto La0
            if (r9 == 0) goto La0
            if (r1 == 0) goto L58
            com.amazon.alexa.routing.api.Route r9 = r1.getRoute()
            boolean r9 = com.amazon.alexa.drive.main.routing.DriveModeRoutes.isDriveModeSafetyWarningRoute(r9)
            if (r9 == 0) goto L58
            r9 = r2
            goto L59
        L58:
            r9 = r3
        L59:
            java.lang.Class<com.amazon.alexa.mode.ModeService> r1 = com.amazon.alexa.mode.ModeService.class
            java.lang.Object r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline21(r1)
            com.amazon.alexa.mode.ModeService r1 = (com.amazon.alexa.mode.ModeService) r1
            io.reactivex.rxjava3.subjects.BehaviorSubject r4 = r1.isDriveModeAccessoryDeviceConnected()
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            io.reactivex.rxjava3.subjects.BehaviorSubject r1 = r1.isAutoBluetoothDeviceConnected()
            java.lang.Object r1 = r1.getValue()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L83
            if (r1 == 0) goto La0
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto La0
        L83:
            if (r9 != 0) goto La0
            com.amazon.alexa.routing.api.RoutingRegistry r9 = r8.routingRegistry     // Catch: java.lang.IllegalArgumentException -> L8e
            com.amazon.alexa.routing.api.Route r9 = r9.get(r0)     // Catch: java.lang.IllegalArgumentException -> L8e
            if (r9 == 0) goto L95
            goto L96
        L8e:
            java.lang.String r9 = com.amazon.alexa.drive.main.DriveModeMainPresenter.TAG
            java.lang.String r1 = "Route not registered: drive-mode/driver-interaction-warning"
            android.util.Log.i(r9, r1)
        L95:
            r2 = r3
        L96:
            if (r2 == 0) goto La0
            com.amazon.alexa.routing.api.RoutingService r9 = r8.routingService
            com.android.tools.r8.GeneratedOutlineSupport1.outline145(r9, r0)
            r8.publishWarningViewShownEvent()
        La0:
            r8.uninitializeRoutingObserver()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.main.DriveModeMainPresenter.uninitialize(boolean):void");
    }
}
