package com.amazon.alexa.fitness.orchestrator;

import com.amazon.alexa.fitness.sdk.Sample;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionOrchestratorImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "locationSamples", "", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "invoke", "com/amazon/alexa/fitness/orchestrator/FitnessSessionOrchestratorImpl$getLocationList$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class FitnessSessionOrchestratorImpl$getLocationList$$inlined$let$lambda$1 extends Lambda implements Function1<List<? extends Sample.LocationSample>, Unit> {
    final /* synthetic */ Function1 $callback$inlined;
    final /* synthetic */ List $listOfLocationCoordinates;
    final /* synthetic */ FitnessSessionOrchestratorImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessSessionOrchestratorImpl$getLocationList$$inlined$let$lambda$1(List list, FitnessSessionOrchestratorImpl fitnessSessionOrchestratorImpl, Function1 function1) {
        super(1);
        this.$listOfLocationCoordinates = list;
        this.this$0 = fitnessSessionOrchestratorImpl;
        this.$callback$inlined = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<? extends Sample.LocationSample> list) {
        invoke2((List<Sample.LocationSample>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull List<Sample.LocationSample> locationSamples) {
        Intrinsics.checkParameterIsNotNull(locationSamples, "locationSamples");
        for (Sample.LocationSample locationSample : locationSamples) {
            this.$listOfLocationCoordinates.add(FitnessSessionOrchestratorImplKt.toLocationCoordinate(locationSample));
        }
        this.$callback$inlined.mo12165invoke(this.$listOfLocationCoordinates);
    }
}
