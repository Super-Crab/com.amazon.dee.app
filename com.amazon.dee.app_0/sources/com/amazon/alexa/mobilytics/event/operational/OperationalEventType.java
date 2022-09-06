package com.amazon.alexa.mobilytics.event.operational;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface OperationalEventType {
    public static final String COUNTER = "counter";
    public static final String DATA = "data";
    public static final String DIAGNOSTIC = "diagnostic";
    public static final String ERROR = "error";
    public static final String SESSION = "session";
    public static final String TIMER = "timer";
}
