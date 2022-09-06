package com.amazon.leaderselection;
/* renamed from: com.amazon.leaderselection.r  reason: case insensitive filesystem */
/* loaded from: classes12.dex */
enum EnumC0247r {
    ERROR,
    FINISHED,
    SELECT_LEADER,
    DEFER_LEADERSHIP_DECISION,
    MAKE_SENDER_LEADER,
    MAKE_SELF_LEADER,
    USURP_LEADERSHIP,
    LEADERSHIP_USURPED,
    REQUEST_CANDIDATE_INFORMATION,
    CANDIDATE_INFORMATION,
    REQUEST_MOST_PREFERRED_INFORMATION,
    MOST_PREFERRED_INFORMATION;

    public static boolean a(int i) {
        return i >= 0 && i < values().length;
    }

    public static EnumC0247r b(int i) {
        if (a(i)) {
            return values()[i];
        }
        return null;
    }
}
