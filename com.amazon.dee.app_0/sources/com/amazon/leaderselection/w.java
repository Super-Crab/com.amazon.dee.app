package com.amazon.leaderselection;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class w {
    private final String a;
    private final LeaderSelectionServiceVersion b;

    private w(String str, LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        u.a(str, "Cannot create without a package name.");
        u.a(leaderSelectionServiceVersion, "Cannot create without a version.");
        this.a = str;
        this.b = leaderSelectionServiceVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static w b(String str, LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        return new w(str, leaderSelectionServiceVersion);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Candidate candidate) {
        return candidate != null && a(candidate.getPackageName(), candidate.getLeaderSelectionServiceVersion());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, LeaderSelectionServiceVersion leaderSelectionServiceVersion) {
        return str != null && leaderSelectionServiceVersion != null && this.a.equals(str) && this.b.isLowerOrEquivalentTo(leaderSelectionServiceVersion);
    }

    public String toString() {
        return w.class.getSimpleName() + RealTimeTextConstants.COLON_SPACE + this.a + "." + this.b;
    }
}
