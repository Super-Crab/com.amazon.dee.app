package com.amazon.deecomms.notifications.service;

import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsActivityMonitor;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.notifications.InboundAnnouncementCoordinator;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CreateNotificationService_MembersInjector implements MembersInjector<CreateNotificationService> {
    private final Provider<CommsActivityMonitor> activityMonitorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<InboundAnnouncementCoordinator> mAnnouncementCoordinatorProvider;
    private final Provider<MetricsService> mMetricsServiceProvider;
    private final Provider<ModeSwitchHelper> modeSwitchHelperProvider;

    public CreateNotificationService_MembersInjector(Provider<CommsIdentityManager> provider, Provider<CommsNotificationManager> provider2, Provider<ModeSwitchHelper> provider3, Provider<CommsActivityMonitor> provider4, Provider<InboundAnnouncementCoordinator> provider5, Provider<MetricsService> provider6) {
        this.commsIdentityManagerProvider = provider;
        this.commsNotificationManagerProvider = provider2;
        this.modeSwitchHelperProvider = provider3;
        this.activityMonitorProvider = provider4;
        this.mAnnouncementCoordinatorProvider = provider5;
        this.mMetricsServiceProvider = provider6;
    }

    public static MembersInjector<CreateNotificationService> create(Provider<CommsIdentityManager> provider, Provider<CommsNotificationManager> provider2, Provider<ModeSwitchHelper> provider3, Provider<CommsActivityMonitor> provider4, Provider<InboundAnnouncementCoordinator> provider5, Provider<MetricsService> provider6) {
        return new CreateNotificationService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectActivityMonitor(CreateNotificationService createNotificationService, CommsActivityMonitor commsActivityMonitor) {
        createNotificationService.activityMonitor = commsActivityMonitor;
    }

    public static void injectCommsIdentityManager(CreateNotificationService createNotificationService, CommsIdentityManager commsIdentityManager) {
        createNotificationService.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsNotificationManager(CreateNotificationService createNotificationService, CommsNotificationManager commsNotificationManager) {
        createNotificationService.commsNotificationManager = commsNotificationManager;
    }

    public static void injectMAnnouncementCoordinator(CreateNotificationService createNotificationService, InboundAnnouncementCoordinator inboundAnnouncementCoordinator) {
        createNotificationService.mAnnouncementCoordinator = inboundAnnouncementCoordinator;
    }

    public static void injectMMetricsService(CreateNotificationService createNotificationService, MetricsService metricsService) {
        createNotificationService.mMetricsService = metricsService;
    }

    public static void injectModeSwitchHelper(CreateNotificationService createNotificationService, ModeSwitchHelper modeSwitchHelper) {
        createNotificationService.modeSwitchHelper = modeSwitchHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CreateNotificationService createNotificationService) {
        createNotificationService.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        createNotificationService.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        createNotificationService.modeSwitchHelper = this.modeSwitchHelperProvider.mo10268get();
        createNotificationService.activityMonitor = this.activityMonitorProvider.mo10268get();
        createNotificationService.mAnnouncementCoordinator = this.mAnnouncementCoordinatorProvider.mo10268get();
        createNotificationService.mMetricsService = this.mMetricsServiceProvider.mo10268get();
    }
}
