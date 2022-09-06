package com.amazon.alexa.drive.comms.routing;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.comms.view.CommsLandingPageViewController;
import com.amazon.alexa.drive.main.routing.DriveModeRoutingConstants;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes7.dex */
public class CommsViewControllerFactory implements ViewControllerFactory<ViewController> {
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        viewManagerEventNotifier.onLoadingComplete();
        if (routeContext.getRoute().is(DriveModeRoutingConstants.DRIVE_MODE_COMMS_NATIVE_ROUTE_NAME)) {
            return new CommsLandingPageViewController();
        }
        return null;
    }
}
