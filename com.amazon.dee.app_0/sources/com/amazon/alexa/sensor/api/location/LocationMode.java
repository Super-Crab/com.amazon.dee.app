package com.amazon.alexa.sensor.api.location;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes10.dex */
public @interface LocationMode {
    public static final int ALL_CHANGES = 3;
    public static final int ALL_CHANGES_DURING_FOREGROUND = 2;
    public static final int SIGNIFICANT_CHANGES_ONLY = 1;
    public static final int SINGLE_LOCATION = 0;
}
