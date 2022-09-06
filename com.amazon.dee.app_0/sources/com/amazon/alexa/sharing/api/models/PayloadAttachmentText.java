package com.amazon.alexa.sharing.api.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes10.dex */
public class PayloadAttachmentText implements PayloadAttachment {
    public static final String SERIALIZED_NAME_TEXT = "text";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static final String TYPE = "text";
    @SerializedName("text")
    private String text;
    @SerializedName("type")
    private String type = "text";

    public PayloadAttachmentText(String str) {
        this.text = str;
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PayloadAttachmentText.class == obj.getClass()) {
            return Objects.equals(this.text, ((PayloadAttachmentText) obj).text);
        }
        return false;
    }

    @Nullable
    public String getText() {
        return this.text;
    }

    @Override // com.amazon.alexa.sharing.api.models.PayloadAttachment
    public String getType() {
        return "text";
    }

    public int hashCode() {
        return Objects.hash(this.text);
    }

    public void setText(String str) {
        this.text = str;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class PayloadAttachmentText {\n", "    text: "), toIndentedString(this.text), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
