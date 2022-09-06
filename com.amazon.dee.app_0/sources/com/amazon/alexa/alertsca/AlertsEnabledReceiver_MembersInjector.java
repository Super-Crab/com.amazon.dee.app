package com.amazon.alexa.alertsca;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsEnabledReceiver_MembersInjector implements MembersInjector<AlertsEnabledReceiver> {
    private final Provider<AlertsCapabilityAgent> alertsCapabilityAgentProvider;

    public AlertsEnabledReceiver_MembersInjector(Provider<AlertsCapabilityAgent> provider) {
        this.alertsCapabilityAgentProvider = provider;
    }

    public static MembersInjector<AlertsEnabledReceiver> create(Provider<AlertsCapabilityAgent> provider) {
        return new AlertsEnabledReceiver_MembersInjector(provider);
    }

    public static void injectAlertsCapabilityAgent(AlertsEnabledReceiver alertsEnabledReceiver, AlertsCapabilityAgent alertsCapabilityAgent) {
        alertsEnabledReceiver.alertsCapabilityAgent = alertsCapabilityAgent;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertsEnabledReceiver alertsEnabledReceiver) {
        injectAlertsCapabilityAgent(alertsEnabledReceiver, this.alertsCapabilityAgentProvider.mo10268get());
    }
}
