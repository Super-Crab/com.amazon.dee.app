package com.amazon.alexa.voice.navigation;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class NavigationModule_ProvidePreferredNavigationAppRepositoryFactory implements Factory<PreferredNavigationAppRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<PreferredNavigationAppContentAccessor> preferredNavigationAppContentAccessorProvider;

    public NavigationModule_ProvidePreferredNavigationAppRepositoryFactory(Provider<Context> provider, Provider<PreferredNavigationAppContentAccessor> provider2) {
        this.contextProvider = provider;
        this.preferredNavigationAppContentAccessorProvider = provider2;
    }

    public static NavigationModule_ProvidePreferredNavigationAppRepositoryFactory create(Provider<Context> provider, Provider<PreferredNavigationAppContentAccessor> provider2) {
        return new NavigationModule_ProvidePreferredNavigationAppRepositoryFactory(provider, provider2);
    }

    public static PreferredNavigationAppRepository provideInstance(Provider<Context> provider, Provider<PreferredNavigationAppContentAccessor> provider2) {
        return proxyProvidePreferredNavigationAppRepository(provider.mo10268get(), provider2.mo10268get());
    }

    public static PreferredNavigationAppRepository proxyProvidePreferredNavigationAppRepository(Context context, Object obj) {
        return (PreferredNavigationAppRepository) Preconditions.checkNotNull(NavigationModule.providePreferredNavigationAppRepository(context, (PreferredNavigationAppContentAccessor) obj), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferredNavigationAppRepository mo10268get() {
        return provideInstance(this.contextProvider, this.preferredNavigationAppContentAccessorProvider);
    }
}
