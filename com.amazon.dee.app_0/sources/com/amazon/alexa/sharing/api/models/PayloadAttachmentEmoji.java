package com.amazon.alexa.sharing.api.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes10.dex */
public class PayloadAttachmentEmoji implements PayloadAttachment {
    public static final String SERIALIZED_NAME_TEXT = "text";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static final String TYPE = "emoji";
    @SerializedName("text")
    private String text;
    @SerializedName("type")
    private String type = TYPE;

    public PayloadAttachmentEmoji(String str) {
        this.text = str;
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PayloadAttachmentEmoji.class != obj.getClass()) {
            return false;
        }
        PayloadAttachmentEmoji payloadAttachmentEmoji = (PayloadAttachmentEmoji) obj;
        return Objects.equals(this.type, payloadAttachmentEmoji.type) && Objects.equals(this.text, payloadAttachmentEmoji.text);
    }

    @Nullable
    public String getText() {
        return this.text;
    }

    @Override // com.amazon.alexa.sharing.api.models.PayloadAttachment
    public String getType() {
        return TYPE;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.text);
    }

    public void setText(String str) {
        this.text = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class PayloadAttachmentEmoji {\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    text: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.text), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
