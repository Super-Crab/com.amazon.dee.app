package com.amazon.leaderselection;
/* loaded from: classes12.dex */
class n {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static m a(Candidate candidate, s sVar, v vVar, i iVar, p pVar, q qVar) {
        return CandidateRole.canLead(candidate.getCandidateRole()) ? new e(candidate, sVar, vVar, iVar, pVar, qVar) : new g(candidate, sVar, vVar, iVar, pVar, qVar);
    }
}
