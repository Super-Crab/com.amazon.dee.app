package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.gg;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gu extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.gu";
    private static gu oY;
    private final ed o;
    private gg oZ;
    private gg pa;
    private final boolean pb;

    private gu(ed edVar) {
        io.i(TAG, "Constructing RuntimeSwitchableDataStorage");
        this.o = edVar;
        this.oZ = gc.S(this.o);
        ds dsVar = new ds(this.o);
        if (!dsVar.dt() && !dsVar.du()) {
            if (!hx.an(this.o)) {
                io.i(TAG, "Using DistributedDataStorage as SSO storage");
                this.pa = gk.U(this.o);
                this.pb = true;
                return;
            }
            io.e(TAG, "Runtime isolated mode currently only supported on 3P devices. Please remove runtime isolated entry in manifest if your app is running on 1P devices.");
            throw new IllegalStateException("Runtime isolated mode currently only supported on 3P devices. Please remove runtime isolated entry in manifest if your app is running on 1P devices.");
        }
        io.i(TAG, "Using CentralAccountManagerDataStorage as SSO storage");
        this.pa = gb.R(this.o);
        this.pb = false;
    }

    public static synchronized gu aa(Context context) {
        gu guVar;
        synchronized (gu.class) {
            if (oY == null) {
                oY = new gu(ed.M(context.getApplicationContext()));
            }
            guVar = oY;
        }
        return guVar;
    }

    public static boolean ab(Context context) {
        return IsolatedModeSwitcher.doesAppSupportRuntimeIsolatedMode(context);
    }

    private gg fJ() {
        if (this.o.dW().a(Feature.IsolateApplication)) {
            return this.oZ;
        }
        return this.pa;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        fJ().B(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public String C(String str, String str2) {
        return fJ().C(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void G(String str) {
        fJ().G(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        return fJ().a(str, fzVar, aVar);
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        return fJ().b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cc(String str) {
        return fJ().cc(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        return fJ().ce(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cf(String str) {
        return fJ().cf(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
        this.pa.eS();
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        return fJ().eT();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void f(String str, String str2, String str3) {
        fJ().f(str, str2, str3);
    }

    public boolean fK() {
        return this.pb;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void g(String str, String str2, String str3) {
        fJ().g(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> getAccounts() {
        return fJ().getAccounts();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        return fJ().getDeviceSnapshot();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void initialize() {
        fJ().initialize();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
        fJ().setup();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        return fJ().z(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        fJ().a(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        fJ().a(fzVar);
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        return fJ().a(str, fzVar, aVar, list);
    }
}
