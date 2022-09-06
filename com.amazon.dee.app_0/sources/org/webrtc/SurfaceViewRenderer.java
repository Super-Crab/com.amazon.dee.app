package org.webrtc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CountDownLatch;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
/* loaded from: classes5.dex */
public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback, VideoRenderer.Callbacks, VideoSink {
    private static final String TAG = "SurfaceViewRenderer";
    private final EglRenderer eglRenderer;
    private boolean enableFixedSize;
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private boolean isRenderingPaused;
    private final Object layoutLock;
    private RendererCommon.RendererEvents rendererEvents;
    private final String resourceName;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure;

    public SurfaceViewRenderer(Context context) {
        super(context);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        this.layoutLock = new Object();
        this.isRenderingPaused = false;
        this.resourceName = getResourceName();
        this.eglRenderer = new EglRenderer(this.resourceName);
        getHolder().addCallback(this);
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + RealTimeTextConstants.COLON_SPACE;
        } catch (Resources.NotFoundException unused) {
            return "";
        }
    }

    private void logD(String str) {
        Logging.d(TAG, this.resourceName + str);
    }

    private void updateFrameDimensionsAndReportEvents(VideoRenderer.I420Frame i420Frame) {
        synchronized (this.layoutLock) {
            if (this.isRenderingPaused) {
                return;
            }
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                logD("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (this.rotatedFrameWidth != i420Frame.rotatedWidth() || this.rotatedFrameHeight != i420Frame.rotatedHeight() || this.frameRotation != i420Frame.rotationDegree) {
                logD("Reporting frame resolution changed to " + i420Frame.width + ReactProperties.HereMapMarker.X + i420Frame.height + " with rotation " + i420Frame.rotationDegree);
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.rotatedFrameWidth = i420Frame.rotatedWidth();
                this.rotatedFrameHeight = i420Frame.rotatedHeight();
                this.frameRotation = i420Frame.rotationDegree;
                post(new Runnable() { // from class: org.webrtc.SurfaceViewRenderer.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SurfaceViewRenderer.this.updateSurfaceSize();
                        SurfaceViewRenderer.this.requestLayout();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSurfaceSize() {
        int i;
        int i2;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            if (this.enableFixedSize && this.rotatedFrameWidth != 0 && this.rotatedFrameHeight != 0 && getWidth() != 0 && getHeight() != 0) {
                float width = getWidth() / getHeight();
                if (this.rotatedFrameWidth / this.rotatedFrameHeight > width) {
                    i2 = (int) (this.rotatedFrameHeight * width);
                    i = this.rotatedFrameHeight;
                } else {
                    int i3 = this.rotatedFrameWidth;
                    i = (int) (this.rotatedFrameWidth / width);
                    i2 = i3;
                }
                int min = Math.min(getWidth(), i2);
                int min2 = Math.min(getHeight(), i);
                logD("updateSurfaceSize. Layout size: " + getWidth() + ReactProperties.HereMapMarker.X + getHeight() + ", frame size: " + this.rotatedFrameWidth + ReactProperties.HereMapMarker.X + this.rotatedFrameHeight + ", requested surface size: " + min + ReactProperties.HereMapMarker.X + min2 + ", old surface size: " + this.surfaceWidth + ReactProperties.HereMapMarker.X + this.surfaceHeight);
                if (min != this.surfaceWidth || min2 != this.surfaceHeight) {
                    this.surfaceWidth = min;
                    this.surfaceHeight = min2;
                    getHolder().setFixedSize(min, min2);
                }
            } else {
                this.surfaceHeight = 0;
                this.surfaceWidth = 0;
                getHolder().setSizeFromLayout();
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

    public void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents) {
        init(context, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$updateFrameDimensionsAndReportEvents$0$SurfaceViewRenderer() {
        updateSurfaceSize();
        requestLayout();
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        updateFrameDimensionsAndReportEvents(videoFrame);
        this.eglRenderer.onFrame(videoFrame);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.setLayoutAspectRatio((i3 - i) / (i4 - i2));
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        Point measure;
        ThreadUtils.checkIsOnMainThread();
        synchronized (this.layoutLock) {
            measure = this.videoLayoutMeasure.measure(i, i2, this.rotatedFrameWidth, this.rotatedFrameHeight);
        }
        setMeasuredDimension(measure.x, measure.y);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMeasure(). New size: ");
        outline107.append(measure.x);
        outline107.append(ReactProperties.HereMapMarker.X);
        outline107.append(measure.y);
        logD(outline107.toString());
    }

    public void pauseVideo() {
        synchronized (this.layoutLock) {
            this.isRenderingPaused = true;
        }
        this.eglRenderer.pauseVideo();
    }

    public void release() {
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
        ThreadUtils.checkIsOnMainThread();
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
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingType);
        requestLayout();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        ThreadUtils.checkIsOnMainThread();
        logD("surfaceChanged: format: " + i + " size: " + i2 + ReactProperties.HereMapMarker.X + i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.createEglSurface(surfaceHolder.getSurface());
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        ThreadUtils.checkIsOnMainThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.eglRenderer.releaseEglSurface(new Runnable() { // from class: org.webrtc.SurfaceViewRenderer.1
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
        ThreadUtils.checkIsOnMainThread();
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
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(scalingType, scalingType2);
        requestLayout();
    }

    public SurfaceViewRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        this.layoutLock = new Object();
        this.isRenderingPaused = false;
        this.resourceName = getResourceName();
        this.eglRenderer = new EglRenderer(this.resourceName);
        getHolder().addCallback(this);
    }

    private void updateFrameDimensionsAndReportEvents(VideoFrame videoFrame) {
        synchronized (this.layoutLock) {
            if (this.isRenderingPaused) {
                return;
            }
            if (!this.isFirstFrameRendered) {
                this.isFirstFrameRendered = true;
                logD("Reporting first rendered frame.");
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFirstFrameRendered();
                }
            }
            if (this.rotatedFrameWidth != videoFrame.getRotatedWidth() || this.rotatedFrameHeight != videoFrame.getRotatedHeight() || this.frameRotation != videoFrame.getRotation()) {
                logD("Reporting frame resolution changed to " + videoFrame.getBuffer().getWidth() + ReactProperties.HereMapMarker.X + videoFrame.getBuffer().getHeight() + " with rotation " + videoFrame.getRotation());
                if (this.rendererEvents != null) {
                    this.rendererEvents.onFrameResolutionChanged(videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation());
                }
                this.rotatedFrameWidth = videoFrame.getRotatedWidth();
                this.rotatedFrameHeight = videoFrame.getRotatedHeight();
                this.frameRotation = videoFrame.getRotation();
                post(new Runnable(this) { // from class: org.webrtc.SurfaceViewRenderer$$Lambda$0
                    private final SurfaceViewRenderer arg$1;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$updateFrameDimensionsAndReportEvents$0$SurfaceViewRenderer();
                    }
                });
            }
        }
    }
}
