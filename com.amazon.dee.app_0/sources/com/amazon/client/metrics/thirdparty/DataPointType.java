package com.amazon.client.metrics.thirdparty;

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
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("invalid datapoint ", i));
    }

    @Deprecated
    public static DataPointType getCounterType() {
        return CT;
    }

    @Deprecated
    public static DataPointType getDiscreteValueType() {
        return DV;
    }

    @Deprecated
    public static DataPointType getTimerType() {
        return TI;
    }
}
