package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dm extends RetryLogic {
    private static final String TAG = "com.amazon.identity.auth.device.dm";
    private final AuthEndpointErrorParser aU = new AuthEndpointErrorParser();
    private int jF = 0;
    private Context mContext;

    public dm(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public RetryLogic.a a(HttpURLConnection httpURLConnection, int i, ej ejVar) {
        JSONObject jSONObject;
        this.jF++;
        URL url = httpURLConnection.getURL();
        try {
            mv bz = ejVar.bz(mp.h(url));
            int responseCode = httpURLConnection.getResponseCode();
            bz.iM();
            try {
                jSONObject = ik.f(httpURLConnection);
            } catch (JSONException e) {
                String str = mp.a(url, responseCode) + ":JSONException";
                io.a(TAG, ejVar, str, str);
                io.e(TAG, "Got JSONException while parsing response.", e);
                jSONObject = null;
            }
            String h = this.aU.h(jSONObject);
            if (TextUtils.isEmpty(h)) {
                bz.eP(mp.a(url, responseCode));
            } else {
                bz.eP(mp.a(url, responseCode, h));
            }
            bz.stop();
            if (RetryLogic.j(responseCode)) {
                if (RetryLogic.b(url)) {
                    return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.BackoffError);
                }
                return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.ServerInternalError);
            } else if (jSONObject == null) {
                io.e(TAG, "Malformed exchange token json response detected. Should retry if still within retry limit.");
                return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.InvalidJSON);
            } else {
                if (i > 0) {
                    String k = mp.k(url);
                    io.a(TAG, ejVar, k, k);
                }
                RetryLogic.a(this.jF, url, ejVar);
                return new RetryLogic.a();
            }
        } catch (IOException e2) {
            io.e(TAG, "IOException while calling exchange token endpoint. Will retry. Exception : ", e2);
            if (!mp.aM(this.mContext)) {
                this.jF--;
            }
            String i2 = mp.i(url);
            io.a(TAG, ejVar, i2, i2);
            String a = mp.a(url, e2, this.mContext);
            io.a(TAG, ejVar, a, a);
            return new RetryLogic.a(e2);
        }
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public int cS() {
        return this.jF;
    }
}
