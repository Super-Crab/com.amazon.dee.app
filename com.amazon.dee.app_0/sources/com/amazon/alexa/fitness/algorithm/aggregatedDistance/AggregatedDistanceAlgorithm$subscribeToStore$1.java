package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.sdk.Sample;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AggregatedDistanceAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"getSampleHandler", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/sdk/Sample;", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AggregatedDistanceAlgorithm$subscribeToStore$1 extends Lambda implements Function0<Function1<? super Sample, ? extends Unit>> {
    final /* synthetic */ Long $lastProcessedTimestamp;
    final /* synthetic */ AggregatedDistanceAlgorithm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AggregatedDistanceAlgorithm.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/alexa/fitness/sdk/Sample;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$subscribeToStore$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<Sample, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Sample sample) {
            invoke2(sample);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Sample it2) {
            Intrinsics.checkParameterIsNotNull(it2, "it");
            if (it2.getReceivedAtTimestamp() > AggregatedDistanceAlgorithm$subscribeToStore$1.this.$lastProcessedTimestamp.longValue()) {
                AggregatedDistanceAlgorithm$subscribeToStore$1.this.this$0.receive(it2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AggregatedDistanceAlgorithm.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/alexa/fitness/sdk/Sample;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.algorithm.aggregatedDistance.AggregatedDistanceAlgorithm$subscribeToStore$1$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends Lambda implements Function1<Sample, Unit> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12165invoke(Sample sample) {
            invoke2(sample);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Sample it2) {
            Intrinsics.checkParameterIsNotNull(it2, "it");
            AggregatedDistanceAlgorithm$subscribeToStore$1.this.this$0.receive(it2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AggregatedDistanceAlgorithm$subscribeToStore$1(AggregatedDistanceAlgorithm aggregatedDistanceAlgorithm, Long l) {
        super(0);
        this.this$0 = aggregatedDistanceAlgorithm;
        this.$lastProcessedTimestamp = l;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Function1<? super Sample, ? extends Unit> mo12560invoke() {
        if (this.$lastProcessedTimestamp != null) {
            return new AnonymousClass1();
        }
        return new AnonymousClass2();
    }
}
