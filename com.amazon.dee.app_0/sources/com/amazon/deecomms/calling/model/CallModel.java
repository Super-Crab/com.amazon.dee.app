package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class CallModel {
    private String callStatus;
    private String remoteParticipantName;

    public String getCallStatus() {
        return this.callStatus;
    }

    public String getRemoteParticipantName() {
        return this.remoteParticipantName;
    }

    public void setCallStatus(@NonNull String str) {
        this.callStatus = str;
    }

    public void setRemoteParticipantName(@NonNull String str) {
        this.remoteParticipantName = str;
    }
}
