package com.amazon.alexa.location.phase3.sensor.motiondetection;

import com.amazon.alexa.location.phase3.evaluator.LocationEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class MotionEvent extends LocationEvent {
    public static final int MOTION_ARRIVING = 1;
    public static final int MOTION_DWELLING = 2;
    public static final int MOTION_INTRANSIT = 4;
    public static final int MOTION_LEAVING = 3;
    public static final int MOTION_UNKNOWN = 0;
    public final int state;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface MotionState {
    }

    public MotionEvent(int i, long j) {
        super("MotionEvent", 1, j);
        this.state = i;
    }
}
