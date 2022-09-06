package com.amazon.alexa.voice.alerts;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlertsFeatureEnabler_Factory implements Factory<AlertsFeatureEnabler> {
    private final Provider<Context> contextProvider;

    public AlertsFeatureEnabler_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AlertsFeatureEnabler_Factory create(Provider<Context> provider) {
        return new AlertsFeatureEnabler_Factory(provider);
    }

    public static AlertsFeatureEnabler newAlertsFeatureEnabler(Context context) {
        return new AlertsFeatureEnabler(context);
    }

    public static AlertsFeatureEnabler provideInstance(Provider<Context> provider) {
        return new AlertsFeatureEnabler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsFeatureEnabler mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
