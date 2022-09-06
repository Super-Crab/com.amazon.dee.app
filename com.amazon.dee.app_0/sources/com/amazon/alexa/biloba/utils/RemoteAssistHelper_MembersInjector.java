package com.amazon.alexa.biloba.utils;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class RemoteAssistHelper_MembersInjector implements MembersInjector<RemoteAssistHelper> {
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<BilobaMetricsService> metricServiceProvider;
    private final Provider<RemoteManagementInactivityHandler> remoteManagementInactivityHandlerProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public RemoteAssistHelper_MembersInjector(Provider<RoutingService> provider, Provider<DeviceInformation> provider2, Provider<RemoteManagementInactivityHandler> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        this.routingServiceProvider = provider;
        this.deviceInformationProvider = provider2;
        this.remoteManagementInactivityHandlerProvider = provider3;
        this.metricServiceProvider = provider4;
        this.careActorsStoreProvider = provider5;
    }

    public static MembersInjector<RemoteAssistHelper> create(Provider<RoutingService> provider, Provider<DeviceInformation> provider2, Provider<RemoteManagementInactivityHandler> provider3, Provider<BilobaMetricsService> provider4, Provider<CareActorsStore> provider5) {
        return new RemoteAssistHelper_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectCareActorsStore(RemoteAssistHelper remoteAssistHelper, Lazy<CareActorsStore> lazy) {
        remoteAssistHelper.careActorsStore = lazy;
    }

    public static void injectDeviceInformation(RemoteAssistHelper remoteAssistHelper, Lazy<DeviceInformation> lazy) {
        remoteAssistHelper.deviceInformation = lazy;
    }

    public static void injectMetricService(RemoteAssistHelper remoteAssistHelper, Lazy<BilobaMetricsService> lazy) {
        remoteAssistHelper.metricService = lazy;
    }

    public static void injectRemoteManagementInactivityHandler(RemoteAssistHelper remoteAssistHelper, Lazy<RemoteManagementInactivityHandler> lazy) {
        remoteAssistHelper.remoteManagementInactivityHandler = lazy;
    }

    public static void injectRoutingService(RemoteAssistHelper remoteAssistHelper, Lazy<RoutingService> lazy) {
        remoteAssistHelper.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RemoteAssistHelper remoteAssistHelper) {
        injectRoutingService(remoteAssistHelper, DoubleCheck.lazy(this.routingServiceProvider));
        injectDeviceInformation(remoteAssistHelper, DoubleCheck.lazy(this.deviceInformationProvider));
        injectRemoteManagementInactivityHandler(remoteAssistHelper, DoubleCheck.lazy(this.remoteManagementInactivityHandlerProvider));
        injectMetricService(remoteAssistHelper, DoubleCheck.lazy(this.metricServiceProvider));
        injectCareActorsStore(remoteAssistHelper, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
