package com.bugsnag.android;
/* loaded from: classes.dex */
class Intrinsics {
    Intrinsics() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
