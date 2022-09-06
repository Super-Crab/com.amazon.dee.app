package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.NavigationCardManager;
import com.amazon.alexa.drive.navigation.NavigationDataProvider;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideNavigationCardManagerFactory implements Factory<NavigationCardManager> {
    private final Provider<PreferredNavigationAppContentResolver> contentResolverProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeThemeManager> driveModeThemeManagerProvider;
    private final RepositoryModule module;
    private final Provider<NavigationDataProvider> navigationDataProvider;
    private final Provider<NavigationMetrics> navigationMetricsProvider;
    private final Provider<NetworkConnectivityManager> networkConnectivityManagerProvider;
    private final Provider<WeblabManager> weblabManagerProvider;

    public RepositoryModule_ProvideNavigationCardManagerFactory(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationDataProvider> provider2, Provider<PreferredNavigationAppContentResolver> provider3, Provider<NavigationMetrics> provider4, Provider<NetworkConnectivityManager> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        this.module = repositoryModule;
        this.contextProvider = provider;
        this.navigationDataProvider = provider2;
        this.contentResolverProvider = provider3;
        this.navigationMetricsProvider = provider4;
        this.networkConnectivityManagerProvider = provider5;
        this.driveModeThemeManagerProvider = provider6;
        this.weblabManagerProvider = provider7;
    }

    public static RepositoryModule_ProvideNavigationCardManagerFactory create(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationDataProvider> provider2, Provider<PreferredNavigationAppContentResolver> provider3, Provider<NavigationMetrics> provider4, Provider<NetworkConnectivityManager> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        return new RepositoryModule_ProvideNavigationCardManagerFactory(repositoryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static NavigationCardManager provideInstance(RepositoryModule repositoryModule, Provider<Context> provider, Provider<NavigationDataProvider> provider2, Provider<PreferredNavigationAppContentResolver> provider3, Provider<NavigationMetrics> provider4, Provider<NetworkConnectivityManager> provider5, Provider<DriveModeThemeManager> provider6, Provider<WeblabManager> provider7) {
        return proxyProvideNavigationCardManager(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static NavigationCardManager proxyProvideNavigationCardManager(RepositoryModule repositoryModule, Context context, NavigationDataProvider navigationDataProvider, PreferredNavigationAppContentResolver preferredNavigationAppContentResolver, NavigationMetrics navigationMetrics, NetworkConnectivityManager networkConnectivityManager, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        return (NavigationCardManager) Preconditions.checkNotNull(repositoryModule.provideNavigationCardManager(context, navigationDataProvider, preferredNavigationAppContentResolver, navigationMetrics, networkConnectivityManager, driveModeThemeManager, weblabManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NavigationCardManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.navigationDataProvider, this.contentResolverProvider, this.navigationMetricsProvider, this.networkConnectivityManagerProvider, this.driveModeThemeManagerProvider, this.weblabManagerProvider);
    }
}
