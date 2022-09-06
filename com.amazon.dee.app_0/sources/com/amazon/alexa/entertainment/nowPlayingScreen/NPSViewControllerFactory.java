package com.amazon.alexa.entertainment.nowPlayingScreen;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.dee.app.elements.ReactViewControllerFactory;
import com.facebook.react.ReactInstanceManager;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public class NPSViewControllerFactory extends ReactViewControllerFactory {
    private static final String TAG = "NPSViewControllerFactory";

    @Override // com.amazon.dee.app.elements.ReactViewControllerFactory
    public ViewController createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier, @NonNull Provider<ReactInstanceManager> provider) {
        viewManagerEventNotifier.onLoadingComplete();
        if (routeContext.getRoute().is("now-playing-screen")) {
            return new NPSViewController(provider.mo10268get());
        }
        return null;
    }
}
