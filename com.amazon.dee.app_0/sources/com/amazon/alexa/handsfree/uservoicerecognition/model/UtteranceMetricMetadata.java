package com.amazon.alexa.handsfree.uservoicerecognition.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class UtteranceMetricMetadata {
    private final Map<UVRComponent, UVRComponentMetadata> mUserVerificationMetadataMap;
    private final Map<VerificationStage, VerificationStageMetadata> mVerificationLatencyMap;

    /* loaded from: classes8.dex */
    public static final class Builder {
        private Map<UVRComponent, UVRComponentMetadata> mUserVerificationMetadataMap;
        private Map<VerificationStage, VerificationStageMetadata> mVerificationLatencyMap;

        public Builder() {
            this(new HashMap(), new HashMap());
        }

        public UtteranceMetricMetadata build() {
            return new UtteranceMetricMetadata(Collections.unmodifiableMap(new HashMap(this.mUserVerificationMetadataMap)), Collections.unmodifiableMap(new HashMap(this.mVerificationLatencyMap)));
        }

        public Builder recordLatency(@NonNull VerificationStage verificationStage, long j, long j2) {
            this.mVerificationLatencyMap.put(verificationStage, new VerificationStageMetadata(j, j2));
            return this;
        }

        public Builder recordUtteranceResult(@NonNull UVRComponent uVRComponent, boolean z, int i) {
            this.mUserVerificationMetadataMap.put(uVRComponent, new UVRComponentMetadata(z, i));
            return this;
        }

        @VisibleForTesting
        Builder(@NonNull Map<UVRComponent, UVRComponentMetadata> map, @NonNull Map<VerificationStage, VerificationStageMetadata> map2) {
            this.mUserVerificationMetadataMap = map;
            this.mVerificationLatencyMap = map2;
        }
    }

    @VisibleForTesting
    public UtteranceMetricMetadata(@NonNull Map<UVRComponent, UVRComponentMetadata> map, @NonNull Map<VerificationStage, VerificationStageMetadata> map2) {
        this.mUserVerificationMetadataMap = map;
        this.mVerificationLatencyMap = map2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UtteranceMetricMetadata.class) {
            return false;
        }
        UtteranceMetricMetadata utteranceMetricMetadata = (UtteranceMetricMetadata) obj;
        return Objects.equals(this.mUserVerificationMetadataMap, utteranceMetricMetadata.mUserVerificationMetadataMap) && Objects.equals(this.mVerificationLatencyMap, utteranceMetricMetadata.mVerificationLatencyMap);
    }

    public Map<UVRComponent, UVRComponentMetadata> getUserVerificationMetadataMap() {
        return this.mUserVerificationMetadataMap;
    }

    public Map<VerificationStage, VerificationStageMetadata> getVerificationLatencyMap() {
        return this.mVerificationLatencyMap;
    }

    public int hashCode() {
        return Objects.hash(this.mUserVerificationMetadataMap, this.mVerificationLatencyMap);
    }

    public String toString() {
        return String.format("[userVerificationMetadata = %s, verificationLatency = %s]", this.mUserVerificationMetadataMap, this.mVerificationLatencyMap);
    }
}
