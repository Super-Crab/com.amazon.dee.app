package com.amazon.alexa.voice.alerts;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlertsModule_ProvideAlertsFeatureEnablerFactory implements Factory<AlertsFeatureEnabler> {
    private final Provider<Context> contextProvider;

    public AlertsModule_ProvideAlertsFeatureEnablerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AlertsModule_ProvideAlertsFeatureEnablerFactory create(Provider<Context> provider) {
        return new AlertsModule_ProvideAlertsFeatureEnablerFactory(provider);
    }

    public static AlertsFeatureEnabler provideInstance(Provider<Context> provider) {
        return proxyProvideAlertsFeatureEnabler(provider.mo10268get());
    }

    public static AlertsFeatureEnabler proxyProvideAlertsFeatureEnabler(Context context) {
        return (AlertsFeatureEnabler) Preconditions.checkNotNull(AlertsModule.provideAlertsFeatureEnabler(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsFeatureEnabler mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
