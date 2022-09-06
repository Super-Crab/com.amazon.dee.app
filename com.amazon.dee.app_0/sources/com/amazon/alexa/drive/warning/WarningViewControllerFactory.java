package com.amazon.alexa.drive.warning;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes7.dex */
public class WarningViewControllerFactory implements ViewControllerFactory<ViewController> {
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        viewManagerEventNotifier.onLoadingComplete();
        if (routeContext.getRoute().is("drive-mode/driver-interaction-warning")) {
            return new WarningViewController(routeContext.getBoolean("SHOW_INGRESS_WARNING_SCREEN", false));
        }
        return null;
    }
}
