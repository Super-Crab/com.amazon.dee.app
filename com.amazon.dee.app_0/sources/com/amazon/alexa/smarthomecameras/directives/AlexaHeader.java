package com.amazon.alexa.smarthomecameras.directives;

import android.text.TextUtils;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class AlexaHeader {
    @Nullable
    private final String correlationToken;
    @Nullable
    private final String instance;
    private final String messageId;
    private final String name;
    private final String namespace;
    private final String payloadVersion;

    /* loaded from: classes10.dex */
    public static class Builder {
        @Nullable
        private String correlationToken;
        @Nullable
        private String instance;
        private String messageId;
        private String name;
        private String namespace;
        private String payloadVersion;

        public AlexaHeader build() {
            return new AlexaHeader(this);
        }

        public Builder setCorrelationToken(@Nullable String str) {
            this.correlationToken = str;
            return this;
        }

        public Builder setInstance(@Nullable String str) {
            this.instance = str;
            return this;
        }

        public Builder setMessageId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.messageId = str;
            }
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setNamespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder setPayloadVersion(String str) {
            this.payloadVersion = str;
            return this;
        }

        private Builder() {
            this.messageId = AlexaHeader.generateUuid();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlexaHeader)) {
            return false;
        }
        AlexaHeader alexaHeader = (AlexaHeader) obj;
        return Objects.equals(this.namespace, alexaHeader.namespace) && Objects.equals(this.name, alexaHeader.name) && Objects.equals(this.messageId, alexaHeader.messageId) && Objects.equals(this.payloadVersion, alexaHeader.payloadVersion) && Objects.equals(this.instance, alexaHeader.instance) && Objects.equals(this.correlationToken, alexaHeader.correlationToken);
    }

    @Nullable
    public String getCorrelationToken() {
        return this.correlationToken;
    }

    @Nullable
    public String getInstance() {
        return this.instance;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getPayloadVersion() {
        return this.payloadVersion;
    }

    private AlexaHeader(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6) {
        Preconditions.isFalse(TextUtils.isEmpty(str), "Namespace cannot benull or empty");
        Preconditions.isFalse(TextUtils.isEmpty(str2), "Name cannot be null or empty");
        Preconditions.isFalse(TextUtils.isEmpty(str4), "PayloadVersion was null or empty");
        this.namespace = str;
        this.name = str2;
        this.messageId = str3;
        this.instance = str5;
        this.correlationToken = str6;
        this.payloadVersion = str4;
    }

    private AlexaHeader(Builder builder) {
        this(builder.namespace, builder.name, builder.messageId, builder.payloadVersion, builder.instance, builder.correlationToken);
    }
}
