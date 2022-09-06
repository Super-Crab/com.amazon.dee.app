package com.amazon.deecomms.common.util;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public final class PatternUtils {
    private PatternUtils() {
    }

    public static boolean isValidDuration(@NonNull String str) {
        return str.matches("[0-9]+:[0-5][0-9]:[0-5][0-9]") || str.matches("[0-5][0-9]:[0-5][0-9]");
    }
}
