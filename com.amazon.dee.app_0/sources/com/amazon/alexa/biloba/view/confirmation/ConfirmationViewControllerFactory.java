package com.amazon.alexa.biloba.view.confirmation;

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
public class ConfirmationViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "ConfirmationViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        Bundle bundle = (Bundle) routeContext.get(RouteArgumentKeys.BUNDLE);
        LogUtils.d(TAG, "creating ConfirmationView");
        ConfirmationView confirmationView = new ConfirmationView(context, (ConfirmationViewModel) routeContext.getParcelable(RouteArgumentKeys.PARCEL));
        viewManagerEventNotifier.onLoadingComplete();
        return confirmationView;
    }
}
