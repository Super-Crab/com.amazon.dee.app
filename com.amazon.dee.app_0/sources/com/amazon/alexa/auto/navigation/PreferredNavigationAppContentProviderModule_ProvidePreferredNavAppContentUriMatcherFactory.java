package com.amazon.alexa.auto.navigation;

import android.content.UriMatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class PreferredNavigationAppContentProviderModule_ProvidePreferredNavAppContentUriMatcherFactory implements Factory<UriMatcher> {
    private final PreferredNavigationAppContentProviderModule module;

    public PreferredNavigationAppContentProviderModule_ProvidePreferredNavAppContentUriMatcherFactory(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        this.module = preferredNavigationAppContentProviderModule;
    }

    public static PreferredNavigationAppContentProviderModule_ProvidePreferredNavAppContentUriMatcherFactory create(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return new PreferredNavigationAppContentProviderModule_ProvidePreferredNavAppContentUriMatcherFactory(preferredNavigationAppContentProviderModule);
    }

    public static UriMatcher provideInstance(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return proxyProvidePreferredNavAppContentUriMatcher(preferredNavigationAppContentProviderModule);
    }

    public static UriMatcher proxyProvidePreferredNavAppContentUriMatcher(PreferredNavigationAppContentProviderModule preferredNavigationAppContentProviderModule) {
        return (UriMatcher) Preconditions.checkNotNull(preferredNavigationAppContentProviderModule.providePreferredNavAppContentUriMatcher(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UriMatcher mo10268get() {
        return provideInstance(this.module);
    }
}
