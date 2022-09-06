package com.amazon.alexa.viewmanagement.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
/* loaded from: classes10.dex */
public interface ViewControllerFactory<T extends ViewController> {
    @Deprecated
    default T createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        return mo1459createView(context, routeContext, viewManagerEventNotifier);
    }

    @Deprecated
    /* renamed from: createView */
    default T mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        return null;
    }

    /* renamed from: createView */
    default T mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        return createView(context, permissionsService, routeContext, (ViewManagerEventNotifier) viewManagerLoadingDelegate);
    }
}
