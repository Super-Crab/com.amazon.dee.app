package com.amazon.alexa.viewmanagement.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.dee.app.elements.ReactViewControllerFactory;
import com.facebook.react.ReactInstanceManager;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public class ReactNativeViewManager {
    private static final String TAG = "ReactNativeViewManager";
    private final Provider<ReactInstanceManager> reactInstanceManagerProvider;

    public ReactNativeViewManager(@NonNull Provider<ReactInstanceManager> provider) {
        this.reactInstanceManagerProvider = provider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewController createView(@NonNull ReactViewControllerFactory reactViewControllerFactory, @NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        return reactViewControllerFactory.createView(context, routeContext, viewManagerEventNotifier, this.reactInstanceManagerProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isReactNativeViewControllerFactory(ViewControllerFactory viewControllerFactory) {
        return viewControllerFactory instanceof ReactViewControllerFactory;
    }
}
