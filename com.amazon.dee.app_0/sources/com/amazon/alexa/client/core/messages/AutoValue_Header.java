package com.amazon.alexa.client.core.messages;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import java.util.Map;
/* loaded from: classes6.dex */
final class AutoValue_Header extends Header {
    private final Map<String, JsonElement> additionalFields;
    private final CorrelationToken correlationToken;
    private final DialogRequestIdentifier dialogRequestIdentifier;
    private final MessageIdentifier messageIdentifier;
    private final Name name;
    private final Namespace namespace;
    private final PayloadVersion payloadVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class Builder extends Header.Builder {
        private Map<String, JsonElement> additionalFields;
        private CorrelationToken correlationToken;
        private DialogRequestIdentifier dialogRequestIdentifier;
        private MessageIdentifier messageIdentifier;
        private Name name;
        private Namespace namespace;
        private PayloadVersion payloadVersion;

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header build() {
            String str = "";
            if (this.namespace == null) {
                str = GeneratedOutlineSupport1.outline72(str, " namespace");
            }
            if (this.name == null) {
                str = GeneratedOutlineSupport1.outline72(str, " name");
            }
            if (this.messageIdentifier == null) {
                str = GeneratedOutlineSupport1.outline72(str, " messageIdentifier");
            }
            if (str.isEmpty()) {
                return new AutoValue_Header(this.namespace, this.name, this.messageIdentifier, this.correlationToken, this.payloadVersion, this.dialogRequestIdentifier, this.additionalFields);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setAdditionalFields(@Nullable Map<String, JsonElement> map) {
            this.additionalFields = map;
            return this;
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setCorrelationToken(@Nullable CorrelationToken correlationToken) {
            this.correlationToken = correlationToken;
            return this;
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setDialogRequestIdentifier(@Nullable DialogRequestIdentifier dialogRequestIdentifier) {
            this.dialogRequestIdentifier = dialogRequestIdentifier;
            return this;
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setMessageIdentifier(MessageIdentifier messageIdentifier) {
            if (messageIdentifier != null) {
                this.messageIdentifier = messageIdentifier;
                return this;
            }
            throw new NullPointerException("Null messageIdentifier");
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setName(Name name) {
            if (name != null) {
                this.name = name;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setNamespace(Namespace namespace) {
            if (namespace != null) {
                this.namespace = namespace;
                return this;
            }
            throw new NullPointerException("Null namespace");
        }

        @Override // com.amazon.alexa.client.core.messages.Header.Builder
        public Header.Builder setPayloadVersion(@Nullable PayloadVersion payloadVersion) {
            this.payloadVersion = payloadVersion;
            return this;
        }
    }

    public boolean equals(Object obj) {
        CorrelationToken correlationToken;
        PayloadVersion payloadVersion;
        DialogRequestIdentifier dialogRequestIdentifier;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (this.namespace.equals(header.getNamespace()) && this.name.equals(header.getName()) && this.messageIdentifier.equals(header.getMessageIdentifier()) && ((correlationToken = this.correlationToken) != null ? correlationToken.equals(header.getCorrelationToken()) : header.getCorrelationToken() == null) && ((payloadVersion = this.payloadVersion) != null ? payloadVersion.equals(header.getPayloadVersion()) : header.getPayloadVersion() == null) && ((dialogRequestIdentifier = this.dialogRequestIdentifier) != null ? dialogRequestIdentifier.equals(header.getDialogRequestIdentifier()) : header.getDialogRequestIdentifier() == null)) {
            Map<String, JsonElement> map = this.additionalFields;
            if (map == null) {
                if (header.getAdditionalFields() == null) {
                    return true;
                }
            } else if (map.equals(header.getAdditionalFields())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    @Nullable
    public Map<String, JsonElement> getAdditionalFields() {
        return this.additionalFields;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    @Nullable
    public CorrelationToken getCorrelationToken() {
        return this.correlationToken;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    @Nullable
    public DialogRequestIdentifier getDialogRequestIdentifier() {
        return this.dialogRequestIdentifier;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    public MessageIdentifier getMessageIdentifier() {
        return this.messageIdentifier;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    public Name getName() {
        return this.name;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    public Namespace getNamespace() {
        return this.namespace;
    }

    @Override // com.amazon.alexa.client.core.messages.Header
    @Nullable
    public PayloadVersion getPayloadVersion() {
        return this.payloadVersion;
    }

    public int hashCode() {
        int hashCode = (((((this.namespace.hashCode() ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.messageIdentifier.hashCode()) * 1000003;
        CorrelationToken correlationToken = this.correlationToken;
        int i = 0;
        int hashCode2 = (hashCode ^ (correlationToken == null ? 0 : correlationToken.hashCode())) * 1000003;
        PayloadVersion payloadVersion = this.payloadVersion;
        int hashCode3 = (hashCode2 ^ (payloadVersion == null ? 0 : payloadVersion.hashCode())) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.dialogRequestIdentifier;
        int hashCode4 = (hashCode3 ^ (dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode())) * 1000003;
        Map<String, JsonElement> map = this.additionalFields;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode4 ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Header{namespace=");
        outline107.append(this.namespace);
        outline107.append(", name=");
        outline107.append(this.name);
        outline107.append(", messageIdentifier=");
        outline107.append(this.messageIdentifier);
        outline107.append(", correlationToken=");
        outline107.append(this.correlationToken);
        outline107.append(", payloadVersion=");
        outline107.append(this.payloadVersion);
        outline107.append(", dialogRequestIdentifier=");
        outline107.append(this.dialogRequestIdentifier);
        outline107.append(", additionalFields=");
        outline107.append(this.additionalFields);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_Header(Namespace namespace, Name name, MessageIdentifier messageIdentifier, @Nullable CorrelationToken correlationToken, @Nullable PayloadVersion payloadVersion, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable Map<String, JsonElement> map) {
        this.namespace = namespace;
        this.name = name;
        this.messageIdentifier = messageIdentifier;
        this.correlationToken = correlationToken;
        this.payloadVersion = payloadVersion;
        this.dialogRequestIdentifier = dialogRequestIdentifier;
        this.additionalFields = map;
    }
}
