package com.amazon.leaderselection;

import android.content.ComponentName;
import com.amazon.leaderselection.t;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class i {
    private static final String e = "i";
    private final ComponentName a;
    private final s b;
    private final t c;
    private Candidate d;

    public i(ComponentName componentName, s sVar, t tVar) {
        this.a = componentName;
        this.b = sVar;
        this.c = tVar;
    }

    private void c() {
        this.d = Candidate.UNKNOWN;
        a(this.d);
    }

    private Candidate d() {
        if (e()) {
            CandidateRole create = CandidateRole.create(this.c.a("LEADER_ROLE_KEY", (String) null));
            String a = this.c.a("LEADER_PACKAGE_NAME_KEY", "unknown");
            String a2 = this.c.a("LEADER_SERVICE_NAME_KEY", "unknown");
            AccountIdentifier create2 = AccountIdentifier.create(this.c.a("LEADER_ACCOUNT_ID_KEY", (String) null));
            LeaderSelectionServiceVersion create3 = LeaderSelectionServiceVersion.create(this.c.a("LEADER_VERSION_KEY", LeaderSelectionServiceVersion.UNKNOWN.toString()));
            PreferredOpinion create4 = PreferredOpinion.create(this.c.a("LEADER_PREFERRED_OPINION_KEY", (String) null));
            if (!"unknown".equals(a) && !"unknown".equals(a2) && !create3.equals(LeaderSelectionServiceVersion.UNKNOWN)) {
                this.d = Candidate.create(CandidateRegistration.create(create, create3), h.a(a, a2), create2, create4);
                if (!f()) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Leader no longer exists, resetting: ");
                    outline107.append(this.d.getComponentName());
                    outline107.toString();
                    c();
                }
                return this.d;
            }
        }
        this.d = Candidate.UNKNOWN;
        return this.d;
    }

    private boolean e() {
        return this.c.a("HAS_LEADER_KEY", false);
    }

    private boolean f() {
        Candidate candidate = this.d;
        if (candidate == null) {
            return false;
        }
        if (!this.b.a(candidate)) {
            return this.a.equals(this.d.getComponentName());
        }
        return true;
    }

    public synchronized Candidate a() {
        if (this.d == null) {
            this.d = d();
        }
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(Candidate candidate) {
        this.d = candidate;
        t.a a = this.c.a();
        a.a("LEADER_ROLE_KEY", candidate.getCandidateRole().toString());
        a.a("LEADER_PACKAGE_NAME_KEY", candidate.getPackageName());
        a.a("LEADER_SERVICE_NAME_KEY", candidate.getServiceName());
        a.a("LEADER_ACCOUNT_ID_KEY", candidate.getAccountIdentifier().getValue());
        a.a("LEADER_VERSION_KEY", candidate.getLeaderSelectionServiceVersion().toString());
        a.a("LEADER_PREFERRED_OPINION_KEY", candidate.getPreferredOpinion().toString());
        a.a("HAS_LEADER_KEY", true);
        a.a();
    }

    public synchronized boolean b() {
        boolean z;
        if (a() != null && !Candidate.UNKNOWN.equals(this.d)) {
            if (f()) {
                z = true;
            }
        }
        z = false;
        return z;
    }
}
