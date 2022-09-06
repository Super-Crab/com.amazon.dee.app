package com.amazon.alexa.voice.ui.onedesign.util;

import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes11.dex */
public final class AvsDomain {
    private AvsDomain() {
    }

    public static boolean containsAnyDomain(@Nullable List<CharSequence> list, String... strArr) {
        if (list != null) {
            HashSet hashSet = new HashSet(list);
            for (String str : strArr) {
                if (hashSet.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
