package com.amazon.rtcmedia.webrtc;

import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazon.rtcmedia.util.ViewDirection;
import org.webrtc.RendererCommon;
/* loaded from: classes13.dex */
public class RTCRendererEventsHandler implements RendererCommon.RendererEvents {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCRendererEventsListener.class);
    private ViewDirection direction;
    private boolean isNotificationPending;
    private RTCRendererEventsListener listener;
    private final Object notificationStateLock = new Object();

    /* loaded from: classes13.dex */
    public interface RTCRendererEventsListener {
        void onFirstFrameRendered(ViewDirection viewDirection);
    }

    public RTCRendererEventsHandler(ViewDirection viewDirection) {
        this.direction = viewDirection;
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFirstFrameRendered() {
        mLog.i("onFirstFrameRendered");
        synchronized (this.notificationStateLock) {
            if (this.listener != null) {
                this.listener.onFirstFrameRendered(this.direction);
            } else {
                this.isNotificationPending = true;
            }
        }
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(int i, int i2, int i3) {
        mLog.i("onFrameResolutionChanged");
    }

    public void registerListener(RTCRendererEventsListener rTCRendererEventsListener) {
        mLog.i("registerListener");
        synchronized (this.notificationStateLock) {
            this.listener = rTCRendererEventsListener;
            if (this.isNotificationPending) {
                rTCRendererEventsListener.onFirstFrameRendered(this.direction);
                this.isNotificationPending = false;
            }
        }
    }
}
