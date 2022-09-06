package com.amazon.alexa.biloba.view.alerts;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class AlertsViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "AlertsViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        ViewController alertSettingsListView;
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        Bundle bundle = routeContext.getBundle(RouteArgumentKeys.BUNDLE);
        if (routeContext.getRoute().is(Routes.BILOBA_ALERT_FORM)) {
            LogUtils.d(TAG, "creating AlertSettingsView");
            alertSettingsListView = new AlertSettingsView(context, bundle);
        } else {
            LogUtils.d(TAG, "creating AlertSettingsListView");
            alertSettingsListView = new AlertSettingsListView(context, bundle);
        }
        viewManagerEventNotifier.onLoadingComplete();
        return alertSettingsListView;
    }
}
