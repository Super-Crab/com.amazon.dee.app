package com.amazon.blueshift.bluefront.android.vad.config;
/* loaded from: classes11.dex */
public abstract class VADConfig {
    public static final int DEFAULT_ENDPOINTING_THRESHOLD = 50;
    public static final int DEFAULT_STARTPOINTING_THRESHOLD = 26;
    private final int mEndpointingThreshold;
    private final int mStartpointingThreshold;
    private final int mWakewordStartOfUtteranceEndpointingThreshold;

    public VADConfig(int i, int i2) {
        this.mStartpointingThreshold = i;
        this.mEndpointingThreshold = i2;
        this.mWakewordStartOfUtteranceEndpointingThreshold = 0;
    }

    public int getEndpointingThreshold() {
        return this.mEndpointingThreshold;
    }

    public int getStartpointingThreshold() {
        return this.mStartpointingThreshold;
    }

    public int getWakewordStartOfUtteranceEndpointingThreshold() {
        return this.mWakewordStartOfUtteranceEndpointingThreshold;
    }

    public VADConfig(int i, int i2, int i3) {
        this.mStartpointingThreshold = i;
        this.mEndpointingThreshold = i2;
        this.mWakewordStartOfUtteranceEndpointingThreshold = i3;
    }
}
