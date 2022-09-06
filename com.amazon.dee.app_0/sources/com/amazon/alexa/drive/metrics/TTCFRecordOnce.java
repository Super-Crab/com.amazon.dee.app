package com.amazon.alexa.drive.metrics;

import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
/* loaded from: classes7.dex */
public class TTCFRecordOnce {
    public static final String DRIVE_MODE_MAIN = "drivemode:main";
    public static final String ENTERTAINMENT_MAIN = "drivemode:entertainment";
    public static final String ENTERTAINMENT_NOWPLAYING_COLD = "drivemode:nowplaying:cold";
    public static final String ENTERTAINMENT_NOWPLAYING_WARM = "drivemode:nowplaying:warm";
    public static final String HOME = "drivemode:landing";
    public static final String NAVIGATION_MAIN = "drivemode:navigation";
    private boolean recordedOnce = false;
    LazyComponent<TTCFCheckpoint> ttcfCheckpoint;

    public TTCFRecordOnce(LazyComponent<TTCFCheckpoint> lazyComponent) {
        this.ttcfCheckpoint = lazyComponent;
    }

    public void markComplete(String str) {
        if (!this.recordedOnce) {
            this.ttcfCheckpoint.mo10268get().markComplete(str);
            this.recordedOnce = true;
        }
    }
}
