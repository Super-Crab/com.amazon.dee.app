package com.amazon.alexa.reactnative.api;

import android.content.Context;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.regulator.ViewController;
/* loaded from: classes9.dex */
public interface ElementsViewFactory {
    default ViewController makeViewControllerForRoute(Context context, RouteContext routeContext) {
        return null;
    }

    ViewController makeViewControllerForRoute(RouteContext routeContext);
}
