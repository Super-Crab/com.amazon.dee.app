package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import org.webrtc.EglBase;
import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    private OnTextureFrameAvailableListener listener;
    private final int oesTextureId;
    private OesConverter oesToRgbConverter;
    private OnTextureFrameAvailableListener pendingListener;
    private final int rgbTextureId;
    private RgbConverter rgbToYuvConverter;
    final Runnable setListenerRunnable;
    private final SurfaceTexture surfaceTexture;
    private YuvConverter yuvConverter;

    /* loaded from: classes5.dex */
    public interface OnTextureFrameAvailableListener {
        void onTextureFrameAvailable(int i, float[] fArr, long j);
    }

    public static SurfaceTextureHelper create(final String str, final EglBase.Context context) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeAtFrontUninterruptibly(handler, new Callable<SurfaceTextureHelper>() { // from class: org.webrtc.SurfaceTextureHelper.1
            @Override // java.util.concurrent.Callable
            public SurfaceTextureHelper call() {
                try {
                    return new SurfaceTextureHelper(EglBase.Context.this, handler);
                } catch (RuntimeException e) {
                    Logging.e(SurfaceTextureHelper.TAG, str + " create failure", e);
                    return null;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        if (this.handler.getLooper().getThread() == Thread.currentThread()) {
            if (!this.isTextureInUse && this.isQuitting) {
                YuvConverter yuvConverter = this.yuvConverter;
                if (yuvConverter != null) {
                    yuvConverter.release();
                }
                OesConverter oesConverter = this.oesToRgbConverter;
                if (oesConverter != null) {
                    oesConverter.release();
                }
                RgbConverter rgbConverter = this.rgbToYuvConverter;
                if (rgbConverter != null) {
                    rgbConverter.release();
                }
                GLES20.glDeleteTextures(2, new int[]{this.oesTextureId, this.rgbTextureId}, 0);
                this.surfaceTexture.release();
                this.eglBase.release();
                this.handler.getLooper().quit();
                return;
            }
            throw new IllegalStateException("Unexpected release.");
        }
        throw new IllegalStateException("Wrong thread.");
    }

    @TargetApi(21)
    private static void setOnFrameAvailableListener(SurfaceTexture surfaceTexture, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, Handler handler) {
        int i = Build.VERSION.SDK_INT;
        surfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener, handler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryDeliverTextureFrame() {
        if (this.handler.getLooper().getThread() == Thread.currentThread()) {
            if (this.isQuitting || !this.hasPendingTexture || this.isTextureInUse || this.listener == null) {
                return;
            }
            this.isTextureInUse = true;
            this.hasPendingTexture = false;
            updateTexImage();
            float[] fArr = new float[16];
            this.surfaceTexture.getTransformMatrix(fArr);
            this.listener.onTextureFrameAvailable(this.oesTextureId, fArr, this.surfaceTexture.getTimestamp());
            return;
        }
        throw new IllegalStateException("Wrong thread.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTexImage() {
        synchronized (EglBase.lock) {
            this.surfaceTexture.updateTexImage();
        }
    }

    public void RGBToYUV(final ByteBuffer byteBuffer, final int i, final int i2, final int i3, final int i4, final float[] fArr) {
        if (i4 == this.rgbTextureId) {
            ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.7
                @Override // java.lang.Runnable
                public void run() {
                    if (SurfaceTextureHelper.this.rgbToYuvConverter == null) {
                        SurfaceTextureHelper.this.rgbToYuvConverter = new RgbConverter();
                    }
                    SurfaceTextureHelper.this.rgbToYuvConverter.convert(byteBuffer, i, i2, i3, i4, fArr);
                }
            });
            return;
        }
        throw new IllegalStateException("textureToByteBuffer called with unexpected textureId");
    }

    public VideoFrame.TextureBuffer createProcessedTextureBuffer(final int i, final int i2, Matrix matrix, final int i3) {
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.9
            @Override // java.lang.Runnable
            public void run() {
                if (SurfaceTextureHelper.this.oesToRgbConverter == null) {
                    SurfaceTextureHelper.this.oesToRgbConverter = new OesConverter();
                    SurfaceTextureHelper.this.oesToRgbConverter.setParameters(i, i2, SurfaceTextureHelper.this.rgbTextureId);
                }
                SurfaceTextureHelper.this.oesToRgbConverter.convert(SurfaceTextureHelper.this.oesTextureId, i, i2, i3, RendererCommon.identityMatrix());
            }
        });
        return new TextureBufferImpl(i, i2, VideoFrame.TextureBuffer.Type.RGB, this.rgbTextureId, matrix, this, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.10
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.returnTextureFrame();
            }
        });
    }

    public VideoFrame.TextureBuffer createTextureBuffer(int i, int i2, Matrix matrix) {
        return new TextureBufferImpl(i, i2, VideoFrame.TextureBuffer.Type.OES, this.oesTextureId, matrix, this, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.8
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.returnTextureFrame();
            }
        });
    }

    public void dispose() {
        Logging.d(TAG, "dispose()");
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.5
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.isQuitting = true;
                if (!SurfaceTextureHelper.this.isTextureInUse) {
                    SurfaceTextureHelper.this.release();
                }
            }
        });
    }

    public Handler getHandler() {
        return this.handler;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$SurfaceTextureHelper(SurfaceTexture surfaceTexture) {
        this.hasPendingTexture = true;
        tryDeliverTextureFrame();
    }

    public void returnTextureFrame() {
        this.handler.post(new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.4
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.isTextureInUse = false;
                if (SurfaceTextureHelper.this.isQuitting) {
                    SurfaceTextureHelper.this.release();
                } else {
                    SurfaceTextureHelper.this.tryDeliverTextureFrame();
                }
            }
        });
    }

    public void startListening(OnTextureFrameAvailableListener onTextureFrameAvailableListener) {
        if (this.listener == null && this.pendingListener == null) {
            this.pendingListener = onTextureFrameAvailableListener;
            this.handler.post(this.setListenerRunnable);
            return;
        }
        throw new IllegalStateException("SurfaceTextureHelper listener has already been set.");
    }

    public void stopListening() {
        Logging.d(TAG, "stopListening()");
        this.handler.removeCallbacks(this.setListenerRunnable);
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.3
            @Override // java.lang.Runnable
            public void run() {
                SurfaceTextureHelper.this.listener = null;
                SurfaceTextureHelper.this.pendingListener = null;
            }
        });
    }

    public void textureToYUV(final ByteBuffer byteBuffer, final int i, final int i2, final int i3, final int i4, final float[] fArr) {
        if (i4 == this.oesTextureId) {
            ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.6
                @Override // java.lang.Runnable
                public void run() {
                    if (SurfaceTextureHelper.this.yuvConverter == null) {
                        SurfaceTextureHelper.this.yuvConverter = new YuvConverter();
                    }
                    SurfaceTextureHelper.this.yuvConverter.convert(byteBuffer, i, i2, i3, i4, fArr);
                }
            });
            return;
        }
        throw new IllegalStateException("textureToByteBuffer called with unexpected textureId");
    }

    private SurfaceTextureHelper(EglBase.Context context, Handler handler) {
        this.hasPendingTexture = false;
        this.isTextureInUse = false;
        this.isQuitting = false;
        this.setListenerRunnable = new Runnable() { // from class: org.webrtc.SurfaceTextureHelper.2
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Setting listener to ");
                outline107.append(SurfaceTextureHelper.this.pendingListener);
                Logging.d(SurfaceTextureHelper.TAG, outline107.toString());
                SurfaceTextureHelper surfaceTextureHelper = SurfaceTextureHelper.this;
                surfaceTextureHelper.listener = surfaceTextureHelper.pendingListener;
                SurfaceTextureHelper.this.pendingListener = null;
                if (SurfaceTextureHelper.this.hasPendingTexture) {
                    SurfaceTextureHelper.this.updateTexImage();
                    SurfaceTextureHelper.this.hasPendingTexture = false;
                }
            }
        };
        if (handler.getLooper().getThread() == Thread.currentThread()) {
            this.handler = handler;
            this.eglBase = EglBase.create(context, EglBase.CONFIG_PIXEL_BUFFER);
            try {
                this.eglBase.createDummyPbufferSurface();
                this.eglBase.makeCurrent();
                this.oesTextureId = GlUtil.generateTexture(36197);
                this.rgbTextureId = GlUtil.generateTexture(3553);
                this.surfaceTexture = new SurfaceTexture(this.oesTextureId);
                setOnFrameAvailableListener(this.surfaceTexture, new SurfaceTexture.OnFrameAvailableListener(this) { // from class: org.webrtc.SurfaceTextureHelper$$Lambda$0
                    private final SurfaceTextureHelper arg$1;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                    }

                    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
                    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        this.arg$1.lambda$new$0$SurfaceTextureHelper(surfaceTexture);
                    }
                }, handler);
                return;
            } catch (RuntimeException e) {
                this.eglBase.release();
                handler.getLooper().quit();
                throw e;
            }
        }
        throw new IllegalStateException("SurfaceTextureHelper must be created on the handler thread");
    }
}
