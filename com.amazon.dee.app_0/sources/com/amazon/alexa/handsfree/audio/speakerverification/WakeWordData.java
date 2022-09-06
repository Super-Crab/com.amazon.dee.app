package com.amazon.alexa.handsfree.audio.speakerverification;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class WakeWordData {
    private final long mBeginSampleIndex;
    private final long mEndSampleIndex;
    private final byte[] mMetadata;
    private final String mWakeWord;

    public WakeWordData(@NonNull String str, long j, long j2, @NonNull byte[] bArr) {
        this.mWakeWord = str;
        this.mBeginSampleIndex = j;
        this.mEndSampleIndex = j2;
        this.mMetadata = (byte[]) bArr.clone();
    }

    public long getBeginSampleIndex() {
        return this.mBeginSampleIndex;
    }

    public long getEndSampleIndex() {
        return this.mEndSampleIndex;
    }

    @NonNull
    public byte[] getMetadata() {
        return (byte[]) this.mMetadata.clone();
    }

    @NonNull
    public String getWakeWord() {
        return this.mWakeWord;
    }
}
