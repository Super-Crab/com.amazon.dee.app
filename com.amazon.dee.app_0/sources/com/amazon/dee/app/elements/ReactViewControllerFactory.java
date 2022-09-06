package com.amazon.dee.app.elements;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.facebook.react.ReactInstanceManager;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public abstract class ReactViewControllerFactory implements ViewControllerFactory<ViewController> {
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public final ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        throw new UnsupportedOperationException("Constructor not supported for ReactViewControllerFactory");
    }

    public abstract ViewController createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier, @NonNull Provider<ReactInstanceManager> provider);
}
