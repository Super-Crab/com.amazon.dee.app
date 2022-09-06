package com.amazon.alexa.fitness.algorithm.calories;

import com.amazon.alexa.fitness.algorithms.ActivityType;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CaloriesActivityBuffer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "a", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "kotlin.jvm.PlatformType", "b", "compare"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$2<T> implements Comparator<ActivityType> {
    public static final CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$2 INSTANCE = new CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$2();

    CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$2() {
    }

    @Override // java.util.Comparator
    public final int compare(ActivityType a, ActivityType b) {
        CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 caloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 = CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(a, "a");
        int invoke2 = caloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1.invoke2(a);
        CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 caloriesActivityWindowSummary$getPrioritizedActivityTypeCount$12 = CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(b, "b");
        return Intrinsics.compare(invoke2, caloriesActivityWindowSummary$getPrioritizedActivityTypeCount$12.invoke2(b));
    }
}
