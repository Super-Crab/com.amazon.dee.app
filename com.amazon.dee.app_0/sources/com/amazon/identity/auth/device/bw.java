package com.amazon.identity.auth.device;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bw {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void a(Context context, Uri uri);
    }

    public static void a(Context context, Uri uri, String str) {
        by byVar = new by();
        if (MAPRuntimePermissionHandler.I(context)) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            if (str != null) {
                try {
                    builder.setToolbarColor(Color.parseColor(str));
                } catch (IllegalArgumentException unused) {
                    io.e("CustomTabActivityHelper", "Exception while getting CCT toolbar color_code");
                }
            }
            a(context, builder.build(), uri, byVar);
            return;
        }
        mq.incrementCounterAndRecord("chromeCustomNotSupported:OpenFederatedAuthRegistrationRequestWithExternalBrowser:Url=" + uri.toString(), new String[0]);
        new StringBuilder("Chrome Custom Tab is not supported. Opening in external browser - url=").append(uri.toString());
        io.dm("CustomTabActivityHelper");
        byVar.a(context, uri);
    }

    private static void a(Context context, CustomTabsIntent customTabsIntent, Uri uri, a aVar) {
        String s = bx.s(context);
        if (s == null) {
            aVar.a(context, uri);
            return;
        }
        customTabsIntent.intent.setPackage(s);
        customTabsIntent.intent.addFlags(1073741824);
        customTabsIntent.launchUrl(context, uri);
    }
}
