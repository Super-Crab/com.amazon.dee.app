package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactListAdapter_MembersInjector implements MembersInjector<ContactListAdapter> {
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public ContactListAdapter_MembersInjector(Provider<ThemingUpdateUtil> provider) {
        this.themingUpdateUtilProvider = provider;
    }

    public static MembersInjector<ContactListAdapter> create(Provider<ThemingUpdateUtil> provider) {
        return new ContactListAdapter_MembersInjector(provider);
    }

    public static void injectThemingUpdateUtil(ContactListAdapter contactListAdapter, ThemingUpdateUtil themingUpdateUtil) {
        contactListAdapter.themingUpdateUtil = themingUpdateUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactListAdapter contactListAdapter) {
        contactListAdapter.themingUpdateUtil = this.themingUpdateUtilProvider.mo10268get();
    }
}
