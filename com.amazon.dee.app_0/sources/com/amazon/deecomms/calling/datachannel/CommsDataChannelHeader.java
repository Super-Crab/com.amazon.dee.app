package com.amazon.deecomms.calling.datachannel;
/* loaded from: classes12.dex */
public final class CommsDataChannelHeader {
    private Capability capability;
    private String instance;
    private String name;
    private Namespace namespace;
    private PayloadVersion payloadVersion;
    private Long timestamp;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private Capability capability;
        private String instance;
        private String name;
        private Namespace namespace;
        private PayloadVersion payloadVersion = PayloadVersion.LATEST;
        private Long timestamp = Long.valueOf(System.currentTimeMillis());

        public CommsDataChannelHeader build() {
            return new CommsDataChannelHeader(this.namespace, this.instance, this.capability, this.name, this.payloadVersion, this.timestamp);
        }

        public Builder withCapability(Capability capability) {
            this.capability = capability;
            return this;
        }

        public Builder withInstance(String str) {
            this.instance = str;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withNamespace(Namespace namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder withPayloadVersion(PayloadVersion payloadVersion) {
            this.payloadVersion = payloadVersion;
            return this;
        }

        public Builder withTimeStamp(Long l) {
            this.timestamp = l;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Capability getCapability() {
        return this.capability;
    }

    public String getInstance() {
        return this.instance;
    }

    public String getName() {
        return this.name;
    }

    public Namespace getNamespace() {
        return this.namespace;
    }

    public PayloadVersion getPayloadVersion() {
        return this.payloadVersion;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    private CommsDataChannelHeader(Namespace namespace, String str, Capability capability, String str2, PayloadVersion payloadVersion, Long l) {
        this.namespace = namespace;
        this.instance = str;
        this.capability = capability;
        this.name = str2;
        this.payloadVersion = payloadVersion;
        this.timestamp = l;
    }
}
