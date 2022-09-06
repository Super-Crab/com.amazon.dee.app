package com.amazon.alexa.smarthomecameras.metrics;

import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.ptz.metrics.MetricRecorderImpl;
/* loaded from: classes10.dex */
public class PtzMetricsRecorder extends MetricRecorderImpl {
    final CamerasMobilyticsService metricsService;

    public PtzMetricsRecorder(CamerasMobilyticsService camerasMobilyticsService) {
        this.metricsService = camerasMobilyticsService;
    }
}
