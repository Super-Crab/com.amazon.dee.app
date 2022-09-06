package com.amazon.alexa.fitness.sdk;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\tH&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SampleMonitor;", "", "lastKnownSampleTime", "", "getLastKnownSampleTime", "()Ljava/lang/Long;", "setLastKnownSampleTime", "(Ljava/lang/Long;)V", "handleSample", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "reset", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SampleMonitor {
    @Nullable
    Long getLastKnownSampleTime();

    void handleSample(@NotNull Sample sample);

    void reset();

    void setLastKnownSampleTime(@Nullable Long l);
}
