package com.amazon.alexa.alertsca;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertReschedulingBootCompletedReceiver_MembersInjector implements MembersInjector<AlertReschedulingBootCompletedReceiver> {
    private final Provider<AlertsAnalyzer> alertsAnalyzerProvider;
    private final Provider<AlertsScheduler> alertsSchedulerProvider;
    private final Provider<AlertsStore> alertsStoreProvider;

    public AlertReschedulingBootCompletedReceiver_MembersInjector(Provider<AlertsStore> provider, Provider<AlertsAnalyzer> provider2, Provider<AlertsScheduler> provider3) {
        this.alertsStoreProvider = provider;
        this.alertsAnalyzerProvider = provider2;
        this.alertsSchedulerProvider = provider3;
    }

    public static MembersInjector<AlertReschedulingBootCompletedReceiver> create(Provider<AlertsStore> provider, Provider<AlertsAnalyzer> provider2, Provider<AlertsScheduler> provider3) {
        return new AlertReschedulingBootCompletedReceiver_MembersInjector(provider, provider2, provider3);
    }

    public static void injectAlertsAnalyzer(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver, AlertsAnalyzer alertsAnalyzer) {
        alertReschedulingBootCompletedReceiver.alertsAnalyzer = alertsAnalyzer;
    }

    public static void injectAlertsScheduler(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver, AlertsScheduler alertsScheduler) {
        alertReschedulingBootCompletedReceiver.alertsScheduler = alertsScheduler;
    }

    public static void injectAlertsStore(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver, AlertsStore alertsStore) {
        alertReschedulingBootCompletedReceiver.alertsStore = alertsStore;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertReschedulingBootCompletedReceiver alertReschedulingBootCompletedReceiver) {
        injectAlertsStore(alertReschedulingBootCompletedReceiver, this.alertsStoreProvider.mo10268get());
        injectAlertsAnalyzer(alertReschedulingBootCompletedReceiver, this.alertsAnalyzerProvider.mo10268get());
        injectAlertsScheduler(alertReschedulingBootCompletedReceiver, this.alertsSchedulerProvider.mo10268get());
    }
}
