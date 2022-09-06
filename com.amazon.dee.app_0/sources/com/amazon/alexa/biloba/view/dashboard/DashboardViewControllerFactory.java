package com.amazon.alexa.biloba.view.dashboard;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class DashboardViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "DashboardViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        LogUtils.d(TAG, "creating DashboardView");
        BilobaDashboardView bilobaDashboardView = new BilobaDashboardView(context, (Bundle) routeContext.get(RouteArgumentKeys.BUNDLE));
        viewManagerEventNotifier.onLoadingComplete();
        return bilobaDashboardView;
    }
}
