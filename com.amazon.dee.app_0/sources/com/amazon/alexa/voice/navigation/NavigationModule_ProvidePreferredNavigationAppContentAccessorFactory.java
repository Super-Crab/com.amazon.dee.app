package com.amazon.alexa.voice.navigation;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory implements Factory<PreferredNavigationAppContentAccessor> {
    private final Provider<Context> contextProvider;

    public NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory create(Provider<Context> provider) {
        return new NavigationModule_ProvidePreferredNavigationAppContentAccessorFactory(provider);
    }

    public static PreferredNavigationAppContentAccessor provideInstance(Provider<Context> provider) {
        return proxyProvidePreferredNavigationAppContentAccessor(provider.mo10268get());
    }

    public static PreferredNavigationAppContentAccessor proxyProvidePreferredNavigationAppContentAccessor(Context context) {
        return (PreferredNavigationAppContentAccessor) Preconditions.checkNotNull(NavigationModule.providePreferredNavigationAppContentAccessor(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferredNavigationAppContentAccessor mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
