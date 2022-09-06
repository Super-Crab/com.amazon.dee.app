package com.amazon.alexa.alertsca.notification;

import android.app.NotificationManager;
import android.content.Context;
import com.amazon.alexa.alertsca.AlertsEventBus;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.device.api.DeviceInformation;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaAlertsNotificationManager_Factory implements Factory<AlexaAlertsNotificationManager> {
    private final Provider<NotificationManager> androidNotificationManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<AlertsEventBus> eventBusProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<ScheduledExecutorService> sharedScheduledExecutorProvider;

    public AlexaAlertsNotificationManager_Factory(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<NotificationManager> provider3, Provider<MetricsService> provider4, Provider<ScheduledExecutorService> provider5, Provider<DeviceInformation> provider6) {
        this.contextProvider = provider;
        this.eventBusProvider = provider2;
        this.androidNotificationManagerProvider = provider3;
        this.metricsServiceProvider = provider4;
        this.sharedScheduledExecutorProvider = provider5;
        this.deviceInformationProvider = provider6;
    }

    public static AlexaAlertsNotificationManager_Factory create(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<NotificationManager> provider3, Provider<MetricsService> provider4, Provider<ScheduledExecutorService> provider5, Provider<DeviceInformation> provider6) {
        return new AlexaAlertsNotificationManager_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static AlexaAlertsNotificationManager newAlexaAlertsNotificationManager(Context context, AlertsEventBus alertsEventBus, NotificationManager notificationManager, MetricsService metricsService, ScheduledExecutorService scheduledExecutorService, DeviceInformation deviceInformation) {
        return new AlexaAlertsNotificationManager(context, alertsEventBus, notificationManager, metricsService, scheduledExecutorService, deviceInformation);
    }

    public static AlexaAlertsNotificationManager provideInstance(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<NotificationManager> provider3, Provider<MetricsService> provider4, Provider<ScheduledExecutorService> provider5, Provider<DeviceInformation> provider6) {
        return new AlexaAlertsNotificationManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaAlertsNotificationManager mo10268get() {
        return provideInstance(this.contextProvider, this.eventBusProvider, this.androidNotificationManagerProvider, this.metricsServiceProvider, this.sharedScheduledExecutorProvider, this.deviceInformationProvider);
    }
}
