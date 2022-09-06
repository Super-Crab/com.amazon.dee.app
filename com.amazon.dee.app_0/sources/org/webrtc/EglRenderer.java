package org.webrtc;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
/* loaded from: classes5.dex */
public class EglRenderer implements VideoRenderer.Callbacks, VideoSink {
    private static final long LOG_INTERVAL_SEC = 4;
    private static final int MAX_SURFACE_CLEAR_COUNT = 3;
    private static final String TAG = "EglRenderer";
    private GlTextureFrameBuffer bitmapTextureFramebuffer;
    private RendererCommon.GlDrawer drawer;
    private EglBase eglBase;
    private int framesDropped;
    private int framesReceived;
    private int framesRendered;
    private float layoutAspectRatio;
    private long minRenderPeriodNs;
    private boolean mirror;
    private final String name;
    private long nextFrameTimeNs;
    private VideoFrame pendingFrame;
    private long renderSwapBufferTimeNs;
    private Handler renderThreadHandler;
    private long renderTimeNs;
    private long statisticsStartTimeNs;
    private final Object handlerLock = new Object();
    private final ArrayList<FrameListenerAndParams> frameListeners = new ArrayList<>();
    private final Object fpsReductionLock = new Object();
    private final VideoFrameDrawer frameDrawer = new VideoFrameDrawer();
    private final Matrix drawMatrix = new Matrix();
    private final Object frameLock = new Object();
    private final Object layoutLock = new Object();
    private final Object statisticsLock = new Object();
    private final Runnable logStatisticsRunnable = new Runnable() { // from class: org.webrtc.EglRenderer.1
        @Override // java.lang.Runnable
        public void run() {
            EglRenderer.this.logStatistics();
            synchronized (EglRenderer.this.handlerLock) {
                if (EglRenderer.this.renderThreadHandler != null) {
                    EglRenderer.this.renderThreadHandler.removeCallbacks(EglRenderer.this.logStatisticsRunnable);
                    EglRenderer.this.renderThreadHandler.postDelayed(EglRenderer.this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4L));
                }
            }
        }
    };
    private final EglSurfaceCreation eglSurfaceCreationRunnable = new EglSurfaceCreation();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class EglSurfaceCreation implements Runnable {
        private Object surface;

        private EglSurfaceCreation() {
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            if (this.surface != null && EglRenderer.this.eglBase != null && !EglRenderer.this.eglBase.hasSurface()) {
                if (this.surface instanceof Surface) {
                    EglRenderer.this.eglBase.createSurface((Surface) this.surface);
                } else if (this.surface instanceof SurfaceTexture) {
                    EglRenderer.this.eglBase.createSurface((SurfaceTexture) this.surface);
                } else {
                    throw new IllegalStateException("Invalid surface: " + this.surface);
                }
                EglRenderer.this.eglBase.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }

        public synchronized void setSurface(Object obj) {
            this.surface = obj;
        }
    }

    /* loaded from: classes5.dex */
    public interface FrameListener {
        void onFrame(Bitmap bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class FrameListenerAndParams {
        public final boolean applyFpsReduction;
        public final RendererCommon.GlDrawer drawer;
        public final FrameListener listener;
        public final float scale;

        public FrameListenerAndParams(FrameListener frameListener, float f, RendererCommon.GlDrawer glDrawer, boolean z) {
            this.listener = frameListener;
            this.scale = f;
            this.drawer = glDrawer;
            this.applyFpsReduction = z;
        }
    }

    public EglRenderer(String str) {
        this.name = str;
    }

    private String averageTimeAsString(long j, int i) {
        if (i <= 0) {
            return RegionUtil.REGION_STRING_NA;
        }
        return TimeUnit.NANOSECONDS.toMicros(j / i) + " μs";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearSurfaceOnRenderThread */
    public void lambda$clearImage$6$EglRenderer(float f, float f2, float f3, float f4) {
        EglBase eglBase = this.eglBase;
        if (eglBase == null || !eglBase.hasSurface()) {
            return;
        }
        logD("clearSurface");
        GLES20.glClearColor(f, f2, f3, f4);
        GLES20.glClear(16384);
        this.eglBase.swapBuffers();
    }

    private void createEglSurfaceInternal(Object obj) {
        this.eglSurfaceCreationRunnable.setSurface(obj);
        postToRenderThread(this.eglSurfaceCreationRunnable);
    }

    private void logD(String str) {
        Logging.d(TAG, this.name + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logStatistics() {
        long nanoTime = System.nanoTime();
        synchronized (this.statisticsLock) {
            long j = nanoTime - this.statisticsStartTimeNs;
            if (j <= 0) {
                return;
            }
            float nanos = ((float) (this.framesRendered * TimeUnit.SECONDS.toNanos(1L))) / ((float) j);
            logD("Duration: " + TimeUnit.NANOSECONDS.toMillis(j) + " ms. Frames received: " + this.framesReceived + ". Dropped: " + this.framesDropped + ". Rendered: " + this.framesRendered + ". Render fps: " + String.format(Locale.US, "%.1f", Float.valueOf(nanos)) + ". Average render time: " + averageTimeAsString(this.renderTimeNs, this.framesRendered) + ". Average swapBuffer time: " + averageTimeAsString(this.renderSwapBufferTimeNs, this.framesRendered) + ".");
            resetStatistics(nanoTime);
        }
    }

    private void notifyCallbacks(VideoFrame videoFrame, boolean z) {
        if (this.frameListeners.isEmpty()) {
            return;
        }
        this.drawMatrix.reset();
        this.drawMatrix.preTranslate(0.5f, 0.5f);
        if (this.mirror) {
            this.drawMatrix.preScale(-1.0f, 1.0f);
        }
        this.drawMatrix.preScale(1.0f, -1.0f);
        this.drawMatrix.preTranslate(-0.5f, -0.5f);
        Iterator<FrameListenerAndParams> it2 = this.frameListeners.iterator();
        while (it2.hasNext()) {
            FrameListenerAndParams next = it2.next();
            if (z || !next.applyFpsReduction) {
                it2.remove();
                int rotatedWidth = (int) (next.scale * videoFrame.getRotatedWidth());
                int rotatedHeight = (int) (next.scale * videoFrame.getRotatedHeight());
                if (rotatedWidth != 0 && rotatedHeight != 0) {
                    if (this.bitmapTextureFramebuffer == null) {
                        this.bitmapTextureFramebuffer = new GlTextureFrameBuffer(6408);
                    }
                    this.bitmapTextureFramebuffer.setSize(rotatedWidth, rotatedHeight);
                    GLES20.glBindFramebuffer(36160, this.bitmapTextureFramebuffer.getFrameBufferId());
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.bitmapTextureFramebuffer.getTextureId(), 0);
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                    GLES20.glClear(16384);
                    this.frameDrawer.drawFrame(videoFrame, next.drawer, this.drawMatrix, 0, 0, rotatedWidth, rotatedHeight);
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(rotatedWidth * rotatedHeight * 4);
                    GLES20.glViewport(0, 0, rotatedWidth, rotatedHeight);
                    GLES20.glReadPixels(0, 0, rotatedWidth, rotatedHeight, 6408, FujifilmMakernoteDirectory.TAG_FILM_MODE, allocateDirect);
                    GLES20.glBindFramebuffer(36160, 0);
                    GlUtil.checkNoGLES2Error("EglRenderer.notifyCallbacks");
                    Bitmap createBitmap = Bitmap.createBitmap(rotatedWidth, rotatedHeight, Bitmap.Config.ARGB_8888);
                    createBitmap.copyPixelsFromBuffer(allocateDirect);
                    next.listener.onFrame(createBitmap);
                } else {
                    next.listener.onFrame(null);
                }
            }
        }
    }

    private void postToRenderThread(Runnable runnable) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.post(runnable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: renderFrameOnRenderThread */
    public void bridge$lambda$0$EglRenderer() {
        boolean z;
        float f;
        float f2;
        float f3;
        synchronized (this.frameLock) {
            if (this.pendingFrame == null) {
                return;
            }
            VideoFrame videoFrame = this.pendingFrame;
            this.pendingFrame = null;
            EglBase eglBase = this.eglBase;
            if (eglBase != null && eglBase.hasSurface()) {
                synchronized (this.fpsReductionLock) {
                    if (this.minRenderPeriodNs != Long.MAX_VALUE) {
                        if (this.minRenderPeriodNs > 0) {
                            long nanoTime = System.nanoTime();
                            if (nanoTime < this.nextFrameTimeNs) {
                                logD("Skipping frame rendering - fps reduction is active.");
                            } else {
                                this.nextFrameTimeNs += this.minRenderPeriodNs;
                                this.nextFrameTimeNs = Math.max(this.nextFrameTimeNs, nanoTime);
                            }
                        }
                        z = true;
                    }
                    z = false;
                }
                long nanoTime2 = System.nanoTime();
                float rotatedWidth = videoFrame.getRotatedWidth() / videoFrame.getRotatedHeight();
                synchronized (this.layoutLock) {
                    f = this.layoutAspectRatio != 0.0f ? this.layoutAspectRatio : rotatedWidth;
                }
                if (rotatedWidth > f) {
                    f3 = f / rotatedWidth;
                    f2 = 1.0f;
                } else {
                    f2 = rotatedWidth / f;
                    f3 = 1.0f;
                }
                this.drawMatrix.reset();
                this.drawMatrix.preTranslate(0.5f, 0.5f);
                if (this.mirror) {
                    this.drawMatrix.preScale(-1.0f, 1.0f);
                }
                this.drawMatrix.preScale(f3, f2);
                this.drawMatrix.preTranslate(-0.5f, -0.5f);
                if (z) {
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                    GLES20.glClear(16384);
                    this.frameDrawer.drawFrame(videoFrame, this.drawer, this.drawMatrix, 0, 0, this.eglBase.surfaceWidth(), this.eglBase.surfaceHeight());
                    long nanoTime3 = System.nanoTime();
                    this.eglBase.swapBuffers();
                    long nanoTime4 = System.nanoTime();
                    synchronized (this.statisticsLock) {
                        this.framesRendered++;
                        this.renderTimeNs = (nanoTime4 - nanoTime2) + this.renderTimeNs;
                        this.renderSwapBufferTimeNs = (nanoTime4 - nanoTime3) + this.renderSwapBufferTimeNs;
                    }
                }
                notifyCallbacks(videoFrame, z);
                videoFrame.release();
                return;
            }
            logD("Dropping frame - No surface");
            videoFrame.release();
        }
    }

    private void resetStatistics(long j) {
        synchronized (this.statisticsLock) {
            this.statisticsStartTimeNs = j;
            this.framesReceived = 0;
            this.framesDropped = 0;
            this.framesRendered = 0;
            this.renderTimeNs = 0L;
            this.renderSwapBufferTimeNs = 0L;
        }
    }

    public void addFrameListener(FrameListener frameListener, float f) {
        addFrameListener(frameListener, f, null, false);
    }

    public void clearImage() {
        clearImage(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void createEglSurface(Surface surface) {
        createEglSurfaceInternal(surface);
    }

    public void disableFpsReduction() {
        setFpsReduction(Float.POSITIVE_INFINITY);
    }

    public void init(final EglBase.Context context, final int[] iArr, RendererCommon.GlDrawer glDrawer) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                logD("Initializing EglRenderer");
                this.drawer = glDrawer;
                HandlerThread handlerThread = new HandlerThread(this.name + TAG);
                handlerThread.start();
                this.renderThreadHandler = new Handler(handlerThread.getLooper());
                ThreadUtils.invokeAtFrontUninterruptibly(this.renderThreadHandler, new Runnable(this, context, iArr) { // from class: org.webrtc.EglRenderer$$Lambda$0
                    private final EglRenderer arg$1;
                    private final EglBase.Context arg$2;
                    private final int[] arg$3;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                        this.arg$2 = context;
                        this.arg$3 = iArr;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$init$0$EglRenderer(this.arg$2, this.arg$3);
                    }
                });
                this.renderThreadHandler.post(this.eglSurfaceCreationRunnable);
                resetStatistics(System.nanoTime());
                this.renderThreadHandler.postDelayed(this.logStatisticsRunnable, TimeUnit.SECONDS.toMillis(4L));
            } else {
                throw new IllegalStateException(this.name + "Already initialized");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$addFrameListener$3$EglRenderer(RendererCommon.GlDrawer glDrawer, FrameListener frameListener, float f, boolean z) {
        if (glDrawer == null) {
            glDrawer = this.drawer;
        }
        this.frameListeners.add(new FrameListenerAndParams(frameListener, f, glDrawer, z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$init$0$EglRenderer(EglBase.Context context, int[] iArr) {
        if (context == null) {
            logD("EglBase10.create context");
            this.eglBase = EglBase.createEgl10(iArr);
            return;
        }
        logD("EglBase.create shared context");
        this.eglBase = EglBase.create(context, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$release$1$EglRenderer(CountDownLatch countDownLatch) {
        RendererCommon.GlDrawer glDrawer = this.drawer;
        if (glDrawer != null) {
            glDrawer.release();
            this.drawer = null;
        }
        this.frameDrawer.release();
        GlTextureFrameBuffer glTextureFrameBuffer = this.bitmapTextureFramebuffer;
        if (glTextureFrameBuffer != null) {
            glTextureFrameBuffer.release();
            this.bitmapTextureFramebuffer = null;
        }
        if (this.eglBase != null) {
            logD("eglBase detach and release.");
            this.eglBase.detachCurrent();
            this.eglBase.release();
            this.eglBase = null;
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$release$2$EglRenderer(Looper looper) {
        logD("Quitting render thread.");
        looper.quit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$releaseEglSurface$5$EglRenderer(Runnable runnable) {
        EglBase eglBase = this.eglBase;
        if (eglBase != null) {
            eglBase.detachCurrent();
            this.eglBase.releaseSurface();
        }
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$removeFrameListener$4$EglRenderer(CountDownLatch countDownLatch, FrameListener frameListener) {
        countDownLatch.countDown();
        Iterator<FrameListenerAndParams> it2 = this.frameListeners.iterator();
        while (it2.hasNext()) {
            if (it2.next().listener == frameListener) {
                it2.remove();
            }
        }
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        boolean z;
        synchronized (this.statisticsLock) {
            this.framesReceived++;
        }
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                logD("Dropping frame - Not initialized or already released.");
                return;
            }
            synchronized (this.frameLock) {
                z = this.pendingFrame != null;
                if (z) {
                    this.pendingFrame.release();
                }
                this.pendingFrame = videoFrame;
                this.pendingFrame.retain();
                this.renderThreadHandler.post(new Runnable(this) { // from class: org.webrtc.EglRenderer$$Lambda$5
                    private final EglRenderer arg$1;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.bridge$lambda$0$EglRenderer();
                    }
                });
            }
            if (!z) {
                return;
            }
            synchronized (this.statisticsLock) {
                this.framesDropped++;
            }
        }
    }

    public void pauseVideo() {
        setFpsReduction(0.0f);
    }

    public void printStackTrace() {
        synchronized (this.handlerLock) {
            Thread thread = this.renderThreadHandler == null ? null : this.renderThreadHandler.getLooper().getThread();
            if (thread != null) {
                StackTraceElement[] stackTrace = thread.getStackTrace();
                if (stackTrace.length > 0) {
                    logD("EglRenderer stack trace:");
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        logD(stackTraceElement.toString());
                    }
                }
            }
        }
    }

    public void release() {
        logD("Releasing.");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                logD("Already released");
                return;
            }
            this.renderThreadHandler.removeCallbacks(this.logStatisticsRunnable);
            this.renderThreadHandler.postAtFrontOfQueue(new Runnable(this, countDownLatch) { // from class: org.webrtc.EglRenderer$$Lambda$1
                private final EglRenderer arg$1;
                private final CountDownLatch arg$2;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.arg$1 = this;
                    this.arg$2 = countDownLatch;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$release$1$EglRenderer(this.arg$2);
                }
            });
            final Looper looper = this.renderThreadHandler.getLooper();
            this.renderThreadHandler.post(new Runnable(this, looper) { // from class: org.webrtc.EglRenderer$$Lambda$2
                private final EglRenderer arg$1;
                private final Looper arg$2;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.arg$1 = this;
                    this.arg$2 = looper;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$release$2$EglRenderer(this.arg$2);
                }
            });
            this.renderThreadHandler = null;
            ThreadUtils.awaitUninterruptibly(countDownLatch);
            synchronized (this.frameLock) {
                if (this.pendingFrame != null) {
                    this.pendingFrame.release();
                    this.pendingFrame = null;
                }
            }
            logD("Releasing done.");
        }
    }

    public void releaseEglSurface(final Runnable runnable) {
        this.eglSurfaceCreationRunnable.setSurface(null);
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler != null) {
                this.renderThreadHandler.removeCallbacks(this.eglSurfaceCreationRunnable);
                this.renderThreadHandler.postAtFrontOfQueue(new Runnable(this, runnable) { // from class: org.webrtc.EglRenderer$$Lambda$6
                    private final EglRenderer arg$1;
                    private final Runnable arg$2;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                        this.arg$2 = runnable;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$releaseEglSurface$5$EglRenderer(this.arg$2);
                    }
                });
                return;
            }
            runnable.run();
        }
    }

    public void removeFrameListener(final FrameListener frameListener) {
        if (Thread.currentThread() != this.renderThreadHandler.getLooper().getThread()) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            postToRenderThread(new Runnable(this, countDownLatch, frameListener) { // from class: org.webrtc.EglRenderer$$Lambda$4
                private final EglRenderer arg$1;
                private final CountDownLatch arg$2;
                private final EglRenderer.FrameListener arg$3;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.arg$1 = this;
                    this.arg$2 = countDownLatch;
                    this.arg$3 = frameListener;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$removeFrameListener$4$EglRenderer(this.arg$2, this.arg$3);
                }
            });
            ThreadUtils.awaitUninterruptibly(countDownLatch);
            return;
        }
        throw new RuntimeException("removeFrameListener must not be called on the render thread.");
    }

    @Override // org.webrtc.VideoRenderer.Callbacks
    public void renderFrame(VideoRenderer.I420Frame i420Frame) {
        VideoFrame videoFrame = i420Frame.toVideoFrame();
        onFrame(videoFrame);
        videoFrame.release();
    }

    public void setFpsReduction(float f) {
        logD("setFpsReduction: " + f);
        synchronized (this.fpsReductionLock) {
            long j = this.minRenderPeriodNs;
            if (f <= 0.0f) {
                this.minRenderPeriodNs = Long.MAX_VALUE;
            } else {
                this.minRenderPeriodNs = ((float) TimeUnit.SECONDS.toNanos(1L)) / f;
            }
            if (this.minRenderPeriodNs != j) {
                this.nextFrameTimeNs = System.nanoTime();
            }
        }
    }

    public void setLayoutAspectRatio(float f) {
        logD("setLayoutAspectRatio: " + f);
        synchronized (this.layoutLock) {
            this.layoutAspectRatio = f;
        }
    }

    public void setMirror(boolean z) {
        logD("setMirror: " + z);
        synchronized (this.layoutLock) {
            this.mirror = z;
        }
    }

    public void addFrameListener(FrameListener frameListener, float f, RendererCommon.GlDrawer glDrawer) {
        addFrameListener(frameListener, f, glDrawer, false);
    }

    public void clearImage(final float f, final float f2, final float f3, final float f4) {
        synchronized (this.handlerLock) {
            if (this.renderThreadHandler == null) {
                return;
            }
            this.renderThreadHandler.postAtFrontOfQueue(new Runnable(this, f, f2, f3, f4) { // from class: org.webrtc.EglRenderer$$Lambda$7
                private final EglRenderer arg$1;
                private final float arg$2;
                private final float arg$3;
                private final float arg$4;
                private final float arg$5;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.arg$1 = this;
                    this.arg$2 = f;
                    this.arg$3 = f2;
                    this.arg$4 = f3;
                    this.arg$5 = f4;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$clearImage$6$EglRenderer(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
                }
            });
        }
    }

    public void createEglSurface(SurfaceTexture surfaceTexture) {
        createEglSurfaceInternal(surfaceTexture);
    }

    public void addFrameListener(final FrameListener frameListener, final float f, final RendererCommon.GlDrawer glDrawer, final boolean z) {
        postToRenderThread(new Runnable(this, glDrawer, frameListener, f, z) { // from class: org.webrtc.EglRenderer$$Lambda$3
            private final EglRenderer arg$1;
            private final RendererCommon.GlDrawer arg$2;
            private final EglRenderer.FrameListener arg$3;
            private final float arg$4;
            private final boolean arg$5;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.arg$1 = this;
                this.arg$2 = glDrawer;
                this.arg$3 = frameListener;
                this.arg$4 = f;
                this.arg$5 = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.arg$1.lambda$addFrameListener$3$EglRenderer(this.arg$2, this.arg$3, this.arg$4, this.arg$5);
            }
        });
    }
}
