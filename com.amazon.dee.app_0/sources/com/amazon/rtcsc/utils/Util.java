package com.amazon.rtcsc.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class Util {
    public static Object nullCheck(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " cannot be null"));
    }
}
