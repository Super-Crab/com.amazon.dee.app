package com.amazon.alexa.header;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import lombok.Generated;
/* loaded from: classes8.dex */
public class Header<NamespaceType, NameType, InstanceType> {
    @CheckForNull
    private final String correlationToken;
    @CheckForNull
    private final InstanceType instance;
    @CheckForNull
    private final UUID messageId;
    @CheckForNull
    private final NameType name;
    @CheckForNull
    private final NamespaceType namespace;
    @CheckForNull
    private final String payloadVersion;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes8.dex */
    public static class HeaderBuilder<NamespaceType, NameType, InstanceType> {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String correlationToken;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private InstanceType instance;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private UUID messageId;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private NameType name;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private NamespaceType namespace;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String payloadVersion;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        HeaderBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Header<NamespaceType, NameType, InstanceType> build() {
            return new Header<>(this.namespace, this.instance, this.name, this.payloadVersion, this.messageId, this.correlationToken);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> correlationToken(String str) {
            this.correlationToken = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> instance(InstanceType instancetype) {
            this.instance = instancetype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> messageId(UUID uuid) {
            this.messageId = uuid;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> name(NameType nametype) {
            this.name = nametype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> namespace(NamespaceType namespacetype) {
            this.namespace = namespacetype;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public HeaderBuilder<NamespaceType, NameType, InstanceType> payloadVersion(String str) {
            this.payloadVersion = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Header.HeaderBuilder(namespace=");
            outline107.append(this.namespace);
            outline107.append(", instance=");
            outline107.append(this.instance);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", payloadVersion=");
            outline107.append(this.payloadVersion);
            outline107.append(", messageId=");
            outline107.append(this.messageId);
            outline107.append(", correlationToken=");
            return GeneratedOutlineSupport1.outline91(outline107, this.correlationToken, ")");
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected Header(NamespaceType namespacetype, InstanceType instancetype, NameType nametype, String str, UUID uuid, String str2) {
        this.namespace = namespacetype;
        this.instance = instancetype;
        this.name = nametype;
        this.payloadVersion = str;
        this.messageId = uuid;
        this.correlationToken = str2;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static <NamespaceType, NameType, InstanceType> HeaderBuilder<NamespaceType, NameType, InstanceType> builder() {
        return new HeaderBuilder<>();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    protected boolean canEqual(Object obj) {
        return obj instanceof Header;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        if (!header.canEqual(this)) {
            return false;
        }
        NamespaceType namespace = getNamespace();
        Object namespace2 = header.getNamespace();
        if (namespace != null ? !namespace.equals(namespace2) : namespace2 != null) {
            return false;
        }
        InstanceType header2 = getInstance();
        Object header3 = header.getInstance();
        if (header2 != null ? !header2.equals(header3) : header3 != null) {
            return false;
        }
        NameType name = getName();
        Object name2 = header.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String payloadVersion = getPayloadVersion();
        String payloadVersion2 = header.getPayloadVersion();
        if (payloadVersion != null ? !payloadVersion.equals(payloadVersion2) : payloadVersion2 != null) {
            return false;
        }
        UUID messageId = getMessageId();
        UUID messageId2 = header.getMessageId();
        if (messageId != null ? !messageId.equals(messageId2) : messageId2 != null) {
            return false;
        }
        String correlationToken = getCorrelationToken();
        String correlationToken2 = header.getCorrelationToken();
        return correlationToken != null ? correlationToken.equals(correlationToken2) : correlationToken2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getCorrelationToken() {
        return this.correlationToken;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public InstanceType getInstance() {
        return this.instance;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public UUID getMessageId() {
        return this.messageId;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public NameType getName() {
        return this.name;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public NamespaceType getNamespace() {
        return this.namespace;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getPayloadVersion() {
        return this.payloadVersion;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        NamespaceType namespace = getNamespace();
        int i = 43;
        int hashCode = namespace == null ? 43 : namespace.hashCode();
        InstanceType header = getInstance();
        int hashCode2 = ((hashCode + 59) * 59) + (header == null ? 43 : header.hashCode());
        NameType name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        String payloadVersion = getPayloadVersion();
        int hashCode4 = (hashCode3 * 59) + (payloadVersion == null ? 43 : payloadVersion.hashCode());
        UUID messageId = getMessageId();
        int hashCode5 = (hashCode4 * 59) + (messageId == null ? 43 : messageId.hashCode());
        String correlationToken = getCorrelationToken();
        int i2 = hashCode5 * 59;
        if (correlationToken != null) {
            i = correlationToken.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Header(namespace=");
        outline107.append(getNamespace());
        outline107.append(", instance=");
        outline107.append(getInstance());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", payloadVersion=");
        outline107.append(getPayloadVersion());
        outline107.append(", messageId=");
        outline107.append(getMessageId());
        outline107.append(", correlationToken=");
        outline107.append(getCorrelationToken());
        outline107.append(")");
        return outline107.toString();
    }
}
