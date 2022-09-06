package com.amazon.client.metrics.thirdparty.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum BatchQueueType {
    VOLATILE("Volatile"),
    NON_VOLATILE("NonVolatile"),
    SEMI_VOLATILE("SemiVolatile");
    
    private final String mName;

    BatchQueueType(String str) {
        this.mName = str;
    }

    public static BatchQueueType fromString(String str) throws MetricsConfigurationException {
        BatchQueueType[] values;
        for (BatchQueueType batchQueueType : values()) {
            if (batchQueueType.getName().equalsIgnoreCase(str)) {
                return batchQueueType;
            }
        }
        throw new MetricsConfigurationException(GeneratedOutlineSupport1.outline72(str, " is not a valid BatchQueueType"));
    }

    public String getName() {
        return this.mName;
    }
}
