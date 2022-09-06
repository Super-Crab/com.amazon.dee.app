package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gv;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gm extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.gm";
    private final gg nw;
    private final ed o;
    private f oy;

    public gm(Context context) {
        this.o = ed.M(context);
        this.nw = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
    }

    private boolean n(String str, String str2, String str3) {
        ej by = ej.by("RegisterChildApplicationFromDBLayer");
        MAPFuture<Bundle> a = fr().a(str, str2, GeneratedOutlineSupport1.outline11("override_dsn", str3), (Callback) null, by);
        boolean z = false;
        try {
            try {
                try {
                    if (a.get() != null) {
                        z = true;
                    }
                    return z;
                } catch (MAPCallbackErrorException e) {
                    String str4 = TAG;
                    io.e(str4, "Error registeringChildAccount. Bundle Error: " + hw.M(e.getErrorBundle()), e);
                    return false;
                }
            } catch (InterruptedException e2) {
                io.e(TAG, "Interrupted exception while registeringChildAccount", e2);
                return false;
            } catch (ExecutionException e3) {
                io.e(TAG, "Execution exception while registeringChildAccount", e3);
                return false;
            }
        } finally {
            by.eb();
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        String b = b(str, str2, true);
        if (b == null) {
            io.w(TAG, "expireToken failed because key does not make sense on the platform");
        } else {
            this.nw.B(str, b);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public String C(String str, String str2) {
        return this.nw.C(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void G(String str) {
        this.nw.G(str);
    }

    public String M(String str, String str2) {
        String b = b(str, str2, false);
        if (b == null) {
            io.w(TAG, "peekUserData failed because key does not make sense on the platform");
            return null;
        }
        return this.nw.b(str, b);
    }

    public String N(String str, String str2) throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        String b = b(str, str2, true);
        String str3 = TAG;
        String.format("Token key: %s. ContextualKey: %s", str2, b);
        io.dm(str3);
        if (b == null) {
            io.w(TAG, "getToken failed because key does not make sense on the platform");
            return null;
        }
        gg ggVar = this.nw;
        if (ggVar instanceof BackwardsCompatiableDataStorage) {
            return ((BackwardsCompatiableDataStorage) ggVar).A(str, b);
        }
        return ggVar.z(str, b);
    }

    public String O(String str, String str2) {
        String b = b(str, str2, false);
        if (b == null) {
            io.w(TAG, "peekToken failed because key does not make sense on the platform");
            return null;
        }
        return this.nw.z(str, b);
    }

    public void P(String str, String str2) {
        this.nw.B(str, str2);
    }

    boolean Q(String str, String str2) {
        boolean a = p.a(this.o, this.nw, str, str2);
        String str3 = TAG;
        String.format("Child application device type %s has already been registered", str2);
        io.dm(str3);
        return a;
    }

    public Map<String, String> R(String str, String str2) {
        gv.a aVar;
        HashMap hashMap = new HashMap();
        for (String str3 : cf(str)) {
            String str4 = null;
            if (str3.endsWith(str2)) {
                int indexOf = str3.indexOf(47);
                if (indexOf >= 0) {
                    str4 = str3.substring(0, indexOf);
                }
                aVar = new gv.a(str4, str2, str3);
            } else {
                aVar = null;
            }
            a(aVar, str, hashMap);
        }
        return hashMap;
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        return this.nw.a(str, fzVar, aVar);
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        String b = b(str, str2, true);
        if (b == null) {
            io.w(TAG, "getUserData failed because key does not make sense on the platform");
            return null;
        }
        return this.nw.b(str, b);
    }

    du bm(String str) {
        return MAPApplicationInformationQueryer.E(this.o).bm(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cc(String str) {
        return this.nw.cc(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        return this.nw.ce(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cf(String str) {
        return this.nw.cf(str);
    }

    boolean cm(String str) {
        return ie.p(this.o, str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void e(String str, String str2, String str3, String str4) {
        this.nw.a(str, F(str2, str3), str4);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
        this.nw.eS();
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        return this.nw.eT();
    }

    @Override // com.amazon.identity.auth.device.gg
    @Deprecated
    public void f(String str, String str2, String str3) {
        String b = b(str, str2, true);
        if (b == null) {
            io.w(TAG, "setToken failed because key does not make sense on the platform");
        } else {
            this.nw.f(str, b, str3);
        }
    }

    synchronized f fr() {
        if (this.oy == null) {
            this.oy = g.a(this.o);
        }
        return this.oy;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void g(String str, String str2, String str3) {
        this.nw.g(str, str2, str3);
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> getAccounts() {
        return this.nw.getAccounts();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        return this.nw.getDeviceSnapshot();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void initialize() {
        this.nw.initialize();
    }

    public String m(String str, String str2, String str3) {
        String a = a(str, str2, str3, true);
        String str4 = TAG;
        String.format("Token key: %s, ActorContextualKey: %s", str3, a);
        io.dm(str4);
        return this.nw.z(str, a);
    }

    public Map<String, String> o(String str, String str2, String str3) {
        gv.a aVar;
        HashMap hashMap = new HashMap();
        for (String str4 : cf(str)) {
            int lastIndexOf = str4.lastIndexOf("/".concat(String.valueOf(str2)));
            String str5 = null;
            if (lastIndexOf > 0) {
                String substring = str4.substring(0, lastIndexOf);
                if (substring.endsWith(str3)) {
                    int indexOf = substring.indexOf(47);
                    if (indexOf >= 0) {
                        str5 = substring.substring(0, indexOf);
                    }
                    aVar = new gv.a(str5, str3, str4);
                    a(aVar, str, hashMap);
                }
            }
            aVar = null;
            a(aVar, str, hashMap);
        }
        return hashMap;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
        this.nw.setup();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        String b = b(str, str2, true);
        String str3 = TAG;
        String.format("Token key: %s. ContextualKey: %s", str2, b);
        io.dm(str3);
        if (b == null) {
            io.w(TAG, "getToken failed because key does not make sense on the platform");
            return null;
        }
        return this.nw.z(str, b);
    }

    private Map<String, String> e(String str, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String b = b(str, entry.getKey(), true);
            if (b == null) {
                String str2 = TAG;
                io.w(str2, "Not setting " + entry.getKey() + " because the child device type could not be registered.");
            } else {
                hashMap.put(b, entry.getValue());
            }
        }
        return hashMap;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        String b = b(str, str2, true);
        if (b == null) {
            io.w(TAG, "setUserData failed because key does not make sense on the platform");
        } else {
            this.nw.a(str, b, str3);
        }
    }

    private String b(String str, String str2, boolean z) {
        if (z && ji.gP()) {
            throw new IllegalStateException("Cannot get local data on the main thread");
        }
        im dl = im.dl(str2);
        String packageName = dl.getPackageName();
        if (packageName == null) {
            return dl.getKey();
        }
        du bm = bm(packageName);
        if (bm == null) {
            return dl.getKey();
        }
        try {
            String deviceType = bm.getDeviceType();
            String dE = bm.dE();
            if (cm(deviceType)) {
                String str3 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(deviceType);
                sb.append(" is the central device type for ");
                sb.append(packageName);
                io.dm(str3);
                return dl.getKey();
            } else if (z && !Q(str, deviceType)) {
                if (n(str, deviceType, dE)) {
                    return gv.i(this.o, deviceType, dl.getKey());
                }
                io.e(TAG, String.format("Could not register application with the device type %s", deviceType));
                return null;
            } else {
                String str4 = TAG;
                StringBuilder sb2 = new StringBuilder("device: ");
                sb2.append(deviceType);
                sb2.append(" already registered, returning key");
                io.dm(str4);
                return gv.i(this.o, deviceType, dl.getKey());
            }
        } catch (RemoteMAPException unused) {
            io.w(TAG, "Couldn't determine override device type/DSN for the package ");
            return null;
        }
    }

    public void a(String str, String str2, Set<String> set) {
        for (String str3 : set) {
            this.nw.B(str, gv.i(this.o, str2, im.dl(str3).getKey()));
        }
    }

    public gm(Context context, gg ggVar) {
        this.o = ed.M(context);
        this.nw = ggVar;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        String directedId = fzVar.getDirectedId();
        this.nw.a(new fz(fzVar.getDirectedId(), e(directedId, fzVar.eQ()), e(directedId, fzVar.eP())));
    }

    private String a(String str, String str2, String str3, boolean z) {
        String b = b(str, str3, z);
        if (b == null) {
            io.w(TAG, "getActorToken failed because key does not make sense on the platform");
            return null;
        }
        return gv.X(b, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        return this.nw.a(str, fzVar, aVar, list);
    }

    private void a(gv.a aVar, String str, Map<String, String> map) {
        if (aVar != null) {
            String z = this.nw.z(str, aVar.fL());
            if (TextUtils.isEmpty(z)) {
                return;
            }
            if (aVar.getDeviceType() == null) {
                map.put(ie.au(this.o), z);
            } else {
                map.put(aVar.getDeviceType(), z);
            }
        }
    }

    public void b(String str, String str2, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String a = a(str, str2, entry.getKey(), false);
            if (a == null) {
                String str3 = TAG;
                io.w(str3, "Not setting actor key " + entry.getKey());
            } else {
                hashMap.put(a, entry.getValue());
            }
        }
        this.nw.d(str, hashMap);
    }

    public void a(String str, String str2, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(gv.i(this.o, str2, im.dl(entry.getKey()).getKey()), entry.getValue());
        }
        this.nw.d(str, hashMap);
    }

    public void a(String str, String str2, String str3, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            hashMap.put(gv.X(gv.i(this.o, str3, im.dl(entry.getKey()).getKey()), str2), entry.getValue());
        }
        this.nw.d(str, hashMap);
    }
}
