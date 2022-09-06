package com.amazon.deecomms.calling.api;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.api.enums.CallDirection;
import com.amazon.deecomms.calling.api.enums.CallMediaStream;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public final class HistoricalCall {
    @NonNull
    private final CallDirection callDirection;
    @NonNull
    private final long callStartTime;
    @NonNull
    private final CallTarget callTarget;
    @NonNull
    private final String displayName;
    @NonNull
    private final CallMediaStream[] supportedMediaStreams;

    public HistoricalCall(@NonNull CallTarget callTarget, @NonNull CallMediaStream[] callMediaStreamArr, long j, @NonNull String str, @NonNull CallDirection callDirection) {
        this.callTarget = (CallTarget) Preconditions.checkNotNull(callTarget, "callTarget must not be null");
        this.supportedMediaStreams = (CallMediaStream[]) Preconditions.checkNotNull(callMediaStreamArr, "supportedMediaStreams must not be null");
        this.callStartTime = j;
        this.displayName = (String) Preconditions.checkNotNull(str, "Display Name must not be null");
        this.callDirection = (CallDirection) Preconditions.checkNotNull(callDirection, "Call direction must not be null");
    }

    @NonNull
    public CallDirection getCallDirection() {
        return this.callDirection;
    }

    public long getCallStartTime() {
        return this.callStartTime;
    }

    @NonNull
    public CallTarget getCallTarget() {
        return this.callTarget;
    }

    @NonNull
    public String getDisplayName() {
        return this.displayName;
    }

    @NonNull
    public CallMediaStream[] getSupportedMediaStreams() {
        return (CallMediaStream[]) this.supportedMediaStreams.clone();
    }
}
