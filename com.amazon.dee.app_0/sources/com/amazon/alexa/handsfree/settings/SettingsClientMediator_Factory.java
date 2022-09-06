package com.amazon.alexa.handsfree.settings;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class SettingsClientMediator_Factory implements Factory<SettingsClientMediator> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<RemoteConfigManager> remoteConfigManagerProvider;

    public SettingsClientMediator_Factory(Provider<Context> provider, Provider<RemoteConfigManager> provider2, Provider<MetricsBuilderProvider> provider3) {
        this.contextProvider = provider;
        this.remoteConfigManagerProvider = provider2;
        this.metricsBuilderProvider = provider3;
    }

    public static SettingsClientMediator_Factory create(Provider<Context> provider, Provider<RemoteConfigManager> provider2, Provider<MetricsBuilderProvider> provider3) {
        return new SettingsClientMediator_Factory(provider, provider2, provider3);
    }

    public static SettingsClientMediator newSettingsClientMediator(Context context, RemoteConfigManager remoteConfigManager, MetricsBuilderProvider metricsBuilderProvider) {
        return new SettingsClientMediator(context, remoteConfigManager, metricsBuilderProvider);
    }

    public static SettingsClientMediator provideInstance(Provider<Context> provider, Provider<RemoteConfigManager> provider2, Provider<MetricsBuilderProvider> provider3) {
        return new SettingsClientMediator(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SettingsClientMediator mo10268get() {
        return provideInstance(this.contextProvider, this.remoteConfigManagerProvider, this.metricsBuilderProvider);
    }
}
