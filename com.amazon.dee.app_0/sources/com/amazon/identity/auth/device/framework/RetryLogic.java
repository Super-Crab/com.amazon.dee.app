package com.amazon.identity.auth.device.framework;

import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ls;
import com.amazon.identity.auth.device.lt;
import com.amazon.identity.auth.device.mp;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.kcpsdk.common.BackoffException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class RetryLogic {
    private static final String TAG = "RetryLogic";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum RetryErrorMessageFromServerSide {
        InvalidJSON("Backend service returns invalid JSON"),
        ServerInternalError(String.format(Locale.ENGLISH, "Backend service returns error code %d to %d", 500, 599)),
        BackoffError("Request is within backoff interval");
        
        private String mReason;

        RetryErrorMessageFromServerSide(String str) {
            this.mReason = str;
        }

        public String getReason() {
            return this.mReason;
        }
    }

    public static void a(int i, URL url, ej ejVar) {
        if (i > 0) {
            ejVar.incrementCounter(mp.j(url), 1.0d / i);
        }
    }

    public static boolean b(URL url) {
        return lt.f(url) != null;
    }

    public static int d(HttpURLConnection httpURLConnection) throws IOException {
        a(httpURLConnection.getURL());
        int responseCode = httpURLConnection.getResponseCode();
        lt.a(responseCode, httpURLConnection.getURL());
        return responseCode;
    }

    public static void e(HttpURLConnection httpURLConnection) {
        int ip;
        ls f = lt.f(httpURLConnection.getURL());
        if (f != null && (ip = f.ip()) > 0 && EnvironmentUtils.cd().bc(httpURLConnection.getURL().getHost())) {
            httpURLConnection.addRequestProperty("x-amzn-identity-retry-attempt", Integer.toString(ip));
        }
    }

    public static boolean j(int i) {
        return i >= 500 && i <= 599;
    }

    public abstract a a(HttpURLConnection httpURLConnection, int i, ej ejVar);

    public abstract int cS();

    public static void a(URL url) throws BackoffException {
        ls f = lt.f(url);
        if (f == null || !f.iq()) {
            return;
        }
        ls f2 = lt.f(url);
        String str = url.getHost() + url.getPath();
        String str2 = TAG;
        String.format(Locale.ENGLISH, "Host is %s not available and MAP is applying backoff", str);
        io.dm(str2);
        mq.incrementCounterAndRecord("BackoffException:".concat(String.valueOf(str)), new String[0]);
        if (f2 != null) {
            throw new BackoffException(String.format(Locale.ENGLISH, "Service %s is unavailable and MAP is applying backoff, please retry after %d ms.", url.getHost(), Long.valueOf(f2.io() - System.currentTimeMillis())), f);
        }
        throw new BackoffException(String.format(Locale.ENGLISH, "MAP run in to a rare race condition during backoff interval, this call is backed off but %s server is back to available after this point.", url.getHost()), f);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private RetryErrorMessageFromServerSide kT;
        private IOException kU;
        private boolean kV = true;

        public a(RetryErrorMessageFromServerSide retryErrorMessageFromServerSide) {
            this.kT = retryErrorMessageFromServerSide;
        }

        public IOException dN() {
            return this.kU;
        }

        public RetryErrorMessageFromServerSide dO() {
            return this.kT;
        }

        public boolean dP() {
            RetryErrorMessageFromServerSide retryErrorMessageFromServerSide = this.kT;
            return retryErrorMessageFromServerSide != null && retryErrorMessageFromServerSide.equals(RetryErrorMessageFromServerSide.BackoffError);
        }

        public boolean isSuccess() {
            return this.kV;
        }

        public a(IOException iOException) {
            this.kU = iOException;
        }

        public a() {
        }
    }
}
