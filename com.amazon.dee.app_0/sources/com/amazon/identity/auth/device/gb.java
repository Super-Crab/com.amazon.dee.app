package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gb extends gg {
    private static final String TAG = "com.amazon.identity.auth.device.gb";
    private static gb nE;
    private final ds bb;
    private final Context mContext;
    private final Object[] nF = new Object[0];
    private final gw nG;
    private final hq nH;
    private volatile ConcurrentHashMap<String, a> nI;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a implements ix<a> {
        public final Account account;
        public final String directedId;
        private final Object[] fD;
        public final Map<String, en<String>> nJ;
        private he nK;
        public final Map<String, en<String>> tokens;

        public a(String str, Account account) {
            this(str, account, new ConcurrentHashMap(), new ConcurrentHashMap());
        }

        public he a(gw gwVar) {
            he heVar;
            synchronized (this.fD) {
                if (this.nK == null) {
                    this.nK = gwVar.c(this.account);
                }
                heVar = this.nK;
            }
            return heVar;
        }

        @Override // com.amazon.identity.auth.device.ix
        /* renamed from: eZ */
        public a ek() {
            return new a(this.directedId, this.account, Cif.i(this.nJ), Cif.i(this.tokens));
        }

        private a(String str, Account account, Map<String, en<String>> map, Map<String, en<String>> map2) {
            this.fD = new Object[0];
            this.directedId = str;
            this.account = account;
            this.nJ = map;
            this.tokens = map2;
        }
    }

    gb(Context context) {
        this.mContext = ed.M(context);
        this.bb = (ds) this.mContext.getSystemService("sso_platform");
        this.nG = (gw) this.mContext.getSystemService("dcp_token_cache_holder");
        this.nH = (hq) this.mContext.getSystemService("dcp_account_manager");
    }

    public static synchronized gb R(Context context) {
        gb gbVar;
        synchronized (gb.class) {
            if (nE == null || jk.gR()) {
                nE = new gb(context.getApplicationContext());
            }
            gbVar = nE;
        }
        return gbVar;
    }

    public static boolean c(ds dsVar) {
        return dsVar.dk();
    }

    private a cg(String str) {
        return b(str, eW());
    }

    private a ch(String str) {
        return b(str, eX());
    }

    private void eV() {
        synchronized (this.nF) {
            this.nI = null;
        }
    }

    private Map<String, a> eW() {
        Map<String, a> i;
        ConcurrentHashMap<String, a> concurrentHashMap = this.nI;
        if (c(concurrentHashMap)) {
            return Cif.i(concurrentHashMap);
        }
        synchronized (this.nF) {
            i = Cif.i(eX());
        }
        return i;
    }

    private Map<String, a> eX() {
        if (!c(this.nI)) {
            this.nI = eY();
        }
        return this.nI;
    }

    private ConcurrentHashMap<String, a> eY() {
        Map<String, Account> b = hu.b(this.nH);
        ConcurrentHashMap<String, a> concurrentHashMap = new ConcurrentHashMap<>();
        for (Map.Entry<String, Account> entry : b.entrySet()) {
            concurrentHashMap.put(entry.getKey(), new a(entry.getKey(), entry.getValue()));
        }
        return concurrentHashMap;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void B(String str, String str2) {
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.e(TAG, "Cannot expire the requested token for the given directed ID because we couldn't construct a TokenCache");
                return;
            }
            he a2 = ch.a(this.nG);
            ch.tokens.remove(str2);
            a2.cP(str2);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public String C(String str, String str2) {
        if (this.bb.dj()) {
            return new gp(this.mContext, str).cs(str2);
        }
        throw new UnsupportedOperationException("getDeviceData should only be called via central apk");
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean D(String str) {
        if (str == null) {
            return false;
        }
        return eW().containsKey(str);
    }

    @Override // com.amazon.identity.auth.device.gg
    public void G(String str) {
        synchronized (this.nF) {
            Account ce = ce(str);
            if (ce == null) {
                io.w(TAG, "Cannot remove the requested user because it is not registered on the device");
                return;
            }
            this.nI.remove(str);
            AccountManagerFuture<Boolean> a2 = this.nH.a(ce, (AccountManagerCallback<Boolean>) null, true);
            boolean z = false;
            try {
                z = a2.getResult().booleanValue();
            } catch (AuthenticatorException e) {
                String str2 = TAG;
                io.e(str2, "Could not locally removed account because their was an Authenticator Exception. Error: " + e.getMessage());
            } catch (OperationCanceledException e2) {
                String str3 = TAG;
                io.e(str3, "Could not locally removed account because the operation was canceled. Error: " + e2.getMessage());
            } catch (IOException e3) {
                String str4 = TAG;
                io.e(str4, "Could not locally removed account because their was an IO Exception. Error: " + e3.getMessage());
            }
            eV();
            if (!z) {
                io.e(TAG, "Locally removing the account from the central store was not succesful");
            }
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar) {
        String directedId = fzVar.getDirectedId();
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : fzVar.eQ().entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        Map<String, String> eP = fzVar.eP();
        synchronized (this.nF) {
            if (D(directedId)) {
                return false;
            }
            Account account = new Account(str, AccountConstants.AMAZON_ACCOUNT_TYPE);
            bundle.putString("com.amazon.dcp.sso.property.account.acctId", directedId);
            boolean a2 = this.nH.a(account, bundle);
            eV();
            if (a2 && eP != null) {
                d(directedId, eP);
            }
            if (a2 && aVar != null) {
                aVar.onSuccess();
            }
            return a2;
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public String b(String str, String str2) {
        a cg = cg(str);
        if (cg == null) {
            io.w(TAG, "Cannot get the requested user data for the given directed ID because it is not registered on the device");
            return null;
        }
        en<String> enVar = cg.nJ.get(str2);
        if (enVar != null) {
            return enVar.getValue();
        }
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.w(TAG, "Cannot get the requested user data for the given directed ID because it is not registered on the device");
                return null;
            }
            en<String> enVar2 = ch.nJ.get(str2);
            if (enVar2 != null) {
                return enVar2.getValue();
            }
            String c = this.nH.c(ch.account, str2);
            ch.nJ.put(str2, new en<>(c));
            return c;
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cc(String str) {
        io.e(TAG, "Actor is not supported in this platform!");
        throw new UnsupportedOperationException("Actor is not supported in this platform.");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Account ce(String str) {
        a cg = cg(str);
        if (cg == null) {
            return null;
        }
        return cg.account;
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> cf(String str) {
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.e(TAG, "Cannot get all token keys for the directedId because we couldn't get the account info");
                return new HashSet();
            }
            return ch.tokens.keySet();
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void eS() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> eT() {
        HashSet hashSet = new HashSet();
        for (a aVar : eW().values()) {
            hashSet.add(aVar.account.name);
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void f(String str, String str2, String str3) {
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.e(TAG, "Cannot set the requested token for the given directed ID because we couldn't construct a TokenCache");
                return;
            }
            he a2 = ch.a(this.nG);
            ch.tokens.remove(str2);
            a2.al(str2, str3);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void g(String str, String str2, String str3) {
        if (this.bb.dj()) {
            new gp(this.mContext, str).U(str2, str3);
            return;
        }
        throw new UnsupportedOperationException("setDeviceData should only be called via central apk");
    }

    @Override // com.amazon.identity.auth.device.gg
    public Set<String> getAccounts() {
        return eW().keySet();
    }

    @Override // com.amazon.identity.auth.device.gg
    public String getDeviceSnapshot() {
        io.i(TAG, "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.gg
    public void initialize() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public void setup() {
    }

    @Override // com.amazon.identity.auth.device.gg
    public String z(String str, String str2) {
        a cg = cg(str);
        if (cg == null) {
            io.e(TAG, "Cannot get the requested token for the given directed ID because we couldn't construct a TokenCache");
            return null;
        }
        en<String> enVar = cg.tokens.get(str2);
        if (enVar != null) {
            return enVar.getValue();
        }
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.e(TAG, "Cannot get the requested token for the given directed ID because we couldn't construct a TokenCache");
                return null;
            }
            en<String> enVar2 = ch.tokens.get(str2);
            if (enVar2 != null) {
                return enVar2.getValue();
            }
            String ca = ch.a(this.nG).ca(str2);
            ch.tokens.put(str2, new en<>(ca));
            return ca;
        }
    }

    private boolean c(Map<String, a> map) {
        return map != null && this.bb.dj();
    }

    private a b(String str, Map<String, a> map) {
        if (str == null) {
            io.e(TAG, "Cannot find account for null directedId");
            return null;
        }
        a aVar = map.get(str);
        if (aVar == null) {
            io.a(TAG, str, map.keySet());
        }
        return aVar;
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(String str, String str2, String str3) {
        synchronized (this.nF) {
            a ch = ch(str);
            if (ch == null) {
                io.w(TAG, "Cannot set the requested user data for the given directed ID because it is not registered on the device");
                return;
            }
            ch.nJ.remove(str2);
            this.nH.setUserData(ch.account, str2, str3);
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public void a(fz fzVar) {
        for (Map.Entry<String, String> entry : fzVar.eQ().entrySet()) {
            a(fzVar.getDirectedId(), entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : fzVar.eP().entrySet()) {
            f(fzVar.getDirectedId(), entry2.getKey(), entry2.getValue());
        }
    }

    @Override // com.amazon.identity.auth.device.gg
    public boolean a(String str, fz fzVar, gg.a aVar, List<String> list) {
        io.e(TAG, "Replace accounts not supported");
        return false;
    }
}
