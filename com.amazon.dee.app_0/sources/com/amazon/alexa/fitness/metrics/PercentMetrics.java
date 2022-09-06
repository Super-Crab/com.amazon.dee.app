package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.utils.MetricComponent;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregatedMetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/PercentMetrics;", "", "identifiers", "Lkotlin/Pair;", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "(Ljava/lang/String;ILkotlin/Pair;)V", "getIdentifiers", "()Lkotlin/Pair;", "BACKGROUNDED_PERCENTAGE", "DISCONNECTED_PERCENTAGE", "FOREGROUNDED_PERCENTAGE", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum PercentMetrics {
    BACKGROUNDED_PERCENTAGE(new Pair(AggregatedMetricsConstants.Companion.getBACKGROUNDED_DURATION(), AggregatedMetricsConstants.Companion.getBACKGROUNDED_PERCENTAGE())),
    DISCONNECTED_PERCENTAGE(new Pair(AggregatedMetricsConstants.Companion.getDISCONNECTED_DURATION(), AggregatedMetricsConstants.Companion.getDISCONNECTED_PERCENTAGE())),
    FOREGROUNDED_PERCENTAGE(new Pair(AggregatedMetricsConstants.Companion.getFOREGROUNDED_DURATION(), AggregatedMetricsConstants.Companion.getFOREGROUNDED_PERCENTAGE()));
    
    @NotNull
    private final Pair<MetricComponent, MetricComponent> identifiers;

    PercentMetrics(Pair pair) {
        this.identifiers = pair;
    }

    @NotNull
    public final Pair<MetricComponent, MetricComponent> getIdentifiers() {
        return this.identifiers;
    }
}
