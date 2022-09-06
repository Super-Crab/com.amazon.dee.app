package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.deecomms.notifications.util.TextMessageTranslator;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class TextMessagePayload implements MessagePayload {
    public static final String ERROR_MISSING_PROPERTY_TEXT = "MISSING_PAYLOAD_PROPERTY_TEXT";
    public static final String TYPE = "message/text";
    @JsonProperty("templateId")
    private String templateId;
    @JsonProperty("text")
    private String text;

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        if (!TextUtils.isEmpty(this.templateId)) {
            return new TextMessageTranslator(context).translate(this.templateId, getText());
        }
        return getText();
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public String getText() {
        return this.text;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "message/text";
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
