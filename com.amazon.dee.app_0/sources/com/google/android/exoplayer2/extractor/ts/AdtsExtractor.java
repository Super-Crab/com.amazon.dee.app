package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = $$Lambda$AdtsExtractor$cqGYwjddB4W6E3ogPGiWfjTa23c.INSTANCE;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int MAX_PACKET_SIZE = 2048;
    private static final int MAX_SNIFF_BYTES = 8192;
    private static final int NUM_FRAMES_FOR_AVERAGE_FRAME_SIZE = 1000;
    private int averageFrameSize;
    private ExtractorOutput extractorOutput;
    private long firstFramePosition;
    private long firstSampleTimestampUs;
    private final int flags;
    private boolean hasCalculatedAverageFrameSize;
    private boolean hasOutputSeekMap;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private final ParsableByteArray scratch;
    private final ParsableBitArray scratchBits;
    private boolean startedPacket;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flags {
    }

    public AdtsExtractor() {
        this(0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x006d, code lost:
        r9.hasCalculatedAverageFrameSize = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0076, code lost:
        throw new com.google.android.exoplayer2.ParserException("Malformed ADTS stream");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void calculateAverageFrameSize(com.google.android.exoplayer2.extractor.ExtractorInput r10) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.hasCalculatedAverageFrameSize
            if (r0 == 0) goto L5
            return
        L5:
            r0 = -1
            r9.averageFrameSize = r0
            r10.resetPeekPosition()
            long r1 = r10.getPosition()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L18
            r9.peekId3Header(r10)
        L18:
            r1 = 0
            r2 = r1
        L1a:
            r5 = 1
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r9.scratch     // Catch: java.io.EOFException -> L77
            byte[] r6 = r6.getData()     // Catch: java.io.EOFException -> L77
            r7 = 2
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch: java.io.EOFException -> L77
            if (r6 == 0) goto L77
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r9.scratch     // Catch: java.io.EOFException -> L77
            r6.setPosition(r1)     // Catch: java.io.EOFException -> L77
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r9.scratch     // Catch: java.io.EOFException -> L77
            int r6 = r6.readUnsignedShort()     // Catch: java.io.EOFException -> L77
            boolean r6 = com.google.android.exoplayer2.extractor.ts.AdtsReader.isAdtsSyncWord(r6)     // Catch: java.io.EOFException -> L77
            if (r6 != 0) goto L3b
            r2 = r1
            goto L77
        L3b:
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r9.scratch     // Catch: java.io.EOFException -> L77
            byte[] r6 = r6.getData()     // Catch: java.io.EOFException -> L77
            r7 = 4
            boolean r6 = r10.peekFully(r6, r1, r7, r5)     // Catch: java.io.EOFException -> L77
            if (r6 != 0) goto L49
            goto L77
        L49:
            com.google.android.exoplayer2.util.ParsableBitArray r6 = r9.scratchBits     // Catch: java.io.EOFException -> L77
            r7 = 14
            r6.setPosition(r7)     // Catch: java.io.EOFException -> L77
            com.google.android.exoplayer2.util.ParsableBitArray r6 = r9.scratchBits     // Catch: java.io.EOFException -> L77
            r7 = 13
            int r6 = r6.readBits(r7)     // Catch: java.io.EOFException -> L77
            r7 = 6
            if (r6 <= r7) goto L6d
            long r7 = (long) r6     // Catch: java.io.EOFException -> L77
            long r3 = r3 + r7
            int r2 = r2 + 1
            r7 = 1000(0x3e8, float:1.401E-42)
            if (r2 != r7) goto L64
            goto L77
        L64:
            int r6 = r6 + (-6)
            boolean r6 = r10.advancePeekPosition(r6, r5)     // Catch: java.io.EOFException -> L77
            if (r6 != 0) goto L1a
            goto L77
        L6d:
            r9.hasCalculatedAverageFrameSize = r5     // Catch: java.io.EOFException -> L77
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException     // Catch: java.io.EOFException -> L77
            java.lang.String r6 = "Malformed ADTS stream"
            r1.<init>(r6)     // Catch: java.io.EOFException -> L77
            throw r1     // Catch: java.io.EOFException -> L77
        L77:
            r10.resetPeekPosition()
            if (r2 <= 0) goto L82
            long r0 = (long) r2
            long r3 = r3 / r0
            int r10 = (int) r3
            r9.averageFrameSize = r10
            goto L84
        L82:
            r9.averageFrameSize = r0
        L84:
            r9.hasCalculatedAverageFrameSize = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.AdtsExtractor.calculateAverageFrameSize(com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    private static int getBitrateFromFrameSize(int i, long j) {
        return (int) (((i * 8) * 1000000) / j);
    }

    private SeekMap getConstantBitrateSeekMap(long j) {
        return new ConstantBitrateSeekMap(j, this.firstFramePosition, getBitrateFromFrameSize(this.averageFrameSize, this.reader.getSampleDurationUs()), this.averageFrameSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AdtsExtractor()};
    }

    @RequiresNonNull({"extractorOutput"})
    private void maybeOutputSeekMap(long j, boolean z, boolean z2) {
        if (this.hasOutputSeekMap) {
            return;
        }
        boolean z3 = z && this.averageFrameSize > 0;
        if (z3 && this.reader.getSampleDurationUs() == C.TIME_UNSET && !z2) {
            return;
        }
        if (z3 && this.reader.getSampleDurationUs() != C.TIME_UNSET) {
            this.extractorOutput.seekMap(getConstantBitrateSeekMap(j));
        } else {
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        }
        this.hasOutputSeekMap = true;
    }

    private int peekId3Header(ExtractorInput extractorInput) throws IOException {
        int i = 0;
        while (true) {
            extractorInput.peekFully(this.scratch.getData(), 0, 10);
            this.scratch.setPosition(0);
            if (this.scratch.readUnsignedInt24() != 4801587) {
                break;
            }
            this.scratch.skipBytes(3);
            int readSynchSafeInt = this.scratch.readSynchSafeInt();
            i += readSynchSafeInt + 10;
            extractorInput.advancePeekPosition(readSynchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        if (this.firstFramePosition == -1) {
            this.firstFramePosition = i;
        }
        return i;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        Assertions.checkStateNotNull(this.extractorOutput);
        long length = extractorInput.getLength();
        boolean z = ((this.flags & 1) == 0 || length == -1) ? false : true;
        if (z) {
            calculateAverageFrameSize(extractorInput);
        }
        int read = extractorInput.read(this.packetBuffer.getData(), 0, 2048);
        boolean z2 = read == -1;
        maybeOutputSeekMap(length, z, z2);
        if (z2) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(read);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, 4);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
        this.firstSampleTimestampUs = j2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:
        r9.resetPeekPosition();
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002c, code lost:
        if ((r3 - r0) < 8192) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:
        return false;
     */
    @Override // com.google.android.exoplayer2.extractor.Extractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean sniff(com.google.android.exoplayer2.extractor.ExtractorInput r9) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r8.peekId3Header(r9)
            r1 = 0
            r3 = r0
        L6:
            r2 = r1
            r4 = r2
        L8:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.scratch
            byte[] r5 = r5.getData()
            r6 = 2
            r9.peekFully(r5, r1, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.scratch
            r5.setPosition(r1)
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.scratch
            int r5 = r5.readUnsignedShort()
            boolean r5 = com.google.android.exoplayer2.extractor.ts.AdtsReader.isAdtsSyncWord(r5)
            if (r5 != 0) goto L33
            r9.resetPeekPosition()
            int r3 = r3 + 1
            int r2 = r3 - r0
            r4 = 8192(0x2000, float:1.14794E-41)
            if (r2 < r4) goto L2f
            return r1
        L2f:
            r9.advancePeekPosition(r3)
            goto L6
        L33:
            r5 = 1
            int r2 = r2 + r5
            r6 = 4
            if (r2 < r6) goto L3d
            r7 = 188(0xbc, float:2.63E-43)
            if (r4 <= r7) goto L3d
            return r5
        L3d:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.scratch
            byte[] r5 = r5.getData()
            r9.peekFully(r5, r1, r6)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r8.scratchBits
            r6 = 14
            r5.setPosition(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r8.scratchBits
            r6 = 13
            int r5 = r5.readBits(r6)
            r6 = 6
            if (r5 > r6) goto L59
            return r1
        L59:
            int r6 = r5 + (-6)
            r9.advancePeekPosition(r6)
            int r4 = r4 + r5
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.AdtsExtractor.sniff(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }

    public AdtsExtractor(int i) {
        this(i, null, null);
    }

    public AdtsExtractor(byte[] bArr, byte[] bArr2) {
        this(0, bArr, bArr2);
    }

    public AdtsExtractor(int i, byte[] bArr, byte[] bArr2) {
        this.flags = i;
        this.reader = new AdtsReader(true, bArr, bArr2);
        this.packetBuffer = new ParsableByteArray(2048);
        this.averageFrameSize = -1;
        this.firstFramePosition = -1L;
        this.scratch = new ParsableByteArray(10);
        this.scratchBits = new ParsableBitArray(this.scratch.getData());
    }
}
