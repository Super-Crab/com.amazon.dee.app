package com.amazon.alexa.mode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface EgressType {
    public static final int APP_TERMINATION = 2;
    public static final int DEVICE_DISCONNECTION = 1;
    public static final int MANUAL_EGRESS = 0;
}
