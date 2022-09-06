package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public class ContactConnectionSuccessPayload implements MessagePayload {
    public static final String TYPE = "message/contact-connection-success";

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        return context.getString(R.string.contact_connection_success);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "message/contact-connection-success";
    }
}
