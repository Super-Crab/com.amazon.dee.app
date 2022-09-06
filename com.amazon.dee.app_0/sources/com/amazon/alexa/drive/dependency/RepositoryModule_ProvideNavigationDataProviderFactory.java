package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.NavigationDataProvider;
import com.amazon.alexa.drive.navigation.NavigationRepo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideNavigationDataProviderFactory implements Factory<NavigationDataProvider> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;
    private final Provider<NavigationMetrics> navigationMetricsProvider;
    private final Provider<NavigationRepo> navigationRepoProvider;

    public RepositoryModule_ProvideNavigationDataProviderFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationRepo> provider2, Provider<NavigationMetrics> provider3) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.navigationRepoProvider = provider2;
        this.navigationMetricsProvider = provider3;
    }

    public static RepositoryModule_ProvideNavigationDataProviderFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationRepo> provider2, Provider<NavigationMetrics> provider3) {
        return new RepositoryModule_ProvideNavigationDataProviderFactory(repositoryModule, provider, provider2, provider3);
    }

    public static NavigationDataProvider provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationRepo> provider2, Provider<NavigationMetrics> provider3) {
        return proxyProvideNavigationDataProvider(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static NavigationDataProvider proxyProvideNavigationDataProvider(RepositoryModule repositoryModule, Context context, NavigationRepo navigationRepo, NavigationMetrics navigationMetrics) {
        return (NavigationDataProvider) Preconditions.checkNotNull(repositoryModule.provideNavigationDataProvider(context, navigationRepo, navigationMetrics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavigationDataProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.navigationRepoProvider, this.navigationMetricsProvider);
    }
}
