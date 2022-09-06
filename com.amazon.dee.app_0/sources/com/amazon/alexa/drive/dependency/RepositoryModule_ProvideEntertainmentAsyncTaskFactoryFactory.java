package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.entertainment.interactor.EntertainmentAsyncTaskFactory;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideEntertainmentAsyncTaskFactoryFactory implements Factory<EntertainmentAsyncTaskFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<EntertainmentMetrics> entertainmentMetricsProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideEntertainmentAsyncTaskFactoryFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentMetrics> provider2) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.entertainmentMetricsProvider = provider2;
    }

    public static RepositoryModule_ProvideEntertainmentAsyncTaskFactoryFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentMetrics> provider2) {
        return new RepositoryModule_ProvideEntertainmentAsyncTaskFactoryFactory(repositoryModule, provider, provider2);
    }

    public static EntertainmentAsyncTaskFactory provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<EntertainmentMetrics> provider2) {
        return proxyProvideEntertainmentAsyncTaskFactory(repositoryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static EntertainmentAsyncTaskFactory proxyProvideEntertainmentAsyncTaskFactory(RepositoryModule repositoryModule, Context context, EntertainmentMetrics entertainmentMetrics) {
        return (EntertainmentAsyncTaskFactory) Preconditions.checkNotNull(repositoryModule.provideEntertainmentAsyncTaskFactory(context, entertainmentMetrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EntertainmentAsyncTaskFactory mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.entertainmentMetricsProvider);
    }
}
