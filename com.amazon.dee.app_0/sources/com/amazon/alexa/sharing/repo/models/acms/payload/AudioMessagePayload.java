package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class AudioMessagePayload implements MessagePayload {
    public static final String ERROR_MISSING_PROPERTY_MEDIA_ID = "MISSING_PAYLOAD_PROPERTY_MEDIA_ID";
    public static final String TYPE = "message/audio";
    @SerializedName("mediaId")
    private String mediaId;
    @SerializedName("transcript")
    private String transcript;

    public AudioMessagePayload() {
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public String getTranscript() {
        return this.transcript;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "message/audio";
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setTranscript(String str) {
        this.transcript = str;
    }

    public AudioMessagePayload(String str, String str2) {
        this.mediaId = str;
        this.transcript = str2;
    }
}
