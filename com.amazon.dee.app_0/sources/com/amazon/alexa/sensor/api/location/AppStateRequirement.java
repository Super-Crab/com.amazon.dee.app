package com.amazon.alexa.sensor.api.location;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes10.dex */
public @interface AppStateRequirement {
    public static final int FOREGROUND_AND_BACKGROUND = 1;
    public static final int FOREGROUND_AND_BACKGROUND_WITH_PRIORITY = 2;
    public static final int FOREGROUND_ONLY = 0;
}
