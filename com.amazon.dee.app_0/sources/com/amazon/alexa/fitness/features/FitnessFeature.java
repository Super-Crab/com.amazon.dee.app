package com.amazon.alexa.fitness.features;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessFeature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/features/FitnessFeature;", "", "featureName", "", "(Ljava/lang/String;)V", "getFeatureName", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class FitnessFeature {
    @NotNull
    private final String featureName;

    private FitnessFeature(String str) {
        this.featureName = str;
    }

    @NotNull
    public final String getFeatureName() {
        return this.featureName;
    }
}
