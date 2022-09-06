package com.amazon.deecomms.contacts.ui;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.ndt.DevicesSource;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactListFragment_MembersInjector implements MembersInjector<ContactListFragment> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<ContactsOperationsManager> contactManagerAndContactsManagerProvider;
    private final Provider<DevicesSource> devicesSourceProvider;
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;

    public ContactListFragment_MembersInjector(Provider<ContactsOperationsManager> provider, Provider<ApplicationManager> provider2, Provider<CommsIdentityManager> provider3, Provider<ProvisioningManager> provider4, Provider<CapabilitiesManager> provider5, Provider<InitiationLogicFactory> provider6, Provider<CallingFacade> provider7, Provider<DevicesSource> provider8) {
        this.contactManagerAndContactsManagerProvider = provider;
        this.applicationManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.provisioningManagerProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
        this.initiationLogicFactoryProvider = provider6;
        this.callingFacadeProvider = provider7;
        this.devicesSourceProvider = provider8;
    }

    public static MembersInjector<ContactListFragment> create(Provider<ContactsOperationsManager> provider, Provider<ApplicationManager> provider2, Provider<CommsIdentityManager> provider3, Provider<ProvisioningManager> provider4, Provider<CapabilitiesManager> provider5, Provider<InitiationLogicFactory> provider6, Provider<CallingFacade> provider7, Provider<DevicesSource> provider8) {
        return new ContactListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectApplicationManager(ContactListFragment contactListFragment, ApplicationManager applicationManager) {
        contactListFragment.applicationManager = applicationManager;
    }

    public static void injectCallingFacade(ContactListFragment contactListFragment, CallingFacade callingFacade) {
        contactListFragment.callingFacade = callingFacade;
    }

    public static void injectCapabilitiesManager(ContactListFragment contactListFragment, CapabilitiesManager capabilitiesManager) {
        contactListFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(ContactListFragment contactListFragment, CommsIdentityManager commsIdentityManager) {
        contactListFragment.commsIdentityManager = commsIdentityManager;
    }

    public static void injectContactManager(ContactListFragment contactListFragment, ContactsOperationsManager contactsOperationsManager) {
        contactListFragment.contactManager = contactsOperationsManager;
    }

    public static void injectContactsManager(ContactListFragment contactListFragment, ContactsOperationsManager contactsOperationsManager) {
        contactListFragment.contactsManager = contactsOperationsManager;
    }

    public static void injectDevicesSource(ContactListFragment contactListFragment, DevicesSource devicesSource) {
        contactListFragment.devicesSource = devicesSource;
    }

    public static void injectInitiationLogicFactory(ContactListFragment contactListFragment, InitiationLogicFactory initiationLogicFactory) {
        contactListFragment.initiationLogicFactory = initiationLogicFactory;
    }

    public static void injectProvisioningManager(ContactListFragment contactListFragment, ProvisioningManager provisioningManager) {
        contactListFragment.provisioningManager = provisioningManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactListFragment contactListFragment) {
        contactListFragment.contactsManager = this.contactManagerAndContactsManagerProvider.mo10268get();
        contactListFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        contactListFragment.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        contactListFragment.provisioningManager = this.provisioningManagerProvider.mo10268get();
        contactListFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        contactListFragment.initiationLogicFactory = this.initiationLogicFactoryProvider.mo10268get();
        contactListFragment.callingFacade = this.callingFacadeProvider.mo10268get();
        contactListFragment.devicesSource = this.devicesSourceProvider.mo10268get();
        contactListFragment.contactManager = this.contactManagerAndContactsManagerProvider.mo10268get();
    }
}
