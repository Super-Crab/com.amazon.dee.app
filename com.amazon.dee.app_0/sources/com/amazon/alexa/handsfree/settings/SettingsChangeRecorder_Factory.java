package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SettingsChangeRecorder_Factory implements Factory<SettingsChangeRecorder> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;

    public SettingsChangeRecorder_Factory(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        this.contextProvider = provider;
        this.metricsBuilderProvider = provider2;
    }

    public static SettingsChangeRecorder_Factory create(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new SettingsChangeRecorder_Factory(provider, provider2);
    }

    public static SettingsChangeRecorder newSettingsChangeRecorder(Context context, MetricsBuilderProvider metricsBuilderProvider) {
        return new SettingsChangeRecorder(context, metricsBuilderProvider);
    }

    public static SettingsChangeRecorder provideInstance(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new SettingsChangeRecorder(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SettingsChangeRecorder mo10268get() {
        return provideInstance(this.contextProvider, this.metricsBuilderProvider);
    }
}
