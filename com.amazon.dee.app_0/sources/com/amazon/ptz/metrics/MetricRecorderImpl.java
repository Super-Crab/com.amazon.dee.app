package com.amazon.ptz.metrics;

import android.util.Log;
import com.amazon.ptz.physical.events.PhysicalPtzEvent;
import com.amazon.ptz.util.LogTag;
import com.amazon.ptz.util.ZoomInput;
import com.amazon.ptzcontroller.lib.model.api.ptz.directive.CameraPtzInstance;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class MetricRecorderImpl implements MetricRecorder {
    private static final String TAG = LogTag.forClass(MetricRecorderImpl.class);
    private boolean doNotSpamLogsForZoom = false;
    private boolean doNotSpamLogsForPanToBorder = false;

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordDigitalPanToBorder() {
        if (!this.doNotSpamLogsForPanToBorder) {
            Log.w(TAG, "Client has not implemented digital pan to border metric logging. Ignoring metric for digital pan to border.");
            this.doNotSpamLogsForPanToBorder = true;
        }
    }

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordDigitalZoomRequest(ZoomInput zoomInput) {
        if (!this.doNotSpamLogsForZoom) {
            Log.w(TAG, "Client has not implemented zoom request metric logging. Ignoring metric for zoom request.");
            this.doNotSpamLogsForZoom = true;
        }
    }

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordErrorResponse(PhysicalPtzEvent physicalPtzEvent) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Client has not implemented PTZ error response metric logging. Ignoring metric PTZ error response: ");
        outline107.append(physicalPtzEvent.name());
        Log.w(str, outline107.toString());
    }

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordPhysicalPtzRequest(CameraPtzInstance cameraPtzInstance) {
        Log.w(TAG, "Client has not implemented physical PTZ request metric logging. Ignoring metric for physical PTZ request.");
    }

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordPhysicalPtzRequestNotSupported(CameraPtzInstance cameraPtzInstance) {
        Log.w(TAG, "Client has not implemented unsupported physical PTZ request metric logging. Ignoring metric for unsupported physical PTZ request.");
    }

    @Override // com.amazon.ptz.metrics.MetricRecorder
    public void recordRtcDataChannelLatency(Long l) {
        String str = TAG;
        Log.w(str, "Client has not implemented RTC data channel latency metric logging. Ignoring metric for data channel latency: " + l);
    }
}
