package com.amazon.client.metrics.thirdparty.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum BatchTransmitterType {
    PERIODIC("Periodic"),
    NOS("Nos"),
    URGENT("Urgent"),
    SCHEDULED("Scheduled"),
    CRITICAL("Critical");
    
    private final String mName;

    BatchTransmitterType(String str) {
        this.mName = str;
    }

    public static BatchTransmitterType fromString(String str) throws MetricsConfigurationException {
        BatchTransmitterType[] values;
        for (BatchTransmitterType batchTransmitterType : values()) {
            if (batchTransmitterType.getName().equalsIgnoreCase(str)) {
                return batchTransmitterType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid BatchTransmitterType"));
    }

    public String getName() {
        return this.mName;
    }
}
