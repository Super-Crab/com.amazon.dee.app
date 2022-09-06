package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class EditContactFragment_MembersInjector implements MembersInjector<EditContactFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CommsInternal> commsInternalProvider;

    public EditContactFragment_MembersInjector(Provider<ApplicationManager> provider, Provider<CommsInternal> provider2) {
        this.applicationManagerProvider = provider;
        this.commsInternalProvider = provider2;
    }

    public static MembersInjector<EditContactFragment> create(Provider<ApplicationManager> provider, Provider<CommsInternal> provider2) {
        return new EditContactFragment_MembersInjector(provider, provider2);
    }

    public static void injectApplicationManager(EditContactFragment editContactFragment, ApplicationManager applicationManager) {
        editContactFragment.applicationManager = applicationManager;
    }

    public static void injectCommsInternal(EditContactFragment editContactFragment, CommsInternal commsInternal) {
        editContactFragment.commsInternal = commsInternal;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EditContactFragment editContactFragment) {
        editContactFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        editContactFragment.commsInternal = this.commsInternalProvider.mo10268get();
    }
}
