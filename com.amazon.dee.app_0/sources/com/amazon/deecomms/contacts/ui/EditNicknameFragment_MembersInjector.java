package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EditNicknameFragment_MembersInjector implements MembersInjector<EditNicknameFragment> {
    private final Provider<Context> appContextProvider;
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<ArcusConfig> arcusConfigProvider;

    public EditNicknameFragment_MembersInjector(Provider<ApplicationManager> provider, Provider<ArcusConfig> provider2, Provider<Context> provider3) {
        this.applicationManagerProvider = provider;
        this.arcusConfigProvider = provider2;
        this.appContextProvider = provider3;
    }

    public static MembersInjector<EditNicknameFragment> create(Provider<ApplicationManager> provider, Provider<ArcusConfig> provider2, Provider<Context> provider3) {
        return new EditNicknameFragment_MembersInjector(provider, provider2, provider3);
    }

    public static void injectAppContext(EditNicknameFragment editNicknameFragment, Context context) {
        editNicknameFragment.appContext = context;
    }

    public static void injectApplicationManager(EditNicknameFragment editNicknameFragment, ApplicationManager applicationManager) {
        editNicknameFragment.applicationManager = applicationManager;
    }

    public static void injectArcusConfig(EditNicknameFragment editNicknameFragment, ArcusConfig arcusConfig) {
        editNicknameFragment.arcusConfig = arcusConfig;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EditNicknameFragment editNicknameFragment) {
        editNicknameFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        editNicknameFragment.arcusConfig = this.arcusConfigProvider.mo10268get();
        editNicknameFragment.appContext = this.appContextProvider.mo10268get();
    }
}
