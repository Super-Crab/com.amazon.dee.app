package com.amazon.client.metrics.thirdparty;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum Priority {
    NORMAL,
    HIGH,
    RESERVED_FOR_LOCATION_SERVICE,
    RESERVED_FOR_NON_ANONYMOUS_METRICS,
    CRITICAL;

    public static Priority fromInt(int i) {
        if (i >= 0 && i <= values().length) {
            return values()[i];
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("invalid priority ", i));
    }
}
