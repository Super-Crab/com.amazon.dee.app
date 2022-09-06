package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.ApplicationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ChildContactCardFragment_MembersInjector implements MembersInjector<ChildContactCardFragment> {
    private final Provider<Context> appContextProvider;
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CommsIdentityManager> mCommsIdentityManagerProvider;

    public ChildContactCardFragment_MembersInjector(Provider<ApplicationManager> provider, Provider<CommsIdentityManager> provider2, Provider<Context> provider3) {
        this.applicationManagerProvider = provider;
        this.mCommsIdentityManagerProvider = provider2;
        this.appContextProvider = provider3;
    }

    public static MembersInjector<ChildContactCardFragment> create(Provider<ApplicationManager> provider, Provider<CommsIdentityManager> provider2, Provider<Context> provider3) {
        return new ChildContactCardFragment_MembersInjector(provider, provider2, provider3);
    }

    public static void injectAppContext(ChildContactCardFragment childContactCardFragment, Context context) {
        childContactCardFragment.appContext = context;
    }

    public static void injectApplicationManager(ChildContactCardFragment childContactCardFragment, ApplicationManager applicationManager) {
        childContactCardFragment.applicationManager = applicationManager;
    }

    public static void injectMCommsIdentityManager(ChildContactCardFragment childContactCardFragment, CommsIdentityManager commsIdentityManager) {
        childContactCardFragment.mCommsIdentityManager = commsIdentityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ChildContactCardFragment childContactCardFragment) {
        childContactCardFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        childContactCardFragment.mCommsIdentityManager = this.mCommsIdentityManagerProvider.mo10268get();
        childContactCardFragment.appContext = this.appContextProvider.mo10268get();
    }
}
