package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.hg;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.AtzTokenManager;
import com.amazon.identity.auth.device.token.MobileAuthEncryptionKeyManager;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hj implements hh {
    private static final String TAG = "com.amazon.identity.auth.device.hj";
    private static hj qo;
    private final OAuthTokenManager B;
    private final ha ay;
    private volatile boolean hk;
    private final ed o;
    private final gm pA;
    private final hd pB;
    private final AtzTokenManager qp;
    private final MobileAuthEncryptionKeyManager qq;
    private final ip qs;
    private final bm qr = new bm();
    private final hg qt = hg.gd();

    hj(Context context) {
        this.o = ed.M(context);
        this.pA = new gm(this.o, new BackwardsCompatiableDataStorage(this.o));
        this.B = new OAuthTokenManager(context);
        this.qp = new AtzTokenManager(context);
        this.ay = new ha(context);
        this.qq = new MobileAuthEncryptionKeyManager(context);
        this.pB = new hd(this.o, this.pA);
        this.qs = new ip(context);
    }

    public static synchronized hj ae(Context context) {
        hj hjVar;
        synchronized (hj.class) {
            if (qo == null || jk.gR()) {
                generateNewInstance(context);
            }
            hjVar = qo;
        }
        return hjVar;
    }

    public static void generateNewInstance(Context context) {
        qo = new hj(context.getApplicationContext());
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(final Context context, final String str, final String str2, final String str3, final String str4, final Bundle bundle, Callback callback, final ej ejVar) {
        String format;
        String str5 = TAG;
        "Getting token for actor ".concat(String.valueOf(str2));
        io.dm(str5);
        bl blVar = new bl(callback);
        if (bundle == null) {
            new Bundle();
        }
        this.hk = false;
        if (this.hk) {
            return blVar;
        }
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "Account Id used in getTokenForActor is null or empty");
            MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
            hf.a(blVar, commonError, commonError.getErrorMessage(), 8, "Account Id used in getTokenForActor is null or empty");
            return blVar;
        } else if (TextUtils.isEmpty(str2)) {
            io.e(TAG, "Actor Id used in getTokenForActor is null or empty");
            MAPError.CommonError commonError2 = MAPError.CommonError.BAD_REQUEST;
            hf.a(blVar, commonError2, commonError2.getErrorMessage(), 8, "Actor Id used in getTokenForActor is null or empty");
            return blVar;
        } else if (TextUtils.isEmpty(str3)) {
            io.e(TAG, "Token type used in getTokenForActor is null or empty.");
            MAPError.CommonError commonError3 = MAPError.CommonError.BAD_REQUEST;
            hf.a(blVar, commonError3, commonError3.getErrorMessage(), 8, "Token type used in getTokenForActor is null or empty.");
            return blVar;
        } else {
            String valueOf = String.valueOf(this.qt.ge());
            bm bmVar = this.qr;
            if (TextUtils.isEmpty(str4)) {
                format = String.format(Locale.ENGLISH, "%s#%s#%s", str, str2, str3);
            } else {
                format = String.format(Locale.ENGLISH, "%s#%s#%s#%s", str, str2, str3, "failure_context_flag");
            }
            if (!TextUtils.isEmpty(valueOf)) {
                format = GeneratedOutlineSupport1.outline75(valueOf, MqttTopic.MULTI_LEVEL_WILDCARD, format);
            }
            Callback b = bmVar.b(format, blVar);
            if (b == null) {
                String str6 = TAG;
                String.format("Get actor token for type %s is already in flight.", str3);
                io.dm(str6);
            } else {
                this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.2
                    @Override // com.amazon.identity.auth.device.hg.d
                    public void g(Callback callback2) {
                        hj.this.b(context, str, str2, str3, str4, bundle, callback2, ejVar);
                    }

                    @Override // com.amazon.identity.auth.device.hg.d
                    public String gi() {
                        return "GetActorToken:" + str3;
                    }

                    @Override // com.amazon.identity.auth.device.hg.d
                    public boolean gj() {
                        return false;
                    }
                }, b);
            }
            return blVar;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v7, types: [com.amazon.identity.auth.device.im] */
    /* JADX WARN: Type inference failed for: r15v8, types: [com.amazon.identity.auth.device.im] */
    void b(Context context, String str, String str2, String str3, String str4, Bundle bundle, Callback callback, ej ejVar) {
        im imVar;
        Bundle bundle2;
        String str5;
        String str6;
        im imVar2 = "Cannot get cookies before launching the challenge UI";
        this.qs.gM();
        im dl = im.dl(str3);
        MAPApplicationInformationQueryer.E(this.o).br(dl.getPackageName());
        try {
            String str7 = TAG;
            "GetActorToken: ".concat(str3);
            io.dm(str7);
            try {
                if ("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token".equals(dl.getKey())) {
                    Bundle bundle3 = bundle == null ? new Bundle() : bundle;
                    try {
                        bundle2 = bundle3;
                        str6 = imVar2;
                        imVar2 = dl;
                        str5 = "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token";
                        try {
                            hf.a(callback, this.B.a(context, str, str2, dl, str4, bundle2, ejVar), this.B.b(str, str2, (im) imVar2));
                            imVar = imVar2;
                        } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                            e = e;
                            String errorMessage = e.getErrorMessage();
                            if (MAPError.AccountError.AUTHENTICATION_CHALLENGED.equals(e.getError()) && lm.eA(errorMessage) && context != null) {
                                Bundle bundle4 = bundle2;
                                bundle4.putString(str5, imVar2.gA());
                                io.i(TAG, "Opening webview to handle actor token exchange challenge.");
                                Intent t = ii.t(context, AuthChallengeHandleActivity.class.getName());
                                if (ejVar != null) {
                                    ejVar.e(t);
                                }
                                if (t != null) {
                                    bundle4.putString("account_id", str);
                                    bundle4.putString("actor_id", str2);
                                    bundle4.putString("challenge_url", errorMessage);
                                    try {
                                        this.B.b(str, str2, bundle4.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN), bundle4, ejVar);
                                        t.putExtras(bundle4);
                                        t.putExtra("callback", new RemoteCallbackWrapper(callback));
                                        if (!(context instanceof Activity)) {
                                            t.addFlags(268435456);
                                        }
                                        context.startActivity(t);
                                        imVar = imVar2;
                                    } catch (OAuthTokenManager.OAuthTokenManagerException e2) {
                                        String str8 = str6;
                                        io.e(TAG, str8);
                                        MAPErrorCallbackHelper.onError(callback, e2.getError(), str8);
                                        imVar = imVar2;
                                    }
                                } else {
                                    throw new RuntimeException("No activity can handle the intent. Probably because you do not declare AuthChallengeHandleActivity in android manifest");
                                }
                            } else {
                                io.e(TAG, String.format(Locale.ENGLISH, "Received an error when calling getActorAccessToken. ErrorCode: %d ErrorMessage: %s ", Integer.valueOf(e.getError().getErrorCode()), e.getError().getErrorMessage()));
                                hf.a(callback, e.getError(), e.getErrorMessage(), e);
                                imVar = imVar2;
                            }
                            MAPApplicationInformationQueryer.E(this.o).bs(imVar.getPackageName());
                        }
                    } catch (OAuthTokenManager.OAuthTokenManagerException e3) {
                        e = e3;
                        bundle2 = bundle3;
                        str5 = "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token";
                        str6 = imVar2;
                        imVar2 = dl;
                    }
                } else {
                    imVar = dl;
                    MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
                    hf.d(callback, commonError, commonError.getErrorMessage(), 7, "Key for getting actor token is not recognized");
                }
                MAPApplicationInformationQueryer.E(this.o).bs(imVar.getPackageName());
            } catch (Throwable th) {
                th = th;
                MAPApplicationInformationQueryer.E(this.o).bs(imVar2.getPackageName());
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            imVar2 = dl;
        }
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> e(final String str, final String str2, final Bundle bundle, Callback callback, final ej ejVar) {
        String str3 = TAG;
        "Getting token ".concat(String.valueOf(str2));
        io.dm(str3);
        bl blVar = new bl(callback);
        this.hk = false;
        if (this.hk) {
            return blVar;
        }
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "Directed Id used in getToken is null or empty");
            hf.a(blVar, MAPError.CommonError.BAD_REQUEST, "Directed Id used in getToken is null or empty", 8, "Directed Id used in getToken is null or empty");
            return blVar;
        } else if (TextUtils.isEmpty(str2)) {
            io.e(TAG, "Token key used in getToken is null or empty.");
            hf.a(blVar, MAPError.CommonError.BAD_REQUEST, "Token key used in getToken is null or empty.", 8, "Token key used in getToken is null or empty.");
            return blVar;
        } else {
            String valueOf = String.valueOf(this.qt.ge());
            bm bmVar = this.qr;
            String format = String.format(Locale.ENGLISH, "%s#%s", str, str2);
            if (!TextUtils.isEmpty(valueOf)) {
                format = GeneratedOutlineSupport1.outline75(valueOf, MqttTopic.MULTI_LEVEL_WILDCARD, format);
            }
            Callback b = bmVar.b(format, blVar);
            if (b == null) {
                String str4 = TAG;
                String.format("Get token for type %s is already in flight.", str2);
                io.dm(str4);
            } else {
                this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.1
                    @Override // com.amazon.identity.auth.device.hg.d
                    public void g(Callback callback2) {
                        hj.this.h(str, str2, bundle, callback2, ejVar);
                    }

                    @Override // com.amazon.identity.auth.device.hg.d
                    public String gi() {
                        return "GetToken:" + str2;
                    }

                    @Override // com.amazon.identity.auth.device.hg.d
                    public boolean gj() {
                        return false;
                    }
                }, b);
            }
            return blVar;
        }
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> f(final String str, final String str2, Bundle bundle, Callback callback, final ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        this.hk = false;
        if (this.hk) {
            return blVar;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        final Bundle bundle2 = bundle;
        this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.3
            @Override // com.amazon.identity.auth.device.hg.d
            public void g(Callback callback2) {
                hj.this.i(str, str2, bundle2, callback2, ejVar);
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public String gi() {
                return "GetCookies:" + str2;
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public boolean gj() {
                return false;
            }
        }, blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> g(final String str, final String str2, final Bundle bundle, Callback callback, ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.4
            @Override // com.amazon.identity.auth.device.hg.d
            public void g(Callback callback2) {
                hj.a(hj.this, str, str2, bundle, callback2);
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public String gi() {
                return "invalidateCookies:" + str2;
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public boolean gj() {
                return false;
            }
        }, blVar);
        return blVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c0 A[Catch: all -> 0x02ff, TRY_LEAVE, TryCatch #8 {all -> 0x02ff, blocks: (B:3:0x0016, B:5:0x002e, B:13:0x003f, B:24:0x0066, B:27:0x0089, B:29:0x0091, B:30:0x00a0, B:33:0x00af, B:15:0x0047, B:17:0x004f, B:19:0x0055, B:34:0x00c0, B:37:0x00cd, B:46:0x0120, B:48:0x012c, B:52:0x0162, B:54:0x016e, B:65:0x01fe, B:67:0x020a, B:68:0x0219, B:71:0x0227, B:76:0x0234, B:81:0x0266, B:79:0x0240, B:84:0x0286, B:86:0x02a9, B:87:0x02cf, B:89:0x02e9, B:90:0x02fa, B:57:0x0188, B:60:0x01b7, B:61:0x01bc, B:63:0x01dd, B:64:0x01e9, B:51:0x013c, B:41:0x00da, B:42:0x00df, B:44:0x0100, B:45:0x010d), top: B:103:0x0016, inners: #1, #3, #6, #7, #9, #8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void h(final java.lang.String r11, java.lang.String r12, android.os.Bundle r13, com.amazon.identity.auth.device.api.Callback r14, final com.amazon.identity.auth.device.ej r15) {
        /*
            Method dump skipped, instructions count: 782
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.hj.h(java.lang.String, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):void");
    }

    void i(String str, String str2, Bundle bundle, Callback callback, ej ejVar) {
        try {
            this.qs.gM();
            callback.onSuccess(this.ay.a(str, str2, bundle, ejVar));
        } catch (MAPCallbackErrorException e) {
            callback.onError(e.getErrorBundle());
        }
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(final String str, final String str2, final String str3, Bundle bundle, Callback callback, final ej ejVar) {
        io.dm(TAG);
        bl blVar = new bl(callback);
        this.hk = false;
        if (this.hk) {
            return blVar;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        final Bundle bundle2 = bundle;
        this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.5
            @Override // com.amazon.identity.auth.device.hg.d
            public void g(Callback callback2) {
                hj.this.b(str, str2, str3, bundle2, callback2, ejVar);
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public String gi() {
                return "GetActorCookies:" + str3;
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public boolean gj() {
                return false;
            }
        }, blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.hh
    public MAPFuture<Bundle> a(final String str, final String str2, final Bundle bundle, Callback callback, gy gyVar, final ej ejVar) {
        io.i(TAG, "Upgrade token for account or actor.");
        bl blVar = new bl(callback);
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "Account Id in upgradeToken is null or empty");
            MAPErrorCallbackHelper.onError(blVar, MAPError.CommonError.BAD_REQUEST, "Account Id in upgradeToken is null or empty");
            return blVar;
        }
        final String string = bundle.getString("key_auth_code");
        if (TextUtils.isEmpty(string)) {
            io.e(TAG, "AuthCode used in upgradeToken is null or empty");
            MAPErrorCallbackHelper.onError(blVar, MAPError.CommonError.BAD_REQUEST, "AuthCode used in upgradeToken is null or empty");
            return blVar;
        }
        this.qt.a(new hg.d() { // from class: com.amazon.identity.auth.device.hj.6
            @Override // com.amazon.identity.auth.device.hg.d
            public void g(Callback callback2) {
                hj.this.a(str, str2, bundle.getString("key_token_type"), string, callback2, ejVar, bundle);
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public String gi() {
                return "UpgradeToken";
            }

            @Override // com.amazon.identity.auth.device.hg.d
            public boolean gj() {
                return true;
            }
        }, blVar);
        if (gyVar != null) {
            io.i(TAG, "Finish listener upon enqueuing.");
            gyVar.onFinish(new Bundle());
        }
        return blVar;
    }

    void b(String str, String str2, String str3, Bundle bundle, Callback callback, ej ejVar) {
        try {
            this.qs.gM();
            callback.onSuccess(this.ay.a(str, str2, str3, bundle, ejVar));
        } catch (MAPCallbackErrorException e) {
            callback.onError(e.getErrorBundle());
        }
    }

    void a(@NonNull String str, String str2, String str3, @NonNull String str4, @NonNull Callback callback, @NonNull ej ejVar, Bundle bundle) {
        try {
            if ("token_type_oauth_refresh_token".equals(str3)) {
                this.B.a(str, str2, str4, callback, ejVar, bundle);
                return;
            }
            io.e(TAG, "Upgrade non OAuth refresh token is not supported.");
            MAPErrorCallbackHelper.onError(callback, MAPError.CommonError.FEATURE_NOT_IMPLEMENTED);
        } catch (OAuthTokenManager.OAuthTokenManagerException e) {
            MAPErrorCallbackHelper.onError(callback, e.getError(), e.getErrorMessage());
        }
    }

    static /* synthetic */ void a(hj hjVar, String str, String str2, Bundle bundle, Callback callback) {
        Bundle bundle2 = new Bundle();
        gp gpVar = new gp(hjVar.o, "token_storage");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CookieKeys.KEY_INVALIDATE_COOKIES);
        outline107.append(hjVar.o.getPackageName());
        outline107.append(str);
        outline107.append(str2);
        if (bundle.containsKey(CookieKeys.Options.KEY_INVALIDATE_ACTOR_COOKIES)) {
            outline107.append(bundle.getString(CookieKeys.Options.KEY_INVALIDATE_ACTOR_COOKIES));
        }
        String str3 = TAG;
        new StringBuilder("invalidateCookiesKey: ").append(outline107.toString());
        io.dm(str3);
        boolean b = gpVar.b(outline107.toString(), Boolean.TRUE);
        bundle2.putBoolean(CookieKeys.KEY_INVALIDATE_COOKIES, b);
        if (b) {
            callback.onSuccess(bundle2);
        } else {
            callback.onError(bundle2);
        }
    }

    static /* synthetic */ Callback a(hj hjVar) {
        return new Callback() { // from class: com.amazon.identity.auth.device.hj.8
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                io.e(hj.TAG, "Registration check failed. This does not mean the device deregistered, this can happen if the network call failed.  Also this will not ever be raised to an application calling one of our apis as this is a background task to check the serverside registration state.");
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                io.i(hj.TAG, "Registration check succeeded.");
            }
        };
    }
}
