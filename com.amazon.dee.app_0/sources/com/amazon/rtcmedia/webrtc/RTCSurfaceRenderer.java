package com.amazon.rtcmedia.webrtc;

import android.graphics.Point;
import android.view.Surface;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.GlRectDrawer;
import org.webrtc.RendererCommon;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoFrame;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSink;
/* loaded from: classes13.dex */
public class RTCSurfaceRenderer implements VideoRenderer.Callbacks, VideoSink {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCSurfaceRenderer.class);
    private RendererCallbacks callback;
    private final EglRenderer eglRenderer;
    private boolean enableFixedSize;
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private RendererCommon.RendererEvents rendererEvents;
    private final String resourceName;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
    private final Object layoutLock = new Object();
    private boolean isRenderingPaused = false;

    /* loaded from: classes13.dex */
    public interface RendererCallbacks {
        int onGetHeight();

        int onGetWidth();

        void onHolderSetFixedSize(int i, int i2);

        void onHolderSetSizeFromLayout();

        void onMeasuredDimension(int i, int i2);

        void onRequestLayout();
    }

    public RTCSurfaceRenderer(RendererCallbacks rendererCallbacks, String str) {
        this.callback = null;
        this.resourceName = str;
        this.eglRenderer = new EglRenderer(str);
        if (rendererCallbacks != null) {
            this.callback = rendererCallbacks;
            return;
        }
        throw new RuntimeException("Null callback when creating RTCSurfaceRenderer");
    }

    private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame i420Frame) {
        synchronized (this.layoutLock) {
            if (this.isRenderingPaused) {
                return;
            }
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                mLog.d("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (this.rotatedFrameWidth != i420Frame.rotatedWidth() || this.rotatedFrameHeight != i420Frame.rotatedHeight() || this.frameRotation != i420Frame.rotationDegree) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.d("Reporting frame resolution changed to " + i420Frame.width + ReactProperties.HereMapMarker.X + i420Frame.height + " with rotation " + i420Frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.rotatedFrameWidth = i420Frame.rotatedWidth();
                this.rotatedFrameHeight = i420Frame.rotatedHeight();
                this.frameRotation = i420Frame.rotationDegree;
                updateSurfaceSize();
                this.callback.onRequestLayout();
            }
        }
    }

    private void updateSurfaceSize() {
        int i;
        int i2;
        synchronized (this.layoutLock) {
            if (this.enableFixedSize && this.rotatedFrameWidth != 0 && this.rotatedFrameHeight != 0 && this.callback.onGetWidth() != 0 && this.callback.onGetHeight() != 0) {
                float onGetWidth = this.callback.onGetWidth() / this.callback.onGetHeight();
                if (this.rotatedFrameWidth / this.rotatedFrameHeight > onGetWidth) {
                    i2 = (int) (this.rotatedFrameHeight * onGetWidth);
                    i = this.rotatedFrameHeight;
                } else {
                    int i3 = this.rotatedFrameWidth;
                    i = (int) (this.rotatedFrameWidth / onGetWidth);
                    i2 = i3;
                }
                int min = Math.min(this.callback.onGetWidth(), i2);
                int min2 = Math.min(this.callback.onGetHeight(), i);
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.d("updateSurfaceSize. Layout size: " + this.callback.onGetWidth() + ReactProperties.HereMapMarker.X + this.callback.onGetHeight() + ", frame size: " + this.rotatedFrameWidth + ReactProperties.HereMapMarker.X + this.rotatedFrameHeight + ", requested surface size: " + min + ReactProperties.HereMapMarker.X + min2 + ", old surface size: " + this.surfaceWidth + ReactProperties.HereMapMarker.X + this.surfaceHeight);
                if (min != this.surfaceWidth || min2 != this.surfaceHeight) {
                    this.surfaceWidth = min;
                    this.surfaceHeight = min2;
                    this.callback.onHolderSetFixedSize(min, min2);
                }
            } else {
                this.surfaceHeight = 0;
                this.surfaceWidth = 0;
                this.callback.onHolderSetSizeFromLayout();
            }
        }
    }

    public void addFrameListener(EglRenderer.FrameListener frameListener, float f, RendererCommon.GlDrawer glDrawer) {
        this.eglRenderer.addFrameListener(frameListener, f, glDrawer);
    }

    public void clearImage() {
        this.eglRenderer.clearImage();
    }

    public void disableFpsReduction() {
        synchronized (this.layoutLock) {
            this.isRenderingPaused = false;
        }
        this.eglRenderer.disableFpsReduction();
    }

    public RTCRendererEventsHandler getRendererEventsHandler() {
        mLog.d("getRendererEventsHandler");
        RendererCommon.RendererEvents rendererEvents = this.rendererEvents;
        if (rendererEvents instanceof RTCRendererEventsHandler) {
            return (RTCRendererEventsHandler) rendererEvents;
        }
        mLog.w("rendererEvents not implemented by RTCRendererEventsHandler.");
        return null;
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents) {
        mLog.d("init");
        init(context, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        updateFrameDimensionsAndReportEvents(videoFrame);
        this.eglRenderer.onFrame(videoFrame);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        mLog.d(ViewProps.ON_LAYOUT);
        this.eglRenderer.setLayoutAspectRatio((i3 - i) / (i4 - i2));
        updateSurfaceSize();
    }

    public void onMeasure(int i, int i2) {
        Point measure;
        mLog.d("onMeasure");
        synchronized (this.layoutLock) {
            measure = this.videoLayoutMeasure.measure(i, i2, this.rotatedFrameWidth, this.rotatedFrameHeight);
        }
        this.callback.onMeasuredDimension(measure.x, measure.y);
    }

    public void pauseVideo() {
        synchronized (this.layoutLock) {
            this.isRenderingPaused = true;
        }
        this.eglRenderer.pauseVideo();
    }

    public void release() {
        mLog.d("release");
        this.eglRenderer.release();
    }

    public void removeFrameListener(EglRenderer.FrameListener frameListener) {
        this.eglRenderer.removeFrameListener(frameListener);
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        updateFrameDimensionsAndReportEvents(i420Frame);
        this.eglRenderer.renderFrame(i420Frame);
    }

    public void setEnableHardwareScaler(boolean z) {
        this.enableFixedSize = z;
        updateSurfaceSize();
    }

    public void setFpsReduction(float f) {
        synchronized (this.layoutLock) {
            this.isRenderingPaused = f == 0.0f;
        }
        this.eglRenderer.setFpsReduction(f);
    }

    public void setMirror(boolean z) {
        this.eglRenderer.setMirror(z);
    }

    public void setScalingType(RendererCommon.ScalingType scalingType) {
        this.videoLayoutMeasure.setScalingType(scalingType);
        this.callback.onRequestLayout();
    }

    public void surfaceChanged(Surface surface, int i, int i2, int i3) {
        mLog.d("surfaceChanged");
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("surfaceChanged: format: ", i, " size: ", i2, ReactProperties.HereMapMarker.X);
        outline110.append(i3);
        rtcscLogger.d(outline110.toString());
    }

    public void surfaceCreated(Surface surface) {
        mLog.d("surfaceCreated");
        this.eglRenderer.createEglSurface(surface);
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
    }

    public void surfaceDestroyed(Surface surface) {
        mLog.d("surfaceDestroyed");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.eglRenderer.releaseEglSurface(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.RTCSurfaceRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                countDownLatch.countDown();
            }
        });
        ThreadUtils.awaitUninterruptibly(countDownLatch);
    }

    public void addFrameListener(EglRenderer.FrameListener frameListener, float f) {
        this.eglRenderer.addFrameListener(frameListener, f);
    }

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents, int[] iArr, RendererCommon.GlDrawer glDrawer) {
        this.rendererEvents = rendererEvents;
        synchronized (this.layoutLock) {
            this.isFirstFrameRendered = false;
            this.rotatedFrameWidth = 0;
            this.rotatedFrameHeight = 0;
            this.frameRotation = 0;
        }
        this.eglRenderer.init(context, iArr, glDrawer);
    }

    public void setScalingType(RendererCommon.ScalingType scalingType, RendererCommon.ScalingType scalingType2) {
        this.videoLayoutMeasure.setScalingType(scalingType, scalingType2);
        this.callback.onRequestLayout();
    }

    private void updateFrameDimensionsAndReportEvents(VideoFrame videoFrame) {
        synchronized (this.layoutLock) {
            if (this.isRenderingPaused) {
                return;
            }
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                mLog.d("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (this.rotatedFrameWidth != videoFrame.getRotatedWidth() || this.rotatedFrameHeight != videoFrame.getRotatedHeight() || this.frameRotation != videoFrame.getRotation()) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.d("Reporting frame resolution changed to " + videoFrame.getBuffer().getWidth() + ReactProperties.HereMapMarker.X + videoFrame.getBuffer().getHeight() + " with rotation " + videoFrame.getRotation());
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation());
                }
                this.rotatedFrameWidth = videoFrame.getRotatedWidth();
                this.rotatedFrameHeight = videoFrame.getRotatedHeight();
                this.frameRotation = videoFrame.getRotation();
                updateSurfaceSize();
                this.callback.onRequestLayout();
            }
        }
    }
}
