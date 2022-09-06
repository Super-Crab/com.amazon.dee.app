package org.webrtc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Range;
import android.view.Surface;
import android.view.WindowManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraSession;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoFrame;
/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(21)
/* loaded from: classes5.dex */
public class Camera2Session implements CameraSession {
    private static final String TAG = "Camera2Session";
    private final Context applicationContext;
    private final CameraSession.CreateSessionCallback callback;
    private Camera2Attributes camera2Attributes;
    private CameraCharacteristics cameraCharacteristics;
    private CameraDevice cameraDevice;
    private final String cameraId;
    private final CameraManager cameraManager;
    private int cameraOrientation;
    private final Handler cameraThreadHandler;
    private CameraEnumerationAndroid.CaptureFormat captureFormat;
    private CameraCaptureSession captureSession;
    private final long constructionTimeNs;
    private RectF currentROI;
    private String currentVideoEffectStatus;
    private boolean enableVideoEffect;
    private final CameraSession.Events events;
    private Surface followMeSurface;
    private int fpsUnitFactor;
    private FollowMeCameraSessionFrameUpdater frameUpdater;
    private final int framerate;
    private final int height;
    private boolean isCameraFrontFacing;
    private final Surface mediaRecorderSurface;
    private String statusParam;
    private Surface surface;
    private final SurfaceTextureHelper surfaceTextureHelper;
    private float transitionTime;
    private VideoEffectStatusListener videoEffectStatusListener;
    private final boolean videoFrameEmitTrialEnabled;
    private final int width;
    private static final Histogram camera2StartTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StartTimeMs", 1, 10000, 50);
    private static final Histogram camera2StopTimeMsHistogram = Histogram.createCounts("WebRTC.Android.Camera2.StopTimeMs", 1, 10000, 50);
    private static final Histogram camera2ResolutionHistogram = Histogram.createEnumeration("WebRTC.Android.Camera2.Resolution", CameraEnumerationAndroid.COMMON_RESOLUTIONS.size());
    private long transitionStartTimeNs = 0;
    private SessionState state = SessionState.RUNNING;
    private boolean firstFrameReported = false;

    /* loaded from: classes5.dex */
    private class CameraCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private CameraCaptureCallback() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            Logging.d(Camera2Session.TAG, "Capture failed: " + captureFailure);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class CameraStateCallback extends CameraDevice.StateCallback {
        private CameraStateCallback() {
        }

        private String getErrorDescription(int i) {
            return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? GeneratedOutlineSupport1.outline49("Unknown camera error: ", i) : "Camera service has encountered a fatal error." : "Camera device has encountered a fatal error." : "Camera device could not be opened due to a device policy." : "Camera device could not be opened because there are too many other open camera devices." : "Camera device is in use already.";
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera device closed.");
            Camera2Session.this.events.onCameraClosed(Camera2Session.this);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            boolean z = Camera2Session.this.captureSession == null && Camera2Session.this.state != SessionState.STOPPED;
            Camera2Session.this.state = SessionState.STOPPED;
            Camera2Session.this.stopInternal();
            if (z) {
                Camera2Session.this.callback.onFailure(CameraSession.FailureType.DISCONNECTED, "Camera disconnected / evicted.");
            } else {
                Camera2Session.this.events.onCameraDisconnected(Camera2Session.this);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            Camera2Session.this.checkIsOnCameraThread();
            Camera2Session.this.reportError(getErrorDescription(i));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera opened.");
            Camera2Session.this.cameraDevice = cameraDevice;
            SurfaceTexture surfaceTexture = Camera2Session.this.surfaceTextureHelper.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height);
            Camera2Session.this.surface = new Surface(surfaceTexture);
            ArrayList arrayList = new ArrayList();
            arrayList.add(Camera2Session.this.surface);
            if (Camera2Session.this.followMeSurface != null) {
                arrayList.add(Camera2Session.this.followMeSurface);
            }
            if (Camera2Session.this.mediaRecorderSurface != null) {
                Logging.d(Camera2Session.TAG, "Add MediaRecorder surface to capture session.");
                arrayList.add(Camera2Session.this.mediaRecorderSurface);
            }
            try {
                cameraDevice.createCaptureSession(arrayList, new CaptureSessionCallback(), Camera2Session.this.cameraThreadHandler);
            } catch (CameraAccessException e) {
                Camera2Session camera2Session = Camera2Session.this;
                camera2Session.reportError("Failed to create capture session. " + e);
            }
        }
    }

