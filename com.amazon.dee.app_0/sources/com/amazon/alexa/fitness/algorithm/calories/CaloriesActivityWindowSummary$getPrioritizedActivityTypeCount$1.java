package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithm.calories.CaloriesActivityWindowSummary;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CaloriesActivityBuffer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"getKeyPriority", "", "key", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 extends Lambda implements Function1<ActivityType, Integer> {
    public static final CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 INSTANCE = new CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1();

    CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Integer mo12165invoke(ActivityType activityType) {
        return Integer.valueOf(invoke2(activityType));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final int invoke2(@NotNull ActivityType key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        int i = CaloriesActivityWindowSummary.WhenMappings.$EnumSwitchMapping$0[key.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return 2;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            return 1;
        }
        return 3;
    }
}
