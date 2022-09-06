package com.google.android.exoplayer2.transformer;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;
@RequiresApi(18)
/* loaded from: classes2.dex */
final class TransformerVideoRenderer extends TransformerBaseRenderer {
    private static final String TAG = "TransformerVideoRenderer";
    private final DecoderInputBuffer buffer;
    private boolean formatRead;
    private boolean isBufferPending;
    private boolean isInputStreamEnded;
    @Nullable
    private SampleTransformer sampleTransformer;

    public TransformerVideoRenderer(MuxerWrapper muxerWrapper, TransformerMediaClock transformerMediaClock, Transformation transformation) {
        super(2, muxerWrapper, transformerMediaClock, transformation);
        this.buffer = new DecoderInputBuffer(2);
    }

    private boolean readAndTransformBuffer() {
        this.buffer.clear();
        int readSource = readSource(getFormatHolder(), this.buffer, false);
        if (readSource != -5) {
            if (readSource == -3) {
                return false;
            }
            if (this.buffer.isEndOfStream()) {
                this.isInputStreamEnded = true;
                this.muxerWrapper.endTrack(getTrackType());
                return false;
            }
            this.mediaClock.updateTimeForTrackType(getTrackType(), this.buffer.timeUs);
            ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).flip();
            SampleTransformer sampleTransformer = this.sampleTransformer;
            if (sampleTransformer != null) {
                sampleTransformer.transformSample(this.buffer);
            }
            return true;
        }
        throw new IllegalStateException("Format changes are not supported.");
    }

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
    public String getName() {
        return TAG;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.isInputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) {
        if (!this.isRendererStarted || isEnded()) {
            return;
        }
        if (!this.formatRead) {
            FormatHolder formatHolder = getFormatHolder();
            if (readSource(formatHolder, this.buffer, true) != -5) {
                return;
            }
            Format format = (Format) Assertions.checkNotNull(formatHolder.format);
            this.formatRead = true;
            if (this.transformation.flattenForSlowMotion) {
                this.sampleTransformer = new SefSlowMotionVideoSampleTransformer(format);
            }
            this.muxerWrapper.addTrackFormat(format);
        }
        do {
            if (!this.isBufferPending && !readAndTransformBuffer()) {
                return;
            }
            MuxerWrapper muxerWrapper = this.muxerWrapper;
            int trackType = getTrackType();
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            this.isBufferPending = !muxerWrapper.writeSample(trackType, decoderInputBuffer.data, decoderInputBuffer.isKeyFrame(), this.buffer.timeUs);
        } while (!this.isBufferPending);
    }
}
