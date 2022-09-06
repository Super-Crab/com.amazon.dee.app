package com.amazon.alexa.viewmanagement.impl;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.amazon.dee.app.elements.ReactViewControllerFactory;
import com.google.common.base.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes10.dex */
public final class ViewControllerFactoryProducer {
    private static final String TAG = "ViewControllerFactoryProducer";
    @VisibleForTesting
    ConcurrentMap<String, ViewControllerFactory> viewControllerFactoryMap = new ConcurrentHashMap();

    public void clear() {
        this.viewControllerFactoryMap.clear();
    }

    @NonNull
    @VisibleForTesting
    ViewControllerFactory createFactory(Class cls) throws ReflectiveOperationException {
        try {
            return (ViewControllerFactory) ViewControllerFactory.class.cast(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (NoSuchMethodException unused) {
            throw new NoSuchMethodException(String.format("No generic constructor found for %s", cls.getName()));
        }
    }

    @NonNull
    @VisibleForTesting
    Optional<ViewController> getViewController(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull ReactNativeViewManager reactNativeViewManager, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate, @NonNull ViewControllerFactory viewControllerFactory) {
        if (reactNativeViewManager.isReactNativeViewControllerFactory(viewControllerFactory)) {
            return Optional.of(reactNativeViewManager.createView((ReactViewControllerFactory) viewControllerFactory, context, routeContext, (ViewManagerEventNotifier) viewManagerLoadingDelegate));
        }
        return Optional.of(viewControllerFactory.mo2381createView(context, permissionsService, routeContext, viewManagerLoadingDelegate));
    }

    @NonNull
    @VisibleForTesting
    Optional<ViewControllerFactory> getViewControllerFactory(@NonNull String str) {
        if (this.viewControllerFactoryMap.containsKey(str)) {
            return Optional.of(this.viewControllerFactoryMap.get(str));
        }
        try {
            ViewControllerFactory createFactory = createFactory(Class.forName(str));
            ViewControllerFactory putIfAbsent = this.viewControllerFactoryMap.putIfAbsent(str, createFactory);
            if (putIfAbsent != null) {
                createFactory = putIfAbsent;
            }
            return Optional.of(createFactory);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, String.format("Could not find ViewControllerFactory class %s", str), e);
            return Optional.absent();
        } catch (ReflectiveOperationException e2) {
            Log.e(TAG, String.format("Unable to create ViewControllerFactory class %s", str), e2);
            return Optional.absent();
        }
    }

    @NonNull
    public Optional<ViewController> getViewController(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull ReactNativeViewManager reactNativeViewManager, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate, @NonNull String str) {
        Optional<ViewControllerFactory> viewControllerFactory = getViewControllerFactory(str);
        if (viewControllerFactory.isPresent()) {
            return getViewController(context, permissionsService, reactNativeViewManager, routeContext, viewManagerLoadingDelegate, viewControllerFactory.get());
        }
        String str2 = TAG;
        Log.w(str2, "Could not create factory with name - " + str);
        return Optional.absent();
    }
}
