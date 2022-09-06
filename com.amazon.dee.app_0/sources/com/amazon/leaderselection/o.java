package com.amazon.leaderselection;

import android.os.Bundle;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes12.dex */
class o {
    private final Candidate a;
    private final Candidate b;
    private final Candidate c;
    private final Candidate d;
    private final Bundle e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(Bundle bundle) {
        this.e = bundle;
        this.e.setClassLoader(o.class.getClassLoader());
        this.a = (Candidate) bundle.getParcelable("SENDER_KEY");
        this.b = (Candidate) bundle.getParcelable("LEADER_KEY");
        this.d = (Candidate) bundle.getParcelable("MOST_PREFERRED_KEY");
        this.c = (Candidate) bundle.getParcelable("USURPER_KEY");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(Candidate candidate, Candidate candidate2, Candidate candidate3) {
        this(candidate, candidate2, candidate3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(Candidate candidate, Candidate candidate2, Candidate candidate3, @Nullable Candidate candidate4) {
        this.e = new Bundle(o.class.getClassLoader());
        this.e.putParcelable("SENDER_KEY", candidate);
        this.e.putParcelable("LEADER_KEY", candidate2);
        this.e.putParcelable("MOST_PREFERRED_KEY", candidate3);
        if (candidate4 != null) {
            this.e.putParcelable("USURPER_KEY", candidate4);
        }
        this.a = candidate;
        this.b = candidate2;
        this.d = candidate3;
        this.c = candidate4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(EnumC0247r enumC0247r, Bundle bundle) {
        if (enumC0247r == null || bundle == null) {
            return false;
        }
        bundle.setClassLoader(o.class.getClassLoader());
        return bundle.containsKey("SENDER_KEY") && bundle.containsKey("LEADER_KEY") && (EnumC0247r.MOST_PREFERRED_INFORMATION != enumC0247r || !bundle.containsKey("MOST_PREFERRED_KEY") || bundle.get("MOST_PREFERRED_KEY") != null) && (EnumC0247r.LEADERSHIP_USURPED != enumC0247r || !bundle.containsKey("USURPER_KEY") || bundle.get("USURPER_KEY") != null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle b() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate c() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Candidate d() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Candidate e() {
        return this.c;
    }
}
