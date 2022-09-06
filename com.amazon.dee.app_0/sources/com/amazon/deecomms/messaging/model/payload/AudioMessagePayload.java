package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.deecomms.R;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class AudioMessagePayload implements MessagePayload {
    public static final String ERROR_MISSING_PROPERTY_MEDIA_ID = "MISSING_PAYLOAD_PROPERTY_MEDIA_ID";
    public static final String TYPE = "message/audio";
    @JsonProperty("mediaId")
    private String mediaId;
    @JsonProperty("transcript")
    private String transcript;

    public AudioMessagePayload() {
    }

    public String getMediaId() {
        return this.mediaId;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        String transcript = getTranscript();
        if (TextUtils.isEmpty(transcript)) {
            return new String(Character.toChars(127908)) + context.getString(R.string.voice_message);
        }
        return new String(Character.toChars(127908)) + context.getString(R.string.notification_text_audio_message, transcript);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        return getTranscript();
    }

    public String getTranscript() {
        return this.transcript;
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
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
