package com.amazon.alexa.sensor.api.location;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class Error {
    public final int errorCode;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface ErrorCode {
        public static final int AUTHORIZATION_FAILURE_BACKGROUND_LOCATION_NOT_ALLOWED = 2;
        public static final int AUTHORIZATION_FAILURE_FEATURE_NOT_AUTHORIZED = 1;
        public static final int AUTHORIZATION_FAILURE_PRIORITY_TRACKING_NOT_ALLOWED = 3;
        public static final int LOCATION_NOT_AVAILABLE_DEVICE_LOCATION_DISABLED = 11;
        public static final int LOCATION_NOT_AVAILABLE_GMS_NOT_AVAILABLE = 12;
        public static final int LOCATION_NOT_AVAILABLE_NO_LOCATION_SENSOR = 10;
        public static final int MISSING_DEVICE_PERMISSION_BACKGROUND_LOCATION = 22;
        public static final int MISSING_DEVICE_PERMISSION_FINE_LOCATION = 21;
        public static final int MISSING_DEVICE_PERMISSION_GENERAL_LOCATION = 20;
        public static final int PRIORITY_TRACKING_NOT_AVAILABLE_APP_IN_BACKGROUND = 30;
        public static final int PRIORITY_TRACKING_NOT_AVAILABLE_DENIED_BY_USER = 32;
        public static final int PRIORITY_TRACKING_NOT_AVAILABLE_TIME_LIMIT_EXCEEDED = 31;
        public static final int UNKNOWN = -1;
    }

    public Error(int i) {
        this.errorCode = i;
    }
}
