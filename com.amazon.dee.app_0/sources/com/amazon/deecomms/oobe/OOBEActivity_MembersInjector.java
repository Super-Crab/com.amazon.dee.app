package com.amazon.deecomms.oobe;

import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class OOBEActivity_MembersInjector implements MembersInjector<OOBEActivity> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsAccessorySessionListener> commsAccessorySessionListenerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<DeviceMetadataStoreRegistrar> deviceMetadataStoreRegistrarProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<MessagingControllerContextProvider> mcContextProvider;
    private final Provider<MessagingControllerManager> messagingControllerManagerProvider;
    private final Provider<OOBEPersonManager> oobePersonManagerProvider;
    private final Provider<OobeService> oobeServiceProvider;
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public OOBEActivity_MembersInjector(Provider<ApplicationManager> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3, Provider<DeviceMetadataStoreRegistrar> provider4, Provider<MessagingControllerManager> provider5, Provider<MessagingControllerContextProvider> provider6, Provider<CommsAccessorySessionListener> provider7, Provider<EventBus> provider8, Provider<OobeService> provider9, Provider<OOBEPersonManager> provider10, Provider<ThemingUpdateUtil> provider11) {
        this.applicationManagerProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.commsIdentityManagerProvider = provider3;
        this.deviceMetadataStoreRegistrarProvider = provider4;
        this.messagingControllerManagerProvider = provider5;
        this.mcContextProvider = provider6;
        this.commsAccessorySessionListenerProvider = provider7;
        this.eventBusProvider = provider8;
        this.oobeServiceProvider = provider9;
        this.oobePersonManagerProvider = provider10;
        this.themingUpdateUtilProvider = provider11;
    }

    public static MembersInjector<OOBEActivity> create(Provider<ApplicationManager> provider, Provider<CapabilitiesManager> provider2, Provider<CommsIdentityManager> provider3, Provider<DeviceMetadataStoreRegistrar> provider4, Provider<MessagingControllerManager> provider5, Provider<MessagingControllerContextProvider> provider6, Provider<CommsAccessorySessionListener> provider7, Provider<EventBus> provider8, Provider<OobeService> provider9, Provider<OOBEPersonManager> provider10, Provider<ThemingUpdateUtil> provider11) {
        return new OOBEActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static void injectApplicationManager(OOBEActivity oOBEActivity, ApplicationManager applicationManager) {
        oOBEActivity.applicationManager = applicationManager;
    }

    public static void injectCapabilitiesManager(OOBEActivity oOBEActivity, CapabilitiesManager capabilitiesManager) {
        oOBEActivity.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsAccessorySessionListener(OOBEActivity oOBEActivity, CommsAccessorySessionListener commsAccessorySessionListener) {
        oOBEActivity.commsAccessorySessionListener = commsAccessorySessionListener;
    }

    public static void injectCommsIdentityManager(OOBEActivity oOBEActivity, CommsIdentityManager commsIdentityManager) {
        oOBEActivity.commsIdentityManager = commsIdentityManager;
    }

    public static void injectDeviceMetadataStoreRegistrar(OOBEActivity oOBEActivity, DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar) {
        oOBEActivity.deviceMetadataStoreRegistrar = deviceMetadataStoreRegistrar;
    }

    public static void injectEventBus(OOBEActivity oOBEActivity, EventBus eventBus) {
        oOBEActivity.eventBus = eventBus;
    }

    public static void injectMcContextProvider(OOBEActivity oOBEActivity, MessagingControllerContextProvider messagingControllerContextProvider) {
        oOBEActivity.mcContextProvider = messagingControllerContextProvider;
    }

    public static void injectMessagingControllerManager(OOBEActivity oOBEActivity, MessagingControllerManager messagingControllerManager) {
        oOBEActivity.messagingControllerManager = messagingControllerManager;
    }

    public static void injectOobePersonManager(OOBEActivity oOBEActivity, OOBEPersonManager oOBEPersonManager) {
        oOBEActivity.oobePersonManager = oOBEPersonManager;
    }

    public static void injectOobeService(OOBEActivity oOBEActivity, OobeService oobeService) {
        oOBEActivity.oobeService = oobeService;
    }

    public static void injectThemingUpdateUtil(OOBEActivity oOBEActivity, ThemingUpdateUtil themingUpdateUtil) {
        oOBEActivity.themingUpdateUtil = themingUpdateUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OOBEActivity oOBEActivity) {
        oOBEActivity.applicationManager = this.applicationManagerProvider.mo10268get();
        oOBEActivity.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        oOBEActivity.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        oOBEActivity.deviceMetadataStoreRegistrar = this.deviceMetadataStoreRegistrarProvider.mo10268get();
        oOBEActivity.messagingControllerManager = this.messagingControllerManagerProvider.mo10268get();
        oOBEActivity.mcContextProvider = this.mcContextProvider.mo10268get();
        oOBEActivity.commsAccessorySessionListener = this.commsAccessorySessionListenerProvider.mo10268get();
        oOBEActivity.eventBus = this.eventBusProvider.mo10268get();
        oOBEActivity.oobeService = this.oobeServiceProvider.mo10268get();
        oOBEActivity.oobePersonManager = this.oobePersonManagerProvider.mo10268get();
        oOBEActivity.themingUpdateUtil = this.themingUpdateUtilProvider.mo10268get();
    }
}
