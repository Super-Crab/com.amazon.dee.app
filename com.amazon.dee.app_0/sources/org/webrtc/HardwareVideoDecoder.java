package org.webrtc;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import com.amazon.tarazed.activity.TarazedPrimerActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import org.webrtc.EglBase;
import org.webrtc.EncodedImage;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoFrame;
@TargetApi(16)
/* loaded from: classes5.dex */
class HardwareVideoDecoder implements VideoDecoder, SurfaceTextureHelper.OnTextureFrameAvailableListener {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String MEDIA_FORMAT_KEY_CROP_BOTTOM = "crop-bottom";
    private static final String MEDIA_FORMAT_KEY_CROP_LEFT = "crop-left";
    private static final String MEDIA_FORMAT_KEY_CROP_RIGHT = "crop-right";
    private static final String MEDIA_FORMAT_KEY_CROP_TOP = "crop-top";
    private static final String MEDIA_FORMAT_KEY_SLICE_HEIGHT = "slice-height";
    private static final String MEDIA_FORMAT_KEY_STRIDE = "stride";
    private static final String TAG = "HardwareVideoDecoder";
    private VideoDecoder.Callback callback;
    private final String codecName;
    private final VideoCodecType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final BlockingDeque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private int height;
    private boolean keyFrameRequired;
    private Thread outputThread;
    private ThreadUtils.ThreadChecker outputThreadChecker;
    private DecodedTextureMetadata renderedTextureMetadata;
    private final EglBase.Context sharedContext;
    private int sliceHeight;
    private int stride;
    private SurfaceTextureHelper surfaceTextureHelper;
    private int width;
    private volatile boolean running = false;
    private volatile Exception shutdownException = null;
    private final Object dimensionLock = new Object();
    private Surface surface = null;
    private Object renderedTextureMetadataLock = new Object();
    private MediaCodec codec = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DecodedTextureMetadata {
        final Integer decodeTimeMs;
        final int height;
        final long presentationTimestampUs;
        final int rotation;
        final int width;

        DecodedTextureMetadata(int i, int i2, int i3, long j, Integer num) {
            this.width = i;
            this.height = i2;
            this.rotation = i3;
            this.presentationTimestampUs = j;
            this.decodeTimeMs = num;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        FrameInfo(long j, int i) {
            this.decodeStartTimeMs = j;
            this.rotation = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareVideoDecoder(String str, VideoCodecType videoCodecType, int i, EglBase.Context context) {
        if (isSupportedColorFormat(i)) {
            this.codecName = str;
            this.codecType = videoCodecType;
            this.colorFormat = i;
            this.sharedContext = context;
            this.frameInfos = new LinkedBlockingDeque();
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unsupported color format: ", i));
    }

    private VideoFrame.Buffer copyI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        int i5 = i / 2;
        int i6 = (i * i2) + 0;
        int i7 = (i2 / 2) * i5;
        int i8 = i6 + i7;
        int i9 = ((i5 * i2) / 2) + i6;
        int i10 = i7 + i9;
        JavaI420Buffer allocate = JavaI420Buffer.allocate(i3, i4);
        ByteBuffer dataY = allocate.getDataY();
        byteBuffer.position(0);
        byteBuffer.limit(i6);
        dataY.put(byteBuffer);
        ByteBuffer dataU = allocate.getDataU();
        byteBuffer.position(i6);
        byteBuffer.limit(i8);
        dataU.put(byteBuffer);
        int i11 = i2 % 2;
        if (i11 != 0) {
            byteBuffer.position(i8 - i5);
            dataU.put(byteBuffer);
        }
        ByteBuffer dataV = allocate.getDataV();
        byteBuffer.position(i9);
        byteBuffer.limit(i10);
        dataV.put(byteBuffer);
        if (i11 != 0) {
            byteBuffer.position(i10 - i5);
            dataV.put(byteBuffer);
        }
        return allocate;
    }

    private VideoFrame.Buffer copyNV12ToI420Buffer(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        return new NV12Buffer(i3, i4, i, i2, byteBuffer, null).toI420();
    }

    private Thread createOutputThread() {
        return new Thread("HardwareVideoDecoder.outputThread") { // from class: org.webrtc.HardwareVideoDecoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                HardwareVideoDecoder.this.outputThreadChecker = new ThreadUtils.ThreadChecker();
                while (HardwareVideoDecoder.this.running) {
                    HardwareVideoDecoder.this.deliverDecodedFrame();
                }
                HardwareVideoDecoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    private void deliverByteFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        int i5;
        int i6;
        VideoFrame.Buffer copyNV12ToI420Buffer;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
            i5 = this.stride;
            i6 = this.sliceHeight;
        }
        int i7 = bufferInfo.size;
        if (i7 < ((i3 * i4) * 3) / 2) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Insufficient output buffer size: ");
            outline107.append(bufferInfo.size);
            Logging.e(TAG, outline107.toString());
            return;
        }
        int i8 = (i7 >= ((i5 * i4) * 3) / 2 || i6 != i4 || i5 <= i3) ? i5 : (i7 * 2) / (i4 * 3);
        ByteBuffer byteBuffer = this.codec.getOutputBuffers()[i];
        byteBuffer.position(bufferInfo.offset);
        byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
        ByteBuffer slice = byteBuffer.slice();
        if (this.colorFormat == 19) {
            copyNV12ToI420Buffer = copyI420Buffer(slice, i8, i6, i3, i4);
        } else {
            copyNV12ToI420Buffer = copyNV12ToI420Buffer(slice, i8, i6, i3, i4);
        }
        this.codec.releaseOutputBuffer(i, false);
        VideoFrame videoFrame = new VideoFrame(copyNV12ToI420Buffer, i2, bufferInfo.presentationTimeUs * 1000);
        this.callback.onDecodedFrame(videoFrame, num, null);
        videoFrame.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deliverDecodedFrame() {
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, TarazedPrimerActivity.PRIMER_FROZEN_TIMEOUT_MS);
            if (dequeueOutputBuffer == -2) {
                reformat(this.codec.getOutputFormat());
            } else if (dequeueOutputBuffer < 0) {
                Logging.v(TAG, "dequeueOutputBuffer returned " + dequeueOutputBuffer);
            } else {
                FrameInfo poll = this.frameInfos.poll();
                Integer num = null;
                int i = 0;
                if (poll != null) {
                    num = Integer.valueOf((int) (SystemClock.elapsedRealtime() - poll.decodeStartTimeMs));
                    i = poll.rotation;
                }
                this.hasDecodedFirstFrame = true;
                if (this.surfaceTextureHelper != null) {
                    deliverTextureFrame(dequeueOutputBuffer, bufferInfo, i, num);
                } else {
                    deliverByteFrame(dequeueOutputBuffer, bufferInfo, i, num);
                }
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "deliverDecodedFrame failed", e);
        }
    }

    private void deliverTextureFrame(int i, MediaCodec.BufferInfo bufferInfo, int i2, Integer num) {
        int i3;
        int i4;
        synchronized (this.dimensionLock) {
            i3 = this.width;
            i4 = this.height;
        }
        synchronized (this.renderedTextureMetadataLock) {
            if (this.renderedTextureMetadata != null) {
                return;
            }
            this.renderedTextureMetadata = new DecodedTextureMetadata(i3, i4, i2, bufferInfo.presentationTimeUs, num);
            this.codec.releaseOutputBuffer(i, true);
        }
    }

    private VideoCodecStatus initDecodeInternal(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "initDecodeInternal");
        if (this.outputThread != null) {
            Logging.e(TAG, "initDecodeInternal called while the codec is already running");
            return VideoCodecStatus.ERROR;
        }
        this.width = i;
        this.height = i2;
        this.stride = i;
        this.sliceHeight = i2;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        try {
            this.codec = MediaCodec.createByCodecName(this.codecName);
            try {
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.codecType.mimeType(), i, i2);
                if (this.sharedContext == null) {
                    createVideoFormat.setInteger("color-format", this.colorFormat);
                }
                this.codec.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                this.codec.start();
                this.running = true;
                this.outputThread = createOutputThread();
                this.outputThread.start();
                Logging.d(TAG, "initDecodeInternal done");
                return VideoCodecStatus.OK;
            } catch (IllegalStateException e) {
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.ERROR;
            }
        } catch (IOException | IllegalArgumentException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot create media decoder ");
            outline107.append(this.codecName);
            Logging.e(TAG, outline107.toString());
            return VideoCodecStatus.ERROR;
        }
    }

    private boolean isSupportedColorFormat(int i) {
        for (int i2 : MediaCodecUtils.DECODER_COLOR_FORMATS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void reformat(MediaFormat mediaFormat) {
        int integer;
        int integer2;
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Decoder format changed: " + mediaFormat.toString());
        if (mediaFormat.containsKey(MEDIA_FORMAT_KEY_CROP_LEFT) && mediaFormat.containsKey(MEDIA_FORMAT_KEY_CROP_RIGHT) && mediaFormat.containsKey(MEDIA_FORMAT_KEY_CROP_BOTTOM) && mediaFormat.containsKey(MEDIA_FORMAT_KEY_CROP_TOP)) {
            integer = (mediaFormat.getInteger(MEDIA_FORMAT_KEY_CROP_RIGHT) + 1) - mediaFormat.getInteger(MEDIA_FORMAT_KEY_CROP_LEFT);
            integer2 = (mediaFormat.getInteger(MEDIA_FORMAT_KEY_CROP_BOTTOM) + 1) - mediaFormat.getInteger(MEDIA_FORMAT_KEY_CROP_TOP);
        } else {
            integer = mediaFormat.getInteger("width");
            integer2 = mediaFormat.getInteger("height");
        }
        synchronized (this.dimensionLock) {
            if (this.hasDecodedFirstFrame && (this.width != integer || this.height != integer2)) {
                stopOnOutputThread(new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + integer + "*" + integer2));
                return;
            }
            this.width = integer;
            this.height = integer2;
            if (this.surfaceTextureHelper == null && mediaFormat.containsKey("color-format")) {
                this.colorFormat = mediaFormat.getInteger("color-format");
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Color: 0x");
                outline107.append(Integer.toHexString(this.colorFormat));
                Logging.d(TAG, outline107.toString());
                if (!isSupportedColorFormat(this.colorFormat)) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unsupported color format: ");
                    outline1072.append(this.colorFormat);
                    stopOnOutputThread(new IllegalStateException(outline1072.toString()));
                    return;
                }
            }
            synchronized (this.dimensionLock) {
                if (mediaFormat.containsKey(MEDIA_FORMAT_KEY_STRIDE)) {
                    this.stride = mediaFormat.getInteger(MEDIA_FORMAT_KEY_STRIDE);
                }
                if (mediaFormat.containsKey(MEDIA_FORMAT_KEY_SLICE_HEIGHT)) {
                    this.sliceHeight = mediaFormat.getInteger(MEDIA_FORMAT_KEY_SLICE_HEIGHT);
                }
                Logging.d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                this.stride = Math.max(this.width, this.stride);
                this.sliceHeight = Math.max(this.height, this.sliceHeight);
            }
        }
    }

    private VideoCodecStatus reinitDecode(int i, int i2) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus releaseInternal = releaseInternal();
        return releaseInternal != VideoCodecStatus.OK ? releaseInternal : initDecodeInternal(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        try {
            this.codec.stop();
        } catch (Exception e) {
            Logging.e(TAG, "Media decoder stop failed", e);
        }
        try {
            this.codec.release();
        } catch (Exception e2) {
            Logging.e(TAG, "Media decoder release failed", e2);
            this.shutdownException = e2;
        }
        Logging.d(TAG, "Release on output thread done");
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [android.media.MediaCodec, java.lang.Thread] */
    private VideoCodecStatus releaseInternal() {
        if (!this.running) {
            Logging.d(TAG, "release: Decoder is not running.");
            return VideoCodecStatus.OK;
        }
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000L)) {
                Logging.e(TAG, "Media decoder release timeout", new RuntimeException());
                return VideoCodecStatus.TIMEOUT;
            } else if (this.shutdownException != null) {
                Logging.e(TAG, "Media decoder release error", new RuntimeException(this.shutdownException));
                this.shutdownException = null;
                return VideoCodecStatus.ERROR;
            } else {
                this.codec = null;
                this.outputThread = null;
                return VideoCodecStatus.OK;
            }
        } finally {
            this.codec = null;
            this.outputThread = null;
        }
    }

    private void stopOnOutputThread(Exception exc) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = exc;
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        int i;
        int i2;
        VideoCodecStatus reinitDecode;
        this.decoderThreadChecker.checkIsOnValidThread();
        if (this.codec != null && this.callback != null) {
            ByteBuffer byteBuffer = encodedImage.buffer;
            if (byteBuffer == null) {
                Logging.e(TAG, "decode() - no input data");
                return VideoCodecStatus.ERR_PARAMETER;
            }
            int remaining = byteBuffer.remaining();
            if (remaining == 0) {
                Logging.e(TAG, "decode() - input buffer empty");
                return VideoCodecStatus.ERR_PARAMETER;
            }
            synchronized (this.dimensionLock) {
                i = this.width;
                i2 = this.height;
            }
            int i3 = encodedImage.encodedWidth;
            int i4 = encodedImage.encodedHeight;
            if (i3 * i4 > 0 && ((i3 != i || i4 != i2) && (reinitDecode = reinitDecode(encodedImage.encodedWidth, encodedImage.encodedHeight)) != VideoCodecStatus.OK)) {
                return reinitDecode;
            }
            if (this.keyFrameRequired) {
                if (encodedImage.frameType != EncodedImage.FrameType.VideoFrameKey) {
                    Logging.e(TAG, "decode() - key frame required first");
                    return VideoCodecStatus.ERROR;
                } else if (!encodedImage.completeFrame) {
                    Logging.e(TAG, "decode() - complete frame required first");
                    return VideoCodecStatus.ERROR;
                }
            }
            try {
                int dequeueInputBuffer = this.codec.dequeueInputBuffer(500000L);
                if (dequeueInputBuffer < 0) {
                    Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
                    return VideoCodecStatus.ERROR;
                }
                try {
                    ByteBuffer byteBuffer2 = this.codec.getInputBuffers()[dequeueInputBuffer];
                    if (byteBuffer2.capacity() < remaining) {
                        Logging.e(TAG, "decode() - HW buffer too small");
                        return VideoCodecStatus.ERROR;
                    }
                    byteBuffer2.put(encodedImage.buffer);
                    this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), encodedImage.rotation));
                    try {
                        this.codec.queueInputBuffer(dequeueInputBuffer, 0, remaining, TimeUnit.NANOSECONDS.toMicros(encodedImage.captureTimeNs), 0);
                        if (this.keyFrameRequired) {
                            this.keyFrameRequired = false;
                        }
                        return VideoCodecStatus.OK;
                    } catch (IllegalStateException e) {
                        Logging.e(TAG, "queueInputBuffer failed", e);
                        this.frameInfos.pollLast();
                        return VideoCodecStatus.ERROR;
                    }
                } catch (IllegalStateException e2) {
                    Logging.e(TAG, "getInputBuffers failed", e2);
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e3) {
                Logging.e(TAG, "dequeueInputBuffer failed", e3);
                return VideoCodecStatus.ERROR;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("decode uninitalized, codec: ");
        outline107.append(this.codec);
        outline107.append(", callback: ");
        outline107.append(this.callback);
        Logging.d(TAG, outline107.toString());
        return VideoCodecStatus.UNINITIALIZED;
    }

    @Override // org.webrtc.VideoDecoder
    public String getImplementationName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HardwareVideoDecoder: ");
        outline107.append(this.codecName);
        return outline107.toString();
    }

    @Override // org.webrtc.VideoDecoder
    public boolean getPrefersLateDecoding() {
        return true;
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        this.callback = callback;
        EglBase.Context context = this.sharedContext;
        if (context != null) {
            this.surfaceTextureHelper = SurfaceTextureHelper.create("decoder-texture-thread", context);
            this.surface = new Surface(this.surfaceTextureHelper.getSurfaceTexture());
            this.surfaceTextureHelper.startListening(this);
        }
        return initDecodeInternal(settings.width, settings.height);
    }

    @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
        VideoFrame videoFrame;
        int intValue;
        synchronized (this.renderedTextureMetadataLock) {
            if (this.renderedTextureMetadata != null) {
                videoFrame = new VideoFrame(this.surfaceTextureHelper.createTextureBuffer(this.renderedTextureMetadata.width, this.renderedTextureMetadata.height, RendererCommon.convertMatrixToAndroidGraphicsMatrix(fArr)), this.renderedTextureMetadata.rotation, this.renderedTextureMetadata.presentationTimestampUs * 1000);
                intValue = this.renderedTextureMetadata.decodeTimeMs.intValue();
                this.renderedTextureMetadata = null;
            } else {
                throw new IllegalStateException("Rendered texture metadata was null in onTextureFrameAvailable.");
            }
        }
        this.callback.onDecodedFrame(videoFrame, Integer.valueOf(intValue), null);
        videoFrame.release();
    }

    @Override // org.webrtc.VideoDecoder
    public VideoCodecStatus release() {
        Logging.d(TAG, "release");
        VideoCodecStatus releaseInternal = releaseInternal();
        Surface surface = this.surface;
        if (surface != null) {
            surface.release();
            this.surface = null;
            this.surfaceTextureHelper.stopListening();
            this.surfaceTextureHelper.dispose();
            this.surfaceTextureHelper = null;
        }
        synchronized (this.renderedTextureMetadataLock) {
            this.renderedTextureMetadata = null;
        }
        this.callback = null;
        this.frameInfos.clear();
        return releaseInternal;
    }
}
