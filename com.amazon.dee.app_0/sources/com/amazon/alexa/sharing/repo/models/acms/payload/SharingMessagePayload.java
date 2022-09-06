package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEventPayload;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class SharingMessagePayload implements MessagePayload {
    public static final String TYPE = "message/shared-content";
    @SerializedName("sharingMessage")
    private GenericSharingMessageEventPayload sharingMessage;

    public SharingMessagePayload() {
        this(null);
    }

    public GenericSharingMessageEventPayload getSharingMessage() {
        return this.sharingMessage;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "message/shared-content";
    }

    public void setSharingMessage(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
    }

    public SharingMessagePayload(GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
    }
}
