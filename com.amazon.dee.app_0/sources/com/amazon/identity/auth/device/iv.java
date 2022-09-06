package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class iv {
    private static final Pattern ru = Pattern.compile("((.*[^0-9])|(^))([0-9]{4,6})[^0-9].*[a-zA-Z0-9+\\\\/]{11}", 32);

    public static String dv(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = ru.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(4);
    }
}
