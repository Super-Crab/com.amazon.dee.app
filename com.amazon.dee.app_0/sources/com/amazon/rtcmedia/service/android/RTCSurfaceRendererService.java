package com.amazon.rtcmedia.service.android;

import android.view.Surface;
import com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface;
import com.amazon.rtcmedia.util.EglBaseProvider;
import com.amazon.rtcmedia.util.MediaUIBridge;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazon.rtcmedia.util.ViewDirection;
import com.amazon.rtcmedia.webrtc.RTCRendererEventsHandler;
import com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer;
import com.facebook.react.uimanager.ViewProps;
import org.webrtc.RendererCommon;
/* loaded from: classes13.dex */
public class RTCSurfaceRendererService implements RTCSurfaceRenderer.RendererCallbacks, RTCSurfaceRendererServiceInterface {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCSurfaceRendererService.class);
    private ViewDirection direction;
    private RTCSurfaceRenderer renderer = null;
    private RTCSurfaceRendererServiceInterface.RendererListener rtcSurfaceRendererListener;
    private String sessionId;

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void clearImage() {
        mLog.i("clearImage");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.clearImage();
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void init(RTCSurfaceRendererServiceInterface.RendererListener rendererListener, ViewDirection viewDirection, String str, String str2) {
        mLog.i("init");
        RtcscLogger rtcscLogger = mLog;
        rtcscLogger.i("RTCViewRenderConnector, resourceName = " + str2 + ", sessionId = " + str);
        this.rtcSurfaceRendererListener = rendererListener;
        this.direction = viewDirection;
        this.sessionId = str;
        this.renderer = new RTCSurfaceRenderer(this, str2);
        if (this.direction == ViewDirection.LOCAL_VIEW) {
            MediaUIBridge.getInstance().addLocalRenderer(str, this.renderer);
        } else {
            MediaUIBridge.getInstance().addRemoteRenderer(str, this.renderer);
        }
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.init(EglBaseProvider.getEglBaseContext(), new RTCRendererEventsHandler(this.direction));
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public int onGetHeight() {
        mLog.i("getHeight");
        return this.rtcSurfaceRendererListener.onGetHeight();
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public int onGetWidth() {
        mLog.i("getWidth");
        return this.rtcSurfaceRendererListener.onGetWidth();
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public void onHolderSetFixedSize(int i, int i2) {
        mLog.i("onSetFixedSize");
        this.rtcSurfaceRendererListener.onHolderSetFixedSize(i, i2);
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public void onHolderSetSizeFromLayout() {
        mLog.i("onSetSizeFromLayout");
        this.rtcSurfaceRendererListener.onHolderSetSizeFromLayout();
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        mLog.i(ViewProps.ON_LAYOUT);
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.onLayout(z, i, i2, i3, i4);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void onMeasure(int i, int i2) {
        mLog.i("onMeasure");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.onMeasure(i, i2);
        }
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public void onMeasuredDimension(int i, int i2) {
        mLog.i("onMeasuredDimension");
        this.rtcSurfaceRendererListener.onMeasuredDimension(i, i2);
    }

    @Override // com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.RendererCallbacks
    public void onRequestLayout() {
        mLog.i("onRequestLayout");
        this.rtcSurfaceRendererListener.onRequestLayout();
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void release() {
        mLog.i("release");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.release();
        }
        if (this.direction == ViewDirection.LOCAL_VIEW) {
            MediaUIBridge.getInstance().removeLocalRenderer(this.sessionId, this.renderer);
        } else {
            MediaUIBridge.getInstance().removeRemoteRenderer(this.sessionId, this.renderer);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void setEnableHardwareScaler(Boolean bool) {
        mLog.i("setEnableHardwareScaler");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.setEnableHardwareScaler(bool.booleanValue());
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void setMirror(boolean z) {
        mLog.i("setMirror");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.setMirror(z);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void setScalingType(RTCSurfaceRendererServiceInterface.ScalingType scalingType) {
        mLog.i("setScalingType");
        if (this.renderer != null) {
            this.renderer.setScalingType(RendererCommon.ScalingType.valueOf(scalingType.name()));
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void surfaceChanged(Surface surface, int i, int i2, int i3) {
        mLog.i("surfaceChanged");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.surfaceChanged(surface, i, i2, i3);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void surfaceCreated(Surface surface) {
        mLog.i("surfaceCreated");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.surfaceCreated(surface);
        }
    }

    @Override // com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface
    public void surfaceDestroyed(Surface surface) {
        mLog.i("surfaceDestroyed");
        RTCSurfaceRenderer rTCSurfaceRenderer = this.renderer;
        if (rTCSurfaceRenderer != null) {
            rTCSurfaceRenderer.surfaceDestroyed(surface);
        }
    }
}
