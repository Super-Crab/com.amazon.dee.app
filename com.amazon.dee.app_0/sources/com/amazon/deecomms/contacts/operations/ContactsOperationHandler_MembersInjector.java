package com.amazon.deecomms.contacts.operations;

import android.content.Context;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactsOperationHandler_MembersInjector implements MembersInjector<ContactsOperationHandler> {
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<ContactsOperationsManager> contactsManagerProvider;
    private final Provider<Context> contextProvider;

    public ContactsOperationHandler_MembersInjector(Provider<Context> provider, Provider<ContactsOperationsManager> provider2, Provider<CapabilitiesManager> provider3) {
        this.contextProvider = provider;
        this.contactsManagerProvider = provider2;
        this.capabilitiesManagerProvider = provider3;
    }

    public static MembersInjector<ContactsOperationHandler> create(Provider<Context> provider, Provider<ContactsOperationsManager> provider2, Provider<CapabilitiesManager> provider3) {
        return new ContactsOperationHandler_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCapabilitiesManager(ContactsOperationHandler contactsOperationHandler, CapabilitiesManager capabilitiesManager) {
        contactsOperationHandler.capabilitiesManager = capabilitiesManager;
    }

    public static void injectContactsManager(ContactsOperationHandler contactsOperationHandler, ContactsOperationsManager contactsOperationsManager) {
        contactsOperationHandler.contactsManager = contactsOperationsManager;
    }

    public static void injectContext(ContactsOperationHandler contactsOperationHandler, Context context) {
        contactsOperationHandler.context = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactsOperationHandler contactsOperationHandler) {
        contactsOperationHandler.context = this.contextProvider.mo10268get();
        contactsOperationHandler.contactsManager = this.contactsManagerProvider.mo10268get();
        contactsOperationHandler.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
