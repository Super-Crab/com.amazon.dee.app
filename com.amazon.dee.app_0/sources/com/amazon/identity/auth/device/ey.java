package com.amazon.identity.auth.device;

import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ey {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) uRLConnection).setSSLSocketFactory(ev.lO);
        }
    }
}
