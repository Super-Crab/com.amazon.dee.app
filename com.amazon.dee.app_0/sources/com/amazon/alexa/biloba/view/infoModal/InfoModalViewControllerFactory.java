package com.amazon.alexa.biloba.view.infoModal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CommsHelper;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import dagger.Lazy;
/* loaded from: classes6.dex */
public class InfoModalViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "InfoModalViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        Bundle bundle = (Bundle) routeContext.get(RouteArgumentKeys.BUNDLE);
        LogUtils.d(TAG, "creating InfoModalView");
        InfoModalView infoModalView = new InfoModalView(context, (InfoModalViewModel) routeContext.getParcelable(RouteArgumentKeys.PARCEL));
        viewManagerEventNotifier.onLoadingComplete();
        return infoModalView;
    }

    @VisibleForTesting
    public ViewController createViewTest(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier, Lazy<CommsHelper> lazy, Lazy<CareActorsStore> lazy2) {
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        Bundle bundle = (Bundle) routeContext.get(RouteArgumentKeys.BUNDLE);
        LogUtils.d(TAG, "creating InfoModalView");
        InfoModalView infoModalView = new InfoModalView(context, (InfoModalViewModel) routeContext.getParcelable(RouteArgumentKeys.PARCEL), lazy, lazy2);
        viewManagerEventNotifier.onLoadingComplete();
        return infoModalView;
    }
}
