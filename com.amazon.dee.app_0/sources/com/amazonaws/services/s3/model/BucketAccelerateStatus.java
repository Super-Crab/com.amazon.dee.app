package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public enum BucketAccelerateStatus {
    Enabled("Enabled"),
    Suspended(BucketVersioningConfiguration.SUSPENDED);
    
    private final String accelerateStatus;

    BucketAccelerateStatus(String str) {
        this.accelerateStatus = str;
    }

    public static BucketAccelerateStatus fromValue(String str) throws IllegalArgumentException {
        BucketAccelerateStatus[] values;
        for (BucketAccelerateStatus bucketAccelerateStatus : values()) {
            if (bucketAccelerateStatus.toString().equals(str)) {
                return bucketAccelerateStatus;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.accelerateStatus;
    }
}
