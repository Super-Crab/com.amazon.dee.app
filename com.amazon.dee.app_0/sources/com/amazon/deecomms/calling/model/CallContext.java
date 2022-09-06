package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class CallContext {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallContext.class);
    private BeginCallPayload beginCallPayload;
    private Call currentCall;
    private boolean isCallDowngradedToAudio;
    private boolean isEnhancedProcessingCall;
    private boolean isGUIInitiated;
    private boolean isIncomingCall;
    private boolean isRTTEnabled;
    private boolean shouldShowCallRating;
    private String callInitiationScreenName = "";
    private String callEndReason = "";
    private String callDuration = "";
    private String remoteParticipantName = "";

    @VisibleForTesting
    public BeginCallPayload getBeginCallPayload() {
        return this.beginCallPayload;
    }

    public String getCallDuration() {
        return this.callDuration;
    }

    public String getCallEndReason() {
        return this.callEndReason;
    }

    public String getCallID() {
        Call call = this.currentCall;
        return call != null ? call.getCallId() : "";
    }

    @VisibleForTesting
    public Call getCurrentCall() {
        return this.currentCall;
    }

    public String getDirection() {
        return this.isIncomingCall ? Constants.INCOMING : Constants.OUTGOING;
    }

    public String getRemoteParticipantName() {
        return this.remoteParticipantName;
    }

    public boolean isCallDowngradedToAudio() {
        return this.isCallDowngradedToAudio;
    }

    public boolean isDropIn() {
        if (this.isIncomingCall) {
            return this.currentCall.isDropInCall();
        }
        BeginCallPayload beginCallPayload = this.beginCallPayload;
        if (beginCallPayload == null) {
            return false;
        }
        return beginCallPayload.sipCommand.payload.isDropIn.booleanValue();
    }

    public boolean isEnhancedProcessingCall() {
        if (this.isIncomingCall) {
            GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Enhanced processing for incoming call is : "), this.isEnhancedProcessingCall, LOG);
            return this.isEnhancedProcessingCall;
        }
        boolean equalsIgnoreCase = EnhancedProcessingState.ON.getValue().equalsIgnoreCase(this.beginCallPayload.displayInfo.mediaSettingsInfo.enhancedProcessing);
        GeneratedOutlineSupport.outline5("Enhanced processing for outgoing call is : ", equalsIgnoreCase, LOG);
        return equalsIgnoreCase;
    }

    public boolean isGroupCall() {
        if (this.isIncomingCall) {
            SipHeaders incomingHeaders = this.currentCall.getIncomingHeaders();
            return incomingHeaders != null && incomingHeaders.contains(Constants.GROUPID_HEADER);
        }
        BeginCallPayload beginCallPayload = this.beginCallPayload;
        if (beginCallPayload != null) {
            for (Map<String, Object> map : beginCallPayload.sipCommand.payload.headers) {
                if (map.containsKey(Constants.GROUPID_HEADER)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isIncomingCall() {
        return this.isIncomingCall;
    }

    public boolean isPSTN() {
        BeginCallPayload beginCallPayload = this.beginCallPayload;
        if (beginCallPayload != null) {
            return CallProvider.COBO.equalsIgnoreCase(beginCallPayload.sipCommand.payload.callProvider);
        }
        return false;
    }

    public boolean isRTTEnabledByCaller() {
        for (BeginCallPayload.MediaStream mediaStream : this.beginCallPayload.sipCommand.payload.offer.mediaStreams) {
            if (mediaStream.type.equalsIgnoreCase(Constants.Calling.MEDIA_STREAM_TYPE_TEXT)) {
                return true;
            }
        }
        return false;
    }

    public boolean isVideoCall() {
        Call call;
        boolean z = this.isCallDowngradedToAudio || Utils.areAccessoriesConnected() || Utils.isInDriveMode();
        if (this.isIncomingCall && (call = this.currentCall) != null) {
            return call.isVideoRequested() && !z;
        }
        BeginCallPayload beginCallPayload = this.beginCallPayload;
        if (beginCallPayload != null) {
            for (BeginCallPayload.MediaStream mediaStream : beginCallPayload.sipCommand.payload.offer.mediaStreams) {
                if (mediaStream.type.equalsIgnoreCase("VIDEO")) {
                    return !z;
                }
            }
        }
        return false;
    }

    public void reset() {
        this.beginCallPayload = null;
        this.currentCall = null;
        this.callInitiationScreenName = null;
        this.callEndReason = null;
        this.isIncomingCall = false;
        this.isEnhancedProcessingCall = false;
        this.isGUIInitiated = false;
    }

    public void setBeginCallPayload(@NonNull BeginCallPayload beginCallPayload) {
        this.beginCallPayload = beginCallPayload;
        this.isIncomingCall = false;
    }

    public void setCallDowngradedToAudio(boolean z) {
        this.isCallDowngradedToAudio = z;
    }

    public void setCallDuration(String str) {
        this.callDuration = str;
    }

    public void setCallEndReason(String str) {
        this.callEndReason = str;
    }

    public void setCurrentCall(@NonNull Call call) {
        this.currentCall = call;
        this.isIncomingCall = call.getOrigin().equals(Call.Side.Remote);
        if (this.isIncomingCall) {
            this.isGUIInitiated = false;
            this.callInitiationScreenName = "";
        }
        this.callEndReason = null;
    }

    public void setEnhancedProcessingCall(boolean z) {
        GeneratedOutlineSupport.outline5("Setting enhanced processing state to : ", z, LOG);
        this.isEnhancedProcessingCall = z;
    }

    public void setOutgoingCallDetails(boolean z, @NonNull String str) {
        this.isGUIInitiated = z;
        this.callInitiationScreenName = str;
    }

    public void setRemoteParticipantName(@Nullable String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        this.remoteParticipantName = str;
    }

    public void setShouldShowCallRating(boolean z) {
        this.shouldShowCallRating = z;
    }

    public boolean shouldShowCallRating() {
        return this.shouldShowCallRating;
    }
}
