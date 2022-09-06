package com.amazon.client.metrics.common.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum TransportType {
    HTTP("Http"),
    OUTPUT_STREAM("OutputStream");
    
    private final String mName;

    TransportType(String str) {
        this.mName = str;
    }

    public static TransportType fromString(String str) throws MetricsConfigurationException {
        TransportType[] values;
        for (TransportType transportType : values()) {
            if (transportType.getName().equalsIgnoreCase(str)) {
                return transportType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid TransportType"));
    }

    public String getName() {
        return this.mName;
    }
}
