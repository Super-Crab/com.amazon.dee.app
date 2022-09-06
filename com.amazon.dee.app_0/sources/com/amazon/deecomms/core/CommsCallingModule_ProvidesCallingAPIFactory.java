package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.api.ICallingAPI;
import com.amazon.deecomms.calling.impl.CallingAPIMonitor;
import com.amazon.deecomms.calling.impl.CallingAPIPopulator;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.notifications.PushNotificationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsCallingModule_ProvidesCallingAPIFactory implements Factory<ICallingAPI> {
    private final Provider<CallingAPIPopulator> callingAPIPopulatorProvider;
    private final Provider<CommsAccessorySessionListener> commsAccessorySessionListenerProvider;
    private final Provider<CommsDirectiveHandler> commsDirectiveHandlerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DriveModeCallPermissionHandler> driveModeCallPermissionHandlerProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<InitiationLogicFactory> initiationLogicFactoryProvider;
    private final Provider<CallingAPIMonitor> listenerProvider;
    private final Provider<MakeNativeCallHandler> makeNativeCallHandlerProvider;
    private final CommsCallingModule module;
    private final Provider<PushNotificationManager> pushNotificationManagerProvider;

    public CommsCallingModule_ProvidesCallingAPIFactory(CommsCallingModule commsCallingModule, Provider<InitiationLogicFactory> provider, Provider<Context> provider2, Provider<CallingAPIMonitor> provider3, Provider<CallingAPIPopulator> provider4, Provider<CommsDirectiveHandler> provider5, Provider<CommsIdentityManager> provider6, Provider<PushNotificationManager> provider7, Provider<CommsAccessorySessionListener> provider8, Provider<MakeNativeCallHandler> provider9, Provider<DriveModeCallPermissionHandler> provider10, Provider<EventBus> provider11) {
        this.module = commsCallingModule;
        this.initiationLogicFactoryProvider = provider;
        this.contextProvider = provider2;
        this.listenerProvider = provider3;
        this.callingAPIPopulatorProvider = provider4;
        this.commsDirectiveHandlerProvider = provider5;
        this.commsIdentityManagerProvider = provider6;
        this.pushNotificationManagerProvider = provider7;
        this.commsAccessorySessionListenerProvider = provider8;
        this.makeNativeCallHandlerProvider = provider9;
        this.driveModeCallPermissionHandlerProvider = provider10;
        this.eventBusProvider = provider11;
    }

    public static CommsCallingModule_ProvidesCallingAPIFactory create(CommsCallingModule commsCallingModule, Provider<InitiationLogicFactory> provider, Provider<Context> provider2, Provider<CallingAPIMonitor> provider3, Provider<CallingAPIPopulator> provider4, Provider<CommsDirectiveHandler> provider5, Provider<CommsIdentityManager> provider6, Provider<PushNotificationManager> provider7, Provider<CommsAccessorySessionListener> provider8, Provider<MakeNativeCallHandler> provider9, Provider<DriveModeCallPermissionHandler> provider10, Provider<EventBus> provider11) {
        return new CommsCallingModule_ProvidesCallingAPIFactory(commsCallingModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static ICallingAPI provideInstance(CommsCallingModule commsCallingModule, Provider<InitiationLogicFactory> provider, Provider<Context> provider2, Provider<CallingAPIMonitor> provider3, Provider<CallingAPIPopulator> provider4, Provider<CommsDirectiveHandler> provider5, Provider<CommsIdentityManager> provider6, Provider<PushNotificationManager> provider7, Provider<CommsAccessorySessionListener> provider8, Provider<MakeNativeCallHandler> provider9, Provider<DriveModeCallPermissionHandler> provider10, Provider<EventBus> provider11) {
        return (ICallingAPI) Preconditions.checkNotNull(commsCallingModule.providesCallingAPI(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ICallingAPI proxyProvidesCallingAPI(CommsCallingModule commsCallingModule, InitiationLogicFactory initiationLogicFactory, Context context, CallingAPIMonitor callingAPIMonitor, CallingAPIPopulator callingAPIPopulator, CommsDirectiveHandler commsDirectiveHandler, CommsIdentityManager commsIdentityManager, PushNotificationManager pushNotificationManager, CommsAccessorySessionListener commsAccessorySessionListener, MakeNativeCallHandler makeNativeCallHandler, DriveModeCallPermissionHandler driveModeCallPermissionHandler, EventBus eventBus) {
        return (ICallingAPI) Preconditions.checkNotNull(commsCallingModule.providesCallingAPI(initiationLogicFactory, context, callingAPIMonitor, callingAPIPopulator, commsDirectiveHandler, commsIdentityManager, pushNotificationManager, commsAccessorySessionListener, makeNativeCallHandler, driveModeCallPermissionHandler, eventBus), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ICallingAPI mo10268get() {
        return provideInstance(this.module, this.initiationLogicFactoryProvider, this.contextProvider, this.listenerProvider, this.callingAPIPopulatorProvider, this.commsDirectiveHandlerProvider, this.commsIdentityManagerProvider, this.pushNotificationManagerProvider, this.commsAccessorySessionListenerProvider, this.makeNativeCallHandlerProvider, this.driveModeCallPermissionHandlerProvider, this.eventBusProvider);
    }
}
