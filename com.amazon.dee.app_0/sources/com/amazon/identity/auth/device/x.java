package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.android.amazonprofile.AmazonProfileManager;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.SubAuthenticatorConnection;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.i;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.storage.KeystoreProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class x {
    private static final String TAG = "com.amazon.identity.auth.device.x";
    private static final ExecutorService bJ = Executors.newFixedThreadPool(10, ji.dG("MAP-DeregisterThreadPool"));
    private final ac bK;
    private final a bL = new a() { // from class: com.amazon.identity.auth.device.x.1
        @Override // com.amazon.identity.auth.device.x.a
        public i B() {
            return x.this.B();
        }

        @Override // com.amazon.identity.auth.device.x.a
        public SubAuthenticatorConnection a(aj ajVar) {
            return x.this.a(ajVar);
        }
    };
    private final AmazonAccountManager bg;
    private final Context mContext;
    private final j t;
    private final ab x;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        i B();

        SubAuthenticatorConnection a(aj ajVar);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class b implements a {
        private final Context mContext;

        public b(Context context) {
            this.mContext = context;
        }

        @Override // com.amazon.identity.auth.device.x.a
        public i B() {
            return new i(this.mContext);
        }

        @Override // com.amazon.identity.auth.device.x.a
        public SubAuthenticatorConnection a(aj ajVar) {
            return new SubAuthenticatorConnection(ajVar, this.mContext);
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class c implements Runnable {
        private final ac bK;
        private a bL;
        private final MAPAccountManager bO;
        final String bP;
        private final ArrayList<aj> bQ;
        final ej bR;
        private final br bS;
        private final Bundle bT;
        private final boolean bU;
        private e bV;
        private final ds bb;
        private final Context mContext;
        private final AmazonAccountManager s;
        final j t;
        private final gg w;

        public c(Context context, String str, Collection<aj> collection, j jVar, br brVar, ej ejVar, Bundle bundle) {
            this.mContext = context;
            this.bb = (ds) this.mContext.getSystemService("sso_platform");
            this.bO = new MAPAccountManager(this.mContext);
            this.bK = new ac(this.mContext);
            this.s = (AmazonAccountManager) this.mContext.getSystemService("dcp_amazon_account_man");
            this.w = ((gh) this.mContext.getSystemService("dcp_data_storage_factory")).dV();
            this.bQ = new ArrayList<>(collection);
            this.bP = str;
            this.t = jVar;
            this.bU = bundle != null && (bundle.getBoolean("DeregisteringDevice") || (bundle.getBoolean("DeregisteringDefaultPrimary") && TextUtils.isEmpty(bundle.getString("NewDefaultPrimary"))));
            this.bS = brVar;
            this.bR = ejVar;
            this.bT = bundle;
        }

        private boolean F() {
            SubAuthenticatorConnection J;
            Account o = hu.o(this.mContext, this.bP);
            boolean z = true;
            if (o == null) {
                io.e(x.TAG, "Sub authenticators are not supported on 3rd party devices yet");
                return true;
            }
            Iterator<aj> it2 = this.bQ.iterator();
            while (it2.hasNext()) {
                aj next = it2.next();
                g gVar = new g(D().a(next));
                gVar.run();
                if (!gVar.I()) {
                    io.e(x.TAG, "Failed to establish SubAuthenticator Connection");
                    J = null;
                } else {
                    J = gVar.J();
                }
                if (J == null) {
                    SSOMetrics.bQ(next.packageName);
                    z = false;
                } else {
                    try {
                        if (!a(o, J)) {
                            SSOMetrics.bQ(J.getPackageName());
                            z = false;
                        }
                    } finally {
                        J.closeConnection();
                    }
                }
            }
            return z;
        }

        private boolean G() {
            try {
                KeystoreProvider keystoreProvider = new KeystoreProvider(String.format("%s_%s", "mobile_auth_storage", this.bP));
                if (keystoreProvider.fm() != null) {
                    keystoreProvider.fn();
                }
                gp.l(this.mContext, "mobile_auth_storage").fE();
                return true;
            } catch (KeystoreProvider.KeystoreProviderException unused) {
                return false;
            }
        }

        public synchronized a D() {
            if (this.bL == null) {
                this.bL = new b(this.mContext);
            }
            return this.bL;
        }

        public synchronized e E() {
            return this.bV;
        }

        protected boolean isRegistered(String str) {
            return this.s.D(str);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean H;
            boolean z = false;
            if (!isRegistered(this.bP)) {
                SSOMetrics.a(MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED);
            } else {
                mv ez = SSOMetrics.ez();
                ez.start();
                boolean F = F();
                if (x.b(this.s, this.bP)) {
                    String unused = x.TAG;
                    io.a("Have already notified server of deregister of %s", this.bP);
                    H = true;
                } else {
                    f fVar = new f(this.mContext, this.bP, this.bU, D().B(), this.t, this.bS, this.bR, this.bT);
                    fVar.run();
                    H = fVar.H();
                    if (!H) {
                        io.e(x.TAG, "Deregister was not successful. Not marking accounts that they were deregistered on the server");
                    } else {
                        for (String str : this.bU ? this.bO.getAccounts() : hs.a(this.bP)) {
                            x.a(this.s, str);
                        }
                    }
                }
                if (!H) {
                    F = false;
                }
                if (this.bb.ds()) {
                    Collection<String> a = q.a(this.bP, this.w);
                    if (!a.isEmpty()) {
                        for (final String str2 : a) {
                            final i B = D().B();
                            final i.a aVar = new i.a() { // from class: com.amazon.identity.auth.device.x.c.1
                                @Override // com.amazon.identity.auth.device.i.a
                                public void a(MAPError mAPError, String str3, MAPAccountManager.RegistrationError registrationError, String str4, Bundle bundle) {
                                    io.e(x.TAG, String.format("Deregister Failure for Overriding DSN Child Device Type: %s", registrationError.name()));
                                }

                                @Override // com.amazon.identity.auth.device.i.a
                                public void b(String str3, String str4, Bundle bundle) {
                                    String str5 = x.TAG;
                                    String.format("Deregister Succeeded for Overriding DSN Child Device Type. directedId %s", str3);
                                    io.dm(str5);
                                }

                                @Override // com.amazon.identity.auth.device.i.a
                                public void t(String str3) {
                                    io.e(x.TAG, String.format("Deregister Failure for Overriding DSN Child Device Type due to registration already existing.  This should not happen. DirectedId: %s", str3));
                                }
                            };
                            final br a2 = B.a(this.bP, str2, aVar);
                            x.bJ.execute(new Runnable() { // from class: com.amazon.identity.auth.device.x.c.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    i iVar = B;
                                    i.a aVar2 = aVar;
                                    String str3 = str2;
                                    c cVar = c.this;
                                    iVar.a(aVar2, str3, cVar.bP, a2, true, cVar.t, cVar.bR, (Bundle) null);
                                }
                            });
                        }
                    }
                }
                io.i(x.TAG, "Deregister dependent accounts");
                if (this.bU || this.bT.getBoolean("DeregisteringDefaultPrimary")) {
                    io.i(x.TAG, "Deregister the secondary accounts");
                    for (final String str3 : this.s.r()) {
                        if (!this.bU) {
                            if (this.bK.hasPrimaryRole(str3)) {
                                String unused2 = x.TAG;
                                String.format("keeping the secondary primary account %s", str3);
                                io.gC();
                            } else {
                                String b = this.w.b(str3, "com.amazon.dcp.sso.property.account.delegateeaccount");
                                if (!TextUtils.isEmpty(b) && !b.equals(this.bP) && this.bK.hasPrimaryRole(b)) {
                                    String unused3 = x.TAG;
                                    String.format("keeping the delegated account %s", str3);
                                    io.gC();
                                }
                            }
                        }
                        String unused4 = x.TAG;
                        String.format("Deregister the secondary account %s", str3);
                        io.gC();
                        this.bO.deregisterAccount(str3, new Callback() { // from class: com.amazon.identity.auth.device.x.c.4
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle) {
                                String str4 = x.TAG;
                                new StringBuilder("Deregister secondary account fail: ").append(bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                                io.dm(str4);
                                c.this.bR.bA("FailDeregisterSecondaryAccount");
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle) {
                                String str4 = x.TAG;
                                new StringBuilder("Deregister secondary account success: ").append(str3);
                                io.dm(str4);
                            }
                        });
                    }
                } else {
                    LinkedList<String> linkedList = new LinkedList();
                    Set<String> accounts = this.s.getAccounts();
                    if (accounts != null) {
                        for (String str4 : accounts) {
                            if (!str4.equals(this.bP) && this.bP.equals(this.w.b(str4, "com.amazon.dcp.sso.property.account.delegateeaccount"))) {
                                linkedList.add(str4);
                            }
                        }
                        for (final String str5 : linkedList) {
                            this.bO.deregisterAccount(str5, new Callback() { // from class: com.amazon.identity.auth.device.x.c.3
                                @Override // com.amazon.identity.auth.device.api.Callback
                                public void onError(Bundle bundle) {
                                    String str6 = x.TAG;
                                    new StringBuilder("Deregister delegated account fail: ").append(bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                                    io.dm(str6);
                                    c.this.bR.bA("FailDeregisterDelegatedAccount");
                                }

                                @Override // com.amazon.identity.auth.device.api.Callback
                                public void onSuccess(Bundle bundle) {
                                    String str6 = x.TAG;
                                    new StringBuilder("Deregister delegated account success: ").append(str5);
                                    io.dm(str6);
                                }
                            });
                        }
                    }
                }
                Context context = this.mContext;
                String str6 = this.bP;
                boolean X = this.bK.X(str6);
                if (!mz.bn(context)) {
                    io.w("AmazonProfileManagerSafeConsumer", "Not removing profiles for the account as either APS is not on this device or APS version is older than 11 or required permission not granted.");
                } else {
                    AmazonProfileManager amazonProfileManager = AmazonProfileManager.getAmazonProfileManager(context);
                    int unregisterAccount = amazonProfileManager.unregisterAccount(amazonProfileManager.getDefaultProgramIdOnDevice(), str6, X);
                    String dp = io.dp(str6);
                    if (unregisterAccount == AmazonProfileManager.SUCCESS) {
                        io.i("AmazonProfileManagerSafeConsumer", String.format("Removing all profiles from APS for current deregistered account %s success! Primary account: %b", dp, Boolean.valueOf(X)));
                    } else {
                        io.e("AmazonProfileManagerSafeConsumer", String.format("Removing all profiles from APS for current deregistered account %s is not success. Primary account: %b", dp, Boolean.valueOf(X)));
                    }
                }
                this.w.G(this.bP);
                if (G()) {
                    z = F;
                }
                try {
                    gp.l(this.mContext, "actor_info_storage_" + this.bP).fE();
                } catch (NullPointerException unused5) {
                    io.e(x.TAG, "No Actor Info to clear");
                }
                try {
                    gp.l(this.mContext, "DMS_ATS").fE();
                } catch (Exception e) {
                    io.e(x.TAG, "Something went wrong when clearing account transfer info", e);
                }
                ez.stop();
            }
            a(z);
        }

        public synchronized void a(a aVar) {
            this.bL = aVar;
        }

        public synchronized void a(e eVar) {
            this.bV = eVar;
        }

        protected boolean a(Account account, SubAuthenticatorConnection subAuthenticatorConnection) {
            String str = x.TAG;
            new StringBuilder("Notifying subauth: ").append(subAuthenticatorConnection.getPackageName());
            io.dm(str);
            h hVar = new h(subAuthenticatorConnection, account);
            mv bR = SSOMetrics.bR(subAuthenticatorConnection.getPackageName());
            bR.start();
            hVar.run(7L, TimeUnit.SECONDS, "NotifySubAuthAccountRemoval");
            bR.stop();
            return hVar.H();
        }

        public void a(boolean z) {
            e E = E();
            if (E != null) {
                E.a(z);
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface d {
        void onResult(Bundle bundle);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface e {
        void a(boolean z);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class f extends bk implements i.a {
        private final String bP;
        private final ej bR;
        private final br bS;
        private final Bundle bT;
        private AtomicBoolean cd = new AtomicBoolean(false);
        private final boolean ce;
        private final Context mContext;
        private final j t;
        private final i u;

        public f(Context context, String str, boolean z, i iVar, j jVar, br brVar, ej ejVar, Bundle bundle) {
            this.mContext = context;
            this.bP = str;
            this.ce = z;
            this.u = iVar;
            this.t = jVar;
            this.bS = brVar;
            this.bR = ejVar;
            this.bT = bundle;
        }

        public boolean H() {
            return this.cd.get();
        }

        @Override // com.amazon.identity.auth.device.i.a
        public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
            io.dm(x.TAG);
            SSOMetrics.a(registrationError);
            b(false);
            asyncOperationComplete();
        }

        public void b(boolean z) {
            this.cd.set(z);
        }

        @Override // com.amazon.identity.auth.device.bk
        public void startAsyncOperation() {
            this.u.a(this, this.mContext.getPackageName(), this.bP, this.bS, this.ce, this.t, this.bR, this.bT);
        }

        @Override // com.amazon.identity.auth.device.i.a
        public void t(String str) {
            MAPError.AccountError accountError = MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED;
            a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS, null, null);
        }

        @Override // com.amazon.identity.auth.device.i.a
        public void b(String str, String str2, Bundle bundle) {
            io.dm(x.TAG);
            b(true);
            asyncOperationComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class g extends bk implements SubAuthenticatorConnection.b {
        private final SubAuthenticatorConnection cf;
        private AtomicBoolean cg = new AtomicBoolean(false);

        public g(SubAuthenticatorConnection subAuthenticatorConnection) {
            this.cf = subAuthenticatorConnection;
        }

        private void c(boolean z) {
            this.cg.set(z);
        }

        public boolean I() {
            return this.cg.get();
        }

        public SubAuthenticatorConnection J() {
            return this.cf;
        }

        @Override // com.amazon.identity.auth.accounts.SubAuthenticatorConnection.b
        public void K() {
            io.e(x.TAG, "SubAuth Connection timeout");
            c(false);
            asyncOperationComplete();
        }

        @Override // com.amazon.identity.auth.accounts.SubAuthenticatorConnection.b
        public void L() {
            c(true);
            asyncOperationComplete();
        }

        @Override // com.amazon.identity.auth.accounts.SubAuthenticatorConnection.b
        public void a(SubAuthenticatorConnection subAuthenticatorConnection) {
            String str = x.TAG;
            new StringBuilder("SubAuth Disconnected: ").append(subAuthenticatorConnection.getPackageName());
            io.dm(str);
            c(false);
        }

        @Override // com.amazon.identity.auth.device.bk
        public void startAsyncOperation() {
            if (!this.cf.a(this)) {
                io.e(x.TAG, "Error binding to service");
                c(false);
                asyncOperationComplete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class h extends bk implements SubAuthenticatorConnection.a {
        private final SubAuthenticatorConnection cf;
        private final Account ci;
        private final Object[] ch = new Object[0];
        private AtomicBoolean cd = new AtomicBoolean(false);

        public h(SubAuthenticatorConnection subAuthenticatorConnection, Account account) {
            this.cf = subAuthenticatorConnection;
            this.ci = account;
        }

        public boolean H() {
            return this.cd.get();
        }

        @Override // com.amazon.identity.auth.accounts.SubAuthenticatorConnection.a
        public void M() {
            synchronized (this.ch) {
                String str = x.TAG;
                String.format("SubAuth Deregister Success: Package=%s,", this.cf.getPackageName());
                io.dm(str);
                b(true);
                asyncOperationComplete();
            }
        }

        @Override // com.amazon.identity.auth.device.bk
        public void onTimeout() {
            synchronized (this.ch) {
                io.e(x.TAG, String.format("SubAuth Deregister Timeout", new Object[0]));
                b(false);
                asyncOperationComplete();
            }
        }

        @Override // com.amazon.identity.auth.device.bk
        public void startAsyncOperation() {
            x.bJ.execute(new Runnable() { // from class: com.amazon.identity.auth.device.x.h.1
                @Override // java.lang.Runnable
                public void run() {
                    h.this.cf.a(h.this.ci, h.this);
                }
            });
        }

        private void b(boolean z) {
            this.cd.set(z);
        }

        @Override // com.amazon.identity.auth.accounts.SubAuthenticatorConnection.a
        public void b(int i, String str) {
            synchronized (this.ch) {
                io.e(x.TAG, String.format(Locale.ENGLISH, "SubAuth Deregister Error: errorCode=%d, msg=%s", Integer.valueOf(i), str));
                b(false);
                asyncOperationComplete();
            }
        }
    }

    public x(Context context) {
        this.mContext = ed.M(context);
        this.t = new j(this.mContext);
        this.bg = (AmazonAccountManager) this.mContext.getSystemService("dcp_amazon_account_man");
        this.x = ab.g(this.mContext);
        this.bK = new ac(this.mContext);
    }

    public static boolean b(AmazonAccountManager amazonAccountManager, String str) {
        return amazonAccountManager.b(str, "has.notified.server.of.deregister") != null;
    }

    protected i B() {
        return new i(this.mContext);
    }

    public void a(List<aj> list, d dVar, String str, ej ejVar, Bundle bundle) {
        io.i(TAG, "Starting deregister request");
        Bundle K = hw.K(bundle);
        if (this.bK.X(str)) {
            K.putBoolean("DeregisteringDefaultPrimary", true);
        }
        br a2 = this.bL.B().a(str, (String) null, (i.a) null);
        this.bK.b(str, K);
        this.x.R(str);
        bJ.execute(a(str, list, dVar, a2, ejVar, K));
    }

    protected c a(String str, List<aj> list, final d dVar, br brVar, ej ejVar, Bundle bundle) {
        c cVar = new c(this.mContext, str, list, this.t, brVar, ejVar, bundle);
        cVar.a(new e() { // from class: com.amazon.identity.auth.device.x.2
            @Override // com.amazon.identity.auth.device.x.e
            public void a(boolean z) {
                dVar.onResult(GeneratedOutlineSupport1.outline13("booleanResult", z));
            }
        });
        cVar.a(this.bL);
        return cVar;
    }

    protected SubAuthenticatorConnection a(aj ajVar) {
        return new SubAuthenticatorConnection(ajVar, this.mContext);
    }

    public static void a(AmazonAccountManager amazonAccountManager, String str) {
        amazonAccountManager.a(str, "has.notified.server.of.deregister", "true");
    }
}
