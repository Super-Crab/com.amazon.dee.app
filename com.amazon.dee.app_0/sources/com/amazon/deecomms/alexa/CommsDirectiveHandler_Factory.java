package com.amazon.deecomms.alexa;

import android.content.Context;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsDirectiveHandler_Factory implements Factory<CommsDirectiveHandler> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CallContext> callContextProvider;
    private final Provider<CallManager> callManagerLazyProvider;
    private final Provider<CallingFacade> callingFacadeProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EnhancedProcessingStateObservable> enhancedProcessingStateObservableProvider;
    private final Provider<NameChangeObservable> nameChangeObservableProvider;
    private final Provider<SipClientState> sipClientStateProvider;
    private final Provider<VoxUtils> voxUtilsProvider;

    public CommsDirectiveHandler_Factory(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CallManager> provider3, Provider<VoxUtils> provider4, Provider<ApplicationManager> provider5, Provider<CallingFacade> provider6, Provider<CapabilitiesManager> provider7, Provider<SipClientState> provider8, Provider<CallContext> provider9, Provider<NameChangeObservable> provider10, Provider<EnhancedProcessingStateObservable> provider11) {
        this.contextProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.callManagerLazyProvider = provider3;
        this.voxUtilsProvider = provider4;
        this.applicationManagerProvider = provider5;
        this.callingFacadeProvider = provider6;
        this.capabilitiesManagerProvider = provider7;
        this.sipClientStateProvider = provider8;
        this.callContextProvider = provider9;
        this.nameChangeObservableProvider = provider10;
        this.enhancedProcessingStateObservableProvider = provider11;
    }

    public static CommsDirectiveHandler_Factory create(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CallManager> provider3, Provider<VoxUtils> provider4, Provider<ApplicationManager> provider5, Provider<CallingFacade> provider6, Provider<CapabilitiesManager> provider7, Provider<SipClientState> provider8, Provider<CallContext> provider9, Provider<NameChangeObservable> provider10, Provider<EnhancedProcessingStateObservable> provider11) {
        return new CommsDirectiveHandler_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static CommsDirectiveHandler newCommsDirectiveHandler(Context context, CommsIdentityManager commsIdentityManager, Lazy<CallManager> lazy, VoxUtils voxUtils, ApplicationManager applicationManager, CallingFacade callingFacade, CapabilitiesManager capabilitiesManager, SipClientState sipClientState, CallContext callContext, NameChangeObservable nameChangeObservable, EnhancedProcessingStateObservable enhancedProcessingStateObservable) {
        return new CommsDirectiveHandler(context, commsIdentityManager, lazy, voxUtils, applicationManager, callingFacade, capabilitiesManager, sipClientState, callContext, nameChangeObservable, enhancedProcessingStateObservable);
    }

    public static CommsDirectiveHandler provideInstance(Provider<Context> provider, Provider<CommsIdentityManager> provider2, Provider<CallManager> provider3, Provider<VoxUtils> provider4, Provider<ApplicationManager> provider5, Provider<CallingFacade> provider6, Provider<CapabilitiesManager> provider7, Provider<SipClientState> provider8, Provider<CallContext> provider9, Provider<NameChangeObservable> provider10, Provider<EnhancedProcessingStateObservable> provider11) {
        return new CommsDirectiveHandler(provider.mo10268get(), provider2.mo10268get(), DoubleCheck.lazy(provider3), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDirectiveHandler mo10268get() {
        return provideInstance(this.contextProvider, this.commsIdentityManagerProvider, this.callManagerLazyProvider, this.voxUtilsProvider, this.applicationManagerProvider, this.callingFacadeProvider, this.capabilitiesManagerProvider, this.sipClientStateProvider, this.callContextProvider, this.nameChangeObservableProvider, this.enhancedProcessingStateObservableProvider);
    }
}
