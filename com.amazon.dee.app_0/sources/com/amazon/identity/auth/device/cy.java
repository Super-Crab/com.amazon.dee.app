package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cy {
    private static final String TAG = "com.amazon.identity.auth.device.cy";

    private cy() {
    }

    public static HttpURLConnection a(URL url, RetryLogic retryLogic, ej ejVar, Context context) throws IOException {
        return (HttpURLConnection) jw.b(dy.a(url, retryLogic, ejVar, context));
    }

    public static HttpURLConnection openConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        return (HttpURLConnection) jw.b(AuthenticatedURLConnection.openConnection(url, authenticationMethod));
    }

    public static void a(HttpURLConnection httpURLConnection, String str) throws IOException {
        InputStream inputStream;
        String str2;
        try {
            inputStream = httpURLConnection.getErrorStream();
            if (inputStream == null) {
                str2 = "No Error Stream Found";
            } else {
                try {
                    str2 = new String(jd.a(inputStream));
                } catch (Throwable th) {
                    th = th;
                    jd.a((Closeable) inputStream);
                    throw th;
                }
            }
            io.a("Received Error from %s: %s", str, str2);
            jd.a((Closeable) inputStream);
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public static HttpURLConnection openConnection(HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) throws IOException {
        return (HttpURLConnection) jw.b(AuthenticatedURLConnection.openConnection(httpURLConnection, authenticationMethod));
    }

    public static URLConnection openConnection(URL url) throws IOException {
        return jw.b(ew.c(url));
    }
}
