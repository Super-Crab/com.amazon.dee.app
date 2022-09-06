package org.webrtc;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.core.content.ContextCompat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.webrtc.PeerConnectionFactoryWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.webrtc.CameraMetadataShim;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.VideoEffectShim;
/* loaded from: classes5.dex */
public class LocalAudioVideoShim {
    private static final String AMAZON_HAL_EFFECTS = "amazon-hal-effects";
    private static final int HD_VIDEO_HEIGHT = 720;
    private static final int HD_VIDEO_WIDTH = 1280;
    private static final int MAX_VIDEO_FPS = 30;
    public static final String MAX_VIDEO_FPS_CONSTRAINT = "maxFrameRate";
    private static final int MAX_VIDEO_HEIGHT = 1280;
    public static final String MAX_VIDEO_HEIGHT_CONSTRAINT = "maxHeight";
    private static final int MAX_VIDEO_WIDTH = 1280;
    public static final String MAX_VIDEO_WIDTH_CONSTRAINT = "maxWidth";
    private static final String TAG = "LocalAudioVideoShim";
    public static final String VIDEO_CODEC_H264 = "H264";
    public static final String VIDEO_CODEC_VP8 = "VP8";
    private boolean allowCameraHalFramerateUpdate;
    private Context applicationContext;
    private AudioSource audioSource;
    private Map<String, String> cachedPlatformCameraParameters;
    private CameraEnumerator cameraEnumerator;
    private final CameraVideoCapturer.CameraEventsHandler cameraEventsHandler;
    private CameraInformation cameraInformation;
    private final Object cameraInformationStateLock;
    private CameraMetadataShim cameraMetadataShim;
    private Handler cameraMetricsHandler;
    private CameraMetadataShim.WebRTCCameraMetadataObserver cameraMetricsObserver;
    private boolean captureToTexture;
    private String currentCameraDeviceName;
    private String externalFacingCameraDeviceName;
    private FollowMeShim followMeShim;
    private boolean forceCamera2api;
    private String frontFacingCameraDeviceName;
    private final LocalAudioVideoListener localAudioVideoListener;
    private int maxVideoFpsAllowed;
    private int maxVideoHeightAllowed;
    private int maxVideoWidthAllowed;
    private int numberOfCameras;
    private int pendingNewMaxFps;
    private boolean preferCamera1api;
    private String preferredVideoCodec;
    private boolean provideCallingServiceHalParameter;
    private String rearFacingCameraDeviceName;
    private boolean usingCamera2api;
    private boolean videoCapable;
    private volatile VideoCapturer videoCapturer;
    private volatile boolean videoCapturerStopped;
    private boolean videoCodecHwAcceleration;
    private VideoEffectShim videoEffectShim;
    private int videoFps;
    private int videoHeight;
    private VideoSource videoSource;
    private int videoWidth;
    private static final String videoEffectCommandRegEx = "^\\[.*\\sTIME:(\\d+\\.\\d+).*";
    private static final Pattern videoEffectCommandPattern = Pattern.compile(videoEffectCommandRegEx);
    private static final HashMap<String, CameraErrorCode> cameraErrorCodeMap = new HashMap<>();

    /* loaded from: classes5.dex */
    public interface LocalAudioVideoListener {
        void onCameraFailure(CameraErrorCode cameraErrorCode, String str);

        void onCameraOpening(String str, boolean z);
    }

    static {
        cameraErrorCodeMap.put("Camera server died!", CameraErrorCode.CAMERA_SERVER_DIED);
    }

