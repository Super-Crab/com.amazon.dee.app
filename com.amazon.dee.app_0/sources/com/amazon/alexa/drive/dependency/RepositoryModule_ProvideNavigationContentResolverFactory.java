package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.navigation.PreferredNavigationAppContentResolver;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideNavigationContentResolverFactory implements Factory<PreferredNavigationAppContentResolver> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideNavigationContentResolverFactory(RepositoryModule repositoryModule, Provider<Context> provider) {
        this.module = repositoryModule;
        this.contextProvider = provider;
    }

    public static RepositoryModule_ProvideNavigationContentResolverFactory create(RepositoryModule repositoryModule, Provider<Context> provider) {
        return new RepositoryModule_ProvideNavigationContentResolverFactory(repositoryModule, provider);
    }

    public static PreferredNavigationAppContentResolver provideInstance(RepositoryModule repositoryModule, Provider<Context> provider) {
        return proxyProvideNavigationContentResolver(repositoryModule, provider.mo10268get());
    }

    public static PreferredNavigationAppContentResolver proxyProvideNavigationContentResolver(RepositoryModule repositoryModule, Context context) {
        return (PreferredNavigationAppContentResolver) Preconditions.checkNotNull(repositoryModule.provideNavigationContentResolver(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferredNavigationAppContentResolver mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
