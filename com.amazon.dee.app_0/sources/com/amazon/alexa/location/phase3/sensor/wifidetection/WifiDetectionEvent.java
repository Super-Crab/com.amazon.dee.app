package com.amazon.alexa.location.phase3.sensor.wifidetection;

import com.amazon.alexa.location.phase3.evaluator.LocationEvent;
/* loaded from: classes9.dex */
public class WifiDetectionEvent extends LocationEvent {
    private static final String DATA_TYPE = "WIFI_DETECTION_TRIGGER_EVENT";
    public TransitionType transitionType;

    /* loaded from: classes9.dex */
    public enum TransitionType {
        ARRIVE,
        LEAVE
    }

    public WifiDetectionEvent(long j, TransitionType transitionType) {
        super(DATA_TYPE, 1, j);
        this.transitionType = transitionType;
    }
}
