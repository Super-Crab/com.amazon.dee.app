package com.amazon.alexa.fitness.algorithm.aggregatedDistance;

import com.amazon.alexa.fitness.sdk.Sample;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DistanceDeltaCalculator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH&J\b\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDeltaCalculator;", "", "isActive", "", "()Z", "addSample", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "addSamples", "samples", "", "calculate", "Lcom/amazon/alexa/fitness/algorithm/aggregatedDistance/DistanceDelta;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface DistanceDeltaCalculator {
    void addSample(@NotNull Sample sample);

    void addSamples(@NotNull List<? extends Sample> list);

    @NotNull
    DistanceDelta calculate() throws InsufficientDataDistanceCalculationError;

    boolean isActive();
}
