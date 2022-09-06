package com.amazon.deecomms.alexa.fireos;

import amazon.speech.simclient.Header;
import amazon.speech.simclient.SimClient;
import amazon.speech.simclient.SimError;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.device.SipDeviceState;
import com.amazon.deecomms.alexa.CallTypeEvent;
import com.amazon.deecomms.alexa.CallTypeEventModel;
import com.amazon.deecomms.alexa.HalloEvent;
import com.amazon.deecomms.alexa.InCallEvent;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class SimEventSender {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SimEventSender.class);
    private int lastRequestStatus;
    private final SimClientAlexaInterface simClientAlexaInterface;

    public SimEventSender(SimClientAlexaInterface simClientAlexaInterface) {
        this.simClientAlexaInterface = simClientAlexaInterface;
    }

    private synchronized boolean addDeviceContext(@NonNull SipClientState sipClientState, @NonNull String str) {
        String writeValueAsString;
        SipDeviceState sipDeviceState = CallUtils.getSipDeviceState(sipClientState, str);
        sipDeviceState.setCLIENT_IDENTIFIER("Domain:Application:Communications:Calling");
        String callState = sipDeviceState.getSipCallState().getCallState();
        String callId = sipDeviceState.getSipCallState().getCallId();
        if (SipClientState.CallState.INACTIVE.name().equals(callState) && callId == null) {
            LOG.w("Call ID was null while adding device context");
            return false;
        }
        Header header = new Header("SipClient", "SipClientState");
        try {
            writeValueAsString = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(sipDeviceState);
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Context payload is : ");
            sb.append(writeValueAsString);
            commsLogger.d(sb.toString());
        } catch (JsonProcessingException e) {
            LOG.w("JSON Processing error :", e);
        }
        SimClient simClient = this.simClientAlexaInterface.getSimClient();
        boolean z = true;
        if (simClient == null) {
            LOG.e(String.format("Dropping adding device context since we aren't connected to cloud service yet. ClientState: %s", writeValueAsString));
            return false;
        }
        this.lastRequestStatus = simClient.addDeviceContext(header, writeValueAsString, true);
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Adding device context status : ");
        outline1.append(SimError.getDescriptionFor(this.lastRequestStatus));
        commsLogger2.i(outline1.toString());
        if (this.lastRequestStatus != 0) {
            z = false;
        }
        return z;
    }

    private synchronized boolean sendCallTypeEvent(@NonNull CallTypeEvent callTypeEvent) {
        Header header;
        ObjectWriter withDefaultPrettyPrinter;
        HashMap hashMap;
        header = new Header("SipClient", callTypeEvent.getEventName());
        CallTypeEventModel callTypeEventModel = (CallTypeEventModel) callTypeEvent.getData();
        withDefaultPrettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            hashMap = new HashMap();
            hashMap.put("requestId", callTypeEventModel.getCallId());
            hashMap.put("callExperienceType", callTypeEventModel.getCallType());
        } catch (JsonProcessingException e) {
            LOG.w("JSON Processing error :", e);
            return false;
        }
        return sendEventHelper(callTypeEvent, header, withDefaultPrettyPrinter.writeValueAsString(hashMap));
    }

    private synchronized boolean sendEventHelper(InCallEvent inCallEvent, Header header, String str) {
        SimClient simClient = this.simClientAlexaInterface.getSimClient();
        boolean z = true;
        if (simClient == null) {
            LOG.e(String.format("Dropping event since we aren't connected to cloud service yet. Event: %s, ClientState: %s", inCallEvent, inCallEvent.getData()));
            return false;
        }
        this.lastRequestStatus = simClient.sendEvent(header, str, new ReportedEvent(inCallEvent, this.simClientAlexaInterface), 0);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Sending event status : ");
        outline1.append(SimError.getDescriptionFor(this.lastRequestStatus));
        commsLogger.i(outline1.toString());
        if (this.lastRequestStatus != 0) {
            z = false;
        }
        return z;
    }

    private synchronized boolean sendHalloEvent(@NonNull HalloEvent halloEvent) {
        JsonProcessingException e;
        String str;
        SipClientState sipClientState = (SipClientState) halloEvent.getData();
        LOG.i(String.format("Outgoing Event : %s; Call ID: %s", halloEvent.toString(), LOG.sensitiveCallId(sipClientState.getCallId())));
        LOG.d(String.format("SipClientState: %s", sipClientState.toString()));
        String name = sipClientState.getCallState().name();
        String callId = sipClientState.getCallId();
        if (SipClientState.CallState.INACTIVE.name().equals(name) && callId == null) {
            LOG.w("Call ID was null while adding device context");
            return false;
        }
        Header header = new Header("SipClient", halloEvent.getEventName());
        ObjectWriter withDefaultPrettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            str = withDefaultPrettyPrinter.writeValueAsString(sipClientState);
            try {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("SipClient payload is : ");
                sb.append(LOG.sensitive(str));
                commsLogger.d(sb.toString());
                String writeValueAsString = withDefaultPrettyPrinter.writeValueAsString(new HalloEventPayload("Domain:Application:Communications:Calling", str));
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Event payload is : ");
                sb2.append(LOG.sensitive(writeValueAsString));
                commsLogger2.d(sb2.toString());
                str = writeValueAsString;
            } catch (JsonProcessingException e2) {
                e = e2;
                LOG.w("JSON Processing error :", e);
                return sendEventHelper(halloEvent, header, str);
            }
        } catch (JsonProcessingException e3) {
            e = e3;
            str = "";
        }
        return sendEventHelper(halloEvent, header, str);
    }

    public synchronized boolean sendEvent(@NonNull InCallEvent inCallEvent) {
        boolean z = true;
        if (inCallEvent instanceof HalloEvent) {
            if (!sendHalloEvent((HalloEvent) inCallEvent) || !addDeviceContext((SipClientState) inCallEvent.getData(), inCallEvent.getCallId())) {
                z = false;
            }
            return z;
        } else if (!(inCallEvent instanceof CallTypeEvent)) {
            return false;
        } else {
            CallTypeEventModel callTypeEventModel = (CallTypeEventModel) inCallEvent.getData();
            if (!sendCallTypeEvent((CallTypeEvent) inCallEvent) || !addDeviceContext(callTypeEventModel.getSipClientState(), callTypeEventModel.getCallId())) {
                z = false;
            }
            return z;
        }
    }
}
