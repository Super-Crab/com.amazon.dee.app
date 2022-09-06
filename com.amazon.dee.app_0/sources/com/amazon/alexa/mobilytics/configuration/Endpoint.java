package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public interface Endpoint {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Type {
        public static final int DCM = 1;
        public static final int KINESIS = 0;
    }

    @NonNull
    String tag();

    int type();
}
