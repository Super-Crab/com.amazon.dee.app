package com.amazon.leaderselection;

import android.os.Message;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class g extends m {
    private static final String j = "g";

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(Candidate candidate, s sVar, v vVar, i iVar, p pVar, q qVar) {
        super(candidate, sVar, vVar, iVar, pVar, qVar);
    }

    @Override // com.amazon.leaderselection.m
    protected Message a(Candidate candidate) {
        String str = j;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Candidate (");
        outline107.append(candidate.getPackageName());
        outline107.append(") attempted to usurp leadership from a client.");
        Log.e(str, outline107.toString());
        return p.d;
    }

    @Override // com.amazon.leaderselection.m
    protected Message a(Candidate candidate, boolean z) {
        return b(candidate);
    }

    @Override // com.amazon.leaderselection.m
    protected void a() {
        this.d.a(Candidate.UNKNOWN);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        if ((r2.a.getLeaderSelectionServiceVersion().isHigherOrEquivalent(r3.getLeaderSelectionServiceVersion()) ? r2.c.a(r3) : r3.getPreferredOpinion() == com.amazon.leaderselection.PreferredOpinion.PREFERRED) != false) goto L16;
     */
    @Override // com.amazon.leaderselection.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.os.Message b(com.amazon.leaderselection.Candidate r3) {
        /*
            r2 = this;
            com.amazon.leaderselection.Candidate r0 = r2.a
            com.amazon.leaderselection.CandidateRole r0 = r0.getCandidateRole()
            com.amazon.leaderselection.CandidateRole r1 = com.amazon.leaderselection.CandidateRole.CLIENT
            if (r0 != r1) goto Lb
            goto L33
        Lb:
            com.amazon.leaderselection.CandidateRole r1 = com.amazon.leaderselection.CandidateRole.PICKY_CLIENT
            if (r0 != r1) goto L38
            com.amazon.leaderselection.Candidate r0 = r2.a
            com.amazon.leaderselection.LeaderSelectionServiceVersion r0 = r0.getLeaderSelectionServiceVersion()
            com.amazon.leaderselection.LeaderSelectionServiceVersion r1 = r3.getLeaderSelectionServiceVersion()
            boolean r0 = r0.isHigherOrEquivalent(r1)
            if (r0 == 0) goto L26
            com.amazon.leaderselection.v r0 = r2.c
            boolean r0 = r0.a(r3)
            goto L31
        L26:
            com.amazon.leaderselection.PreferredOpinion r0 = r3.getPreferredOpinion()
            com.amazon.leaderselection.PreferredOpinion r1 = com.amazon.leaderselection.PreferredOpinion.PREFERRED
            if (r0 != r1) goto L30
            r0 = 1
            goto L31
        L30:
            r0 = 0
        L31:
            if (r0 == 0) goto L38
        L33:
            com.amazon.leaderselection.i r0 = r2.d
            r0.a(r3)
        L38:
            com.amazon.leaderselection.p r3 = r2.e
            com.amazon.leaderselection.r r0 = com.amazon.leaderselection.EnumC0247r.FINISHED
            android.os.Message r3 = r3.a(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.leaderselection.g.b(com.amazon.leaderselection.Candidate):android.os.Message");
    }

    @Override // com.amazon.leaderselection.m
    protected Message b(o oVar) {
        Candidate d = oVar.d();
        if (CandidateRole.CANDIDATE == d.getCandidateRole()) {
            b(d);
            return this.e.a(EnumC0247r.MAKE_SELF_LEADER);
        }
        Log.e(j, "Another client deferred leader decision to us, this is not supposed to happen.");
        return p.d;
    }

    @Override // com.amazon.leaderselection.m
    protected Message d(o oVar) {
        Candidate d = oVar.d();
        String str = j;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Candidate (");
        outline107.append(d.getPackageName());
        outline107.append(") attempted to make a client leader.");
        Log.e(str, outline107.toString());
        a();
        return p.d;
    }
}
