package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.api.enums.CallDirection;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.amazon.deecomms.calling.api.enums.CallState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class CallInfo {
    @NonNull
    private final CallDirection callDirection;
    @NonNull
    private final String callID;
    @NonNull
    private final CallState callState;
    @NonNull
    private final CallTarget callTarget;
    @NonNull
    private final Caller callerInfo;
    @NonNull
    private final CallMediaStream[] supportedMediaStreams;

    public CallInfo(@NonNull @JsonProperty("callID") String str, @NonNull @JsonProperty("callerInfo") Caller caller, @NonNull @JsonProperty("callTarget") CallTarget callTarget, @NonNull @JsonProperty("callState") CallState callState, @NonNull @JsonProperty("callDirection") CallDirection callDirection, @NonNull @JsonProperty("supportedMediaStreams") CallMediaStream[] callMediaStreamArr) {
        this.callID = (String) Preconditions.checkNotNull(str, "callID must not be null");
        this.callerInfo = (Caller) Preconditions.checkNotNull(caller, "callerInfo must not be null");
        this.callTarget = (CallTarget) Preconditions.checkNotNull(callTarget, "callTarget must not be null");
        this.callState = (CallState) Preconditions.checkNotNull(callState, "callState must not be null");
        this.callDirection = (CallDirection) Preconditions.checkNotNull(callDirection, "callDirection must not be null");
        this.supportedMediaStreams = (CallMediaStream[]) Preconditions.checkNotNull(callMediaStreamArr, "supportedMediaStreams must not be null");
    }

    @NonNull
    public CallDirection getCallDirection() {
        return this.callDirection;
    }

    @NonNull
    public String getCallID() {
        return this.callID;
    }

    @NonNull
    public CallState getCallState() {
        return this.callState;
    }

    @NonNull
    public CallTarget getCallTarget() {
        return this.callTarget;
    }

    @NonNull
    public Caller getCallerInfo() {
        return this.callerInfo;
    }

    @NonNull
    public CallMediaStream[] getSupportedMediaStreams() {
        return (CallMediaStream[]) this.supportedMediaStreams.clone();
    }
}
