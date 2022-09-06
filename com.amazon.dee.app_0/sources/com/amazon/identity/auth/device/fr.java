package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class fr {
    private static final String TAG = "com.amazon.identity.auth.device.fr";
    private mv mZ;
    protected final ed o;

    public fr(ed edVar) {
        this.o = edVar;
    }

    protected void a(HttpsURLConnection httpsURLConnection, List<MAPCookie> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (MAPCookie mAPCookie : list) {
            httpsURLConnection.addRequestProperty("Cookie", String.format("%s=%s", mAPCookie.getName(), mAPCookie.getValue()));
        }
    }

    protected abstract JSONObject b(ej ejVar) throws JSONException;

    /* JADX WARN: Not initialized variable reg: 9, insn: 0x02b5: MOVE  (r14 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:77:0x02b5 */
    public a c(ej ejVar) {
        HttpsURLConnection httpsURLConnection;
        HttpsURLConnection httpsURLConnection2;
        HttpsURLConnection httpsURLConnection3;
        HttpsURLConnection httpsURLConnection4;
        URL eM = eM();
        int i = 2;
        try {
            try {
                String str = TAG;
                io.i(str, "Starting request to amazon backend service. URL : " + eM.toString());
                this.mZ = ejVar.bz(mp.h(eM));
                HttpsURLConnection d = d(ejVar);
                try {
                    httpsURLConnection = eH() != null ? (HttpsURLConnection) cy.openConnection(d, eH()) : d;
                    try {
                        httpsURLConnection.setDoOutput(true);
                        httpsURLConnection.setDoInput(true);
                        httpsURLConnection.setRequestProperty("Accept", "application/json");
                        httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                        httpsURLConnection.setRequestProperty("x-amzn-identity-auth-domain", eG());
                        a(httpsURLConnection);
                        a(httpsURLConnection, eI());
                        JSONObject b = b(ejVar);
                        jd.a(httpsURLConnection.getOutputStream(), b.toString().getBytes());
                        io.dm(TAG);
                        for (Map.Entry entry : httpsURLConnection.getRequestProperties().entrySet()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append((String) entry.getKey());
                            sb.append(RealTimeTextConstants.COLON_SPACE);
                            for (String str2 : (List) entry.getValue()) {
                                sb.append(str2);
                                sb.append(", ");
                            }
                            io.dm(TAG);
                        }
                        String str3 = TAG;
                        new StringBuilder("Panda request body: ").append(b.toString());
                        io.dm(str3);
                        int d2 = RetryLogic.d(httpsURLConnection);
                        this.mZ.iM();
                        if (RetryLogic.j(d2)) {
                            ejVar.incrementCounter(mp.j(eM), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                        } else {
                            ejVar.incrementCounter(mp.j(eM), 1.0d);
                        }
                        String path = getPath();
                        io.i(TAG, String.format("Backend service %s returned response code: %d", eM.toString(), Integer.valueOf(d2)));
                        if (AuthEndpointErrorParser.a(Integer.valueOf(d2))) {
                            io.e(TAG, String.format("Error happens when calling backend service %s", eM.toString()));
                        }
                        JSONObject f = ik.f(httpsURLConnection);
                        if (f != null) {
                            k(f);
                            String str4 = TAG;
                            String.format("Panda %s api response json: %s", path, f.toString());
                            io.dm(str4);
                            a aVar = new a(Integer.valueOf(d2), f);
                            httpsURLConnection.disconnect();
                            return aVar;
                        }
                        io.e(TAG, String.format("Fail to get backend service response from %s", eM.toString()));
                        a aVar2 = new a(Integer.valueOf(d2), null, null, 3);
                        httpsURLConnection.disconnect();
                        return aVar2;
                    } catch (IOException e) {
                        e = e;
                        httpsURLConnection3 = httpsURLConnection;
                        String message = e.getMessage();
                        if (message != null && message.contains("Received authentication challenge is")) {
                            io.e(TAG, "Encountered bug around 401 returned from the server. Assuming this means invalid credentials");
                            ejVar.incrementCounter(mp.j(eM), 1.0d);
                            ejVar.bA(mp.a(eM, (int) HttpServletResponse.SC_UNAUTHORIZED, AuthEndpointErrorParser.AuthErrorType.CredentialError.getCode()));
                            a aVar3 = new a(Integer.valueOf((int) HttpServletResponse.SC_UNAUTHORIZED), null, null, 0);
                            if (httpsURLConnection3 != null) {
                                httpsURLConnection3.disconnect();
                            }
                            return aVar3;
                        }
                        ejVar.bA(mp.i(eM));
                        ejVar.bA(mp.a(eM, e, this.o));
                        io.e(TAG, String.format(Locale.US, "Error getting response from server. Error: %s", message));
                        if (!mp.aM(this.o)) {
                            io.e(TAG, "The device is not connected to internet. Please check your device network connection.");
                        } else if (e instanceof UnknownHostException) {
                            io.e(TAG, String.format("Cannot parse url %s, Please check your device network connection.", eM.toString()), e);
                        } else if (e instanceof SSLHandshakeException) {
                            io.e(TAG, String.format("Cannot hit url %s. Please check your device network connection.", eM.toString()), e);
                        } else {
                            ejVar.incrementCounter(mp.j(eM), FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                            String str5 = TAG;
                            io.e(str5, "Unable to reach " + eM + ", despite valid network connection due to " + e.getMessage() + ". Please contact service owners for investigation if it is not BackoffException", e);
                            i = 1;
                        }
                        a aVar4 = new a(e, Integer.valueOf(i));
                        if (httpsURLConnection3 != null) {
                            httpsURLConnection3.disconnect();
                        }
                        return aVar4;
                    } catch (JSONException e2) {
                        e = e2;
                        httpsURLConnection2 = httpsURLConnection;
                        io.e(TAG, String.format(Locale.US, "Error parsing backend service response from %s response. Not of an expected format. Error: %s", eM.toString(), e.getMessage()));
                        a aVar5 = new a((Exception) e, (Integer) 3);
                        if (httpsURLConnection2 != null) {
                            httpsURLConnection2.disconnect();
                        }
                        return aVar5;
                    } catch (Throwable th) {
                        th = th;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    httpsURLConnection3 = d;
                } catch (JSONException e4) {
                    e = e4;
                    httpsURLConnection2 = d;
                } catch (Throwable th2) {
                    th = th2;
                    httpsURLConnection = d;
                }
            } catch (Throwable th3) {
                th = th3;
                httpsURLConnection = httpsURLConnection4;
            }
        } catch (IOException e5) {
            e = e5;
            httpsURLConnection3 = null;
        } catch (JSONException e6) {
            e = e6;
            httpsURLConnection2 = null;
        } catch (Throwable th4) {
            th = th4;
            httpsURLConnection = null;
        }
    }

    protected HttpsURLConnection d(ej ejVar) throws IOException {
        return (HttpsURLConnection) cy.a(eM(), eL(), ejVar, this.o);
    }

    protected abstract String eF();

    protected abstract String eG();

    protected abstract AuthenticationMethod eH();

    protected List<MAPCookie> eI() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<String, String> eK() {
        HashMap hashMap = new HashMap();
        int cS = eL().cS();
        if (cS > 0) {
            hashMap.put("x-amzn-identity-retry-attempt", String.valueOf(cS));
        }
        return hashMap;
    }

    protected RetryLogic eL() {
        return new jy(this.o, null);
    }

    public URL eM() {
        String path = getPath();
        if (!TextUtils.isEmpty(path)) {
            try {
                return EnvironmentUtils.cd().n(eF(), path);
            } catch (MalformedURLException e) {
                io.c(TAG, "Domain or path for service call invalid", eF(), path, e.getMessage());
                throw new IllegalArgumentException("Domain or path for service call invalid", e);
            }
        }
        io.e(TAG, "No path specified for service call");
        throw new IllegalArgumentException("No path specified for service call");
    }

    protected abstract String getHttpVerb();

    protected abstract String getPath();

    protected abstract String j(JSONObject jSONObject);

    protected void k(JSONObject jSONObject) {
        String j = j(jSONObject);
        if (!TextUtils.isEmpty(j)) {
            io.e(TAG, "Error index is found in error response: ".concat(String.valueOf(j)));
            io.dm(TAG);
        }
    }

    public void a(Integer num, String str) {
        mv mvVar = this.mZ;
        if (mvVar != null) {
            mvVar.eP(mp.a(eM(), num == null ? 0 : num.intValue(), str));
            this.mZ.stop();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        public JSONObject na;
        public Integer nb;
        public Exception nc;
        public Integer nd;

        public a(Integer num, JSONObject jSONObject, Exception exc, Integer num2) {
            this.nb = num;
            this.na = jSONObject;
            this.nc = exc;
            this.nd = num2;
        }

        public a(Integer num, JSONObject jSONObject) {
            this(num, jSONObject, null, null);
        }

        public a(Exception exc, Integer num) {
            this(null, null, exc, num);
        }
    }

    private void a(HttpsURLConnection httpsURLConnection) throws IOException {
        httpsURLConnection.setRequestMethod(getHttpVerb());
        Map<String, String> eK = eK();
        if (eK == null || eK.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : eK.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String str = TAG;
            String.format("Setting panda api %s connection properties:%s to %s", getPath(), key, value);
            io.dm(str);
            httpsURLConnection.setRequestProperty(key, value);
        }
    }
}
