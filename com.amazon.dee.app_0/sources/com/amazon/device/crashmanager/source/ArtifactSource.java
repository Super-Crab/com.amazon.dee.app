package com.amazon.device.crashmanager.source;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.Artifact;
/* loaded from: classes12.dex */
public interface ArtifactSource {
    Artifact getNextArtifact(MetricEvent metricEvent, String str);

    void saveCurrentIndex();
}
