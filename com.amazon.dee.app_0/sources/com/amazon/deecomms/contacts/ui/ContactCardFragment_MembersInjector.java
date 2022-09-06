package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ContactCardFragment_MembersInjector implements MembersInjector<ContactCardFragment> {
    private final Provider<Context> appContextProvider;
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<ArcusConfig> arcusConfigProvider;
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<DevicesSource> devicesSourceProvider;
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;
    private final Provider<CommsIdentityManager> mCommsIdentityManagerProvider;

    public ContactCardFragment_MembersInjector(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<InitiationLogicFactory> provider3, Provider<CallingFacade> provider4, Provider<ArcusConfig> provider5, Provider<DevicesSource> provider6, Provider<Context> provider7, Provider<ApplicationManager> provider8, Provider<DeviceUtils> provider9) {
        this.mCommsIdentityManagerProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.initiationLogicFactoryProvider = provider3;
        this.callingFacadeProvider = provider4;
        this.arcusConfigProvider = provider5;
        this.devicesSourceProvider = provider6;
        this.appContextProvider = provider7;
        this.applicationManagerProvider = provider8;
        this.deviceUtilsProvider = provider9;
    }

    public static MembersInjector<ContactCardFragment> create(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<InitiationLogicFactory> provider3, Provider<CallingFacade> provider4, Provider<ArcusConfig> provider5, Provider<DevicesSource> provider6, Provider<Context> provider7, Provider<ApplicationManager> provider8, Provider<DeviceUtils> provider9) {
        return new ContactCardFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectAppContext(ContactCardFragment contactCardFragment, Context context) {
        contactCardFragment.appContext = context;
    }

    public static void injectApplicationManager(ContactCardFragment contactCardFragment, ApplicationManager applicationManager) {
        contactCardFragment.applicationManager = applicationManager;
    }

    public static void injectArcusConfig(ContactCardFragment contactCardFragment, ArcusConfig arcusConfig) {
        contactCardFragment.arcusConfig = arcusConfig;
    }

    public static void injectCallingFacade(ContactCardFragment contactCardFragment, CallingFacade callingFacade) {
        contactCardFragment.callingFacade = callingFacade;
    }

    public static void injectCapabilitiesManager(ContactCardFragment contactCardFragment, CapabilitiesManager capabilitiesManager) {
        contactCardFragment.capabilitiesManager = capabilitiesManager;
    }

    public static void injectDeviceUtils(ContactCardFragment contactCardFragment, DeviceUtils deviceUtils) {
        contactCardFragment.deviceUtils = deviceUtils;
    }

    public static void injectDevicesSource(ContactCardFragment contactCardFragment, DevicesSource devicesSource) {
        contactCardFragment.devicesSource = devicesSource;
    }

    public static void injectInitiationLogicFactory(ContactCardFragment contactCardFragment, InitiationLogicFactory initiationLogicFactory) {
        contactCardFragment.initiationLogicFactory = initiationLogicFactory;
    }

    public static void injectMCommsIdentityManager(ContactCardFragment contactCardFragment, CommsIdentityManager commsIdentityManager) {
        contactCardFragment.mCommsIdentityManager = commsIdentityManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ContactCardFragment contactCardFragment) {
        contactCardFragment.mCommsIdentityManager = this.mCommsIdentityManagerProvider.mo10268get();
        contactCardFragment.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        contactCardFragment.initiationLogicFactory = this.initiationLogicFactoryProvider.mo10268get();
        contactCardFragment.callingFacade = this.callingFacadeProvider.mo10268get();
        contactCardFragment.arcusConfig = this.arcusConfigProvider.mo10268get();
        contactCardFragment.devicesSource = this.devicesSourceProvider.mo10268get();
        contactCardFragment.appContext = this.appContextProvider.mo10268get();
        contactCardFragment.applicationManager = this.applicationManagerProvider.mo10268get();
        contactCardFragment.deviceUtils = this.deviceUtilsProvider.mo10268get();
    }
}
