package com.amazon.deecomms.alexa;

import android.content.Context;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler;
import com.android.tools.r8.GeneratedOutlineSupport;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CommsCapabilityAgent extends AlexaCapabilityAgentService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsCapabilityAgent.class);
    private static final Set<AlexaCapability> capabilities = Collections.unmodifiableSet(new HashSet(Arrays.asList(AlexaCapability.create("SipClient", "0.1"), AlexaCapability.create(HalloConstants.CONTACTS_NAMESPACE, "0.1"), AlexaCapability.create(PCCConstants.PHONE_CALL_CONTROLLER_NAMESPACE, PCCConstants.CAPABILITY_INTERFACE_VERSION), AlexaCapability.create(MessagingControllerConstant.MESSAGING_CONTROLLER_NAMESPACE, "0.1"))));
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CallContext callContext;
    @Inject
    Lazy<CallManager> callManagerLazy;
    @Inject
    CallingFacade callingFacade;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    Context context;
    @Inject
    EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    @Inject
    NameChangeObservable nameChangeObservable;
    @Inject
    protected PCCDirectiveHandler pCCDirectiveHandler;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomManager telecomManager;
    @Inject
    VoxUtils voxUtils;

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public Set<AlexaCapability> getCapabilities() {
        return capabilities;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate();
        LOG.i("Comms Capability Agent created");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(@NonNull AlexaDirective alexaDirective) {
        String namespace = alexaDirective.getNamespace();
        String name = alexaDirective.getName();
        String payload = alexaDirective.getAlexaPayload().getPayload();
        if (namespace != null && name != null && payload != null) {
            char c = 65535;
            int hashCode = namespace.hashCode();
            if (hashCode != 117940768) {
                if (hashCode != 1253912869) {
                    if (hashCode != 1682705326) {
                        if (hashCode == 2042189718 && namespace.equals(PCCConstants.PHONE_CALL_CONTROLLER_NAMESPACE)) {
                            c = 2;
                        }
                    } else if (namespace.equals(MessagingControllerConstant.MESSAGING_CONTROLLER_NAMESPACE)) {
                        c = 3;
                    }
                } else if (namespace.equals("SipClient")) {
                    c = 0;
                }
            } else if (namespace.equals(HalloConstants.CONTACTS_NAMESPACE)) {
                c = 1;
            }
            if (c != 0 && c != 1) {
                if (c == 2) {
                    this.pCCDirectiveHandler.handleDirective(this.context, name, payload);
                } else if (c != 3) {
                    GeneratedOutlineSupport.outline3("Received directive over unsupported namespace: ", namespace, LOG);
                    return false;
                }
                return new MessagingControllerDirectiveHandler(this.capabilitiesManager).handleDirective(this.context, name, payload);
            }
            return new CommsDirectiveHandler(this.context, this.commsIdentityManager, this.callManagerLazy, this.voxUtils, this.applicationManager, this.callingFacade, this.capabilitiesManager, this.sipClientState, this.callContext, this.nameChangeObservable, this.enhancedProcessingStateObservable).handleDirective(name, namespace, payload);
        }
        LOG.e("Received directive does not have the proper components for processing.");
        return false;
    }
}
