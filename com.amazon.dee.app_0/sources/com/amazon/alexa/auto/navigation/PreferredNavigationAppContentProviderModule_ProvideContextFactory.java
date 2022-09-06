package com.amazon.alexa.auto.navigation;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class PreferredNavigationAppContentProviderModule_ProvideContextFactory implements Factory<Context> {
    private final PreferredNavigationAppContentProviderModule module;

    public PreferredNavigationAppContentProviderModule_ProvideContextFactory(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        this.module = preferredNavigationAppContentProviderModule;
    }

    public static PreferredNavigationAppContentProviderModule_ProvideContextFactory create(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return new PreferredNavigationAppContentProviderModule_ProvideContextFactory(preferredNavigationAppContentProviderModule);
    }

    public static Context provideInstance(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return proxyProvideContext(preferredNavigationAppContentProviderModule);
    }

    public static Context proxyProvideContext(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return (Context) Preconditions.checkNotNull(preferredNavigationAppContentProviderModule.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
