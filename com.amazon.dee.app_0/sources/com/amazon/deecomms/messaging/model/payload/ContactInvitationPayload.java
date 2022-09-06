package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.deecomms.notifications.util.TextMessageTranslator;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class ContactInvitationPayload implements MessagePayload {
    public static final String TYPE = "message/contact-invitation";
    @JsonProperty("invitationText")
    private String invitationText;
    @JsonProperty("templateId")
    private String templateId;

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        String text = getText();
        return (!text.isEmpty() || TextUtils.isEmpty(this.templateId)) ? text : new TextMessageTranslator(context).translate(this.templateId, getText());
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public String getText() {
        return this.invitationText;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "message/contact-invitation";
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public void setText(String str) {
        this.invitationText = str;
    }
}
