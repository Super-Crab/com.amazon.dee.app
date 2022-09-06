package org.webrtc;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import org.webrtc.CameraSession;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;
/* loaded from: classes5.dex */
abstract class CameraCapturer implements CameraVideoCapturer {
    private static final int DEFAULT_METRICS_FREQUENCY = 2000;
    private static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    private static final int OPEN_CAMERA_DELAY_MS = 500;
    private static final int OPEN_CAMERA_TIMEOUT = 10000;
    private static final String TAG = "CameraCapturer";
    private Context applicationContext;
    private final CameraEnumerator cameraEnumerator;
    private String cameraName;
    private CameraVideoCapturer.CameraStatistics cameraStatistics;
    private Handler cameraThreadHandler;
    private VideoCapturer.CapturerObserver capturerObserver;
    private CameraSession currentSession;
    private final CameraVideoCapturer.CameraEventsHandler eventsHandler;
    private boolean firstFrameObserved;
    private int framerate;
    private int height;
    private CameraVideoCapturer.MediaRecorderHandler mediaRecorderEventsHandler;
    private int openAttemptsRemaining;
    private String pendingCameraName;
    private boolean sessionOpening;
    private SurfaceTextureHelper surfaceHelper;
    private CameraVideoCapturer.CameraSwitchHandler switchEventsHandler;
    private final Handler uiThreadHandler;
    private int width;
    private final CameraSession.CreateSessionCallback createSessionCallback = new CameraSession.CreateSessionCallback() { // from class: org.webrtc.CameraCapturer.1
        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onDone(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Create session done. Switch state: ");
            outline107.append(CameraCapturer.this.switchState);
            outline107.append(". MediaRecorder state: ");
            outline107.append(CameraCapturer.this.mediaRecorderState);
            Logging.d(CameraCapturer.TAG, outline107.toString());
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.capturerObserver.onCapturerStarted(true);
                CameraCapturer.this.sessionOpening = false;
                CameraCapturer.this.currentSession = cameraSession;
                CameraCapturer.this.cameraStatistics = new CameraVideoCapturer.CameraStatistics(CameraCapturer.this.surfaceHelper, CameraCapturer.this.eventsHandler);
                CameraCapturer.this.firstFrameObserved = false;
                CameraCapturer.this.stateLock.notifyAll();
                if (CameraCapturer.this.switchState == SwitchState.IN_PROGRESS) {
                    if (CameraCapturer.this.switchEventsHandler != null) {
                        CameraCapturer.this.switchEventsHandler.onCameraSwitchDone(CameraCapturer.this.cameraEnumerator.isFrontFacing(CameraCapturer.this.cameraName));
                        CameraCapturer.this.switchEventsHandler = null;
                    }
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                } else if (CameraCapturer.this.switchState == SwitchState.PENDING) {
                    CameraCapturer.this.switchState = SwitchState.IDLE;
                    CameraCapturer.this.switchCameraInternal(CameraCapturer.this.pendingCameraName, CameraCapturer.this.switchEventsHandler);
                    CameraCapturer.this.pendingCameraName = null;
                }
                if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE || CameraCapturer.this.mediaRecorderState == MediaRecorderState.ACTIVE_TO_IDLE) {
                    if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                        CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderSuccess();
                        CameraCapturer.this.mediaRecorderEventsHandler = null;
                    }
                    if (CameraCapturer.this.mediaRecorderState == MediaRecorderState.IDLE_TO_ACTIVE) {
                        CameraCapturer.this.mediaRecorderState = MediaRecorderState.ACTIVE;
                    } else {
                        CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                }
            }
        }

        @Override // org.webrtc.CameraSession.CreateSessionCallback
        public void onFailure(CameraSession.FailureType failureType, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            CameraCapturer.this.uiThreadHandler.removeCallbacks(CameraCapturer.this.openCameraTimeoutRunnable);
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession != null && CameraCapturer.this.cameraStatistics == null && CameraCapturer.this.sessionOpening) {
                    CameraCapturer.this.currentSession = null;
                    Logging.d(CameraCapturer.TAG, "Opening camera failed (after onCameraOpened)");
                }
                CameraCapturer.this.capturerObserver.onCapturerStarted(false);
                CameraCapturer.access$1910(CameraCapturer.this);
                if (CameraCapturer.this.openAttemptsRemaining <= 0) {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, passing: " + str);
                    CameraCapturer.this.sessionOpening = false;
                    CameraCapturer.this.stateLock.notifyAll();
                    if (CameraCapturer.this.switchState != SwitchState.IDLE) {
                        if (CameraCapturer.this.switchEventsHandler != null) {
                            CameraCapturer.this.switchEventsHandler.onCameraSwitchError(str);
                            CameraCapturer.this.switchEventsHandler = null;
                        }
                        CameraCapturer.this.switchState = SwitchState.IDLE;
                    }
                    if (CameraCapturer.this.mediaRecorderState != MediaRecorderState.IDLE) {
                        if (CameraCapturer.this.mediaRecorderEventsHandler != null) {
                            CameraCapturer.this.mediaRecorderEventsHandler.onMediaRecorderError(str);
                            CameraCapturer.this.mediaRecorderEventsHandler = null;
                        }
                        CameraCapturer.this.mediaRecorderState = MediaRecorderState.IDLE;
                    }
                    if (failureType == CameraSession.FailureType.DISCONNECTED) {
                        CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    } else {
                        CameraCapturer.this.eventsHandler.onCameraError(str);
                    }
                } else {
                    Logging.w(CameraCapturer.TAG, "Opening camera failed, retry: " + str);
                    CameraCapturer.this.createSessionInternal(500, null);
                }
            }
        }
    };
    private final CameraSession.Events cameraSessionEventsHandler = new CameraSession.Events() { // from class: org.webrtc.CameraCapturer.2
        @Override // org.webrtc.CameraSession.Events
        public void onByteBufferFrameCaptured(CameraSession cameraSession, byte[] bArr, int i, int i2, int i3, long j) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    if (!CameraCapturer.this.firstFrameObserved) {
                        CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                        CameraCapturer.this.firstFrameObserved = true;
                    }
                    CameraCapturer.this.cameraStatistics.addFrame();
                    CameraCapturer.this.capturerObserver.onByteBufferFrameCaptured(bArr, i, i2, i3, j);
                    return;
                }
                Logging.w(CameraCapturer.TAG, "onByteBufferFrameCaptured from another session.");
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraClosed(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession || CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraClosed();
                } else {
                    Logging.d(CameraCapturer.TAG, "onCameraClosed from another session.");
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraDisconnected(CameraSession cameraSession) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    CameraCapturer.this.eventsHandler.onCameraDisconnected();
                    CameraCapturer.this.stopCapture();
                    return;
                }
                Logging.w(CameraCapturer.TAG, "onCameraDisconnected from another session.");
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraError(CameraSession cameraSession, String str) {
            CameraCapturer.this.checkIsOnCameraThread();
            Logging.w(CameraCapturer.TAG, "Encountered onCameraError: " + str);
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    CameraCapturer.this.eventsHandler.onCameraError(str);
                    if ((cameraSession instanceof Camera1Session) && str == CameraSession.ERROR_SET_ATTRIBUTES_FAILED) {
                        Logging.e(CameraCapturer.TAG, "Set attributes failed.");
                        return;
                    } else {
                        CameraCapturer.this.stopCapture();
                        return;
                    }
                }
                Logging.w(CameraCapturer.TAG, "onCameraError from another session: " + str);
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraOpened(CameraSession cameraSession, CameraInformation cameraInformation) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                CameraCapturer.this.currentSession = cameraSession;
                Logging.d(CameraCapturer.TAG, "Camera opened.");
            }
            CameraCapturer.this.eventsHandler.onCameraOpened(CameraCapturer.this, cameraInformation);
        }

        @Override // org.webrtc.CameraSession.Events
        public void onCameraOpening() {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (CameraCapturer.this.currentSession == null) {
                    CameraCapturer.this.eventsHandler.onCameraOpening(CameraCapturer.this.cameraName);
                } else {
                    Logging.w(CameraCapturer.TAG, "onCameraOpening while session was open.");
                }
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onFrameCaptured(CameraSession cameraSession, VideoFrame videoFrame) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    if (!CameraCapturer.this.firstFrameObserved) {
                        CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                        CameraCapturer.this.firstFrameObserved = true;
                    }
                    CameraCapturer.this.cameraStatistics.addFrame();
                    CameraCapturer.this.capturerObserver.onFrameCaptured(videoFrame);
                    return;
                }
                Logging.w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
            }
        }

        @Override // org.webrtc.CameraSession.Events
        public void onTextureFrameCaptured(CameraSession cameraSession, int i, int i2, int i3, float[] fArr, int i4, long j) {
            CameraCapturer.this.checkIsOnCameraThread();
            synchronized (CameraCapturer.this.stateLock) {
                if (cameraSession == CameraCapturer.this.currentSession) {
                    if (!CameraCapturer.this.firstFrameObserved) {
                        CameraCapturer.this.eventsHandler.onFirstFrameAvailable();
                        CameraCapturer.this.firstFrameObserved = true;
                    }
                    CameraCapturer.this.cameraStatistics.addFrame();
                    CameraCapturer.this.capturerObserver.onTextureFrameCaptured(i, i2, i3, fArr, i4, j);
                    return;
                }
                Logging.w(CameraCapturer.TAG, "onTextureFrameCaptured from another session.");
                CameraCapturer.this.surfaceHelper.returnTextureFrame();
            }
        }
    };
    private final Runnable openCameraTimeoutRunnable = new Runnable() { // from class: org.webrtc.CameraCapturer.3
        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.this.eventsHandler.onCameraError("Camera failed to start within timeout.");
        }
    };
    private final Object stateLock = new Object();
    private SwitchState switchState = SwitchState.IDLE;
    private MediaRecorderState mediaRecorderState = MediaRecorderState.IDLE;
    private CameraMetadataObserver cameraMetadataObserver = null;
    private Handler cameraMetadataCallbackHandler = null;
    private int metric_frequency_in_ms = 2000;
    private final Runnable cameraMetricsRunnable = new Runnable() { // from class: org.webrtc.CameraCapturer.4
        @Override // java.lang.Runnable
        public void run() {
            CameraCapturer.this.checkIsOnCameraThread();
            if (CameraCapturer.this.cameraMetadataObserver != null && CameraCapturer.this.cameraMetadataCallbackHandler != null) {
                synchronized (CameraCapturer.this.stateLock) {
                    if (CameraCapturer.this.currentSession != null) {
                        CameraCapturer.this.currentSession.getCameraMetadata(CameraCapturer.this.cameraMetadataObserver, CameraCapturer.this.cameraMetadataCallbackHandler);
                        CameraCapturer.this.cameraThreadHandler.postDelayed(this, CameraCapturer.this.metric_frequency_in_ms);
                        return;
                    }
                    Logging.w(CameraCapturer.TAG, "No session open");
                    CameraCapturer.this.cameraThreadHandler.postDelayed(this, CameraCapturer.this.metric_frequency_in_ms);
                    return;
                }
            }
            Logging.d(CameraCapturer.TAG, "null cameraMetadataObserver or cameraMetadataCallbackHandler");
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum MediaRecorderState {
        IDLE,
        IDLE_TO_ACTIVE,
        ACTIVE_TO_IDLE,
        ACTIVE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum SwitchState {
        IDLE,
        PENDING,
        IN_PROGRESS
    }

    public CameraCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, CameraEnumerator cameraEnumerator) {
        this.eventsHandler = cameraEventsHandler == null ? new CameraVideoCapturer.CameraEventsHandler() { // from class: org.webrtc.CameraCapturer.5
            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpened(CameraVideoCapturer cameraVideoCapturer, CameraInformation cameraInformation) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str2) {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
            }
        } : cameraEventsHandler;
        this.cameraEnumerator = cameraEnumerator;
        this.cameraName = str;
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        if (deviceNames.length != 0) {
            if (!Arrays.asList(deviceNames).contains(this.cameraName)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Camera name "), this.cameraName, " does not match any known camera device."));
            }
            return;
        }
        throw new RuntimeException("No cameras attached.");
    }

    static /* synthetic */ int access$1910(CameraCapturer cameraCapturer) {
        int i = cameraCapturer.openAttemptsRemaining;
        cameraCapturer.openAttemptsRemaining = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            return;
        }
        Logging.e(TAG, "Check is on camera thread failed.");
        throw new RuntimeException("Not on camera thread.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSessionInternal(int i, final MediaRecorder mediaRecorder) {
        this.uiThreadHandler.postDelayed(this.openCameraTimeoutRunnable, i + 10000);
        this.cameraThreadHandler.postDelayed(new Runnable() { // from class: org.webrtc.CameraCapturer.6
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer cameraCapturer = CameraCapturer.this;
                cameraCapturer.createCameraSession(cameraCapturer.createSessionCallback, CameraCapturer.this.cameraSessionEventsHandler, CameraCapturer.this.applicationContext, CameraCapturer.this.surfaceHelper, mediaRecorder, CameraCapturer.this.cameraName, CameraCapturer.this.width, CameraCapturer.this.height, CameraCapturer.this.framerate);
            }
        }, i);
    }

    private boolean isCameraThread() {
        return Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread();
    }

    private void reportCameraSwitchError(String str, CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.e(TAG, str);
        if (cameraSwitchHandler != null) {
            cameraSwitchHandler.onCameraSwitchError(str);
        }
    }

    private void reportUpdateMediaRecorderError(String str, CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        checkIsOnCameraThread();
        Logging.e(TAG, str);
        if (mediaRecorderHandler != null) {
            mediaRecorderHandler.onMediaRecorderError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchCameraInternal(String str, CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.d(TAG, "switchCamera internal");
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        if (deviceNames.length < 2) {
            if (cameraSwitchHandler == null) {
                return;
            }
            cameraSwitchHandler.onCameraSwitchError("No camera to switch to.");
            return;
        }
        synchronized (this.stateLock) {
            if (this.switchState != SwitchState.IDLE) {
                reportCameraSwitchError("Camera switch already in progress.", cameraSwitchHandler);
            } else if (this.mediaRecorderState != MediaRecorderState.IDLE) {
                reportCameraSwitchError("switchCamera: media recording is active", cameraSwitchHandler);
            } else if (!this.sessionOpening && this.currentSession == null) {
                reportCameraSwitchError("switchCamera: camera is not running.", cameraSwitchHandler);
            } else {
                if (str == null) {
                    str = deviceNames[(Arrays.asList(deviceNames).indexOf(this.cameraName) + 1) % deviceNames.length];
                }
                this.switchEventsHandler = cameraSwitchHandler;
                if (this.sessionOpening) {
                    this.switchState = SwitchState.PENDING;
                    this.pendingCameraName = str;
                    return;
                }
                this.switchState = SwitchState.IN_PROGRESS;
                Logging.d(TAG, "switchCamera: Stopping session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.16
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.cameraName = str;
                this.sessionOpening = true;
                this.openAttemptsRemaining = 1;
                createSessionInternal(0, null);
                Logging.d(TAG, "switchCamera done");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaRecorderInternal(MediaRecorder mediaRecorder, CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        checkIsOnCameraThread();
        boolean z = mediaRecorder != null;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateMediaRecoderInternal internal. State: ");
        outline107.append(this.mediaRecorderState);
        outline107.append(". Switch state: ");
        outline107.append(this.switchState);
        outline107.append(". Add MediaRecorder: ");
        outline107.append(z);
        Logging.d(TAG, outline107.toString());
        synchronized (this.stateLock) {
            if (z) {
                try {
                    if (this.mediaRecorderState == MediaRecorderState.IDLE) {
                    }
                    reportUpdateMediaRecorderError("Incorrect state for MediaRecorder update.", mediaRecorderHandler);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z || this.mediaRecorderState == MediaRecorderState.ACTIVE) {
                if (this.switchState != SwitchState.IDLE) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is switching.", mediaRecorderHandler);
                    return;
                } else if (this.currentSession == null) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is closed.", mediaRecorderHandler);
                    return;
                } else if (this.sessionOpening) {
                    reportUpdateMediaRecorderError("MediaRecorder update while camera is still opening.", mediaRecorderHandler);
                    return;
                } else {
                    this.mediaRecorderEventsHandler = mediaRecorderHandler;
                    this.mediaRecorderState = z ? MediaRecorderState.IDLE_TO_ACTIVE : MediaRecorderState.ACTIVE_TO_IDLE;
                    Logging.d(TAG, "updateMediaRecoder: Stopping session");
                    this.cameraStatistics.release();
                    this.cameraStatistics = null;
                    final CameraSession cameraSession = this.currentSession;
                    this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.17
                        @Override // java.lang.Runnable
                        public void run() {
                            cameraSession.stop();
                        }
                    });
                    this.currentSession = null;
                    this.sessionOpening = true;
                    this.openAttemptsRemaining = 1;
                    createSessionInternal(0, mediaRecorder);
                    Logging.d(TAG, "updateMediaRecoderInternal done");
                    return;
                }
            }
            reportUpdateMediaRecorderError("Incorrect state for MediaRecorder update.", mediaRecorderHandler);
        }
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void addMediaRecorderToCamera(final MediaRecorder mediaRecorder, final CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        Logging.d(TAG, "addMediaRecorderToCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.11
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal(mediaRecorder, mediaRecorderHandler);
            }
        });
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int i, int i2, int i3) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("changeCaptureFormat: ", i, ReactProperties.HereMapMarker.X, i2, "@");
        outline110.append(i3);
        Logging.d(TAG, outline110.toString());
        synchronized (this.stateLock) {
            stopCapture();
            startCapture(i, i2, i3);
        }
    }

    protected abstract void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, String str, int i, int i2, int i3);

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        Logging.d(TAG, "dispose");
        stopCapture();
    }

    protected String getCameraName() {
        String str;
        synchronized (this.stateLock) {
            str = this.cameraName;
        }
        return str;
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.applicationContext = context;
        this.capturerObserver = capturerObserver;
        this.surfaceHelper = surfaceTextureHelper;
        this.cameraThreadHandler = surfaceTextureHelper == null ? null : surfaceTextureHelper.getHandler();
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    public void printStackTrace() {
        Handler handler = this.cameraThreadHandler;
        Thread thread = handler != null ? handler.getLooper().getThread() : null;
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length <= 0) {
                return;
            }
            Logging.d(TAG, "CameraCapturer stack trace:");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Logging.d(TAG, stackTraceElement.toString());
            }
        }
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void removeMediaRecorderFromCamera(final CameraVideoCapturer.MediaRecorderHandler mediaRecorderHandler) {
        Logging.d(TAG, "removeMediaRecorderFromCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.12
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.updateMediaRecorderInternal(null, mediaRecorderHandler);
            }
        });
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void setCameraInformation(CameraInformation cameraInformation) {
        Logging.d(TAG, "Set camera information");
        final CameraAttributes mo13073getAttributes = cameraInformation.mo13073getAttributes();
        synchronized (this.stateLock) {
            if (this.currentSession == null) {
                Logging.w(TAG, "No session open");
                return;
            }
            final CameraSession cameraSession = this.currentSession;
            if (!isCameraThread()) {
                this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.13
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.setAttributes(mo13073getAttributes);
                    }
                });
                return;
            }
            Logging.d(TAG, "setCameraInformation on camera thread");
            cameraSession.setAttributes(mo13073getAttributes);
        }
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void setCameraMetadataObserver(final CameraMetadataObserver cameraMetadataObserver, final Handler handler, final int i) {
        Logging.d(TAG, "Setting Camera Metadata Observer, metric_frequency_in_ms = " + i);
        if (this.cameraThreadHandler == null) {
            Logging.d(TAG, "cameraThreadHandler is null");
            return;
        }
        synchronized (this.stateLock) {
            if (this.currentSession == null) {
                Logging.w(TAG, "No session open");
                return;
            }
            this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.15
                @Override // java.lang.Runnable
                public void run() {
                    int i2 = i;
                    if (i2 > 0) {
                        CameraCapturer.this.metric_frequency_in_ms = i2;
                    } else {
                        CameraCapturer.this.metric_frequency_in_ms = 2000;
                    }
                    CameraCapturer.this.cameraMetadataObserver = cameraMetadataObserver;
                    CameraCapturer.this.cameraMetadataCallbackHandler = handler;
                }
            });
            this.cameraThreadHandler.removeCallbacks(this.cameraMetricsRunnable);
            this.cameraThreadHandler.post(this.cameraMetricsRunnable);
        }
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void setVideoEffectStatusListener(final String str, final VideoEffectStatusListener videoEffectStatusListener) {
        Logging.d(TAG, "Setting Video Effect Status Listener");
        synchronized (this.stateLock) {
            if (this.currentSession == null) {
                Logging.w(TAG, "No session open");
                return;
            }
            final CameraSession cameraSession = this.currentSession;
            this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.14
                @Override // java.lang.Runnable
                public void run() {
                    cameraSession.setVideoEffectStatusListener(str, videoEffectStatusListener);
                }
            });
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3) {
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("startCapture: ", i, ReactProperties.HereMapMarker.X, i2, "@");
        outline110.append(i3);
        Logging.d(TAG, outline110.toString());
        if (this.applicationContext != null) {
            synchronized (this.stateLock) {
                if (!this.sessionOpening && this.currentSession == null) {
                    this.width = i;
                    this.height = i2;
                    this.framerate = i3;
                    this.sessionOpening = true;
                    this.openAttemptsRemaining = 3;
                    createSessionInternal(0, null);
                    return;
                }
                Logging.w(TAG, "Session already open");
                return;
            }
        }
        throw new RuntimeException("CameraCapturer must be initialized before calling startCapture.");
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        Logging.d(TAG, "Stop capture");
        this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.7
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.cameraThreadHandler.removeCallbacks(CameraCapturer.this.cameraMetricsRunnable);
                CameraCapturer.this.cameraMetadataObserver = null;
                CameraCapturer.this.cameraMetadataCallbackHandler = null;
            }
        });
        synchronized (this.stateLock) {
            if (this.sessionOpening && isCameraThread()) {
                Logging.w(TAG, "stopCapture called on camera thread whensessionOpening true! Early return");
                return;
            }
            while (this.sessionOpening) {
                Logging.d(TAG, "Stop capture: Waiting for session to open");
                ThreadUtils.waitUninterruptibly(this.stateLock);
            }
            if (this.currentSession != null) {
                Logging.d(TAG, "Stop capture: Nulling session");
                this.cameraStatistics.release();
                this.cameraStatistics = null;
                final CameraSession cameraSession = this.currentSession;
                this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.8
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraSession.stop();
                    }
                });
                this.currentSession = null;
                this.capturerObserver.onCapturerStopped();
            } else {
                Logging.d(TAG, "Stop capture: No session open");
            }
            Logging.d(TAG, "Stop capture done");
        }
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void switchCamera(final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.d(TAG, "switchCamera");
        this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.9
            @Override // java.lang.Runnable
            public void run() {
                CameraCapturer.this.switchCameraInternal(null, cameraSwitchHandler);
            }
        });
    }

    @Override // org.webrtc.CameraVideoCapturer
    public void switchCamera(final String str, final CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        Logging.d(TAG, "switchCamera: " + str);
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        if (str != null && Arrays.asList(deviceNames).contains(str)) {
            this.cameraThreadHandler.post(new Runnable() { // from class: org.webrtc.CameraCapturer.10
                @Override // java.lang.Runnable
                public void run() {
                    CameraCapturer.this.switchCameraInternal(str, cameraSwitchHandler);
                }
            });
            return;
        }
        throw new IllegalArgumentException("cameraName");
    }
}
