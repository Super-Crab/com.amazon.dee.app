package com.google.android.exoplayer2.transformer;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.mp4.SlowMotionData;
import com.google.android.exoplayer2.metadata.mp4.SmtaMetadataEntry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
final class SefSlowMotionVideoSampleTransformer implements SampleTransformer {
    @VisibleForTesting
    static final int INPUT_FRAME_RATE = 30;
    private static final int NAL_START_CODE_LENGTH = NalUnitUtil.NAL_START_CODE.length;
    private static final int NAL_UNIT_TYPE_PREFIX = 14;
    private static final int TARGET_OUTPUT_FRAME_RATE = 30;
    private final float captureFrameRate;
    @Nullable
    private SegmentInfo currentSegmentInfo;
    private long frameTimeDeltaUs;
    private final int inputMaxLayer;
    @Nullable
    private SegmentInfo nextSegmentInfo;
    private final int normalSpeedMaxLayer;
    private final byte[] scratch = new byte[NAL_START_CODE_LENGTH];
    private final Iterator<SlowMotionData.Segment> segmentIterator;
    @Nullable
    private final SlowMotionData slowMotionData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class MetadataInfo {
        public float captureFrameRate = -3.4028235E38f;
        public int inputMaxLayer = -1;
        public int normalSpeedMaxLayer = -1;
        @Nullable
        public SlowMotionData slowMotionData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SegmentInfo {
        public final long endTimeUs;
        public final int maxLayer;
        public final int speedDivisor;
        public final long startTimeUs;

        public SegmentInfo(SlowMotionData.Segment segment, int i, int i2) {
            this.startTimeUs = C.msToUs(segment.startTimeMs);
            this.endTimeUs = C.msToUs(segment.endTimeMs);
            this.speedDivisor = segment.speedDivisor;
            this.maxLayer = getSlowMotionMaxLayer(this.speedDivisor, i, i2);
        }

        private static int getSlowMotionMaxLayer(int i, int i2, int i3) {
            int i4 = i3;
            int i5 = i;
            while (true) {
                if (i5 <= 0) {
                    break;
                }
                boolean z = true;
                if ((i5 & 1) == 1) {
                    if ((i5 >> 1) != 0) {
                        z = false;
                    }
                    Assertions.checkState(z, "Invalid speed divisor: " + i);
                } else {
                    i4++;
                    i5 >>= 1;
                }
            }
            return Math.min(i4, i2);
        }
    }

    public SefSlowMotionVideoSampleTransformer(Format format) {
        MetadataInfo metadataInfo = getMetadataInfo(format.metadata);
        this.slowMotionData = metadataInfo.slowMotionData;
        SlowMotionData slowMotionData = this.slowMotionData;
        this.segmentIterator = (slowMotionData != null ? slowMotionData.segments : ImmutableList.of()).iterator();
        this.captureFrameRate = metadataInfo.captureFrameRate;
        this.inputMaxLayer = metadataInfo.inputMaxLayer;
        this.normalSpeedMaxLayer = metadataInfo.normalSpeedMaxLayer;
        this.nextSegmentInfo = this.segmentIterator.hasNext() ? new SegmentInfo(this.segmentIterator.next(), this.inputMaxLayer, this.normalSpeedMaxLayer) : null;
        if (this.slowMotionData != null) {
            boolean equals = "video/avc".equals(format.sampleMimeType);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported MIME type for SEF slow motion video track: ");
            outline107.append(format.sampleMimeType);
            Assertions.checkArgument(equals, outline107.toString());
        }
    }

    private void enterNextSegment() {
        if (this.currentSegmentInfo != null) {
            leaveCurrentSegment();
        }
        this.currentSegmentInfo = this.nextSegmentInfo;
        this.nextSegmentInfo = this.segmentIterator.hasNext() ? new SegmentInfo(this.segmentIterator.next(), this.inputMaxLayer, this.normalSpeedMaxLayer) : null;
    }

    private static MetadataInfo getMetadataInfo(@Nullable Metadata metadata) {
        MetadataInfo metadataInfo = new MetadataInfo();
        if (metadata == null) {
            return metadataInfo;
        }
        boolean z = false;
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof SmtaMetadataEntry) {
                SmtaMetadataEntry smtaMetadataEntry = (SmtaMetadataEntry) entry;
                metadataInfo.captureFrameRate = smtaMetadataEntry.captureFrameRate;
                metadataInfo.inputMaxLayer = smtaMetadataEntry.svcTemporalLayerCount - 1;
            } else if (entry instanceof SlowMotionData) {
                metadataInfo.slowMotionData = (SlowMotionData) entry;
            }
        }
        if (metadataInfo.slowMotionData == null) {
            return metadataInfo;
        }
        Assertions.checkState(metadataInfo.inputMaxLayer != -1, "SVC temporal layer count not found.");
        Assertions.checkState(metadataInfo.captureFrameRate != -3.4028235E38f, "Capture frame rate not found.");
        float f = metadataInfo.captureFrameRate;
        boolean z2 = f % 1.0f == 0.0f && f % 30.0f == 0.0f;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid capture frame rate: ");
        outline107.append(metadataInfo.captureFrameRate);
        Assertions.checkState(z2, outline107.toString());
        int i2 = ((int) metadataInfo.captureFrameRate) / 30;
        int i3 = metadataInfo.inputMaxLayer;
        while (true) {
            if (i3 < 0) {
                break;
            } else if ((i2 & 1) == 1) {
                if ((i2 >> 1) == 0) {
                    z = true;
                }
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Could not compute normal speed max SVC layer for capture frame rate  ");
                outline1072.append(metadataInfo.captureFrameRate);
                Assertions.checkState(z, outline1072.toString());
                metadataInfo.normalSpeedMaxLayer = i3;
            } else {
                i2 >>= 1;
                i3--;
            }
        }
        return metadataInfo;
    }

    @RequiresNonNull({"currentSegmentInfo"})
    private void leaveCurrentSegment() {
        long j = this.frameTimeDeltaUs;
        SegmentInfo segmentInfo = this.currentSegmentInfo;
        this.frameTimeDeltaUs = ((segmentInfo.endTimeUs - segmentInfo.startTimeUs) * (segmentInfo.speedDivisor - 1)) + j;
        this.currentSegmentInfo = null;
    }

    private boolean shouldKeepFrameForOutputValidity(int i, long j) {
        int i2;
        SegmentInfo segmentInfo = this.nextSegmentInfo;
        if (segmentInfo != null && i < (i2 = segmentInfo.maxLayer)) {
            long j2 = ((segmentInfo.startTimeUs - j) * 30) / 1000000;
            float f = (-(1 << (this.inputMaxLayer - i2))) + 0.45f;
            for (int i3 = 1; i3 < this.nextSegmentInfo.maxLayer && ((float) j2) < (1 << (this.inputMaxLayer - i3)) + f; i3++) {
                if (i <= i3) {
                    return true;
                }
            }
        }
        return false;
    }

    private void skipToNextNalUnit(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        while (true) {
            int remaining = byteBuffer.remaining();
            int i = NAL_START_CODE_LENGTH;
            if (remaining >= i) {
                byteBuffer.get(this.scratch, 0, i);
                if (Arrays.equals(this.scratch, NalUnitUtil.NAL_START_CODE)) {
                    byteBuffer.position(position);
                    return;
                } else {
                    position++;
                    byteBuffer.position(position);
                }
            } else {
                throw new IllegalStateException("Could not find NAL unit start code.");
            }
        }
    }

    @VisibleForTesting
    long getCurrentFrameOutputTimeUs(long j) {
        long j2 = this.frameTimeDeltaUs + j;
        SegmentInfo segmentInfo = this.currentSegmentInfo;
        if (segmentInfo != null) {
            j2 += (j - segmentInfo.startTimeUs) * (segmentInfo.speedDivisor - 1);
        }
        return Math.round(((float) (j2 * 30)) / this.captureFrameRate);
    }

    @VisibleForTesting
    boolean processCurrentFrame(int i, long j) {
        while (true) {
            SegmentInfo segmentInfo = this.nextSegmentInfo;
            if (segmentInfo == null || j < segmentInfo.endTimeUs) {
                break;
            }
            enterNextSegment();
        }
        SegmentInfo segmentInfo2 = this.nextSegmentInfo;
        if (segmentInfo2 != null && j >= segmentInfo2.startTimeUs) {
            enterNextSegment();
        } else {
            SegmentInfo segmentInfo3 = this.currentSegmentInfo;
            if (segmentInfo3 != null && j >= segmentInfo3.endTimeUs) {
                leaveCurrentSegment();
            }
        }
        SegmentInfo segmentInfo4 = this.currentSegmentInfo;
        return i <= (segmentInfo4 != null ? segmentInfo4.maxLayer : this.normalSpeedMaxLayer) || shouldKeepFrameForOutputValidity(i, j);
    }

    @Override // com.google.android.exoplayer2.transformer.SampleTransformer
    public void transformSample(DecoderInputBuffer decoderInputBuffer) {
        if (this.slowMotionData == null) {
            return;
        }
        ByteBuffer byteBuffer = (ByteBuffer) Util.castNonNull(decoderInputBuffer.data);
        byteBuffer.position(byteBuffer.position() + NAL_START_CODE_LENGTH);
        boolean z = false;
        byteBuffer.get(this.scratch, 0, 4);
        byte[] bArr = this.scratch;
        int i = bArr[0] & 31;
        boolean z2 = ((bArr[1] & 255) >> 7) == 1;
        if (i == 14 && z2) {
            z = true;
        }
        Assertions.checkState(z, "Missing SVC extension prefix NAL unit.");
        if (processCurrentFrame((this.scratch[3] & 255) >> 5, decoderInputBuffer.timeUs)) {
            decoderInputBuffer.timeUs = getCurrentFrameOutputTimeUs(decoderInputBuffer.timeUs);
            skipToNextNalUnit(byteBuffer);
            return;
        }
        decoderInputBuffer.data = null;
    }
}
