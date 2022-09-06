package com.amazon.alexa.sharing.api.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public final class Payload {
    public static final String SERIALIZED_NAME_ATTACHMENTS = "attachments";
    public static final String SERIALIZED_NAME_TEXT = "text";
    public static final String SERIALIZED_NAME_TYPE = "type";
    @SerializedName(SERIALIZED_NAME_ATTACHMENTS)
    private List<PayloadAttachment> attachments;
    @SerializedName("text")
    private String text;
    @SerializedName("type")
    private TypeEnum type;

    /* loaded from: classes10.dex */
    public static class Builder {
        public static final String DETECTION_URL_REGEX = "((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?";
        private final List<PayloadAttachment> attachments = new ArrayList(1);
        private String type = "";
        private String textFallback = "";

        private boolean deepLinkDetectedInText(String str) {
            return str.contains("://");
        }

        private boolean urlDetectedInText(String str) {
            return Pattern.compile(DETECTION_URL_REGEX).matcher(str).find();
        }

        public Builder addAttachment(PayloadAttachment payloadAttachment) {
            this.attachments.add(payloadAttachment);
            return this;
        }

        public Payload build() {
            String str = this.textFallback;
            if (str != null && str.length() != 0) {
                if (this.attachments.size() == 0) {
                    this.type = textContainsUrl(this.textFallback) ? "url" : "text";
                } else if (this.attachments.size() == 1) {
                    this.type = this.attachments.get(0).getType();
                } else {
                    this.type = this.attachments.get(0).getType();
                }
                return new Payload(this.type, this.attachments, this.textFallback);
            }
            throw new IllegalArgumentException("Cannot build a payload without text.");
        }

        public Builder setTextFallback(String str) {
            this.textFallback = str;
            return this;
        }

        @VisibleForTesting
        boolean textContainsUrl(String str) {
            return urlDetectedInText(str) || deepLinkDetectedInText(str);
        }
    }

    @JsonAdapter(Adapter.class)
    /* loaded from: classes10.dex */
    public enum TypeEnum {
        URL("url"),
        EMOJI(PayloadAttachmentEmoji.TYPE),
        TEXT("text"),
        MULTI_PART("multi-part"),
        _DOMAIN_ATTACHMENT_("<domain-attachment>");
        
        private final String value;

        /* loaded from: classes10.dex */
        public static class Adapter extends TypeAdapter<TypeEnum> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public TypeEnum mo8353read(JsonReader jsonReader) throws IOException {
                return TypeEnum.fromValue(jsonReader.nextString());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, TypeEnum typeEnum) throws IOException {
                jsonWriter.value(typeEnum.getValue());
            }
        }

        TypeEnum(String str) {
            this.value = str;
        }

        public static TypeEnum fromValue(String str) {
            TypeEnum[] values;
            for (TypeEnum typeEnum : values()) {
                if (typeEnum.value.equals(str)) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unexpected value '", str, "'"));
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public Payload addAttachmentsItem(PayloadAttachment payloadAttachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList();
        }
        this.attachments.add(payloadAttachment);
        return this;
    }

    public Payload attachments(List<PayloadAttachment> list) {
        this.attachments = list;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Payload.class != obj.getClass()) {
            return false;
        }
        Payload payload = (Payload) obj;
        return Objects.equals(this.type, payload.type) && Objects.equals(this.text, payload.text) && Objects.equals(this.attachments, payload.attachments);
    }

    @Nullable
    public List<PayloadAttachment> getAttachments() {
        return this.attachments;
    }

    @NonNull
    public String getText() {
        return this.text;
    }

    @NonNull
    public TypeEnum getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.text, this.attachments);
    }

    public void setAttachments(List<PayloadAttachment> list) {
        this.attachments = list;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setType(TypeEnum typeEnum) {
        this.type = typeEnum;
    }

    public Payload text(String str) {
        this.text = str;
        return this;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class Payload {\n", "    type: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.type), "\n", "    text: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.text), "\n", "    attachments: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.attachments), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public Payload type(TypeEnum typeEnum) {
        this.type = typeEnum;
        return this;
    }

    private Payload(String str, List<PayloadAttachment> list, String str2) {
        this.attachments = null;
        this.attachments = list;
        this.text = str2;
        this.type = TypeEnum.fromValue(str);
    }
}
