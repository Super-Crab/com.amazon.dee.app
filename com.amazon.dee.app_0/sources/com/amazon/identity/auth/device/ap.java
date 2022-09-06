package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.features.Feature;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ap {
    private static final String TAG = "com.amazon.identity.auth.device.ap";
    private final eh F = new eh();
    private final ea at;
    private final ej bR;
    private final AuthenticationMethodFactory dx;
    private final dd dy;
    private final Context mContext;

    public ap(Context context, String str, ej ejVar) {
        this.mContext = context;
        this.dx = new AuthenticationMethodFactory(context, str);
        this.dy = (dd) context.getSystemService("dcp_authenticated_url_connection_factory");
        this.at = (ea) this.mContext.getSystemService("dcp_device_info");
        this.bR = ejVar;
    }

    private URL ar() {
        try {
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").authority(EnvironmentUtils.cd().cn()).appendPath("getCustomerAttribute").appendQueryParameter("version", "1_0").appendQueryParameter(NetworkConstants.PREFERENCES_KEY, "cor,pfm").appendQueryParameter("devicetype", this.at.getDeviceType());
            if (cp.a(new cq(this.mContext)).a(Feature.DSNWhenNotRegistered)) {
                appendQueryParameter.appendQueryParameter("dsn", this.at.getDeviceSerialNumber());
            }
            return new URL(appendQueryParameter.build().toString());
        } catch (MalformedURLException unused) {
            io.e(TAG, "Could not construct DCA endpoint");
            return null;
        }
    }

    CORPFMResponse a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        try {
            try {
                try {
                    inputStream = httpURLConnection.getInputStream();
                } catch (Throwable th) {
                    th = th;
                    jd.a((Closeable) null);
                    throw th;
                }
            } catch (IOException e) {
                e = e;
            } catch (JSONException e2) {
                e = e2;
                inputStream = null;
            }
            try {
                String str = new String(jd.a(inputStream));
                String str2 = TAG;
                "Response: ".concat(str);
                io.dm(str2);
                JSONObject jSONObject = new JSONObject(str);
                JSONObject optJSONObject = jSONObject.optJSONObject("customerAttribute");
                if (optJSONObject == null) {
                    io.w(TAG, "The server returned an error with message: ".concat(String.valueOf(ik.a(jSONObject, AlexaMetricsConstants.EventConstants.MESSAGE, "Internal Error."))));
                    jd.a((Closeable) inputStream);
                    return null;
                } else if (!optJSONObject.has("cor") && !optJSONObject.has("pfm")) {
                    io.w(TAG, "The server did not return a cor pfm for the customer. Message: ".concat(String.valueOf(ik.a(optJSONObject, "Description", "Generic Error. No COR or PFM found."))));
                    jd.a((Closeable) inputStream);
                    return null;
                } else {
                    String a = ik.a(optJSONObject, "cor", null);
                    String a2 = ik.a(optJSONObject, "pfm", null);
                    String a3 = ik.a(optJSONObject, "sourceOfComputationCOR", null);
                    String a4 = ik.a(optJSONObject, "sourceOfComputationPFM", null);
                    String a5 = ik.a(optJSONObject, "computationConfidenceValue", null);
                    io.i(TAG, String.format("Received response with: %nCoR: %s %nPFM:%s %nSource Of Computation CoR: %s %n Source Of Computation PFM: %s %n Computation Confidence Value: %s", a, a2, a3, a4, a5));
                    CORPFMResponse.ComputationConfidenceValue parseFromValue = CORPFMResponse.ComputationConfidenceValue.parseFromValue(a5, CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED);
                    mq.b("fetchCORPFMSuccess", new String[0]);
                    CORPFMResponse cORPFMResponse = new CORPFMResponse(a, a2, parseFromValue, Long.valueOf(this.F.currentTimeMillis()));
                    jd.a((Closeable) inputStream);
                    return cORPFMResponse;
                }
            } catch (IOException e3) {
                e = e3;
                mq.b("fetchCORPFMFailure", "IOException");
                cy.a(httpURLConnection, "DCA service");
                throw e;
            } catch (JSONException e4) {
                e = e4;
                mq.b("fetchCORPFMFailure", "JSONException");
                io.e(TAG, "Error parsing DCAS JSON Response: " + e.getMessage());
                jd.a((Closeable) inputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.amazon.identity.auth.attributes.CORPFMResponse aq() throws com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException {
        /*
            r9 = this;
            java.lang.String r0 = com.amazon.identity.auth.device.ap.TAG
            java.lang.String r1 = "Fetching the COR and PFM values"
            com.amazon.identity.auth.device.io.i(r0, r1)
            java.net.URL r0 = r9.ar()
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            com.amazon.identity.auth.device.api.AuthenticationMethodFactory r2 = r9.dx     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            com.amazon.identity.auth.device.api.AuthenticationType r3 = com.amazon.identity.auth.device.api.AuthenticationType.ADPAuthenticator     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            com.amazon.identity.auth.device.api.AuthenticationMethod r2 = r2.newAuthenticationMethod(r3)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            com.amazon.identity.auth.device.ej r3 = r9.bR     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            java.lang.String r4 = com.amazon.identity.auth.device.mp.h(r0)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            com.amazon.identity.auth.device.mv r3 = r3.bz(r4)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            com.amazon.identity.auth.device.dd r4 = r9.dy     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            java.net.HttpURLConnection r2 = r4.openConnection(r0, r2)     // Catch: java.lang.Throwable -> L73 java.io.IOException -> L76 com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> Ld5
            java.lang.String r4 = "GET"
            r2.setRequestMethod(r4)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            r2.setRequestProperty(r4, r5)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            int r4 = com.amazon.identity.auth.device.framework.RetryLogic.d(r2)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r3.iM()     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r5 = com.amazon.identity.auth.device.mp.a(r0, r4)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r3.eP(r5)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r3.stop()     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r3 = com.amazon.identity.auth.device.ap.TAG     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.util.Locale r5 = java.util.Locale.ENGLISH     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r6 = "Received Response Code %d from DCAS"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r8 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r7[r8] = r4     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r4 = java.lang.String.format(r5, r6, r7)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            com.amazon.identity.auth.device.io.i(r3, r4)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            com.amazon.identity.auth.device.ej r3 = r9.bR     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            java.lang.String r4 = com.amazon.identity.auth.device.mp.j(r0)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3.incrementCounter(r4, r5)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            com.amazon.identity.auth.attributes.CORPFMResponse r0 = r9.a(r2)     // Catch: java.io.IOException -> L6e com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException -> L70 java.lang.Throwable -> Ld3
            r2.disconnect()
            return r0
        L6e:
            r3 = move-exception
            goto L78
        L70:
            r0 = move-exception
            r1 = r2
            goto Ld6
        L73:
            r0 = move-exception
            r2 = r1
            goto Ld7
        L76:
            r3 = move-exception
            r2 = r1
        L78:
            java.lang.String r4 = com.amazon.identity.auth.device.ap.TAG     // Catch: java.lang.Throwable -> Ld3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r6 = "IOException: Could not call DCAS Service. "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Ld3
            java.lang.Class r6 = r3.getClass()     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r6 = r6.getName()     // Catch: java.lang.Throwable -> Ld3
            r5.append(r6)     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Ld3
            com.amazon.identity.auth.device.io.e(r4, r5)     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r4 = com.amazon.identity.auth.device.ap.TAG     // Catch: java.lang.Throwable -> Ld3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r6 = "IOException: "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r6 = r3.getMessage()     // Catch: java.lang.Throwable -> Ld3
            r5.append(r6)     // Catch: java.lang.Throwable -> Ld3
            com.amazon.identity.auth.device.io.dn(r4)     // Catch: java.lang.Throwable -> Ld3
            android.content.Context r4 = r9.mContext     // Catch: java.lang.Throwable -> Ld3
            boolean r4 = com.amazon.identity.auth.device.mp.aM(r4)     // Catch: java.lang.Throwable -> Ld3
            if (r4 == 0) goto Lb9
            com.amazon.identity.auth.device.ej r4 = r9.bR     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r5 = com.amazon.identity.auth.device.mp.j(r0)     // Catch: java.lang.Throwable -> Ld3
            r6 = 0
            r4.incrementCounter(r5, r6)     // Catch: java.lang.Throwable -> Ld3
        Lb9:
            com.amazon.identity.auth.device.ej r4 = r9.bR     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r5 = com.amazon.identity.auth.device.mp.i(r0)     // Catch: java.lang.Throwable -> Ld3
            r4.bA(r5)     // Catch: java.lang.Throwable -> Ld3
            com.amazon.identity.auth.device.ej r4 = r9.bR     // Catch: java.lang.Throwable -> Ld3
            android.content.Context r5 = r9.mContext     // Catch: java.lang.Throwable -> Ld3
            java.lang.String r0 = com.amazon.identity.auth.device.mp.a(r0, r3, r5)     // Catch: java.lang.Throwable -> Ld3
            r4.bA(r0)     // Catch: java.lang.Throwable -> Ld3
            if (r2 == 0) goto Ld2
            r2.disconnect()
        Ld2:
            return r1
        Ld3:
            r0 = move-exception
            goto Ld7
        Ld5:
            r0 = move-exception
        Ld6:
            throw r0     // Catch: java.lang.Throwable -> L73
        Ld7:
            if (r2 == 0) goto Ldc
            r2.disconnect()
        Ldc:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ap.aq():com.amazon.identity.auth.attributes.CORPFMResponse");
    }
}
