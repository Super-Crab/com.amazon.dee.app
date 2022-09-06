package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class MissedCallEventPayload implements MessagePayload {
    public static final String TYPE = "event/missed-call";
    @SerializedName("dropIn")
    private boolean dropIn;

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "event/missed-call";
    }

    public boolean isDropIn() {
        return this.dropIn;
    }

    public void setDropIn(boolean z) {
        this.dropIn = z;
    }
}
