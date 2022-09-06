package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import com.amazon.photos.discovery.internal.util.WorkerHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideWorkerHelperFactory implements Factory<WorkerHelper> {
    private final Provider<Logger> loggerProvider;
    private final Provider<Metrics> metricsProvider;
    private final DiscoveryModule module;
    private final Provider<SystemUtil> systemUtilProvider;

    public DiscoveryModule_ProvideWorkerHelperFactory(DiscoveryModule discoveryModule, Provider<SystemUtil> provider, Provider<Logger> provider2, Provider<Metrics> provider3) {
        this.module = discoveryModule;
        this.systemUtilProvider = provider;
        this.loggerProvider = provider2;
        this.metricsProvider = provider3;
    }

    public static DiscoveryModule_ProvideWorkerHelperFactory create(DiscoveryModule discoveryModule, Provider<SystemUtil> provider, Provider<Logger> provider2, Provider<Metrics> provider3) {
        return new DiscoveryModule_ProvideWorkerHelperFactory(discoveryModule, provider, provider2, provider3);
    }

    public static WorkerHelper provideWorkerHelper(DiscoveryModule discoveryModule, SystemUtil systemUtil, Logger logger, Metrics metrics) {
        return (WorkerHelper) Preconditions.checkNotNull(discoveryModule.provideWorkerHelper(systemUtil, logger, metrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkerHelper mo10268get() {
        return provideWorkerHelper(this.module, this.systemUtilProvider.mo10268get(), this.loggerProvider.mo10268get(), this.metricsProvider.mo10268get());
    }
}
