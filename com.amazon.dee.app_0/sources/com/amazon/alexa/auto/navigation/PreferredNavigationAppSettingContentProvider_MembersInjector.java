package com.amazon.alexa.auto.navigation;

import android.content.UriMatcher;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class PreferredNavigationAppSettingContentProvider_MembersInjector implements MembersInjector<PreferredNavigationAppSettingContentProvider> {
    private final Provider<PreferredNavigationAppSettingManager> preferredNavAppManagerProvider;
    private final Provider<UriMatcher> uriMatcherProvider;

    public PreferredNavigationAppSettingContentProvider_MembersInjector(Provider<UriMatcher> provider, Provider<PreferredNavigationAppSettingManager> provider2) {
        this.uriMatcherProvider = provider;
        this.preferredNavAppManagerProvider = provider2;
    }

    public static MembersInjector<PreferredNavigationAppSettingContentProvider> create(Provider<UriMatcher> provider, Provider<PreferredNavigationAppSettingManager> provider2) {
        return new PreferredNavigationAppSettingContentProvider_MembersInjector(provider, provider2);
    }

    public static void injectPreferredNavAppManager(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider, Object obj) {
        preferredNavigationAppSettingContentProvider.preferredNavAppManager = (PreferredNavigationAppSettingManager) obj;
    }

    public static void injectUriMatcher(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider, UriMatcher uriMatcher) {
        preferredNavigationAppSettingContentProvider.uriMatcher = uriMatcher;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PreferredNavigationAppSettingContentProvider preferredNavigationAppSettingContentProvider) {
        injectUriMatcher(preferredNavigationAppSettingContentProvider, this.uriMatcherProvider.mo10268get());
        injectPreferredNavAppManager(preferredNavigationAppSettingContentProvider, this.preferredNavAppManagerProvider.mo10268get());
    }
}
