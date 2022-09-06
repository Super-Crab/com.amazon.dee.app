package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.webrtc.Camera1Session;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoFrame;
/* loaded from: classes5.dex */
class Camera1Session implements CameraSession {
    private static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    private static final String TAG = "Camera1Session";
    private final Context applicationContext;
    private Camera1Attributes cam1Attr;
    private final Camera camera;
    private final int cameraId;
    private final Handler cameraThreadHandler;
    private final CameraEnumerationAndroid.CaptureFormat captureFormat;
    private final boolean captureToTexture;
    private final long constructionTimeNs;
    private final CameraSession.Events events;
    private final Camera.CameraInfo info;
    private final Size pictureSize;
    private SessionState state;
    private String statusParam;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private String videoEffectStatus;
    private VideoEffectStatusListener videoEffectStatusListener;
    private final boolean videoFrameEmitTrialEnabled;
    private static final Histogram camera1StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StartTimeMs", 1, 10000, 50);
    private static final Histogram camera1StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera1.StopTimeMs", 1, 10000, 50);
    private static final Histogram camera1ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera1.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private float transitionTime = 0.0f;
    private long transitionStartTimeNs = 0;
    private boolean enableVideoEffect = false;
    private boolean firstFrameReported = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.webrtc.Camera1Session$5  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass5 implements Camera.PreviewCallback {
        AnonymousClass5() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$null$0$Camera1Session$5(byte[] bArr) {
            if (Camera1Session.this.state == SessionState.RUNNING) {
                Camera1Session.this.camera.addCallbackBuffer(bArr);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onPreviewFrame$1$Camera1Session$5(final byte[] bArr) {
            Camera1Session.this.cameraThreadHandler.post(new Runnable(this, bArr) { // from class: org.webrtc.Camera1Session$5$$Lambda$1
                private final Camera1Session.AnonymousClass5 arg$1;
                private final byte[] arg$2;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.arg$1 = this;
                    this.arg$2 = bArr;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.arg$1.lambda$null$0$Camera1Session$5(this.arg$2);
                }
            });
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(final byte[] bArr, Camera camera) {
            Camera1Session.this.checkIsOnCameraThread();
            if (camera == Camera1Session.this.camera) {
                if (Camera1Session.this.state != SessionState.RUNNING) {
                    Logging.d(Camera1Session.TAG, "Bytebuffer frame captured but camera is no longer running.");
                    return;
                }
                long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                if (!Camera1Session.this.firstFrameReported) {
                    Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                    Camera1Session.this.firstFrameReported = true;
                }
                if (!Camera1Session.this.videoFrameEmitTrialEnabled) {
                    CameraSession.Events events = Camera1Session.this.events;
                    Camera1Session camera1Session = Camera1Session.this;
                    events.onByteBufferFrameCaptured(camera1Session, bArr, camera1Session.captureFormat.width, Camera1Session.this.captureFormat.height, Camera1Session.this.getFrameOrientation(), nanos);
                    Camera1Session.this.camera.addCallbackBuffer(bArr);
                    return;
                }
                VideoFrame videoFrame = new VideoFrame(new NV21Buffer(bArr, Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, new Runnable(this, bArr) { // from class: org.webrtc.Camera1Session$5$$Lambda$0
                    private final Camera1Session.AnonymousClass5 arg$1;
                    private final byte[] arg$2;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.arg$1 = this;
                        this.arg$2 = bArr;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        this.arg$1.lambda$onPreviewFrame$1$Camera1Session$5(this.arg$2);
                    }
                }), Camera1Session.this.getFrameOrientation(), nanos);
                Camera1Session.this.events.onFrameCaptured(Camera1Session.this, videoFrame);
                videoFrame.release();
                return;
            }
            Logging.e(Camera1Session.TAG, "Callback from a different camera. This should never happen.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum SessionState {
        RUNNING,
        STOPPED
    }

    private Camera1Session(CameraSession.Events events, boolean z, Context context, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, int i, Camera camera, Camera.CameraInfo cameraInfo, CameraEnumerationAndroid.CaptureFormat captureFormat, Size size, long j) {
        Logging.d(TAG, "Create new camera1 session on camera " + i);
        this.videoFrameEmitTrialEnabled = PeerConnectionFactory.fieldTrialsFindFullName(PeerConnectionFactory.VIDEO_FRAME_EMIT_TRIAL).equals("Enabled");
        this.cameraThreadHandler = new Handler();
        this.events = events;
        this.captureToTexture = z;
        this.applicationContext = context;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.cameraId = i;
        this.camera = camera;
        this.info = cameraInfo;
        this.captureFormat = captureFormat;
        this.pictureSize = size;
        this.constructionTimeNs = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            return;
        }
        throw new IllegalStateException("Wrong thread");
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, boolean z, Context context, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, int i, int i2, int i3, int i4) {
        long nanoTime = System.nanoTime();
        Logging.d(TAG, "Open camera " + i);
        events.onCameraOpening();
        try {
            Camera open = Camera.open(i);
            try {
                open.setPreviewTexture(surfaceTextureHelper.getSurfaceTexture());
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                Camera.Parameters parameters = open.getParameters();
                CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat = findClosestCaptureFormat(parameters, i2, i3, i4);
                Size findClosestPictureSize = findClosestPictureSize(parameters, i2, i3);
                updateCameraParameters(open, parameters, findClosestCaptureFormat, findClosestPictureSize, z);
                if (!z) {
                    int frameSize = findClosestCaptureFormat.frameSize();
                    for (int i5 = 0; i5 < 3; i5++) {
                        open.addCallbackBuffer(ByteBuffer.allocateDirect(frameSize).array());
                    }
                }
                open.setDisplayOrientation(0);
                Camera1Session camera1Session = new Camera1Session(events, z, context, surfaceTextureHelper, mediaRecorder, i, open, cameraInfo, findClosestCaptureFormat, findClosestPictureSize, nanoTime);
                events.onCameraOpened(camera1Session, new Camera1Information(open.getParameters()));
                camera1Session.startCapturing();
                camera1Session.setCameraRecorder(mediaRecorder);
                if (camera1Session.state == SessionState.RUNNING) {
                    createSessionCallback.onDone(camera1Session);
                } else {
                    createSessionCallback.onFailure(CameraSession.FailureType.ERROR, "camera preview failed to start");
                }
            } catch (IOException e) {
                open.release();
                createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e.getMessage());
            }
        } catch (RuntimeException e2) {
            createSessionCallback.onFailure(CameraSession.FailureType.ERROR, e2.getMessage());
        }
    }

    private static CameraEnumerationAndroid.CaptureFormat findClosestCaptureFormat(Camera.Parameters parameters, int i, int i2, int i3) {
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates = Camera1Enumerator.convertFramerates(parameters.getSupportedPreviewFpsRange());
        Logging.d(TAG, "Available fps ranges: " + convertFramerates);
        CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(convertFramerates, i3);
        Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPreviewSizes()), i, i2);
        CameraEnumerationAndroid.reportCameraResolution(camera1ResolutionHistogram, closestSupportedSize);
        return new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
    }

    private static Size findClosestPictureSize(Camera.Parameters parameters, int i, int i2) {
        return CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.convertSizes(parameters.getSupportedPictureSizes()), i, i2);
    }

    private int getDeviceOrientation() {
        int rotation = ((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation != 1) {
            if (rotation == 2) {
                return 180;
            }
            return rotation != 3 ? 0 : 270;
        }
        return 90;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFrameOrientation() {
        int deviceOrientation = getDeviceOrientation();
        if (this.info.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (this.info.orientation + deviceOrientation) % 360;
    }

    private void listenForBytebufferFrames() {
        this.camera.setPreviewCallbackWithBuffer(new AnonymousClass5());
    }

    private void listenForTextureFrames() {
        this.surfaceTextureHelper.startListening(new SurfaceTextureHelper.OnTextureFrameAvailableListener() { // from class: org.webrtc.Camera1Session.4
            @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
            public void onTextureFrameAvailable(int i, float[] fArr, long j) {
                VideoFrame.TextureBuffer createTextureBuffer;
                Camera1Session.this.checkIsOnCameraThread();
                if (Camera1Session.this.state == SessionState.RUNNING) {
                    if (!Camera1Session.this.firstFrameReported) {
                        Camera1Session.camera1StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera1Session.this.constructionTimeNs));
                        Camera1Session.this.firstFrameReported = true;
                    }
                    int frameOrientation = Camera1Session.this.getFrameOrientation();
                    float[] multiplyMatrices = Camera1Session.this.info.facing == 1 ? RendererCommon.multiplyMatrices(fArr, RendererCommon.horizontalFlipMatrix()) : fArr;
                    if (Camera1Session.this.videoFrameEmitTrialEnabled) {
                        if (Camera1Session.this.enableVideoEffect) {
                            int videoEffectRadius = Camera1Session.this.cam1Attr.getVideoEffectRadius(Camera1Session.this.transitionStartTimeNs);
                            if (videoEffectRadius == 0) {
                                Camera1Session.this.updateVideoEffectStatus();
                                Camera1Session.this.enableVideoEffect = false;
                                Camera1Session.this.transitionStartTimeNs = 0L;
                                Logging.d(Camera1Session.TAG, "Video effect ended.");
                            }
                            createTextureBuffer = Camera1Session.this.surfaceTextureHelper.createProcessedTextureBuffer(Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(multiplyMatrices), videoEffectRadius);
                        } else {
                            createTextureBuffer = Camera1Session.this.surfaceTextureHelper.createTextureBuffer(Camera1Session.this.captureFormat.width, Camera1Session.this.captureFormat.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(multiplyMatrices));
                        }
                        VideoFrame videoFrame = new VideoFrame(createTextureBuffer, frameOrientation, j);
                        Camera1Session.this.events.onFrameCaptured(Camera1Session.this, videoFrame);
                        videoFrame.release();
                        return;
                    } else if (!Camera1Session.this.enableVideoEffect) {
                        CameraSession.Events events = Camera1Session.this.events;
                        Camera1Session camera1Session = Camera1Session.this;
                        events.onTextureFrameCaptured(camera1Session, camera1Session.captureFormat.width, Camera1Session.this.captureFormat.height, i, multiplyMatrices, frameOrientation, j);
                        return;
                    } else {
                        throw new UnsupportedOperationException("Video effect for onTextureFrameCaptured not implemented. Need enable VideoFrameEmit.");
                    }
                }
                Logging.d(Camera1Session.TAG, "Texture frame captured but camera is no longer running.");
                Camera1Session.this.surfaceTextureHelper.returnTextureFrame();
            }
        });
    }

    private void setCameraRecorder(MediaRecorder mediaRecorder) {
        if (mediaRecorder != null) {
            Logging.d(TAG, "Media recoder being attached");
            this.camera.unlock();
            mediaRecorder.setCamera(this.camera);
        }
    }

    private void startCapturing() {
        Logging.d(TAG, "Start capturing");
        checkIsOnCameraThread();
        this.state = SessionState.RUNNING;
        this.camera.setErrorCallback(new Camera.ErrorCallback() { // from class: org.webrtc.Camera1Session.2
            @Override // android.hardware.Camera.ErrorCallback
            public void onError(int i, Camera camera) {
                String outline49 = i == 100 ? "Camera server died!" : GeneratedOutlineSupport1.outline49("Camera error: ", i);
                Logging.e(Camera1Session.TAG, outline49);
                Camera1Session.this.stopInternal();
                if (i == 2) {
                    Camera1Session.this.events.onCameraDisconnected(Camera1Session.this);
                } else {
                    Camera1Session.this.events.onCameraError(Camera1Session.this, outline49);
                }
            }
        });
        if (this.captureToTexture) {
            listenForTextureFrames();
        } else {
            listenForBytebufferFrames();
        }
        this.camera.setFaceDetectionListener(new Camera.FaceDetectionListener() { // from class: org.webrtc.Camera1Session.3
            @Override // android.hardware.Camera.FaceDetectionListener
            public void onFaceDetection(Camera.Face[] faceArr, Camera camera) {
                Camera1Session.this.checkIsOnCameraThread();
                if (Camera1Session.this.state == SessionState.STOPPED) {
                    Logging.d(Camera1Session.TAG, "Ignoring onFaceDetection callback as session already stopped");
                } else if (faceArr.length == 0) {
                    if (Camera1Session.this.videoEffectStatusListener == null) {
                        return;
                    }
                    Logging.d(Camera1Session.TAG, "Calling video effect status listener");
                    Camera1Session.this.videoEffectStatusListener.onStatusChanged(camera.getParameters().get(Camera1Session.this.statusParam));
                } else {
                    Logging.d(Camera1Session.TAG, "Video effect callback received, no listener registered ignoring");
                }
            }
        });
        try {
            this.camera.startPreview();
            Logging.d(TAG, "Start capturing succeeded");
        } catch (RuntimeException e) {
            stopInternal();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startCapturing failed. startPreview error: ");
            outline107.append(e.getMessage());
            Logging.w(TAG, outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        SessionState sessionState = this.state;
        SessionState sessionState2 = SessionState.STOPPED;
        if (sessionState == sessionState2) {
            Logging.d(TAG, "Camera is already stopped");
            return;
        }
        this.state = sessionState2;
        this.surfaceTextureHelper.stopListening();
        this.camera.stopPreview();
        this.camera.release();
        this.events.onCameraClosed(this);
        Logging.d(TAG, "Stop done");
    }

    private static void updateCameraParameters(Camera camera, Camera.Parameters parameters, CameraEnumerationAndroid.CaptureFormat captureFormat, Size size, boolean z) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        CameraEnumerationAndroid.CaptureFormat.FramerateRange framerateRange = captureFormat.framerate;
        parameters.setPreviewFpsRange(framerateRange.min, framerateRange.max);
        parameters.setPreviewSize(captureFormat.width, captureFormat.height);
        parameters.setPictureSize(size.width, size.height);
        if (!z) {
            parameters.setPreviewFormat(17);
        }
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        camera.setParameters(parameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoEffectStatus() {
        if (this.videoEffectStatusListener != null) {
            this.videoEffectStatus = this.cam1Attr.getCurrentVideoEffectStatus();
            this.videoEffectStatusListener.onStatusChanged(this.videoEffectStatus);
            Logging.d(TAG, "videoEffectStatus = " + this.videoEffectStatus);
            return;
        }
        Logging.d(TAG, "Video effect updated. However, no VideoEffectStatusListener is listening.");
    }

    @Override // org.webrtc.CameraSession
    public void getCameraMetadata(final CameraMetadataObserver cameraMetadataObserver, Handler handler) {
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) {
            Logging.d(TAG, "Ignoring getCameraMetadata callback as session already stopped");
        } else if (cameraMetadataObserver != null && handler != null) {
            final String str = this.camera.getParameters().get("amazon-vq-metadata");
            if (str != null) {
                handler.post(new Runnable() { // from class: org.webrtc.Camera1Session.1
                    @Override // java.lang.Runnable
                    public void run() {
                        cameraMetadataObserver.onCameraQualityMetrics(str);
                    }
                });
            } else {
                Logging.d(TAG, "No data returned for key amazon-vq-metadata");
            }
        } else {
            Logging.w(TAG, "observer == null || handler == null");
        }
    }

    @Override // org.webrtc.CameraSession
    public void setAttributes(CameraAttributes cameraAttributes) {
        Logging.d(TAG, "Set parameters to camera");
        checkIsOnCameraThread();
        if (this.state == SessionState.STOPPED) {
            Logging.d(TAG, "Ignore parameters because camera is released");
            return;
        }
        Camera.Parameters parameters = this.camera.getParameters();
        Camera.Size previewSize = parameters.getPreviewSize();
        Camera.Size pictureSize = parameters.getPictureSize();
        this.cam1Attr = (Camera1Attributes) cameraAttributes;
        parameters.unflatten(this.cam1Attr.getCameraParametersString());
        Camera.Size previewSize2 = parameters.getPreviewSize();
        Camera.Size pictureSize2 = parameters.getPictureSize();
        if (previewSize2 != null && (previewSize == null || !previewSize.equals(previewSize2))) {
            Logging.w(TAG, "setAttributes - Changing previewSize is not supported");
            if (previewSize != null) {
                parameters.setPreviewSize(previewSize.width, previewSize.height);
            } else {
                CameraEnumerationAndroid.CaptureFormat captureFormat = this.captureFormat;
                parameters.setPreviewSize(captureFormat.width, captureFormat.height);
            }
        }
        if (pictureSize2 != null && (pictureSize == null || !pictureSize.equals(pictureSize2))) {
            Logging.w(TAG, "setAttributes - Changing pictureSize is not supported");
            if (pictureSize != null) {
                parameters.setPictureSize(pictureSize.width, pictureSize.height);
            } else {
                Size size = this.pictureSize;
                parameters.setPictureSize(size.width, size.height);
            }
        }
        try {
            this.camera.setParameters(parameters);
        } catch (RuntimeException e) {
            Logging.e(TAG, CameraSession.ERROR_SET_ATTRIBUTES_FAILED, e);
            this.events.onCameraError(this, CameraSession.ERROR_SET_ATTRIBUTES_FAILED);
        }
        this.enableVideoEffect = this.cam1Attr.getEnableVideoEffect();
        this.transitionTime = this.cam1Attr.getTransitionTime();
        if (this.transitionTime > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && this.transitionStartTimeNs == 0) {
            this.transitionStartTimeNs = System.nanoTime();
        }
        if (!this.cam1Attr.getEnableGpuPath()) {
            return;
        }
        updateVideoEffectStatus();
    }

    @Override // org.webrtc.CameraSession
    public void setVideoEffectStatusListener(String str, VideoEffectStatusListener videoEffectStatusListener) {
        checkIsOnCameraThread();
        this.statusParam = str;
        this.videoEffectStatusListener = videoEffectStatusListener;
        if (this.cam1Attr.getEnableGpuPath()) {
            updateVideoEffectStatus();
        }
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stop camera1 session on camera ");
        outline107.append(this.cameraId);
        Logging.d(TAG, outline107.toString());
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long nanoTime = System.nanoTime();
            stopInternal();
            camera1StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        }
    }
}
