package com.amazon.identity.auth.device;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jo {
    private static final AtomicBoolean rL = new AtomicBoolean(false);

    public static void a(Context context, String str, String[] strArr) {
        aJ(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        for (String str2 : strArr) {
            cookieManager.setCookie(str, str2);
        }
        CookieSyncManager.getInstance().sync();
    }

    public static void aJ(Context context) {
        if (rL.compareAndSet(false, true)) {
            CookieSyncManager.createInstance(context.getApplicationContext());
        }
        CookieSyncManager.getInstance().sync();
    }
}
