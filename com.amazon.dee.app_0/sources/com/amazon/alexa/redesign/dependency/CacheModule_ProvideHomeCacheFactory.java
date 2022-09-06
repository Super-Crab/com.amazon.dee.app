package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import rx.Scheduler;
/* loaded from: classes10.dex */
public final class CacheModule_ProvideHomeCacheFactory implements Factory<Cache<AppDataCacheEntry>> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsServiceV2> metricsServiceProvider;
    private final CacheModule module;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<ExecutorService> shortLivedTaskExecutorProvider;

    public CacheModule_ProvideHomeCacheFactory(CacheModule cacheModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        this.module = cacheModule;
        this.contextProvider = provider;
        this.metricsServiceProvider = provider2;
        this.shortLivedTaskExecutorProvider = provider3;
        this.schedulerProvider = provider4;
    }

    public static CacheModule_ProvideHomeCacheFactory create(CacheModule cacheModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return new CacheModule_ProvideHomeCacheFactory(cacheModule, provider, provider2, provider3, provider4);
    }

    public static Cache<AppDataCacheEntry> provideInstance(CacheModule cacheModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return proxyProvideHomeCache(cacheModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static Cache<AppDataCacheEntry> proxyProvideHomeCache(CacheModule cacheModule, Context context, MetricsServiceV2 metricsServiceV2, ExecutorService executorService, Scheduler scheduler) {
        return (Cache) Preconditions.checkNotNull(cacheModule.provideHomeCache(context, metricsServiceV2, executorService, scheduler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Cache<AppDataCacheEntry> mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.metricsServiceProvider, this.shortLivedTaskExecutorProvider, this.schedulerProvider);
    }
}
