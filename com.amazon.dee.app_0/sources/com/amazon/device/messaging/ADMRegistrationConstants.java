package com.amazon.device.messaging;

import android.net.Uri;
/* loaded from: classes12.dex */
public final class ADMRegistrationConstants {
    public static final String ARG_API_FLAVOR = "api_flavor";
    public static final String ARG_PACKAGE_NAME = "package_name";
    public static final String ARG_SENDERS = "senders";
    public static final String CALL_EXCEPTION = "exception";
    public static final String CALL_RESPONSE = "response";
    public static final String METHOD_GET_REGISTRATION = "get_registration";
    public static final String METHOD_REGISTER = "register";
    public static final String METHOD_UNREGISTER = "unregister";
    public static final String AUTHORITY = "com.amazon.device.messaging.registrations";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content").authority(AUTHORITY).build();

    private ADMRegistrationConstants() {
    }
}
