package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.az;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.co;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.eh;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.fp;
import com.amazon.identity.auth.device.fr;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.ft;
import com.amazon.identity.auth.device.fv;
import com.amazon.identity.auth.device.gm;
import com.amazon.identity.auth.device.gp;
import com.amazon.identity.auth.device.gv;
import com.amazon.identity.auth.device.h;
import com.amazon.identity.auth.device.ha;
import com.amazon.identity.auth.device.hb;
import com.amazon.identity.auth.device.hc;
import com.amazon.identity.auth.device.hd;
import com.amazon.identity.auth.device.hk;
import com.amazon.identity.auth.device.hr;
import com.amazon.identity.auth.device.ht;
import com.amazon.identity.auth.device.ie;
import com.amazon.identity.auth.device.ik;
import com.amazon.identity.auth.device.im;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ip;
import com.amazon.identity.auth.device.iy;
import com.amazon.identity.auth.device.je;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.jk;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.w;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class OAuthTokenManager {
    private final eh F;
    private final AuthEndpointErrorParser aU;
    private final co ax;
    private final MAPAccountManager dY;
    private final iy hO;
    private final hc ng;
    private final ed o;
    private final gm pA;
    private final hd pB;
    private final ht pC;
    private final ip pD;
    private final hb pE;
    private final hk po;
    private final w z;
    private static final long px = jj.d(1, TimeUnit.MILLISECONDS);
    private static final long pn = jj.d(1, TimeUnit.MILLISECONDS);
    private static final Set<AuthTokenExchangeType> py = EnumSet.allOf(AuthTokenExchangeType.class);
    private static final Set<String> pz = new HashSet(Arrays.asList("A4ZP7ZC4PI6TO", "A1Z88NGR2BK6A2", "A15996VY63BQ2D", "A1XWJRHALS1REP"));

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum AuthTokenExchangeType {
        DMSTokenToOauthTokenExchange("exchangeDMSCredentialsForOAuthTokenFailure"),
        OauthRefreshToAccessExchange("refreshNormalOAuthTokenFailure"),
        OauthRefreshToCookieExchange("fetchCookiesFromServerFailure"),
        OauthRefreshToDelegationAccessExchange("refreshDelegatedOAuthTokenFailure"),
        AuthorizationCodeToOAuthAccessTokenExchange("authorizationCodeToAccessTokenFailure");
        
        final String mFailureMetric;

        AuthTokenExchangeType(String str) {
            this.mFailureMetric = str;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        final String bk;
        final String mAccessToken;
        final String ne;
        final int pH;
        final String pI;

        public a(String str, int i, String str2) {
            this(str, i, str2, null, null);
        }

        public a(String str, int i, String str2, String str3, String str4) {
            this.mAccessToken = str;
            this.pH = i;
            this.pI = str3;
            this.ne = str2;
            this.bk = str4;
        }
    }

    public OAuthTokenManager(Context context) {
        this(context, (eh) ed.M(context).getSystemService("dcp_system"), new gm(context), new iy(), new MAPAccountManager(context), new w(), new hb(ed.M(context), new iy()), new ip(context), new hd(ed.M(context), new gm(context)), new hc(context));
    }

    private static String ac(String str, String str2) {
        return GeneratedOutlineSupport1.outline75(str, "_", str2);
    }

    private String af(String str, String str2) {
        String O;
        synchronized (this.po) {
            O = this.pA.O(str, gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
        }
        return O;
    }

    private String ag(String str, String str2) {
        String O;
        synchronized (this.po) {
            O = this.pA.O(str, TokenKeys.getAccessTokenKeyForPackage(str2));
        }
        return O;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x001b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void ah(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            com.amazon.identity.auth.device.hk r0 = r7.po
            monitor-enter(r0)
            java.lang.String r1 = "OAuthTokenManager"
            java.lang.String r2 = "Expiring all actor tokens for actor: "
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L6b
            r2.concat(r3)     // Catch: java.lang.Throwable -> L6b
            com.amazon.identity.auth.device.io.dm(r1)     // Catch: java.lang.Throwable -> L6b
            com.amazon.identity.auth.device.gm r1 = r7.pA     // Catch: java.lang.Throwable -> L6b
            java.util.Set r1 = r1.cf(r8)     // Catch: java.lang.Throwable -> L6b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L6b
        L1b:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L69
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = "/"
            java.lang.String r4 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = r3.concat(r4)     // Catch: java.lang.Throwable -> L6b
            boolean r4 = r7.aj(r2, r9)     // Catch: java.lang.Throwable -> L6b
            if (r4 != 0) goto L56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b
            r4.<init>()     // Catch: java.lang.Throwable -> L6b
            r5 = 0
            java.lang.String r6 = "com.amazon.dcp.sso.token.oauth.amazon.actor.refresh_token"
            java.lang.String r5 = com.amazon.identity.auth.device.gv.W(r5, r6)     // Catch: java.lang.Throwable -> L6b
            r4.append(r5)     // Catch: java.lang.Throwable -> L6b
            r4.append(r3)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L6b
            boolean r3 = r2.endsWith(r3)     // Catch: java.lang.Throwable -> L6b
            if (r3 == 0) goto L54
            goto L56
        L54:
            r3 = 0
            goto L57
        L56:
            r3 = 1
        L57:
            if (r3 == 0) goto L1b
            java.lang.String r3 = "OAuthTokenManager"
            java.lang.String r4 = "Expiring token key: "
            r4.concat(r2)     // Catch: java.lang.Throwable -> L6b
            com.amazon.identity.auth.device.io.dm(r3)     // Catch: java.lang.Throwable -> L6b
            com.amazon.identity.auth.device.gm r3 = r7.pA     // Catch: java.lang.Throwable -> L6b
            r3.P(r8, r2)     // Catch: java.lang.Throwable -> L6b
            goto L1b
        L69:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6b
            return
        L6b:
            r8 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6b
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.ah(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
        if (r2.contains(r7 + ".") != false) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x001b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void ai(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            com.amazon.identity.auth.device.hk r0 = r5.po
            monitor-enter(r0)
            java.lang.String r1 = "OAuthTokenManager"
            java.lang.String r2 = "Expiring actor cookies for actor: "
            java.lang.String r3 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L63
            r2.concat(r3)     // Catch: java.lang.Throwable -> L63
            com.amazon.identity.auth.device.io.dm(r1)     // Catch: java.lang.Throwable -> L63
            com.amazon.identity.auth.device.gm r1 = r5.pA     // Catch: java.lang.Throwable -> L63
            java.util.Set r1 = r1.cf(r6)     // Catch: java.lang.Throwable -> L63
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L63
        L1b:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L63
            if (r2 == 0) goto L61
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L63
            java.lang.String r3 = "com.amazon.dcp.sso.token.amazon.actor.cookies"
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> L63
            if (r3 == 0) goto L4e
            boolean r3 = r2.endsWith(r7)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L4c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r3.<init>()     // Catch: java.lang.Throwable -> L63
            r3.append(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = "."
            r3.append(r4)     // Catch: java.lang.Throwable -> L63
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L63
            boolean r3 = r2.contains(r3)     // Catch: java.lang.Throwable -> L63
            if (r3 == 0) goto L4e
        L4c:
            r3 = 1
            goto L4f
        L4e:
            r3 = 0
        L4f:
            if (r3 == 0) goto L1b
            java.lang.String r3 = "OAuthTokenManager"
            java.lang.String r4 = "Expiring cookie key: "
            r4.concat(r2)     // Catch: java.lang.Throwable -> L63
            com.amazon.identity.auth.device.io.dm(r3)     // Catch: java.lang.Throwable -> L63
            com.amazon.identity.auth.device.gm r3 = r5.pA     // Catch: java.lang.Throwable -> L63
            r3.P(r6, r2)     // Catch: java.lang.Throwable -> L63
            goto L1b
        L61:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L63
            return
        L63:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L63
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.token.OAuthTokenManager.ai(java.lang.String, java.lang.String):void");
    }

    private boolean aj(String str, String str2) {
        String concat = "/".concat(String.valueOf(str2));
        if (!str.endsWith(gv.W(null, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token") + concat)) {
            if (str.endsWith(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT) + concat)) {
                return true;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
            sb.append(concat);
            return str.endsWith(sb.toString());
        }
        return true;
    }

    private String g(String str, String str2, ej ejVar) throws OAuthTokenManagerException {
        if (!ie.q(this.o, str2)) {
            String af = af(str, str2);
            if (TextUtils.isEmpty(af)) {
                h(str, str2, ejVar);
                af = af(str, str2);
            }
            if (!TextUtils.isEmpty(af)) {
                return af;
            }
            io.e("OAuthTokenManager", "Fail to get child device type refresh token!");
            throw new OAuthTokenManagerException(MAPError.CommonError.SERVER_ERROR, "Fail to get child device type refresh token, probably due to child device type registration failed", 1, "Unable to get child device type refresh token");
        }
        return null;
    }

    public Map<String, String> F(Bundle bundle) {
        if (bundle != null && bundle.size() > 0) {
            HashMap hashMap = new HashMap();
            String string = bundle.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
            int i = 0;
            try {
                i = Integer.parseInt(bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
            } catch (NumberFormatException unused) {
                io.e("OAuthTokenManager", "NumberFormatException fetching expiresInSeconds data");
            }
            String string2 = bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put(TokenKeys.getAccessTokenKeyForPackage(null), string);
            hashMap.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + currentTimeMillis));
            hashMap.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), string2);
            hashMap.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(currentTimeMillis));
            return hashMap;
        }
        return Collections.emptyMap();
    }

    public String a(String str, im imVar, Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        if (!TextUtils.isEmpty(str)) {
            boolean z = true;
            if ("com.amazon.dcp.sso.token.oauth.amazon.access_token".equals(imVar.getKey())) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                Bundle bundle2 = bundle;
                String d = d(str, bundle2);
                String str2 = null;
                if (!TextUtils.isEmpty(d)) {
                    if (!TextUtils.isEmpty(d) && !TextUtils.isEmpty(str)) {
                        String packageName = imVar.getPackageName();
                        if (this.dY.isAccountRegistered(d)) {
                            if (c(bundle2, ejVar)) {
                                str2 = c(str, a(d, imVar.getPackageName(), true, ejVar), imVar.getPackageName(), bundle2, ejVar);
                            } else if (b(str, imVar, bundle2, ejVar)) {
                                String d2 = d(d, packageName, ejVar);
                                str2 = c(str, TextUtils.isEmpty(d2) ? a(d, imVar.getPackageName(), true, ejVar) : d2, imVar.getPackageName(), bundle2, ejVar);
                            }
                        } else {
                            io.w("OAuthTokenManager", "The delegatee account is already deregistered.");
                            String.format("The delegatee account %s is already deregistered.", d);
                            io.gC();
                            MAPError.AccountError accountError = MAPError.AccountError.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED;
                            throw new OAuthTokenManagerException(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED.value(), "The delegatee account is already deregistered on this device");
                        }
                    } else {
                        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given account or delegated account is currently not valid", 8, "Given account or delegated account is currently not valid");
                    }
                } else {
                    try {
                        String packageName2 = imVar.getPackageName();
                        "Getting access token for package ".concat(String.valueOf(packageName2));
                        io.dm("OAuthTokenManager");
                        if (ad(str, packageName2) && !c(bundle2, ejVar)) {
                            if (b(str, imVar, bundle2, ejVar)) {
                                str2 = f(str, imVar.getPackageName(), ejVar);
                            }
                        }
                        if (bundle2.getBoolean("authorizationCode")) {
                            ejVar.bA("AUTHORIZATION_CODE_TO_ACCESS_TOKEN");
                        } else {
                            z = false;
                        }
                        if (z) {
                            str2 = c(str, packageName2, bundle2, ejVar);
                        } else {
                            str2 = a(str, imVar.getPackageName(), false, ejVar);
                        }
                    } catch (UnsupportedOperationException e) {
                        MAPError.AccountError accountError2 = MAPError.AccountError.CUSTOMER_NOT_FOUND;
                        throw new OAuthTokenManagerException(accountError2, accountError2.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), e);
                    }
                }
                return TextUtils.isEmpty(str2) ? b(str, imVar) : str2;
            }
            String format = String.format("Token key %s is not a valid key", imVar.gA());
            throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, format, 7, format);
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
    }

    public boolean ad(String str, String str2) {
        if (af(str, str2) != null) {
            GeneratedOutlineSupport1.outline161(str2, "Local refresh token is not empty for package: ", "OAuthTokenManager");
            return true;
        }
        GeneratedOutlineSupport1.outline161(str2, "Local refresh token is empty for package: ", "OAuthTokenManager");
        return false;
    }

    public boolean ae(String str, String str2) {
        return ag(str, str2) != null;
    }

    public String b(String str, im imVar, Bundle bundle) {
        String b = b(str, imVar);
        if (a(str, imVar, bundle)) {
            return null;
        }
        return b;
    }

    protected void c(String str, @NonNull Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle.putBundle("auth_portal_config", bundle2);
        String m = TextUtils.isEmpty(bundle.getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN)) ? hr.m(this.o, str) : bundle.getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN);
        bundle2.putString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN, m);
        String string = bundle.getString(TokenKeys.Options.KEY_CHALLENGE_URL_ASSOC_HANDLE);
        if (TextUtils.isEmpty(string)) {
            string = TextUtils.isEmpty(EnvironmentUtils.cd().bd(m)) ? "amzn_device_android" : EnvironmentUtils.cd().bd(m);
        }
        bundle2.putString(TokenKeys.Options.KEY_CHALLENGE_URL_ASSOC_HANDLE, string);
        if (!TextUtils.isEmpty(bundle.getString(TokenKeys.Options.KEY_CHALLENGE_URL_PAGE_ID))) {
            string = bundle.getString(TokenKeys.Options.KEY_CHALLENGE_URL_PAGE_ID);
        }
        bundle2.putString(TokenKeys.Options.KEY_CHALLENGE_URL_PAGE_ID, string);
        if (!TextUtils.isEmpty(bundle.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN))) {
            m = bundle.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN);
        }
        bundle2.putString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN, m);
    }

    public String d(String str, String str2, ej ejVar) throws OAuthTokenManagerException {
        String af = af(str, str2);
        return af != null ? af : a(str, str2, true, ejVar);
    }

    public String e(String str, String str2, ej ejVar) throws OAuthTokenManagerException {
        String m = this.pA.m(str, str2, gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN));
        if (TextUtils.isEmpty(m)) {
            io.i("OAuthTokenManager", "No local actor refresh token, try get one by calling getActorAccessToken.");
            a(str, str2, new im("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), (String) null, ejVar, new Bundle());
            return this.pA.m(str, str2, gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN));
        }
        return m;
    }

    public String f(String str, String str2, ej ejVar) throws OAuthTokenManagerException {
        if (str != null) {
            StringBuilder sb = new StringBuilder("Refreshing access token for ");
            sb.append(str2 != null ? "package ".concat(str2) : "central");
            io.i("OAuthTokenManager", sb.toString());
            String d = d(str, new Bundle());
            if (!TextUtils.isEmpty(d)) {
                String z = this.pA.z(d, gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
                if (TextUtils.isEmpty(z)) {
                    z = a(d, str2, true, ejVar);
                }
                return c(str, z, str2, new Bundle(), ejVar);
            }
            return h(str, str2, ejVar);
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
    }

    String h(String str, String str2, ej ejVar) throws OAuthTokenManagerException {
        try {
            String z = this.pA.z(str, gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
            if (z == null) {
                return a(str, str2, false, ejVar);
            }
            a a2 = a(str, str2, z, ejVar);
            synchronized (this.po) {
                String z2 = this.pA.z(str, gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
                if (TextUtils.equals(z2, z)) {
                    io.i("OAuthTokenManager", "Refresh token is not changed, store the exchanged token.");
                    a(str, str2, a2);
                    return a2.mAccessToken;
                }
                io.i("OAuthTokenManager", "Refresh token has been changed, try read from the database.");
                mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_TokenExchange", new String[0]);
                String ag = ag(str, str2);
                if (!TextUtils.isEmpty(ag)) {
                    mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_TokenExchange_ReturnCached", new String[0]);
                    io.i("OAuthTokenManager", "Local database access token is not empty, return it.");
                    return ag;
                }
                mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_TokenExchange_Refresh", new String[0]);
                io.i("OAuthTokenManager", "Local database access token is empty, refresh it.");
                return a(str, str2, z2, ejVar).mAccessToken;
            }
        } catch (IOException e) {
            mq.b("refreshNormalOAuthTokenFailure:IOException", new String[0]);
            mq.incrementCounterAndRecord("NetworkError10:OAuthTokenManager", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e);
        } catch (ParseException e2) {
            mq.b("refreshNormalOAuthTokenFailure:ParseException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e2.getMessage()), 5, e2.getMessage());
        } catch (JSONException e3) {
            mq.b("refreshNormalOAuthTokenFailure:JSONException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3.getMessage());
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class OAuthTokenManagerException extends Exception implements fp.a {
        private static final long serialVersionUID = -7354549861193710767L;
        private fp mAccountRecoverContext;
        private AuthEndpointErrorParser.a mAuthEndpointError;
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;
        private final boolean mShouldClearAuthCookies;

        public OAuthTokenManagerException(MAPError mAPError, String str) {
            this(mAPError, str, MAPAccountManager.RegistrationError.LEGACY_ERROR_CODE_NOT_SUPPORTED_ERROR.value(), "Legacy MAP error code is not supported. Please refer com.amazon.identity.auth.device.api.MAPError");
        }

        @Override // com.amazon.identity.auth.device.fp.a
        @Deprecated
        public int bE() {
            return this.mLegacyErrorCode;
        }

        @Override // com.amazon.identity.auth.device.fp.a
        @Deprecated
        public String bF() {
            return this.mLegacyErrorMessage;
        }

        @Override // com.amazon.identity.auth.device.fp.a
        public fp eE() {
            return this.mAccountRecoverContext;
        }

        public AuthEndpointErrorParser.a fT() {
            return this.mAuthEndpointError;
        }

        public boolean fU() {
            return this.mShouldClearAuthCookies;
        }

        public MAPError getError() {
            return this.mError;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAccountRecoverContext = null;
            this.mAuthEndpointError = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, Throwable th) {
            super(th.getMessage(), th);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = th.getMessage();
            this.mAccountRecoverContext = null;
            this.mAuthEndpointError = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar, byte b) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = null;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = true;
        }

        public OAuthTokenManagerException(MAPError mAPError, String str, int i, String str2, AuthEndpointErrorParser.a aVar, fp fpVar) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mAuthEndpointError = aVar;
            this.mAccountRecoverContext = fpVar;
            this.mError = mAPError;
            this.mErrorMessage = str;
            this.mShouldClearAuthCookies = false;
        }
    }

    private String d(String str, Bundle bundle) {
        String string = bundle.getString("com.amazon.dcp.sso.property.account.delegateeaccount");
        if (TextUtils.isEmpty(string)) {
            mq.incrementCounterAndRecord("GetDelegatedTokenUnnecessaryDelegatee", new String[0]);
            return this.z.b(str, this.pA);
        }
        return string;
    }

    public void b(String str, String str2, String str3, @NonNull Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        try {
            bundle.putBundle("actor_cookies_for_request", new ha(this.o).a(str, str2, str3, new Bundle(), ejVar));
        } catch (MAPCallbackErrorException unused) {
            throw new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch actor cookies internally for get actor token request with failure context.", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Unable to fetch actor cookies internally for get actor token request with failure context.");
        }
    }

    OAuthTokenManager(Context context, eh ehVar, gm gmVar, iy iyVar, MAPAccountManager mAPAccountManager, w wVar, hb hbVar, ip ipVar, hd hdVar, hc hcVar) {
        this.o = ed.M(context);
        this.F = ehVar;
        this.pA = gmVar;
        this.hO = iyVar;
        this.dY = mAPAccountManager;
        this.z = wVar;
        this.pB = hdVar;
        this.ax = this.o.dW();
        this.aU = new AuthEndpointErrorParser();
        this.pC = new ht();
        this.pE = hbVar;
        this.pD = ipVar;
        this.ng = hcVar;
        this.po = hk.gk();
    }

    public void b(String str, String str2, @NonNull Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        try {
            bundle.putBundle("account_cookies_for_request", new ha(this.o).a(str, str2, new Bundle(), ejVar));
        } catch (MAPCallbackErrorException unused) {
            throw new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, "Unable to fetch account cookies internally.", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Unable to fetch account cookies internally.");
        }
    }

    protected String b(String str, im imVar) {
        return this.pA.z(str, imVar.gA());
    }

    public long b(String str, String str2, im imVar) {
        return je.dB(this.pA.m(str, str2, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT))).longValue() - this.F.currentTimeMillis();
    }

    private boolean c(Bundle bundle, ej ejVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_DMS_TO_OAUTH)) {
            io.i("OAuthTokenManager", "Force refresh the DMS token for OAuth token.");
            ejVar.bA("FORCE_REFRESH_DMS");
            return true;
        }
        return false;
    }

    private boolean b(String str, im imVar, Bundle bundle, ej ejVar) {
        if (!ae(str, imVar.getPackageName())) {
            return true;
        }
        return c(str, imVar, bundle, ejVar);
    }

    protected boolean b(String str, String str2, im imVar, Bundle bundle) {
        String m = this.pA.m(str, str2, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
        long currentTimeMillis = this.F.currentTimeMillis();
        if (a(currentTimeMillis, m)) {
            io.i("OAuthTokenManager", "Clock skew detected. Refreshing...");
            return true;
        }
        Long dB = je.dB(this.pA.m(str, str2, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT)));
        if (dB == null || !a(dB, Long.valueOf(currentTimeMillis), bundle)) {
            return false;
        }
        io.i("OAuthTokenManager", "OAuth actor access token near or past expiry. Need to refresh it...");
        return true;
    }

    public boolean c(String str, im imVar, Bundle bundle, ej ejVar) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH)) {
            io.i("OAuthTokenManager", "Force refresh the OAuth access token.");
            ejVar.bA("FORCE_REFRESH_OAUTH");
            return true;
        }
        return a(str, imVar, bundle);
    }

    public String c(String str, String str2, Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        int i;
        int i2;
        int i3;
        fv.a a2;
        if (str != null) {
            io.i("OAuthTokenManager", "Exchange AuthorizationCode to access token for package " + str2 + " due to " + ejVar.O(this.o));
            try {
                mv aD = mq.aD("OAuthTokenManager", "exchangeAuthorizationCodeForAccessToken");
                i = 0;
                try {
                    fr.a c = fv.a(new hb(ed.M(this.o), new iy()), this.o, str, bundle.getString(AccountConstants.KEY_AUTHORIZATION_CODE), bundle.getString(AccountConstants.KEY_CODE_VERIFIER), bundle.getString("code_challenge_method"), bundle.getString("client_id"), bundle.getString(AccountConstants.KEY_CLIENT_DOMAIN)).c(ejVar);
                    io.i("OAuthTokenManager", "Exchanging authorizationCode for access token with exchange token endpoint: " + a2.eM().toString());
                    aD.stop();
                    Integer num = c.nb;
                    JSONObject jSONObject = c.na;
                    io.i("OAuthTokenManager", "Response received for exchanging authorizationCode to access token");
                    if (!this.pE.a(num) && jSONObject != null) {
                        mq.incrementCounterAndRecord("exchangeAuthorizationCodeForAccessTokenSuccess", new String[0]);
                        return this.pE.l(jSONObject).mAccessToken;
                    }
                    i2 = 1;
                    try {
                        Object[] objArr = new Object[1];
                        objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
                        io.a("Error Response: %s", objArr);
                        throw a(str, this.pE.n(jSONObject), num, AuthTokenExchangeType.AuthorizationCodeToOAuthAccessTokenExchange);
                    } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e) {
                        e = e;
                        i3 = 3;
                        if (e.getAccountRecoverContextBundle() != null) {
                            throw new OAuthTokenManagerException(MAPError.CommonError.CORRUPTED_DATABASE, "MAP Database is corrupted", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP Database is corrupted", new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.InvalidToken, "RecoverAccount", "MAP client side database is corrupted.", null, null), fp.E(e.getAccountRecoverContextBundle()));
                        }
                        mq.incrementCounterAndRecord("authorizationCodeToAccessTokenFailure:IOException", new String[i]);
                        mq.incrementCounterAndRecord("NetworkError8:OAuthTokenManager", new String[i]);
                        MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
                        Object[] objArr2 = new Object[i2];
                        objArr2[i] = e.getMessage();
                        throw new OAuthTokenManagerException(commonError, String.format("A network error occurred: %s", objArr2), i3, e.getMessage());
                    } catch (IOException e2) {
                        e = e2;
                        mq.b("authorizationCodeToAccessTokenFailure:IOException", new String[i]);
                        mq.incrementCounterAndRecord("NetworkError9:OAuthTokenManager", new String[i]);
                        MAPError.CommonError commonError2 = MAPError.CommonError.NETWORK_ERROR;
                        Object[] objArr3 = new Object[i2];
                        objArr3[i] = e.getMessage();
                        throw new OAuthTokenManagerException(commonError2, String.format("A network error occurred: %s", objArr3), 3, e);
                    } catch (ParseException e3) {
                        e = e3;
                        mq.incrementCounterAndRecord("authorizationCodeToAccessTokenFailure:ParseException", new String[i]);
                        MAPError.CommonError commonError3 = MAPError.CommonError.INVALID_RESPONSE;
                        Object[] objArr4 = new Object[i2];
                        objArr4[i] = e.getMessage();
                        throw new OAuthTokenManagerException(commonError3, String.format("An invalid response was received: %s", objArr4), 5, e.getMessage());
                    } catch (JSONException e4) {
                        e = e4;
                        mq.incrementCounterAndRecord("authorizationCodeToAccessTokenFailure:JSONException", new String[i]);
                        MAPError.CommonError commonError4 = MAPError.CommonError.INVALID_RESPONSE;
                        Object[] objArr5 = new Object[i2];
                        objArr5[i] = e.getMessage();
                        throw new OAuthTokenManagerException(commonError4, String.format("An invalid response was received: %s", objArr5), 5, e.getMessage());
                    }
                } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e5) {
                    e = e5;
                    i2 = 1;
                } catch (IOException e6) {
                    e = e6;
                    i2 = 1;
                } catch (ParseException e7) {
                    e = e7;
                    i2 = 1;
                } catch (JSONException e8) {
                    e = e8;
                    i2 = 1;
                }
            } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e9) {
                e = e9;
                i = 0;
                i2 = 1;
                i3 = 3;
            } catch (IOException e10) {
                e = e10;
                i = 0;
                i2 = 1;
            } catch (ParseException e11) {
                e = e11;
                i = 0;
                i2 = 1;
            } catch (JSONException e12) {
                e = e12;
                i = 0;
                i2 = 1;
            }
        } else {
            throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
        }
    }

    public String a(Context context, String str, String str2, im imVar, String str3, @NonNull Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                if ("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token".equals(imVar.getKey())) {
                    if (!TextUtils.isEmpty(str3) && context == null) {
                        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Parameter context is null with non-null tokenValidationFailureContext, please pass the context.", 8, "Parameter context is null with non-null tokenValidationFailureContext, please pass the context.");
                    }
                    if (bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_DMS_TO_OAUTH)) {
                        io.w("OAuthTokenManager", "Key KEY_FORCE_REFRESH_DMS_TO_OAUTH is not supported for get actor access token, ignoring...");
                    }
                    String a2 = a(str, str2, imVar, bundle);
                    if (a2 != null && !bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH) && TextUtils.isEmpty(str3)) {
                        return a2;
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        c(str, bundle);
                        b(str, str2, bundle.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN), bundle, ejVar);
                    }
                    return a(str, str2, imVar, str3, ejVar, bundle);
                }
                String format = String.format("Token key %s is not a valid key for getting actor access token", imVar.gA());
                throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, format, 7, format);
            }
            throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given actorId is null.", 8, "Given actorId is null.");
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given accountId is null.", 8, "Given accountId is null.");
    }

    public void a(@NonNull String str, String str2, @NonNull String str3, @NonNull Callback callback, @NonNull ej ejVar, Bundle bundle) throws OAuthTokenManagerException {
        Map<String, String> hashMap;
        mv aD;
        Map<String, String> R = this.pA.R(str, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
        Set<String> keySet = R.keySet();
        if (!TextUtils.isEmpty(str2)) {
            hashMap = this.pA.o(str, str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN);
        } else {
            hashMap = new HashMap<>();
        }
        ft a2 = ft.a(this.o, str, R, str2, hashMap, str3, bundle, this.ng);
        try {
            if (TextUtils.isEmpty(str2)) {
                aD = mq.aD("OAuthTokenManager", "upgradeOAuthRefreshTokenCIDOnly");
            } else {
                aD = mq.aD("OAuthTokenManager", "upgradeOAuthRefreshTokenCIDPID");
            }
            mv mvVar = aD;
            fr.a c = a2.c(ejVar);
            io.i("OAuthTokenManager", "Upgrade OAuth token with endpoint: " + a2.eM().toString());
            mvVar.stop();
            Integer num = c.nb;
            JSONObject jSONObject = c.na;
            io.i("OAuthTokenManager", "Response received for token upgrade request");
            if (!this.ng.a(num) && jSONObject != null) {
                a(this.ng.q(jSONObject), keySet, str, str2);
                callback.onSuccess(new Bundle());
                return;
            }
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
            io.a("Error Response: %s", objArr);
            AuthEndpointErrorParser.a r = this.ng.r(jSONObject);
            io.i("OAuthTokenManager", r.cG().getErrorMessage());
            throw new OAuthTokenManagerException(r.cG().getError(), r.getMessage());
        } catch (IOException e) {
            io.e("OAuthTokenManager", "A network error occurred.", e);
            mq.b("upgradeOAuthRefreshTokenFailurePanda:IOException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()));
        } catch (JSONException e2) {
            io.e("OAuthTokenManager", "An invalid response was received.", e2);
            mq.b("upgradeOAuthRefreshTokenFailurePanda:JSONException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e2.getMessage()));
        } catch (Exception e3) {
            io.e("OAuthTokenManager", "Unknown exception.", e3);
            mq.b("upgradeOAuthRefreshTokenFailurePanda:Exception", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, e3.getMessage());
        }
    }

    private String c(String str, String str2, String str3, Bundle bundle, ej ejVar) throws OAuthTokenManagerException {
        try {
            mv aD = mq.aD("OAuthTokenManager", "refreshDelegatedOAuthToken");
            fr.a c = fv.a(new hb(ed.M(this.o), new iy()), this.o, str, str2, bundle).c(ejVar);
            aD.stop();
            JSONObject jSONObject = c.na;
            Integer num = c.nb;
            io.i("OAuthTokenManager", "Response received for exchange delegate account token.");
            if (!this.pE.a(num) && jSONObject != null) {
                mq.b("refreshDelegatedOAuthTokenPandaSuccess", new String[0]);
                a l = this.pE.l(jSONObject);
                a(str, str3, l);
                return l.mAccessToken;
            }
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
            io.a("Error Response: %s", objArr);
            throw a(str, this.pE.n(jSONObject), num, AuthTokenExchangeType.OauthRefreshToDelegationAccessExchange);
        } catch (IOException e) {
            mq.b("refreshDelegatedOAuthTokenFailurePanda:IOException", new String[0]);
            mq.incrementCounterAndRecord("NetworkError11:OAuthTokenManager", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e);
        } catch (ParseException e2) {
            mq.b("refreshDelegatedOAuthTokenFailurePanda:ParseException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e2.getMessage()), 5, e2);
        } catch (JSONException e3) {
            mq.b("refreshDelegatedOAuthTokenFailurePanda:JSONException", new String[0]);
            throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3);
        }
    }

    private void a(List<hc.c> list, Set<String> set, String str, String str2) {
        synchronized (this.po) {
            for (String str3 : this.pA.cc(str)) {
                synchronized (this.po) {
                    "Expiring actor access tokens for actor: ".concat(String.valueOf(str3));
                    io.dm("OAuthTokenManager");
                    for (String str4 : this.pA.cf(str)) {
                        if (aj(str4, str3)) {
                            "Expiring token key: ".concat(str4);
                            io.dm("OAuthTokenManager");
                            this.pA.P(str, str4);
                        }
                    }
                }
                ai(str, str3);
            }
            for (String str5 : this.pA.cf(str)) {
                if (str5.startsWith("com.amazon.dcp.sso.token.amazon.cookies.")) {
                    this.pA.B(str, str5);
                }
            }
            for (String str6 : set) {
                HashSet hashSet = new HashSet();
                hashSet.add(TokenKeys.getAccessTokenKeyForPackage(null));
                hashSet.add(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
                hashSet.add(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
                this.pA.a(str, str6, hashSet);
            }
            for (hc.c cVar : list) {
                String deviceType = cVar.getDeviceType();
                "Store account upgraded token for device type ".concat(String.valueOf(deviceType));
                io.dm("OAuthTokenManager");
                hc.b ga = cVar.ga();
                if (ga != null && ga.isValid()) {
                    io.i("OAuthTokenManager", "Store upgraded account refresh token.");
                    String fW = ga.fW();
                    HashMap hashMap = new HashMap();
                    hashMap.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), fW);
                    this.pA.a(str, deviceType, hashMap);
                } else {
                    io.e("OAuthTokenManager", "No valid upgraded token in the response, this should never happen!");
                    mq.incrementCounterAndRecord("invalidUpgradedAccountRefreshToken", new String[0]);
                }
                hc.a fZ = cVar.fZ();
                if (fZ != null && fZ.isValid()) {
                    io.i("OAuthTokenManager", "Store upgraded account access token.");
                    int fV = fZ.fV();
                    String accessToken = fZ.getAccessToken();
                    long currentTimeMillis = System.currentTimeMillis();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(TokenKeys.getAccessTokenKeyForPackage(null), accessToken);
                    hashMap2.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(TimeUnit.MILLISECONDS.convert(fV, TimeUnit.SECONDS) + currentTimeMillis));
                    hashMap2.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(currentTimeMillis));
                    this.pA.a(str, deviceType, hashMap2);
                } else {
                    io.e("OAuthTokenManager", "Upgraded account access token is invalid, not store it.");
                }
                if (!TextUtils.isEmpty(str2)) {
                    "Update local actor token for device type ".concat(String.valueOf(deviceType));
                    io.dm("OAuthTokenManager");
                    hc.b fY = cVar.fY();
                    if (fY != null && fY.isValid()) {
                        io.i("OAuthTokenManager", "Store upgraded actor refresh token.");
                        String fW2 = fY.fW();
                        synchronized (this.po) {
                            HashMap hashMap3 = new HashMap();
                            hashMap3.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN), fW2);
                            this.pA.a(str, str2, deviceType, hashMap3);
                        }
                    } else {
                        io.e("OAuthTokenManager", "Upgraded actor refresh token is invalid, not store it.");
                    }
                    hc.a fX = cVar.fX();
                    if (fX != null && fX.isValid()) {
                        io.i("OAuthTokenManager", "Store upgraded actor access token.");
                        int fV2 = fX.fV();
                        String accessToken2 = fX.getAccessToken();
                        synchronized (this.po) {
                            long currentTimeMillis2 = System.currentTimeMillis();
                            HashMap hashMap4 = new HashMap();
                            hashMap4.put(gv.W(null, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), accessToken2);
                            hashMap4.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(TimeUnit.MILLISECONDS.convert(fV2, TimeUnit.SECONDS) + currentTimeMillis2));
                            hashMap4.put(gv.W(null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(currentTimeMillis2));
                            this.pA.a(str, str2, deviceType, hashMap4);
                        }
                    } else {
                        io.e("OAuthTokenManager", "Upgraded actor access token is invalid, not store it.");
                    }
                }
            }
        }
    }

    protected String a(String str, String str2, im imVar, Bundle bundle) {
        String a2 = a(str, str2, imVar);
        if (TextUtils.isEmpty(a2) || b(str, str2, imVar, bundle)) {
            return null;
        }
        return a2;
    }

    protected String a(String str, String str2, im imVar) {
        return this.pA.m(str, str2, imVar.gA());
    }

    protected boolean a(String str, im imVar, Bundle bundle) {
        String z = this.pA.z(str, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT));
        long currentTimeMillis = this.F.currentTimeMillis();
        if (a(currentTimeMillis, z)) {
            io.i("OAuthTokenManager", "Clock skew detected. Refreshing...");
            return true;
        }
        Long dB = je.dB(this.pA.z(str, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT)));
        if (dB == null || !a(dB, Long.valueOf(currentTimeMillis), bundle)) {
            return false;
        }
        io.i("OAuthTokenManager", "OAuth access token near or past expiry. Need to refresh it...");
        return true;
    }

    private boolean a(Long l, Long l2, Bundle bundle) {
        return (bundle.getLong(TokenKeys.Options.KEY_OAUTH_TTL_MS_LONG, 0L) + l2.longValue()) + pn >= l.longValue();
    }

    private boolean a(long j, String str) {
        Long dB;
        return !TextUtils.isEmpty(str) && (dB = je.dB(str)) != null && j < dB.longValue();
    }

    private String a(String str, String str2, boolean z, ej ejVar) throws OAuthTokenManagerException {
        if (str != null) {
            io.i("OAuthTokenManager", "Exchange DMS token to OAuth token for package " + str2 + " due to " + ejVar.O(this.o));
            try {
                mv aD = mq.aD("OAuthTokenManager", "exchangeDMSCredentialsForOAuthToken");
                fv.b a2 = fv.a(new hb(ed.M(this.o), new iy()), this.o, str, str2);
                fr.a c = a2.c(ejVar);
                io.i("OAuthTokenManager", "Exchanging DMS token with exchange token endpoint: " + a2.eM().toString());
                aD.stop();
                Integer num = c.nb;
                JSONObject jSONObject = c.na;
                io.i("OAuthTokenManager", "Response received for exchange DMS to OAuth end-point");
                if (!this.pE.a(num) && jSONObject != null) {
                    mq.b("exchangeDMSCredentialsForOAuthTokenSuccess", new String[0]);
                    a l = this.pE.l(jSONObject);
                    a(str, str2, l);
                    if (z) {
                        return l.ne;
                    }
                    return l.mAccessToken;
                }
                Object[] objArr = new Object[1];
                objArr[0] = jSONObject != null ? jSONObject.toString() : "Null Json Response";
                io.a("Error Response: %s", objArr);
                throw a(str, this.pE.n(jSONObject), num, AuthTokenExchangeType.DMSTokenToOauthTokenExchange);
            } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e) {
                if (e.getAccountRecoverContextBundle() != null) {
                    throw new OAuthTokenManagerException(MAPError.CommonError.CORRUPTED_DATABASE, "MAP Database is corrupted", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP Database is corrupted", new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.InvalidToken, "RecoverAccount", "MAP client side database is corrupted.", null, null), fp.E(e.getAccountRecoverContextBundle()));
                }
                mq.b("exchangeDMSCredentialsForOAuthTokenFailure:IOException", new String[0]);
                mq.incrementCounterAndRecord("NetworkError8:OAuthTokenManager", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), 3, e.getMessage());
            } catch (IOException e2) {
                mq.b("exchangeDMSCredentialsForOAuthTokenFailure:IOException", new String[0]);
                mq.incrementCounterAndRecord("NetworkError9:OAuthTokenManager", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e2.getMessage()), 3, e2);
            } catch (ParseException e3) {
                mq.b("exchangeDMSCredentialsForOAuthTokenFailure:ParseException", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e3.getMessage()), 5, e3.getMessage());
            } catch (JSONException e4) {
                mq.b("exchangeDMSCredentialsForOAuthTokenFailure:JSONException", new String[0]);
                throw new OAuthTokenManagerException(MAPError.CommonError.INVALID_RESPONSE, String.format("An invalid response was received: %s", e4.getMessage()), 5, e4.getMessage());
            }
        }
        throw new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given Account is currently not valid", 8, "Given Account is currently not valid");
    }

    a[] a(String str, String str2, String str3, String str4, String str5, String str6, String str7, ej ejVar, @NonNull Bundle bundle) throws IOException, JSONException, OAuthTokenManagerException, ParseException {
        HttpURLConnection httpURLConnection;
        try {
            mv aD = mq.aD("OAuthTokenManager", "refreshActorToken");
            httpURLConnection = this.pE.a(str2, str3, str5, str, str4, str6, str7, bundle, ejVar);
            try {
                int d = RetryLogic.d(httpURLConnection);
                aD.stop();
                io.i("OAuthTokenManager", "Response received actor access token exchange");
                JSONObject f = ik.f(httpURLConnection);
                if (!this.pE.a(Integer.valueOf(d)) && f != null) {
                    a[] m = this.pE.m(f);
                    az.a o = this.pE.o(f);
                    if (o != null) {
                        this.pA.e(str, str4, AccountConstants.KEY_ACTOR_SUBTYPE, o.fr);
                        this.pA.e(str, str4, AccountConstants.KEY_ACTOR_ENTITYTYPE, o.fs);
                        this.pA.e(str, str4, AccountConstants.KEY_ACTOR_CONVERTEDTYPE, o.ft);
                    }
                    mq.incrementCounterAndRecord("refreshActorTokenSuccess", new String[0]);
                    httpURLConnection.disconnect();
                    return m;
                }
                Object[] objArr = new Object[1];
                objArr[0] = f != null ? f.toString() : "Null Json Response";
                io.a("Error Response: %s", objArr);
                throw a(str, str4, this.pE.n(f), Integer.valueOf(d), AuthTokenExchangeType.OauthRefreshToAccessExchange);
            } catch (Throwable th) {
                th = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [com.amazon.identity.auth.device.hk] */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.amazon.identity.auth.device.token.OAuthTokenManager] */
    public String a(String str, String str2, im imVar, String str3, ej ejVar, @NonNull Bundle bundle) throws OAuthTokenManagerException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        String str4 = str2;
        io.i("OAuthTokenManager", "refreshing actor access token...");
        String str5 = null;
        int af = af(str, null);
        String g = g(str, imVar.getPackageName(), ejVar);
        String m = this.pA.m(str, str4, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN);
        try {
            try {
                a[] a2 = a(str, af, g, str2, m, imVar.getPackageName(), str3, ejVar, bundle);
                ?? r10 = this.po;
                try {
                    synchronized (r10) {
                        try {
                            String g2 = g(str, imVar.getPackageName(), ejVar);
                            String af2 = af(str, null);
                            String m2 = this.pA.m(str, str4, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN);
                            try {
                                if (TextUtils.equals(m2, m)) {
                                    try {
                                        if (TextUtils.equals(g2, g) && TextUtils.equals(af2, af)) {
                                            io.i("OAuthTokenManager", "Actor and account refresh token is not changed, store and return it.");
                                            String packageName = imVar.getPackageName();
                                            if (this.dY.isAccountRegistered(str) || jk.gR()) {
                                                for (a aVar : a2) {
                                                    String str6 = aVar.bk;
                                                    if (!TextUtils.isEmpty(str6)) {
                                                        if (str6.equals(ie.s(this.o, packageName))) {
                                                            a(str, str4, packageName, aVar);
                                                            str5 = aVar.mAccessToken;
                                                        } else if (str6.equals(ie.au(this.o))) {
                                                            a(str, str4, packageName, aVar);
                                                        } else {
                                                            io.w("OAuthTokenManager", "The device type is not supported for the package , ignoring...");
                                                            mq.incrementCounterAndRecord("UNSUPPORTED_DEVICE_TYPE_FROM_SERVER", new String[0]);
                                                        }
                                                    }
                                                }
                                                i6 = 0;
                                                this.pB.cN(str);
                                            } else {
                                                i6 = 0;
                                            }
                                            if (!TextUtils.isEmpty(str5)) {
                                                return str5;
                                            }
                                            io.dm("OAuthTokenManager");
                                            throw new ParseException("Can not get actor token from service response", i6);
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        af = 0;
                                        str4 = r10;
                                        try {
                                            throw th;
                                        } catch (IOException e) {
                                            e = e;
                                            i5 = 1;
                                            i4 = af;
                                            mq.incrementCounterAndRecord("refreshActorTokenFailure:IOException", new String[i4]);
                                            MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
                                            Object[] objArr = new Object[i5];
                                            objArr[i4] = e.getMessage();
                                            throw new OAuthTokenManagerException(commonError, String.format("A network error occurred: %s", objArr), 3, e);
                                        } catch (ParseException e2) {
                                            e = e2;
                                            i2 = 1;
                                            i3 = 5;
                                            i = af;
                                            mq.incrementCounterAndRecord("refreshActorTokenFailure:ParseException", new String[i]);
                                            MAPError.CommonError commonError2 = MAPError.CommonError.INVALID_RESPONSE;
                                            Object[] objArr2 = new Object[i2];
                                            objArr2[i] = e.getMessage();
                                            throw new OAuthTokenManagerException(commonError2, String.format("An invalid response was received: %s", objArr2), i3, e.getMessage());
                                        } catch (JSONException e3) {
                                            e = e3;
                                            mq.incrementCounterAndRecord("refreshActorTokenFailure:JSONException", new String[af]);
                                            MAPError.CommonError commonError3 = MAPError.CommonError.INVALID_RESPONSE;
                                            Object[] objArr3 = new Object[1];
                                            objArr3[af] = e.getMessage();
                                            throw new OAuthTokenManagerException(commonError3, String.format("An invalid response was received: %s", objArr3), 5, e.getMessage());
                                        }
                                    }
                                }
                                io.i("OAuthTokenManager", "Actor or account refresh token has been changed, read from database.");
                                mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_TokenExchange", new String[0]);
                                String m3 = this.pA.m(str, str4, gv.W(imVar.getPackageName(), "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"));
                                if (!TextUtils.isEmpty(m3)) {
                                    mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_TokenExchange_ReturnCached", new String[0]);
                                    io.i("OAuthTokenManager", "Local database actor access token is not empty, return it.");
                                    return m3;
                                }
                                io.i("OAuthTokenManager", "Local database actor access token is empty, refreshing it.");
                                mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_TokenExchange_Refresh", new String[0]);
                                return a(imVar.getPackageName(), a(str, af2, g2, str2, m2, imVar.getPackageName(), str3, ejVar, bundle));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            str4 = r10;
                            af = 0;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (IOException e4) {
                e = e4;
                af = 0;
            } catch (ParseException e5) {
                e = e5;
                af = 0;
            } catch (JSONException e6) {
                e = e6;
                af = 0;
            }
        } catch (IOException e7) {
            e = e7;
            i4 = 0;
            i5 = 1;
        } catch (ParseException e8) {
            e = e8;
            i = 0;
            i2 = 1;
            i3 = 5;
        } catch (JSONException e9) {
            e = e9;
            af = 0;
        }
    }

    a a(String str, String str2, String str3, ej ejVar) throws OAuthTokenManagerException, IOException, JSONException, ParseException {
        HttpURLConnection httpURLConnection = null;
        try {
            mv aD = mq.aD("OAuthTokenManager", "refreshNormalOAuthToken");
            HttpURLConnection b = this.pE.b(str3, str, str2, ejVar);
            int d = RetryLogic.d(b);
            io.i("OAuthTokenManager", "Response received from OAuth refresh to access exchange end-point");
            this.pD.gH();
            JSONObject f = ik.f(b);
            aD.stop();
            if (!this.pE.a(Integer.valueOf(d)) && f != null) {
                a l = this.pE.l(f);
                mq.b("refreshNormalOAuthTokenSuccess", new String[0]);
                b.disconnect();
                return l;
            }
            Object[] objArr = new Object[1];
            objArr[0] = f != null ? f.toString() : "Null Json Response";
            io.a("Error Response: %s", objArr);
            throw a(str, this.pE.n(f), Integer.valueOf(d), AuthTokenExchangeType.OauthRefreshToAccessExchange);
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public synchronized void a(String str, String str2, int i, String str3, String str4) {
        long currentTimeMillis = System.currentTimeMillis();
        long convert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + currentTimeMillis;
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), str3);
        }
        hashMap.put(TokenKeys.getAccessTokenKeyForPackage(str2), str4);
        hashMap.put(gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(convert));
        hashMap.put(gv.W(str2, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(currentTimeMillis));
        this.pA.d(str, hashMap);
    }

    public OAuthTokenManagerException a(String str, AuthEndpointErrorParser.a aVar, Integer num, AuthTokenExchangeType authTokenExchangeType) {
        return a(str, (String) null, aVar, num, authTokenExchangeType);
    }

    boolean a(ds dsVar, AuthTokenExchangeType authTokenExchangeType) {
        return py.contains(authTokenExchangeType) && !dsVar.dy();
    }

    private void a(String str, String str2, a aVar) {
        if (this.dY.isAccountRegistered(str) || jk.gR()) {
            a(str, str2, aVar.pH, aVar.ne, aVar.mAccessToken);
            this.pB.cN(str);
        }
    }

    private String a(String str, a[] aVarArr) throws ParseException {
        for (a aVar : aVarArr) {
            String str2 = aVar.bk;
            if (!TextUtils.isEmpty(str2) && str2.equals(ie.s(this.o, str))) {
                return aVar.mAccessToken;
            }
        }
        io.dm("OAuthTokenManager");
        throw new ParseException("Can not get actor token from service response", 0);
    }

    private void a(String str, String str2, String str3, a aVar) throws ParseException {
        synchronized (this.po) {
            String str4 = aVar.ne;
            String str5 = aVar.pI;
            String str6 = aVar.mAccessToken;
            if (!TextUtils.isEmpty(str6)) {
                int i = aVar.pH;
                String str7 = aVar.bk;
                long currentTimeMillis = System.currentTimeMillis();
                long convert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + currentTimeMillis;
                HashMap hashMap = new HashMap();
                if (ie.au(this.o).equals(str7)) {
                    str3 = null;
                }
                if (!TextUtils.isEmpty(str4)) {
                    hashMap.put(gv.W(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), str4);
                }
                if (!TextUtils.isEmpty(str5)) {
                    hashMap.put(gv.W(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN), str5);
                }
                hashMap.put(gv.W(str3, "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token"), str6);
                hashMap.put(gv.W(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(convert));
                hashMap.put(gv.W(str3, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(currentTimeMillis));
                this.pA.b(str, str2, hashMap);
            } else {
                io.dm("OAuthTokenManager");
                throw new ParseException("No access token received for package: ".concat(String.valueOf(str3)), 0);
            }
        }
    }

    public OAuthTokenManagerException a(final String str, String str2, AuthEndpointErrorParser.a aVar, Integer num, AuthTokenExchangeType authTokenExchangeType) {
        boolean booleanValue;
        String format = aVar != null ? String.format("Received Error code %s from the server. Message: %s Detail: %s Index: %s", aVar.cG().getCode(), aVar.getMessage(), aVar.cH(), aVar.cI()) : "Invalid error response received from the token exchange endpoint";
        String str3 = authTokenExchangeType.mFailureMetric;
        String[] strArr = new String[1];
        strArr[0] = aVar == null ? "InvalidErrorResponse" : aVar.cG().name();
        mq.b(str3, strArr);
        if (aVar == null) {
            io.e("OAuthTokenManager", String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", num));
        } else {
            io.e("OAuthTokenManager", String.format("Received error code: %s %n Message: %s %n Detail: %s %n Index: %s", aVar.cG().getCode(), aVar.getMessage(), aVar.cH(), aVar.cI()));
            io.dm("OAuthTokenManager");
            if (aVar.cG() == AuthEndpointErrorParser.AuthErrorType.InvalidToken || aVar.cG() == AuthEndpointErrorParser.AuthErrorType.InvalidValue) {
                if (a(new ds(this.o), authTokenExchangeType)) {
                    try {
                        this.dY.deregisterAccount(str, new bl()).get(5L, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        io.e("OAuthTokenManager", "Exception while waiting for deregistration as the result of an invalid token to complete", e);
                    }
                    return new OAuthTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", format), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), format, aVar, (byte) 0);
                }
                if (!pz.contains(ie.au(this.o))) {
                    io.i("OAuthTokenManager", "This is not enterprise supported device type. Bypassing remote device transfer check.");
                    booleanValue = false;
                } else {
                    io.i("OAuthTokenManager", "Checking hasOngoingRemoteAccountTransfer.");
                    final gp l = gp.l(this.o, "DMS_ATS");
                    long cv = l.cv(ac("timestamp_key", str));
                    String ac = ac("account_transfer_key", str);
                    if (this.F.currentTimeMillis() - cv < px) {
                        Boolean cu = l.cu(ac);
                        io.i("OAuthTokenManager", "AccountTransfer status found in sharedPreference is ".concat(String.valueOf(cu)));
                        booleanValue = cu.booleanValue();
                    } else {
                        h b = h.b(this.o);
                        Callback callback = new Callback() { // from class: com.amazon.identity.auth.device.token.OAuthTokenManager.1
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle) {
                                io.i("OAuthTokenManager", "Failed to get transferred account credential due to " + bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                                OAuthTokenManager.a(OAuthTokenManager.this, l, str, false);
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle) {
                                io.i("OAuthTokenManager", "Successfully get transferred account credential");
                                OAuthTokenManager.a(OAuthTokenManager.this, l, str, true);
                            }
                        };
                        io.i("OAuthTokenManager", "Fetching transferred account credential before triggering account recover bundle");
                        b.c(callback);
                        Boolean cu2 = l.cu(ac);
                        io.i("OAuthTokenManager", "Returning AccountTransfer status from hasOngoingRemoteAccountTransfer is ".concat(String.valueOf(cu2)));
                        mq.incrementCounterAndRecord("FetchTransferredAccountCredentials:".concat(cu2.booleanValue() ? "HasOngoingRemoteAccountTransfer" : "HasNoOngoingRemoteAccountTransfer"), new String[0]);
                        booleanValue = cu2.booleanValue();
                    }
                }
                if (booleanValue) {
                    io.i("OAuthTokenManager", "Return network error due to mismatched account info between the device and DMS side.");
                    return new OAuthTokenManagerException(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", "The account to getAccessToken with is no longer registered."), 3, "The account to getAccessToken with is no longer registered.");
                }
                return new OAuthTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", format), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), format, aVar, fp.eB().bT(str).bU(authTokenExchangeType.name() + ":" + aVar.cG().name()));
            }
            if (aVar.cG() == AuthEndpointErrorParser.AuthErrorType.ActorNotAssociated) {
                io.i("OAuthTokenManager", "Received an ActorNotAssociatedError, expire actor tokens and cookies for actor");
                ai(str, str2);
                ah(str, str2);
                return new OAuthTokenManagerException(MAPError.CommonError.BAD_REQUEST, format, aVar.getRegistrationError().value(), format, aVar);
            }
            if (aVar.cG() == AuthEndpointErrorParser.AuthErrorType.InvalidActorToken) {
                io.i("OAuthTokenManager", "Received an InvalidActorTokenError, expire actor tokens for actor");
                ah(str, str2);
                return new OAuthTokenManagerException(MAPError.CommonError.INTERNAL_ERROR, format, aVar.getRegistrationError().value(), format, aVar);
            }
        }
        return new OAuthTokenManagerException(MAPError.CommonError.PARSE_ERROR, String.format("A ParseError occurred: %s", format), MAPAccountManager.RegistrationError.PARSE_ERROR.value(), format, aVar);
    }

    static /* synthetic */ void a(OAuthTokenManager oAuthTokenManager, gp gpVar, String str, boolean z) {
        gpVar.b(ac("account_transfer_key", str), Boolean.valueOf(z));
        gpVar.a(ac("timestamp_key", str), oAuthTokenManager.F.currentTimeMillis());
    }
}
