package com.amazon.client.metrics.thirdparty.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum CodecType {
    PROTOCOL_BUFFERS("ProtocolBuffers"),
    STRING("String");
    
    private final String mName;

    CodecType(String str) {
        this.mName = str;
    }

    public static CodecType fromString(String str) throws MetricsConfigurationException {
        CodecType[] values;
        for (CodecType codecType : values()) {
            if (codecType.getName().equalsIgnoreCase(str)) {
                return codecType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid Codec"));
    }

    public String getName() {
        return this.mName;
    }
}
