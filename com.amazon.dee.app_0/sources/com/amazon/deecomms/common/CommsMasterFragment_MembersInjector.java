package com.amazon.deecomms.common;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsMasterFragment_MembersInjector implements MembersInjector<CommsMasterFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CommsConnectivityMonitor> commsConnectivityMonitorProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<ContactsOperationsManager> contactManagerProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public CommsMasterFragment_MembersInjector(Provider<ContactsOperationsManager> provider, Provider<CommsConnectivityMonitor> provider2, Provider<SipClientState> provider3, Provider<ApplicationManager> provider4, Provider<ProvisioningManager> provider5, Provider<CommsIdentityManager> provider6) {
        this.contactManagerProvider = provider;
        this.commsConnectivityMonitorProvider = provider2;
        this.sipClientStateProvider = provider3;
        this.applicationManagerProvider = provider4;
        this.provisioningManagerProvider = provider5;
        this.commsIdentityManagerProvider = provider6;
    }

    public static MembersInjector<CommsMasterFragment> create(Provider<ContactsOperationsManager> provider, Provider<CommsConnectivityMonitor> provider2, Provider<SipClientState> provider3, Provider<ApplicationManager> provider4, Provider<ProvisioningManager> provider5, Provider<CommsIdentityManager> provider6) {
        return new CommsMasterFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectApplicationManager(CommsMasterFragment commsMasterFragment, ApplicationManager applicationManager) {
        commsMasterFragment.applicationManager = applicationManager;
    }

    public static void injectCommsConnectivityMonitor(CommsMasterFragment commsMasterFragment, CommsConnectivityMonitor commsConnectivityMonitor) {
        commsMasterFragment.commsConnectivityMonitor = commsConnectivityMonitor;
    }

    public static void injectCommsIdentityManager(CommsMasterFragment commsMasterFragment, CommsIdentityManager commsIdentityManager) {
        commsMasterFragment.commsIdentityManager = commsIdentityManager;
    }

    public static void injectContactManager(CommsMasterFragment commsMasterFragment, ContactsOperationsManager contactsOperationsManager) {
        commsMasterFragment.contactManager = contactsOperationsManager;
    }

    public static void injectProvisioningManager(CommsMasterFragment commsMasterFragment, ProvisioningManager provisioningManager) {
        commsMasterFragment.provisioningManager = provisioningManager;
    }

    public static void injectSipClientState(CommsMasterFragment commsMasterFragment, SipClientState sipClientState) {
        commsMasterFragment.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsMasterFragment commsMasterFragment) {
        commsMasterFragment.contactManager = this.contactManagerProvider.mo10268get();
        commsMasterFragment.commsConnectivityMonitor = this.commsConnectivityMonitorProvider.mo10268get();
        commsMasterFragment.sipClientState = this.sipClientStateProvider.mo10268get();
        commsMasterFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        commsMasterFragment.provisioningManager = this.provisioningManagerProvider.mo10268get();
        commsMasterFragment.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
    }
}
