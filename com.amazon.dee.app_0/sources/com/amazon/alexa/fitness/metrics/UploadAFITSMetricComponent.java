package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.utils.MetricComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: MetricsConstants.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/UploadAFITSMetricComponent;", "Lcom/amazon/alexa/fitness/utils/MetricComponent;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class UploadAFITSMetricComponent extends MetricComponent {
    @NotNull
    private final String name;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadAFITSMetricComponent(@NotNull String name) {
        super(name, Components.AFX, "sessionUploader");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }
}
