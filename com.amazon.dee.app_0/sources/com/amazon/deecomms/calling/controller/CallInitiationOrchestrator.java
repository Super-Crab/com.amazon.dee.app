package com.amazon.deecomms.calling.controller;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.alexa.fireos.CommsAlexaServicesConnectionListener;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.model.BeginCallMapper;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.InitiateOutboundCallRequest;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CallInitiationOrchestrator {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallInitiationOrchestrator.class);
    private AlexaServicesConnection alexaServicesConnection;
    private BeginCallMapper beginCallMapper;
    private CallContext callContext;
    private CallPayloadValidator callPayloadValidator;
    private CapabilitiesManager capabilitiesManager;
    private CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener;
    private CommsIdentityManager commsIdentityManager;
    private ValidBeginCallPayloadHandler validPayloadHandler;

    @Inject
    public CallInitiationOrchestrator(@NonNull CallPayloadValidator callPayloadValidator, @NonNull CommsIdentityManager commsIdentityManager, @NonNull BeginCallMapper beginCallMapper, @NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler, @NonNull @Named("commsAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection, @NonNull CommsAlexaServicesConnectionListener commsAlexaServicesConnectionListener, @NonNull CallContext callContext, @NonNull CapabilitiesManager capabilitiesManager) {
        this.callPayloadValidator = callPayloadValidator;
        this.commsIdentityManager = commsIdentityManager;
        this.validPayloadHandler = validBeginCallPayloadHandler;
        this.beginCallMapper = beginCallMapper;
        this.callContext = callContext;
        this.alexaServicesConnection = alexaServicesConnection;
        this.commsAlexaServicesConnectionListener = commsAlexaServicesConnectionListener;
        this.capabilitiesManager = capabilitiesManager;
    }

    private void processValidPayload(@NonNull BeginCallPayload beginCallPayload) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Processing valid begin call payload with call id : ");
        outline1.append(beginCallPayload.getCallId());
        commsLogger.i(outline1.toString());
        this.validPayloadHandler.handle(beginCallPayload);
    }

    private void requestValidPayload(@NonNull BeginCallPayload beginCallPayload) {
        LOG.i("Requesting valid payload");
        Optional<InitiateOutboundCallRequest> map = this.beginCallMapper.map(beginCallPayload);
        if (map.isPresent()) {
            makeCallInitiationNetworkRequest(map.get());
            return;
        }
        LOG.i("Unable to obtain valid payload");
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CALL_CALLER_HOMEGROUP);
    }

    private void setInternalState() {
        this.callContext.setOutgoingCallDetails(true, "");
    }

    public void handleCallInitiationRequest(@NonNull BeginCallPayload beginCallPayload) {
        int isPayloadValid = this.callPayloadValidator.isPayloadValid(beginCallPayload);
        if (isPayloadValid == 0) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Valid begincall payload with call id : ");
            outline1.append(beginCallPayload.getCallId());
            commsLogger.i(outline1.toString());
            processValidPayload(beginCallPayload);
        } else if (isPayloadValid != 1) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Failed to retry for a valid begincall payload with call id : ");
            outline12.append(beginCallPayload.getCallId());
            commsLogger2.i(outline12.toString());
        } else {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline13 = GeneratedOutlineSupport.outline1("Invalid begincall payload with call id : ");
            outline13.append(beginCallPayload.getCallId());
            commsLogger3.i(outline13.toString());
            requestValidPayload(beginCallPayload);
        }
    }

    public void makeCallInitiationNetworkRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest) {
        LOG.i("Using Synchronous begin call flow");
        new SynchronousInitiateCallRequest(initiateOutboundCallRequest, this.validPayloadHandler, this.commsIdentityManager, this).execute(new Void[0]);
    }

    public void handleCallInitiationRequest(@NonNull InitiateOutboundCallRequest initiateOutboundCallRequest) {
        setInternalState();
        makeCallInitiationNetworkRequest(initiateOutboundCallRequest);
    }
}
