package com.amazon.deecomms.alexa;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum HalloEventType {
    OUTBOUND_CALL_RINGING("OutboundCallRinging"),
    OUTBOUND_CALL_REQUESTED("OutboundCallRequested"),
    INBOUND_CALL_RECEIVED("InboundCallReceived"),
    INBOUND_CALL_RINGING("InboundCallRinging"),
    OUTBOUND_CALL_ACCEPTED("OutboundCallAccepted"),
    INBOUND_CALL_ACCEPTED("InboundCallAccepted"),
    LOCAL_HANG_UP("LocalHangUp"),
    REMOTE_HANG_UP("RemoteHangUp"),
    REMOTE_HANG_UP_ON_RING("RemoteHangUpOnRing"),
    CALL_DISCONNECTED("CallDisconnected"),
    TERMINATE_CALL_FOR_ALL("TerminateCallForAll"),
    CALL_LOCAL_HOLD("CallLocalHold"),
    CALL_REMOTE_HOLD("CallRemoteHold"),
    CALL_RESUMED("CallResumed"),
    CALL_ERROR("CallError"),
    CALL_MEDIA_ERROR("CallMediaError"),
    CONFIGURE_COMMS_REQUEST("ConfigureCommsRequest"),
    CONFIGURE_COMMS_RESULT("ConfigureCommsResult"),
    CALL_CANCEL("CallCancel"),
    SIP_CLIENT_UPDATE_STATE("SipClientUpdateState"),
    INBOUND_CALLERID_RECEIVED("InboundCallerIdReceived"),
    RECONNECT_CALL_REQUEST("ReconnectCallRequest"),
    RECONNECT_CALL_RESULT("ReconnectCallResult"),
    SIP_INFO_RESPONSE("SipInfoResponse"),
    SEND_DTMF_SUCCEEDED("SendDTMFSucceeded"),
    SEND_DTMF_FAILED("SendDTMFFailed");
    
    @NonNull
    private final String eventName;

    HalloEventType(@NonNull String str) {
        this.eventName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.eventName;
    }
}
