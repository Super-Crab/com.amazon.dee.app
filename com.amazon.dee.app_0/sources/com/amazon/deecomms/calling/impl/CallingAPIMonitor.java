package com.amazon.deecomms.calling.impl;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.CallListener;
import com.amazon.comms.calling.service.ErrorCode;
import com.amazon.comms.calling.service.HangupReason;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.api.CallInfo;
import com.amazon.deecomms.calling.api.CallStateListener;
import com.amazon.deecomms.common.Constants;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CallingAPIMonitor implements CallListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingAPIMonitor.class);
    @VisibleForTesting
    CallInfo currentCall;
    @VisibleForTesting
    CallStateListener currentListener = null;
    @NonNull
    private final CallingAPIPopulator populator;

    @Inject
    public CallingAPIMonitor(@NonNull CallingAPIPopulator callingAPIPopulator) {
        this.populator = callingAPIPopulator;
    }

    public void addListener(@NonNull CallStateListener callStateListener) {
        this.currentListener = callStateListener;
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onAccepted(Call call) {
        CallStateListener callStateListener = this.currentListener;
        if (callStateListener != null) {
            callStateListener.onCallAccepted(this.currentCall);
        }
    }

    public void onCallAdded(@NonNull Call call) {
        LOG.i("New call is added. Registering call listener");
        call.registerCallListener(this);
        this.currentCall = this.populator.getCurrentCallInfo();
        CallStateListener callStateListener = this.currentListener;
        if (callStateListener != null) {
            callStateListener.onCallAdded(this.currentCall);
        }
    }

    public void onCallRemoved(@NonNull Call call) {
        LOG.i("Call is removed");
        call.unregisterCallListener(this);
        this.currentCall = null;
        this.currentListener = null;
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onEarlyMedia(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onError(Call call, ErrorCode errorCode, int i, String str) {
        CallStateListener callStateListener = this.currentListener;
        if (callStateListener != null) {
            callStateListener.onCallError(errorCode.getValue(), str);
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onHangup(Call call, HangupReason hangupReason) {
        CallStateListener callStateListener = this.currentListener;
        if (callStateListener != null) {
            callStateListener.onCallEnded(this.currentCall);
        }
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onMediaDTMFResponse(Call call, boolean z, String str) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onReconnect(Call call, boolean z, boolean z2, String str) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRemoteParticipantUpdated(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onRinging(Call call) {
    }

    @Override // com.amazon.comms.calling.service.CallListener
    public void onSignalingDTMFResponse(Call call, int i, String str) {
    }
}
