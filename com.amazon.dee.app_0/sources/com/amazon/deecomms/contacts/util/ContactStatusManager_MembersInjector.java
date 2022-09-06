package com.amazon.deecomms.contacts.util;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactStatusManager_MembersInjector implements MembersInjector<ContactStatusManager> {
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;

    public ContactStatusManager_MembersInjector(Provider<Context> provider, Provider<CommsIdentityManager> provider2) {
        this.contextProvider = provider;
        this.commsIdentityManagerProvider = provider2;
    }

    public static MembersInjector<ContactStatusManager> create(Provider<Context> provider, Provider<CommsIdentityManager> provider2) {
        return new ContactStatusManager_MembersInjector(provider, provider2);
    }

    public static void injectCommsIdentityManager(ContactStatusManager contactStatusManager, CommsIdentityManager commsIdentityManager) {
        contactStatusManager.commsIdentityManager = commsIdentityManager;
    }

    public static void injectContext(ContactStatusManager contactStatusManager, Context context) {
        contactStatusManager.context = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactStatusManager contactStatusManager) {
        contactStatusManager.context = this.contextProvider.mo10268get();
        contactStatusManager.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
