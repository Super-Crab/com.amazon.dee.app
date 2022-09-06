package com.amazon.alexa.entertainment.devicepicker;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.dee.app.elements.ReactViewControllerFactory;
import com.facebook.react.ReactInstanceManager;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public class DevicePickerViewControllerFactory extends ReactViewControllerFactory {
    private static final String TAG = "DevicePickerViewControllerFactory";

    @Override // com.amazon.dee.app.elements.ReactViewControllerFactory
    public ViewController createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier, @NonNull Provider<ReactInstanceManager> provider) {
        viewManagerEventNotifier.onLoadingComplete();
        if (routeContext.getRoute().is("universal-device-picker")) {
            return new DevicePickerViewController(provider.mo10268get());
        }
        return null;
    }
}
