package com.amazon.alexa.biloba.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes6.dex */
public @interface AlertConfigurationType {
    public static final int ANY_ACTIVITY_DETECTED = 2;
    public static final int FIRST_ACTIVITY_DETECTED = 1;
    public static final int NO_ACTIVITY_DETECTED = 0;
    public static final int PAUSE_NOTIFICATION = 4;
    public static final int SMART_HOME_ACTIVITY_DETECTED = 3;
    public static final int UNKNOWN = -1;
}
