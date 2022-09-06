package com.amazon.leaderselection;

import com.amazon.alexa.utils.security.SignatureVerifier;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class s {
    private final d a;
    private final v b;
    private final SignatureVerifier c;
    private TreeSet<Candidate> d;
    private Candidate e = Candidate.UNKNOWN;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class b implements Comparator<Candidate> {
        private b() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Candidate candidate, Candidate candidate2) {
            LeaderSelectionServiceVersion leaderSelectionServiceVersion = candidate.getLeaderSelectionServiceVersion();
            LeaderSelectionServiceVersion leaderSelectionServiceVersion2 = candidate2.getLeaderSelectionServiceVersion();
            if (candidate.equals(candidate2)) {
                return 0;
            }
            return ((!leaderSelectionServiceVersion.equals(leaderSelectionServiceVersion2) || !CandidateRole.canLead(candidate.getCandidateRole())) && !leaderSelectionServiceVersion.isHigherOrEquivalent(leaderSelectionServiceVersion2)) ? -1 : 1;
        }
    }

    public s(v vVar, d dVar, SignatureVerifier signatureVerifier) {
        this.b = vVar;
        this.a = dVar;
        this.c = signatureVerifier;
    }

    private boolean a(c cVar) {
        return cVar.a().getRole() != CandidateRole.NONE && this.c.verify(cVar.b().getPackageName());
    }

    private void e(Candidate candidate) {
        if (this.b.a(candidate, this.e) || (!this.b.a(this.e) && CandidateRole.canLead(candidate.getCandidateRole()) && candidate.getLeaderSelectionServiceVersion().isHigherOrEquivalent(this.e.getLeaderSelectionServiceVersion()))) {
            this.e = candidate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Candidate> a() {
        if (this.d == null) {
            this.d = new TreeSet<>(new b());
            for (c cVar : this.a.a()) {
                if (a(cVar)) {
                    Candidate create = Candidate.create(cVar, AccountIdentifier.UNKNOWN, PreferredOpinion.UNKNOWN);
                    e(create);
                    this.d.add(create);
                }
            }
        }
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Candidate> a(Candidate... candidateArr) {
        Set<Candidate> a2 = a();
        HashSet hashSet = new HashSet();
        hashSet.addAll(a2);
        for (Candidate candidate : candidateArr) {
            hashSet.remove(candidate);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Candidate candidate) {
        for (Candidate candidate2 : a()) {
            if (candidate2.equals(candidate)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate b() {
        a();
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate b(Candidate candidate) {
        a();
        Iterator<Candidate> descendingIterator = this.d.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (candidate.equals(descendingIterator.next()) && descendingIterator.hasNext()) {
                return descendingIterator.next();
            }
        }
        return Candidate.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate c() {
        a();
        return this.d.isEmpty() ? Candidate.UNKNOWN : this.d.last();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(Candidate candidate) {
        return candidate.getLeaderSelectionServiceVersion().isHigherOrEquivalent(c().getLeaderSelectionServiceVersion());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d(Candidate candidate) {
        if (candidate.getCandidateRole() != CandidateRole.CANDIDATE) {
            return false;
        }
        Candidate b2 = b();
        return this.b.a(b2) ? this.b.a(candidate, b2) : c(candidate);
    }
}
