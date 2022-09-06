package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.jl;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ha {
    private static final long pn = jj.d(1, TimeUnit.MILLISECONDS);
    private final OAuthTokenManager B;
    private final gd D;
    private eh F;
    private final MAPAccountManager dY;
    private final ed o;
    private gg w;
    private final iy hO = new iy();
    private final hk po = hk.gk();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private final List<MAPCookie> nO;
        private final boolean pp;

        a(List<MAPCookie> list, boolean z) {
            this.nO = list;
            this.pp = z;
        }

        public boolean fP() {
            return this.pp;
        }

        public List<MAPCookie> getCookies() {
            if (hz.isEmpty(this.nO)) {
                io.i("MAPCookieManager", "Cached cookies are empty");
            }
            return this.nO;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class b {
        private final List<MAPCookie> pq;
        private final List<MAPCookie> pr;
        private final String ps;

        public b(List<MAPCookie> list, List<MAPCookie> list2, String str) {
            this.pq = list;
            this.pr = list2;
            this.ps = str;
        }

        public List<MAPCookie> fQ() {
            return this.pq;
        }

        public List<MAPCookie> fR() {
            return this.pr;
        }

        public String fS() {
            return this.ps;
        }
    }

    public ha(Context context) {
        this.o = ed.M(context);
        this.F = (eh) this.o.getSystemService("dcp_system");
        this.B = new OAuthTokenManager(this.o);
        this.dY = new MAPAccountManager(this.o);
        this.D = new ge(this.o).fa();
        this.w = this.o.dV();
    }

    private List<MAPCookie> Z(String str, String str2) {
        return this.D.D(str, str2);
    }

    public static MAPCookie aa(String str, String str2) {
        return new MAPCookie("sid", str, str2, ia.gz(), "/", null, true, true);
    }

    private void ab(String str, String str2) throws MAPCallbackErrorException {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                if (this.dY.isAccountRegistered(str)) {
                    return;
                }
                io.i("MAPCookieManager", "Given account is not registered");
                throw new MAPCallbackErrorException(hf.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, "Given account is not registered", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given account is not registered"));
            }
            io.e("MAPCookieManager", "Given actor is null");
            throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.BAD_REQUEST, "Given actor is null", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given actor is null"));
        }
        io.e("MAPCookieManager", "Given account is null");
        throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.BAD_REQUEST, "Given account is null", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given account is null"));
    }

    private void b(List<MAPCookie> list, String str, boolean z) throws MAPCallbackErrorException {
        if (z && TextUtils.isEmpty(str)) {
            io.e("MAPCookieManager", "Expected url, but did not receive one from getActorCookies request. Cannot proceed.");
            mq.incrementCounterAndRecord("fetchActorCookiesFromServerFailure:MissingExpectedResponseUrl", new String[0]);
            throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.SERVER_ERROR, "Expected url, but did not receive one from getActorCookies request. Cannot proceed.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected url, but did not receive one from getActorCookies request. Cannot proceed."));
        } else if (z || !hz.isEmpty(list)) {
        } else {
            io.e("MAPCookieManager", "Expected cookies, but did not receive them from getActorCookies request");
            mq.incrementCounterAndRecord("fetchActorCookiesFromServerFailure:MissingExpectedCookies", new String[0]);
            throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.SERVER_ERROR, "Expected cookies, but did not receive them from getActorCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected cookies, but did not receive them from getActorCookies request"));
        }
    }

    public static String e(List<MAPCookie> list) {
        if (hz.isEmpty(list)) {
            return null;
        }
        for (MAPCookie mAPCookie : list) {
            if (mAPCookie.getName().equals("sid")) {
                String value = mAPCookie.getValue();
                if (TextUtils.isEmpty(value)) {
                    io.e("MAPCookieManager", "The sid value inside the returned cookies is null or a empty string. There's a bug on server side!");
                    mq.b("EmptySidCookieValueInsideAuthCookiesFromServer", new String[0]);
                }
                return value;
            }
        }
        return null;
    }

    private boolean f(List<MAPCookie> list) {
        if (list != null && list.size() > 0) {
            for (MAPCookie mAPCookie : list) {
                Date expiryDate = mAPCookie.getExpiryDate();
                long currentTimeMillis = this.F.currentTimeMillis();
                if (expiryDate == null) {
                    io.w("MAPCookieManager", String.format("Cookie: %s has null expiry date.", mAPCookie.getName()));
                    return true;
                } else if (currentTimeMillis + pn >= expiryDate.getTime()) {
                    io.i("MAPCookieManager", String.format("Cookie: %s near expiry, refreshing", mAPCookie.getName()));
                    return true;
                }
            }
        }
        return false;
    }

    public static void g(List<MAPCookie> list) {
        if (hz.isEmpty(list)) {
            return;
        }
        for (MAPCookie mAPCookie : list) {
            if (mAPCookie.getName().equals("sid")) {
                io.dm("MAPCookieManager");
                list.remove(mAPCookie);
                return;
            }
        }
    }

    private List<MAPCookie> s(String str, String str2, String str3) {
        return this.D.i(str, str2, str3);
    }

    public Bundle a(String str, String str2, Bundle bundle, ej ejVar) throws MAPCallbackErrorException {
        String str3;
        Object obj;
        if (!TextUtils.isEmpty(str) && !this.dY.isAccountRegistered(str)) {
            io.i("MAPCookieManager", "Account not registered");
            throw new MAPCallbackErrorException(hf.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, "Given account is not registered or directedId is empty", MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Given Account is currently not registered."));
        }
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        String string = bundle2.getString(CookieKeys.Options.KEY_ASSOC_HANDLE);
        "Assoc handle for cookies is: ".concat(String.valueOf(string));
        io.dm("MAPCookieManager");
        boolean z = false;
        if (bundle2.getBoolean("com.amazon.identity.auth.device.internal.cookiekeys.options.ignorefresh", false)) {
            List<MAPCookie> s = s(str, str2, string);
            if (hz.isEmpty(s)) {
                s = new ArrayList<>();
            }
            return a(s, str, str2);
        }
        boolean containsKey = bundle2.containsKey(CookieKeys.Options.KEY_SIGN_IN_URL);
        gp gpVar = new gp(this.o, "token_storage");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CookieKeys.KEY_INVALIDATE_COOKIES);
        outline107.append(this.o.getPackageName());
        outline107.append(str);
        outline107.append(str2);
        String sb = outline107.toString();
        boolean booleanValue = gpVar.cu(sb).booleanValue();
        StringBuilder sb2 = new StringBuilder("InvalidateCookiesKey and value: [");
        sb2.append(sb);
        sb2.append(", ");
        sb2.append(booleanValue);
        sb2.append("]");
        io.dm("MAPCookieManager");
        if (bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false) || containsKey || booleanValue) {
            z = true;
        }
        boolean z2 = z;
        String string2 = bundle2.getString(CookieKeys.Options.KEY_SIGN_IN_URL);
        io.dm("MAPCookieManager");
        a b2 = b(str, str2, string, z2);
        List<MAPCookie> cookies = b2.getCookies();
        boolean fP = b2.fP();
        "returnCachedCookies is ".concat(String.valueOf(fP));
        io.dm("MAPCookieManager");
        boolean f = f(cookies);
        if (!hz.isEmpty(cookies) && !f && fP) {
            io.dm("MAPCookieManager");
            return a(cookies, str, str2);
        }
        io.i("MAPCookieManager", "Fetching cookies from server due to " + ejVar.O(this.o) + ", for domain " + str2 + " and associate handle " + string + " with options forceRefresh=" + z2 + " and with isCookiesInvalid=" + booleanValue + " haveCookiesExpired=" + f + " returnCachedCookies=" + fP);
        if (bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false)) {
            mq.b("getCookiesFromServerWithForceRefresh", new String[0]);
        }
        if (booleanValue) {
            mq.b("getCookiesFromServerWithCookiesInvalidated", new String[0]);
        }
        try {
            String d = !TextUtils.isEmpty(str) ? this.B.d(str, null, ejVar) : null;
            Bundle bundle3 = bundle2;
            String str4 = d;
            b a2 = a(bundle2, str, d, str2, string, string2, cookies, ejVar);
            List<MAPCookie> fQ = a2.fQ();
            String fS = a2.fS();
            a(fQ, fS, containsKey);
            mq.b("fetchCookiesFromServerSuccess", new String[0]);
            if (!hz.isEmpty(fQ)) {
                io.dm("MAPCookieManager");
                Object obj2 = this.po;
                try {
                    synchronized (obj2) {
                        try {
                            if (TextUtils.isEmpty(str)) {
                                io.dm("MAPCookieManager");
                                a(str, str2, string, z2, fQ);
                            } else {
                                String d2 = this.B.d(str, null, ejVar);
                                if (TextUtils.equals(d2, str4)) {
                                    io.dm("MAPCookieManager");
                                    a(str, str2, string, z2, fQ);
                                } else {
                                    io.dm("MAPCookieManager");
                                    mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_CookiesExchange", new String[0]);
                                    a b3 = b(str, str2, string, false);
                                    if (b3.fP()) {
                                        mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_CookiesExchange_ReturnCached", new String[0]);
                                        fQ = b3.getCookies();
                                    } else {
                                        mq.incrementCounterAndRecord("MAP_CID_ATNR_Changed_CookiesExchange_Refresh", new String[0]);
                                        obj = obj2;
                                        str3 = fS;
                                        b a3 = a(bundle3, str, d2, str2, string, string2, b3.getCookies(), ejVar);
                                        List<MAPCookie> fQ2 = a3.fQ();
                                        a(fQ2, a3.fS(), containsKey);
                                        fQ = fQ2;
                                    }
                                }
                            }
                            obj = obj2;
                            str3 = fS;
                        } catch (Throwable th) {
                            th = th;
                            string = obj2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                str3 = fS;
            }
            gpVar.cw(sb);
            return a(fQ, str, str2, str3);
        } catch (OAuthTokenManager.OAuthTokenManagerException e) {
            io.e("MAPCookieManager", String.format("Get error when fetchAuthCookies: %s", e.bF()));
            mq.b("fetchCookiesFromServerFailure:OAuthTokenManagerException", new String[0]);
            throw new MAPCallbackErrorException(hf.getErrorBundle(e.getError(), e.getErrorMessage(), e));
        }
    }

    a c(String str, String str2, String str3, boolean z) throws MAPCallbackErrorException {
        if (!TextUtils.isEmpty(str)) {
            List<MAPCookie> s = s(str, str2, str3);
            boolean z2 = false;
            if (z) {
                return new a(s, false);
            }
            if (!hz.isEmpty(s) && !f(s)) {
                z2 = true;
            }
            return new a(s, z2);
        }
        throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.BAD_REQUEST, "directId in getValidCookieCacheResponseFromDataStore is null!", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "directId in getValidCookieCacheResponseFromDataStore is null!"));
    }

    public List<MAPCookie> cJ(String str) {
        List<MAPCookie> Z = Z(str, null);
        if (hz.isEmpty(Z)) {
            return new ArrayList();
        }
        return f(Z) ? new ArrayList() : Z;
    }

    private List<MAPCookie> c(String str, JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return null;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String trim = ((String) keys.next()).trim();
            JSONArray jSONArray = jSONObject.getJSONArray(trim);
            if (jSONArray != null) {
                arrayList.addAll(a(str, jSONArray, trim));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r8 == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r8 == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.identity.auth.device.ha.a b(java.lang.String r5, java.lang.String r6, java.lang.String r7, boolean r8) {
        /*
            r4 = this;
            r0 = 1
            java.lang.String r1 = "MAPCookieManager"
            r2 = 0
            if (r5 == 0) goto L1e
            com.amazon.identity.auth.device.io.dm(r1)
            java.util.List r5 = r4.s(r5, r6, r7)
            boolean r3 = com.amazon.identity.auth.device.hz.isEmpty(r5)
            if (r3 != 0) goto L19
            com.amazon.identity.auth.device.io.dm(r1)
            if (r8 != 0) goto L28
            goto L27
        L19:
            java.util.List r5 = r4.Z(r6, r7)
            goto L28
        L1e:
            com.amazon.identity.auth.device.io.dm(r1)
            java.util.List r5 = r4.Z(r6, r7)
            if (r8 != 0) goto L28
        L27:
            r2 = r0
        L28:
            com.amazon.identity.auth.device.ha$a r6 = new com.amazon.identity.auth.device.ha$a
            r6.<init>(r5, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ha.b(java.lang.String, java.lang.String, java.lang.String, boolean):com.amazon.identity.auth.device.ha$a");
    }

    a b(String str, String str2, String str3, String str4, boolean z) throws MAPCallbackErrorException {
        ab(str, str2);
        List<MAPCookie> d = this.D.d(str, str2, str3, str4);
        boolean z2 = false;
        if (z) {
            return new a(d, false);
        }
        if (!hz.isEmpty(d) && !f(d)) {
            z2 = true;
        }
        return new a(d, z2);
    }

    public void b(List<MAPCookie> list, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (list == null) {
            io.e("MAPCookieManager", "The currentCookies is null. Cannot add sidCookie!");
            return;
        }
        String z = this.w.z(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
        if (!TextUtils.isEmpty(z)) {
            if (!TextUtils.isEmpty(e(list))) {
                io.w("MAPCookieManager", "Before we add the sid cookie to the cookies that will be sent out, there's already a sid cookie inside; this is a bug since we should have removed the sid cookie before we saved the cookies.");
                mq.b("SidCookieExistsInCookiesBeforeAddingOne", new String[0]);
                g(list);
            }
            String cZ = hr.cZ(str2);
            "Add the sid cookie to cookies that will be sent out. The domain we use to create the sid cookies is ".concat(String.valueOf(cZ));
            io.dm("MAPCookieManager");
            list.add(aa(z, cZ));
        } else if (TextUtils.isEmpty(e(list))) {
        } else {
            io.e("MAPCookieManager", "There's a sid cookie in the cookies that will be sent out, but we didn't update the sid; this is a bug since we should have updated the sid and removed the sid cookie before we saved the cookies.");
            mq.b("SidCookieExistsButHaveNotBeenSavedAsAToken", new String[0]);
        }
    }

    private b b(String str, JSONObject jSONObject) {
        String str2;
        JSONObject jSONObject2;
        List<MAPCookie> arrayList;
        List<MAPCookie> arrayList2;
        try {
            jSONObject2 = jSONObject.getJSONObject("response");
            str2 = jSONObject2.optString("uri");
        } catch (JSONException unused) {
            str2 = null;
        }
        try {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("tokens");
            try {
                "Parsing account cookies Response for id=".concat(String.valueOf(str));
                io.dm("MAPCookieManager");
                arrayList = c(str, jSONObject3.getJSONObject(AccountManagerConstants.GetCookiesParams.COOKIES));
            } catch (JSONException unused2) {
                arrayList = new ArrayList<>();
            }
            try {
                "Parsing actorCookies Response for id=".concat(String.valueOf(str));
                io.dm("MAPCookieManager");
                arrayList2 = c(str, jSONObject3.getJSONObject("actor_cookies"));
            } catch (JSONException unused3) {
                arrayList2 = new ArrayList<>();
            }
            return new b(arrayList, arrayList2, str2);
        } catch (JSONException unused4) {
            return new b(new ArrayList(), new ArrayList(), str2);
        }
    }

    private void a(List<MAPCookie> list, String str, boolean z) throws MAPCallbackErrorException {
        if (z && TextUtils.isEmpty(str)) {
            io.e("MAPCookieManager", "Expected url, but did not receive one from cookies request. Cannot proceed.");
            mq.b("fetchCookiesFromServerFailure:MissingExpectedResponseUrl", new String[0]);
            throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.INVALID_RESPONSE, "Expected a URL, but did not receive one from the getCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected url, but did not receive one from getCookies request"));
        } else if (z || !hz.isEmpty(list)) {
        } else {
            io.e("MAPCookieManager", "Expected cookies, but did not receive them from getCookies request");
            mq.b("fetchCookiesFromServerFailure:MissingExpectedCookies", new String[0]);
            throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.INVALID_RESPONSE, "Expected cookies, but did not receive them from the getCookies request", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "Expected cookies, but did not receive them from getCookies request"));
        }
    }

    public Bundle a(String str, String str2, String str3, Bundle bundle, ej ejVar) throws MAPCallbackErrorException {
        String str4;
        String str5;
        Object obj;
        ab(str, str2);
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        String string = bundle2.getString(CookieKeys.Options.KEY_ASSOC_HANDLE);
        "Assoc handle for actor cookies is: ".concat(String.valueOf(string));
        io.dm("MAPCookieManager");
        boolean containsKey = bundle2.containsKey(CookieKeys.Options.KEY_SIGN_IN_URL);
        gp gpVar = new gp(this.o, "token_storage");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CookieKeys.KEY_INVALIDATE_COOKIES);
        outline107.append(this.o.getPackageName());
        outline107.append(str);
        outline107.append(str3);
        outline107.append(str2);
        String sb = outline107.toString();
        boolean booleanValue = gpVar.cu(sb).booleanValue();
        StringBuilder sb2 = new StringBuilder("InvalidateCookiesKey and value: [");
        sb2.append(sb);
        sb2.append(", ");
        sb2.append(booleanValue);
        sb2.append("] for getCookiesForActor");
        io.dm("MAPCookieManager");
        boolean z = bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false) || containsKey || booleanValue;
        String string2 = bundle2.getString(CookieKeys.Options.KEY_SIGN_IN_URL);
        io.dm("MAPCookieManager");
        a c = c(str, str3, string, z);
        List<MAPCookie> cookies = c.getCookies();
        boolean z2 = z;
        a b2 = b(str, str2, str3, string, z2);
        List<MAPCookie> cookies2 = b2.getCookies();
        boolean z3 = c.fP() && b2.fP();
        io.i("MAPCookieManager", "areBothCookiesValid from cache responses: ".concat(String.valueOf(z3)));
        if (z3) {
            List<MAPCookie> a2 = a(cookies, cookies2);
            io.dm("MAPCookieManager");
            return a(a2, str, str3);
        }
        io.i("MAPCookieManager", "Fetching actor cookies from server due to " + ejVar.O(this.o) + ", for domain " + str3 + " and associate handle " + string + " with options forceRefresh=" + z2 + " and with isCookiesInvalid=" + booleanValue + " , should return from cache: " + z3);
        if (bundle2.getBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, false)) {
            mq.incrementCounterAndRecord("getActorCookiesFromServerWithForceRefresh", new String[0]);
        }
        if (booleanValue) {
            mq.incrementCounterAndRecord("getActorCookiesFromServerWithCookiesInvalidated", new String[0]);
        }
        List<MAPCookie> a3 = a(cookies, cookies2, str3, string);
        try {
            String d = this.B.d(str, null, ejVar);
            String e = this.B.e(str, str2, ejVar);
            Bundle bundle3 = bundle2;
            b a4 = a(bundle2, str, d, str2, e, str3, string, string2, a3, ejVar);
            List<MAPCookie> fQ = a4.fQ();
            List<MAPCookie> fR = a4.fR();
            String fS = a4.fS();
            b(fR, fS, containsKey);
            mq.incrementCounterAndRecord("fetchActorCookiesFromServerSuccess", new String[0]);
            if (hz.isEmpty(fR) || hz.isEmpty(fQ)) {
                str4 = str3;
                str5 = fS;
                if (!containsKey) {
                    throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.PARSE_ERROR, "Account cookies or actor cookies from server is empty.", MAPAccountManager.RegistrationError.PARSE_ERROR.value(), "Account cookies or actor cookies from server is empty."));
                }
            } else {
                io.dm("MAPCookieManager");
                Object obj2 = this.po;
                try {
                    synchronized (obj2) {
                        try {
                            String e2 = this.B.e(str, str2, ejVar);
                            String d2 = this.B.d(str, null, ejVar);
                            if (TextUtils.equals(e, e2) && TextUtils.equals(d, d2)) {
                                io.i("MAPCookieManager", "Actor or account refresh token is not changed, store it.");
                                a(str, str3, string, z2, fQ);
                                this.D.b(str, str2, str3, string, fR);
                                str4 = str3;
                                obj = obj2;
                                str5 = fS;
                            } else {
                                io.i("MAPCookieManager", "Actor or account refresh token has been changed, try using the cached cookies");
                                mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_CookiesExchange", new String[0]);
                                a c2 = c(str, str3, string, false);
                                a b3 = b(str, str2, str3, string, false);
                                if (c2.fP() && b3.fP()) {
                                    mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_CookiesExchange_ReturnCached", new String[0]);
                                    io.i("MAPCookieManager", "Returning cached cookies refreshed by other threads");
                                    str5 = fS;
                                    fQ = c2.getCookies();
                                    str4 = str3;
                                    obj = obj2;
                                    fR = b3.getCookies();
                                } else {
                                    mq.incrementCounterAndRecord("MAP_CID_PID_ATNR_Changed_CookiesExchange_Refresh", new String[0]);
                                    io.i("MAPCookieManager", "Cached cookies is invalid, refresh it.");
                                    str4 = str3;
                                    obj = obj2;
                                    str5 = fS;
                                    b a5 = a(bundle3, str, d2, str2, e2, str3, string, string2, a(c2.getCookies(), b3.getCookies(), str3, string), ejVar);
                                    b(a5.fR(), a5.fS(), containsKey);
                                    List<MAPCookie> fQ2 = a5.fQ();
                                    fR = a5.fR();
                                    fQ = fQ2;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            string2 = obj2;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            gpVar.cw(sb);
            return a(a(fQ, fR), str, str4, str5);
        } catch (OAuthTokenManager.OAuthTokenManagerException e3) {
            io.e("MAPCookieManager", String.format("Get error when fetchAuthCookies: %s", e3.bF()));
            mq.b("fetchCookiesFromServerFailure:OAuthTokenManagerException", new String[0]);
            throw new MAPCallbackErrorException(hf.getErrorBundle(e3.getError(), e3.getErrorMessage(), e3));
        }
    }

    private List<MAPCookie> a(List<MAPCookie> list, List<MAPCookie> list2, String str, String str2) throws MAPCallbackErrorException {
        if (hz.isEmpty(list) || hz.isEmpty(list2)) {
            return !hz.isEmpty(list) ? list : Z(str, str2);
        }
        return a(list, list2);
    }

    private void a(String str, String str2, String str3, boolean z, List<MAPCookie> list) {
        if (TextUtils.isEmpty(str)) {
            this.D.a(str2, str3, list);
            return;
        }
        a(list, str);
        this.D.b(str, str2, str3, list);
        if (z) {
            return;
        }
        this.D.a(str2, str3, (List<MAPCookie>) null);
        "Cleared non-auth cookies for domain:".concat(String.valueOf(str2));
        io.dm("MAPCookieManager");
    }

    public void a(List<MAPCookie> list, String str) {
        String e = e(list);
        if (!TextUtils.isEmpty(e)) {
            g(list);
            if (e.equals(this.w.z(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN))) {
                return;
            }
            io.dm("MAPCookieManager");
            this.w.f(str, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN, e);
        }
    }

    public static void a(List<MAPCookie> list, Map<String, String> map) {
        String e = e(list);
        if (!TextUtils.isEmpty(e)) {
            g(list);
            io.dm("MAPCookieManager");
            map.put(AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN, e);
        }
    }

    private Bundle a(List<MAPCookie> list, String str, String str2) {
        return a(list, str, str2, (String) null);
    }

    private Bundle a(List<MAPCookie> list, String str, String str2, String str3) {
        if (list == null) {
            list = new ArrayList<>();
        }
        b(list, str, str2);
        Bundle i = ia.i(list);
        if (!TextUtils.isEmpty(str3)) {
            i.putString(CookieKeys.KEY_RESPONSE_URL, str3);
        }
        return i;
    }

    b a(Bundle bundle, String str, String str2, String str3, String str4, String str5, List<MAPCookie> list, ej ejVar) throws MAPCallbackErrorException {
        return a(bundle, str, str2, null, null, str3, str4, str5, list, ejVar);
    }

    b a(Bundle bundle, String str, String str2, String str3, String str4, String str5, String str6, String str7, List<MAPCookie> list, ej ejVar) throws MAPCallbackErrorException {
        boolean z = !TextUtils.isEmpty(str3);
        io.i("MAPCookieManager", "Fetching cookies... Is is for actor : ".concat(String.valueOf(z)));
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                mv aD = mq.aD("MAPCookieManager", z ? "fetchActorCookiesFromServer" : "fetchCookiesFromServer");
                URL a2 = a(bundle, str5, str);
                if (a2 != null) {
                    io.i("MAPCookieManager", "Using CookieExchangeToken URL: " + a2.toString());
                    jl.b a3 = a(str5, str, str2, str3, str4, str7, str6, mz.aY(this.o) ? ejVar.O(this.o) : null);
                    ArrayList arrayList = list == null ? new ArrayList() : list;
                    b(arrayList, str, str5);
                    HttpURLConnection a4 = this.hO.a(this.o, a2, a3, arrayList, str, ejVar);
                    int d = RetryLogic.d(a4);
                    io.i("MAPCookieManager", "Headers received update request to exchange token endpoint");
                    JSONObject f = ik.f(a4);
                    aD.stop();
                    io.i("MAPCookieManager", "Response parsed for cookie request to exchange token endpoint");
                    if (!this.hO.a(Integer.valueOf(d)) && f != null) {
                        io.i("MAPCookieManager", "Parsing getCookies or getCookiesForActor success response");
                        StringBuilder sb = new StringBuilder("DirectedId = ");
                        sb.append(str);
                        sb.append(", ActorId = ");
                        sb.append(str3);
                        io.dm("MAPCookieManager");
                        b b2 = b(str, f);
                        a4.disconnect();
                        return b2;
                    }
                    Object[] objArr = new Object[1];
                    objArr[0] = f != null ? f.toString() : "Null Json Response";
                    io.a("Error Response: %s", objArr);
                    AuthEndpointErrorParser.a g = new AuthEndpointErrorParser().g(f);
                    if (g != null) {
                        OAuthTokenManager.OAuthTokenManagerException a5 = this.B.a(str, str3, g, Integer.valueOf(d), OAuthTokenManager.AuthTokenExchangeType.OauthRefreshToCookieExchange);
                        String format = String.format("Received Error code %s from the server. Message: %s. Detail: %s. Index: %s.", g.cG().getCode(), g.getMessage(), g.cH(), g.cI());
                        Bundle a6 = hf.a(MAPError.CommonError.PARSE_ERROR, format, a5.fT().getRegistrationError().value(), format);
                        io.e("MAPCookieManager", "Panda error index: " + g.cI());
                        io.dm("MAPCookieManager");
                        fp eE = a5.eE();
                        if (eE != null) {
                            a6.putBundle("com.amazon.identity.mobi.account.recover.context", eE.toBundle());
                        }
                        a6.putBoolean(MAPError.KEY_SHOULD_CLEAR_AUTH_COOKIES, a5.fU());
                        throw new MAPCallbackErrorException(a6);
                    }
                    String format2 = String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", Integer.valueOf(d));
                    throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.SERVER_ERROR, format2, MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), format2));
                }
                throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.BAD_REQUEST, "Invalid Parameter: Domain", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Invalid Parameter: Domain"));
            } catch (IOException e) {
                io.e("MAPCookieManager", "Got IOException when fetching Cookie from server ", e);
                mq.b("fetchCookiesFromServerFailure:IOException", new String[0]);
                mq.incrementCounterAndRecord("NetworkError1:MAPCookieManager", new String[0]);
                throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", e.getMessage()), MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), e.getMessage()));
            } catch (JSONException e2) {
                io.e("MAPCookieManager", "Got JSONException while parsing response ", e2);
                mq.b("fetchCookiesFromServerFailure:JSONException", new String[0]);
                throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.PARSE_ERROR, String.format("Received a JSONException while parsing server response with message: %s", e2.getMessage()), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), e2.getMessage()));
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public List<MAPCookie> a(String str, JSONArray jSONArray) throws JSONException {
        return a(str, jSONArray, (String) null);
    }

    private List<MAPCookie> a(String str, JSONArray jSONArray, String str2) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            MAPCookie mAPCookie = new MAPCookie(jSONObject.getString(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_NAME), jSONObject.getString(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_VALUE), str2, str, jSONObject.getBoolean(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_SECURE));
            if (TextUtils.isEmpty(str2)) {
                mAPCookie.setDomain(jSONObject.getString(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_DOMAIN));
            }
            mAPCookie.setPath(jSONObject.getString(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_PATH));
            if (jSONObject.has("Expires")) {
                mAPCookie.cI(jSONObject.getString("Expires"));
            }
            mAPCookie.setHttpOnly(jSONObject.getBoolean(com.amazon.identity.auth.map.device.token.MAPCookie.KEY_HTTP_ONLY));
            arrayList.add(mAPCookie);
        }
        return arrayList;
    }

    private URL a(Bundle bundle, String str, String str2) {
        String be;
        try {
            String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
            if (!TextUtils.isEmpty(string)) {
                io.i("MAPCookieManager", "Using explicitly passed endpoint for cookie exchange : ".concat(String.valueOf(string)));
            } else {
                if (TextUtils.isEmpty(str2)) {
                    be = EnvironmentUtils.cd().be(str);
                } else {
                    be = EnvironmentUtils.cd().be(str);
                    if (TextUtils.isEmpty(be)) {
                        string = hr.c(this.o, str2);
                        io.i("MAPCookieManager", String.format("Cookies exchange panda host: %s", string));
                    }
                }
                string = EnvironmentUtils.cd().getPandaHost(be);
                io.i("MAPCookieManager", String.format("Cookies exchange panda host: %s", string));
            }
            EnvironmentUtils.cd();
            return new URL("https", string, 443, "/ap/exchangetoken/cookies");
        } catch (MalformedURLException e) {
            io.e("MAPCookieManager", "Error parsing URL", e);
            return null;
        }
    }

    jl.b a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) throws MAPCallbackErrorException {
        jl.b gT = jl.gT();
        gT.av("requested_token_type", "auth_cookies");
        gT.aI(this.o);
        gT.av("domain", str);
        io.i("MAPCookieManager", String.format("Use associate handle %s to exchange cookies", str7));
        if (!TextUtils.isEmpty(str7)) {
            gT.av("openid.assoc_handle", str7);
        }
        if (TextUtils.isEmpty(str4)) {
            if (!TextUtils.isEmpty(str2)) {
                io.i("MAPCookieManager", "Requesting all cookies");
                a(str3, gT);
            } else {
                io.i("MAPCookieManager", "Requesting just non-auth cookies");
            }
        } else {
            ab(str2, str4);
            a(str3, gT);
            gT.av("actor_refresh_token", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            gT.av("url", str6);
        }
        if (!TextUtils.isEmpty(str8)) {
            gT.av("calling_package_name", str8);
        }
        return gT;
    }

    private void a(String str, jl.b bVar) {
        bVar.av("source_token_type", AbstractJSONTokenResponse.REFRESH_TOKEN);
        bVar.av("source_token", str);
    }

    static List<MAPCookie> a(List<MAPCookie> list, List<MAPCookie> list2) throws MAPCallbackErrorException {
        if (!hz.isEmpty(list) && !hz.isEmpty(list2)) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list2);
            int size = list2.size();
            for (MAPCookie mAPCookie : list) {
                if (a(mAPCookie, list2)) {
                    size--;
                } else {
                    arrayList.add(mAPCookie);
                }
            }
            if (size != 0) {
                io.i("MAPCookieManager", "Cookies number not match! Return anyway...");
                mq.incrementCounterAndRecord("WRONG_ACTOR_COOKIES_NUMBER_DIFF:".concat(String.valueOf(size)), new String[0]);
            }
            return arrayList;
        }
        mq.incrementCounterAndRecord("NO_COOKIES_WHEN_REPLACE_ACTOR_COOKIES", new String[0]);
        throw new MAPCallbackErrorException(hf.a(MAPError.CommonError.BAD_REQUEST, "No existing cookies, have you called getCookiesForActor before?", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "No existing cookies, have you called getCookiesForActor before?"));
    }

    private static boolean a(MAPCookie mAPCookie, List<MAPCookie> list) {
        for (MAPCookie mAPCookie2 : list) {
            if (TextUtils.equals(mAPCookie2.getDomain(), mAPCookie.getDomain()) && TextUtils.equals(mAPCookie2.getName(), mAPCookie.getName())) {
                return true;
            }
        }
        return false;
    }
}
