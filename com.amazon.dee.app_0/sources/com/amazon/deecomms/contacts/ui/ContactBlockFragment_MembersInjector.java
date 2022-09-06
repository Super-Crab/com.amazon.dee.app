package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.ApplicationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactBlockFragment_MembersInjector implements MembersInjector<ContactBlockFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;

    public ContactBlockFragment_MembersInjector(Provider<ApplicationManager> provider, Provider<CommsIdentityManager> provider2) {
        this.applicationManagerProvider = provider;
        this.commsIdentityManagerProvider = provider2;
    }

    public static MembersInjector<ContactBlockFragment> create(Provider<ApplicationManager> provider, Provider<CommsIdentityManager> provider2) {
        return new ContactBlockFragment_MembersInjector(provider, provider2);
    }

    public static void injectApplicationManager(ContactBlockFragment contactBlockFragment, ApplicationManager applicationManager) {
        contactBlockFragment.applicationManager = applicationManager;
    }

    public static void injectCommsIdentityManager(ContactBlockFragment contactBlockFragment, CommsIdentityManager commsIdentityManager) {
        contactBlockFragment.commsIdentityManager = commsIdentityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactBlockFragment contactBlockFragment) {
        contactBlockFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        contactBlockFragment.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
