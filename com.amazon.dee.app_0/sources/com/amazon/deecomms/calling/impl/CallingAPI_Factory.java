package com.amazon.deecomms.calling.impl;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.notifications.PushNotificationManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CallingAPI_Factory implements Factory<CallingAPI> {
    private final Provider<CallInitiator> callInitiatorProvider;
    private final Provider<CallingAPIMonitor> callMonitorProvider;
    private final Provider<CommsAccessorySessionListener> commsAccessorySessionListenerProvider;
    private final Provider<CommsDirectiveHandler> commsDirectiveHandlerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<ContactsDataStoreUtil> contactsDataStoreUtilProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeCallPermissionHandler> driveModeCallPermissionHandlerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;
    private final Provider<MakeNativeCallHandler> makeNativeCallHandlerProvider;
    private final Provider<CallingAPIPopulator> populatorProvider;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;

    public CallingAPI_Factory(Provider<InitiationLogicFactory> provider, Provider<CallInitiator> provider2, Provider<ContactsDataStoreUtil> provider3, Provider<CallingAPIMonitor> provider4, Provider<CallingAPIPopulator> provider5, Provider<Context> provider6, Provider<CommsDirectiveHandler> provider7, Provider<CommsIdentityManager> provider8, Provider<PushNotificationManager> provider9, Provider<CommsAccessorySessionListener> provider10, Provider<MakeNativeCallHandler> provider11, Provider<DriveModeCallPermissionHandler> provider12, Provider<EventBus> provider13) {
        this.initiationLogicFactoryProvider = provider;
        this.callInitiatorProvider = provider2;
        this.contactsDataStoreUtilProvider = provider3;
        this.callMonitorProvider = provider4;
        this.populatorProvider = provider5;
        this.contextProvider = provider6;
        this.commsDirectiveHandlerProvider = provider7;
        this.commsIdentityManagerProvider = provider8;
        this.pushNotificationManagerProvider = provider9;
        this.commsAccessorySessionListenerProvider = provider10;
        this.makeNativeCallHandlerProvider = provider11;
        this.driveModeCallPermissionHandlerProvider = provider12;
        this.eventBusProvider = provider13;
    }

    public static CallingAPI_Factory create(Provider<InitiationLogicFactory> provider, Provider<CallInitiator> provider2, Provider<ContactsDataStoreUtil> provider3, Provider<CallingAPIMonitor> provider4, Provider<CallingAPIPopulator> provider5, Provider<Context> provider6, Provider<CommsDirectiveHandler> provider7, Provider<CommsIdentityManager> provider8, Provider<PushNotificationManager> provider9, Provider<CommsAccessorySessionListener> provider10, Provider<MakeNativeCallHandler> provider11, Provider<DriveModeCallPermissionHandler> provider12, Provider<EventBus> provider13) {
        return new CallingAPI_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static CallingAPI newCallingAPI(InitiationLogicFactory initiationLogicFactory, CallInitiator callInitiator, ContactsDataStoreUtil contactsDataStoreUtil, CallingAPIMonitor callingAPIMonitor, CallingAPIPopulator callingAPIPopulator, Context context, CommsDirectiveHandler commsDirectiveHandler, CommsIdentityManager commsIdentityManager, PushNotificationManager pushNotificationManager, CommsAccessorySessionListener commsAccessorySessionListener, MakeNativeCallHandler makeNativeCallHandler, DriveModeCallPermissionHandler driveModeCallPermissionHandler, EventBus eventBus) {
        return new CallingAPI(initiationLogicFactory, callInitiator, contactsDataStoreUtil, callingAPIMonitor, callingAPIPopulator, context, commsDirectiveHandler, commsIdentityManager, pushNotificationManager, commsAccessorySessionListener, makeNativeCallHandler, driveModeCallPermissionHandler, eventBus);
    }

    public static CallingAPI provideInstance(Provider<InitiationLogicFactory> provider, Provider<CallInitiator> provider2, Provider<ContactsDataStoreUtil> provider3, Provider<CallingAPIMonitor> provider4, Provider<CallingAPIPopulator> provider5, Provider<Context> provider6, Provider<CommsDirectiveHandler> provider7, Provider<CommsIdentityManager> provider8, Provider<PushNotificationManager> provider9, Provider<CommsAccessorySessionListener> provider10, Provider<MakeNativeCallHandler> provider11, Provider<DriveModeCallPermissionHandler> provider12, Provider<EventBus> provider13) {
        return new CallingAPI(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CallingAPI mo10268get() {
        return provideInstance(this.initiationLogicFactoryProvider, this.callInitiatorProvider, this.contactsDataStoreUtilProvider, this.callMonitorProvider, this.populatorProvider, this.contextProvider, this.commsDirectiveHandlerProvider, this.commsIdentityManagerProvider, this.pushNotificationManagerProvider, this.commsAccessorySessionListenerProvider, this.makeNativeCallHandlerProvider, this.driveModeCallPermissionHandlerProvider, this.eventBusProvider);
    }
}
