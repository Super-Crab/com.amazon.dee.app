package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.EglBase;
import org.webrtc.EglBase14;
/* loaded from: classes5.dex */
public class HardwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final List<String> H264_HW_EXCEPTION_MODELS = Arrays.asList("SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4");
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_L_MS = 15000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_M_MS = 20000;
    private static final int QCOM_VP8_KEY_FRAME_INTERVAL_ANDROID_N_MS = 15000;
    private static final String TAG = "HardwareVideoEncoderFactory";
    private final boolean enableH264HighProfile;
    private final boolean enableIntelVp8Encoder;
    private final EglBase14.Context sharedContext;

    /* renamed from: org.webrtc.HardwareVideoEncoderFactory$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$VideoCodecType = new int[VideoCodecType.values().length];

        static {
            try {
                $SwitchMap$org$webrtc$VideoCodecType[VideoCodecType.VP8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$webrtc$VideoCodecType[VideoCodecType.VP9.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$webrtc$VideoCodecType[VideoCodecType.H264.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HardwareVideoEncoderFactory(EglBase.Context context, boolean z, boolean z2) {
        if (context instanceof EglBase14.Context) {
            this.sharedContext = (EglBase14.Context) context;
        } else {
            Logging.w(TAG, "No shared EglBase.Context.  Encoders will not use texture mode.");
            this.sharedContext = null;
        }
        this.enableIntelVp8Encoder = z;
        this.enableH264HighProfile = z2;
    }

    private BitrateAdjuster createBitrateAdjuster(VideoCodecType videoCodecType, String str) {
        if (str.startsWith("OMX.Exynos.")) {
            if (videoCodecType == VideoCodecType.VP8) {
                return new DynamicBitrateAdjuster();
            }
            return new FramerateBitrateAdjuster();
        }
        return new BaseBitrateAdjuster();
    }

    private MediaCodecInfo findCodecForType(VideoCodecType videoCodecType) {
        int i = 0;
        while (true) {
            MediaCodecInfo mediaCodecInfo = null;
            if (i < MediaCodecList.getCodecCount()) {
                try {
                    mediaCodecInfo = MediaCodecList.getCodecInfoAt(i);
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                }
                if (mediaCodecInfo != null && mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecType)) {
                    return mediaCodecInfo;
                }
                i++;
            } else {
                return null;
            }
        }
    }

    private Map<String, String> getCodecProperties(VideoCodecType videoCodecType, boolean z) {
        int ordinal = videoCodecType.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return new HashMap();
        }
        if (ordinal == 2) {
            HashMap outline134 = GeneratedOutlineSupport1.outline134(VideoCodecInfo.H264_FMTP_LEVEL_ASYMMETRY_ALLOWED, "1", VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE, "1");
            outline134.put(VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID, z ? VideoCodecInfo.H264_CONSTRAINED_HIGH_3_1 : VideoCodecInfo.H264_CONSTRAINED_BASELINE_3_1);
            return outline134;
        }
        throw new IllegalArgumentException("Unsupported codec: " + videoCodecType);
    }

    private int getForcedKeyFrameIntervalMs(VideoCodecType videoCodecType, String str) {
        if (videoCodecType != VideoCodecType.VP8 || !str.startsWith("OMX.qcom.")) {
            return 0;
        }
        int i = Build.VERSION.SDK_INT;
        return 15000;
    }

    private int getKeyFrameIntervalSec(VideoCodecType videoCodecType) {
        int ordinal = videoCodecType.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return 100;
        }
        if (ordinal == 2) {
            return 20;
        }
        throw new IllegalArgumentException("Unsupported VideoCodecType " + videoCodecType);
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo mediaCodecInfo) {
        return this.enableH264HighProfile && mediaCodecInfo.getName().startsWith("OMX.qcom.");
    }

    private boolean isHardwareSupportedInCurrentSdk(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        int ordinal = videoCodecType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return isHardwareSupportedInCurrentSdkVp9(mediaCodecInfo);
            }
            if (ordinal == 2) {
                return isHardwareSupportedInCurrentSdkH264(mediaCodecInfo);
            }
            return false;
        }
        return isHardwareSupportedInCurrentSdkVp8(mediaCodecInfo);
    }

    private boolean isHardwareSupportedInCurrentSdkH264(MediaCodecInfo mediaCodecInfo) {
        if (H264_HW_EXCEPTION_MODELS.contains(Build.MODEL)) {
            return false;
        }
        String name = mediaCodecInfo.getName();
        if (name.startsWith("OMX.qcom.")) {
            int i = Build.VERSION.SDK_INT;
        } else if (!name.startsWith("OMX.Exynos.")) {
            return false;
        } else {
            int i2 = Build.VERSION.SDK_INT;
        }
        return true;
    }

    private boolean isHardwareSupportedInCurrentSdkVp8(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        if (name.startsWith("OMX.qcom.")) {
            int i = Build.VERSION.SDK_INT;
        } else if (!name.startsWith("OMX.Exynos.")) {
            if (name.startsWith("OMX.Intel.")) {
                int i2 = Build.VERSION.SDK_INT;
                if (this.enableIntelVp8Encoder) {
                }
            }
            return false;
        } else {
            int i3 = Build.VERSION.SDK_INT;
        }
        return true;
    }

    private boolean isHardwareSupportedInCurrentSdkVp9(MediaCodecInfo mediaCodecInfo) {
        String name = mediaCodecInfo.getName();
        if (name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.")) {
            int i = Build.VERSION.SDK_INT;
            return true;
        }
        return false;
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecType.mimeType())) != null) {
            return isHardwareSupportedInCurrentSdk(mediaCodecInfo, videoCodecType);
        }
        return false;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        VideoCodecType valueOf = VideoCodecType.valueOf(videoCodecInfo.name);
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType == null) {
            return null;
        }
        String name = findCodecForType.getName();
        String mimeType = valueOf.mimeType();
        return new HardwareVideoEncoder(name, valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.TEXTURE_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType)), MediaCodecUtils.selectColorFormat(MediaCodecUtils.ENCODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(mimeType)), videoCodecInfo.params, getKeyFrameIntervalSec(valueOf), getForcedKeyFrameIntervalMs(valueOf, name), createBitrateAdjuster(valueOf, name), this.sharedContext);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecType[] videoCodecTypeArr;
        ArrayList arrayList = new ArrayList();
        for (VideoCodecType videoCodecType : new VideoCodecType[]{VideoCodecType.VP8, VideoCodecType.VP9, VideoCodecType.H264}) {
            MediaCodecInfo findCodecForType = findCodecForType(videoCodecType);
            if (findCodecForType != null) {
                String name = videoCodecType.name();
                if (videoCodecType == VideoCodecType.H264 && isH264HighProfileSupported(findCodecForType)) {
                    arrayList.add(new VideoCodecInfo(0, name, getCodecProperties(videoCodecType, true)));
                }
                arrayList.add(new VideoCodecInfo(0, name, getCodecProperties(videoCodecType, false)));
            }
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }

    @Deprecated
    public HardwareVideoEncoderFactory(boolean z, boolean z2) {
        this(null, z, z2);
    }
}
