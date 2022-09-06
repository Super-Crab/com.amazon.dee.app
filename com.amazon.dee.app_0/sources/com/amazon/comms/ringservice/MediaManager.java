package com.amazon.comms.ringservice;

import com.amazon.comms.calling.service.DeviceCallingServiceParams;
import com.amazon.comms.instrumentation.EventTracer;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import org.webrtc.EglBase;
/* loaded from: classes12.dex */
public interface MediaManager {
    void close();

    void disposeCachedMediaSession();

    EglBase.Context getEglContext();

    MediaSession getMediaSession(String str, boolean z, MediaSessionListener mediaSessionListener, EventTracer eventTracer, PeerConnectionClient.PeerConnectionParameters peerConnectionParameters, DataChannelListener dataChannelListener, RealTimeTextDataChannelListener realTimeTextDataChannelListener, boolean z2, String str2);

    void warmupMediaSession(int i, boolean z, boolean z2, boolean z3, boolean z4, DeviceCallingServiceParams deviceCallingServiceParams);
}
