package com.amazon.comms.ringservice;

import android.content.Context;
import android.os.Handler;
import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.ringservice.webrtc.WebRTCMediaManager;
/* loaded from: classes12.dex */
public class MediaManagerFactory {
    private MediaManagerFactory() {
    }

    public static MediaManager createMediaManager(Context context, Handler handler, MetricsSession metricsSession, DeviceCallingServiceParams deviceCallingServiceParams, boolean z) throws Exception {
        return new WebRTCMediaManager(context, handler, metricsSession, deviceCallingServiceParams, z);
    }
}
