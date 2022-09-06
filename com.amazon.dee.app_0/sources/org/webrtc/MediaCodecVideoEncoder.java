package org.webrtc;

import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.VideoFrame;
@TargetApi(19)
/* loaded from: classes5.dex */
public class MediaCodecVideoEncoder {
    private static final int BITRATE_ADJUSTMENT_FPS = 30;
    private static final double BITRATE_CORRECTION_MAX_SCALE = 4.0d;
    private static final double BITRATE_CORRECTION_SEC = 3.0d;
    private static final int BITRATE_CORRECTION_STEPS = 20;
    private static final int DEQUEUE_TIMEOUT = 0;
    private static final String H264_MIME_TYPE = "video/avc";
    private static final int MAXIMUM_INITIAL_FPS = 30;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final long QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "MediaCodecVideoEncoder";
    private static final int VIDEO_AVCLevel3 = 256;
    private static final int VIDEO_AVCProfileHigh = 8;
    private static final int VIDEO_ControlRateConstant = 2;
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoEncoderErrorCallback errorCallback = null;
    private static MediaCodecVideoEncoder runningInstance;
    private double bitrateAccumulator;
    private double bitrateAccumulatorMax;
    private int bitrateAdjustmentScaleExp;
    private double bitrateObservationTimeMs;
    private int colorFormat;
    private GlRectDrawer drawer;
    private EglBase14 eglBase;
    private long forcedKeyFrameMs;
    private int height;
    private Surface inputSurface;
    private long lastKeyFrameMs;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int profile;
    private int targetBitrateBps;
    private int targetFps;
    private VideoCodecType type;
    private int width;
    private static Set<String> hwEncoderDisabledTypes = new HashSet();
    private static final MediaCodecProperties qcomVp8HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp8HwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties intelVp8HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties qcomVp9HwProperties = new MediaCodecProperties("OMX.qcom.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosVp9HwProperties = new MediaCodecProperties("OMX.Exynos.", 24, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] vp9HwList = {qcomVp9HwProperties, exynosVp9HwProperties};
    private static final MediaCodecProperties qcomH264HwProperties = new MediaCodecProperties("OMX.qcom.", 19, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties exynosH264HwProperties = new MediaCodecProperties("OMX.Exynos.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties intelH264HwProperties = new MediaCodecProperties("OMX.Intel.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties mtkH264HwProperties = new MediaCodecProperties("OMX.MTK.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties mtkH264HwPropertiesWithNoAdjust = new MediaCodecProperties("OMX.MTK.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties amlogicH264HwProperties = new MediaCodecProperties("OMX.amlogic.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties amlogicH264HwPropertiesFpsAdjust = new MediaCodecProperties("OMX.amlogic.", 21, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties amlogicH264HwPropertiesDynamicAdjust = new MediaCodecProperties("OMX.amlogic.", 21, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties msH264HwProperties = new MediaCodecProperties("OMX.MS.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties brcmH264HwProperties = new MediaCodecProperties("OMX.brcm.", 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties nvidiaH264HwProperties = new MediaCodecProperties("OMX.Nvidia.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties kirinH264HwPropertiesNoAdjust = new MediaCodecProperties("OMX.hisi.", 26, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties kirinH264HwPropertiesFpsAdjust = new MediaCodecProperties("OMX.hisi.", 26, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties kirinH264HwPropertiesDynamicAdjust = new MediaCodecProperties("OMX.hisi.", 26, BitrateAdjustmentType.DYNAMIC_ADJUSTMENT);
    private static final MediaCodecProperties berlinH264HwProperties = new MediaCodecProperties("OMX.Berlin.", 24, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties googleTensorExynosH264HwProperties = new MediaCodecProperties("c2.exynos.", 26, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final String googleCodecPrefix = "OMX.google.";
    private static final MediaCodecProperties omxGoogle264HwProperties = new MediaCodecProperties(googleCodecPrefix, 21, BitrateAdjustmentType.NO_ADJUSTMENT);
    private static final MediaCodecProperties[] omxGoogleCodecList = {omxGoogle264HwProperties};
    private static final MediaCodecProperties exynosH264HighProfileHwProperties = new MediaCodecProperties("OMX.Exynos.", 23, BitrateAdjustmentType.FRAMERATE_ADJUSTMENT);
    private static final MediaCodecProperties[] h264HighProfileHwList = {exynosH264HighProfileHwProperties};
    private static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final int[] supportedColorList = {19, 21, 2141391872, COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m};
    private static final int[] supportedSurfaceColorList = {2130708361};
    private BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
    private ByteBuffer configData = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.webrtc.MediaCodecVideoEncoder$1CaughtException  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class C1CaughtException {
        Exception e;

        C1CaughtException() {
        }
    }

    /* loaded from: classes5.dex */
    public enum BitrateAdjustmentType {
        NO_ADJUSTMENT,
        FRAMERATE_ADJUSTMENT,
        DYNAMIC_ADJUSTMENT
    }

    /* loaded from: classes5.dex */
    public static class EncoderProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecName = str;
            this.colorFormat = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    /* loaded from: classes5.dex */
    public enum H264Profile {
        CONSTRAINED_BASELINE(0),
        BASELINE(1),
        MAIN(2),
        CONSTRAINED_HIGH(3),
        HIGH(4);
        
        private final int value;

        H264Profile(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class MediaCodecProperties {
        public final BitrateAdjustmentType bitrateAdjustmentType;
        public final String codecPrefix;
        public final int minSdk;

        MediaCodecProperties(String str, int i, BitrateAdjustmentType bitrateAdjustmentType) {
            this.codecPrefix = str;
            this.minSdk = i;
            this.bitrateAdjustmentType = bitrateAdjustmentType;
        }
    }

    /* loaded from: classes5.dex */
    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    /* loaded from: classes5.dex */
    static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
        }
    }

    /* loaded from: classes5.dex */
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    private void checkOnMediaCodecThread() {
        if (this.mediaCodecThread.getId() == Thread.currentThread().getId()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaCodecVideoEncoder previously operated on ");
        outline107.append(this.mediaCodecThread);
        outline107.append(" but is now called on ");
        outline107.append(Thread.currentThread());
        throw new RuntimeException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    private static EncoderProperties findHwEncoder(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr) {
        EncoderProperties findHwEncoderHelper = findHwEncoderHelper(str, mediaCodecPropertiesArr, iArr, false);
        return (findHwEncoderHelper != null || !"video/avc".equals(str) || !isGoogleCodecUseRequested()) ? findHwEncoderHelper : findHwEncoderHelper(str, omxGoogleCodecList, iArr, true);
    }

    private static EncoderProperties findHwEncoderHelper(String str, MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr, boolean z) {
        MediaCodecInfo mediaCodecInfo;
        String str2;
        boolean z2;
        int[] iArr2;
        int[] iArr3;
        int i = Build.VERSION.SDK_INT;
        if (str.equals("video/avc") && !z && Arrays.asList(H264_HW_EXCEPTION_MODELS).contains(Build.MODEL)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Model: ");
            outline107.append(Build.MODEL);
            outline107.append(" has black listed H.264 encoder.");
            Logging.w(TAG, outline107.toString());
            return null;
        }
        for (int i2 = 0; i2 < MediaCodecList.getCodecCount(); i2++) {
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i2);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        str2 = null;
                        break;
                    } else if (supportedTypes[i3].equals(str)) {
                        str2 = mediaCodecInfo.getName();
                        break;
                    } else {
                        i3++;
                    }
                }
                if (str2 == null) {
                    continue;
                } else {
                    Logging.v(TAG, "Found candidate encoder " + str2);
                    BitrateAdjustmentType bitrateAdjustmentType = BitrateAdjustmentType.NO_ADJUSTMENT;
                    int length2 = mediaCodecPropertiesArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            z2 = false;
                            break;
                        }
                        MediaCodecProperties mediaCodecProperties = mediaCodecPropertiesArr[i4];
                        if (str2.startsWith(mediaCodecProperties.codecPrefix)) {
                            if (Build.VERSION.SDK_INT < mediaCodecProperties.minSdk) {
                                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Codec ", str2, " is disabled due to SDK version ");
                                outline115.append(Build.VERSION.SDK_INT);
                                Logging.w(TAG, outline115.toString());
                            } else {
                                BitrateAdjustmentType bitrateAdjustmentType2 = mediaCodecProperties.bitrateAdjustmentType;
                                if (bitrateAdjustmentType2 != BitrateAdjustmentType.NO_ADJUSTMENT) {
                                    Logging.w(TAG, "Codec " + str2 + " requires bitrate adjustment: " + bitrateAdjustmentType2);
                                    bitrateAdjustmentType = bitrateAdjustmentType2;
                                }
                                z2 = true;
                            }
                        }
                        i4++;
                    }
                    if (!z2) {
                        continue;
                    } else {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            for (int i5 : capabilitiesForType.colorFormats) {
                                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("   Color: 0x");
                                outline1072.append(Integer.toHexString(i5));
                                Logging.v(TAG, outline1072.toString());
                            }
                            for (int i6 : iArr) {
                                for (int i7 : capabilitiesForType.colorFormats) {
                                    if (i7 == i6) {
                                        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Found target encoder for mime ", str, " : ", str2, ". Color: 0x");
                                        outline116.append(Integer.toHexString(i7));
                                        outline116.append(". Bitrate adjustment: ");
                                        outline116.append(bitrateAdjustmentType);
                                        Logging.d(TAG, outline116.toString());
                                        return new EncoderProperties(str2, i7, bitrateAdjustmentType);
                                    }
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.e(TAG, "Cannot retrieve encoder capabilities", e2);
                        }
                    }
                }
            }
        }
        return null;
    }

    private double getBitrateScale(int i) {
        return Math.pow(BITRATE_CORRECTION_MAX_SCALE, i / 20.0d);
    }

    private static final MediaCodecProperties[] h264HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomH264HwProperties);
        arrayList.add(exynosH264HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelH264").equals("Enabled")) {
            arrayList.add(intelH264HwProperties);
        }
        String fieldTrialsFindFullName = PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MTKH264");
        if (fieldTrialsFindFullName.startsWith("Enabled")) {
            if (fieldTrialsFindFullName.equals("Enabled-NoAdjust")) {
                arrayList.add(mtkH264HwPropertiesWithNoAdjust);
            } else {
                arrayList.add(mtkH264HwProperties);
            }
        }
        String fieldTrialsFindFullName2 = PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-AMLOGICH264");
        if (fieldTrialsFindFullName2.startsWith("Enabled")) {
            if (fieldTrialsFindFullName2.equals("Enabled-DynamicAdjust")) {
                arrayList.add(amlogicH264HwPropertiesDynamicAdjust);
            } else if (fieldTrialsFindFullName2.equals("Enabled-FpsAdjust")) {
                arrayList.add(amlogicH264HwPropertiesFpsAdjust);
            } else {
                arrayList.add(amlogicH264HwProperties);
            }
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MSH264").equals("Enabled")) {
            arrayList.add(msH264HwProperties);
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-BRCMH264").equals("Enabled")) {
            arrayList.add(brcmH264HwProperties);
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-NVIDIAH264").equals("Enabled")) {
            arrayList.add(nvidiaH264HwProperties);
        }
        String fieldTrialsFindFullName3 = PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-KIRINH264");
        if (fieldTrialsFindFullName3.startsWith("Enabled")) {
            if (fieldTrialsFindFullName3.startsWith("Enabled-FpsAdjust")) {
                arrayList.add(kirinH264HwPropertiesFpsAdjust);
            } else if (fieldTrialsFindFullName3.startsWith("Enabled-DynamicAdjust")) {
                arrayList.add(kirinH264HwPropertiesDynamicAdjust);
            } else {
                arrayList.add(kirinH264HwPropertiesNoAdjust);
            }
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-BERLINH264").startsWith("Enabled")) {
            arrayList.add(berlinH264HwProperties);
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-TENSOREXYNOSH264").startsWith("Enabled")) {
            arrayList.add(googleTensorExynosH264HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    private static boolean isGoogleCodecProperty(EncoderProperties encoderProperties) {
        return encoderProperties.codecName.contains(googleCodecPrefix);
    }

    private static boolean isGoogleCodecUseRequested() {
        return PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-GoogleH264").equals("Enabled");
    }

    public static boolean isH264HighProfileHwSupported() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoderHelper("video/avc", h264HighProfileHwList, supportedColorList, false) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoder("video/avc", h264HwList(), supportedColorList) != null;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/avc") && findHwEncoder("video/avc", h264HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp8HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") && findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList) != null;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") && findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedSurfaceColorList) != null;
    }

    public static boolean isVp9HwSupported() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") && findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedColorList) != null;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        return !hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") && findHwEncoder("video/x-vnd.on2.vp9", vp9HwList, supportedSurfaceColorList) != null;
    }

    private static native void nativeFillBuffer(long j, int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3, ByteBuffer byteBuffer3, int i4);

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (mediaCodecVideoEncoder == null || (thread = mediaCodecVideoEncoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length <= 0) {
            return;
        }
        Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
        for (StackTraceElement stackTraceElement : stackTrace) {
            Logging.d(TAG, stackTraceElement.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void reportEncodedFrame(int r12) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.reportEncodedFrame(int):void");
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    private boolean setRates(int i, int i2) {
        int i3;
        checkOnMediaCodecThread();
        int i4 = i * 1000;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            double d = i4;
            this.bitrateAccumulatorMax = d / 8.0d;
            int i5 = this.targetBitrateBps;
            if (i5 > 0 && i4 < i5) {
                this.bitrateAccumulator = (this.bitrateAccumulator * d) / i5;
            }
        }
        this.targetBitrateBps = i4;
        this.targetFps = i2;
        if (this.bitrateAdjustmentType == BitrateAdjustmentType.FRAMERATE_ADJUSTMENT && (i3 = this.targetFps) > 0) {
            i4 = (this.targetBitrateBps * 30) / i3;
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("setRates: ", i, " -> ");
            outline109.append(i4 / 1000);
            outline109.append(" kbps. Fps: ");
            outline109.append(this.targetFps);
            Logging.v(TAG, outline109.toString());
        } else if (this.bitrateAdjustmentType == BitrateAdjustmentType.DYNAMIC_ADJUSTMENT) {
            StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("setRates: ", i, " kbps. Fps: ");
            outline1092.append(this.targetFps);
            outline1092.append(". ExpScale: ");
            outline1092.append(this.bitrateAdjustmentScaleExp);
            Logging.v(TAG, outline1092.toString());
            int i6 = this.bitrateAdjustmentScaleExp;
            if (i6 != 0) {
                i4 = (int) (i4 * getBitrateScale(i6));
            }
        } else {
            StringBuilder outline1093 = GeneratedOutlineSupport1.outline109("setRates: ", i, " kbps. Fps: ");
            outline1093.append(this.targetFps);
            Logging.v(TAG, outline1093.toString());
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i4);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    public static EncoderProperties vp8HwEncoderProperties() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8")) {
            return null;
        }
        return findHwEncoder("video/x-vnd.on2.vp8", vp8HwList(), supportedColorList);
    }

    private static MediaCodecProperties[] vp8HwList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(qcomVp8HwProperties);
        arrayList.add(exynosVp8HwProperties);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-IntelVP8").equals("Enabled")) {
            arrayList.add(intelVp8HwProperties);
        }
        return (MediaCodecProperties[]) arrayList.toArray(new MediaCodecProperties[arrayList.size()]);
    }

    public static boolean willH264HighProfileUseGoogleCodec() {
        return false;
    }

    public static boolean willH264HighProfileUseGoogleCodecUsingTexture() {
        return false;
    }

    public static boolean willH264UseGoogleCodec() {
        return willH264UseGoogleCodecPath(h264HwList(), supportedColorList);
    }

    private static boolean willH264UseGoogleCodecPath(MediaCodecProperties[] mediaCodecPropertiesArr, int[] iArr) {
        EncoderProperties findHwEncoder;
        if (!isGoogleCodecUseRequested() || hwEncoderDisabledTypes.contains("video/avc") || (findHwEncoder = findHwEncoder("video/avc", mediaCodecPropertiesArr, iArr)) == null) {
            return false;
        }
        return isGoogleCodecProperty(findHwEncoder);
    }

    public static boolean willH264UseGoogleCodecUsingTexture() {
        return willH264UseGoogleCodecPath(h264HwList(), supportedSurfaceColorList);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void checkKeyFrameRequired(boolean r7, long r8) {
        /*
            r6 = this;
            r0 = 500(0x1f4, double:2.47E-321)
            long r8 = r8 + r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r0
            long r0 = r6.lastKeyFrameMs
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L10
            r6.lastKeyFrameMs = r8
        L10:
            r0 = 0
            if (r7 != 0) goto L22
            long r4 = r6.forcedKeyFrameMs
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 <= 0) goto L22
            long r1 = r6.lastKeyFrameMs
            long r1 = r1 + r4
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 <= 0) goto L22
            r1 = 1
            goto L23
        L22:
            r1 = r0
        L23:
            if (r7 != 0) goto L27
            if (r1 == 0) goto L47
        L27:
            java.lang.String r1 = "MediaCodecVideoEncoder"
            if (r7 == 0) goto L31
            java.lang.String r7 = "Sync frame request"
            org.webrtc.Logging.d(r1, r7)
            goto L36
        L31:
            java.lang.String r7 = "Sync frame forced"
            org.webrtc.Logging.d(r1, r7)
        L36:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r1 = "request-sync"
            r7.putInt(r1, r0)
            android.media.MediaCodec r0 = r6.mediaCodec
            r0.setParameters(r7)
            r6.lastKeyFrameMs = r8
        L47:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.checkKeyFrameRequired(boolean, long):void");
    }

    int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0L);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
            boolean z = true;
            if (dequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Logging.d(TAG, "Config frame generated. Offset: " + bufferInfo.offset + ". Size: " + bufferInfo.size);
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[dequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[dequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[dequeueOutputBuffer]);
                    String str = "";
                    int i = 0;
                    while (true) {
                        int i2 = 8;
                        if (bufferInfo.size < 8) {
                            i2 = bufferInfo.size;
                        }
                        if (i >= i2) {
                            break;
                        }
                        str = str + Integer.toHexString(this.configData.get(i) & 255) + " ";
                        i++;
                    }
                    Logging.d(TAG, str);
                    this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                    dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0L);
                }
            }
            int i3 = dequeueOutputBuffer;
            if (i3 < 0) {
                if (i3 == -3) {
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    return dequeueOutputBuffer();
                } else if (i3 == -2) {
                    return dequeueOutputBuffer();
                } else {
                    if (i3 == -1) {
                        return null;
                    }
                    throw new RuntimeException("dequeueOutputBuffer: " + i3);
                }
            }
            ByteBuffer duplicate = this.outputBuffers[i3].duplicate();
            duplicate.position(bufferInfo.offset);
            duplicate.limit(bufferInfo.offset + bufferInfo.size);
            reportEncodedFrame(bufferInfo.size);
            if ((bufferInfo.flags & 1) == 0) {
                z = false;
            }
            if (z) {
                Logging.d(TAG, "Sync frame generated");
            }
            if (z && this.type == VideoCodecType.VIDEO_CODEC_H264) {
                Logging.d(TAG, "Appending config frame of size " + this.configData.capacity() + " to output buffer with offset " + bufferInfo.offset + ", size " + bufferInfo.size);
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
                this.configData.rewind();
                allocateDirect.put(this.configData);
                allocateDirect.put(duplicate);
                allocateDirect.position(0);
                return new OutputBufferInfo(i3, allocateDirect, z, bufferInfo.presentationTimeUs);
            }
            return new OutputBufferInfo(i3, duplicate.slice(), z, bufferInfo.presentationTimeUs);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, null, false, -1L);
        }
    }

    boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "encodeBuffer failed", e);
            return false;
        }
    }

    boolean encodeFrame(long j, boolean z, VideoFrame videoFrame, int i) {
        checkOnMediaCodecThread();
        try {
            long micros = TimeUnit.NANOSECONDS.toMicros(videoFrame.getTimestampNs());
            checkKeyFrameRequired(z, micros);
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            if (buffer instanceof VideoFrame.TextureBuffer) {
                this.eglBase.makeCurrent();
                GLES20.glClear(16384);
                VideoFrameDrawer.drawTexture(this.drawer, (VideoFrame.TextureBuffer) buffer, new Matrix(), this.width, this.height, 0, 0, this.width, this.height);
                this.eglBase.swapBuffers(videoFrame.getTimestampNs());
            } else {
                VideoFrame.I420Buffer i420 = buffer.toI420();
                int i2 = (this.height + 1) / 2;
                ByteBuffer dataY = i420.getDataY();
                ByteBuffer dataU = i420.getDataU();
                ByteBuffer dataV = i420.getDataV();
                int strideY = i420.getStrideY();
                int strideU = i420.getStrideU();
                int strideV = i420.getStrideV();
                if (dataY.capacity() >= this.height * strideY) {
                    if (dataU.capacity() >= strideU * i2) {
                        if (dataV.capacity() >= i2 * strideV) {
                            nativeFillBuffer(j, i, dataY, strideY, dataU, strideU, dataV, strideV);
                            i420.release();
                            this.mediaCodec.queueInputBuffer(i, 0, ((this.width * this.height) * 3) / 2, micros, 0);
                        } else {
                            throw new RuntimeException("V-plane buffer size too small.");
                        }
                    } else {
                        throw new RuntimeException("U-plane buffer size too small.");
                    }
                } else {
                    throw new RuntimeException("Y-plane buffer size too small.");
                }
            }
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeFrame failed", e);
            return false;
        }
    }

    boolean encodeTexture(boolean z, int i, float[] fArr, long j) {
        checkOnMediaCodecThread();
        try {
            checkKeyFrameRequired(z, j);
            this.eglBase.makeCurrent();
            GLES20.glClear(16384);
            this.drawer.drawOes(i, fArr, this.width, this.height, 0, 0, this.width, this.height);
            this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j));
            return true;
        } catch (RuntimeException e) {
            Logging.e(TAG, "encodeTexture failed", e);
            return false;
        }
    }

    ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Input buffers: ");
        outline107.append(inputBuffers.length);
        Logging.d(TAG, outline107.toString());
        return inputBuffers;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0214  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean initEncode(org.webrtc.MediaCodecVideoEncoder.VideoCodecType r9, int r10, int r11, int r12, int r13, int r14, org.webrtc.EglBase14.Context r15) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.initEncode(org.webrtc.MediaCodecVideoEncoder$VideoCodecType, int, int, int, int, int, org.webrtc.EglBase14$Context):boolean");
    }

    void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final C1CaughtException c1CaughtException = new C1CaughtException();
        boolean z = false;
        if (this.mediaCodec != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() { // from class: org.webrtc.MediaCodecVideoEncoder.1
                @Override // java.lang.Runnable
                public void run() {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.stop();
                    } catch (Exception e) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder stop failed", e);
                    }
                    try {
                        MediaCodecVideoEncoder.this.mediaCodec.release();
                    } catch (Exception e2) {
                        Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e2);
                        c1CaughtException.e = e2;
                    }
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                    countDownLatch.countDown();
                }
            }).start();
            if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000L)) {
                Logging.e(TAG, "Media encoder release timeout");
                z = true;
            }
            this.mediaCodec = null;
        }
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        if (z) {
            codecErrors++;
            if (errorCallback != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invoke codec error callback. Errors: ");
                outline107.append(codecErrors);
                Logging.e(TAG, outline107.toString());
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
            throw new RuntimeException("Media encoder release timeout.");
        }
        Exception exc = c1CaughtException.e;
        if (exc == null) {
            Logging.d(TAG, "Java releaseEncoder done");
            return;
        }
        RuntimeException runtimeException = new RuntimeException(exc);
        runtimeException.setStackTrace(ThreadUtils.concatStackTraces(c1CaughtException.e.getStackTrace(), runtimeException.getStackTrace()));
        throw runtimeException;
    }

    boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }
}
