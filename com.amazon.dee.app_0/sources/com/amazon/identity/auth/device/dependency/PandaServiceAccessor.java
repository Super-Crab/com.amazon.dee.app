package com.amazon.identity.auth.device.dependency;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.bf;
import com.amazon.identity.auth.device.bh;
import com.amazon.identity.auth.device.ca;
import com.amazon.identity.auth.device.cb;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.hb;
import com.amazon.identity.auth.device.hr;
import com.amazon.identity.auth.device.ik;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iy;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class PandaServiceAccessor {
    private static final String TAG = "com.amazon.identity.auth.device.dependency.PandaServiceAccessor";
    private final ed o;
    private final iy hO = new iy();
    private final AuthEndpointErrorParser aU = new AuthEndpointErrorParser();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class PandaServiceException extends Exception {
        private final MAPError mError;
        private final String mErrorMessage;
        private final int mLegacyErrorCode;
        private final String mLegacyErrorMessage;

        public PandaServiceException(MAPError mAPError, String str, String str2) {
            super(str2);
            this.mLegacyErrorCode = 5;
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
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a {
        private final JSONObject hP;
        private final Map<String, List<String>> hQ;
        private final int mResponseCode;

        public a(int i, JSONObject jSONObject, Map<String, List<String>> map) {
            this.mResponseCode = i;
            this.hP = jSONObject;
            this.hQ = map;
        }

        public JSONObject bG() {
            return this.hP;
        }

        public Date bH() {
            Calendar calendar = Calendar.getInstance();
            Map<String, List<String>> map = this.hQ;
            if (map != null && map.containsKey("Cache-Control")) {
                List<String> list = this.hQ.get("Cache-Control");
                if (!list.isEmpty()) {
                    String str = list.get(0);
                    if (str.contains("max-age=")) {
                        try {
                            calendar.add(13, Integer.parseInt(str.substring(str.indexOf("max-age=") + 8)));
                        } catch (NumberFormatException unused) {
                            io.w(PandaServiceAccessor.TAG, String.format("Cache-Control header has malformed value: %s", str));
                        }
                    }
                }
            }
            return calendar.getTime();
        }

        public int getResponseCode() {
            return this.mResponseCode;
        }
    }

    public PandaServiceAccessor(Context context) {
        this.o = ed.M(context);
    }

    public OAuthTokenManager.a a(String str, cb cbVar, ej ejVar) throws JSONException, IOException, ParseException, PandaServiceException {
        HttpURLConnection httpURLConnection = null;
        try {
            URL a2 = a(str, cbVar);
            httpURLConnection = this.hO.a(cbVar.bC(), a2, cbVar.a(ejVar), (List<MAPCookie>) null, str, cbVar.bC().getPackageName(), ejVar);
            int d = RetryLogic.d(httpURLConnection);
            io.i(TAG, String.format("Call to %s with request-id %s ended with status %d", a2, httpURLConnection.getHeaderField("X-Amzn-RequestId"), Integer.valueOf(d)));
            a aVar = new a(d, ik.f(httpURLConnection), httpURLConnection.getHeaderFields());
            a(aVar);
            OAuthTokenManager.a l = new hb(this.o, this.hO).l(aVar.bG());
            httpURLConnection.disconnect();
            return l;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public a b(bf bfVar, ej ejVar) throws JSONException, IOException, PandaServiceException {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = this.hO.a(bfVar.bC(), EnvironmentUtils.cd().n(EnvironmentUtils.cd().getPandaHost(".amazon.com"), bfVar.bo()), bfVar.bp(), (List<MAPCookie>) null, (String) null, bfVar.bC().getPackageName(), ejVar);
            a aVar = new a(RetryLogic.d(httpURLConnection), ik.f(httpURLConnection), httpURLConnection.getHeaderFields());
            a(aVar);
            httpURLConnection.disconnect();
            return aVar;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public a a(String str, bh bhVar, ej ejVar) throws JSONException, IOException, PandaServiceException {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = this.hO.a(bhVar.bC(), a(str, bhVar), bhVar.bp(), (List<MAPCookie>) null, str, bhVar.bC().getPackageName(), ejVar);
            a aVar = new a(RetryLogic.d(httpURLConnection), ik.f(httpURLConnection), httpURLConnection.getHeaderFields());
            a(aVar);
            httpURLConnection.disconnect();
            return aVar;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private URL a(String str, ca caVar) {
        try {
            return EnvironmentUtils.cd().n(hr.c(caVar.bC(), str), caVar.bo());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Should never occur, hardcoded constant.", e);
        }
    }

    private void a(a aVar) throws PandaServiceException {
        JSONObject bG = aVar.bG();
        int responseCode = aVar.getResponseCode();
        if (this.hO.a(Integer.valueOf(responseCode)) || bG == null) {
            Object[] objArr = new Object[1];
            objArr[0] = bG != null ? bG.toString() : "Null Json Response from Panda Service";
            io.a("Error Response: %s", objArr);
            String a2 = a(this.aU.g(bG), responseCode);
            throw new PandaServiceException(MAPError.CommonError.INVALID_RESPONSE, a2, a2);
        }
    }

    private String a(AuthEndpointErrorParser.a aVar, int i) {
        return aVar != null ? String.format("Received Error code %s from the server. Message: %s Detail: %s Index: %s", aVar.cG().getCode(), aVar.getMessage(), aVar.cH(), aVar.cI()) : String.format(Locale.ENGLISH, "Received unrecognized error from the server with status code %d", Integer.valueOf(i));
    }
}
