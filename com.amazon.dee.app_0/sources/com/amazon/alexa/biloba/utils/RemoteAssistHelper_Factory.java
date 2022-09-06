package com.amazon.alexa.biloba.utils;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class RemoteAssistHelper_Factory implements Factory<RemoteAssistHelper> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<BilobaMetricsService> metricServiceProvider;
    private final Provider<RemoteManagementInactivityHandler> remoteManagementInactivityHandlerProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public RemoteAssistHelper_Factory(Provider<RoutingService> provider, Provider<DeviceInformation> provider2, Provider<RemoteManagementInactivityHandler> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        this.routingServiceProvider = provider;
        this.deviceInformationProvider = provider2;
        this.remoteManagementInactivityHandlerProvider = provider3;
        this.metricServiceProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static RemoteAssistHelper_Factory create(Provider<RoutingService> provider, Provider<DeviceInformation> provider2, Provider<RemoteManagementInactivityHandler> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        return new RemoteAssistHelper_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static RemoteAssistHelper newRemoteAssistHelper() {
        return new RemoteAssistHelper();
    }

    public static RemoteAssistHelper provideInstance(Provider<RoutingService> provider, Provider<DeviceInformation> provider2, Provider<RemoteManagementInactivityHandler> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        RemoteAssistHelper remoteAssistHelper = new RemoteAssistHelper();
        RemoteAssistHelper_MembersInjector.injectRoutingService(remoteAssistHelper, DoubleCheck.lazy(provider));
        RemoteAssistHelper_MembersInjector.injectDeviceInformation(remoteAssistHelper, DoubleCheck.lazy(provider2));
        RemoteAssistHelper_MembersInjector.injectRemoteManagementInactivityHandler(remoteAssistHelper, DoubleCheck.lazy(provider3));
        RemoteAssistHelper_MembersInjector.injectMetricService(remoteAssistHelper, DoubleCheck.lazy(provider4));
        RemoteAssistHelper_MembersInjector.injectCareActorsStore(remoteAssistHelper, DoubleCheck.lazy(provider5));
        return remoteAssistHelper;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RemoteAssistHelper mo10268get() {
        return provideInstance(this.routingServiceProvider, this.deviceInformationProvider, this.remoteManagementInactivityHandlerProvider, this.metricServiceProvider, this.careActorsStoreProvider);
    }
}
