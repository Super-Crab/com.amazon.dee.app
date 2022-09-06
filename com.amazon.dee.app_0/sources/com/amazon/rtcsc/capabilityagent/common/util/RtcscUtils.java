package com.amazon.rtcsc.capabilityagent.common.util;

import androidx.annotation.VisibleForTesting;
/* loaded from: classes13.dex */
public final class RtcscUtils {
    private RtcscUtils() {
    }

    @VisibleForTesting
    public static String stripJsonContents(String str) {
        return str.replaceAll("[^\\[\\]{}\":,\\s]", "_");
    }
}
