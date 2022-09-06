package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class WhitelistContactFragment_MembersInjector implements MembersInjector<WhitelistContactFragment> {
    private final Provider<Context> appContextProvider;
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public WhitelistContactFragment_MembersInjector(Provider<CommsIdentityManager> provider, Provider<ApplicationManager> provider2, Provider<Context> provider3, Provider<CapabilitiesManager> provider4, Provider<ThemingUpdateUtil> provider5) {
        this.commsIdentityManagerProvider = provider;
        this.applicationManagerProvider = provider2;
        this.appContextProvider = provider3;
        this.capabilitiesManagerProvider = provider4;
        this.themingUpdateUtilProvider = provider5;
    }

    public static MembersInjector<WhitelistContactFragment> create(Provider<CommsIdentityManager> provider, Provider<ApplicationManager> provider2, Provider<Context> provider3, Provider<CapabilitiesManager> provider4, Provider<ThemingUpdateUtil> provider5) {
        return new WhitelistContactFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAppContext(WhitelistContactFragment whitelistContactFragment, Context context) {
        whitelistContactFragment.appContext = context;
    }

    public static void injectApplicationManager(WhitelistContactFragment whitelistContactFragment, ApplicationManager applicationManager) {
        whitelistContactFragment.applicationManager = applicationManager;
    }

    public static void injectCapabilitiesManager(WhitelistContactFragment whitelistContactFragment, CapabilitiesManager capabilitiesManager) {
        whitelistContactFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(WhitelistContactFragment whitelistContactFragment, CommsIdentityManager commsIdentityManager) {
        whitelistContactFragment.commsIdentityManager = commsIdentityManager;
    }

    public static void injectThemingUpdateUtil(WhitelistContactFragment whitelistContactFragment, ThemingUpdateUtil themingUpdateUtil) {
        whitelistContactFragment.themingUpdateUtil = themingUpdateUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(WhitelistContactFragment whitelistContactFragment) {
        whitelistContactFragment.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        whitelistContactFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        whitelistContactFragment.appContext = this.appContextProvider.mo10268get();
        whitelistContactFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        whitelistContactFragment.themingUpdateUtil = this.themingUpdateUtilProvider.mo10268get();
    }
}