    /* loaded from: classes5.dex */
    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        private CaptureSessionCallback() {
        }

        private void chooseFocusMode(CaptureRequest.Builder builder) {
            int[] iArr = (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (iArr != null) {
                for (int i : iArr) {
                    if (i == 3) {
                        builder.set(CaptureRequest.CONTROL_AF_MODE, 3);
                        Logging.d(Camera2Session.TAG, "Using continuous video auto-focus.");
                        return;
                    }
                }
            }
            Logging.d(Camera2Session.TAG, "Auto-focus is not available.");
        }

        private void chooseStabilizationMode(CaptureRequest.Builder builder) {
            int[] iArr = (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
            if (iArr != null) {
                for (int i : iArr) {
                    if (i == 1) {
                        builder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 1);
                        builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
                        Logging.d(Camera2Session.TAG, "Using optical stabilization.");
                        return;
                    }
                }
            }
            int[] iArr2 = (int[]) Camera2Session.this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
            if (iArr2 != null) {
                for (int i2 : iArr2) {
                    if (i2 == 1) {
                        builder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
                        builder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 0);
                        Logging.d(Camera2Session.TAG, "Using video stabilization.");
                        return;
                    }
                }
            }
            Logging.d(Camera2Session.TAG, "Stabilization not available.");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
            Camera2Session.this.checkIsOnCameraThread();
            cameraCaptureSession.close();
            Camera2Session.this.reportError("Failed to configure capture session.");
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession cameraCaptureSession) {
            Camera2Session.this.checkIsOnCameraThread();
            Logging.d(Camera2Session.TAG, "Camera capture session configured.");
            Camera2Session.this.captureSession = cameraCaptureSession;
            try {
                CaptureRequest.Builder createCaptureRequest = Camera2Session.this.cameraDevice.createCaptureRequest(3);
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(Camera2Session.this.captureFormat.framerate.min / Camera2Session.this.fpsUnitFactor), Integer.valueOf(Camera2Session.this.captureFormat.framerate.max / Camera2Session.this.fpsUnitFactor)));
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_LOCK, false);
                chooseStabilizationMode(createCaptureRequest);
                chooseFocusMode(createCaptureRequest);
                Camera2Session.this.processCustomCaptureRequests(createCaptureRequest);
                createCaptureRequest.addTarget(Camera2Session.this.surface);
                if (Camera2Session.this.followMeSurface != null) {
                    createCaptureRequest.addTarget(Camera2Session.this.followMeSurface);
                }
                if (Camera2Session.this.mediaRecorderSurface != null) {
                    Logging.d(Camera2Session.TAG, "Add MediaRecorder surface to CaptureRequest.Builder");
                    createCaptureRequest.addTarget(Camera2Session.this.mediaRecorderSurface);
                }
                cameraCaptureSession.setRepeatingRequest(createCaptureRequest.build(), new CameraCaptureCallback(), Camera2Session.this.cameraThreadHandler);
                Camera2Session.this.surfaceTextureHelper.startListening(new SurfaceTextureHelper.OnTextureFrameAvailableListener() { // from class: org.webrtc.Camera2Session.CaptureSessionCallback.1
                    @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
                    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
                        VideoFrame.TextureBuffer createTextureBuffer;
                        Camera2Session.this.checkIsOnCameraThread();
                        if (Camera2Session.this.state == SessionState.RUNNING) {
                            if (!Camera2Session.this.firstFrameReported) {
                                Camera2Session.this.firstFrameReported = true;
                                Camera2Session.camera2StartTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Camera2Session.this.constructionTimeNs));
                            }
                            int frameOrientation = Camera2Session.this.getFrameOrientation();
                            float[] rotateTextureMatrix = RendererCommon.rotateTextureMatrix(Camera2Session.this.isCameraFrontFacing ? RendererCommon.multiplyMatrices(fArr, RendererCommon.horizontalFlipMatrix()) : fArr, -Camera2Session.this.cameraOrientation);
                            if (Camera2Session.this.followMeSurface != null && Camera2Session.this.frameUpdater != null && Camera2Session.this.currentROI != null) {
                                Camera2Session.this.frameUpdater.onCameraFrameUpdateROI(Camera2Session.this.currentROI);
                                Matrix matrix = new Matrix();
                                matrix.postScale((Camera2Session.this.currentROI.right - Camera2Session.this.currentROI.left) / Camera2Session.this.captureFormat.width, (Camera2Session.this.currentROI.bottom - Camera2Session.this.currentROI.top) / Camera2Session.this.captureFormat.height);
                                matrix.postTranslate(Camera2Session.this.currentROI.left / Camera2Session.this.captureFormat.width, Camera2Session.this.currentROI.top / Camera2Session.this.captureFormat.height);
                                rotateTextureMatrix = RendererCommon.multiplyMatrices(RendererCommon.convertMatrixFromAndroidGraphicsMatrix(matrix), rotateTextureMatrix);
                            }
                            float[] fArr2 = rotateTextureMatrix;
                            if (Camera2Session.this.videoFrameEmitTrialEnabled) {
                                if (Camera2Session.this.enableVideoEffect) {
                                    int videoEffectRadius = Camera2Session.this.camera2Attributes.getVideoEffectRadius(Camera2Session.this.transitionStartTimeNs);
                                    if (videoEffectRadius == 0) {
                                        Camera2Session.this.updateVideoEffectStatus();
                                        Camera2Session.this.enableVideoEffect = false;
                                        Camera2Session.this.transitionStartTimeNs = 0L;
                                        Logging.d(Camera2Session.TAG, "Video effect ended.");
                                    }
                                    createTextureBuffer = Camera2Session.this.surfaceTextureHelper.createProcessedTextureBuffer(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr2), videoEffectRadius);
                                } else {
                                    createTextureBuffer = Camera2Session.this.surfaceTextureHelper.createTextureBuffer(Camera2Session.this.captureFormat.width, Camera2Session.this.captureFormat.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr2));
                                }
                                VideoFrame videoFrame = new VideoFrame(createTextureBuffer, frameOrientation, j);
                                Camera2Session.this.events.onFrameCaptured(Camera2Session.this, videoFrame);
                                videoFrame.release();
                                return;
                            } else if (!Camera2Session.this.enableVideoEffect) {
                                CameraSession.Events events = Camera2Session.this.events;
                                Camera2Session camera2Session = Camera2Session.this;
                                events.onTextureFrameCaptured(camera2Session, camera2Session.captureFormat.width, Camera2Session.this.captureFormat.height, i, fArr2, frameOrientation, j);
                                return;
                            } else {
                                throw new UnsupportedOperationException("Video effect for onTextureFrameCaptured not implemented. Need enable VideoFrameEmit.");
                            }
                        }
                        Logging.d(Camera2Session.TAG, "Texture frame captured but camera is no longer running.");
                        Camera2Session.this.surfaceTextureHelper.returnTextureFrame();
                    }
                });
                Logging.d(Camera2Session.TAG, "Camera device successfully started.");
                Camera2Session.this.callback.onDone(Camera2Session.this);
            } catch (CameraAccessException e) {
                Camera2Session camera2Session = Camera2Session.this;
                camera2Session.reportError("Failed to start capture request. " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum SessionState {
        RUNNING,
        STOPPED
    }

    private Camera2Session(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, CameraManager cameraManager, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, String str, int i, int i2, int i3) {
        Logging.d(TAG, "Create new camera2 session on camera " + str);
        this.videoFrameEmitTrialEnabled = PeerConnectionFactory.fieldTrialsFindFullName(PeerConnectionFactory.VIDEO_FRAME_EMIT_TRIAL).equals("Enabled");
        this.constructionTimeNs = System.nanoTime();
        this.cameraThreadHandler = new Handler();
        this.callback = createSessionCallback;
        this.events = events;
        this.applicationContext = context;
        this.cameraManager = cameraManager;
        this.surfaceTextureHelper = surfaceTextureHelper;
        this.mediaRecorderSurface = mediaRecorder != null ? mediaRecorder.getSurface() : null;
        this.cameraId = str;
        this.width = i;
        this.height = i2;
        this.framerate = i3;
        this.camera2Attributes = new Camera2Attributes();
        this.followMeSurface = null;
        this.frameUpdater = null;
        start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsOnCameraThread() {
        if (Thread.currentThread() == this.cameraThreadHandler.getLooper().getThread()) {
            return;
        }
        throw new IllegalStateException("Wrong thread");
    }

    public static void create(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, CameraManager cameraManager, SurfaceTextureHelper surfaceTextureHelper, MediaRecorder mediaRecorder, String str, int i, int i2, int i3) {
        new Camera2Session(createSessionCallback, events, context, cameraManager, surfaceTextureHelper, mediaRecorder, str, i, i2, i3);
    }

    private boolean findCaptureFormat() {
        checkIsOnCameraThread();
        Range[] rangeArr = (Range[]) this.cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        this.fpsUnitFactor = Camera2Enumerator.getFpsUnitFactor(rangeArr);
        List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates = Camera2Enumerator.convertFramerates(rangeArr, this.fpsUnitFactor);
        List<Size> supportedSizes = Camera2Enumerator.getSupportedSizes(this.cameraCharacteristics);
        Logging.d(TAG, "Available preview sizes: " + supportedSizes);
        Logging.d(TAG, "Available fps ranges: " + convertFramerates);
        if (!convertFramerates.isEmpty() && !supportedSizes.isEmpty()) {
            CameraEnumerationAndroid.CaptureFormat.FramerateRange closestSupportedFramerateRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(convertFramerates, this.framerate);
            Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(supportedSizes, this.width, this.height);
            CameraEnumerationAndroid.reportCameraResolution(camera2ResolutionHistogram, closestSupportedSize);
            this.captureFormat = new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, closestSupportedFramerateRange);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Using capture format: ");
            outline107.append(this.captureFormat);
            Logging.d(TAG, outline107.toString());
            return true;
        }
        reportError("No supported capture formats.");
        return false;
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
        if (!this.isCameraFrontFacing) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (this.cameraOrientation + deviceOrientation) % 360;
    }

    private void openCamera() {
        checkIsOnCameraThread();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Opening camera ");
        outline107.append(this.cameraId);
        Logging.d(TAG, outline107.toString());
        this.events.onCameraOpening();
        try {
            this.cameraManager.openCamera(this.cameraId, new CameraStateCallback(), this.cameraThreadHandler);
            Camera2Information camera2Information = new Camera2Information(this.cameraCharacteristics.getAvailableCaptureRequestKeys());
            camera2Information.setCaptureHeight(this.captureFormat.height);
            camera2Information.setCaptureWidth(this.captureFormat.width);
            this.events.onCameraOpened(this, camera2Information);
        } catch (CameraAccessException e) {
            reportError("Failed to open camera: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCustomCaptureRequests(CaptureRequest.Builder builder) {
        HashMap<String, Map.Entry<CaptureRequest.Key<Object>, Object>> customCaptureRequestKeys = this.camera2Attributes.getCustomCaptureRequestKeys();
        if (customCaptureRequestKeys == null || customCaptureRequestKeys.size() == 0) {
            return;
        }
        for (CaptureRequest.Key<?> key : this.cameraCharacteristics.getAvailableCaptureRequestKeys()) {
            String name = key.getName();
            if (name.startsWith("com.") && customCaptureRequestKeys.containsKey(name)) {
                Map.Entry<CaptureRequest.Key<Object>, Object> entry = customCaptureRequestKeys.get(name);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Set CaptureRequest field: ");
                outline107.append(entry.getKey().getName());
                outline107.append(" to value: ");
                outline107.append(entry.getValue().toString());
                Logging.d(TAG, outline107.toString());
                builder.set(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportError(String str) {
        checkIsOnCameraThread();
        Logging.e(TAG, "Error: " + str);
        boolean z = this.captureSession == null && this.state != SessionState.STOPPED;
        this.state = SessionState.STOPPED;
        stopInternal();
        if (z) {
            this.callback.onFailure(CameraSession.FailureType.ERROR, str);
        } else {
            this.events.onCameraError(this, str);
        }
    }

    private void start() {
        checkIsOnCameraThread();
        Logging.d(TAG, "start");
        try {
            this.cameraCharacteristics = this.cameraManager.getCameraCharacteristics(this.cameraId);
            this.cameraOrientation = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            this.isCameraFrontFacing = ((Integer) this.cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
            if (!findCaptureFormat()) {
                return;
            }
            CameraEnumerationAndroid.CaptureFormat captureFormat = this.captureFormat;
            this.currentROI = new RectF(0.0f, 0.0f, captureFormat.width, captureFormat.height);
            openCamera();
        } catch (CameraAccessException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getCameraCharacteristics(): ");
            outline107.append(e.getMessage());
            reportError(outline107.toString());
        } catch (IllegalArgumentException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("getCameraCharacteristics(): invalid camera id: ");
            outline1072.append(this.cameraId);
            outline1072.append(" , camera yanked? error:");
            outline1072.append(e2.getMessage());
            reportError(outline1072.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternal() {
        Logging.d(TAG, "Stop internal");
        checkIsOnCameraThread();
        this.surfaceTextureHelper.stopListening();
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            this.captureSession = null;
        }
        Surface surface = this.surface;
        if (surface != null) {
            surface.release();
            this.surface = null;
        }
        CameraDevice cameraDevice = this.cameraDevice;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.cameraDevice = null;
        }
        Logging.d(TAG, "Stop done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoEffectStatus() {
        if (this.videoEffectStatusListener != null) {
            this.currentVideoEffectStatus = this.camera2Attributes.getCurrentVideoEffectStatus();
            this.videoEffectStatusListener.onStatusChanged(this.currentVideoEffectStatus);
            return;
        }
        Logging.w(TAG, "Video effect updated. However, no VideoEffectStatusListener is listening.");
    }

    @Override // org.webrtc.CameraSession
    public void getCameraMetadata(CameraMetadataObserver cameraMetadataObserver, Handler handler) {
    }

    @Override // org.webrtc.CameraSession
    public void setAttributes(CameraAttributes cameraAttributes) {
        checkIsOnCameraThread();
        this.camera2Attributes = (Camera2Attributes) cameraAttributes;
        if (this.state == SessionState.STOPPED) {
            Logging.d(TAG, "Ignore parameters because camera is released");
            return;
        }
        this.enableVideoEffect = this.camera2Attributes.getEnableVideoEffect();
        this.transitionTime = this.camera2Attributes.getTransitionTime();
        if (this.cameraDevice == null) {
            this.followMeSurface = this.camera2Attributes.getFollowMeSurface();
            this.frameUpdater = this.camera2Attributes.getFollowMeCameraSessionFrameUpdater();
        }
        if (this.transitionTime > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && this.transitionStartTimeNs == 0) {
            this.transitionStartTimeNs = System.nanoTime();
        }
        updateVideoEffectStatus();
    }

    @Override // org.webrtc.CameraSession
    public void setVideoEffectStatusListener(String str, VideoEffectStatusListener videoEffectStatusListener) {
        checkIsOnCameraThread();
        this.statusParam = str;
        this.videoEffectStatusListener = videoEffectStatusListener;
        updateVideoEffectStatus();
    }

    @Override // org.webrtc.CameraSession
    public void stop() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stop camera2 session on camera ");
        outline107.append(this.cameraId);
        Logging.d(TAG, outline107.toString());
        checkIsOnCameraThread();
        if (this.state != SessionState.STOPPED) {
            long nanoTime = System.nanoTime();
            this.state = SessionState.STOPPED;
            stopInternal();
            camera2StopTimeMsHistogram.addSample((int) TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        }
    }
}
