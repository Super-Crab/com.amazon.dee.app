package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.sdk.sample.SampleStore;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "", "sessionChangedState", "", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "sessionEnded", "setup", "sampleStore", "Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface FitnessAlgorithm {
    void sessionChangedState(@NotNull Session session);

    void sessionEnded();

    void setup(@NotNull SampleStore sampleStore, @NotNull Session session);
}
