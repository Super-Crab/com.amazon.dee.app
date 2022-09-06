package com.amazon.alexa.smarthomecameras.metrics;

import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.ptz.PtzEventListener;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
/* loaded from: classes10.dex */
public class PtzMetricsListener implements PtzEventListener {
    private static final String TAG = "PtzMetricsListener";
    final CamerasMobilyticsService metricsService;

    public PtzMetricsListener(CamerasMobilyticsService camerasMobilyticsService) {
        this.metricsService = camerasMobilyticsService;
    }

    @Override // com.amazon.alexa.smarthomecameras.ptz.PtzEventListener
    public void onDoubleTapZoom(String str) {
        this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.DPTZ_ZOOM_DOUBLE_TAP, str);
    }

    @Override // com.amazon.alexa.smarthomecameras.ptz.PtzEventListener
    public void onPan(String str) {
        this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.DPTZ_PAN, str);
    }

    @Override // com.amazon.alexa.smarthomecameras.ptz.PtzEventListener
    public void onPinchZoom(String str) {
        this.metricsService.recordOperationalEventWithProvider(MobilyticsConstants.ALVMetrics.DPTZ_ZOOM_PINCH, str);
    }
}
