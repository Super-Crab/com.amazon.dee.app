package com.amazon.alexa.alertsca;

import dagger.internal.Factory;
/* loaded from: classes6.dex */
public final class AlertsAnalyzer_Factory implements Factory<AlertsAnalyzer> {
    private static final AlertsAnalyzer_Factory INSTANCE = new AlertsAnalyzer_Factory();

    public static AlertsAnalyzer_Factory create() {
        return INSTANCE;
    }

    public static AlertsAnalyzer newAlertsAnalyzer() {
        return new AlertsAnalyzer();
    }

    public static AlertsAnalyzer provideInstance() {
        return new AlertsAnalyzer();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsAnalyzer mo10268get() {
        return provideInstance();
    }
}
