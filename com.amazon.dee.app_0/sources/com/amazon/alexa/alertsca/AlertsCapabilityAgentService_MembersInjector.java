package com.amazon.alexa.alertsca;

import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.networking.ConnectivityAuthority;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.google.gson.Gson;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsCapabilityAgentService_MembersInjector implements MembersInjector<AlertsCapabilityAgentService> {
    private final Provider<AlertsCapabilityAgent> alertsCapabilityAgentProvider;
    private final Provider<AlertsEventBus> alertsEventBusProvider;
    private final Provider<AlexaAlertsNotificationManager> alexaAlertsNotificationManagerProvider;
    private final Provider<AlexaMobileFrameworkApis> alexaMobileFrameworkApisProvider;
    private final Provider<ConnectivityAuthority> connectivityAuthorityProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<MetricsService> metricsServiceProvider;

    public AlertsCapabilityAgentService_MembersInjector(Provider<AlertsEventBus> provider, Provider<Gson> provider2, Provider<AlexaMobileFrameworkApis> provider3, Provider<AlexaAlertsNotificationManager> provider4, Provider<AlertsCapabilityAgent> provider5, Provider<ConnectivityAuthority> provider6, Provider<MetricsService> provider7) {
        this.alertsEventBusProvider = provider;
        this.gsonProvider = provider2;
        this.alexaMobileFrameworkApisProvider = provider3;
        this.alexaAlertsNotificationManagerProvider = provider4;
        this.alertsCapabilityAgentProvider = provider5;
        this.connectivityAuthorityProvider = provider6;
        this.metricsServiceProvider = provider7;
    }

    public static MembersInjector<AlertsCapabilityAgentService> create(Provider<AlertsEventBus> provider, Provider<Gson> provider2, Provider<AlexaMobileFrameworkApis> provider3, Provider<AlexaAlertsNotificationManager> provider4, Provider<AlertsCapabilityAgent> provider5, Provider<ConnectivityAuthority> provider6, Provider<MetricsService> provider7) {
        return new AlertsCapabilityAgentService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectAlertsCapabilityAgent(AlertsCapabilityAgentService alertsCapabilityAgentService, AlertsCapabilityAgent alertsCapabilityAgent) {
        alertsCapabilityAgentService.alertsCapabilityAgent = alertsCapabilityAgent;
    }

    public static void injectAlertsEventBus(AlertsCapabilityAgentService alertsCapabilityAgentService, AlertsEventBus alertsEventBus) {
        alertsCapabilityAgentService.alertsEventBus = alertsEventBus;
    }

    public static void injectAlexaAlertsNotificationManager(AlertsCapabilityAgentService alertsCapabilityAgentService, AlexaAlertsNotificationManager alexaAlertsNotificationManager) {
        alertsCapabilityAgentService.alexaAlertsNotificationManager = alexaAlertsNotificationManager;
    }

    public static void injectAlexaMobileFrameworkApis(AlertsCapabilityAgentService alertsCapabilityAgentService, AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        alertsCapabilityAgentService.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
    }

    public static void injectConnectivityAuthority(AlertsCapabilityAgentService alertsCapabilityAgentService, ConnectivityAuthority connectivityAuthority) {
        alertsCapabilityAgentService.connectivityAuthority = connectivityAuthority;
    }

    public static void injectGson(AlertsCapabilityAgentService alertsCapabilityAgentService, Gson gson) {
        alertsCapabilityAgentService.gson = gson;
    }

    public static void injectMetricsService(AlertsCapabilityAgentService alertsCapabilityAgentService, MetricsService metricsService) {
        alertsCapabilityAgentService.metricsService = metricsService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertsCapabilityAgentService alertsCapabilityAgentService) {
        injectAlertsEventBus(alertsCapabilityAgentService, this.alertsEventBusProvider.mo10268get());
        injectGson(alertsCapabilityAgentService, this.gsonProvider.mo10268get());
        injectAlexaMobileFrameworkApis(alertsCapabilityAgentService, this.alexaMobileFrameworkApisProvider.mo10268get());
        injectAlexaAlertsNotificationManager(alertsCapabilityAgentService, this.alexaAlertsNotificationManagerProvider.mo10268get());
        injectAlertsCapabilityAgent(alertsCapabilityAgentService, this.alertsCapabilityAgentProvider.mo10268get());
        injectConnectivityAuthority(alertsCapabilityAgentService, this.connectivityAuthorityProvider.mo10268get());
        injectMetricsService(alertsCapabilityAgentService, this.metricsServiceProvider.mo10268get());
    }
}
