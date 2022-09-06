package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.AlexaMetricsListener;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/api/compat/MetricsApi;", "", "deregisterMetricsListener", "", "metricsListener", "Lcom/amazon/alexa/api/AlexaMetricsListener;", "registerMetricsListener", "alexaMetricsListener", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MetricsApi {
    void deregisterMetricsListener(@NotNull AlexaMetricsListener alexaMetricsListener);

    void registerMetricsListener(@NotNull AlexaMetricsListener alexaMetricsListener);
}
