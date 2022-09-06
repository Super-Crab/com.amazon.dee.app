package com.google.android.exoplayer2.transformer;

import android.media.MediaCodec;
import android.media.MediaFormat;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.mediacodec.SynchronousMediaCodecAdapter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
/* loaded from: classes2.dex */
final class MediaCodecAdapterWrapper {
    private static final int MEDIA_CODEC_PCM_ENCODING = 2;
    private final MediaCodecAdapter codec;
    private boolean inputStreamEnded;
    @Nullable
    private ByteBuffer outputBuffer;
    private Format outputFormat;
    private boolean outputStreamEnded;
    private final MediaCodec.BufferInfo outputBufferInfo = new MediaCodec.BufferInfo();
    private int inputBufferIndex = -1;
    private int outputBufferIndex = -1;

    private MediaCodecAdapterWrapper(MediaCodecAdapter mediaCodecAdapter) {
        this.codec = mediaCodecAdapter;
    }

    public static MediaCodecAdapterWrapper createForAudioDecoding(Format format) throws IOException {
        MediaCodec mediaCodec;
        MediaFormat createAudioFormat;
        MediaCodecAdapter mo7293createAdapter;
        MediaCodecAdapter mediaCodecAdapter = null;
        try {
            mediaCodec = MediaCodec.createDecoderByType((String) Assertions.checkNotNull(format.sampleMimeType));
            try {
                createAudioFormat = MediaFormat.createAudioFormat(format.sampleMimeType, format.sampleRate, format.channelCount);
                MediaFormatUtil.maybeSetInteger(createAudioFormat, "max-input-size", format.maxInputSize);
                MediaFormatUtil.setCsdBuffers(createAudioFormat, format.initializationData);
                mo7293createAdapter = new SynchronousMediaCodecAdapter.Factory().mo7293createAdapter(mediaCodec);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            mediaCodec = null;
        }
        try {
            mo7293createAdapter.configure(createAudioFormat, null, null, 0);
            mo7293createAdapter.start();
            return new MediaCodecAdapterWrapper(mo7293createAdapter);
        } catch (Exception e3) {
            mediaCodecAdapter = mo7293createAdapter;
            e = e3;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.release();
            } else if (mediaCodec != null) {
                mediaCodec.release();
            }
            throw e;
        }
    }

    public static MediaCodecAdapterWrapper createForAudioEncoding(Format format) throws IOException {
        MediaCodec mediaCodec;
        MediaFormat createAudioFormat;
        MediaCodecAdapter mo7293createAdapter;
        MediaCodecAdapter mediaCodecAdapter = null;
        try {
            mediaCodec = MediaCodec.createEncoderByType((String) Assertions.checkNotNull(format.sampleMimeType));
            try {
                createAudioFormat = MediaFormat.createAudioFormat(format.sampleMimeType, format.sampleRate, format.channelCount);
                createAudioFormat.setInteger("bitrate", format.bitrate);
                mo7293createAdapter = new SynchronousMediaCodecAdapter.Factory().mo7293createAdapter(mediaCodec);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            mediaCodec = null;
        }
        try {
            mo7293createAdapter.configure(createAudioFormat, null, null, 1);
            mo7293createAdapter.start();
            return new MediaCodecAdapterWrapper(mo7293createAdapter);
        } catch (Exception e3) {
            mediaCodecAdapter = mo7293createAdapter;
            e = e3;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.release();
            } else if (mediaCodec != null) {
                mediaCodec.release();
            }
            throw e;
        }
    }

