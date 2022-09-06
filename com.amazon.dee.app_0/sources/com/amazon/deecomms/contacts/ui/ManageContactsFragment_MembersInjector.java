package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.common.ApplicationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ManageContactsFragment_MembersInjector implements MembersInjector<ManageContactsFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;

    public ManageContactsFragment_MembersInjector(Provider<ApplicationManager> provider) {
        this.applicationManagerProvider = provider;
    }

    public static MembersInjector<ManageContactsFragment> create(Provider<ApplicationManager> provider) {
        return new ManageContactsFragment_MembersInjector(provider);
    }

    public static void injectApplicationManager(ManageContactsFragment manageContactsFragment, ApplicationManager applicationManager) {
        manageContactsFragment.applicationManager = applicationManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ManageContactsFragment manageContactsFragment) {
        manageContactsFragment.applicationManager = this.applicationManagerProvider.mo10268get();
    }
}
