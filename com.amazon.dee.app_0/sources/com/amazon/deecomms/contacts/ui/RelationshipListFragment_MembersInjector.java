package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import com.amazon.deecomms.common.ApplicationManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class RelationshipListFragment_MembersInjector implements MembersInjector<RelationshipListFragment> {
    private final Provider<Context> appContextProvider;
    private final Provider<ApplicationManager> applicationManagerProvider;

    public RelationshipListFragment_MembersInjector(Provider<ApplicationManager> provider, Provider<Context> provider2) {
        this.applicationManagerProvider = provider;
        this.appContextProvider = provider2;
    }

    public static MembersInjector<RelationshipListFragment> create(Provider<ApplicationManager> provider, Provider<Context> provider2) {
        return new RelationshipListFragment_MembersInjector(provider, provider2);
    }

    public static void injectAppContext(RelationshipListFragment relationshipListFragment, Context context) {
        relationshipListFragment.appContext = context;
    }

    public static void injectApplicationManager(RelationshipListFragment relationshipListFragment, ApplicationManager applicationManager) {
        relationshipListFragment.applicationManager = applicationManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(RelationshipListFragment relationshipListFragment) {
        relationshipListFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        relationshipListFragment.appContext = this.appContextProvider.mo10268get();
    }
}
