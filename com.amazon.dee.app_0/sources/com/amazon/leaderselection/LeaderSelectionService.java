package com.amazon.leaderselection;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import com.amazon.alexa.utils.security.SignatureVerifier;
/* loaded from: classes12.dex */
public class LeaderSelectionService extends Service {
    private static final String c = LeaderSelectionService.class.getSimpleName();
    public static final IntentFilter d = new IntentFilter();
    public static final Intent e = new Intent();
    private m a;
    private b b;

    static {
        d.addCategory("com.amazon.leaderselection.CATEGORY_LEADER_SELECTION");
        d.addCategory("com.amazon.leaderselection.ACTION_SELECT");
        e.addCategory("com.amazon.leaderselection.CATEGORY_LEADER_SELECTION");
        e.setAction("com.amazon.leaderselection.ACTION_SELECT");
    }

    private void a() {
        this.b.a();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String str = getPackageName() + ": onBind";
        return new j(getPackageManager(), new SignatureVerifier(this), this.a);
    }

    @Override // android.app.Service
    public void onCreate() {
        String str = getPackageName() + ": onCreate";
        v a = v.a(this);
        f fVar = new f(getPackageManager());
        Candidate createThisCandidate = Candidate.createThisCandidate(this, fVar, a);
        s sVar = new s(a, new d(getPackageName(), getPackageManager(), fVar), new SignatureVerifier(this));
        i iVar = new i(createThisCandidate.getComponentName(), sVar, new y(this, "com.amazon.leaderselection.LEADER_AUTHORITY_PREFERENCES_NAMESPACE"));
        p pVar = new p(createThisCandidate, sVar, iVar);
        this.b = new b();
        this.a = n.a(createThisCandidate, sVar, a, iVar, pVar, new q(this, this.b, pVar));
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        String str = getPackageName() + ": onUnbind";
        a();
        return false;
    }
}
