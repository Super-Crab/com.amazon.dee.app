package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StepBasedDistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0016J\u0016\u0010\u0018\u001a\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/StepBasedDistanceDeltaCalculator;", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDeltaCalculator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "referenceSample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "(Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/fitness/sdk/Sample;)V", "isActive", "", "()Z", "setActive", "(Z)V", "referenceDistance", "", "Ljava/lang/Double;", "value", "setReferenceSample", "(Lcom/amazon/alexa/fitness/sdk/Sample;)V", "stepBasedDistanceSamples", "", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/SampleDataWithTimestamp;", "addSample", "", "sample", "addSamples", "samples", "", "calculate", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDelta;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class StepBasedDistanceDeltaCalculator implements DistanceDeltaCalculator {
    private boolean isActive;
    private final ILog log;
    private Double referenceDistance;
    private Sample referenceSample;
    private List<SampleDataWithTimestamp<Double>> stepBasedDistanceSamples;

    public StepBasedDistanceDeltaCalculator(@NotNull ILog log, @Nullable Sample sample) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.log = log;
        setReferenceSample(sample);
        this.stepBasedDistanceSamples = new ArrayList();
    }

    private final void setReferenceSample(Sample sample) {
        Double d = null;
        SampleDataWithTimestamp<Double> stepDistanceSample = sample != null ? StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample(sample) : null;
        boolean z = stepDistanceSample != null ? !Double.isNaN(stepDistanceSample.getSampleData().doubleValue()) : false;
        if (!z) {
            ILog.DefaultImpls.error$default(this.log, "StepBasedDistanceDeltaCalculator", "Invalid sample used to set referenceSample", null, 4, null);
        } else {
            ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Valid referenceSample set, calculator is active", null, 4, null);
        }
        setActive(z);
        if (stepDistanceSample != null) {
            d = stepDistanceSample.getSampleData();
        }
        this.referenceDistance = d;
        this.referenceSample = sample;
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public void addSample(@NotNull Sample sample) {
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        if (!isActive()) {
            ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Currently inactive, setting sample as reference", null, 4, null);
            setReferenceSample(sample);
            return;
        }
        SampleDataWithTimestamp<Double> stepDistanceSample = StepBasedDistanceDeltaCalculatorKt.getStepDistanceSample(sample);
        if (stepDistanceSample != null) {
            this.stepBasedDistanceSamples.add(stepDistanceSample);
        } else {
            ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Ignoring non-StepDistance sample", null, 4, null);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public void addSamples(@NotNull List<? extends Sample> samples) {
        Intrinsics.checkParameterIsNotNull(samples, "samples");
        for (Sample sample : samples) {
            addSample(sample);
        }
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    @NotNull
    public DistanceDelta calculate() {
        int collectionSizeOrDefault;
        Double m10720max;
        ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Performing step-based distance calculation", null, 4, null);
        if (!isActive()) {
            ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Calculator is inactive or reference distance is invalid. DistanceDelta = 0.0", null, 4, null);
            return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
        Double d = this.referenceDistance;
        if (d == null) {
            ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Reference distance is null. DistanceDelta = 0.0", null, 4, null);
            return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
        List<SampleDataWithTimestamp<Double>> list = this.stepBasedDistanceSamples;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(Double.valueOf(((Number) ((SampleDataWithTimestamp) it2.next()).getSampleData()).doubleValue()));
        }
        m10720max = CollectionsKt___CollectionsKt.m10720max((Iterable<Double>) arrayList);
        if (m10720max != null) {
            double doubleValue = m10720max.doubleValue();
            double doubleValue2 = doubleValue - d.doubleValue();
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, "StepBasedDistanceDeltaCalculator", "Current max step-based distance: " + doubleValue, null, 4, null);
            ILog iLog2 = this.log;
            ILog.DefaultImpls.debug$default(iLog2, "StepBasedDistanceDeltaCalculator", "Step-based distance delta: " + doubleValue2, null, 4, null);
            return new DistanceDelta(doubleValue2, Units.Feet, 1.0d);
        }
        ILog.DefaultImpls.debug$default(this.log, "StepBasedDistanceDeltaCalculator", "Not enough step-based samples. DistanceDelta = 0.0", null, 4, null);
        return new DistanceDelta(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, Units.Feet, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    @Override // com.amazon.alexa.fitness.algorithm.aggregatedDistance.DistanceDeltaCalculator
    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }
}
