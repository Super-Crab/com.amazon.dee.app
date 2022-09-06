package com.amazon.alexa.biloba.view.comms;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class EmergencyContactViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "EmergencyContactViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        ViewController emergencyContactView;
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        if (routeContext.getRoute().is(Routes.BILOBA_COMMS_SHARE_SETUP_LINK)) {
            LogUtils.d(TAG, "creating CommsShareSetupLinkView");
            emergencyContactView = new CommsShareSetupLinkView(context);
        } else if (routeContext.getRoute().is(Routes.BILOBA_COMMS_SETUP_INSTRUCTIONS)) {
            LogUtils.d(TAG, "creating CommsSetupInstructionsView");
            emergencyContactView = new CommsSetupInstructionsView(context);
        } else if (routeContext.getRoute().is(Routes.BILOBA_EMERGENCY)) {
            LogUtils.d(TAG, "creating EmergencyView");
            emergencyContactView = new EmergencyView(context, componentRegistry);
        } else {
            LogUtils.d(TAG, "creating EmergencyContactView");
            emergencyContactView = new EmergencyContactView(context);
        }
        viewManagerEventNotifier.onLoadingComplete();
        return emergencyContactView;
    }
}
