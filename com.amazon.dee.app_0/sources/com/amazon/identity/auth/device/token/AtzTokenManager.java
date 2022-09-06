package com.amazon.identity.auth.device.token;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.bz;
import com.amazon.identity.auth.device.dependency.PandaServiceAccessor;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.eh;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gm;
import com.amazon.identity.auth.device.gv;
import com.amazon.identity.auth.device.im;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.je;
import com.amazon.identity.auth.device.jj;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AtzTokenManager {
    private static final String TAG = "com.amazon.identity.auth.device.token.AtzTokenManager";
    private static final long pn = jj.d(1, TimeUnit.MILLISECONDS);
    private final eh F;
    private final PandaServiceAccessor gS;
    private final ed o;
    private final gg w;

    public AtzTokenManager(Context context) {
        this(context, new PandaServiceAccessor(context), new gm(context));
    }

    public String a(String str, String str2, im imVar, Bundle bundle, ej ejVar) throws AtzTokenManagerException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z;
        if (!TextUtils.isEmpty(str2)) {
            if (TextUtils.equals("com.amazon.dcp.sso.token.oauth.atz.access_token", imVar.getKey())) {
                if (!TextUtils.isEmpty(bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID))) {
                    try {
                        String z2 = this.w.z(str, imVar.gA());
                        JSONObject jSONObject = null;
                        if (z2 != null) {
                            jSONObject = new JSONObject(z2);
                        }
                        if (bundle.getBoolean(TokenKeys.Options.KEY_FORCE_REFRESH_OAUTH)) {
                            io.i(TAG, "Force refresh the ATZA token.");
                            ejVar.bA("ATZ_FORCE_REFRESH_OAUTH");
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z && !a(str, jSONObject, bundle, imVar)) {
                            return jSONObject.getString("token");
                        }
                        OAuthTokenManager.a a = this.gS.a(str, new bz(this.o, str2, bundle.getString(TokenKeys.KEY_LWA_APPLICATION_ID), bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID), bundle.getStringArrayList(TokenKeys.KEY_LWA_REQUESTED_SCOPES)), ejVar);
                        mq.b("exchangeAtnrForAtzaTokenSuccess", new String[0]);
                        String string = bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID);
                        String packageName = imVar.getPackageName();
                        int i6 = a.pH;
                        String str3 = a.ne;
                        String str4 = a.mAccessToken;
                        long currentTimeMillis = System.currentTimeMillis();
                        long convert = TimeUnit.MILLISECONDS.convert(i6, TimeUnit.SECONDS) + currentTimeMillis;
                        String W = gv.W(packageName, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
                        String atzTokenKeyForPackage = TokenKeys.getAtzTokenKeyForPackage(packageName);
                        try {
                            String W2 = gv.W(packageName, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_EXPIRES_AT);
                            String W3 = gv.W(packageName, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_REFRESHED_AT);
                            HashMap hashMap = new HashMap();
                            if (!TextUtils.isEmpty(str3)) {
                                hashMap.put(W, str3);
                            }
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("token", str4);
                            jSONObject2.put(TokenKeys.KEY_LWA_CLIENT_ID, string);
                            hashMap.put(atzTokenKeyForPackage, jSONObject2.toString());
                            hashMap.put(W2, Long.toString(convert));
                            hashMap.put(W3, Long.toString(currentTimeMillis));
                            this.w.d(str, hashMap);
                            return a.mAccessToken;
                        } catch (PandaServiceAccessor.PandaServiceException e) {
                            e = e;
                            i5 = 0;
                            mq.b("exchangeAtnrForAtzaTokenFailure:PandaServiceException", new String[i5]);
                            throw new AtzTokenManagerException(e.getError(), e.getErrorMessage(), e.bE(), e.bF());
                        } catch (IOException e2) {
                            e = e2;
                            i4 = 0;
                            mq.b("exchangeAtnrForAtzaTokenFailure:IOException", new String[i4]);
                            mq.incrementCounterAndRecord("NetworkError12:AtzTokenManager", new String[i4]);
                            MAPError.CommonError commonError = MAPError.CommonError.NETWORK_ERROR;
                            Object[] objArr = new Object[1];
                            objArr[i4] = e.getMessage();
                            throw new AtzTokenManagerException(commonError, String.format("Network error occurred: %s", objArr), 3, e.getMessage());
                        } catch (UnsupportedOperationException e3) {
                            e = e3;
                            i3 = 0;
                            mq.b("exchangeAtnrForAtzaTokenFailure:UnsupportedOperationException", new String[i3]);
                            MAPError.AccountError accountError = MAPError.AccountError.CUSTOMER_NOT_FOUND;
                            throw new AtzTokenManagerException(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), e);
                        } catch (ParseException e4) {
                            e = e4;
                            i2 = 0;
                            mq.b("exchangeAtnrForAtzaTokenFailure:ParseException", new String[i2]);
                            MAPError.CommonError commonError2 = MAPError.CommonError.PARSE_ERROR;
                            Object[] objArr2 = new Object[1];
                            objArr2[i2] = e.getMessage();
                            throw new AtzTokenManagerException(commonError2, String.format("ParseException occurred: %s", objArr2), 5, e.getMessage());
                        } catch (JSONException e5) {
                            e = e5;
                            i = 0;
                            mq.b("exchangeAtnrForAtzaTokenFailure:JSONException", new String[i]);
                            MAPError.CommonError commonError3 = MAPError.CommonError.INVALID_RESPONSE;
                            Object[] objArr3 = new Object[1];
                            objArr3[i] = e.getMessage();
                            throw new AtzTokenManagerException(commonError3, String.format("JSONException occurred: %s", objArr3), 5, e.getMessage());
                        }
                    } catch (PandaServiceAccessor.PandaServiceException e6) {
                        e = e6;
                        i5 = 0;
                    } catch (IOException e7) {
                        e = e7;
                        i4 = 0;
                    } catch (UnsupportedOperationException e8) {
                        e = e8;
                        i3 = 0;
                    } catch (ParseException e9) {
                        e = e9;
                        i2 = 0;
                    } catch (JSONException e10) {
                        e = e10;
                        i = 0;
                    }
                } else {
                    throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Client id is missing from the passed options bundle", 7, "Client id is missing from options passed.");
                }
            } else {
                String format = String.format("Token key %s is not a valid key", imVar.gA());
                throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, format, 7, format);
            }
        } else {
            throw new AtzTokenManagerException(MAPError.CommonError.BAD_REQUEST, "Given AtnToken is not valid", 8, "Given AtnToken is not valid");
        }
    }

    public String b(String str, im imVar, Bundle bundle) {
        JSONObject jSONObject;
        String z = this.w.z(str, imVar.gA());
        if (z != null) {
            try {
                jSONObject = new JSONObject(z);
            } catch (JSONException unused) {
                return null;
            }
        } else {
            jSONObject = null;
        }
        if (a(str, jSONObject, bundle, imVar)) {
            return null;
        }
        return jSONObject.getString("token");
    }

    public AtzTokenManager(Context context, PandaServiceAccessor pandaServiceAccessor, gg ggVar) {
        this.o = ed.M(context);
        this.gS = pandaServiceAccessor;
        this.w = ggVar;
        this.F = (eh) this.o.getSystemService("dcp_system");
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class AtzTokenManagerException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;

        public AtzTokenManagerException(MAPError mAPError, String str, int i, String str2) {
            super(str2);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = str2;
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public int bE() {
            return this.mLegacyErrorCode;
        }

        public String bF() {
            return this.mLegacyErrorMessage;
        }

        public MAPError getError() {
            return this.mError;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        public AtzTokenManagerException(MAPError mAPError, String str, int i, Throwable th) {
            super(th.getMessage(), th);
            this.mLegacyErrorCode = i;
            this.mLegacyErrorMessage = th.getMessage();
            this.mError = mAPError;
            this.mErrorMessage = str;
        }
    }

    private boolean a(String str, im imVar, Bundle bundle) {
        Long dB;
        String z = this.w.z(str, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_REFRESHED_AT));
        if (z == null) {
            io.i(TAG, "No previous token refresh time found. Possible case of no cached token. Refreshing...");
            return true;
        }
        long currentTimeMillis = this.F.currentTimeMillis();
        if (!TextUtils.isEmpty(z) && (dB = je.dB(z)) != null && currentTimeMillis < dB.longValue()) {
            io.i(TAG, "Clock skew detected. Refreshing...");
            return true;
        }
        Long dB2 = je.dB(this.w.z(str, gv.W(imVar.getPackageName(), AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_EXPIRES_AT)));
        if (dB2 != null) {
            if ((bundle.getLong(TokenKeys.Options.KEY_OAUTH_TTL_MS_LONG, jj.d(15L, TimeUnit.MILLISECONDS)) + Long.valueOf(currentTimeMillis).longValue()) + pn >= dB2.longValue()) {
                io.i(TAG, "Atz access token near or past expiry. Refreshing...");
                return true;
            }
        }
        return false;
    }

    private boolean a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        return TextUtils.equals(jSONObject.getString(TokenKeys.KEY_LWA_CLIENT_ID), str);
    }

    private boolean a(String str, JSONObject jSONObject, Bundle bundle, im imVar) throws JSONException {
        return jSONObject == null || a(str, imVar, bundle) || !a(jSONObject, bundle.getString(TokenKeys.KEY_LWA_CLIENT_ID));
    }
}
