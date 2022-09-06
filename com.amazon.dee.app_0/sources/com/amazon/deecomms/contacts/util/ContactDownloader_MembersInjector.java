package com.amazon.deecomms.contacts.util;

import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactDownloader_MembersInjector implements MembersInjector<ContactDownloader> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;

    public ContactDownloader_MembersInjector(Provider<CapabilitiesManager> provider) {
        this.capabilitiesManagerProvider = provider;
    }

    public static MembersInjector<ContactDownloader> create(Provider<CapabilitiesManager> provider) {
        return new ContactDownloader_MembersInjector(provider);
    }

    public static void injectCapabilitiesManager(ContactDownloader contactDownloader, CapabilitiesManager capabilitiesManager) {
        contactDownloader.capabilitiesManager = capabilitiesManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactDownloader contactDownloader) {
        contactDownloader.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
