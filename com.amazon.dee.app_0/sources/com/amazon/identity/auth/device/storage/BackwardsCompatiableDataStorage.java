package com.amazon.identity.auth.device.storage;

import android.accounts.Account;
import android.text.TextUtils;
import android.util.Base64;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.cr;
import com.amazon.identity.auth.device.cv;
import com.amazon.identity.auth.device.dk;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.fw;
import com.amazon.identity.auth.device.fz;
import com.amazon.identity.auth.device.gc;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gk;
import com.amazon.identity.auth.device.hu;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.BadPaddingException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class BackwardsCompatiableDataStorage extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage";
    private static AtomicInteger nv = new AtomicInteger(0);
    private final ds bb;
    private final gg nw;
    private final fw nx;
    private final boolean ny;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class BackwardsCompatibleDataStorageException extends Exception implements fp.a {
        private static final int nC = MAPAccountManager.RegistrationError.INTERNAL_ERROR.value();
        private static final String nD = MAPAccountManager.RegistrationError.INTERNAL_ERROR.getName();
        private fp mAccountRecoverContext;

        public BackwardsCompatibleDataStorageException(fp fpVar) {
            super(nD);
            this.mAccountRecoverContext = fpVar;
        }

        @Override // com.amazon.identity.auth.device.fp.a
        public int bE() {
            return nC;
        }

        @Override // com.amazon.identity.auth.device.fp.a
        public String bF() {
            return super.getMessage();
        }

        @Override // com.amazon.identity.auth.device.fp.a
        public fp eE() {
            return this.mAccountRecoverContext;
        }
    }

    public BackwardsCompatiableDataStorage(ed edVar) {
        this(edVar, edVar.dV());
    }

    private boolean cd(String str) {
        return hu.dc(str) || hu.db(str);
    }

    static synchronized void eU() {
        synchronized (BackwardsCompatiableDataStorage.class) {
            nv = new AtomicInteger(0);
        }
    }

    static byte[] stringToBytes(String str) {
        return Base64.decode(str, 0);
    }

    private String y(String str, String str2) throws BackwardsCompatibleDataStorageException {
        fw fwVar;
        GeneratedOutlineSupport1.outline161(str2, "Get user data for: ", TAG);
        String b = this.nw.b(str, str2);
        if (TextUtils.isEmpty(b)) {
            String str3 = TAG;
            String.format(Locale.ENGLISH, "Value for %s is empty", str2);
            io.dm(str3);
            return b;
        } else if (this.ny) {
            return b;
        } else {
            try {
                if (hu.dc(str2)) {
                    io.dm(TAG);
                    if (this.bb.dh()) {
                        fwVar = new cv(this.nw, str);
                    } else {
                        fwVar = this.nx;
                    }
                    String bY = fwVar.bY(b);
                    if (bY == null) {
                        io.w(TAG, "Could not decrypt tokens using expected methods.");
                    }
                    return bY;
                } else if (hu.db(str2)) {
                    io.dm(TAG);
                    return new cv(this.nw, str).bY(b);
                } else {
                    io.dm(TAG);
                    return b;
                }
            } catch (BadPaddingException unused) {
                io.e(TAG, "BadPaddingException occurs.");
                if (nv.getAndIncrement() < 5) {
                    gg ggVar = this.nw;
                    String str4 = null;
                    if (!(ggVar instanceof gk)) {
                        io.e(TAG, "DataStorage is not DistributedDataStorage. That db should never be corrupted");
                    } else if (!hu.dc(str2)) {
                        io.e(TAG, "Token other than DMS token corrupted. This should never happen.");
                    } else {
                        io.i(TAG, "Trying to recover corrupted key locally for key: ".concat(String.valueOf(str2)));
                        gk gkVar = (gk) ggVar;
                        Set<String> fk = gkVar.fk();
                        ArrayList arrayList = new ArrayList();
                        for (final String str5 : fk) {
                            arrayList.add(new fw() { // from class: com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage.2
                                @Override // com.amazon.identity.auth.device.fw
                                public byte[] cq() {
                                    return BackwardsCompatiableDataStorage.stringToBytes(str5);
                                }
                            });
                        }
                        str4 = a(b, arrayList, gkVar);
                    }
                    if (!TextUtils.isEmpty(str4)) {
                        io.dm(TAG);
                        eU();
                        mq.b("map_badpadding_locally_recover_success", new String[0]);
                        return str4;
                    }
                    io.i(TAG, "Failed to recover account in device");
                    mq.b("map_badpadding_locally_recover_failure", new String[0]);
                } else {
                    io.e(TAG, "Exceed local recovery retry upper-bound. Going to return account recovery bundle.");
                }
                throw new BackwardsCompatibleDataStorageException(fp.eB().bT(str).bU("BackwardsCompatiableDataStorage:BadPaddingException"));
            }
        }
    }

    public String A(String str, String str2) throws BackwardsCompatibleDataStorageException {
        if (cd(str2)) {
            return y(str, str2);
        }
        return this.nw.z(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        if (cd(str2)) {
            a(str, str2, (String) null);
        } else {
            this.nw.B(str, str2);
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

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        fw fwVar;
        final String str2 = null;
        if (!this.ny) {
            str2 = cr.cp();
            fwVar = new fw() { // from class: com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage.1
                @Override // com.amazon.identity.auth.device.fw
                public byte[] cq() {
                    return Base64.decode(str2, 0);
                }
            };
        } else {
            fwVar = null;
        }
        fz a = a(fzVar, fwVar);
        if (str2 != null) {
            a.v(AccountConstants.KEY_TOKEN_ENCRYPT_KEY, str2);
        }
        return this.nw.a(str, a, aVar);
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        try {
            return y(str, str2);
        } catch (BackwardsCompatibleDataStorageException e) {
            io.e(TAG, "BadPaddingException occurs. Swallow this exception here.", e);
            return null;
        }
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

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
        this.nw.eS();
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        return this.nw.eT();
    }

    @Override // com.amazon.identity.auth.device.gg
    public void f(String str, String str2, String str3) {
        if (cd(str2)) {
            a(str, str2, str3);
        } else {
            this.nw.f(str, str2, str3);
        }
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

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
        this.nw.setup();
    }

    public String x(String str, String str2) {
        return this.nw.b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        if (cd(str2)) {
            return b(str, str2);
        }
        return this.nw.z(str, str2);
    }

    public BackwardsCompatiableDataStorage(ed edVar, gg ggVar) {
        this(ggVar, (ds) edVar.getSystemService("sso_platform"), new dk(edVar));
    }

    BackwardsCompatiableDataStorage(gg ggVar, ds dsVar, fw fwVar) {
        this.nw = ggVar;
        this.bb = dsVar;
        this.nx = fwVar;
        this.ny = this.nw instanceof gc;
    }

    protected String a(String str, List<fw> list, gk gkVar) {
        String bY;
        for (fw fwVar : list) {
            String encodeToString = Base64.encodeToString(fwVar.cq(), 2);
            try {
                bY = fwVar.bY(str);
            } catch (BadPaddingException unused) {
                io.e(TAG, "This key didn't match, retry!");
            }
            if (!TextUtils.isEmpty(bY)) {
                gkVar.cl(encodeToString);
                io.i(TAG, "Successfully recovered locally!");
                return bY;
            }
            continue;
        }
        return null;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        if (this.ny) {
            this.nw.a(str, str2, str3);
            return;
        }
        this.nw.a(str, str2, a(new cv(this.nw, str), str2, str3));
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        this.nw.a(a(fzVar, this.ny ? null : new cv(this.nw, fzVar.getDirectedId())));
    }

    private fz a(fz fzVar, fw fwVar) {
        HashMap hashMap = new HashMap(fzVar.eQ());
        HashMap hashMap2 = new HashMap();
        for (Map.Entry<String, String> entry : fzVar.eP().entrySet()) {
            if (cd(entry.getKey())) {
                hashMap.put(entry.getKey(), entry.getValue());
            } else {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        if (fwVar != null) {
            for (Map.Entry entry2 : hashMap.entrySet()) {
                entry2.setValue(a(fwVar, (String) entry2.getKey(), (String) entry2.getValue()));
            }
        }
        return new fz(fzVar.getDirectedId(), hashMap, hashMap2);
    }

    private String a(fw fwVar, String str, String str2) {
        if (hu.db(str)) {
            return fwVar.bX(str2);
        }
        return hu.dc(str) ? this.nx.bX(str2) : str2;
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        return this.nw.a(str, fzVar, aVar, list);
    }
}
