package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface VoiceMetadataMetricFactory {

    /* loaded from: classes8.dex */
    public enum ParameterType {
        INT,
        DOUBLE,
        BOOLEAN,
        STRING
    }

    Metric buildVoiceMetadataMetric(@NonNull String str, @NonNull String str2, @NonNull ParameterType parameterType, @NonNull String str3);
}
