package com.amazon.leaderselection;

import android.content.ComponentName;
/* loaded from: classes12.dex */
class h {
    static final ComponentName a = new ComponentName("unknown", "unknown");

    public static ComponentName a(String str, String str2) {
        return (str == null || str2 == null) ? a : new ComponentName(str, str2);
    }
}
