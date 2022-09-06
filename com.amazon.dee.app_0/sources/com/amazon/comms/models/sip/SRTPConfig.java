package com.amazon.comms.models.sip;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = SRTPConfigBuilder.class)
/* loaded from: classes11.dex */
public final class SRTPConfig {
    private final String keying;

    @JsonPOJOBuilder(withPrefix = "")
    /* loaded from: classes11.dex */
    public static class SRTPConfigBuilder {
        private String keying;

        SRTPConfigBuilder() {
        }

        public SRTPConfig build() {
            return new SRTPConfig(this.keying);
        }

        public SRTPConfigBuilder keying(String str) {
            this.keying = str;
            return this;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("SRTPConfig.SRTPConfigBuilder(keying="), this.keying, ")");
        }
    }

    SRTPConfig(String str) {
        this.keying = str;
    }

    public static SRTPConfigBuilder builder() {
        return new SRTPConfigBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SRTPConfig)) {
            return false;
        }
        String keying = getKeying();
        String keying2 = ((SRTPConfig) obj).getKeying();
        return keying != null ? keying.equals(keying2) : keying2 == null;
    }

    public String getKeying() {
        return this.keying;
    }

    public int hashCode() {
        String keying = getKeying();
        return 59 + (keying == null ? 43 : keying.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SRTPConfig(keying=");
        outline107.append(getKeying());
        outline107.append(")");
        return outline107.toString();
    }
}
