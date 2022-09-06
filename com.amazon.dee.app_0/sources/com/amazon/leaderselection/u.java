package com.amazon.leaderselection;
/* loaded from: classes12.dex */
class u {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Object obj, String str) {
        a(obj == null, str);
    }

    static void a(boolean z, String str) {
        if (!z) {
            return;
        }
        throw new IllegalArgumentException(str);
    }
}
