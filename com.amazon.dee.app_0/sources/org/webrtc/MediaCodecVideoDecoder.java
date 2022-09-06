package org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.SystemClock;
import android.view.Surface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.webrtc.SurfaceTextureHelper;
/* loaded from: classes5.dex */
public class MediaCodecVideoDecoder {
    private static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    private static final String FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String FORMAT_KEY_STRIDE = "stride";
    private static final String H264_MIME_TYPE = "video/avc";
    private static final String KIRIN_H264_CODEC_PREFIX = "OMX.hisi.";
    private static final long MAX_DECODE_TIME_MS = 200;
    private static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "MediaCodecVideoDecoder";
    private static final String TENSOR_EXYNOS_H264_CODEC_PREFIX = "c2.exynos.";
    private static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    private static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    private static int codecErrors = 0;
    private static MediaCodecVideoDecoderErrorCallback errorCallback = null;
    private static MediaCodecVideoDecoder runningInstance = null;
    private int colorFormat;
    private int droppedFrames;
    private boolean hasDecodedFirstFrame;
    private int height;
    private ByteBuffer[] inputBuffers;
    private MediaCodec mediaCodec;
    private Thread mediaCodecThread;
    private ByteBuffer[] outputBuffers;
    private int sliceHeight;
    private int stride;
    private TextureListener textureListener;
    private boolean useSurface;
    private int width;
    private static Set<String> hwDecoderDisabledTypes = new HashSet();
    private static final String supportedQcomH264HighProfileHwCodecPrefix = "OMX.qcom.";
    private static final String supportedExynosH264HighProfileHwCodecPrefix = "OMX.Exynos.";
    private static final String[] supportedVp8HwCodecPrefixes = {supportedQcomH264HighProfileHwCodecPrefix, "OMX.Nvidia.", supportedExynosH264HighProfileHwCodecPrefix, "OMX.Intel."};
    private static final String[] supportedVp9HwCodecPrefixes = {supportedQcomH264HighProfileHwCodecPrefix, supportedExynosH264HighProfileHwCodecPrefix};
    private static final String googleCodecPrefix = "OMX.google.";
    private static final String[] omxGoogleCodecPrefixList = {googleCodecPrefix};
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka = 2141391873;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka = 2141391874;
    private static final int COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka = 2141391875;
    private static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    private static final List<Integer> supportedColorList = Arrays.asList(19, 21, 2141391872, Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar32m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar16m4ka), Integer.valueOf((int) COLOR_QCOM_FORMATYVU420PackedSemiPlanar64x32Tile2m8ka), Integer.valueOf((int) COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m));
    private static AtomicInteger desiredMaxPendingFrame = new AtomicInteger(0);
    private final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    private Surface surface = null;
    private final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DecodedOutputBuffer {
        private final long decodeTimeMs;
        private final long endDecodeTimeMs;
        private final int index;
        private final long ntpTimeStampMs;
        private final int offset;
        private final long presentationTimeStampMs;
        private final int size;
        private final long timeStampMs;

        public DecodedOutputBuffer(int i, int i2, int i3, long j, long j2, long j3, long j4, long j5) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.endDecodeTimeMs = j5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DecodedTextureBuffer {
        private final long decodeTimeMs;
        private final long frameDelayMs;
        private final long ntpTimeStampMs;
        private final long presentationTimeStampMs;
        private final int textureID;
        private final long timeStampMs;
        private final float[] transformMatrix;

        public DecodedTextureBuffer(int i, float[] fArr, long j, long j2, long j3, long j4, long j5) {
            this.textureID = i;
            this.transformMatrix = fArr;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.frameDelayMs = j5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String str, int i) {
            this.codecName = str;
            this.colorFormat = i;
        }
    }

    /* loaded from: classes5.dex */
    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TextureListener implements SurfaceTextureHelper.OnTextureFrameAvailableListener {
        private DecodedOutputBuffer bufferToRender;
        private final Object newFrameLock = new Object();
        private DecodedTextureBuffer renderedBuffer;
        private final SurfaceTextureHelper surfaceTextureHelper;

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper) {
            this.surfaceTextureHelper = surfaceTextureHelper;
            surfaceTextureHelper.startListening(this);
        }

        public void addBufferToRender(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.bufferToRender == null) {
                this.bufferToRender = decodedOutputBuffer;
            } else {
                Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int i) {
            DecodedTextureBuffer decodedTextureBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && i > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait(i);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                decodedTextureBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return decodedTextureBuffer;
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = this.bufferToRender != null;
            }
            return z;
        }

        @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
        public void onTextureFrameAvailable(int i, float[] fArr, long j) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null) {
                    this.renderedBuffer = new DecodedTextureBuffer(i, fArr, this.bufferToRender.presentationTimeStampMs, this.bufferToRender.timeStampMs, this.bufferToRender.ntpTimeStampMs, this.bufferToRender.decodeTimeMs, SystemClock.elapsedRealtime() - this.bufferToRender.endDecodeTimeMs);
                    this.bufferToRender = null;
                    this.newFrameLock.notifyAll();
                } else {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected onTextureFrameAvailable() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
            }
        }

        public void release() {
            this.surfaceTextureHelper.stopListening();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.renderedBuffer = null;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class TimeStamps {
        private final long decodeStartTimeMs;
        private final long ntpTimeStampMs;
        private final long timeStampMs;

        public TimeStamps(long j, long j2, long j3) {
            this.decodeStartTimeMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
        }
    }

    /* loaded from: classes5.dex */
    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (this.dequeuedSurfaceOutputBuffers.isEmpty() || this.textureListener.isWaitingForTexture()) {
            return;
        }
        DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
        this.textureListener.addBufferToRender(remove);
        this.mediaCodec.releaseOutputBuffer(remove.index, true);
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        if (this.mediaCodecThread.getId() == Thread.currentThread().getId()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaCodecVideoDecoder previously operated on ");
        outline107.append(this.mediaCodecThread);
        outline107.append(" but is now called on ");
        outline107.append(Thread.currentThread());
        throw new IllegalStateException(outline107.toString());
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000L);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int i) {
        long j;
        int integer;
        int integer2;
        checkOnMediaCodecThread();
        if (this.decodeStartTimeMs.isEmpty()) {
            return null;
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        while (true) {
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, TimeUnit.MILLISECONDS.toMicros(i));
            if (dequeueOutputBuffer == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Decoder output buffers changed: ");
                outline107.append(this.outputBuffers.length);
                Logging.d(TAG, outline107.toString());
                if (this.hasDecodedFirstFrame) {
                    throw new RuntimeException("Unexpected output buffer change event.");
                }
            } else if (dequeueOutputBuffer != -2) {
                if (dequeueOutputBuffer == -1) {
                    return null;
                }
                this.hasDecodedFirstFrame = true;
                TimeStamps remove = this.decodeStartTimeMs.remove();
                long elapsedRealtime = SystemClock.elapsedRealtime() - remove.decodeStartTimeMs;
                if (elapsedRealtime > MAX_DECODE_TIME_MS) {
                    StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Very high decode time: ", elapsedRealtime, "ms. Q size: ");
                    outline111.append(this.decodeStartTimeMs.size());
                    outline111.append(". Might be caused by resuming H264 decoding after a pause.");
                    Logging.e(TAG, outline111.toString());
                    j = 200;
                } else {
                    j = elapsedRealtime;
                }
                return new DecodedOutputBuffer(dequeueOutputBuffer, bufferInfo.offset, bufferInfo.size, TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs), remove.timeStampMs, remove.ntpTimeStampMs, j, SystemClock.elapsedRealtime());
            } else {
                MediaFormat outputFormat = this.mediaCodec.getOutputFormat();
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Decoder format changed: ");
                outline1072.append(outputFormat.toString());
                Logging.d(TAG, outline1072.toString());
                if (outputFormat.containsKey(FORMAT_KEY_CROP_LEFT) && outputFormat.containsKey(FORMAT_KEY_CROP_RIGHT) && outputFormat.containsKey(FORMAT_KEY_CROP_BOTTOM) && outputFormat.containsKey(FORMAT_KEY_CROP_TOP)) {
                    integer = (outputFormat.getInteger(FORMAT_KEY_CROP_RIGHT) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_LEFT);
                    integer2 = (outputFormat.getInteger(FORMAT_KEY_CROP_BOTTOM) + 1) - outputFormat.getInteger(FORMAT_KEY_CROP_TOP);
                } else {
                    integer = outputFormat.getInteger("width");
                    integer2 = outputFormat.getInteger("height");
                }
                if (!this.hasDecodedFirstFrame || (integer == this.width && integer2 == this.height)) {
                    this.width = integer;
                    this.height = integer2;
                    if (!this.useSurface && outputFormat.containsKey("color-format")) {
                        this.colorFormat = outputFormat.getInteger("color-format");
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Color: 0x");
                        outline1073.append(Integer.toHexString(this.colorFormat));
                        Logging.d(TAG, outline1073.toString());
                        if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Non supported color format: ");
                            outline1074.append(this.colorFormat);
                            throw new IllegalStateException(outline1074.toString());
                        }
                    }
                    if (outputFormat.containsKey(FORMAT_KEY_STRIDE)) {
                        this.stride = outputFormat.getInteger(FORMAT_KEY_STRIDE);
                    }
                    if (outputFormat.containsKey(FORMAT_KEY_SLICE_HEIGHT)) {
                        this.sliceHeight = outputFormat.getInteger(FORMAT_KEY_SLICE_HEIGHT);
                    }
                    StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Frame stride and slice height: ");
                    outline1075.append(this.stride);
                    outline1075.append(" x ");
                    outline1075.append(this.sliceHeight);
                    Logging.d(TAG, outline1075.toString());
                    this.stride = Math.max(this.width, this.stride);
                    this.sliceHeight = Math.max(this.height, this.sliceHeight);
                }
            }
        }
        StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Unexpected size change. Configured ");
        outline1076.append(this.width);
        outline1076.append("*");
        GeneratedOutlineSupport1.outline175(outline1076, this.height, ". New ", integer, "*");
        outline1076.append(integer2);
        throw new RuntimeException(outline1076.toString());
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int i) {
        checkOnMediaCodecThread();
        if (this.useSurface) {
            DecodedOutputBuffer dequeueOutputBuffer = dequeueOutputBuffer(i);
            if (dequeueOutputBuffer != null) {
                this.dequeuedSurfaceOutputBuffers.add(dequeueOutputBuffer);
            }
            MaybeRenderDecodedTextureBuffer();
            DecodedTextureBuffer dequeueTextureBuffer = this.textureListener.dequeueTextureBuffer(i);
            if (dequeueTextureBuffer != null) {
                MaybeRenderDecodedTextureBuffer();
                return dequeueTextureBuffer;
            } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (i <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
                return null;
            } else {
                this.droppedFrames++;
                DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
                if (i > 0) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Draining decoder. Dropping frame with TS: ");
                    outline107.append(remove.presentationTimeStampMs);
                    outline107.append(". Total number of dropped frames: ");
                    outline107.append(this.droppedFrames);
                    Logging.w(TAG, outline107.toString());
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Too many output buffers ");
                    outline1072.append(this.dequeuedSurfaceOutputBuffers.size());
                    outline1072.append(". Dropping frame with TS: ");
                    outline1072.append(remove.presentationTimeStampMs);
                    outline1072.append(". Total number of dropped frames: ");
                    outline1072.append(this.droppedFrames);
                    Logging.w(TAG, outline1072.toString());
                }
                this.mediaCodec.releaseOutputBuffer(remove.index, false);
                return new DecodedTextureBuffer(0, null, remove.presentationTimeStampMs, remove.timeStampMs, remove.ntpTimeStampMs, remove.decodeTimeMs, SystemClock.elapsedRealtime() - remove.endDecodeTimeMs);
            }
        }
        throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    private static DecoderProperties findDecoder(String str, String[] strArr) {
        DecoderProperties findDecoderHelper = findDecoderHelper(str, strArr);
        return (findDecoderHelper != null || !"video/avc".equals(str) || !isGoogleCodecUseRequested()) ? findDecoderHelper : findDecoderHelper(str, omxGoogleCodecPrefixList);
    }

    private static DecoderProperties findDecoderHelper(String str, String[] strArr) {
        MediaCodecInfo mediaCodecInfo;
        boolean z;
        int[] iArr;
        int[] iArr2;
        int i = Build.VERSION.SDK_INT;
        Logging.d(TAG, "Trying to find HW decoder for mime " + str);
        int i2 = 0;
        while (true) {
            String str2 = null;
            if (i2 >= MediaCodecList.getCodecCount()) {
                Logging.d(TAG, "No HW decoder found for mime " + str);
                return null;
            }
            try {
                mediaCodecInfo = MediaCodecList.getCodecInfoAt(i2);
            } catch (IllegalArgumentException e) {
                Logging.e(TAG, "Cannot retrieve decoder codec info", e);
                mediaCodecInfo = null;
            }
            if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder()) {
                String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                int length = supportedTypes.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
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
                    Logging.d(TAG, "Found candidate decoder " + str2);
                    int length2 = strArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            z = false;
                            break;
                        } else if (str2.startsWith(strArr[i4])) {
                            z = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (!z) {
                        continue;
                    } else {
                        try {
                            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                            for (int i5 : capabilitiesForType.colorFormats) {
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("   Color: 0x");
                                outline107.append(Integer.toHexString(i5));
                                Logging.v(TAG, outline107.toString());
                            }
                            for (Integer num : supportedColorList) {
                                int intValue = num.intValue();
                                for (int i6 : capabilitiesForType.colorFormats) {
                                    if (i6 == intValue) {
                                        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Found target decoder ", str2, ". Color: 0x");
                                        outline115.append(Integer.toHexString(i6));
                                        Logging.d(TAG, outline115.toString());
                                        return new DecoderProperties(str2, i6);
                                    }
                                }
                            }
                            continue;
                        } catch (IllegalArgumentException e2) {
                            Logging.e(TAG, "Cannot retrieve decoder capabilities", e2);
                        }
                    }
                }
            }
            i2++;
        }
    }

    private static int getMaxPendingFrame() {
        return desiredMaxPendingFrame.get();
    }

    private boolean initDecode(VideoCodecType videoCodecType, int i, int i2, SurfaceTextureHelper surfaceTextureHelper) {
        String[] supportedH264HwCodecPrefixes;
        String str;
        if (this.mediaCodecThread == null) {
            this.useSurface = surfaceTextureHelper != null;
            if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
                supportedH264HwCodecPrefixes = supportedVp8HwCodecPrefixes;
                str = "video/x-vnd.on2.vp8";
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
                supportedH264HwCodecPrefixes = supportedVp9HwCodecPrefixes;
                str = "video/x-vnd.on2.vp9";
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                supportedH264HwCodecPrefixes = supportedH264HwCodecPrefixes();
                str = "video/avc";
            } else {
                throw new RuntimeException("initDecode: Non-supported codec " + videoCodecType);
            }
            DecoderProperties findDecoder = findDecoder(str, supportedH264HwCodecPrefixes);
            if (findDecoder != null) {
                Logging.d(TAG, "Java initDecode: " + videoCodecType + " : " + i + " x " + i2 + ". Color: 0x" + Integer.toHexString(findDecoder.colorFormat) + ". Use Surface: " + this.useSurface);
                runningInstance = this;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    this.width = i;
                    this.height = i2;
                    this.stride = i;
                    this.sliceHeight = i2;
                    if (this.useSurface) {
                        this.textureListener = new TextureListener(surfaceTextureHelper);
                        this.surface = new Surface(surfaceTextureHelper.getSurfaceTexture());
                    }
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, i, i2);
                    if (!this.useSurface) {
                        createVideoFormat.setInteger("color-format", findDecoder.colorFormat);
                    }
                    Logging.d(TAG, "  Format: " + createVideoFormat + " codec: " + findDecoder.codecName);
                    this.mediaCodec = MediaCodecVideoEncoder.createByCodecName(findDecoder.codecName);
                    if (this.mediaCodec == null) {
                        Logging.e(TAG, "Can not create media decoder");
                        return false;
                    }
                    if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MTKH264-LowLatency").equals("Enabled") && videoCodecType == VideoCodecType.VIDEO_CODEC_H264 && findDecoder.codecName.startsWith("OMX.MTK.")) {
                        createVideoFormat.setInteger("vdec-lowlatency", 1);
                        Logging.d(TAG, "MTK HW H.264 decoder set low latency parameter");
                    }
                    if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264 && findDecoder.codecName.startsWith("OMX.MS.")) {
                        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MSH264-LowLatency").equals("Enabled")) {
                            createVideoFormat.setInteger("vdec-lowlatency", 1);
                            Logging.d(TAG, "MS HW H.264 decoder set low latency parameter");
                        }
                        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MSH264-AMZN-Decode").equals("Enabled")) {
                            createVideoFormat.setInteger("vendor.amazon.use-specific-decoder", 1);
                            Logging.d(TAG, "MS HW H.264 decoder set amazon.use-specific parameter");
                        }
                    }
                    this.mediaCodec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                    this.mediaCodec.start();
                    this.colorFormat = findDecoder.colorFormat;
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    this.inputBuffers = this.mediaCodec.getInputBuffers();
                    this.decodeStartTimeMs.clear();
                    this.hasDecodedFirstFrame = false;
                    this.dequeuedSurfaceOutputBuffers.clear();
                    this.droppedFrames = 0;
                    Logging.d(TAG, "Input buffers: " + this.inputBuffers.length + ". Output buffers: " + this.outputBuffers.length);
                    return true;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "initDecode failed", e);
                    return false;
                }
            }
            throw new RuntimeException("Cannot find HW decoder for " + videoCodecType);
        }
        throw new RuntimeException("initDecode: Forgot to release()?");
    }

    private static boolean isGoogleCodecUseRequested() {
        return PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-GoogleH264").equals("Enabled");
    }

    public static boolean isH264HighProfileHwSupported() {
        if (hwDecoderDisabledTypes.contains("video/avc")) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        if (findDecoderHelper("video/avc", new String[]{supportedQcomH264HighProfileHwCodecPrefix}) != null) {
            return true;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (findDecoderHelper("video/avc", new String[]{supportedExynosH264HighProfileHwCodecPrefix}) != null) {
            return true;
        }
        int i3 = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-KIRINH264").startsWith("Enabled")) {
            arrayList.add(KIRIN_H264_CODEC_PREFIX);
        } else if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-TENSOREXYNOSH264").startsWith("Enabled")) {
            arrayList.add(TENSOR_EXYNOS_H264_CODEC_PREFIX);
        }
        return arrayList.size() > 0 && findDecoderHelper("video/avc", (String[]) arrayList.toArray(new String[arrayList.size()])) != null;
    }

    public static boolean isH264HwSupported() {
        return !hwDecoderDisabledTypes.contains("video/avc") && findDecoder("video/avc", supportedH264HwCodecPrefixes()) != null;
    }

    public static boolean isVp8HwSupported() {
        return !hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp8") && findDecoder("video/x-vnd.on2.vp8", supportedVp8HwCodecPrefixes) != null;
    }

    public static boolean isVp9HwSupported() {
        return !hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp9") && findDecoder("video/x-vnd.on2.vp9", supportedVp9HwCodecPrefixes) != null;
    }

    public static void printStackTrace() {
        Thread thread;
        MediaCodecVideoDecoder mediaCodecVideoDecoder = runningInstance;
        if (mediaCodecVideoDecoder == null || (thread = mediaCodecVideoDecoder.mediaCodecThread) == null) {
            return;
        }
        StackTraceElement[] stackTrace = thread.getStackTrace();
        if (stackTrace.length <= 0) {
            return;
        }
        Logging.d(TAG, "MediaCodecVideoDecoder stacks trace:");
        for (StackTraceElement stackTraceElement : stackTrace) {
            Logging.d(TAG, stackTraceElement.toString());
        }
    }

    private boolean queueInputBuffer(int i, int i2, long j, long j2, long j3) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[i].position(0);
            this.inputBuffers[i].limit(i2);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), j2, j3));
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "decode failed", e);
            return false;
        }
    }

    private void release() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Java releaseDecoder. Total number of dropped frames: ");
        outline107.append(this.droppedFrames);
        Logging.d(TAG, outline107.toString());
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: org.webrtc.MediaCodecVideoDecoder.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000L)) {
            Logging.e(TAG, "Media decoder release timeout");
            codecErrors++;
            if (errorCallback != null) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Invoke codec error callback. Errors: ");
                outline1072.append(codecErrors);
                Logging.e(TAG, outline1072.toString());
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.d(TAG, "Java releaseDecoder done");
    }

    private void reset(int i, int i2) {
        if (this.mediaCodecThread != null && this.mediaCodec != null) {
            Logging.d(TAG, "Java reset: " + i + " x " + i2);
            this.mediaCodec.flush();
            this.width = i;
            this.height = i2;
            this.decodeStartTimeMs.clear();
            this.dequeuedSurfaceOutputBuffers.clear();
            this.hasDecodedFirstFrame = false;
            this.droppedFrames = 0;
            return;
        }
        throw new RuntimeException("Incorrect reset call for non-initialized decoder.");
    }

    private void returnDecodedOutputBuffer(int i) throws IllegalStateException, MediaCodec.CodecException {
        checkOnMediaCodecThread();
        if (!this.useSurface) {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return;
        }
        throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback mediaCodecVideoDecoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoDecoderErrorCallback;
    }

    public static void setMaxPendingFramesForH264(int i) {
        if (i >= 0 && i <= 100) {
            Logging.d(TAG, "max pending decoder frames will be set to: " + i);
            desiredMaxPendingFrame.set(i);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("setMaxPendingFrames cannot have negative, values less than default, or very large values. Value given= ", i));
    }

    private static final String[] supportedH264HwCodecPrefixes() {
        ArrayList outline128 = GeneratedOutlineSupport1.outline128(supportedQcomH264HighProfileHwCodecPrefix, "OMX.Intel.", supportedExynosH264HighProfileHwCodecPrefix);
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MTKH264").startsWith("Enabled")) {
            outline128.add("OMX.MTK.");
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-MSH264").equals("Enabled")) {
            outline128.add("OMX.MS.");
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-AMLOGICH264").startsWith("Enabled")) {
            outline128.add("OMX.amlogic.");
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-BRCMH264").equals("Enabled")) {
            outline128.add("OMX.brcm.");
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-NVIDIAH264").equals("Enabled")) {
            outline128.add("OMX.Nvidia.");
        }
        int i = Build.VERSION.SDK_INT;
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-KIRINH264").startsWith("Enabled")) {
            outline128.add(KIRIN_H264_CODEC_PREFIX);
        }
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-BERLINH264").startsWith("Enabled")) {
            outline128.add("OMX.Berlin.");
        }
        int i2 = Build.VERSION.SDK_INT;
        if (PeerConnectionFactory.fieldTrialsFindFullName("WebRTC-TENSOREXYNOSH264").startsWith("Enabled")) {
            outline128.add(TENSOR_EXYNOS_H264_CODEC_PREFIX);
        }
        return (String[]) outline128.toArray(new String[outline128.size()]);
    }

    public static boolean willH264UseGoogleCodec() {
        DecoderProperties findDecoder;
        if (!isGoogleCodecUseRequested() || hwDecoderDisabledTypes.contains("video/avc") || (findDecoder = findDecoder("video/avc", supportedH264HwCodecPrefixes())) == null) {
            return false;
        }
        return findDecoder.codecName.contains(googleCodecPrefix);
    }
}
