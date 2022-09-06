package com.amazon.alexa.cantilever;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
/* loaded from: classes6.dex */
public class HelpViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "HelpViewControllerFactory";
    private HelpViewController helpViewController;

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        if (routeContext.getRoute().is(RouteName.HELP)) {
            viewManagerLoadingDelegate.setLoadingState(true);
            this.helpViewController = new HelpViewController();
            this.helpViewController.setHelpViewDelegate(viewManagerLoadingDelegate);
            this.helpViewController.onRouteUpdated(routeContext);
            return this.helpViewController;
        }
        return null;
    }
}
