package com.amazon.alexa.handsfree.uservoicerecognition.model;

import java.util.Locale;
import java.util.Objects;
/* loaded from: classes8.dex */
public class VerificationStageMetadata {
    private final long mStageEndTimestamp;
    private final long mStageStartTimestamp;

    public VerificationStageMetadata(long j, long j2) {
        this.mStageStartTimestamp = j;
        this.mStageEndTimestamp = j2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != VerificationStageMetadata.class) {
            return false;
        }
        VerificationStageMetadata verificationStageMetadata = (VerificationStageMetadata) obj;
        return this.mStageStartTimestamp == verificationStageMetadata.mStageStartTimestamp && this.mStageEndTimestamp == verificationStageMetadata.mStageEndTimestamp;
    }

    public long getStageEndTimestamp() {
        return this.mStageEndTimestamp;
    }

    public long getStageStartTimestamp() {
        return this.mStageStartTimestamp;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mStageStartTimestamp), Long.valueOf(this.mStageEndTimestamp));
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[start = %d, end = %d]", Long.valueOf(this.mStageStartTimestamp), Long.valueOf(this.mStageEndTimestamp));
    }
}
