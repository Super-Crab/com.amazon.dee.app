package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jf {
    public static String a(String str, Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return TextUtils.join(str, collection);
    }

    public static Set<String> at(String str, String str2) {
        return new HashSet(au(str, str2));
    }

    public static List<String> au(String str, String str2) {
        return Arrays.asList(split(str, str2));
    }

    public static String[] split(String str, String str2) {
        return TextUtils.isEmpty(str) ? new String[0] : TextUtils.split(str, str2);
    }
}
