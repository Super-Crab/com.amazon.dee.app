package com.amazon.rtcmedia.webrtc;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import org.webrtc.ScreenCapturerAndroid;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
/* loaded from: classes13.dex */
public class RTCScreenCapturer extends ScreenCapturerAndroid {
    private static final int FRAME_TIMEOUT_SEC = 3;
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_INTERVAL_MILLIS = 1000;
    private VideoCapturer.CapturerObserver capturerObserver;
    private ContinuousCapturer capturerRunnable;
    private long continuousCapturerInterval;
    private int framerate;
    private int height;
    private Boolean isCapturerStopped;
    private Boolean isStopPermanent;
    private long lastFrameSurfaceTextureTimeStamp;
    private long lastFrameTimeStamp;
    private final Object lock;
    private int numRetries;
    private int oesTextureId;
    private final RestartCapturerAttempt restartRunnable;
    private final Handler rtcHandler;
    private Handler surfaceTextureThreadHandler;
    private float[] transformMatrix;
    private int width;
    private static final long FRAME_TIMEOUT_NS = TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS);
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCScreenCapturer.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class ContinuousCapturer implements Runnable {
        ContinuousCapturer() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (RTCScreenCapturer.this.lock) {
                if (RTCScreenCapturer.this.isCapturerStopped.booleanValue()) {
                    return;
                }
                int i = RTCScreenCapturer.this.width;
                int i2 = RTCScreenCapturer.this.height;
                long j = RTCScreenCapturer.this.continuousCapturerInterval;
                long nanoTime = System.nanoTime();
                RTCScreenCapturer.this.capturerObserver.onTextureFrameCaptured(i, i2, RTCScreenCapturer.this.oesTextureId, RTCScreenCapturer.this.transformMatrix, 0, RTCScreenCapturer.this.lastFrameSurfaceTextureTimeStamp + (nanoTime - RTCScreenCapturer.this.lastFrameTimeStamp));
                if (nanoTime - RTCScreenCapturer.this.lastFrameTimeStamp > RTCScreenCapturer.FRAME_TIMEOUT_NS) {
                    RTCScreenCapturer.mLog.w("More than 3s since last frame, suspending stream until more frames received");
                } else {
                    RTCScreenCapturer.this.surfaceTextureThreadHandler.postDelayed(this, j);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class RTCMediaProjectionCallback extends MediaProjection.Callback {
        private WeakReference<RTCScreenCapturer> weakCapturer;

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            WeakReference<RTCScreenCapturer> weakReference = this.weakCapturer;
            if (weakReference == null) {
                RTCScreenCapturer.mLog.e("Weak capturer reference unexpectedly null");
                return;
            }
            final RTCScreenCapturer rTCScreenCapturer = weakReference.get();
            if (rTCScreenCapturer == null) {
                RTCScreenCapturer.mLog.e("Capturer is null, it was likely garbage collected");
            } else {
                rTCScreenCapturer.rtcHandler.post(new Runnable() { // from class: com.amazon.rtcmedia.webrtc.RTCScreenCapturer.RTCMediaProjectionCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        rTCScreenCapturer.handleMediaProjectionStop();
                    }
                });
            }
        }

        public void setCapturer(@NonNull RTCScreenCapturer rTCScreenCapturer) {
            this.weakCapturer = new WeakReference<>(rTCScreenCapturer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class RestartCapturerAttempt implements Runnable {
        RestartCapturerAttempt() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RTCScreenCapturer.this.handleMediaProjectionRestartAttempt();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RTCScreenCapturer(Intent intent, MediaProjection.Callback callback, Handler handler) {
        super(intent, callback);
        this.restartRunnable = new RestartCapturerAttempt();
        this.lock = new Object();
        this.numRetries = 0;
        this.capturerRunnable = new ContinuousCapturer();
        this.isCapturerStopped = true;
        this.isStopPermanent = true;
        this.rtcHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMediaProjectionRestartAttempt() {
        boolean z;
        synchronized (this.lock) {
            if (this.isStopPermanent.booleanValue()) {
                mLog.i("Restart attempt: capturer has been permanently stopped, not restarting media projection");
                this.numRetries = 0;
            } else if (!this.isCapturerStopped.booleanValue()) {
                mLog.i("Restart attempt: capturer already started, no need to start again");
                this.numRetries = 0;
            } else {
                this.rtcHandler.removeCallbacks(this.restartRunnable);
                if (this.numRetries >= 3) {
                    return;
                }
                try {
                    startCapture(this.width, this.height, this.framerate);
                    this.numRetries = 0;
                    z = false;
                } catch (SecurityException e) {
                    mLog.e("Security exception occurred while trying to restart capture", e);
                    z = true;
                }
                if (!z) {
                    return;
                }
                this.numRetries++;
                if (this.numRetries >= 3) {
                    mLog.w("Attempts exhausted while trying to restart capture, giving up");
                    this.numRetries = 0;
                    return;
                }
                mLog.i("Will retry restarting capture in 1000ms");
                this.rtcHandler.postDelayed(this.restartRunnable, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMediaProjectionStop() {
        synchronized (this.lock) {
            if (this.isCapturerStopped.booleanValue()) {
                mLog.i("Capturer is stopped, not restarting media projection");
            } else if (this.isStopPermanent.booleanValue()) {
                mLog.i("Capturer has been permanently stopped, not restarting media projection");
            } else {
                mLog.i("handleMediaProjectionOnStop called");
                stopCapture();
                synchronized (this.lock) {
                    this.isStopPermanent = false;
                }
                handleMediaProjectionRestartAttempt();
            }
        }
    }

    @Override // org.webrtc.ScreenCapturerAndroid, org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Changing capture format: ", i, ReactProperties.HereMapMarker.X, i2, "@");
        outline110.append(i3);
        rtcscLogger.i(outline110.toString());
        synchronized (this.lock) {
            this.surfaceTextureThreadHandler.removeCallbacks(this.capturerRunnable);
            this.isCapturerStopped = true;
        }
        try {
            super.changeCaptureFormat(i, i2, i3);
        } catch (Exception e) {
            mLog.w("Exception occurred while changing capture format", e);
        }
        synchronized (this.lock) {
            this.width = i;
            this.height = i2;
            this.framerate = i3;
            this.continuousCapturerInterval = 1000 / i3;
        }
    }

    @Override // org.webrtc.ScreenCapturerAndroid, org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        synchronized (this.lock) {
            this.surfaceTextureThreadHandler = surfaceTextureHelper.getHandler();
            this.capturerObserver = capturerObserver;
        }
        super.initialize(surfaceTextureHelper, context, capturerObserver);
    }

    @Override // org.webrtc.ScreenCapturerAndroid, org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
        this.lastFrameSurfaceTextureTimeStamp = j;
        this.lastFrameTimeStamp = System.nanoTime();
        this.oesTextureId = i;
        this.transformMatrix = (float[]) fArr.clone();
        super.onTextureFrameAvailable(i, fArr, j);
        synchronized (this.lock) {
            if (!this.isStopPermanent.booleanValue()) {
                this.surfaceTextureThreadHandler.removeCallbacks(this.capturerRunnable);
                this.isCapturerStopped = false;
                this.surfaceTextureThreadHandler.postDelayed(this.capturerRunnable, this.continuousCapturerInterval);
            }
        }
    }

    @Override // org.webrtc.ScreenCapturerAndroid, org.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Starting capture: ", i, ReactProperties.HereMapMarker.X, i2, "@");
        outline110.append(i3);
        rtcscLogger.i(outline110.toString());
        synchronized (this.lock) {
            this.width = i;
            this.height = i2;
            this.framerate = i3;
            this.continuousCapturerInterval = 1000 / i3;
            this.isCapturerStopped = false;
            this.isStopPermanent = false;
        }
        this.rtcHandler.removeCallbacks(this.restartRunnable);
        super.startCapture(i, i2, i3);
    }

    @Override // org.webrtc.ScreenCapturerAndroid, org.webrtc.VideoCapturer
    public void stopCapture() {
        mLog.i("Stopping capture");
        synchronized (this.lock) {
            this.isCapturerStopped = true;
            this.isStopPermanent = true;
            this.surfaceTextureThreadHandler.removeCallbacks(this.capturerRunnable);
        }
        super.stopCapture();
    }
}
