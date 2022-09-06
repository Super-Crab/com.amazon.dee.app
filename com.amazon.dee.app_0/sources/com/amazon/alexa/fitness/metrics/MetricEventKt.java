package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.util.AssertionException;
import kotlin.Metadata;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"DEFAULT_METRIC_EVENT_TAG", "", "METRIC_PREFIX_DELIMITER", "assertValidMetricName", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class MetricEventKt {
    private static final String DEFAULT_METRIC_EVENT_TAG = "DefaultMetricEvent";
    private static final String METRIC_PREFIX_DELIMITER = ":";

    public static final /* synthetic */ CharSequence access$assertValidMetricName(CharSequence charSequence) {
        return assertValidMetricName(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence assertValidMetricName(@NotNull CharSequence charSequence) throws AssertionException {
        if (new Regex("[a-zA-Z0-9.:]+").matches(charSequence)) {
            return charSequence;
        }
        throw new AssertionException("Metric name '" + charSequence + "' contains invalid characters");
    }
}
