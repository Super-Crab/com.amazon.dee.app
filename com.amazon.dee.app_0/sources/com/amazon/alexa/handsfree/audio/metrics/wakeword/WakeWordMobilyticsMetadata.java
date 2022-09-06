package com.amazon.alexa.handsfree.audio.metrics.wakeword;

import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes8.dex */
public class WakeWordMobilyticsMetadata {
    private final String mWWModelID;
    private final int mWakeWordConfidence;

    public WakeWordMobilyticsMetadata(@Nullable String str, int i) {
        this.mWWModelID = str;
        this.mWakeWordConfidence = i;
    }

    public String getWWModelID() {
        return Objects.toString(this.mWWModelID, "");
    }

    public String getWakeWordConfidence() {
        return String.valueOf(this.mWakeWordConfidence);
    }

    public int getWakeWordConfidenceInt() {
        return this.mWakeWordConfidence;
    }
}
