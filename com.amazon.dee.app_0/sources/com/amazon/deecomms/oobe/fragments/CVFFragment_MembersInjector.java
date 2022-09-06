package com.amazon.deecomms.oobe.fragments;

import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CVFFragment_MembersInjector implements MembersInjector<CVFFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public CVFFragment_MembersInjector(Provider<CapabilitiesManager> provider, Provider<ThemingUpdateUtil> provider2, Provider<ApplicationManager> provider3) {
        this.capabilitiesManagerProvider = provider;
        this.themingUpdateUtilProvider = provider2;
        this.applicationManagerProvider = provider3;
    }

    public static MembersInjector<CVFFragment> create(Provider<CapabilitiesManager> provider, Provider<ThemingUpdateUtil> provider2, Provider<ApplicationManager> provider3) {
        return new CVFFragment_MembersInjector(provider, provider2, provider3);
    }

    public static void injectApplicationManager(CVFFragment cVFFragment, ApplicationManager applicationManager) {
        cVFFragment.applicationManager = applicationManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CVFFragment cVFFragment) {
        MainOOBEFragment_MembersInjector.injectCapabilitiesManager(cVFFragment, this.capabilitiesManagerProvider.mo10268get());
        MainOOBEFragment_MembersInjector.injectThemingUpdateUtil(cVFFragment, this.themingUpdateUtilProvider.mo10268get());
        cVFFragment.applicationManager = this.applicationManagerProvider.mo10268get();
    }
}
