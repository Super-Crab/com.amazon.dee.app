package com.amazon.leaderselection;
/* loaded from: classes12.dex */
enum CandidateRole {
    CANDIDATE,
    PICKY_CLIENT,
    CLIENT,
    NONE;

    public static boolean canLead(CandidateRole candidateRole) {
        return candidateRole == CANDIDATE;
    }

    public static CandidateRole create(String str) {
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return NONE;
        }
    }
}
