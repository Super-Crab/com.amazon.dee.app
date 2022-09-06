package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import rx.Scheduler;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideLocationCacheFactory implements Factory<Cache<AppDataCacheEntry>> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsServiceV2> metricsServiceProvider;
    private final ServiceModule module;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<ExecutorService> shortLivedTaskExecutorProvider;

    public ServiceModule_ProvideLocationCacheFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.metricsServiceProvider = provider2;
        this.shortLivedTaskExecutorProvider = provider3;
        this.schedulerProvider = provider4;
    }

    public static ServiceModule_ProvideLocationCacheFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return new ServiceModule_ProvideLocationCacheFactory(serviceModule, provider, provider2, provider3, provider4);
    }

    public static Cache<AppDataCacheEntry> provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return proxyProvideLocationCache(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static Cache<AppDataCacheEntry> proxyProvideLocationCache(ServiceModule serviceModule, Context context, MetricsServiceV2 metricsServiceV2, ExecutorService executorService, Scheduler scheduler) {
        return (Cache) Preconditions.checkNotNull(serviceModule.provideLocationCache(context, metricsServiceV2, executorService, scheduler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Cache<AppDataCacheEntry> mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.metricsServiceProvider, this.shortLivedTaskExecutorProvider, this.schedulerProvider);
    }
}
