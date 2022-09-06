package com.amazon.alexa.biloba.view.tips;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class TipsViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "TipsViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        LogUtils.d(TAG, "creating TipsView");
        TipsView tipsView = new TipsView(context);
        viewManagerEventNotifier.onLoadingComplete();
        return tipsView;
    }
}
