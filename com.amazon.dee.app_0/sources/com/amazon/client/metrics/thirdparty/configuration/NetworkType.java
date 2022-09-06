package com.amazon.client.metrics.thirdparty.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum NetworkType {
    WIFI("Wifi"),
    ETHERNET("Ethernet"),
    WAN("Wan");
    
    private final String mName;

    NetworkType(String str) {
        this.mName = str;
    }

    public static NetworkType fromString(String str) throws MetricsConfigurationException {
        NetworkType[] values;
        for (NetworkType networkType : values()) {
            if (networkType.getName().equalsIgnoreCase(str)) {
                return networkType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid NetworkType"));
    }

    public String getName() {
        return this.mName;
    }
}
