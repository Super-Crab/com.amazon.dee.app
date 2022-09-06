package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StepBasedDistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003*\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"TAG", "", "getStepDistanceSample", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", "", "Lcom/amazon/alexa/fitness/sdk/Sample;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class StepBasedDistanceDeltaCalculatorKt {
    private static final String TAG = "StepBasedDistanceDeltaCalculator";

    @Nullable
    public static final SampleDataWithTimestamp<Double> getStepDistanceSample(@NotNull Sample getStepDistanceSample) {
        Intrinsics.checkParameterIsNotNull(getStepDistanceSample, "$this$getStepDistanceSample");
        if (!(getStepDistanceSample instanceof Sample.MeasurementSample)) {
            getStepDistanceSample = null;
        }
        Sample.MeasurementSample measurementSample = (Sample.MeasurementSample) getStepDistanceSample;
        if (measurementSample == null || measurementSample.getSampleType() != SampleType.StepBasedDistance) {
            return null;
        }
        return new SampleDataWithTimestamp<>(Double.valueOf(measurementSample.getValue().getValue()), measurementSample.getReceivedAtTimestamp());
    }
}
