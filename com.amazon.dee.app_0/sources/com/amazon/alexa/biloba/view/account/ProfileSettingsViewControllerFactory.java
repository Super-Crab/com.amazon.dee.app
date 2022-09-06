package com.amazon.alexa.biloba.view.account;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerView;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
/* loaded from: classes6.dex */
public class ProfileSettingsViewControllerFactory implements ViewControllerFactory<ViewController> {
    private static final String TAG = "ProfileSettingsViewControllerFactory";

    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo1459createView(@NonNull Context context, @NonNull RouteContext routeContext, @NonNull ViewManagerEventNotifier viewManagerEventNotifier) {
        ViewController profileSettingsView;
        String str = TAG;
        LogUtils.d(str, "Got route for: " + routeContext);
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        if (routeContext.getRoute().is(Routes.TIMEZONE_PICKER)) {
            LogUtils.d(TAG, "creating TimeZonePickerView");
            profileSettingsView = new TimeZonePickerView();
        } else if (routeContext.getRoute().is(Routes.BILOBA_MEMBERSHIP_MANAGE)) {
            LogUtils.d(TAG, "creating MembershipView");
            profileSettingsView = new MembershipView(context);
        } else if (routeContext.getRoute().is(Routes.BILOBA_MEMBERSHIP_LEAVE)) {
            LogUtils.d(TAG, "creating MembershipLeaveView");
            profileSettingsView = new MembershipLeaveView(context);
        } else {
            LogUtils.d(TAG, "creating ProfileSettingsView");
            profileSettingsView = new ProfileSettingsView(context.getResources().getConfiguration(), componentRegistry);
        }
        viewManagerEventNotifier.onLoadingComplete();
        return profileSettingsView;
    }
}
