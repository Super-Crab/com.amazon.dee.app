package com.amazon.deecomms.oobe.fragments;

import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainOOBEFragment_MembersInjector implements MembersInjector<MainOOBEFragment> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public MainOOBEFragment_MembersInjector(Provider<CapabilitiesManager> provider, Provider<ThemingUpdateUtil> provider2) {
        this.capabilitiesManagerProvider = provider;
        this.themingUpdateUtilProvider = provider2;
    }

    public static MembersInjector<MainOOBEFragment> create(Provider<CapabilitiesManager> provider, Provider<ThemingUpdateUtil> provider2) {
        return new MainOOBEFragment_MembersInjector(provider, provider2);
    }

    public static void injectCapabilitiesManager(MainOOBEFragment mainOOBEFragment, CapabilitiesManager capabilitiesManager) {
        mainOOBEFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectThemingUpdateUtil(MainOOBEFragment mainOOBEFragment, ThemingUpdateUtil themingUpdateUtil) {
        mainOOBEFragment.themingUpdateUtil = themingUpdateUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainOOBEFragment mainOOBEFragment) {
        mainOOBEFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        mainOOBEFragment.themingUpdateUtil = this.themingUpdateUtilProvider.mo10268get();
    }
}
