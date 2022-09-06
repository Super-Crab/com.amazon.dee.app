package com.amazon.alexa.fitness.metrics;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: EventMetric.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/metrics/EventMetric;", "metricName", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class EventMetric$Companion$forComponentAndSubComponent$1 extends Lambda implements Function1<String, EventMetric> {
    final /* synthetic */ String $componentName;
    final /* synthetic */ String $subComponentName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventMetric$Companion$forComponentAndSubComponent$1(String str, String str2) {
        super(1);
        this.$subComponentName = str;
        this.$componentName = str2;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final EventMetric mo12165invoke(@NotNull String metricName) {
        Intrinsics.checkParameterIsNotNull(metricName, "metricName");
        return new EventMetric(metricName, this.$subComponentName, this.$componentName, null, 8, null);
    }
}
