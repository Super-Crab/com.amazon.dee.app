package com.amazon.leaderselection;

import android.os.Message;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class e extends m {
    private static final String j = "e";

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Candidate candidate, s sVar, v vVar, i iVar, p pVar, q qVar) {
        super(candidate, sVar, vVar, iVar, pVar, qVar);
    }

    @Override // com.amazon.leaderselection.m
    protected Message a(Candidate candidate) {
        this.d.a(candidate);
        Set<Candidate> a = this.b.a(candidate);
        HashSet<Candidate> hashSet = new HashSet();
        if (!this.c.a(this.a)) {
            for (Candidate candidate2 : a) {
                if (CandidateRole.PICKY_CLIENT != candidate2.getCandidateRole()) {
                    hashSet.add(candidate2);
                }
            }
        } else {
            hashSet.addAll(a);
        }
        for (Candidate candidate3 : hashSet) {
            EnumC0247r b = EnumC0247r.b(this.f.a(candidate, candidate3).what);
            if (EnumC0247r.FINISHED != b) {
                String str = j;
                Log.e(str, "Received " + b + " from a candidate! Should only get " + EnumC0247r.FINISHED);
            }
        }
        return this.e.a(EnumC0247r.FINISHED);
    }

    @Override // com.amazon.leaderselection.m
    protected Message a(Candidate candidate, boolean z) {
        if (this.c.a(this.a, candidate)) {
            a();
            return z ? this.f.a(EnumC0247r.USURP_LEADERSHIP, candidate) : this.e.a(EnumC0247r.FINISHED);
        }
        return b(candidate);
    }

    @Override // com.amazon.leaderselection.m
    protected void a() {
        this.d.a(this.a);
    }

    @Override // com.amazon.leaderselection.m
    protected Message b(Candidate candidate) {
        this.d.a(candidate);
        return this.e.a(EnumC0247r.FINISHED);
    }
}
