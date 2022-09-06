package com.amazon.alexa.location.phase3.sensor.activity;

import com.amazon.alexa.location.phase3.LocationSignal;
import com.google.android.gms.location.ActivityRecognitionResult;
/* loaded from: classes9.dex */
public class ActivitySensorInput extends LocationSignal {
    private static final String TYPE = "ActivitySensorInput";
    public final int activityType;
    public final int confidence;

    public ActivitySensorInput(long j, int i, int i2) {
        super(TYPE, 1, j);
        this.activityType = i;
        this.confidence = i2;
    }

    public static ActivitySensorInput fromActicityRecognitionResult(ActivityRecognitionResult activityRecognitionResult) {
        return new ActivitySensorInput(activityRecognitionResult.getTime(), activityRecognitionResult.getMostProbableActivity().getType(), activityRecognitionResult.getMostProbableActivity().getConfidence());
    }
}
