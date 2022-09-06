package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import com.amazon.deecomms.R;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class CallEventPayload implements MessagePayload {
    public static final String TYPE = "event/call";
    @JsonProperty("dropIn")
    private boolean dropIn;
    @JsonProperty("duration")
    private long duration = -1;

    public long getDuration() {
        return this.duration;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        return context.getString(R.string.call_completed);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
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
