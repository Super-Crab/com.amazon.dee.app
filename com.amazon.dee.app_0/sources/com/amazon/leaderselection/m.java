package com.amazon.leaderselection;

import android.os.Message;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public abstract class m {
    private static final String i = "m";
    protected final Candidate a;
    protected final s b;
    protected final v c;
    protected final i d;
    protected final p e;
    protected final q f;
    private final Map<Candidate, o> g = new HashMap();
    private Candidate h = Candidate.UNKNOWN;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[EnumC0247r.values().length];

        static {
            try {
                a[EnumC0247r.MAKE_SELF_LEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EnumC0247r.MAKE_SENDER_LEADER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EnumC0247r.USURP_LEADERSHIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EnumC0247r.LEADERSHIP_USURPED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[EnumC0247r.REQUEST_CANDIDATE_INFORMATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[EnumC0247r.CANDIDATE_INFORMATION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[EnumC0247r.DEFER_LEADERSHIP_DECISION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[EnumC0247r.REQUEST_MOST_PREFERRED_INFORMATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[EnumC0247r.MOST_PREFERRED_INFORMATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Candidate candidate, s sVar, v vVar, i iVar, p pVar, q qVar) {
        this.a = candidate;
        this.b = sVar;
        this.c = vVar;
        this.d = iVar;
        this.e = pVar;
        this.f = qVar;
    }

    private Message b(Candidate candidate, boolean z) {
        if (!Candidate.UNKNOWN.equals(candidate) && !this.a.equals(candidate)) {
            return candidate.getLeaderSelectionServiceVersion().isHigherOrEquivalent(this.a.getLeaderSelectionServiceVersion()) ? this.f.a(EnumC0247r.DEFER_LEADERSHIP_DECISION, candidate) : a(candidate, z);
        }
        a();
        return this.e.a(EnumC0247r.FINISHED);
    }

    private Message d(Candidate candidate) {
        Candidate b = this.b.b(candidate);
        return Candidate.UNKNOWN.equals(b) ? e() : this.g.containsKey(b) ? b(this.h, false) : this.f.a(EnumC0247r.REQUEST_CANDIDATE_INFORMATION, b);
    }

    private Message e() {
        if (!this.b.c(this.a)) {
            return this.f.a(EnumC0247r.REQUEST_MOST_PREFERRED_INFORMATION, this.b.c());
        } else if (!this.b.d(this.a)) {
            return b(this.b.b(), false);
        } else {
            a();
            return this.e.a(EnumC0247r.FINISHED);
        }
    }

    private Message e(Candidate candidate) {
        if (this.a.equals(candidate)) {
            a();
            return this.e.a(EnumC0247r.FINISHED);
        }
        this.h = candidate;
        return this.f.a(EnumC0247r.REQUEST_CANDIDATE_INFORMATION, candidate);
    }

    protected abstract Message a(Candidate candidate);

    protected abstract Message a(Candidate candidate, boolean z);

    protected Message a(o oVar) {
        Candidate d = oVar.d();
        Candidate a2 = oVar.a();
        this.g.put(d, oVar);
        return Candidate.UNKNOWN.equals(a2) ? d(d) : !d.equals(a2) ? e(a2) : b(d, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message a(EnumC0247r enumC0247r, o oVar) {
        if (enumC0247r != null && oVar != null) {
            switch (a.a[enumC0247r.ordinal()]) {
                case 1:
                    return d(oVar);
                case 2:
                    return e(oVar);
                case 3:
                    return g(oVar);
                case 4:
                    return c(oVar);
                case 5:
                    return b();
                case 6:
                    return a(oVar);
                case 7:
                    return b(oVar);
                case 8:
                    return c();
                case 9:
                    return f(oVar);
                default:
                    String str = "Received unexpected message type: " + enumC0247r;
                    break;
            }
        } else {
            Log.e(i, "Handled a null message: " + enumC0247r + " " + oVar);
        }
        return p.d;
    }

    protected abstract void a();

    protected Message b() {
        return this.e.a(EnumC0247r.CANDIDATE_INFORMATION);
    }

    protected abstract Message b(Candidate candidate);

    protected Message b(o oVar) {
        p pVar;
        EnumC0247r enumC0247r;
        Candidate d = oVar.d();
        if (this.c.a(d, this.a)) {
            Candidate a2 = this.d.a();
            if (!this.d.b() || !this.a.equals(a2)) {
                b(d);
            } else {
                a(d);
            }
            pVar = this.e;
            enumC0247r = EnumC0247r.MAKE_SELF_LEADER;
        } else {
            if (!this.d.b()) {
                a();
            }
            pVar = this.e;
            enumC0247r = EnumC0247r.MAKE_SENDER_LEADER;
        }
        return pVar.a(enumC0247r);
    }

    protected Message c() {
        return this.e.a(EnumC0247r.MOST_PREFERRED_INFORMATION);
    }

    protected Message c(Candidate candidate) {
        return this.f.a(EnumC0247r.REQUEST_CANDIDATE_INFORMATION, candidate);
    }

    protected Message c(o oVar) {
        Candidate d = oVar.d();
        Candidate e = oVar.e();
        if (!this.d.b() || !this.d.a().equals(d)) {
            Log.e(i, "Received a LEADERSHIP_USURPED from a Candidate other than our leader, ignoring");
            return p.d;
        }
        this.d.a(e);
        return this.e.a(EnumC0247r.FINISHED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Message d() {
        Candidate a2 = this.d.a();
        if (!this.b.a().isEmpty()) {
            if (Candidate.UNKNOWN.equals(a2)) {
                Candidate c = this.b.c();
                if (!Candidate.UNKNOWN.equals(c)) {
                    return c(c);
                }
            }
            return this.e.a(EnumC0247r.FINISHED);
        }
        a();
        return this.e.a(EnumC0247r.FINISHED);
    }

    protected Message d(o oVar) {
        a();
        return this.e.a(EnumC0247r.FINISHED);
    }

    protected Message e(o oVar) {
        return b(oVar.d());
    }

    protected Message f(o oVar) {
        return b(oVar.c(), false);
    }

    protected Message g(o oVar) {
        if (this.d.b()) {
            if (this.a.equals(this.d.a())) {
                Candidate d = oVar.d();
                if (this.a.getLeaderSelectionServiceVersion().isSignificantlyHigherThan(d.getLeaderSelectionServiceVersion()) && !this.c.a(d, this.a)) {
                    Log.e(i, "Someone asked to usurp us but should not have.");
                    a();
                    return this.e.a(EnumC0247r.MAKE_SENDER_LEADER);
                }
                return a(d);
            }
        }
        Log.e(i, "Received a USURP_LEADERSHIP when not leader");
        return p.d;
    }
}
