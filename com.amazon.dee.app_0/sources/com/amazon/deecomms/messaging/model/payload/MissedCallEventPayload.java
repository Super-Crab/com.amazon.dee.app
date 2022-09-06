package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import com.amazon.deecomms.R;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class MissedCallEventPayload implements MessagePayload {
    public static final String TYPE = "event/missed-call";
    @JsonProperty("dropIn")
    private boolean dropIn;

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        return context.getString(R.string.missed_call);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
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
