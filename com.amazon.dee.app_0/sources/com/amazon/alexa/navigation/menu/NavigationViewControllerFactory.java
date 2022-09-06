package com.amazon.alexa.navigation.menu;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
/* loaded from: classes9.dex */
public class NavigationViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "NavigationViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        if (routeContext.getRoute().is(RouteName.NAV_MORE_MENU)) {
            return new NavigationViewController(context, ComponentRegistry.getInstance());
        }
        return null;
    }
}
