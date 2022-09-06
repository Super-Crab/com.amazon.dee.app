package com.amazonaws.services.s3.model;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public enum StorageClass {
    Standard(AlexaMetricsConstants.CardMode.STANDARD_MODE),
    ReducedRedundancy("REDUCED_REDUNDANCY"),
    Glacier("GLACIER"),
    StandardInfrequentAccess("STANDARD_IA"),
    OneZoneInfrequentAccess("ONEZONE_IA");
    
    private final String storageClassId;

    StorageClass(String str) {
        this.storageClassId = str;
    }

    public static StorageClass fromValue(String str) throws IllegalArgumentException {
        StorageClass[] values;
        for (StorageClass storageClass : values()) {
            if (storageClass.toString().equals(str)) {
                return storageClass;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.storageClassId;
    }
}
