package com.amazon.alexa.mobilytics.timeline;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface TimelineState {
    public static final int ABORTED = 4;
    public static final int PAUSED = 1;
    public static final int RESUMED = 2;
    public static final int STARTED = 0;
    public static final int STOPPED = 3;
}
