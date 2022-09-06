package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class TextMessagePayload implements MessagePayload {
    public static final String ERROR_MISSING_PROPERTY_TEXT = "MISSING_PAYLOAD_PROPERTY_TEXT";
    public static final String TYPE = "message/text";
    @SerializedName("templateId")
    private String templateId;
    @SerializedName("text")
    private String text;

    public TextMessagePayload(String str, String str2) {
        this.text = str;
        this.templateId = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TextMessagePayload.class != obj.getClass()) {
            return false;
        }
        TextMessagePayload textMessagePayload = (TextMessagePayload) obj;
        return Objects.equals(this.text, textMessagePayload.text) && Objects.equals(this.templateId, textMessagePayload.templateId);
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public String getText() {
        return this.text;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "message/text";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.text, this.templateId);
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TextMessagePayload {text='");
        GeneratedOutlineSupport1.outline176(outline107, this.text, Chars.QUOTE, ", templateId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.templateId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
