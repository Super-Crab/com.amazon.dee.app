package com.amazon.alexa.auto.navigation;

import android.content.Context;
import android.content.pm.PackageManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PreferredNavigationAppContentProviderModule_ProvidePackageManagerFactory implements Factory<PackageManager> {
    private final Provider<Context> contextProvider;
    private final PreferredNavigationAppContentProviderModule module;

    public PreferredNavigationAppContentProviderModule_ProvidePackageManagerFactory(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule, Provider<Context> provider) {
        this.module = preferredNavigationAppContentProviderModule;
        this.contextProvider = provider;
    }

    public static PreferredNavigationAppContentProviderModule_ProvidePackageManagerFactory create(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule, Provider<Context> provider) {
        return new PreferredNavigationAppContentProviderModule_ProvidePackageManagerFactory(preferredNavigationAppContentProviderModule, provider);
    }

    public static PackageManager provideInstance(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule, Provider<Context> provider) {
        return proxyProvidePackageManager(preferredNavigationAppContentProviderModule, provider.mo10268get());
    }

    public static PackageManager proxyProvidePackageManager(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule, Context context) {
        return (PackageManager) Preconditions.checkNotNull(preferredNavigationAppContentProviderModule.providePackageManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PackageManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
