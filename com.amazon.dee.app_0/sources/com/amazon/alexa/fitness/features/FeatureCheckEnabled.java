package com.amazon.alexa.fitness.features;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FeatureCheckEnabled.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/features/FeatureCheckEnabled;", "", "hasAccessTo", "", "feature", "Lcom/amazon/alexa/fitness/features/FeatureCheckEnabled$FitnessFeature;", "FitnessFeature", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface FeatureCheckEnabled {

    /* compiled from: FeatureCheckEnabled.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b6\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/features/FeatureCheckEnabled$FitnessFeature;", "", "()V", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static abstract class FitnessFeature {
        private FitnessFeature() {
        }
    }

    void hasAccessTo(@NotNull FitnessFeature fitnessFeature);
}
