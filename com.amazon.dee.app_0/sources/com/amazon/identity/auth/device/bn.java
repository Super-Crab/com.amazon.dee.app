package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.bp;
import com.amazon.identity.auth.device.c;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bn {
    private static final String TAG = "bn";
    private final OAuthTokenManager B;
    private final TokenManagement au;
    private final MAPAccountManager av;
    private final f hj;
    private volatile boolean hk = false;
    private final Context mContext;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public final String hs;
        public final String ht;
        public final long hu;
        public final long hv;

        a(String str, String str2, long j, long j2) {
            this.hs = str;
            this.ht = str2;
            this.hu = j - System.currentTimeMillis();
            this.hv = j2;
        }
    }

    public bn(Context context) {
        this.mContext = context;
        this.au = new TokenManagement(this.mContext);
        this.av = new MAPAccountManager(this.mContext);
        this.hj = g.a(this.mContext);
        this.B = new OAuthTokenManager(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aI(String str) {
        try {
            return this.au.getToken(str, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, new Bundle(), new DefaultCallback()).get().getString("value_key");
        } catch (Exception e) {
            io.e(TAG, "Exception while trying to get the refresh token in the authorizeLinkCode API", e);
            return null;
        }
    }

    private void k(String str, final String str2) {
        this.av.deregisterAccount(str, new DefaultCallback() { // from class: com.amazon.identity.auth.device.bn.3
            @Override // com.amazon.identity.auth.device.api.DefaultCallback, com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
                String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                String str3 = bn.TAG;
                io.i(str3, "Error while deregistering account in " + str2 + " flow. ErrorCode:" + i + " ErrorMessage:" + string);
            }

            @Override // com.amazon.identity.auth.device.api.DefaultCallback, com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String str3 = bn.TAG;
                io.i(str3, "Successfully completed deregistering account in " + str2 + " flow");
            }
        });
    }

    public static a q(Context context) {
        int i;
        gp gpVar = new gp(context, "cbl_storage");
        String cs = gpVar.cs("public_code");
        String cs2 = gpVar.cs("private_code");
        long cv = gpVar.cv("expires_at");
        long cv2 = gpVar.cv("polling_interval");
        if (TextUtils.isEmpty(cs) || TextUtils.isEmpty(cs2) || cv == 0 || cv2 == 0) {
            io.i(TAG, "Cannot find existing code pair in storage");
            return null;
        }
        if (i > 0) {
            if (cv > System.currentTimeMillis() + 60000) {
                io.i(TAG, "Returning already existing public code");
                return new a(cs, cs2, cv, cv2);
            }
            gpVar.fE();
        }
        return null;
    }

    public void d(final Bundle bundle, Callback callback, final ej ejVar) {
        final String string = bundle.getString(MAPAccountManager.KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            io.dm(TAG);
        }
        h.m.a(new c.b() { // from class: com.amazon.identity.auth.device.bn.1
            @Override // com.amazon.identity.auth.device.c.b
            public Bundle a(Callback callback2) {
                a q = bn.q(bn.this.mContext);
                if (q == null) {
                    bn.a(bn.this, string, bundle, new gp(bn.this.mContext, "cbl_storage"), callback2, ejVar);
                    return null;
                }
                bn.a(q.hs, MAPAccountManager.KEY_LINK_CODE, q.hu, Long.valueOf(q.hv), callback2);
                return null;
            }
        }, callback, "generateLinkCode");
    }

    public void e(final Bundle bundle, Callback callback, final ej ejVar) {
        final String string = bundle.getString(MAPAccountManager.KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            io.dm(TAG);
        }
        final String string2 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (TextUtils.isEmpty(string2)) {
            a(2, "Required value DirectedID(MAPAccountManager.KEY_DIRECTED_ID) is missing for the API generatePreAuthorizedLinkCode", callback, (Bundle) null);
            return;
        }
        a(string2, "generate pre-authorized code", "GeneratePreAuthorizedCode:CredentialError", callback);
        if (this.hk) {
            return;
        }
        final long j = bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, 0L);
        final String string3 = bundle.getString(MAPAccountManager.KEY_ACTOR_ID_FOR_PREAUTHORIZED_LINK_CODE);
        h.m.a(new c.b() { // from class: com.amazon.identity.auth.device.bn.2
            @Override // com.amazon.identity.auth.device.c.b
            public Bundle a(Callback callback2) {
                String a2;
                if (!bn.this.hj.isAccountRegistered(string2)) {
                    bn.a(3, "The directedID passed in the generatePreAuthorizedLinkCode API is not registered on this device", callback2);
                    return null;
                }
                if (TextUtils.isEmpty(string3)) {
                    io.i(bn.TAG, "Generating refresh token");
                    a2 = bn.this.aI(string2);
                } else {
                    io.i(bn.TAG, "Generating refresh token for actor id");
                    a2 = bn.this.a(string2, string3, ejVar);
                }
                String str = a2;
                if (TextUtils.isEmpty(str)) {
                    bn.a(1, "Could not get the auth token for the customer to authorize the link code", callback2);
                    return null;
                }
                bn.a(bn.this, string2, string, str, j, string3, callback2, ejVar);
                return null;
            }
        }, callback, "generatePreAuthorizedLinkCode");
    }

    public void f(final Bundle bundle, Callback callback, final ej ejVar) {
        final String string = bundle.getString(MAPAccountManager.KEY_LINK_CODE_DOMAIN);
        if (TextUtils.isEmpty(string)) {
            io.dm(TAG);
        }
        final String string2 = bundle.getString(MAPAccountManager.KEY_LINK_CODE);
        if (TextUtils.isEmpty(string2)) {
            a(2, "Required value MAPAccountManager.KEY_LINK_CODE is missing for the API authorizeLinkCode", callback, (Bundle) null);
            return;
        }
        final String string3 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (TextUtils.isEmpty(string3)) {
            a(2, "Required value DirectedID(MAPAccountManager.KEY_AUTHORIZE_LINK_CODE_DIRECTED_ID) is missing for the API authorizeLinkCode", callback, (Bundle) null);
            return;
        }
        a(string3, "authorize the link code", "AuthorizePublicCode:CredentialError", callback);
        if (this.hk) {
            return;
        }
        h.m.a(new c.b() { // from class: com.amazon.identity.auth.device.bn.4
            @Override // com.amazon.identity.auth.device.c.b
            public Bundle a(Callback callback2) {
                if (!bn.this.hj.isAccountRegistered(string3)) {
                    bn.a(3, "The directedID passed in the authorizeLinkCode API is not registered on this device", callback2);
                    return null;
                }
                String aI = bn.this.aI(string3);
                if (TextUtils.isEmpty(aI)) {
                    bn.a(1, "Could not get the auth token for the customer to authorize the link code", callback2);
                    return null;
                }
                bn.a(bn.this, string3, string, string2, aI, callback2, ejVar);
                return null;
            }
        }, callback, "authorizeLinkCode");
    }

    boolean b(ds dsVar) {
        return !dsVar.m4061do();
    }

    private void b(String str, String str2, String str3, Callback callback) {
        fp bU = fp.eB().bT(str).bU(str3);
        a(3, "Could not" + str2 + " because the account credentials that MAP had were invalid. This happens if the account was deregistered from the server side. Returning an account recover context to help recover the account", callback, bU.eC());
    }

    public static void a(String str, String str2, long j, Long l, Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString(str2, str);
        bundle.putLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, j);
        if (l != null) {
            bundle.putLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL, l.longValue());
        }
        callback.onSuccess(bundle);
    }

    boolean a(bp.a aVar) {
        return TextUtils.equals(aVar.mErrorCode, "CredentialError");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, ej ejVar) {
        try {
            return this.B.e(str, str2, ejVar);
        } catch (Exception e) {
            io.e(TAG, "Exception while trying to get the actor refresh token in the generatePreAuthorizedLinkCode API", e);
            return null;
        }
    }

    public static void a(int i, String str, Callback callback, Bundle bundle) {
        io.e(TAG, str);
        io.dm(TAG);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle2.putString("com.amazon.dcp.sso.ErrorMessage", str);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        callback.onError(bundle2);
    }

    public static void a(int i, String str, Callback callback) {
        a(i, str, callback, (Bundle) null);
    }

    public static bl a(final Context context, final bl blVar) {
        return new bl() { // from class: com.amazon.identity.auth.device.bn.5
            @Override // com.amazon.identity.auth.device.bl, com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                super.onError(bundle);
                if (bundle.getInt(MAPError.KEY_ERROR_CODE) == MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED.getErrorCode()) {
                    io.i(bn.TAG, "Cleaning CBL code for ACCOUNT_ALREADY_REGISTERED error.");
                    new gp(context, "cbl_storage").fE();
                }
                io.i(bn.TAG, "Register with link code was not successful.");
                blVar.onError(bundle);
            }

            @Override // com.amazon.identity.auth.device.bl, com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                super.onSuccess(bundle);
                io.i(bn.TAG, "Register with link code was successful. Clearing the cbl data in MAP");
                new gp(context, "cbl_storage").fE();
                blVar.onSuccess(bundle);
            }
        };
    }

    private static HttpsURLConnection a(URL url, String str) throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) jw.b((HttpsURLConnection) ew.c(url));
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setRequestProperty("Accept", "application/json");
        httpsURLConnection.setRequestProperty("Content-Type", "application/json");
        httpsURLConnection.setRequestProperty("Accept-Language", lz.a(Locale.getDefault()));
        httpsURLConnection.setRequestProperty("x-amzn-identity-auth-domain", EnvironmentUtils.cd().bf(str));
        return httpsURLConnection;
    }

    private void a(String str, String str2, String str3, Callback callback) {
        this.hk = false;
        if (this.hk) {
            b(str, str2, str3, callback);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.amazon.identity.auth.device.bn r16, java.lang.String r17, android.os.Bundle r18, com.amazon.identity.auth.device.gp r19, com.amazon.identity.auth.device.api.Callback r20, com.amazon.identity.auth.device.ej r21) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.bn.a(com.amazon.identity.auth.device.bn, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.gp, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0153  */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v5, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.amazon.identity.auth.device.env.EnvironmentUtils] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.amazon.identity.auth.device.bn r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, long r19, java.lang.String r21, com.amazon.identity.auth.device.api.Callback r22, com.amazon.identity.auth.device.ej r23) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.bn.a(com.amazon.identity.auth.device.bn, java.lang.String, java.lang.String, java.lang.String, long, java.lang.String, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015f  */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v5, types: [javax.net.ssl.HttpsURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.amazon.identity.auth.device.bn r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, com.amazon.identity.auth.device.api.Callback r11, com.amazon.identity.auth.device.ej r12) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.bn.a(com.amazon.identity.auth.device.bn, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):void");
    }
}
