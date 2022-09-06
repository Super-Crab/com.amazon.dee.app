package com.amazon.deecomms.common.util;

import android.text.TextUtils;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public final class PathMatcher {
    private PathMatcher() {
    }

    public static boolean matches(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Pattern.matches(str.replaceAll("\\{(.*?)\\}", ".*").replace("/", "\\/"), str2);
    }
}
