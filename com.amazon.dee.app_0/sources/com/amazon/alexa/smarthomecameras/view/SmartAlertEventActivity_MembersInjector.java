package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.routing.api.RoutingService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class SmartAlertEventActivity_MembersInjector implements MembersInjector<SmartAlertEventActivity> {
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<SmartAlertEventView> smartAlertEventViewProvider;

    public SmartAlertEventActivity_MembersInjector(Provider<SmartAlertEventView> provider, Provider<RoutingService> provider2) {
        this.smartAlertEventViewProvider = provider;
        this.routingServiceProvider = provider2;
    }

    public static MembersInjector<SmartAlertEventActivity> create(Provider<SmartAlertEventView> provider, Provider<RoutingService> provider2) {
        return new SmartAlertEventActivity_MembersInjector(provider, provider2);
    }

    public static void injectRoutingService(SmartAlertEventActivity smartAlertEventActivity, RoutingService routingService) {
        smartAlertEventActivity.routingService = routingService;
    }

    public static void injectSmartAlertEventView(SmartAlertEventActivity smartAlertEventActivity, SmartAlertEventView smartAlertEventView) {
        smartAlertEventActivity.smartAlertEventView = smartAlertEventView;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SmartAlertEventActivity smartAlertEventActivity) {
        injectSmartAlertEventView(smartAlertEventActivity, this.smartAlertEventViewProvider.mo10268get());
        injectRoutingService(smartAlertEventActivity, this.routingServiceProvider.mo10268get());
    }
}
