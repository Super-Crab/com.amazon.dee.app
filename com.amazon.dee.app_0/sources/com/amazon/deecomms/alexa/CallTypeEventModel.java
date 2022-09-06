package com.amazon.deecomms.alexa;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.sip.SipClientState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
/* loaded from: classes12.dex */
public class CallTypeEventModel {
    private final List<String> callFlags;
    private final String callId;
    private final String callType;
    private final SipClientState sipClientState;

    public CallTypeEventModel(@NonNull String str, @NonNull List<String> list, @NonNull String str2, @NonNull SipClientState sipClientState) {
        this.callType = str;
        this.callFlags = list;
        this.callId = str2;
        this.sipClientState = sipClientState;
    }

    private String toSipDeviceStateSerialized(@NonNull SipClientState sipClientState) {
        try {
            return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(CallUtils.getSipDeviceState(sipClientState, this.callId));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public List<String> getCallFlags() {
        return this.callFlags;
    }

    @NonNull
    public String getCallId() {
        return this.callId;
    }

    @NonNull
    public String getCallType() {
        return this.callType;
    }

    @NonNull
    public SipClientState getSipClientState() {
        return this.sipClientState;
    }

    @NonNull
    public String getSipDeviceStateSerialized() {
        return toSipDeviceStateSerialized(this.sipClientState);
    }
}
