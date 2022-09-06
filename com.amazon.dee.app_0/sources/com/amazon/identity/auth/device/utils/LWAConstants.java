package com.amazon.identity.auth.device.utils;
/* loaded from: classes12.dex */
public final class LWAConstants {
    public static final String AUTHORIZE_REQUEST_TYPE = "com.amazon.identity.auth.device.authorization.request.authorize";
    public static final String CONSTANT_PREFIX = "com.amazon.identity.auth.device.authorization";
    public static final String INTERACTIVE_REQUEST_TYPE_KEY = "InteractiveRequestType";

    /* loaded from: classes12.dex */
    public enum AUTHORIZE_BUNDLE_KEY {
        REGION("com.amazon.identity.auth.device.authorization.region"),
        RETURN_ACCESS_TOKEN("com.amazon.identity.auth.device.authorization.returnAccessToken");
        
        public final String val;

        AUTHORIZE_BUNDLE_KEY(String str) {
            this.val = str;
        }
    }

    /* loaded from: classes12.dex */
    public enum PROFILE_BUNDLE_KEY {
        FAIL_ON_INSUFFICIENT_SCOPE("com.amazon.identity.auth.device.authorization.failOnInsufficientScope");
        
        public final String val;

        PROFILE_BUNDLE_KEY(String str) {
            this.val = str;
        }
    }
}
