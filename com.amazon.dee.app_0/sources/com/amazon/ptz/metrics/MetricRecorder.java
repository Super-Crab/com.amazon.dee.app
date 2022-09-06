package com.amazon.ptz.metrics;

import com.amazon.ptz.physical.events.PhysicalPtzEvent;
import com.amazon.ptz.util.ZoomInput;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
/* loaded from: classes13.dex */
public interface MetricRecorder {
    void recordDigitalPanToBorder();

    void recordDigitalZoomRequest(ZoomInput zoomInput);

    void recordErrorResponse(PhysicalPtzEvent physicalPtzEvent);

    void recordPhysicalPtzRequest(CameraPtzInstance cameraPtzInstance);

    void recordPhysicalPtzRequestNotSupported(CameraPtzInstance cameraPtzInstance);

    void recordRtcDataChannelLatency(Long l);
}
