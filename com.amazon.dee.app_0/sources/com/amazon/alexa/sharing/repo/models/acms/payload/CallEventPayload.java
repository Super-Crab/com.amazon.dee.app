package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class CallEventPayload implements MessagePayload {
    public static final String TYPE = "event/call";
    @SerializedName("dropIn")
    private boolean dropIn;
    @SerializedName("duration")
    private long duration = -1;

    public long getDuration() {
        return this.duration;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "event/call";
    }

    public boolean isDropIn() {
        return this.dropIn;
    }

    public void setDropIn(boolean z) {
        this.dropIn = z;
    }

    public void setDuration(long j) {
        this.duration = j;
    }
}
