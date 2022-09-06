package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.gg;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class gc extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.gc";
    private static gc nL;
    private final go nM;
    private final ed o;

    gc(Context context, go goVar) {
        io.i(TAG, "Constructing CentralLocalDataStorage");
        this.o = ed.M(context);
        this.nM = goVar;
    }

    public static synchronized gc S(Context context) {
        gc gcVar;
        synchronized (gc.class) {
            if (nL == null || jk.gR()) {
                ed M = ed.M(context.getApplicationContext());
                nL = new gc(M, go.X(M));
            }
            gcVar = nL;
        }
        return gcVar;
    }

    public static boolean a(ds dsVar, co coVar) {
        return dsVar.dy() || coVar.a(Feature.IsolateApplication);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        initialize();
        if (!this.nM.T(str, str2)) {
            io.e(TAG, "Expiring the token was not successful. ");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public String C(String str, String str2) {
        initialize();
        return this.nM.C(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void G(String str) {
        initialize();
        if (!this.nM.cp(str)) {
            io.e(TAG, "Removing the account was not successful.");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        initialize();
        return this.nM.S(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void c(String str, Map<String, String> map) {
        initialize();
        if (!this.nM.g(str, map)) {
            io.e(TAG, "Setting device tokens was not successful. ");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cc(String str) {
        initialize();
        return this.nM.cc(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cf(String str) {
        return this.nM.cf(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        initialize();
        return this.nM.eT();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void f(String str, String str2, String str3) {
        initialize();
        if (!this.nM.p(str, str2, str3)) {
            io.e(TAG, "Setting the token was not successful.");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void g(String str, String str2, String str3) {
        initialize();
        if (!this.nM.q(str, str2, str3)) {
            io.e(TAG, "Setting device token was not successful. ");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> getAccounts() {
        initialize();
        return this.nM.getAccounts();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        io.i(TAG, "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.gg
    public synchronized void initialize() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        initialize();
        return this.nM.S(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        initialize();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fzVar.eQ());
        hashMap.putAll(fzVar.eP());
        boolean c = this.nM.c(str, fzVar.getDirectedId(), hashMap);
        if (c && aVar != null) {
            aVar.onSuccess();
        }
        return c;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        initialize();
        if (!this.nM.p(str, str2, str3)) {
            io.e(TAG, "Setting the userdata was not successful.");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public Map<String, String> a(String str, List<String> list) {
        initialize();
        return this.nM.a(str, list);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        initialize();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fzVar.eQ());
        hashMap.putAll(fzVar.eP());
        if (!this.nM.f(fzVar.getDirectedId(), hashMap)) {
            io.e(TAG, "Setting the data was not successful.");
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        initialize();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fzVar.eQ());
        hashMap.putAll(fzVar.eP());
        boolean a = this.nM.a(str, fzVar.getDirectedId(), hashMap, list);
        if (a && aVar != null) {
            aVar.onSuccess();
        }
        return a;
    }
}
