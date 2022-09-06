package com.amazon.leaderselection;

import android.content.ComponentName;
/* loaded from: classes12.dex */
class c {
    private final ComponentName a;
    private final CandidateRegistration b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(ComponentName componentName, CandidateRegistration candidateRegistration) {
        this.a = componentName;
        this.b = candidateRegistration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CandidateRegistration a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentName b() {
        return this.a;
    }
}
