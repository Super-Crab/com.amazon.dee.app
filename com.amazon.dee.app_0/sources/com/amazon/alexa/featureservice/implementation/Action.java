package com.amazon.alexa.featureservice.implementation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes7.dex */
public final class Action {
    public static final int SESSION_CHANGE = 2;
    public static final int SIGN_IN = 0;
    public static final int SIGN_OUT = 1;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface User {
    }

    private Action() {
    }
}
