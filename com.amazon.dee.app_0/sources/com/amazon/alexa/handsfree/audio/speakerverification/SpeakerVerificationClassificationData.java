package com.amazon.alexa.handsfree.audio.speakerverification;

import androidx.annotation.NonNull;
import java.util.Locale;
/* loaded from: classes8.dex */
public class SpeakerVerificationClassificationData {
    private final byte[] mMetadata;
    private final byte[] mProfileId;
    private final float mRawScore;
    private final float mSVAcceptThresholdLowerBound;
    private final float mSVAcceptThresholdUpperBound;
    private final int mScore;
    private final int mWakeWordConfidence;

    public SpeakerVerificationClassificationData(int i, @NonNull byte[] bArr, @NonNull byte[] bArr2, float f, float f2, float f3, int i2) {
        this.mScore = i;
        this.mProfileId = (byte[]) bArr.clone();
        this.mMetadata = (byte[]) bArr2.clone();
        this.mRawScore = f;
        this.mSVAcceptThresholdUpperBound = f3;
        this.mSVAcceptThresholdLowerBound = f2;
        this.mWakeWordConfidence = i2;
    }

    @NonNull
    public byte[] getMetadata() {
        return (byte[]) this.mMetadata.clone();
    }

    @NonNull
    public byte[] getProfileId() {
        return (byte[]) this.mProfileId.clone();
    }

    public float getRawScore() {
        return this.mRawScore;
    }

    public float getSVAcceptThresholdLowerBound() {
        return this.mSVAcceptThresholdLowerBound;
    }

    public float getSVAcceptThresholdUpperBound() {
        return this.mSVAcceptThresholdUpperBound;
    }

    public int getScore() {
        return this.mScore;
    }

    public int getWakeWordConfidence() {
        return this.mWakeWordConfidence;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "Score = %d", Integer.valueOf(this.mScore)) + "\n" + String.format(Locale.US, "Raw score = %f", Float.valueOf(this.mRawScore)) + "\n" + String.format(Locale.US, "Accept threshold lower bound = %f", Float.valueOf(this.mSVAcceptThresholdLowerBound)) + "\n" + String.format(Locale.US, "Accept threshold upper bound = %f", Float.valueOf(this.mSVAcceptThresholdUpperBound));
    }
}
