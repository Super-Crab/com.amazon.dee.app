package com.amazon.alexa.biloba.view.gettingStarted;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class GettingStartedViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "GettingStartedViewControllerFactory";
    private ViewController viewController;

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        if (this.viewController == null) {
            LogUtils.d(TAG, "creating GettingStartedView");
            this.viewController = new GettingStartedViewV3(context);
        }
        viewManagerEventNotifier.onLoadingComplete();
        return this.viewController;
    }
}
