package com.amazon.alexa.sharing.api.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes10.dex */
public class PayloadAttachmentDomainSpecific implements PayloadAttachment {
    public static final String SERIALIZED_NAME_TYPE = "type";
    @SerializedName("type")
    private String type;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && PayloadAttachmentDomainSpecific.class == obj.getClass()) {
            return Objects.equals(this.type, ((PayloadAttachmentDomainSpecific) obj).type);
        }
        return false;
    }

    @Override // com.amazon.alexa.sharing.api.models.PayloadAttachment
    @Nullable
    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.type);
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline113("class PayloadAttachmentDomainSpecific {\n", "    type: "), toIndentedString(this.type), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public PayloadAttachmentDomainSpecific type(String str) {
        this.type = str;
        return this;
    }
}
