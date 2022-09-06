package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithm.FitnessAlgorithmUtilKt;
import com.amazon.alexa.fitness.algorithm.StepsToDistanceAlgorithmAdapterKt;
import com.amazon.alexa.fitness.algorithms.ActivityEvent;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Sample;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CaloriesAlgorithmAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "invoke", "com/amazon/alexa/fitness/algorithm/calories/CaloriesAlgorithmAdapter$setupSampleSubscription$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CaloriesAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1 extends Lambda implements Function1<Sample.EchoBudSample, Unit> {
    final /* synthetic */ CaloriesAlgorithmAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaloriesAlgorithmAdapter$setupSampleSubscription$$inlined$let$lambda$1(CaloriesAlgorithmAdapter caloriesAlgorithmAdapter) {
        super(1);
        this.this$0 = caloriesAlgorithmAdapter;
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
        CaloriesAlgorithmV2 caloriesAlgorithmV2;
        List<? extends ActivityEvent> listOf;
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        ActivityEvent createActivityEvent = FitnessAlgorithmUtilKt.createActivityEvent(sample);
        try {
            long collectionTimestamp = sample.getCollectionTimestamp();
            l = this.this$0.lastKnownSampleTime;
            if (collectionTimestamp > (l != null ? l.longValue() : 0L)) {
                caloriesAlgorithmV2 = this.this$0.caloriesAlgorithm;
                listOf = CollectionsKt__CollectionsJVMKt.listOf(createActivityEvent);
                caloriesAlgorithmV2.addActivityEvents(listOf);
            }
            this.this$0.lastActivityType = StepsToDistanceAlgorithmAdapterKt.toAlgorithmActivityType(sample.getActivity());
        } catch (Exception e) {
            iLog = this.this$0.log;
            iLog.error("CaloriesAlgorithmAdapter", "Failed to add activity Event", e);
        }
    }
}