    private static Format getFormat(MediaFormat mediaFormat) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        int i = 0;
        while (true) {
            ByteBuffer byteBuffer = mediaFormat.getByteBuffer("csd-" + i);
            if (byteBuffer == null) {
                break;
            }
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            builder.mo7849add((ImmutableList.Builder) bArr);
            i++;
        }
        String string = mediaFormat.getString("mime");
        Format.Builder initializationData = new Format.Builder().setSampleMimeType(mediaFormat.getString("mime")).setInitializationData(builder.mo7852build());
        if (MimeTypes.isVideo(string)) {
            initializationData.setWidth(mediaFormat.getInteger("width")).setHeight(mediaFormat.getInteger("height"));
        } else if (MimeTypes.isAudio(string)) {
            initializationData.setChannelCount(mediaFormat.getInteger("channel-count")).setSampleRate(mediaFormat.getInteger("sample-rate")).setPcmEncoding(2);
        }
        return initializationData.build();
    }

    private boolean maybeDequeueOutputBuffer() {
        if (this.outputBufferIndex >= 0) {
            return true;
        }
        if (this.outputStreamEnded) {
            return false;
        }
        this.outputBufferIndex = this.codec.dequeueOutputBufferIndex(this.outputBufferInfo);
        int i = this.outputBufferIndex;
        if (i < 0) {
            if (i == -2) {
                this.outputFormat = getFormat(this.codec.getOutputFormat());
            }
            return false;
        }
        MediaCodec.BufferInfo bufferInfo = this.outputBufferInfo;
        if ((bufferInfo.flags & 4) != 0) {
            this.outputStreamEnded = true;
            if (bufferInfo.size == 0) {
                releaseOutputBuffer();
                return false;
            }
        }
        if ((this.outputBufferInfo.flags & 2) != 0) {
            releaseOutputBuffer();
            return false;
        }
        this.outputBuffer = (ByteBuffer) Assertions.checkNotNull(this.codec.getOutputBuffer(this.outputBufferIndex));
        this.outputBuffer.position(this.outputBufferInfo.offset);
        ByteBuffer byteBuffer = this.outputBuffer;
        MediaCodec.BufferInfo bufferInfo2 = this.outputBufferInfo;
        byteBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
        return true;
    }

    @Nullable
    public ByteBuffer getOutputBuffer() {
        if (maybeDequeueOutputBuffer()) {
            return this.outputBuffer;
        }
        return null;
    }

    @Nullable
    public MediaCodec.BufferInfo getOutputBufferInfo() {
        if (maybeDequeueOutputBuffer()) {
            return this.outputBufferInfo;
        }
        return null;
    }

    @Nullable
    public Format getOutputFormat() {
        maybeDequeueOutputBuffer();
        return this.outputFormat;
    }

    public boolean isEnded() {
        return this.outputStreamEnded && this.outputBufferIndex == -1;
    }

    @EnsuresNonNullIf(expression = {"#1.data"}, result = true)
    public boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (this.inputStreamEnded) {
            return false;
        }
        if (this.inputBufferIndex < 0) {
            this.inputBufferIndex = this.codec.dequeueInputBufferIndex();
            int i = this.inputBufferIndex;
            if (i < 0) {
                return false;
            }
            decoderInputBuffer.data = this.codec.getInputBuffer(i);
            decoderInputBuffer.clear();
        }
        Assertions.checkNotNull(decoderInputBuffer.data);
        return true;
    }

    public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        int i;
        int i2;
        Assertions.checkState(!this.inputStreamEnded, "Input buffer can not be queued after the input stream has ended.");
        ByteBuffer byteBuffer = decoderInputBuffer.data;
        int i3 = 0;
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            i = 0;
            i2 = 0;
        } else {
            i = decoderInputBuffer.data.position();
            i2 = decoderInputBuffer.data.remaining();
        }
        if (decoderInputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            i3 = 4;
        }
        this.codec.queueInputBuffer(this.inputBufferIndex, i, i2, decoderInputBuffer.timeUs, i3);
        this.inputBufferIndex = -1;
        decoderInputBuffer.data = null;
    }

    public void release() {
        this.outputBuffer = null;
        this.codec.release();
    }

    public void releaseOutputBuffer() {
        this.outputBuffer = null;
        this.codec.releaseOutputBuffer(this.outputBufferIndex, false);
        this.outputBufferIndex = -1;
    }
}