    private LocalAudioVideoShim() {
        this.maxVideoWidthAllowed = 1280;
        this.maxVideoHeightAllowed = 1280;
        this.maxVideoFpsAllowed = 30;
        this.videoCapturerStopped = true;
        this.videoCapable = false;
        this.videoCodecHwAcceleration = true;
        this.videoWidth = 1280;
        this.videoHeight = 720;
        this.videoFps = 30;
        this.preferredVideoCodec = "VP8";
        this.cameraInformationStateLock = new Object();
        this.cameraEventsHandler = new CameraVideoCapturer.CameraEventsHandler() { // from class: org.webrtc.LocalAudioVideoShim.1
            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
                Log.i(LocalAudioVideoShim.TAG, "Debugs: onCameraClosed");
                synchronized (LocalAudioVideoShim.this.cameraInformationStateLock) {
                    LocalAudioVideoShim.this.cameraInformation = null;
                    LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
                Log.e(LocalAudioVideoShim.TAG, "onCameraDisconnected");
                LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraFailure(CameraErrorCode.CAMERA_DISCONNECTED, "camera disconnected");
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str) {
                GeneratedOutlineSupport1.outline162("onCameraError: ", str, LocalAudioVideoShim.TAG);
                LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                CameraErrorCode cameraErrorCode = (CameraErrorCode) LocalAudioVideoShim.cameraErrorCodeMap.get(str);
                if (cameraErrorCode == null) {
                    cameraErrorCode = CameraErrorCode.UNKNOWN;
                }
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraFailure(cameraErrorCode, str);
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str) {
                GeneratedOutlineSupport1.outline162("onCameraFreezed: ", str, LocalAudioVideoShim.TAG);
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpened(CameraVideoCapturer cameraVideoCapturer, CameraInformation cameraInformation) {
                String unused = LocalAudioVideoShim.TAG;
                if (LocalAudioVideoShim.this.cameraMetricsHandler != null && LocalAudioVideoShim.this.cameraMetricsObserver != null) {
                    if (LocalAudioVideoShim.this.cameraMetadataShim == null) {
                        LocalAudioVideoShim.this.cameraMetadataShim = new CameraMetadataShim();
                    }
                    LocalAudioVideoShim.this.cameraMetadataShim.registerObserver(cameraVideoCapturer, LocalAudioVideoShim.this.cameraMetricsHandler, LocalAudioVideoShim.this.cameraMetricsObserver);
                }
                synchronized (LocalAudioVideoShim.this.cameraInformationStateLock) {
                    LocalAudioVideoShim.this.cameraInformation = cameraInformation;
                    if (LocalAudioVideoShim.this.videoEffectShim != null) {
                        LocalAudioVideoShim.this.videoEffectShim.init(cameraVideoCapturer);
                    }
                    if (LocalAudioVideoShim.this.cameraInformation instanceof Camera1Information) {
                        Camera.Parameters parameters = ((Camera1Information) LocalAudioVideoShim.this.cameraInformation).getParameters();
                        for (Map.Entry entry : LocalAudioVideoShim.this.cachedPlatformCameraParameters.entrySet()) {
                            parameters.set((String) entry.getKey(), (String) entry.getValue());
                        }
                        if (LocalAudioVideoShim.this.provideCallingServiceHalParameter) {
                            parameters.set("vc-comms", "1");
                        }
                    } else if (LocalAudioVideoShim.this.cameraInformation instanceof Camera2Information) {
                        for (Map.Entry entry2 : LocalAudioVideoShim.this.cachedPlatformCameraParameters.entrySet()) {
                            LocalAudioVideoShim.this.processCamera2Information((String) entry2.getKey(), (String) entry2.getValue(), (Camera2Information) LocalAudioVideoShim.this.cameraInformation);
                        }
                        if (LocalAudioVideoShim.this.provideCallingServiceHalParameter) {
                            for (CaptureRequest.Key<?> key : ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureRequestKeys()) {
                                if ("com.mediatek.webrtc.webrtcenable".equals(key.getName())) {
                                    ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).addCustomCaptureRequest((CaptureRequest.Key) key.getClass().cast(key), new int[]{1});
                                }
                            }
                        }
                        if (LocalAudioVideoShim.this.followMeShim != null) {
                            int captureWidth = ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureWidth();
                            int captureHeight = ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureHeight();
                            Log.i(LocalAudioVideoShim.TAG, "FMCTest:init followMeShim");
                            ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).setFollowMeSurface(LocalAudioVideoShim.this.followMeShim.init(captureWidth, captureHeight));
                            ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).setFollowMeCameraSessionFrameUpdater(LocalAudioVideoShim.this.followMeShim.getFrameUpdater());
                        }
                    } else {
                        Log.e(LocalAudioVideoShim.TAG, "The implementation of CameraInformation is not supported");
                    }
                    LocalAudioVideoShim.this.cachedPlatformCameraParameters.clear();
                    cameraVideoCapturer.setCameraInformation(LocalAudioVideoShim.this.cameraInformation);
                }
                if (LocalAudioVideoShim.this.pendingNewMaxFps > 0) {
                    LocalAudioVideoShim localAudioVideoShim = LocalAudioVideoShim.this;
                    localAudioVideoShim.updateVideoFramerateIfAllowed(localAudioVideoShim.pendingNewMaxFps);
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str) {
                String unused = LocalAudioVideoShim.TAG;
                GeneratedOutlineSupport1.outline158("onCameraOpening: ", str);
                LocalAudioVideoShim.this.setCurrentCameraDeviceName(str);
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraOpening(str, LocalAudioVideoShim.this.cameraEnumerator.isFrontFacing(str));
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
                Log.i(LocalAudioVideoShim.TAG, "onFirstFrameAvailable");
            }
        };
        this.localAudioVideoListener = null;
    }

    private void createCameraCapturer() {
        this.videoCapturerStopped = true;
        if (!hasLocalVideoCapability()) {
            Log.i(TAG, "Can't create camera capturer as no usable camera found.");
            this.videoCapturer = null;
        } else if (!TextUtils.isEmpty(this.frontFacingCameraDeviceName)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating front facing camera capturer. camera= ");
            outline107.append(this.frontFacingCameraDeviceName);
            outline107.toString();
            this.videoCapturer = this.cameraEnumerator.createCapturer(this.frontFacingCameraDeviceName, this.cameraEventsHandler);
            setCurrentCameraDeviceName(this.frontFacingCameraDeviceName);
        } else if (!TextUtils.isEmpty(this.rearFacingCameraDeviceName)) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Creating rear facing camera capturer. camera= ");
            outline1072.append(this.rearFacingCameraDeviceName);
            outline1072.toString();
            this.videoCapturer = this.cameraEnumerator.createCapturer(this.rearFacingCameraDeviceName, this.cameraEventsHandler);
            setCurrentCameraDeviceName(this.rearFacingCameraDeviceName);
        } else if (TextUtils.isEmpty(this.externalFacingCameraDeviceName)) {
        } else {
            GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Creating external facing camera capturer. camera= "), this.externalFacingCameraDeviceName, TAG);
            this.videoCapturer = this.cameraEnumerator.createCapturer(this.externalFacingCameraDeviceName, this.cameraEventsHandler);
            setCurrentCameraDeviceName(this.externalFacingCameraDeviceName);
        }
    }

    private void createCameraEnumerator() {
        boolean z;
        if (this.cameraEnumerator != null) {
            return;
        }
        try {
            z = Camera2Enumerator.isSupported(this.applicationContext);
        } catch (IllegalArgumentException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Camera2ApiSupported not supported. Query returned exception: ");
            outline107.append(e.getMessage());
            Log.i(str, outline107.toString());
            z = false;
        }
        Log.i(TAG, "Creating camera enumator camera2apiSupported=" + z + ", preferCamera1api=" + this.preferCamera1api + "forceCamera2api=" + this.forceCamera2api);
        if (!this.forceCamera2api && (this.preferCamera1api || !z)) {
            Log.i(TAG, "Creating Camera1Enumerator. camera2api support= " + z + " preferCamera1Api= " + this.preferCamera1api);
            this.usingCamera2api = false;
            this.cameraEnumerator = new Camera1Enumerator(this.captureToTexture);
            return;
        }
        Log.i(TAG, "Creating Camera2Enumerator");
        this.usingCamera2api = true;
        this.cameraEnumerator = new Camera2Enumerator(this.applicationContext);
    }

    private VideoTrack createVideoTrack(PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str) {
        try {
            if (this.videoCapturer == null) {
                Log.i(TAG, "Cannot create video track, VideoCapturer not present");
                return null;
            }
            this.videoSource = peerConnectionFactoryWrapper.createVideoSource(this.videoCapturer);
            if (z) {
                this.videoCapturer.startCapture(this.videoWidth, this.videoHeight, this.videoFps);
                this.videoCapturerStopped = false;
            }
            VideoTrack createVideoTrack = peerConnectionFactoryWrapper.createVideoTrack(str, this.videoSource);
            createVideoTrack.setEnabled(z);
            return createVideoTrack;
        } catch (Exception e) {
            Log.e(TAG, "RuntimeException in createVideoTrack while creating video source", e);
            return null;
        }
    }

    private String getCurrentCameraDeviceName() {
        String str;
        synchronized (this.cameraInformationStateLock) {
            String str2 = "currentCameraDeviceName: " + this.currentCameraDeviceName;
            str = this.currentCameraDeviceName;
        }
        return str;
    }

    public static boolean isH264HwDecodeSupported() {
        return MediaCodecVideoDecoder.isH264HwSupported();
    }

    public static boolean isH264HwEncodeSupported() {
        return MediaCodecVideoEncoder.isH264HwSupported();
    }

    public static boolean isVP8HwDecodeSupported() {
        return MediaCodecVideoDecoder.isVp8HwSupported();
    }

    public static boolean isVP8HwEncodeSupported() {
        return MediaCodecVideoEncoder.isVp8HwSupported();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCurrentCameraDeviceName() {
        setCurrentCameraDeviceName(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentCameraDeviceName(String str) {
        synchronized (this.cameraInformationStateLock) {
            String str2 = "Setting currentCameraDeviceName: " + str;
            this.currentCameraDeviceName = str;
        }
    }

    public static void setTraceAndDebugLogLevel(Logging.TraceLevel traceLevel, Logging.Severity severity) {
        Logging.enableTracing("logcat:", EnumSet.of(traceLevel));
        Logging.enableLogToDebugOutput(severity);
    }

    private void setVideoConstraints(int i, int i2, int i3) {
        this.videoWidth = Math.min(i, this.maxVideoWidthAllowed);
        this.videoHeight = Math.min(i2, this.maxVideoHeightAllowed);
        this.videoFps = Math.min(i3, this.maxVideoFpsAllowed);
        String str = TAG;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Setting constraints VideoWidth= ", i, " VideoHeight= ", i2, " VideoFPS= ");
        outline110.append(i3);
        Log.i(str, outline110.toString());
    }

    private void stopVideoCapturer(boolean z) {
        synchronized (this.cameraInformationStateLock) {
            this.cameraInformation = null;
            resetCurrentCameraDeviceName();
        }
        if (this.videoCapturer == null) {
            return;
        }
        for (int i = 1; i <= 10 && !this.videoCapturerStopped; i++) {
            Log.i(TAG, "Attempting to stop video capturer. Try= " + i);
            try {
                this.videoCapturer.stopCapture();
                this.videoCapturerStopped = true;
                Log.i(TAG, "Video capturer stopped.");
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted while trying to stop video capturer", e);
            }
        }
        if (!this.videoCapturerStopped) {
            Log.e(TAG, " Could not stop video capturer after multiple tries!");
        }
        if (!z) {
            return;
        }
        FollowMeShim followMeShim = this.followMeShim;
        if (followMeShim != null) {
            followMeShim.dispose();
        }
        this.videoCapturer.dispose();
        this.videoCapturer = null;
        this.videoCapturerStopped = true;
    }

    private void updateVideoFramerate(int i) {
        int i2 = 0;
        if (i != 0 && i <= this.maxVideoFpsAllowed) {
            if (!this.videoCapturerStopped && this.videoCapturer != null && (this.videoCapturer instanceof CameraVideoCapturer)) {
                if (this.usingCamera2api) {
                    Log.i(TAG, "Using Camera2 api, skip setting requested Fps");
                    return;
                }
                synchronized (this.cameraInformationStateLock) {
                    if (this.cameraInformation == null) {
                        this.pendingNewMaxFps = i;
                        Log.i(TAG, "Camera Information not available, can not set requested Fps");
                        return;
                    }
                    this.pendingNewMaxFps = 0;
                    int i3 = i * 1000;
                    Camera.Parameters parameters = ((Camera1Information) this.cameraInformation).getParameters();
                    int[] iArr = new int[2];
                    parameters.getPreviewFpsRange(iArr);
                    if (i3 == iArr[1]) {
                        return;
                    }
                    Iterator<int[]> it2 = parameters.getSupportedPreviewFpsRange().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        int[] next = it2.next();
                        if (i3 == next[1]) {
                            i2 = next[0];
                            break;
                        }
                    }
                    if (i2 == 0) {
                        Log.e(TAG, "New Fps range not supported");
                        return;
                    }
                    parameters.setPreviewFpsRange(i2, i3);
                    CameraInformation cameraInformation = this.cameraInformation;
                    if (this.videoCapturer instanceof CameraVideoCapturer) {
                        ((CameraVideoCapturer) this.videoCapturer).setCameraInformation(cameraInformation);
                    }
                    Log.i(TAG, GeneratedOutlineSupport1.outline54("updateVideoFramerate [", i2, ",", i3, "]"));
                    return;
                }
            }
            Log.i(TAG, "CameraVideoCapturer is not available, can not set requested Fps");
            return;
        }
        Log.e(TAG, "Invalid framerate");
        this.pendingNewMaxFps = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoFramerateIfAllowed(int i) {
        if (this.allowCameraHalFramerateUpdate) {
            updateVideoFramerate(i);
        } else {
            Log.i(TAG, "Adapting frame rate is not allowed for current device.");
        }
    }

    public boolean changeCapturerOutputFormat(int i, int i2, int i3) {
        synchronized (this.cameraInformationStateLock) {
            this.cameraInformation = null;
        }
        setVideoConstraints(i, i2, i3);
        if (this.videoCapturer == null) {
            Log.e(TAG, "Can't change video format, capturer null");
            return false;
        } else if (this.videoCapturerStopped) {
            Log.i(TAG, "VideoCapturer is not started yet. Ignoring changeCapturerOutputFormat request");
            return true;
        } else {
            String str = TAG;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("changing camera output format to: ", i, ReactProperties.HereMapMarker.X, i2, "@");
            outline110.append(this.videoFps);
            Log.i(str, outline110.toString());
            this.videoCapturer.changeCaptureFormat(i, i2, i3);
            return true;
        }
    }

    public boolean createVideoConstraintsIfSupported(int i, int i2, int i3, boolean z, String str) {
        setVideoConstraints(i, i2, i3);
        this.videoCodecHwAcceleration = z;
        this.preferredVideoCodec = str;
        if (this.videoWidth == 0 || this.videoHeight == 0) {
            this.videoWidth = 1280;
            this.videoHeight = 720;
        }
        return hasLocalVideoCapability();
    }

    public VideoEffectShim createVideoEffectShim(VideoEffectShim.WebRTCVideoEffectTransitionListener webRTCVideoEffectTransitionListener) {
        synchronized (this.cameraInformationStateLock) {
            this.videoEffectShim = new VideoEffectShim(this, webRTCVideoEffectTransitionListener);
        }
        if (this.videoCapturer != null && (this.videoCapturer instanceof CameraVideoCapturer)) {
            this.videoEffectShim.init((CameraVideoCapturer) this.videoCapturer);
        }
        return this.videoEffectShim;
    }

    public void disposeLocalAudioSourcePipe() {
        AudioSource audioSource = this.audioSource;
        if (audioSource != null) {
            audioSource.dispose();
            this.audioSource = null;
        }
    }

    public void disposeLocalAudioVideoPipe() {
        disposeLocalAudioSourcePipe();
        disposeLocalVideoSourcePipe();
    }

    public void disposeLocalVideoSourcePipe() {
        Log.i(TAG, "disposing local video pipe");
        stopVideoCapturer(true);
        VideoSource videoSource = this.videoSource;
        if (videoSource != null) {
            videoSource.dispose();
            this.videoSource = null;
        }
        this.frontFacingCameraDeviceName = null;
        this.rearFacingCameraDeviceName = null;
        this.externalFacingCameraDeviceName = null;
        this.cameraEnumerator = null;
        this.cameraMetadataShim = null;
        this.cameraMetricsObserver = null;
        this.cameraMetricsHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endVideoEffect() {
        if (this.videoCapturer == null || !(this.videoCapturer instanceof CameraVideoCapturer)) {
            return;
        }
        ((CameraVideoCapturer) this.videoCapturer).setVideoEffectStatusListener("", null);
    }

    public boolean hasLocalVideoCapability() {
        if (this.videoCapable) {
            return true;
        }
        createCameraEnumerator();
        this.numberOfCameras = 0;
        String[] deviceNames = this.cameraEnumerator.getDeviceNames();
        if (deviceNames == null || deviceNames.length == 0) {
            Log.i(TAG, "no camera detected");
            this.numberOfCameras = 0;
            this.frontFacingCameraDeviceName = null;
            this.rearFacingCameraDeviceName = null;
            this.externalFacingCameraDeviceName = null;
        } else {
            for (String str : deviceNames) {
                if (this.cameraEnumerator.isFrontFacing(str)) {
                    this.numberOfCameras++;
                    if (TextUtils.isEmpty(this.frontFacingCameraDeviceName)) {
                        this.frontFacingCameraDeviceName = str;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Front facing camera= ");
                        outline107.append(this.frontFacingCameraDeviceName);
                        outline107.toString();
                    }
                } else if (this.cameraEnumerator.isBackFacing(str)) {
                    this.numberOfCameras++;
                    if (TextUtils.isEmpty(this.rearFacingCameraDeviceName)) {
                        this.rearFacingCameraDeviceName = str;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Rear facing camera= ");
                        outline1072.append(this.rearFacingCameraDeviceName);
                        outline1072.toString();
                    }
                } else if (this.cameraEnumerator.isExternalFacing(str)) {
                    this.numberOfCameras++;
                    if (TextUtils.isEmpty(this.externalFacingCameraDeviceName)) {
                        this.externalFacingCameraDeviceName = str;
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("External facing camera= ");
                        outline1073.append(this.externalFacingCameraDeviceName);
                        outline1073.toString();
                    }
                } else {
                    GeneratedOutlineSupport1.outline163("Camera neither front nor back or external = ", str, TAG);
                }
            }
        }
        if (this.numberOfCameras == 0) {
            Log.w(TAG, "No camera on device!");
            this.videoCapable = false;
        } else {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("front, back or external facing camera found= ");
            outline1074.append(this.numberOfCameras);
            outline1074.toString();
            this.videoCapable = true;
        }
        if (!this.videoCapable) {
            this.cameraEnumerator = null;
        }
        return this.videoCapable;
    }

    public boolean isHDVideo() {
        return this.videoCapable && this.videoWidth * this.videoHeight >= 921600;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void processCamera2Information(java.lang.String r5, java.lang.String r6, org.webrtc.Camera2Information r7) {
        /*
            r4 = this;
            java.lang.String r0 = "amazon-hal-effects"
            boolean r5 = r0.equals(r5)
            r0 = 0
            if (r5 == 0) goto L64
            if (r6 == 0) goto L64
            int r5 = r6.length()
            if (r5 <= 0) goto L64
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "processCamera2Information: videoEffect="
            r5.append(r1)
            r5.append(r6)
            r5.toString()
            java.lang.String r5 = r6.toLowerCase()
            java.lang.String r1 = "enable:true"
            boolean r5 = r5.contains(r1)
            java.util.regex.Pattern r1 = org.webrtc.LocalAudioVideoShim.videoEffectCommandPattern
            java.util.regex.Matcher r6 = r1.matcher(r6)
            boolean r1 = r6.matches()
            if (r1 == 0) goto L5c
            r1 = 1
            java.lang.String r2 = r6.group(r1)     // Catch: java.lang.NumberFormatException -> L45
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L45
            float r6 = r2.floatValue()     // Catch: java.lang.NumberFormatException -> L45
            goto L66
        L45:
            java.lang.String r2 = org.webrtc.LocalAudioVideoShim.TAG
            java.lang.String r3 = "Invalid TIME transitionTime value: "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            java.lang.String r6 = r6.group(r1)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            org.webrtc.Logging.e(r2, r6)
            goto L65
        L5c:
            java.lang.String r6 = org.webrtc.LocalAudioVideoShim.TAG
            java.lang.String r1 = "No valid TIME transitionTime value"
            org.webrtc.Logging.d(r6, r1)
            goto L65
        L64:
            r5 = 0
        L65:
            r6 = r0
        L66:
            if (r5 == 0) goto L6a
            r0 = 1065353216(0x3f800000, float:1.0)
        L6a:
            if (r5 == 0) goto L70
            r7.enableVideoEffect(r6, r0)
            goto L73
        L70:
            r7.disableVideoEffect()
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.LocalAudioVideoShim.processCamera2Information(java.lang.String, java.lang.String, org.webrtc.Camera2Information):void");
    }

    public AudioTrack provideAudioTrack(String str, PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, MediaConstraints mediaConstraints) {
        this.audioSource = peerConnectionFactoryWrapper.createAudioSource(mediaConstraints);
        return peerConnectionFactoryWrapper.createAudioTrack(str, this.audioSource);
    }

    public VideoTrack provideLocalFileBasedVideoTrack(PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str, String str2) {
        if (ContextCompat.checkSelfPermission(this.applicationContext, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            Log.e(TAG, "Can't create a video track or capturer. No permissions to read external storage.");
            return null;
        }
        try {
            this.videoCapturer = new FileVideoCapturer(str2);
        } catch (Exception e) {
            Log.e(TAG, "Exception while opening file, ensure correct path and format(y4m) of video file.", e);
        }
        if (this.videoCapturer == null) {
            Log.i(TAG, "FileVideoCapturer could not be created");
            return null;
        }
        return createVideoTrack(peerConnectionFactoryWrapper, z, str);
    }

    public VideoTrack provideLocalVideoTrack(EglBase.Context context, PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str, PeerConnection peerConnection) {
        createCameraEnumerator();
        if (ContextCompat.checkSelfPermission(this.applicationContext, "android.permission.CAMERA") != 0) {
            Log.e(TAG, "Can't create a video track or capturer. No permissions to use camera");
            return null;
        }
        createCameraCapturer();
        return createVideoTrack(peerConnectionFactoryWrapper, z, str);
    }

    public void restartLocalVideoSource() {
        if (this.videoCapturer != null && this.videoCapturerStopped) {
            Log.i(TAG, "Starting video capturer.");
            this.videoCapturer.startCapture(this.videoWidth, this.videoHeight, this.videoFps);
            this.videoCapturerStopped = false;
            return;
        }
        Log.i(TAG, "Can't restart Video capturer; not present or already started");
    }

    public void setMaxVideoConstraints(int i, int i2, int i3, PeerConnection peerConnection) {
        Log.i(TAG, String.format(Locale.US, "Modifying max video constraints, videoWidth = %d, videoHeight = %d, fps = %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)));
        this.maxVideoWidthAllowed = i;
        this.maxVideoHeightAllowed = i2;
        this.maxVideoFpsAllowed = i3;
        setVideoConstraints(i, i2, i3);
        if (!this.videoCapturerStopped && this.videoCapturer != null) {
            if (this.captureToTexture) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("adapting camera output format to: ");
                outline107.append(this.videoWidth);
                outline107.append(ReactProperties.HereMapMarker.X);
                outline107.append(this.videoHeight);
                outline107.append("@");
                outline107.append(this.videoFps);
                Log.i(str, outline107.toString());
                this.videoSource.adaptOutputFormat(this.videoWidth, this.videoHeight, this.videoFps);
                return;
            }
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("changing camera output format to: ");
            outline1072.append(this.videoWidth);
            outline1072.append(ReactProperties.HereMapMarker.X);
            outline1072.append(this.videoHeight);
            outline1072.append("@");
            outline1072.append(this.videoFps);
            Log.i(str2, outline1072.toString());
            this.videoCapturer.changeCaptureFormat(this.videoWidth, this.videoHeight, this.videoFps);
            return;
        }
        Log.i(TAG, "VideoCapturer is not available yet. Just updating the video constraints");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlatformCameraParameter(String str, String str2) {
        GeneratedOutlineSupport1.outline163("Setting camera parameter ", str, TAG);
        if (this.videoCapturerStopped) {
            Log.e(TAG, "Video capturer stopped, cannot set camera parameter");
        } else if (this.videoCapturer == null) {
            Log.e(TAG, "Video capturer not created, cannot set camera parameter");
        } else {
            synchronized (this.cameraInformationStateLock) {
                if (this.cameraInformation == null) {
                    Log.e(TAG, "Camera Information not available, caching request parameter");
                    this.cachedPlatformCameraParameters.put(str, str2);
                    return;
                }
                if (this.cameraInformation instanceof Camera1Information) {
                    ((Camera1Information) this.cameraInformation).getParameters().set(str, str2);
                } else if (this.cameraInformation instanceof Camera2Information) {
                    processCamera2Information(str, str2, (Camera2Information) this.cameraInformation);
                } else {
                    Log.e(TAG, "The implementation of CameraInformation is not supported");
                    return;
                }
                CameraInformation cameraInformation = this.cameraInformation;
                if (!(this.videoCapturer instanceof CameraVideoCapturer)) {
                    return;
                }
                ((CameraVideoCapturer) this.videoCapturer).setCameraInformation(cameraInformation);
            }
        }
    }

    public void stopLocalVideoSource() {
        stopVideoCapturer(false);
    }

    public boolean switchCamera(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler, String str) {
        if (this.videoCapable && this.videoCapturer != null && (this.videoCapturer instanceof CameraVideoCapturer)) {
            CameraVideoCapturer cameraVideoCapturer = (CameraVideoCapturer) this.videoCapturer;
            if (Strings.isNullOrEmpty(str)) {
                cameraVideoCapturer.switchCamera(cameraSwitchHandler);
                return true;
            } else if (str.equals(getCurrentCameraDeviceName())) {
                Log.i(TAG, "Camera is already active.");
                return false;
            } else {
                cameraVideoCapturer.switchCamera(str, cameraSwitchHandler);
                return true;
            }
        }
        Log.e(TAG, "Cannot switch camera.");
        return false;
    }

    public void updateVideoConstraints(PeerConnection peerConnection, List<Pair<String, Integer>> list) {
        int i = this.videoWidth;
        int i2 = this.videoHeight;
        int i3 = 0;
        for (Pair<String, Integer> pair : list) {
            if (((String) pair.first).equals(MAX_VIDEO_FPS_CONSTRAINT)) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("New mediaconstraints update: maxFrameRate ");
                outline107.append(Integer.toString(((Integer) pair.second).intValue()));
                Log.i(str, outline107.toString());
                i3 = Math.min(((Integer) pair.second).intValue(), this.maxVideoFpsAllowed);
            } else if (((String) pair.first).equals("maxWidth")) {
                i = Math.min(((Integer) pair.second).intValue(), this.maxVideoWidthAllowed);
            } else if (((String) pair.first).equals("maxHeight")) {
                i2 = Math.min(((Integer) pair.second).intValue(), this.maxVideoHeightAllowed);
            }
        }
        if (i3 == 0) {
            Log.e(TAG, "Invalid framerate");
            return;
        }
        float f = i / i2;
        int i4 = this.videoWidth;
        int i5 = this.videoHeight;
        float f2 = i4 / i5;
        if (f > f2) {
            i = (int) ((i4 * i2) / i5);
        } else if (f < f2) {
            i2 = (int) ((i5 * i) / i4);
        }
        updateVideoFramerateIfAllowed(i3);
        if (!this.captureToTexture || this.videoSource == null) {
            return;
        }
        String str2 = TAG;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("updateVideoConstraints - adapting video using GPU to: ", i, ReactProperties.HereMapMarker.X, i2, "@");
        outline110.append(i3);
        Log.i(str2, outline110.toString());
        this.videoSource.adaptOutputFormat(i, i2, i3);
    }

    public VideoTrack provideLocalVideoTrack(EglBase.Context context, PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str, PeerConnection peerConnection, Handler handler, CameraMetadataShim.WebRTCCameraMetadataObserver webRTCCameraMetadataObserver) {
        this.cameraMetricsHandler = handler;
        this.cameraMetricsObserver = webRTCCameraMetadataObserver;
        return provideLocalVideoTrack(context, peerConnectionFactoryWrapper, z, str, peerConnection);
    }

    public LocalAudioVideoShim(Context context, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, LocalAudioVideoListener localAudioVideoListener, FollowMeShim followMeShim) {
        this.maxVideoWidthAllowed = 1280;
        this.maxVideoHeightAllowed = 1280;
        this.maxVideoFpsAllowed = 30;
        this.videoCapturerStopped = true;
        this.videoCapable = false;
        this.videoCodecHwAcceleration = true;
        this.videoWidth = 1280;
        this.videoHeight = 720;
        this.videoFps = 30;
        this.preferredVideoCodec = "VP8";
        this.cameraInformationStateLock = new Object();
        this.cameraEventsHandler = new CameraVideoCapturer.CameraEventsHandler() { // from class: org.webrtc.LocalAudioVideoShim.1
            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraClosed() {
                Log.i(LocalAudioVideoShim.TAG, "Debugs: onCameraClosed");
                synchronized (LocalAudioVideoShim.this.cameraInformationStateLock) {
                    LocalAudioVideoShim.this.cameraInformation = null;
                    LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraDisconnected() {
                Log.e(LocalAudioVideoShim.TAG, "onCameraDisconnected");
                LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraFailure(CameraErrorCode.CAMERA_DISCONNECTED, "camera disconnected");
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraError(String str) {
                GeneratedOutlineSupport1.outline162("onCameraError: ", str, LocalAudioVideoShim.TAG);
                LocalAudioVideoShim.this.resetCurrentCameraDeviceName();
                CameraErrorCode cameraErrorCode = (CameraErrorCode) LocalAudioVideoShim.cameraErrorCodeMap.get(str);
                if (cameraErrorCode == null) {
                    cameraErrorCode = CameraErrorCode.UNKNOWN;
                }
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraFailure(cameraErrorCode, str);
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraFreezed(String str) {
                GeneratedOutlineSupport1.outline162("onCameraFreezed: ", str, LocalAudioVideoShim.TAG);
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpened(CameraVideoCapturer cameraVideoCapturer, CameraInformation cameraInformation) {
                String unused = LocalAudioVideoShim.TAG;
                if (LocalAudioVideoShim.this.cameraMetricsHandler != null && LocalAudioVideoShim.this.cameraMetricsObserver != null) {
                    if (LocalAudioVideoShim.this.cameraMetadataShim == null) {
                        LocalAudioVideoShim.this.cameraMetadataShim = new CameraMetadataShim();
                    }
                    LocalAudioVideoShim.this.cameraMetadataShim.registerObserver(cameraVideoCapturer, LocalAudioVideoShim.this.cameraMetricsHandler, LocalAudioVideoShim.this.cameraMetricsObserver);
                }
                synchronized (LocalAudioVideoShim.this.cameraInformationStateLock) {
                    LocalAudioVideoShim.this.cameraInformation = cameraInformation;
                    if (LocalAudioVideoShim.this.videoEffectShim != null) {
                        LocalAudioVideoShim.this.videoEffectShim.init(cameraVideoCapturer);
                    }
                    if (LocalAudioVideoShim.this.cameraInformation instanceof Camera1Information) {
                        Camera.Parameters parameters = ((Camera1Information) LocalAudioVideoShim.this.cameraInformation).getParameters();
                        for (Map.Entry entry : LocalAudioVideoShim.this.cachedPlatformCameraParameters.entrySet()) {
                            parameters.set((String) entry.getKey(), (String) entry.getValue());
                        }
                        if (LocalAudioVideoShim.this.provideCallingServiceHalParameter) {
                            parameters.set("vc-comms", "1");
                        }
                    } else if (LocalAudioVideoShim.this.cameraInformation instanceof Camera2Information) {
                        for (Map.Entry entry2 : LocalAudioVideoShim.this.cachedPlatformCameraParameters.entrySet()) {
                            LocalAudioVideoShim.this.processCamera2Information((String) entry2.getKey(), (String) entry2.getValue(), (Camera2Information) LocalAudioVideoShim.this.cameraInformation);
                        }
                        if (LocalAudioVideoShim.this.provideCallingServiceHalParameter) {
                            for (CaptureRequest.Key<?> key : ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureRequestKeys()) {
                                if ("com.mediatek.webrtc.webrtcenable".equals(key.getName())) {
                                    ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).addCustomCaptureRequest((CaptureRequest.Key) key.getClass().cast(key), new int[]{1});
                                }
                            }
                        }
                        if (LocalAudioVideoShim.this.followMeShim != null) {
                            int captureWidth = ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureWidth();
                            int captureHeight = ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).getCaptureHeight();
                            Log.i(LocalAudioVideoShim.TAG, "FMCTest:init followMeShim");
                            ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).setFollowMeSurface(LocalAudioVideoShim.this.followMeShim.init(captureWidth, captureHeight));
                            ((Camera2Information) LocalAudioVideoShim.this.cameraInformation).setFollowMeCameraSessionFrameUpdater(LocalAudioVideoShim.this.followMeShim.getFrameUpdater());
                        }
                    } else {
                        Log.e(LocalAudioVideoShim.TAG, "The implementation of CameraInformation is not supported");
                    }
                    LocalAudioVideoShim.this.cachedPlatformCameraParameters.clear();
                    cameraVideoCapturer.setCameraInformation(LocalAudioVideoShim.this.cameraInformation);
                }
                if (LocalAudioVideoShim.this.pendingNewMaxFps > 0) {
                    LocalAudioVideoShim localAudioVideoShim = LocalAudioVideoShim.this;
                    localAudioVideoShim.updateVideoFramerateIfAllowed(localAudioVideoShim.pendingNewMaxFps);
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onCameraOpening(String str) {
                String unused = LocalAudioVideoShim.TAG;
                GeneratedOutlineSupport1.outline158("onCameraOpening: ", str);
                LocalAudioVideoShim.this.setCurrentCameraDeviceName(str);
                if (LocalAudioVideoShim.this.localAudioVideoListener != null) {
                    LocalAudioVideoShim.this.localAudioVideoListener.onCameraOpening(str, LocalAudioVideoShim.this.cameraEnumerator.isFrontFacing(str));
                }
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
            public void onFirstFrameAvailable() {
                Log.i(LocalAudioVideoShim.TAG, "onFirstFrameAvailable");
            }
        };
        this.applicationContext = context;
        this.captureToTexture = z;
        this.preferCamera1api = z2;
        this.forceCamera2api = z3;
        this.allowCameraHalFramerateUpdate = z4;
        this.provideCallingServiceHalParameter = z5;
        this.videoEffectShim = null;
        this.followMeShim = followMeShim;
        this.cameraMetadataShim = null;
        this.cameraMetricsObserver = null;
        this.cameraMetricsHandler = null;
        this.cachedPlatformCameraParameters = new HashMap();
        this.localAudioVideoListener = localAudioVideoListener;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalAudioVideoShim created with params: captureToTexture=");
        outline107.append(Boolean.valueOf(z));
        outline107.append(", preferCamera1api=");
        outline107.append(Boolean.valueOf(z2));
        outline107.append(", provideCallingServiceHalParameter=");
        outline107.append(Boolean.valueOf(z5));
        outline107.append(", forceCamera2api=");
        outline107.append(Boolean.valueOf(z3));
        outline107.append(", allowCameraHalFramerateUpdate=");
        outline107.append(Boolean.valueOf(z4));
        outline107.toString();
    }
}
