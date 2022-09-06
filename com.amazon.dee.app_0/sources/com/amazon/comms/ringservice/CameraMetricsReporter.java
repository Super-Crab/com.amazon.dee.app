package com.amazon.comms.ringservice;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.Priority;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import com.amazon.comms.metrics.MetricsDestination;
import com.amazon.comms.ringservice.webrtc.CameraMetadata;
import javax.annotation.Nonnull;
/* loaded from: classes12.dex */
public class CameraMetricsReporter {
    private static final String CAMERA_QUALITY_METRICS_LABEL = "CameraQualityMetrics";
    private static final CommsLogger log = CommsLogger.getLogger(CameraMetricsReporter.class);
    private static final CameraMetadata cameraMetadata = new CameraMetadata();

    private CameraMetricsReporter() {
    }

    public static synchronized void recordCameraQualityMetricsEvent(@Nonnull MetricsSession metricsSession, String str, String str2) {
        synchronized (CameraMetricsReporter.class) {
            if (metricsSession == null || str == null || str2 == null) {
                return;
            }
            if (log.isLoggable(LogLevel.Debug)) {
                CommsLogger commsLogger = log;
                commsLogger.d("recordCameraQualityMetricsEvent(eventLabel=CameraQualityMetrics, callId=" + log.sensitiveCallId(str2) + ", metrics=" + str + ")");
            }
            cameraMetadata.unflatten(str);
            MetricEvent createEvent = metricsSession.createEvent(CAMERA_QUALITY_METRICS_LABEL);
            int luxLevel = cameraMetadata.getLuxLevel();
            if (luxLevel > 0) {
                createEvent.addCounter("CameraLuxLevel", luxLevel);
            }
            int colorTemperature = cameraMetadata.getColorTemperature();
            if (colorTemperature > 0) {
                createEvent.addCounter("CameraColorTemperature", colorTemperature);
            }
            createEvent.addString("CallId", str2);
            metricsSession.recordEvent(createEvent, MetricsDestination.DCM, Priority.NORMAL);
        }
    }
}
