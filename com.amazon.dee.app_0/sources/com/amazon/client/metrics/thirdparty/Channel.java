package com.amazon.client.metrics.thirdparty;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public enum Channel {
    LOCATION,
    ANONYMOUS,
    NON_ANONYMOUS;

    public static Channel fromInt(int i) {
        if (i >= 0 && i <= Priority.values().length) {
            return values()[i];
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("invalid priority ", i));
    }
}
