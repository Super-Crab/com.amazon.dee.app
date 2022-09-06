package com.amazon.rtcmedia.webrtc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.rtcmedia.util.MediaUIBridge;
import com.amazon.rtcmedia.util.RTCContextUtils;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.amazon.rtcmedia.webrtc.RTCScreenCapturer;
import com.amazon.rtcmedia.webrtc.RTCVideoEffect;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera1Information;
import org.webrtc.Camera2Enumerator;
import org.webrtc.Camera2Information;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraInformation;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
/* loaded from: classes13.dex */
public class RTCLocalVideoHandler {
    private static final String AMAZON_HAL_EFFECTS = "amazon-hal-effects";
    private static final int HD_VIDEO_HEIGHT = 720;
    private static final int HD_VIDEO_WIDTH = 1280;
    private static final int MAX_VIDEO_FPS = 30;
    private static final int MAX_VIDEO_HEIGHT = 1080;
    private static final int MAX_VIDEO_WIDTH = 1920;
    private CameraInformation cameraInformation;
    private RTCCameraListener rtcCameraListener;
    private String sessionId;
    private RTCVideoEffect videoEffect;
    private static final String videoEffectCommandRegEx = "^\\[.*\\sTIME:(\\d+\\.\\d+).*";
    private static final Pattern videoEffectCommandPattern = Pattern.compile(videoEffectCommandRegEx);
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCLocalVideoHandler.class);
    private boolean screenCaptureEnabled = false;
    private boolean captureToTexture = true;
    private boolean isFrontFacing = true;
    private boolean preferCamera1 = false;
    private VideoCapturer videoCapturer = null;
    private boolean videoCapturerStopped = true;
    private VideoSource videoSource = null;
    private int maxVideoWidthAllowed = MAX_VIDEO_WIDTH;
    private int maxVideoHeightAllowed = 1080;
    private int maxVideoFpsAllowed = 30;
    private int videoWidth = 1280;
    private int videoHeight = 720;
    private int videoFps = 30;
    private final Object cameraInformationStateLock = new Object();
    private final CameraVideoCapturer.CameraEventsHandler cameraEventsHandler = new CameraVideoCapturer.CameraEventsHandler() { // from class: com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.1
        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraClosed() {
            RTCLocalVideoHandler.mLog.i("onCameraClosed");
            synchronized (RTCLocalVideoHandler.this.cameraInformationStateLock) {
                RTCLocalVideoHandler.this.cameraInformation = null;
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraDisconnected() {
            RTCLocalVideoHandler.mLog.e("onCameraDisconnected");
            if (RTCLocalVideoHandler.this.rtcCameraListener != null) {
                RTCLocalVideoHandler.this.rtcCameraListener.onCameraFailure("Camera Disconnected");
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraError(String str) {
            RtcscLogger rtcscLogger = RTCLocalVideoHandler.mLog;
            rtcscLogger.e("onCameraError: " + str);
            if (RTCLocalVideoHandler.this.rtcCameraListener != null) {
                RTCLocalVideoHandler.this.rtcCameraListener.onCameraFailure(str);
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraFreezed(String str) {
            RtcscLogger rtcscLogger = RTCLocalVideoHandler.mLog;
            rtcscLogger.e("onCameraFreezed: " + str);
            if (RTCLocalVideoHandler.this.rtcCameraListener != null) {
                RTCLocalVideoHandler.this.rtcCameraListener.onCameraFailure(str);
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraOpened(CameraVideoCapturer cameraVideoCapturer, CameraInformation cameraInformation) {
            RTCLocalVideoHandler.mLog.i("onCameraOpened");
            synchronized (RTCLocalVideoHandler.this.cameraInformationStateLock) {
                RTCLocalVideoHandler.this.cameraInformation = cameraInformation;
                if (RTCLocalVideoHandler.this.videoEffect != null) {
                    RTCLocalVideoHandler.this.videoEffect.init(cameraVideoCapturer);
                }
                if (!(RTCLocalVideoHandler.this.cameraInformation instanceof Camera1Information) && !(RTCLocalVideoHandler.this.cameraInformation instanceof Camera2Information)) {
                    RTCLocalVideoHandler.mLog.e("The implementation of CameraInformation is not supported");
                    RTCLocalVideoHandler.this.cachedPlatformCameraParameters.clear();
                    cameraVideoCapturer.setCameraInformation(RTCLocalVideoHandler.this.cameraInformation);
                }
                for (Map.Entry entry : RTCLocalVideoHandler.this.cachedPlatformCameraParameters.entrySet()) {
                    RTCLocalVideoHandler.this.processCameraInformation((String) entry.getKey(), (String) entry.getValue(), RTCLocalVideoHandler.this.cameraInformation);
                }
                RTCLocalVideoHandler.this.cachedPlatformCameraParameters.clear();
                cameraVideoCapturer.setCameraInformation(RTCLocalVideoHandler.this.cameraInformation);
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraOpening(String str) {
            RtcscLogger rtcscLogger = RTCLocalVideoHandler.mLog;
            rtcscLogger.d("onCameraOpening: " + str);
            if (RTCLocalVideoHandler.this.rtcCameraListener != null) {
                RTCLocalVideoHandler.this.rtcCameraListener.onCameraOpening(RTCLocalVideoHandler.this.isFrontFacing);
            }
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onFirstFrameAvailable() {
            RTCLocalVideoHandler.mLog.i("onFirstFrameAvailable");
        }
    };
    private Map<String, String> cachedPlatformCameraParameters = new HashMap();

    /* loaded from: classes13.dex */
    public interface RTCCameraListener {
        void onCameraFailure(String str);

        void onCameraOpening(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RTCLocalVideoHandler(RTCCameraListener rTCCameraListener, String str) {
        this.sessionId = null;
        this.rtcCameraListener = null;
        this.rtcCameraListener = rTCCameraListener;
        this.sessionId = str;
    }

    private VideoCapturer createCameraCapturer(String str) {
        boolean z;
        Context applicationContext = RTCContextUtils.getApplicationContext();
        if (ContextCompat.checkSelfPermission(applicationContext, "android.permission.CAMERA") != 0) {
            mLog.e("Can't create camera capturer. No permissions to use camera");
            return null;
        }
        try {
            z = Camera2Enumerator.isSupported(applicationContext);
        } catch (IllegalArgumentException e) {
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Camera2ApiSupported not supported. Query returned exception: ");
            outline107.append(e.getMessage());
            rtcscLogger.i(outline107.toString());
            z = false;
        }
        RtcscLogger rtcscLogger2 = mLog;
        StringBuilder sb = new StringBuilder();
        sb.append("Creating camera enumator camera2apiSupported = ");
        sb.append(z);
        sb.append(", preferCamera1 = ");
        GeneratedOutlineSupport1.outline185(sb, this.preferCamera1, rtcscLogger2);
        if (!this.preferCamera1 && z) {
            mLog.i("Creating capturer using camera2 API.");
            this.videoCapturer = createCameraCapturer(new Camera2Enumerator(applicationContext), str);
        } else {
            mLog.i("Creating capturer using camera1 API.");
            this.videoCapturer = createCameraCapturer(new Camera1Enumerator(this.captureToTexture), str);
        }
        return this.videoCapturer;
    }

    private void createVideoCapturer(String str) {
        if (this.videoCapturer != null) {
            mLog.i("video capturer is already created.");
            return;
        }
        if (this.screenCaptureEnabled) {
            RTCScreenCapturer.RTCMediaProjectionCallback rTCMediaProjectionCallback = new RTCScreenCapturer.RTCMediaProjectionCallback();
            RTCScreenCapturer rTCScreenCapturer = new RTCScreenCapturer(MediaUIBridge.getInstance().getScreenCapturerPermission(this.sessionId), rTCMediaProjectionCallback, new Handler(Looper.myLooper()));
            rTCMediaProjectionCallback.setCapturer(rTCScreenCapturer);
            this.videoCapturer = rTCScreenCapturer;
        } else {
            this.videoCapturer = createCameraCapturer(str);
        }
        this.videoCapturerStopped = true;
        if (this.videoCapturer != null) {
            return;
        }
        mLog.e("Failed to create video capturer");
    }

    private VideoTrack createVideoTrack(PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str) {
        mLog.i("createVideoTrack");
        try {
            if (this.videoCapturer == null) {
                mLog.w("Cannot create video track, VideoCapturer not present");
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
            mLog.e("exception while creating video source", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void processCameraInformation(java.lang.String r5, java.lang.String r6, org.webrtc.CameraInformation r7) {
        /*
            r4 = this;
            java.lang.String r0 = "amazon-hal-effects"
            boolean r5 = r0.equals(r5)
            r0 = 0
            if (r5 == 0) goto L6c
            if (r6 == 0) goto L6c
            int r5 = r6.length()
            if (r5 <= 0) goto L6c
            com.amazon.rtcmedia.util.RtcscLogger r5 = com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.mLog
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "processCameraInformation: videoEffect="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r5.d(r1)
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r5 = r6.toLowerCase(r5)
            java.lang.String r1 = "enable:true"
            boolean r5 = r5.contains(r1)
            java.util.regex.Pattern r1 = com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.videoEffectCommandPattern
            java.util.regex.Matcher r6 = r1.matcher(r6)
            boolean r1 = r6.matches()
            if (r1 == 0) goto L64
            r1 = 1
            java.lang.String r2 = r6.group(r1)     // Catch: java.lang.NumberFormatException -> L4d
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch: java.lang.NumberFormatException -> L4d
            float r6 = r2.floatValue()     // Catch: java.lang.NumberFormatException -> L4d
            goto L6e
        L4d:
            com.amazon.rtcmedia.util.RtcscLogger r2 = com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.mLog
            java.lang.String r3 = "Invalid TIME transitionTime value: "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            java.lang.String r6 = r6.group(r1)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r2.e(r6)
            goto L6d
        L64:
            com.amazon.rtcmedia.util.RtcscLogger r6 = com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.mLog
            java.lang.String r1 = "No valid TIME transitionTime value"
            r6.d(r1)
            goto L6d
        L6c:
            r5 = 0
        L6d:
            r6 = r0
        L6e:
            if (r5 == 0) goto L72
            r0 = 1065353216(0x3f800000, float:1.0)
        L72:
            if (r5 == 0) goto L78
            r7.enableVideoEffect(r6, r0)
            goto L7b
        L78:
            r7.disableVideoEffect()
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.rtcmedia.webrtc.RTCLocalVideoHandler.processCameraInformation(java.lang.String, java.lang.String, org.webrtc.CameraInformation):void");
    }

    private void setVideoConstraints(int i, int i2, int i3) {
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("Setting constraints VideoWidth= ", i, " VideoHeight= ", i2, " VideoFPS= ");
        outline110.append(i3);
        rtcscLogger.i(outline110.toString());
        if (this.screenCaptureEnabled) {
            int max = Math.max(i, i2);
            float min = Math.min(max, Math.max(this.maxVideoWidthAllowed, this.maxVideoHeightAllowed)) / max;
            this.videoHeight = (int) (i2 * min);
            this.videoWidth = (int) (min * i);
        } else {
            this.videoWidth = Math.min(i, this.maxVideoWidthAllowed);
            this.videoHeight = Math.min(i2, this.maxVideoHeightAllowed);
        }
        this.videoFps = Math.min(i3, this.maxVideoFpsAllowed);
    }

    private void stopVideoCapturer(boolean z) {
        mLog.i("stopVideoCapturer, dispose = " + z);
        synchronized (this.cameraInformationStateLock) {
            this.cameraInformation = null;
        }
        if (this.videoCapturer == null) {
            mLog.i("stopVideoCapturer, videoCapturer is null");
            return;
        }
        for (int i = 1; i <= 10 && !this.videoCapturerStopped; i++) {
            mLog.i("Attempting to stop video capturer. Try= " + i);
            try {
                this.videoCapturer.stopCapture();
                this.videoCapturerStopped = true;
                mLog.i("Video capturer stopped.");
            } catch (InterruptedException e) {
                mLog.w("Interrupted while trying to stop video capturer", e);
            }
        }
        if (!this.videoCapturerStopped) {
            mLog.e(" Could not stop video capturer after multiple tries!");
        }
        if (!z) {
            return;
        }
        this.videoCapturer.dispose();
        this.videoCapturer = null;
        this.videoCapturerStopped = true;
    }

    public boolean changeCapturerOutputFormat(int i, int i2, int i3) {
        synchronized (this.cameraInformationStateLock) {
            this.cameraInformation = null;
        }
        setVideoConstraints(i, i2, i3);
        if (this.videoCapturer == null) {
            mLog.e("Can't change video format, capturer null");
            return false;
        } else if (this.videoCapturerStopped) {
            mLog.i("VideoCapturer is not started yet. Ignoring changeCapturerOutputFormat request");
            return true;
        } else {
            this.videoSource.adaptOutputFormat(this.videoWidth, this.videoHeight, this.videoFps);
            RtcscLogger rtcscLogger = mLog;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("changing camera output format to: ", i, ReactProperties.HereMapMarker.X, i2, "@");
            outline110.append(this.videoFps);
            rtcscLogger.i(outline110.toString());
            this.videoCapturer.changeCaptureFormat(this.videoWidth, this.videoHeight, this.videoFps);
            return true;
        }
    }

    public void configureCapturer(boolean z, boolean z2) {
        this.screenCaptureEnabled = z;
        this.preferCamera1 = z2;
    }

    public RTCVideoEffect createRTCVideoEffect(RTCVideoEffect.WebRTCVideoEffectTransitionListener webRTCVideoEffectTransitionListener) {
        mLog.i("createRTCVideoEffect");
        synchronized (this.cameraInformationStateLock) {
            this.videoEffect = new RTCVideoEffect(this, webRTCVideoEffectTransitionListener);
        }
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer != null && (videoCapturer instanceof CameraVideoCapturer)) {
            this.videoEffect.init((CameraVideoCapturer) videoCapturer);
        }
        return this.videoEffect;
    }

    public void createVideoConstraintsIfSupported(int i, int i2, int i3) {
        setVideoConstraints(i, i2, i3);
        if (this.videoWidth == 0 || this.videoHeight == 0) {
            this.videoWidth = 1280;
            this.videoHeight = 720;
        }
        if (i3 == 0) {
            this.videoFps = 30;
        }
        RtcscLogger rtcscLogger = mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Capturing format: ");
        outline107.append(this.videoWidth);
        outline107.append(ReactProperties.HereMapMarker.X);
        outline107.append(this.videoHeight);
        outline107.append("@");
        outline107.append(this.videoFps);
        rtcscLogger.i(outline107.toString());
    }

    public void dispose() {
        mLog.i("dispose");
        stopVideoCapturer(true);
        if (this.videoSource != null) {
            mLog.i("Closing video source");
            this.videoSource.dispose();
            this.videoSource = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endVideoEffect() {
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer == null) {
            mLog.w("endVideoEffect: Video capturer not created");
        } else if (!(videoCapturer instanceof CameraVideoCapturer)) {
            mLog.w("endVideoEffect: videoCapturer is not CameraVideoCapturer");
        } else {
            ((CameraVideoCapturer) videoCapturer).setVideoEffectStatusListener("", null);
        }
    }

    public VideoTrack provideLocalVideoTrack(PeerConnectionFactoryWrapper peerConnectionFactoryWrapper, boolean z, String str, String str2) {
        createVideoCapturer(str2);
        return createVideoTrack(peerConnectionFactoryWrapper, z, str);
    }

    public void restartLocalVideoSource() {
        if (this.videoCapturer != null && this.videoCapturerStopped) {
            mLog.i("Starting video capturer.");
            this.videoCapturer.startCapture(this.videoWidth, this.videoHeight, this.videoFps);
            this.videoCapturerStopped = false;
            return;
        }
        mLog.i("Can't restart Video capturer; not present or already started");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlatformCameraParameter(String str, String str2) {
        GeneratedOutlineSupport1.outline160("setPlatformCameraParameter: ", str, mLog);
        if (this.videoCapturerStopped) {
            mLog.w("setPlatformCameraParameter: Video capturer stopped");
            return;
        }
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer == null) {
            mLog.w("setPlatformCameraParameter: Video capturer not created");
        } else if (!(videoCapturer instanceof CameraVideoCapturer)) {
            mLog.w("setPlatformCameraParameter: videoCapturer is not CameraVideoCapturer");
        } else {
            synchronized (this.cameraInformationStateLock) {
                if (this.cameraInformation == null) {
                    mLog.w("Camera Information not available, caching request parameter");
                    this.cachedPlatformCameraParameters.put(str, str2);
                    return;
                }
                if (!(this.cameraInformation instanceof Camera1Information) && !(this.cameraInformation instanceof Camera2Information)) {
                    mLog.e("The implementation of CameraInformation is not supported");
                    return;
                }
                processCameraInformation(str, str2, this.cameraInformation);
                ((CameraVideoCapturer) this.videoCapturer).setCameraInformation(this.cameraInformation);
            }
        }
    }

    public void stopLocalVideoSource() {
        mLog.i("stopLocalVideoSource");
        stopVideoCapturer(false);
    }

    public boolean switchCamera() {
        mLog.i("Switch camera");
        VideoCapturer videoCapturer = this.videoCapturer;
        if (videoCapturer == null) {
            mLog.e("switchCamera: Cannot switch camera. videoCapturer is null");
            return false;
        } else if (videoCapturer instanceof CameraVideoCapturer) {
            ((CameraVideoCapturer) videoCapturer).switchCamera(null);
            return true;
        } else {
            mLog.e("Will not switch camera, videoCaputurer is not cameraCapturer");
            return true;
        }
    }

    private VideoCapturer createCameraCapturer(CameraEnumerator cameraEnumerator, String str) {
        boolean z = str != null && !str.isEmpty();
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        GeneratedOutlineSupport1.outline160("createCameraCapturer cameraName = ", str, mLog);
        for (String str2 : deviceNames) {
            if (z) {
                if (str.equalsIgnoreCase(str2)) {
                    return cameraEnumerator.createCapturer(str2, this.cameraEventsHandler);
                }
            } else {
                RtcscLogger rtcscLogger = mLog;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isFrontFacing = ");
                outline107.append(this.isFrontFacing);
                outline107.append(" deviceName = ");
                outline107.append(str2);
                rtcscLogger.d(outline107.toString());
                return cameraEnumerator.createCapturer(str2, this.cameraEventsHandler);
            }
        }
        if (this.rtcCameraListener != null) {
            if (z) {
                GeneratedOutlineSupport1.outline160("Can't find camera set by app: ", str, mLog);
                this.rtcCameraListener.onCameraFailure("Can't find camera set by app: " + str);
                return null;
            }
            mLog.i("Can't find any camera");
            this.rtcCameraListener.onCameraFailure("No Camera was found");
            return null;
        }
        return null;
    }
}
