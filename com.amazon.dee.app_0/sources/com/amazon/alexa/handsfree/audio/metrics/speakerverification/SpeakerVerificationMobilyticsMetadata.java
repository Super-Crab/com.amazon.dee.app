package com.amazon.alexa.handsfree.audio.metrics.speakerverification;

import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes8.dex */
public class SpeakerVerificationMobilyticsMetadata {
    public static final String SV_ACCEPTED = "SV_ACCEPTED";
    public static final String SV_REJECTED = "SV_REJECTED";
    private final String mSVModelID;
    private final float mSVRawScore;
    private final float mSVThresholdLowerBound;
    private final float mSVThresholdUpperBound;

    public SpeakerVerificationMobilyticsMetadata(@Nullable String str, float f, float f2, float f3) {
        this.mSVModelID = str;
        this.mSVRawScore = f;
        this.mSVThresholdLowerBound = f2;
        this.mSVThresholdUpperBound = f3;
    }

    public String getSVModelID() {
        return Objects.toString(this.mSVModelID, "");
    }

    public String getSVRawScore() {
        return String.valueOf(this.mSVRawScore);
    }

    public float getSVRawScoreFloat() {
        return this.mSVRawScore;
    }

    public String getSVThresholdLowerBound() {
        return String.valueOf(this.mSVThresholdLowerBound);
    }

    public float getSVThresholdLowerBoundFloat() {
        return this.mSVThresholdLowerBound;
    }

    public String getSVThresholdUpperBound() {
        return String.valueOf(this.mSVThresholdUpperBound);
    }

    public float getSVThresholdUpperBoundFloat() {
        return this.mSVThresholdUpperBound;
    }
}
