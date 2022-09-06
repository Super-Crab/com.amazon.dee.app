package com.amazon.alexa.sharing.api.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes10.dex */
public class PayloadAttachmentUrl implements PayloadAttachment {
    public static final String SERIALIZED_NAME_DESCRIPTION = "description";
    public static final String SERIALIZED_NAME_ORIGIN = "origin";
    public static final String SERIALIZED_NAME_THUMBNAIL_URL = "thumbnailUrl";
    public static final String SERIALIZED_NAME_TITLE = "title";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static final String SERIALIZED_NAME_VALUE = "value";
    public static final String TYPE = "url";
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("value")
    private String value;
    @SerializedName("type")
    private String type = "url";
    @SerializedName(SERIALIZED_NAME_THUMBNAIL_URL)
    private String thumbnailUrl = "";
    @SerializedName("origin")
    private String origin = "";

    public PayloadAttachmentUrl(String str, String str2, String str3) {
        this.value = str;
        this.title = str2;
        this.description = str3;
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PayloadAttachmentUrl.class != obj.getClass()) {
            return false;
        }
        PayloadAttachmentUrl payloadAttachmentUrl = (PayloadAttachmentUrl) obj;
        return Objects.equals(this.type, payloadAttachmentUrl.type) && Objects.equals(this.thumbnailUrl, payloadAttachmentUrl.thumbnailUrl) && Objects.equals(this.value, payloadAttachmentUrl.value) && Objects.equals(this.title, payloadAttachmentUrl.title) && Objects.equals(this.origin, payloadAttachmentUrl.origin) && Objects.equals(this.description, payloadAttachmentUrl.description);
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public String getOrigin() {
        return this.origin;
    }

    @Nullable
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Nullable
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.alexa.sharing.api.models.PayloadAttachment
    public String getType() {
        return "url";
    }

    @Nullable
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.thumbnailUrl, this.value, this.title, this.origin, this.description);
    }

    public void setDescription(@Nullable String str) {
        this.description = str;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public void setThumbnailUrl(String str) {
        this.thumbnailUrl = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class PayloadAttachmentUrl {\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    thumbnailUrl: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.thumbnailUrl), "\n", "    value: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.value), "\n", "    title: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.title), "\n", "    origin: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.origin), "\n", "    description: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.description), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
