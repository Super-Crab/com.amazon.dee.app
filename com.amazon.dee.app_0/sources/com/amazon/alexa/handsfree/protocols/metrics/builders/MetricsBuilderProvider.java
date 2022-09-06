package com.amazon.alexa.handsfree.protocols.metrics.builders;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MetricsBuilderProvider {
    private static final String TAG = "MetricsBuilderProvider";
    private final MetricsConfiguration mMetricsConfiguration;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MetricsBuilderProvider(@NonNull MetricsConfiguration metricsConfiguration) {
        this.mMetricsConfiguration = metricsConfiguration;
    }

    public static MetricsBuilderProvider getInstance(Context context) {
        return ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).metricsBuilderProvider();
    }

    public MetricsBuilder newBuilder() {
        return new DefaultMetricsBuilder(this.mMetricsConfiguration);
    }
}
