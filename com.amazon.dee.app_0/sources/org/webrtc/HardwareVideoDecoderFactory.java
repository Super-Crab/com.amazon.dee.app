package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build;
import org.webrtc.EglBase;
/* loaded from: classes5.dex */
public class HardwareVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "HardwareVideoDecoderFactory";
    private final EglBase.Context sharedContext;

    /* renamed from: org.webrtc.HardwareVideoDecoderFactory$1  reason: invalid class name */
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

    @Deprecated
    public HardwareVideoDecoderFactory() {
        this(null);
    }

    private MediaCodecInfo findCodecForType(VideoCodecType videoCodecType) {
        int i = Build.VERSION.SDK_INT;
        int i2 = 0;
        while (true) {
            MediaCodecInfo mediaCodecInfo = null;
            if (i2 < MediaCodecList.getCodecCount()) {
                try {
                    mediaCodecInfo = MediaCodecList.getCodecInfoAt(i2);
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, "Cannot retrieve encoder codec info", e);
                }
                if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder() && isSupportedCodec(mediaCodecInfo, videoCodecType)) {
                    return mediaCodecInfo;
                }
                i2++;
            } else {
                return null;
            }
        }
    }

    private boolean isHardwareSupported(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        String name = mediaCodecInfo.getName();
        int ordinal = videoCodecType.ordinal();
        if (ordinal == 0) {
            return name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.") || name.startsWith("OMX.Nvidia.");
        } else if (ordinal == 1) {
            return name.startsWith("OMX.qcom.") || name.startsWith("OMX.Exynos.");
        } else if (ordinal != 2) {
            return false;
        } else {
            return name.startsWith("OMX.qcom.") || name.startsWith("OMX.Intel.") || name.startsWith("OMX.Exynos.");
        }
    }

    private boolean isSupportedCodec(MediaCodecInfo mediaCodecInfo, VideoCodecType videoCodecType) {
        if (MediaCodecUtils.codecSupportsType(mediaCodecInfo, videoCodecType) && MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, mediaCodecInfo.getCapabilitiesForType(videoCodecType.mimeType())) != null) {
            return isHardwareSupported(mediaCodecInfo, videoCodecType);
        }
        return false;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(String str) {
        VideoCodecType valueOf = VideoCodecType.valueOf(str);
        MediaCodecInfo findCodecForType = findCodecForType(valueOf);
        if (findCodecForType == null) {
            return null;
        }
        return new HardwareVideoDecoder(findCodecForType.getName(), valueOf, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, findCodecForType.getCapabilitiesForType(valueOf.mimeType())).intValue(), this.sharedContext);
    }

    public HardwareVideoDecoderFactory(EglBase.Context context) {
        this.sharedContext = context;
    }
}
