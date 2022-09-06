package com.amazon.alexa.handsfree.settings.locale;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppLocalesProvider_Factory implements Factory<VoiceAppLocalesProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;

    public VoiceAppLocalesProvider_Factory(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        this.contextProvider = provider;
        this.metricsBuilderProvider = provider2;
    }

    public static VoiceAppLocalesProvider_Factory create(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new VoiceAppLocalesProvider_Factory(provider, provider2);
    }

    public static VoiceAppLocalesProvider newVoiceAppLocalesProvider(Context context, MetricsBuilderProvider metricsBuilderProvider) {
        return new VoiceAppLocalesProvider(context, metricsBuilderProvider);
    }

    public static VoiceAppLocalesProvider provideInstance(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2) {
        return new VoiceAppLocalesProvider(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceAppLocalesProvider mo10268get() {
        return provideInstance(this.contextProvider, this.metricsBuilderProvider);
    }
}
