package com.amazon.alexa.drive.smart.device.data;

import android.text.TextUtils;
import android.webkit.CookieManager;
/* loaded from: classes7.dex */
public final class SmartDeviceCookie {
    private static SmartDeviceCookie sSmartDeviceCookie;
    private String cookie = "";

    private SmartDeviceCookie() {
    }

    public static SmartDeviceCookie getInstance() {
        if (sSmartDeviceCookie == null) {
            sSmartDeviceCookie = new SmartDeviceCookie();
        }
        return sSmartDeviceCookie;
    }

    public String getCookie() {
        if (!TextUtils.isEmpty(this.cookie)) {
            return this.cookie;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager.hasCookies()) {
            this.cookie = cookieManager.getCookie("https://alexa.amazon.com");
        }
        return this.cookie;
    }
}
