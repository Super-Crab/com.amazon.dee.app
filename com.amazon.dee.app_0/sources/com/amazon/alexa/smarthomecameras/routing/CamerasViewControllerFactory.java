package com.amazon.alexa.smarthomecameras.routing;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.view.CamerasViewController;
import com.amazon.alexa.smarthomecameras.view.SmartAlertEventViewController;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class CamerasViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "CamerasViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        ViewController smartAlertEventViewController;
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createView: ");
        outline107.append(routeContext.getRoute());
        Log.i(str, outline107.toString());
        RoutingService routingService = (RoutingService) ComponentRegistry.getInstance().get(RoutingService.class).get();
        CamerasMobilyticsService camerasMobilyticsService = new CamerasMobilyticsService((Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class));
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
        if (routeContext.getRoute().is(RouteName.SMART_HOME_CAMERAS_LIVEVIEW)) {
            smartAlertEventViewController = new CamerasViewController(context, routeContext, routingService, camerasMobilyticsService, featureServiceV2, eventBus);
        } else {
            smartAlertEventViewController = routeContext.getRoute().is(RouteName.SMART_HOME_CAMERAS_SMART_ALERTS_EVENT) ? new SmartAlertEventViewController(context, routeContext, routingService) : null;
        }
        viewManagerLoadingDelegate.setLoadingState(false);
        return smartAlertEventViewController;
    }
}
