package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class MetricsModule_ProvideApplicationInformationProviderFactory implements Factory<ApplicationInformationProvider> {
    private final Provider<Context> contextProvider;
    private final MetricsModule module;

    public MetricsModule_ProvideApplicationInformationProviderFactory(MetricsModule metricsModule, Provider<Context> provider) {
        this.module = metricsModule;
        this.contextProvider = provider;
    }

    public static MetricsModule_ProvideApplicationInformationProviderFactory create(MetricsModule metricsModule, Provider<Context> provider) {
        return new MetricsModule_ProvideApplicationInformationProviderFactory(metricsModule, provider);
    }

    public static ApplicationInformationProvider provideInstance(MetricsModule metricsModule, Provider<Context> provider) {
        return proxyProvideApplicationInformationProvider(metricsModule, provider.mo10268get());
    }

    public static ApplicationInformationProvider proxyProvideApplicationInformationProvider(MetricsModule metricsModule, Context context) {
        return (ApplicationInformationProvider) Preconditions.checkNotNull(metricsModule.provideApplicationInformationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ApplicationInformationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
