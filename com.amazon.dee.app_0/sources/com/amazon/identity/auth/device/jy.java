package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class jy extends RetryLogic {
    private static final String TAG = "com.amazon.identity.auth.device.jy";
    private int jF = 0;
    private Context mContext;
    private jx rV;

    public jy(Context context, jx jxVar) {
        this.rV = jxVar;
        this.mContext = context;
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public RetryLogic.a a(HttpURLConnection httpURLConnection, int i, ej ejVar) {
        this.jF++;
        URL url = httpURLConnection.getURL();
        try {
            mv bz = ejVar.bz(mp.h(url));
            int responseCode = httpURLConnection.getResponseCode();
            bz.iM();
            String str = null;
            if (this.rV != null) {
                str = this.rV.g(httpURLConnection);
            }
            bz.eP(mp.a(url, responseCode, str));
            bz.stop();
            if (RetryLogic.j(responseCode)) {
                if (RetryLogic.b(url)) {
                    return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.BackoffError);
                }
                io.c(TAG, "Got response code %d. Retrying", Integer.valueOf(responseCode));
                return new RetryLogic.a(RetryLogic.RetryErrorMessageFromServerSide.ServerInternalError);
            }
            RetryLogic.a(this.jF, url, ejVar);
            if (i > 0) {
                String k = mp.k(url);
                io.a(TAG, ejVar, k, k);
            }
            return new RetryLogic.a();
        } catch (IOException e) {
            if (!mp.aM(this.mContext)) {
                this.jF--;
            }
            io.e(TAG, "IOException : ", e);
            String i2 = mp.i(url);
            io.a(TAG, ejVar, i2, i2);
            String a = mp.a(url, e, this.mContext);
            io.a(TAG, ejVar, a, a);
            return new RetryLogic.a(e);
        }
    }

    @Override // com.amazon.identity.auth.device.framework.RetryLogic
    public int cS() {
        return this.jF;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(HttpURLConnection httpURLConnection) {
        if (this.jF <= 0 || !EnvironmentUtils.cd().bc(httpURLConnection.getURL().getHost())) {
            return;
        }
        httpURLConnection.addRequestProperty("x-amzn-identity-retry-attempt", String.valueOf(this.jF));
    }
}
