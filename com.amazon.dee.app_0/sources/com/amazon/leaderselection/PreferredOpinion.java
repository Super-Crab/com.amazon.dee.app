package com.amazon.leaderselection;
/* loaded from: classes12.dex */
enum PreferredOpinion {
    PREFERRED,
    NOT_PREFERRED,
    UNKNOWN;

    public static PreferredOpinion create(String str) {
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return UNKNOWN;
        }
    }
}
