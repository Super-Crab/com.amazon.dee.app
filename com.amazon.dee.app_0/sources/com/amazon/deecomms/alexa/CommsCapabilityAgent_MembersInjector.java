package com.amazon.deecomms.alexa;

import android.content.Context;
import android.telecom.TelecomManager;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsCapabilityAgent_MembersInjector implements MembersInjector<CommsCapabilityAgent> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallManager> callManagerLazyProvider;
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<NameChangeObservable> nameChangeObservableProvider;
    private final Provider<PCCDirectiveHandler> pCCDirectiveHandlerProvider;
    private final Provider<PCCContextProvider> pccContextProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<TelecomManager> telecomManagerProvider;
    private final Provider<VoxUtils> voxUtilsProvider;

    public CommsCapabilityAgent_MembersInjector(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<PCCContextProvider> provider3, Provider<TelecomManager> provider4, Provider<Context> provider5, Provider<CallManager> provider6, Provider<VoxUtils> provider7, Provider<ApplicationManager> provider8, Provider<PCCDirectiveHandler> provider9, Provider<CallingFacade> provider10, Provider<SipClientState> provider11, Provider<NameChangeObservable> provider12, Provider<CallContext> provider13, Provider<EnhancedProcessingStateObservable> provider14) {
        this.commsIdentityManagerProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.pccContextProvider = provider3;
        this.telecomManagerProvider = provider4;
        this.contextProvider = provider5;
        this.callManagerLazyProvider = provider6;
        this.voxUtilsProvider = provider7;
        this.applicationManagerProvider = provider8;
        this.pCCDirectiveHandlerProvider = provider9;
        this.callingFacadeProvider = provider10;
        this.sipClientStateProvider = provider11;
        this.nameChangeObservableProvider = provider12;
        this.callContextProvider = provider13;
        this.enhancedProcessingStateObservableProvider = provider14;
    }

    public static MembersInjector<CommsCapabilityAgent> create(Provider<CommsIdentityManager> provider, Provider<CapabilitiesManager> provider2, Provider<PCCContextProvider> provider3, Provider<TelecomManager> provider4, Provider<Context> provider5, Provider<CallManager> provider6, Provider<VoxUtils> provider7, Provider<ApplicationManager> provider8, Provider<PCCDirectiveHandler> provider9, Provider<CallingFacade> provider10, Provider<SipClientState> provider11, Provider<NameChangeObservable> provider12, Provider<CallContext> provider13, Provider<EnhancedProcessingStateObservable> provider14) {
        return new CommsCapabilityAgent_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public static void injectApplicationManager(CommsCapabilityAgent commsCapabilityAgent, ApplicationManager applicationManager) {
        commsCapabilityAgent.applicationManager = applicationManager;
    }

    public static void injectCallContext(CommsCapabilityAgent commsCapabilityAgent, CallContext callContext) {
        commsCapabilityAgent.callContext = callContext;
    }

    public static void injectCallManagerLazy(CommsCapabilityAgent commsCapabilityAgent, Lazy<CallManager> lazy) {
        commsCapabilityAgent.callManagerLazy = lazy;
    }

    public static void injectCallingFacade(CommsCapabilityAgent commsCapabilityAgent, CallingFacade callingFacade) {
        commsCapabilityAgent.callingFacade = callingFacade;
    }

    public static void injectCapabilitiesManager(CommsCapabilityAgent commsCapabilityAgent, CapabilitiesManager capabilitiesManager) {
        commsCapabilityAgent.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(CommsCapabilityAgent commsCapabilityAgent, CommsIdentityManager commsIdentityManager) {
        commsCapabilityAgent.commsIdentityManager = commsIdentityManager;
    }

    public static void injectContext(CommsCapabilityAgent commsCapabilityAgent, Context context) {
        commsCapabilityAgent.context = context;
    }

    public static void injectEnhancedProcessingStateObservable(CommsCapabilityAgent commsCapabilityAgent, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        commsCapabilityAgent.enhancedProcessingStateObservable = enhancedProcessingStateObservable;
    }

    public static void injectNameChangeObservable(CommsCapabilityAgent commsCapabilityAgent, NameChangeObservable nameChangeObservable) {
        commsCapabilityAgent.nameChangeObservable = nameChangeObservable;
    }

    public static void injectPCCDirectiveHandler(CommsCapabilityAgent commsCapabilityAgent, PCCDirectiveHandler pCCDirectiveHandler) {
        commsCapabilityAgent.pCCDirectiveHandler = pCCDirectiveHandler;
    }

    public static void injectPccContextProvider(CommsCapabilityAgent commsCapabilityAgent, PCCContextProvider pCCContextProvider) {
        commsCapabilityAgent.pccContextProvider = pCCContextProvider;
    }

    public static void injectSipClientState(CommsCapabilityAgent commsCapabilityAgent, SipClientState sipClientState) {
        commsCapabilityAgent.sipClientState = sipClientState;
    }

    public static void injectTelecomManager(CommsCapabilityAgent commsCapabilityAgent, TelecomManager telecomManager) {
        commsCapabilityAgent.telecomManager = telecomManager;
    }

    public static void injectVoxUtils(CommsCapabilityAgent commsCapabilityAgent, VoxUtils voxUtils) {
        commsCapabilityAgent.voxUtils = voxUtils;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsCapabilityAgent commsCapabilityAgent) {
        commsCapabilityAgent.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        commsCapabilityAgent.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        commsCapabilityAgent.pccContextProvider = this.pccContextProvider.mo10268get();
        commsCapabilityAgent.telecomManager = this.telecomManagerProvider.mo10268get();
        commsCapabilityAgent.context = this.contextProvider.mo10268get();
        commsCapabilityAgent.callManagerLazy = DoubleCheck.lazy(this.callManagerLazyProvider);
        commsCapabilityAgent.voxUtils = this.voxUtilsProvider.mo10268get();
        commsCapabilityAgent.applicationManager = this.applicationManagerProvider.mo10268get();
        commsCapabilityAgent.pCCDirectiveHandler = this.pCCDirectiveHandlerProvider.mo10268get();
        commsCapabilityAgent.callingFacade = this.callingFacadeProvider.mo10268get();
        commsCapabilityAgent.sipClientState = this.sipClientStateProvider.mo10268get();
        commsCapabilityAgent.nameChangeObservable = this.nameChangeObservableProvider.mo10268get();
        commsCapabilityAgent.callContext = this.callContextProvider.mo10268get();
        commsCapabilityAgent.enhancedProcessingStateObservable = this.enhancedProcessingStateObservableProvider.mo10268get();
    }
}
