package com.amazon.alexa.mode.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public final class LogTag {
    private static final String TAG_PREFIX = "Mode.";

    private LogTag() {
    }

    public static String forClass(Class<?> cls) {
        return GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107(TAG_PREFIX));
    }
}
