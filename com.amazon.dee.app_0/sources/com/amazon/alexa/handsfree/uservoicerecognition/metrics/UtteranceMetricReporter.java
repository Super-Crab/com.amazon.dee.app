package com.amazon.alexa.handsfree.uservoicerecognition.metrics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.model.UVRComponent;
import com.amazon.alexa.handsfree.uservoicerecognition.model.UVRComponentMetadata;
import com.amazon.alexa.handsfree.uservoicerecognition.model.UtteranceMetricMetadata;
import com.amazon.alexa.handsfree.uservoicerecognition.model.VerificationStage;
import com.amazon.alexa.handsfree.uservoicerecognition.model.VerificationStageMetadata;
import java.util.Map;
/* loaded from: classes8.dex */
public class UtteranceMetricReporter {
    private static final String SOURCE = "UtteranceMetricReporter";
    private static final String VERIFICATION_METRIC = "UserVoiceRecognition";

    public void reportUtteranceMetric(@NonNull Context context, @NonNull UtteranceMetricMetadata utteranceMetricMetadata) {
        MetricsBuilder newBuilder = MetricsBuilderProvider.getInstance(context).newBuilder();
        for (Map.Entry<VerificationStage, VerificationStageMetadata> entry : utteranceMetricMetadata.getVerificationLatencyMap().entrySet()) {
            newBuilder.withLatencyMetric(entry.getValue().getStageStartTimestamp(), entry.getValue().getStageEndTimestamp(), entry.getKey().toString());
        }
        for (Map.Entry<UVRComponent, UVRComponentMetadata> entry2 : utteranceMetricMetadata.getUserVerificationMetadataMap().entrySet()) {
            UVRComponent key = entry2.getKey();
            UVRComponentMetadata value = entry2.getValue();
            newBuilder.withVoiceMetadataMetric(SOURCE, (String) key, (UVRComponent) UVRComponentMetadata.MetadataKey.CONFIDENCE, value.getConfidence()).withVoiceMetadataMetric(SOURCE, (String) key, (UVRComponent) UVRComponentMetadata.MetadataKey.SUCCESS, value.isSuccessful());
            if (entry2.getValue().isSuccessful()) {
                newBuilder.withPercentileMetricSuccess(SOURCE, String.format("%s:%s", VERIFICATION_METRIC, key.getMetricName()));
            } else {
                newBuilder.withPercentileMetricFailure(SOURCE, String.format("%s:%s", VERIFICATION_METRIC, key.getMetricName()));
            }
        }
        newBuilder.emit(context);
    }
}
