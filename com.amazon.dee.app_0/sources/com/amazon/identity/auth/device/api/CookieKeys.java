package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class CookieKeys {
    @FireOsSdk
    public static final String KEY_COOKIES = "com.amazon.identity.auth.device.api.cookiekeys.all";
    @FireOsSdk
    public static final String KEY_INVALIDATE_COOKIES = "invalidate-cookies";
    @FireOsSdk
    public static final String KEY_RESPONSE_URL = "com.amazon.identity.auth.device.api.cookiekeys.ResponseUrl";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public final class Options {
        @FireOsSdk
        public static final String KEY_ASSOC_HANDLE = "com.amazon.identity.auth.device.api.cookiekeys.assoc_handle";
        @FireOsSdk
        public static final String KEY_FORCE_REFRESH = "com.amazon.identity.auth.device.api.cookiekeys.options.forcerefresh";
        @FireOsSdk
        public static final String KEY_INVALIDATE_ACTOR_COOKIES = "com.amazon.identity.auth.device.api.cookiekeys.invalidate.actor";
        @FireOsSdk
        public static final String KEY_SIGN_IN_URL = "com.amazon.identity.auth.device.api.cookiekeys.options.SignInUrl";
    }

    private CookieKeys() {
    }
}
