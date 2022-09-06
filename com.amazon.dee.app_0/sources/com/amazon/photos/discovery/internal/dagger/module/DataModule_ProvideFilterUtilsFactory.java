package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DataModule_ProvideFilterUtilsFactory implements Factory<FilterUtils> {
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final DataModule module;

    public DataModule_ProvideFilterUtilsFactory(DataModule dataModule, Provider<Metrics> provider, Provider<Logger> provider2) {
        this.module = dataModule;
        this.metricsProvider = provider;
        this.loggerProvider = provider2;
    }

    public static DataModule_ProvideFilterUtilsFactory create(DataModule dataModule, Provider<Metrics> provider, Provider<Logger> provider2) {
        return new DataModule_ProvideFilterUtilsFactory(dataModule, provider, provider2);
    }

    public static FilterUtils provideFilterUtils(DataModule dataModule, Metrics metrics, Logger logger) {
        return (FilterUtils) Preconditions.checkNotNull(dataModule.provideFilterUtils(metrics, logger), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FilterUtils mo10268get() {
        return provideFilterUtils(this.module, this.metricsProvider.mo10268get(), this.loggerProvider.mo10268get());
    }
}
