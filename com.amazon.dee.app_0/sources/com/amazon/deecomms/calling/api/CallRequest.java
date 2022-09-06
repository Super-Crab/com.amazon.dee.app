package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class CallRequest {
    @NonNull
    private final CallTarget callTarget;
    @Nullable
    private final Caller callerInfo;
    @NonNull
    private final ClientMetricsInfo clientMetricsInfo;
    @NonNull
    private final CallMediaStream[] supportedMediaStreams;

    public CallRequest(@NonNull @JsonProperty("callTarget") CallTarget callTarget, @NonNull @JsonProperty("supportedMediaStreams") CallMediaStream[] callMediaStreamArr, @NonNull @JsonProperty("clientMetricsInfo") ClientMetricsInfo clientMetricsInfo, @Nullable @JsonProperty("callerInfo") Caller caller) {
        this.callTarget = (CallTarget) Preconditions.checkNotNull(callTarget, "callTarget must not be null");
        this.supportedMediaStreams = (CallMediaStream[]) Preconditions.checkNotNull(callMediaStreamArr, "supportedMediaStreams must not be null");
        this.clientMetricsInfo = (ClientMetricsInfo) Preconditions.checkNotNull(clientMetricsInfo, "clientMetricsInfo must not be null");
        this.callerInfo = caller;
    }

    @NonNull
    public CallTarget getCallTarget() {
        return this.callTarget;
    }

    @Nullable
    public Caller getCallerInfo() {
        return this.callerInfo;
    }

    @NonNull
    public ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }

    @NonNull
    public CallMediaStream[] getSupportedMediaStreams() {
        return (CallMediaStream[]) this.supportedMediaStreams.clone();
    }
}
