package com.amazon.deecomms.contacts.util;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class GetOrCreateContact_MembersInjector implements MembersInjector<GetOrCreateContact> {
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsInternal> commsInternalProvider;
    private final Provider<ContactsOperationsManager> contactsManagerProvider;
    private final Provider<Context> contextProvider;

    public GetOrCreateContact_MembersInjector(Provider<Context> provider, Provider<ContactsOperationsManager> provider2, Provider<CommsIdentityManager> provider3, Provider<CommsInternal> provider4) {
        this.contextProvider = provider;
        this.contactsManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.commsInternalProvider = provider4;
    }

    public static MembersInjector<GetOrCreateContact> create(Provider<Context> provider, Provider<ContactsOperationsManager> provider2, Provider<CommsIdentityManager> provider3, Provider<CommsInternal> provider4) {
        return new GetOrCreateContact_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectCommsIdentityManager(GetOrCreateContact getOrCreateContact, CommsIdentityManager commsIdentityManager) {
        getOrCreateContact.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsInternal(GetOrCreateContact getOrCreateContact, CommsInternal commsInternal) {
        getOrCreateContact.commsInternal = commsInternal;
    }

    public static void injectContactsManager(GetOrCreateContact getOrCreateContact, ContactsOperationsManager contactsOperationsManager) {
        getOrCreateContact.contactsManager = contactsOperationsManager;
    }

    public static void injectContext(GetOrCreateContact getOrCreateContact, Context context) {
        getOrCreateContact.context = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GetOrCreateContact getOrCreateContact) {
        getOrCreateContact.context = this.contextProvider.mo10268get();
        getOrCreateContact.contactsManager = this.contactsManagerProvider.mo10268get();
        getOrCreateContact.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        getOrCreateContact.commsInternal = this.commsInternalProvider.mo10268get();
    }
}
