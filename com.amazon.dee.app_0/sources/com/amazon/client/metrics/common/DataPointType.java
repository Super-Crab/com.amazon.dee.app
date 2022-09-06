package com.amazon.client.metrics.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum DataPointType {
    CT,
    TI,
    DV,
    CK;

    public static DataPointType fromInt(int i) {
        if (i >= 0 && i <= values().length) {
            return values()[i];
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Invalid DataPointType: ", i));
    }
}
