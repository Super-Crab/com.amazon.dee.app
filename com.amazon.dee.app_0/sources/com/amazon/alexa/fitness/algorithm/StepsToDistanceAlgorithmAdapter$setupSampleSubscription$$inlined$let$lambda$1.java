package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.algorithms.StepToDistanceAlgorithm;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Sample;
import dagger.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StepsToDistanceAlgorithmAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "invoke", "com/amazon/alexa/fitness/algorithm/StepsToDistanceAlgorithmAdapter$setupSampleSubscription$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class StepsToDistanceAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1 extends Lambda implements Function1<Sample.EchoBudSample, Unit> {
    final /* synthetic */ StepsToDistanceAlgorithmAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StepsToDistanceAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1(StepsToDistanceAlgorithmAdapter stepsToDistanceAlgorithmAdapter) {
        super(1);
        this.this$0 = stepsToDistanceAlgorithmAdapter;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Sample.EchoBudSample echoBudSample) {
        invoke2(echoBudSample);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Sample.EchoBudSample sample) {
        ILog iLog;
        Long l;
        ILog iLog2;
        ILog iLog3;
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        ActivityEvent createActivityEvent = FitnessAlgorithmUtilKt.createActivityEvent(sample);
        try {
            l = this.this$0.lastKnownSampleTime;
            long longValue = l != null ? l.longValue() : 0L;
            if (sample.getCollectionTimestamp() > longValue) {
                iLog3 = this.this$0.log;
                ILog.DefaultImpls.info$default(iLog3, "StepsToDistanceAlgorithmAdapter", "StepsToDistance received sample " + sample + '.', null, 4, null);
                lazy = this.this$0.stepsToDistanceAlgorithm;
                ((StepToDistanceAlgorithm) lazy.mo358get()).addEvents(new ActivityEvent[]{createActivityEvent});
                this.this$0.generateDerivedSamples(sample.getSessionId());
            } else {
                iLog2 = this.this$0.log;
                ILog.DefaultImpls.info$default(iLog2, "StepsToDistanceAlgorithmAdapter", "StepsToDistance received an out-of-order sample (" + sample.getCollectionTimestamp() + " <= " + longValue + ").", null, 4, null);
            }
            this.this$0.lastActivityType = StepsToDistanceAlgorithmAdapterKt.toAlgorithmActivityType(sample.getActivity());
        } catch (Exception e) {
            iLog = this.this$0.log;
            iLog.error("StepsToDistanceAlgorithmAdapter", "Failed to add activity Event", e);
        }
    }
}
