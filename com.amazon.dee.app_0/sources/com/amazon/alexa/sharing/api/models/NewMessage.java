package com.amazon.alexa.sharing.api.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes10.dex */
public class NewMessage {
    public static final String SERIALIZED_NAME_CLIENT_TOKEN = "clientToken";
    public static final String SERIALIZED_NAME_MESSAGE_PAYLOAD = "messagePayload";
    public static final String SERIALIZED_NAME_RECIPIENTS = "recipients";
    @SerializedName(SERIALIZED_NAME_CLIENT_TOKEN)
    private String clientToken;
    @SerializedName("messagePayload")
    private Payload messagePayload;
    @SerializedName("recipients")
    private Recipients recipients;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public NewMessage clientToken(String str) {
        this.clientToken = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NewMessage.class != obj.getClass()) {
            return false;
        }
        NewMessage newMessage = (NewMessage) obj;
        Recipients recipients = this.recipients;
        if (recipients != null ? recipients.equals(newMessage.recipients) : newMessage.recipients == null) {
            if (Objects.equals(this.messagePayload, newMessage.messagePayload) && Objects.equals(this.clientToken, newMessage.clientToken)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public String getClientToken() {
        return this.clientToken;
    }

    @NonNull
    public Payload getMessagePayload() {
        return this.messagePayload;
    }

    @NonNull
    public Recipients getRecipients() {
        return this.recipients;
    }

    public int hashCode() {
        return Objects.hash(this.recipients, this.messagePayload, this.clientToken);
    }

    public NewMessage messagePayload(Payload payload) {
        this.messagePayload = payload;
        return this;
    }

    public void setClientToken(String str) {
        this.clientToken = str;
    }

    public void setMessagePayload(Payload payload) {
        this.messagePayload = payload;
    }

    public void setRecipients(Recipients recipients) {
        this.recipients = recipients;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class NewMessage {\n", "    recipients: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.recipients), "\n", "    messagePayload: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.messagePayload), "\n", "    clientToken: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.clientToken), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
