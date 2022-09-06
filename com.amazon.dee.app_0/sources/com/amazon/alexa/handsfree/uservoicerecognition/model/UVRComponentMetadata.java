package com.amazon.alexa.handsfree.uservoicerecognition.model;

import com.amazon.alexa.handsfree.protocols.utils.Preconditions;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes8.dex */
public class UVRComponentMetadata {
    private static final int MAX_CONFIDENCE_THRESHOLD = 100;
    private static final int MIN_CONFIDENCE_THRESHOLD = 0;
    private final int mConfidence;
    private final boolean mIsSuccessful;

    /* loaded from: classes8.dex */
    public enum MetadataKey {
        CONFIDENCE,
        SUCCESS
    }

    public UVRComponentMetadata(boolean z, int i) {
        Preconditions.checkArgument(i >= 0 && i <= 100, String.format(Locale.getDefault(), "Confidence should be between %d and %d.", 0, 100));
        this.mIsSuccessful = z;
        this.mConfidence = i;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UVRComponentMetadata.class) {
            return false;
        }
        UVRComponentMetadata uVRComponentMetadata = (UVRComponentMetadata) obj;
        return this.mIsSuccessful == uVRComponentMetadata.mIsSuccessful && this.mConfidence == uVRComponentMetadata.mConfidence;
    }

    public int getConfidence() {
        return this.mConfidence;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsSuccessful), Integer.valueOf(this.mConfidence));
    }

    public boolean isSuccessful() {
        return this.mIsSuccessful;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "[isSuccessful = %b, confidence = %d]", Boolean.valueOf(this.mIsSuccessful), Integer.valueOf(this.mConfidence));
    }
}
