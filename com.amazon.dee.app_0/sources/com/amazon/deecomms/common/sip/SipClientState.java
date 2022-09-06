package com.amazon.deecomms.common.sip;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.sipclient.RealTimeTextMetrics;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.models.sip.SIPContact;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.common.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties({"currentActiveCall", "beginCallPayload", "remoteParticipantName", "isRTTEnabled", "enhancedProcessingState", "srtpKey", "isRTTSettingEnabled"})
/* loaded from: classes12.dex */
public class SipClientState {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SipClientState.class);
    private BeginCallPayload beginCallPayload;
    @JsonProperty
    private long callConnectDurationInMillis;
    @JsonProperty
    private String callId;
    @JsonProperty
    private long callTotalDurationInMillis;
    @JsonProperty
    private SIPContact callee;
    @JsonProperty
    private SIPContact caller;
    private Call currentActiveCall;
    private String deviceGruu;
    private boolean enhancedProcessingSettingEnabled;
    private EnhancedProcessingState enhancedProcessingState;
    @JsonProperty
    private boolean isDropIn;
    private String localParticipantId;
    @JsonProperty
    private RealTimeTextMetrics realTimeTextMetrics;
    @JsonProperty
    private String reason;
    private String remoteParticipantId;
    private String remoteParticipantName;
    @JsonProperty
    private String side;
    private String srtpKey;
    @JsonProperty
    private String statusCode;
    @NonNull
    @JsonProperty
    private CallState callState = CallState.INACTIVE;
    @NonNull
    private CallType callType = CallType.NONE;
    @NonNull
    @JsonProperty
    private String callProvider = "";
    private String cspId = "";
    private boolean userTurnedVideoOff = false;
    private boolean isRTTSettingEnabled = false;
    private boolean isGroupCall = false;
    @NonNull
    private DeviceCallingService.State sipRegistrationStatus = DeviceCallingService.State.Uninitialized;

    /* loaded from: classes12.dex */
    public enum CallState {
        INACTIVE,
        CALLING_INITIATED,
        CALLING,
        INBOUND_RECEIVED,
        INBOUND_RINGING,
        OUTBOUND_RINGING,
        ACTIVE,
        LOCAL_HOLD,
        REMOTE_HOLD
    }

    public BeginCallPayload getBeginCallPayload() {
        return this.beginCallPayload;
    }

    public long getCallConnectDurationInMillis() {
        return this.callConnectDurationInMillis;
    }

    public String getCallId() {
        return this.callId;
    }

    @NonNull
    public synchronized String getCallProvider() {
        return this.callProvider;
    }

    @NonNull
    public synchronized CallState getCallState() {
        return this.callState;
    }

    public long getCallTotalDurationInMillis() {
        return this.callTotalDurationInMillis;
    }

    @NonNull
    public synchronized CallType getCallType() {
        return this.callType;
    }

    public SIPContact getCallee() {
        return this.callee;
    }

    public SIPContact getCaller() {
        return this.caller;
    }

    public String getCspId() {
        return this.cspId;
    }

    public synchronized Call getCurrentActiveCall() {
        return this.currentActiveCall;
    }

    public String getDeviceGruu() {
        return this.deviceGruu;
    }

    @NonNull
    public EnhancedProcessingState getEnhancedProcessingState() {
        return this.enhancedProcessingState;
    }

    public String getLocalParticipantId() {
        return this.localParticipantId;
    }

    public RealTimeTextMetrics getRealTimeTextMetrics() {
        return this.realTimeTextMetrics;
    }

    public String getReason() {
        return this.reason;
    }

    public String getRemoteParticipantId() {
        return this.remoteParticipantId;
    }

    public synchronized String getRemoteParticipantName() {
        return this.remoteParticipantName;
    }

    public String getRemoteUri() {
        Call currentActiveCall = getCurrentActiveCall();
        if (currentActiveCall == null || this.caller == null || this.callee == null) {
            return null;
        }
        return (currentActiveCall.getOrigin() == Call.Side.Remote ? this.caller : this.callee).getUri();
    }

    public String getSide() {
        return this.side;
    }

    @NonNull
    public DeviceCallingService.State getSipRegistrationStatus() {
        return this.sipRegistrationStatus;
    }

    public String getSrtpKey() {
        return this.srtpKey;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public boolean hasUserTurnedVideoOff() {
        return this.userTurnedVideoOff;
    }

    public boolean isEnhancedProcessedCall() {
        return EnhancedProcessingState.ON.equals(getEnhancedProcessingState());
    }

    public boolean isEnhancedProcessingSettingEnabled() {
        return this.enhancedProcessingSettingEnabled;
    }

    public boolean isGroupCall() {
        Call call = this.currentActiveCall;
        if (call != null) {
            if (call.getOrigin() == Call.Side.Local) {
                return this.isGroupCall;
            }
            SipHeaders incomingHeaders = this.currentActiveCall.getIncomingHeaders();
            return incomingHeaders != null && incomingHeaders.contains(Constants.GROUPID_HEADER);
        }
        return this.isGroupCall;
    }

    public boolean isRTTSettingEnabled() {
        return this.isRTTSettingEnabled;
    }

    public void reset() {
        setCallState(CallState.INACTIVE);
        setCallId(null);
        setCurrentActiveCall(null);
        setUserTurnedVideoOff(false);
        setCallType(CallType.NONE);
        setCallProvider("");
        setSrtpKey("");
        setEnhancedProcessingSetting(false);
        setEnhancedProcessingState(EnhancedProcessingState.NOT_APPLICABLE);
        setBeginCallPayload(null);
        setRemoteParticipantName(null);
        setGroupCall(false);
    }

    public void setBeginCallPayload(@NonNull BeginCallPayload beginCallPayload) {
        this.beginCallPayload = beginCallPayload;
    }

    public void setCallConnectDurationInMillis(long j) {
        this.callConnectDurationInMillis = j;
    }

    public void setCallId(String str) {
        this.callId = str;
    }

    public synchronized void setCallProvider(@NonNull String str) {
        this.callProvider = str;
    }

    public synchronized void setCallState(@NonNull CallState callState) {
        this.callState = callState;
    }

    public void setCallTotalDurationInMillis(long j) {
        this.callTotalDurationInMillis = j;
    }

    public synchronized void setCallType(@NonNull CallType callType) {
        this.callType = callType;
        this.isDropIn = callType.isDropIn();
    }

    public void setCallee(SIPContact sIPContact) {
        this.callee = sIPContact;
    }

    public void setCaller(SIPContact sIPContact) {
        this.caller = sIPContact;
    }

    public void setCspId(String str) {
        this.cspId = str;
    }

    public synchronized void setCurrentActiveCall(Call call) {
        this.currentActiveCall = call;
        LOG.i("setCurrentActiveCall");
    }

    public void setDeviceGruu(String str) {
        this.deviceGruu = str;
    }

    public void setEnhancedProcessingSetting(boolean z) {
        this.enhancedProcessingSettingEnabled = z;
    }

    public void setEnhancedProcessingState(@NonNull EnhancedProcessingState enhancedProcessingState) {
        this.enhancedProcessingState = enhancedProcessingState;
    }

    public void setGroupCall(boolean z) {
        this.isGroupCall = z;
    }

    public void setLocalParticipantId(String str) {
        this.localParticipantId = str;
    }

    public void setRTTSettingEnabled(boolean z) {
        this.isRTTSettingEnabled = z;
    }

    public void setRealTimeTextMetrics(@NonNull RealTimeTextMetrics realTimeTextMetrics) {
        this.realTimeTextMetrics = realTimeTextMetrics;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setRemoteParticipantId(String str) {
        this.remoteParticipantId = str;
    }

    public synchronized void setRemoteParticipantName(String str) {
        this.remoteParticipantName = str;
    }

    public void setSide(@NonNull String str) {
        this.side = str;
    }

    public void setSipRegistrationStatus(@NonNull DeviceCallingService.State state) {
        this.sipRegistrationStatus = state;
    }

    public void setSrtpKey(String str) {
        this.srtpKey = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public void setUserTurnedVideoOff(boolean z) {
        this.userTurnedVideoOff = z;
    }
}
