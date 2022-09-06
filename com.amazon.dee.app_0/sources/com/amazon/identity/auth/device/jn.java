package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jn {
    private static final String TAG = "com.amazon.identity.auth.device.jn";

    private jn() {
    }

    public static void a(Context context, String str, MAPCookie mAPCookie) {
        jo.aJ(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(str, mAPCookie.fO());
        jo.aJ(context);
    }

    public static String dK(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        if (parse == null) {
            io.e(TAG, "error happens when parsing the url string");
            return null;
        }
        return String.format(Locale.US, "%s://%s", parse.getScheme(), parse.getAuthority());
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, boolean z) {
        String de;
        try {
            if (z) {
                de = ia.dd(str);
            } else {
                de = ia.de(str);
            }
            a(context, str, new MAPCookie(str2, str3, de, str5, str4, null, true, true));
        } catch (MalformedURLException e) {
            mq.b("failedParsingCookieDomain", "MalformedURLException");
            io.e(TAG, "Could not parse cookie domain from malformed URL.", e);
        }
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        a(context, str, str2, "", str3, str4, false);
    }

    public static MAPCookie a(Context context, String str, String str2, String str3) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        jo.aJ(context);
        String concat = str2.startsWith(".") ? "www".concat(str2) : str2;
        String cookie = cookieManager.getCookie(concat);
        GeneratedOutlineSupport1.outline161(concat, "Extracting cookies from CookieManager for domain=", TAG);
        io.a("Extracting cookie list for domain: %s, id: = %s", str2, str);
        ArrayList<MAPCookie> arrayList = new ArrayList();
        if (cookie != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(cookie, ";");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                String[] split = nextToken.trim().split(Config.Compare.EQUAL_TO, 2);
                if (split.length == 2) {
                    arrayList.add(new MAPCookie(split[0], split[1], str2, str, false));
                } else {
                    io.w(TAG, "Unexpected cookie format");
                    io.b("Unexpected cookie format. cookiePair=%s cookies=%s", nextToken, cookie);
                }
            }
        } else {
            io.a("No cookies in Cookie manager for %s", str2);
        }
        for (MAPCookie mAPCookie : arrayList) {
            if (str3.equals(mAPCookie.getName())) {
                return mAPCookie;
            }
        }
        return null;
    }
}
