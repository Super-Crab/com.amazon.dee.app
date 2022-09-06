package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class ContactInvitationPayload implements MessagePayload {
    public static final String TYPE = "message/contact-invitation";
    @SerializedName("invitationText")
    private String invitationText;
    @SerializedName("templateId")
    private String templateId;

    public String getTemplateId() {
        return this.templateId;
    }

    public String getText() {
        return this.invitationText;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
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
