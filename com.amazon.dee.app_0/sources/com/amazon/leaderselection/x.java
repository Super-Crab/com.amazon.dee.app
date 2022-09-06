package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import com.amazon.alexa.utils.security.ComponentEnabler;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class x {
    private final PackageManager a;

    public x(PackageManager packageManager) {
        this.a = packageManager;
    }

    public boolean a(Candidate candidate, String str) {
        return ComponentEnabler.checkIfServiceIsEnabled(this.a, new ComponentName(candidate.getPackageName(), str));
    }
}
