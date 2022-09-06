package com.amazon.alexa.alertsca;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsCapabilityAgent_Factory implements Factory<AlertsCapabilityAgent> {
    private final Provider<AlertsAuthority> alertsAuthorityProvider;
    private final Provider<AlertsEventBus> alertsEventBusProvider;
    private final Provider<AlexaEventSender> alexaEventSenderProvider;
    private final Provider<Context> contextProvider;
    private final Provider<Gson> gsonProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public AlertsCapabilityAgent_Factory(Provider<AlertsAuthority> provider, Provider<Gson> provider2, Provider<AlertsEventBus> provider3, Provider<Context> provider4, Provider<AlexaEventSender> provider5, Provider<MetricsService> provider6, Provider<SharedPreferences> provider7) {
        this.alertsAuthorityProvider = provider;
        this.gsonProvider = provider2;
        this.alertsEventBusProvider = provider3;
        this.contextProvider = provider4;
        this.alexaEventSenderProvider = provider5;
        this.metricsServiceProvider = provider6;
        this.sharedPreferencesProvider = provider7;
    }

    public static AlertsCapabilityAgent_Factory create(Provider<AlertsAuthority> provider, Provider<Gson> provider2, Provider<AlertsEventBus> provider3, Provider<Context> provider4, Provider<AlexaEventSender> provider5, Provider<MetricsService> provider6, Provider<SharedPreferences> provider7) {
        return new AlertsCapabilityAgent_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static AlertsCapabilityAgent newAlertsCapabilityAgent(AlertsAuthority alertsAuthority, Gson gson, AlertsEventBus alertsEventBus, Context context, AlexaEventSender alexaEventSender, MetricsService metricsService, Lazy<SharedPreferences> lazy) {
        return new AlertsCapabilityAgent(alertsAuthority, gson, alertsEventBus, context, alexaEventSender, metricsService, lazy);
    }

    public static AlertsCapabilityAgent provideInstance(Provider<AlertsAuthority> provider, Provider<Gson> provider2, Provider<AlertsEventBus> provider3, Provider<Context> provider4, Provider<AlexaEventSender> provider5, Provider<MetricsService> provider6, Provider<SharedPreferences> provider7) {
        return new AlertsCapabilityAgent(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), DoubleCheck.lazy(provider7));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsCapabilityAgent mo10268get() {
        return provideInstance(this.alertsAuthorityProvider, this.gsonProvider, this.alertsEventBusProvider, this.contextProvider, this.alexaEventSenderProvider, this.metricsServiceProvider, this.sharedPreferencesProvider);
    }
}
