package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class u implements ISubAuthenticator {
    private final gd D;
    private final a bA;
    private final ds bb;
    private final AmazonAccountManager bg;
    private final ab bh;
    private final BackwardsCompatiableDataStorage bi;
    private final cz bj;
    private final String bk;
    private final String bl;
    private final String bm;
    private final String bn;
    private final Long bo;
    private final boolean bp = true;
    private final a bq;
    private final a br;
    private final a bs;
    private final a bt;
    private final a bu;
    private final a bv;
    private final a bw;
    private final a bx;
    private final a by;
    private final a bz;
    private final ed o;
    private final gg w;
    private static final String bf = aj.ah("com.amazon.kindle");
    private static final String TAG = u.class.getName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.u$6  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] aI = new int[RegisterDeviceErrorType.values().length];

        static {
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognized.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedFirs.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedKindle.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public final String bG;
        public final String bH;

        public a(String str, String str2) {
            this.bG = str;
            this.bH = str2;
        }

        public String toString() {
            Object[] objArr = new Object[2];
            String str = this.bG;
            String str2 = "none";
            if (str == null) {
                str = str2;
            }
            objArr[0] = str;
            String str3 = this.bH;
            if (str3 != null) {
                str2 = str3;
            }
            objArr[1] = str2;
            return String.format("[%s,%s]", objArr);
        }
    }

    protected u(Context context, String str, String str2, String str3, Long l) {
        this.o = ed.M(context);
        this.bg = (AmazonAccountManager) this.o.getSystemService("dcp_amazon_account_man");
        this.bh = ab.g(this.o);
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
        this.bi = new BackwardsCompatiableDataStorage(this.o);
        this.bj = (cz) this.o.getSystemService("sso_webservice_caller_creator");
        this.bb = (ds) this.o.getSystemService("sso_platform");
        this.bk = str2;
        this.bl = str3;
        this.bm = str;
        this.bo = l;
        this.bn = ie.n(context, str2, str);
        this.bq = new a(aj.ad(str), gv.i(this.o, str2, AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN));
        this.br = new a(aj.ae(str), gv.i(this.o, str2, AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        this.bs = new a(aj.af(str), gv.i(this.o, str2, "com.amazon.dcp.sso.token.devicedevicetype"));
        this.bt = new a(aj.ag(str), gv.i(this.o, str2, "com.amazon.dcp.sso.token.device.deviceserialname"));
        this.bu = new a(aj.ah(str), gv.i(this.o, str2, "com.amazon.dcp.sso.property.deviceemail"));
        this.bv = new a(aj.ai(str), gv.i(this.o, str2, "com.amazon.identity.cookies.xfsn"));
        this.bw = new a(aj.aj(str), gv.i(this.o, str2, AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN));
        String str4 = null;
        this.bx = new a(str == null ? null : GeneratedOutlineSupport1.outline72(str, ".tokens.device_name"), gv.i(this.o, str2, "com.amazon.dcp.sso.property.devicename"));
        this.by = new a(str == null ? null : GeneratedOutlineSupport1.outline72(str, ".tokens.user_name"), gv.i(this.o, str2, "com.amazon.dcp.sso.property.username"));
        this.bz = new a(str != null ? GeneratedOutlineSupport1.outline72(str, ".tokens.user_firstname") : str4, gv.i(this.o, str2, "com.amazon.dcp.sso.property.firstname"));
        this.bA = new a(aj.ak(str), gv.i(this.o, str2, "com.amazon.dcp.sso.token.device.accountpool"));
        this.D = new ge(this.o).fa();
    }

    private md K(String str) {
        RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest(this.o);
        registerDeviceRequest.hD();
        registerDeviceRequest.dT(this.bk);
        registerDeviceRequest.dU(M(str));
        if (q.a(this.bl, this.o.dW())) {
            registerDeviceRequest.hE();
        }
        boolean L = L(str);
        if (L) {
            io.i(TAG, String.format("Registering secondary account for device type %s", this.bk));
        }
        registerDeviceRequest.a(L, RegisterDeviceRequest.DeviceAccountRole.SECONDARY);
        Long l = this.bo;
        if (l != null) {
            registerDeviceRequest.a(new mc(Long.toString(l.longValue())));
        }
        String str2 = this.bn;
        if (str2 != null) {
            registerDeviceRequest.ei(str2);
        }
        return registerDeviceRequest.ho();
    }

    private boolean L(String str) {
        return this.bp && !this.bg.C(str);
    }

    private String M(String str) {
        String str2 = this.bl;
        if (str2 != null) {
            io.a("Using custom override DSN %s for registering of device type %s", str2, this.bk);
            return this.bl;
        }
        return new BackwardsCompatiableDataStorage(this.o, this.w).b(str, "com.amazon.dcp.sso.token.device.deviceserialname");
    }

    public static u e(Context context) {
        String packageName = context.getPackageName();
        du bm = MAPApplicationInformationQueryer.E(context).bm(packageName);
        if (bm != null) {
            try {
                return new u(context, packageName, bm.getDeviceType(), bm.dE(), it.x(context, packageName));
            } catch (RemoteMAPException e) {
                throw new IllegalStateException("Could not construct DMSSubAuthenticator for this package (" + packageName + ") because we couldn't query its MAP info provider", e);
            }
        }
        throw new IllegalStateException("Could not construct DMSSubAuthenticator for this package (" + packageName + ") because it's not properly integrated with MAP");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        throw new UnsupportedOperationException("asBinder is not supported in DMSSubAuthenticator");
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) {
        io.w(TAG, "DMS sub authenticator getAccountRemovalAllowed was called");
        Account account = new Account(str2, str);
        if (!this.bb.dm()) {
            io.dm(TAG);
        } else {
            io.i(TAG, "Generating local account removed broadcast.");
            String a2 = hu.a(this.o, account);
            this.D.j(this.o, a2);
            io.i(TAG, "Cleared local cookies in pre merge devices");
            l.a(this.o, this.bh.Q(a2), a2, account, this.o.getPackageName(), this.bh.a(this.o, a2), (Bundle) null);
        }
        try {
            iSubAuthenticatorResponse.onResult(GeneratedOutlineSupport1.outline13("booleanResult", true));
        } catch (RemoteException unused) {
            io.e(TAG, "RemoteException during getAccountRemovalAllowed in DMS sub authenticator");
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        s ahVar;
        ej by = ej.by("DMSSubAuthenticator:GetAuthToken");
        if (!AccountConstants.AMAZON_ACCOUNT_TYPE.equals(str)) {
            io.e(TAG, "An Attempt to retrieve a token for a non amazon account.");
            return;
        }
        String a2 = hu.a(this.o, new Account(str2, str));
        ISubAuthenticatorResponse a3 = a(iSubAuthenticatorResponse, a2, str3, by);
        if (iAmazonAccountAuthenticator != null) {
            ahVar = new ai(iAmazonAccountAuthenticator);
        } else {
            ahVar = new ah(this.o);
        }
        a(a3, ahVar, a2, by);
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        ej by = ej.by("DMSSubAuthenticator:UpdateAuthToken");
        io.i(TAG, "Updating DMS authentication tokens");
        String a2 = hu.a(this.o, new Account(str2, str));
        b(a(iSubAuthenticatorResponse, a2, str3, by), a2, by);
    }

    private void b(fz fzVar, a aVar, String str) {
        String str2 = TAG;
        "Local storeUserData: ".concat(String.valueOf(aVar));
        io.dm(str2);
        if (str == null) {
            io.i(TAG, String.format("Tried to set user data %s to null", aVar));
            return;
        }
        String str3 = aVar.bG;
        if (str3 != null) {
            fzVar.u(str3, str);
        }
        String str4 = aVar.bH;
        if (str4 == null) {
            return;
        }
        fzVar.v(str4, str);
    }

    public static u a(Context context, String str, String str2, Long l, String str3) {
        return new u(context, str3, str, str2, l);
    }

    public static void a(Context context, fz fzVar, String str, Map<String, String> map) {
        if (map != null && !map.isEmpty() && !TextUtils.isEmpty(str)) {
            if (!p.a(context, str)) {
                return;
            }
            new u(context, null, str, null, null).a(fzVar, str, map);
            return;
        }
        io.e(TAG, "The input device type or map is null or empty. Ignoring it.");
    }

    public void b(final ISubAuthenticatorResponse iSubAuthenticatorResponse, final String str, ej ejVar) {
        lm lmVar = new lm();
        Long l = this.bo;
        if (l != null) {
            lmVar.c(new mc(Long.toString(l.longValue())));
        }
        String str2 = this.bn;
        if (str2 != null) {
            lmVar.ei(str2);
        }
        kh khVar = new kh() { // from class: com.amazon.identity.auth.device.u.4
            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void a(Object obj) {
                io.i(u.TAG, "Update credential request succeeded");
                try {
                    u.a(u.this, iSubAuthenticatorResponse, (lb) obj, str);
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during update credentials call");
                }
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void k() {
                try {
                    io.e(u.TAG, "Update SubAuthenticator Credentials onNetworkFailure");
                    mq.incrementCounterAndRecord("NetworkError14:DMSSubAuthenticator", new String[0]);
                    iSubAuthenticatorResponse.onError(3, "Network failure");
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during network failure callback for updateCredentials");
                }
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void l() {
                try {
                    io.e(u.TAG, "Update SubAuthenticator Credentials onParseError");
                    iSubAuthenticatorResponse.onError(5, "Received bad response");
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during invalid response callback for updateCredentials");
                }
            }

            @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
            public void onAuthenticationFailed() {
                try {
                    io.e(u.TAG, "Authentication failure when updating the credentials for child app.");
                    iSubAuthenticatorResponse.onResult(u.this.a(103, "Authentication error during update credentials"));
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during authentication failure callback for updateCredentials");
                }
            }
        };
        this.bj.a(new br() { // from class: com.amazon.identity.auth.device.u.5
            @Override // com.amazon.identity.auth.device.kq
            public com.amazon.identity.auth.device.a y() {
                String z = u.this.w.z(str, u.this.bq.bH);
                String z2 = u.this.w.z(str, u.this.br.bH);
                if (TextUtils.isEmpty(z) || TextUtils.isEmpty(z2)) {
                    z = u.this.w.z(str, u.this.bq.bG);
                    z2 = u.this.w.z(str, u.this.br.bG);
                }
                return new com.amazon.identity.auth.device.a(z, z2);
            }

            @Override // com.amazon.identity.auth.device.br
            public boolean z() {
                return false;
            }
        }, ejVar).b(lmVar.ho(), new ln(), khVar).cD();
    }

    private ISubAuthenticatorResponse a(final ISubAuthenticatorResponse iSubAuthenticatorResponse, final String str, final String str2, final ej ejVar) {
        return new ISubAuthenticatorResponse() { // from class: com.amazon.identity.auth.device.u.1
            @Override // android.os.IInterface
            public IBinder asBinder() {
                return iSubAuthenticatorResponse.asBinder();
            }

            @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
            public void onError(int i, String str3) throws RemoteException {
                iSubAuthenticatorResponse.onError(i, str3);
                ej ejVar2 = ejVar;
                if (ejVar2 != null) {
                    ejVar2.eb();
                }
            }

            @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
            public void onResult(Bundle bundle) throws RemoteException {
                iSubAuthenticatorResponse.onResult(u.a(u.this, bundle, str, str2));
                ej ejVar2 = ejVar;
                if (ejVar2 != null) {
                    ejVar2.eb();
                }
            }
        };
    }

    public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, ej ejVar) {
        if (p.I(this.bk)) {
            io.e(TAG, String.format("An Attempt to register an invalid child device type: %s. This is due to wrong integration with MAP.", this.bk));
            try {
                iSubAuthenticatorResponse.onError(MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Child Application registration failed due to invalid child device type. This is due to wrong integration with MAP.");
            } catch (RemoteException unused) {
                io.e(TAG, "RemoteException on callback error for account not existing.");
            }
        } else if (!this.bg.D(str)) {
            io.e(TAG, "An Attempt to register a child device type for a non-existant amazon account. This can happen if the device has been deregistered during this flow.");
            try {
                iSubAuthenticatorResponse.onError(MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Child Application registration failed due to account not being registered on the device. This can happen if the device has been deregistered during this flow.");
            } catch (RemoteException unused2) {
                io.e(TAG, "RemoteException on callback error for account not existing.");
            }
        } else if (p.a(this.o, this.w, str, this.bk)) {
            io.i(TAG, String.format("Child Application device type %s is already registered", this.bk));
            a(iSubAuthenticatorResponse);
        } else {
            a(iSubAuthenticatorResponse, new ah(this.o), str, ejVar);
        }
    }

    private void a(final ISubAuthenticatorResponse iSubAuthenticatorResponse, s sVar, final String str, ej ejVar) {
        t tVar = new t() { // from class: com.amazon.identity.auth.device.u.2
            @Override // com.amazon.identity.auth.device.t
            public void c(lb lbVar) {
                try {
                    io.i(u.TAG, "Getting response for the child application registration. Storing results.");
                    u.a(u.this, iSubAuthenticatorResponse, lbVar, str);
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException when credentials was received for registerChildApplication");
                }
            }

            @Override // com.amazon.identity.auth.device.t
            public void onAuthenticationFailed() {
                try {
                    io.e(u.TAG, "Authentication error when registering the child app.");
                    iSubAuthenticatorResponse.onResult(u.this.a(103, "Authentication error during register"));
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during authentication failure callback for registerChildApplication");
                }
            }

            @Override // com.amazon.identity.auth.device.t
            public void onBadResponse() {
                try {
                    io.e(u.TAG, "Bad response when registering the child app.");
                    iSubAuthenticatorResponse.onError(5, "Received bad response");
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during invalid response callback for registerChildApplication");
                }
            }

            @Override // com.amazon.identity.auth.device.t
            public void onInvalidRequest() {
                try {
                    io.e(u.TAG, "Bad request when registering the child app.");
                    iSubAuthenticatorResponse.onError(8, "Received bad request");
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during bad request callback for registerChildApplication");
                }
            }

            @Override // com.amazon.identity.auth.device.t
            public void onNetworkError() {
                try {
                    io.e(u.TAG, "Network error when registering the child app.");
                    mq.incrementCounterAndRecord("NetworkError13:DMSSubAuthenticator", new String[0]);
                    iSubAuthenticatorResponse.onError(3, "Network error");
                } catch (RemoteException unused) {
                    io.e(u.TAG, "RemoteException during network failure callback for registerChildApplication");
                }
            }
        };
        md K = K(str);
        if (K != null) {
            sVar.a(str, K, tVar, ejVar);
        } else {
            io.e(TAG, "Could not construct a valid child application registration request");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(lb lbVar, String str, String str2) {
        String M = M(str);
        fz fzVar = new fz(str, new HashMap(), new HashMap(), this.bi);
        a(lbVar, fzVar, str2, M);
        this.bi.a(fzVar);
    }

    private void a(lb lbVar, fz fzVar, String str, String str2) {
        a(fzVar, this.bq, lbVar.hL());
        a(fzVar, this.br, lbVar.a());
        a(fzVar, this.bv, lbVar.hP());
        a(fzVar, this.bw, str);
        b(fzVar, this.bs, this.bk);
        b(fzVar, this.bt, str2);
        b(fzVar, this.bu, lbVar.getEmail());
        b(fzVar, this.bx, lbVar.getDeviceName());
        b(fzVar, this.by, lbVar.getUserName());
        b(fzVar, this.bz, lbVar.hO());
        b(fzVar, this.bA, lbVar.cb());
        p.a(this.o.dW(), fzVar, this.bk, this.bm, this.bl);
    }

    private void a(fz fzVar, a aVar, String str) {
        String str2 = TAG;
        "Local storeToken: ".concat(String.valueOf(aVar));
        io.dm(str2);
        if (str == null) {
            io.i(TAG, String.format("Tried to set token %s to null", aVar));
            return;
        }
        String str3 = aVar.bG;
        if (str3 != null) {
            fzVar.u(str3, str);
        }
        String str4 = aVar.bH;
        if (str4 == null) {
            return;
        }
        fzVar.u(str4, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle a(int i, String str) {
        io.e(TAG, "Error gettingAuthToken ".concat(String.valueOf(str)));
        Bundle bundle = new Bundle();
        bundle.putInt(AccountConstants.KEY_DMS_ERROR_CODE, i);
        bundle.putString(AccountConstants.KEY_DMS_ERROR_MESSAGE, str);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse) {
        try {
            iSubAuthenticatorResponse.onResult(p.x());
        } catch (RemoteException e) {
            io.e(TAG, "Error Callback Success", e);
        }
    }

    public void a(fz fzVar, String str, Map<String, String> map) {
        lb lbVar = null;
        if (map != null && !map.isEmpty()) {
            String str2 = map.get("adp_token");
            String str3 = map.get("device_private_key");
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                String str4 = map.get("store_authentication_cookie");
                if (TextUtils.isEmpty(str4)) {
                    io.i(TAG, "The batch registration did not return store auth cookie for device type: ".concat(String.valueOf(str)));
                }
                String str5 = map.get("user_device_name");
                if (TextUtils.isEmpty(str5)) {
                    str5 = fzVar.cb("com.amazon.dcp.sso.property.devicename");
                    String str6 = TAG;
                    String.format("Using the device name: %s of central device type for child device type: %s", str5, str);
                    io.dm(str6);
                }
                String str7 = str5;
                String str8 = map.get("kindle_email_address");
                if (TextUtils.isEmpty(str8)) {
                    str8 = fzVar.cb("com.amazon.dcp.sso.property.deviceemail");
                    String str9 = TAG;
                    String.format("Using the device email: %s of central device type for child device type: %s", str8, str);
                    io.dm(str9);
                }
                String str10 = str8;
                String str11 = map.get("name");
                if (TextUtils.isEmpty(str11)) {
                    str11 = fzVar.cb("com.amazon.dcp.sso.property.username");
                    String str12 = TAG;
                    String.format("Using the username: %s of central device type for child device type: %s", str11, str);
                    io.dm(str12);
                }
                String str13 = str11;
                String str14 = map.get("given_name");
                if (TextUtils.isEmpty(str14)) {
                    str14 = fzVar.cb("com.amazon.dcp.sso.property.firstname");
                    String str15 = TAG;
                    String.format("Using the first name: %s of central device type for child device type: %s", str14, str);
                    io.dm(str15);
                }
                String str16 = str14;
                String str17 = map.get("account_pool");
                if (TextUtils.isEmpty(str17)) {
                    str17 = fzVar.cb("com.amazon.dcp.sso.token.device.accountpool");
                    String str18 = TAG;
                    String.format("Using the account pool: %s of central device type for child device type: %s", str17, str);
                    io.dm(str18);
                }
                lb lbVar2 = new lb(str2, str7, str3, str13, str16, str10);
                lbVar2.et(str4);
                lbVar2.j(str17);
                lbVar = lbVar2;
            } else {
                io.e(TAG, "The pre-populated credential map does not have valid ADP credentials, ignoring it for device type: ".concat(String.valueOf(str)));
                String str19 = TAG;
                io.e(str19, "The pre-populated credential map contains the following  invalid key: " + map.keySet());
            }
        } else {
            io.e(TAG, "The pre-populated credential map does not have any valid data, ignoring it for device type: ".concat(String.valueOf(str)));
        }
        if (lbVar == null) {
            return;
        }
        io.i(TAG, String.format("There are %d pre-populated tokens for child device type: %s", Integer.valueOf(map.size()), str));
        Iterator<String> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            String str20 = TAG;
            String.format("Pre-populating the token: %s for child device type: %s", it2.next(), str);
            io.dm(str20);
        }
        a(lbVar, fzVar, fzVar.ca(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN), fzVar.cb("com.amazon.dcp.sso.token.device.deviceserialname"));
    }

    static /* synthetic */ Bundle a(u uVar, Bundle bundle, String str, String str2) {
        if (bundle.containsKey(AccountConstants.KEY_DMS_ERROR_CODE)) {
            return bundle;
        }
        String z = uVar.w.z(str, str2);
        if (z == null) {
            return uVar.a(104, "Requested token type was not found in authenticator cache.");
        }
        if (bf.equals(str2)) {
            ax.c(uVar.o, str, z);
        }
        bundle.putString("authtoken", z);
        return bundle;
    }

    static /* synthetic */ void a(u uVar, final ISubAuthenticatorResponse iSubAuthenticatorResponse, final lb lbVar, final String str) throws RemoteException {
        if (lbVar == null) {
            iSubAuthenticatorResponse.onError(5, "Could not parse response");
        } else if (lbVar.hQ() != null) {
            la hQ = lbVar.hQ();
            Bundle bundle = null;
            switch (AnonymousClass6.aI[hQ.hz().ordinal()]) {
                case 1:
                    bundle = uVar.a(100, "Invalid username or password");
                    break;
                case 2:
                    bundle = uVar.a(101, "Device already registered to another user.");
                    break;
                case 3:
                    bundle = uVar.a(102, "Duplicate device name");
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    iSubAuthenticatorResponse.onError(5, "Unrecognized Response " + hQ.hz().getErrorString());
                    break;
                default:
                    iSubAuthenticatorResponse.onError(5, "Invalid Response: " + hQ.hz().getErrorString());
                    break;
            }
            if (bundle == null) {
                return;
            }
            iSubAuthenticatorResponse.onResult(bundle);
        } else {
            final String fb = new gf(lbVar.hN()).fb();
            if (ji.gP()) {
                ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.u.3
                    @Override // java.lang.Runnable
                    public void run() {
                        u.this.a(lbVar, str, fb);
                        if (iSubAuthenticatorResponse != null) {
                            io.i(u.TAG, "Callback with success after storing tokens for the child app.");
                            u.this.a(iSubAuthenticatorResponse);
                        }
                    }
                });
                return;
            }
            uVar.a(lbVar, str, fb);
            if (iSubAuthenticatorResponse == null) {
                return;
            }
            io.i(TAG, "Callback with success after storing tokens for the child app.");
            uVar.a(iSubAuthenticatorResponse);
        }
    }
}
