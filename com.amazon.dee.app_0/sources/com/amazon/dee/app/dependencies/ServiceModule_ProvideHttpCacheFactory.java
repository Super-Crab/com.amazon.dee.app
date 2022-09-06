package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.HttpResponseWrapper;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;
import rx.Scheduler;
/* loaded from: classes12.dex */
public final class ServiceModule_ProvideHttpCacheFactory implements Factory<Cache<HttpResponseWrapper>> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsServiceV2> metricsServiceProvider;
    private final ServiceModule module;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<ExecutorService> shortLivedTaskExecutorProvider;

    public ServiceModule_ProvideHttpCacheFactory(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        this.module = serviceModule;
        this.contextProvider = provider;
        this.metricsServiceProvider = provider2;
        this.shortLivedTaskExecutorProvider = provider3;
        this.schedulerProvider = provider4;
    }

    public static ServiceModule_ProvideHttpCacheFactory create(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return new ServiceModule_ProvideHttpCacheFactory(serviceModule, provider, provider2, provider3, provider4);
    }

    public static Cache<HttpResponseWrapper> provideInstance(ServiceModule serviceModule, Provider<Context> provider, Provider<MetricsServiceV2> provider2, Provider<ExecutorService> provider3, Provider<Scheduler> provider4) {
        return proxyProvideHttpCache(serviceModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static Cache<HttpResponseWrapper> proxyProvideHttpCache(ServiceModule serviceModule, Context context, MetricsServiceV2 metricsServiceV2, ExecutorService executorService, Scheduler scheduler) {
        return (Cache) Preconditions.checkNotNull(serviceModule.provideHttpCache(context, metricsServiceV2, executorService, scheduler), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Cache<HttpResponseWrapper> mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.metricsServiceProvider, this.shortLivedTaskExecutorProvider, this.schedulerProvider);
    }
}
