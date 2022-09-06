package com.amazon.alexa.biloba.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes6.dex */
public @interface PasscodeState {
    public static final int ERROR = 1;
    public static final int IDLE = -1;
    public static final int NOT_SETUP = 2;
    public static final int SETUP_AND_AUTHED = 4;
    public static final int SETUP_AND_NOT_AUTHED = 3;
    public static final int WAITING_FOR_SERVER = 0;
}
