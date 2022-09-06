package com.amazon.alexa.fitness.context;

import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/fitness/context/SessionSummaryProviderDelegate;", "", "onFitnessMetricsUpdated", "", "updatedMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionSummaryProviderDelegate {
    void onFitnessMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics);
}
