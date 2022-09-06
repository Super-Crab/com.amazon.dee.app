package com.amazon.alexa.alertsca;

import android.content.Context;
import android.os.Handler;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.google.gson.Gson;
import dagger.internal.Factory;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsAuthority_Factory implements Factory<AlertsAuthority> {
    private final Provider<AlertView> alertViewProvider;
    private final Provider<AlertsAnalyzer> alertsAnalyzerProvider;
    private final Provider<AlertsEventBus> alertsEventBusProvider;
    private final Provider<AlertsExoPlayer> alertsExoPlayerProvider;
    private final Provider<AlertsScheduler> alertsSchedulerProvider;
    private final Provider<AlertsStore> alertsStoreProvider;
    private final Provider<AlexaEventSender> alexaEventSenderProvider;
    private final Provider<AlexaInteractionScheduler> alexaInteractionSchedulerProvider;
    private final Provider<AudioFocusManager> audioFocusManagerProvider;
    private final Provider<ConnectedAccessoryInquirer> connectedAccessoryInquirerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<ScheduledExecutorService> scheduledExecutorServiceProvider;
    private final Provider<ScoAuthority> scoAuthorityProvider;

    public AlertsAuthority_Factory(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<AlertsScheduler> provider3, Provider<AlertsStore> provider4, Provider<AlertsAnalyzer> provider5, Provider<Gson> provider6, Provider<AlertsExoPlayer> provider7, Provider<AudioFocusManager> provider8, Provider<AlexaEventSender> provider9, Provider<AlexaInteractionScheduler> provider10, Provider<ScheduledExecutorService> provider11, Provider<AlertView> provider12, Provider<Handler> provider13, Provider<ConnectedAccessoryInquirer> provider14, Provider<ScoAuthority> provider15, Provider<MetricsService> provider16) {
        this.contextProvider = provider;
        this.alertsEventBusProvider = provider2;
        this.alertsSchedulerProvider = provider3;
        this.alertsStoreProvider = provider4;
        this.alertsAnalyzerProvider = provider5;
        this.gsonProvider = provider6;
        this.alertsExoPlayerProvider = provider7;
        this.audioFocusManagerProvider = provider8;
        this.alexaEventSenderProvider = provider9;
        this.alexaInteractionSchedulerProvider = provider10;
        this.scheduledExecutorServiceProvider = provider11;
        this.alertViewProvider = provider12;
        this.handlerProvider = provider13;
        this.connectedAccessoryInquirerProvider = provider14;
        this.scoAuthorityProvider = provider15;
        this.metricsServiceProvider = provider16;
    }

    public static AlertsAuthority_Factory create(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<AlertsScheduler> provider3, Provider<AlertsStore> provider4, Provider<AlertsAnalyzer> provider5, Provider<Gson> provider6, Provider<AlertsExoPlayer> provider7, Provider<AudioFocusManager> provider8, Provider<AlexaEventSender> provider9, Provider<AlexaInteractionScheduler> provider10, Provider<ScheduledExecutorService> provider11, Provider<AlertView> provider12, Provider<Handler> provider13, Provider<ConnectedAccessoryInquirer> provider14, Provider<ScoAuthority> provider15, Provider<MetricsService> provider16) {
        return new AlertsAuthority_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16);
    }

    public static AlertsAuthority newAlertsAuthority(Context context, AlertsEventBus alertsEventBus, AlertsScheduler alertsScheduler, AlertsStore alertsStore, AlertsAnalyzer alertsAnalyzer, Gson gson, AlertsExoPlayer alertsExoPlayer, AudioFocusManager audioFocusManager, AlexaEventSender alexaEventSender, AlexaInteractionScheduler alexaInteractionScheduler, ScheduledExecutorService scheduledExecutorService, AlertView alertView, Handler handler, ConnectedAccessoryInquirer connectedAccessoryInquirer, ScoAuthority scoAuthority, MetricsService metricsService) {
        return new AlertsAuthority(context, alertsEventBus, alertsScheduler, alertsStore, alertsAnalyzer, gson, alertsExoPlayer, audioFocusManager, alexaEventSender, alexaInteractionScheduler, scheduledExecutorService, alertView, handler, connectedAccessoryInquirer, scoAuthority, metricsService);
    }

    public static AlertsAuthority provideInstance(Provider<Context> provider, Provider<AlertsEventBus> provider2, Provider<AlertsScheduler> provider3, Provider<AlertsStore> provider4, Provider<AlertsAnalyzer> provider5, Provider<Gson> provider6, Provider<AlertsExoPlayer> provider7, Provider<AudioFocusManager> provider8, Provider<AlexaEventSender> provider9, Provider<AlexaInteractionScheduler> provider10, Provider<ScheduledExecutorService> provider11, Provider<AlertView> provider12, Provider<Handler> provider13, Provider<ConnectedAccessoryInquirer> provider14, Provider<ScoAuthority> provider15, Provider<MetricsService> provider16) {
        return new AlertsAuthority(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsAuthority mo10268get() {
        return provideInstance(this.contextProvider, this.alertsEventBusProvider, this.alertsSchedulerProvider, this.alertsStoreProvider, this.alertsAnalyzerProvider, this.gsonProvider, this.alertsExoPlayerProvider, this.audioFocusManagerProvider, this.alexaEventSenderProvider, this.alexaInteractionSchedulerProvider, this.scheduledExecutorServiceProvider, this.alertViewProvider, this.handlerProvider, this.connectedAccessoryInquirerProvider, this.scoAuthorityProvider, this.metricsServiceProvider);
    }
}
