package com.amazon.alexa.fitness.algorithm.calories;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AutoMETCaloriesAlgorithmImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "", "Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityWindowSummary;", "Lkotlin/ParameterName;", "name", "summaries", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* synthetic */ class AutoMETCaloriesAlgorithmImpl$activityBufferHandler$1 extends FunctionReference implements Function1<List<? extends CaloriesActivityWindowSummary>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoMETCaloriesAlgorithmImpl$activityBufferHandler$1(AutoMETCaloriesAlgorithmImpl autoMETCaloriesAlgorithmImpl) {
        super(1, autoMETCaloriesAlgorithmImpl);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "processBufferSummaries";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(AutoMETCaloriesAlgorithmImpl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "processBufferSummaries(Ljava/util/List;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<? extends CaloriesActivityWindowSummary> list) {
        invoke2((List<CaloriesActivityWindowSummary>) list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull List<CaloriesActivityWindowSummary> p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((AutoMETCaloriesAlgorithmImpl) this.receiver).processBufferSummaries(p1);
    }
}
