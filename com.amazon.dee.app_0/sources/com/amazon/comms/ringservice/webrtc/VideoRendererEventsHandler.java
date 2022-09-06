package com.amazon.comms.ringservice.webrtc;

import android.os.Handler;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.webrtc.RendererEventsShim;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes12.dex */
public final class VideoRendererEventsHandler implements RendererEventsShim {
    private static final CommsLogger log = CommsLogger.getLogger(VideoRendererEventsHandler.class);
    private final Handler callbackHandler;
    private final WebRTCRendererShim renderer;
    private final boolean shouldSimulateFirstFrameReceived;
    private final Call.Side side;
    private final VideoRendererEventsListener videoRendererEventsListener;

    /* loaded from: classes12.dex */
    public interface VideoRendererEventsListener {
        void onFirstFrameReceived(Call.Side side);

        void onFirstFrameRendered(Call.Side side);

        void onLocalFrameResolutionChanged(int i, int i2, int i3);

        void onRemoteFrameResolutionChanged(int i, int i2, int i3);
    }

    public VideoRendererEventsHandler(Call.Side side, WebRTCRendererShim webRTCRendererShim, VideoRendererEventsListener videoRendererEventsListener, Handler handler, boolean z) {
        this.side = side;
        this.renderer = webRTCRendererShim;
        this.callbackHandler = handler;
        this.videoRendererEventsListener = videoRendererEventsListener;
        this.shouldSimulateFirstFrameReceived = z;
    }

    @Override // org.webrtc.RendererEventsShim
    public void onFirstFrameReceived() {
        this.callbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.2
            @Override // java.lang.Runnable
            public void run() {
                VideoRendererEventsHandler.this.renderer.setFirstFrameReceived();
                if (VideoRendererEventsHandler.this.videoRendererEventsListener != null) {
                    VideoRendererEventsHandler.this.videoRendererEventsListener.onFirstFrameReceived(VideoRendererEventsHandler.this.side);
                } else {
                    VideoRendererEventsHandler.log.i("onFirstFrameReceived when session is not attached to a call");
                }
                CommsLogger commsLogger = VideoRendererEventsHandler.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onFirstFrameReceived, side:");
                outline107.append(VideoRendererEventsHandler.this.side);
                commsLogger.i(outline107.toString());
            }
        });
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFirstFrameRendered() {
        if (this.shouldSimulateFirstFrameReceived) {
            onFirstFrameReceived();
        }
        this.callbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.1
            @Override // java.lang.Runnable
            public void run() {
                VideoRendererEventsHandler.this.renderer.setFirstFrameRendered();
                if (VideoRendererEventsHandler.this.videoRendererEventsListener != null) {
                    VideoRendererEventsHandler.this.videoRendererEventsListener.onFirstFrameRendered(VideoRendererEventsHandler.this.side);
                }
                CommsLogger commsLogger = VideoRendererEventsHandler.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onFirstFrameRendered, side:");
                outline107.append(VideoRendererEventsHandler.this.side);
                commsLogger.i(outline107.toString());
            }
        });
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(final int i, final int i2, final int i3) {
        Call.Side side = this.side;
        if (side == Call.Side.Remote) {
            this.callbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    VideoRendererEventsHandler.this.videoRendererEventsListener.onRemoteFrameResolutionChanged(i, i2, i3);
                    CommsLogger commsLogger = VideoRendererEventsHandler.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onRemoteFrameResolutionChanged (remote): ");
                    outline107.append(i);
                    outline107.append(ReactProperties.HereMapMarker.X);
                    outline107.append(i2);
                    outline107.append(" ");
                    outline107.append(i3);
                    outline107.append("degrees");
                    commsLogger.i(outline107.toString());
                }
            });
        } else if (side != Call.Side.Local) {
        } else {
            this.callbackHandler.post(new Runnable() { // from class: com.amazon.comms.ringservice.webrtc.VideoRendererEventsHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    if (VideoRendererEventsHandler.this.videoRendererEventsListener != null) {
                        VideoRendererEventsHandler.this.videoRendererEventsListener.onLocalFrameResolutionChanged(i, i2, i3);
                        CommsLogger commsLogger = VideoRendererEventsHandler.log;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onLocalFrameResolutionChanged (local): ");
                        outline107.append(i);
                        outline107.append(ReactProperties.HereMapMarker.X);
                        outline107.append(i2);
                        outline107.append(" ");
                        outline107.append(i3);
                        outline107.append("degrees");
                        commsLogger.i(outline107.toString());
                    }
                }
            });
        }
    }
}
