package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.gv;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class CustomerAttributeKeys {
    @FireOsSdk
    public static final String KEY_ACCOUNT_POOL = "com.amazon.dcp.sso.token.device.accountpool";
    @FireOsSdk
    public static final String KEY_COR = "COR";
    @FireOsSdk
    public static final String KEY_DEVICE_EMAIL = "com.amazon.dcp.sso.property.deviceemail";
    @FireOsSdk
    public static final String KEY_DEVICE_NAME = "com.amazon.dcp.sso.property.devicename";
    @FireOsSdk
    public static final String KEY_DEVICE_TYPE = "com.amazon.dcp.sso.token.devicedevicetype";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID_DELEGATEE = "com.amazon.dcp.sso.property.account.delegateeaccount";
    @FireOsSdk
    public static final String KEY_DSN = "com.amazon.dcp.sso.token.device.deviceserialname";
    @FireOsSdk
    public static final String KEY_EXTRA_TOKENS_KEY_PREFIX = "com.amazon.dcp.sso.property.account.extratokens";
    @FireOsSdk
    public static final String KEY_FIRST_NAME = "com.amazon.dcp.sso.property.firstname";
    @FireOsSdk
    public static final String KEY_IS_ANONYMOUS = "isAnonymous";
    @FireOsSdk
    public static final String KEY_NAME = "com.amazon.dcp.sso.property.username";
    @FireOsSdk
    public static final String KEY_PFM = "PFM";
    @FireOsSdk
    @Deprecated
    public static final String KEY_XFSN = "com.amazon.identity.cookies.xfsn";
    @FireOsSdk
    @Deprecated
    public static final String KEY_XMAIN_AND_XACB_COOKIES = "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies";

    private CustomerAttributeKeys() {
    }

    @FireOsSdk
    public static String getCustomizedKeyForPackage(String str, String str2) {
        return gv.W(str, "com.amazon.dcp.sso.property.account.extratokens.".concat(String.valueOf(str2)));
    }

    @FireOsSdk
    public static String getDeviceEmailKeyForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.property.deviceemail");
    }

    @FireOsSdk
    public static String getDeviceNameKeyForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.property.devicename");
    }

    @FireOsSdk
    public static String getDeviceTypeKeyForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.token.devicedevicetype");
    }

    @FireOsSdk
    public static String getDsnKeyForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.token.device.deviceserialname");
    }

    @FireOsSdk
    public static String getUserFirstNameForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.property.firstname");
    }

    @FireOsSdk
    public static String getUserNameKeyForPackage(String str) {
        return gv.W(str, "com.amazon.dcp.sso.property.username");
    }
}
