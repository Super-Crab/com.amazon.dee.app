package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dr {
    private static final String TAG = "com.amazon.identity.auth.device.dr";
    private final a kf;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void a(MAPRuntimePermissionHandler mAPRuntimePermissionHandler);

        void aK();

        void aL();
    }

    public dr(a aVar) {
        this.kf = aVar;
    }

    String o(String str, String str2) {
        String str3;
        if (!TextUtils.isEmpty(str2)) {
            str3 = "'" + str2 + "'";
        } else {
            str3 = "";
        }
        return String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s);}", str, str3);
    }

    @JavascriptInterface
    public void onCF() {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.dr.1
            @Override // java.lang.Runnable
            public void run() {
                io.i(dr.TAG, "Javascript interface onCF() is triggered.");
                if (dr.this.kf == null) {
                    return;
                }
                dr.this.kf.aL();
                dr.this.kf.aK();
            }
        });
    }

    @JavascriptInterface
    public void reqPerm(final String str) {
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.dr.2
            @Override // java.lang.Runnable
            public void run() {
                io.i(dr.TAG, "Javascript interface reqPerm() is triggered.");
                MAPRuntimePermissionHandler bt = MAPRuntimePermissionHandler.bt(str);
                if (bt != null) {
                    dr.this.kf.a(bt);
                }
            }
        });
    }

    public void a(final WebView webView, String str, String str2) {
        final String o = o(str, str2);
        String str3 = TAG;
        "Loading callback javascript: ".concat(String.valueOf(o));
        io.dm(str3);
        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.dr.3
            @Override // java.lang.Runnable
            public void run() {
                webView.loadUrl(o);
            }
        });
    }
}
