package com.google.android.exoplayer2.extractor.amr;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import com.google.android.exoplayer2.extractor.ConstantBitrateSeekMap;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
public final class AmrExtractor implements Extractor {
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    private static final int NUM_SAME_SIZE_CONSTANT_BIT_RATE_THRESHOLD = 20;
    private static final int SAMPLE_RATE_NB = 8000;
    private static final int SAMPLE_RATE_WB = 16000;
    private static final int SAMPLE_TIME_PER_FRAME_US = 20000;
    private int currentSampleBytesRemaining;
    private int currentSampleSize;
    private long currentSampleTimeUs;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private int firstSampleSize;
    private final int flags;
    private boolean hasOutputFormat;
    private boolean hasOutputSeekMap;
    private boolean isWideBand;
    private int numSamplesWithSameSize;
    private final byte[] scratch;
    private SeekMap seekMap;
    private long timeOffsetUs;
    private TrackOutput trackOutput;
    public static final ExtractorsFactory FACTORY = $$Lambda$AmrExtractor$lVuGuaAcylUV_XE4hSR1hBylI.INSTANCE;
    private static final int[] frameSizeBytesByTypeNb = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] frameSizeBytesByTypeWb = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
    private static final byte[] amrSignatureNb = Util.getUtf8Bytes("#!AMR\n");
    private static final byte[] amrSignatureWb = Util.getUtf8Bytes("#!AMR-WB\n");
    private static final int MAX_FRAME_SIZE_BYTES = frameSizeBytesByTypeWb[8];

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flags {
    }

    public AmrExtractor() {
        this(0);
    }

    static byte[] amrSignatureNb() {
        byte[] bArr = amrSignatureNb;
        return Arrays.copyOf(bArr, bArr.length);
    }

    static byte[] amrSignatureWb() {
        byte[] bArr = amrSignatureWb;
        return Arrays.copyOf(bArr, bArr.length);
    }

    @EnsuresNonNull({"extractorOutput", "trackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.trackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    static int frameSizeBytesByTypeNb(int i) {
        return frameSizeBytesByTypeNb[i];
    }

    static int frameSizeBytesByTypeWb(int i) {
        return frameSizeBytesByTypeWb[i];
    }

    private static int getBitrateFromFrameSize(int i, long j) {
        return (int) (((i * 8) * 1000000) / j);
    }

    private SeekMap getConstantBitrateSeekMap(long j) {
        return new ConstantBitrateSeekMap(j, this.firstSamplePosition, getBitrateFromFrameSize(this.firstSampleSize, SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US), this.firstSampleSize);
    }

    private int getFrameSizeInBytes(int i) throws ParserException {
        if (isValidFrameType(i)) {
            return this.isWideBand ? frameSizeBytesByTypeWb[i] : frameSizeBytesByTypeNb[i];
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal AMR ");
        outline107.append(this.isWideBand ? "WB" : "NB");
        outline107.append(" frame type ");
        outline107.append(i);
        throw new ParserException(outline107.toString());
    }

    private boolean isNarrowBandValidFrameType(int i) {
        return !this.isWideBand && (i < 12 || i > 14);
    }

    private boolean isValidFrameType(int i) {
        return i >= 0 && i <= 15 && (isWideBandValidFrameType(i) || isNarrowBandValidFrameType(i));
    }

    private boolean isWideBandValidFrameType(int i) {
        return this.isWideBand && (i < 10 || i > 13);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new AmrExtractor()};
    }

    @RequiresNonNull({"trackOutput"})
    private void maybeOutputFormat() {
        if (!this.hasOutputFormat) {
            this.hasOutputFormat = true;
            this.trackOutput.format(new Format.Builder().setSampleMimeType(this.isWideBand ? MimeTypes.AUDIO_AMR_WB : MimeTypes.AUDIO_AMR_NB).setMaxInputSize(MAX_FRAME_SIZE_BYTES).setChannelCount(1).setSampleRate(this.isWideBand ? 16000 : 8000).build());
        }
    }

    @RequiresNonNull({"extractorOutput"})
    private void maybeOutputSeekMap(long j, int i) {
        int i2;
        if (this.hasOutputSeekMap) {
            return;
        }
        if ((this.flags & 1) != 0 && j != -1 && ((i2 = this.firstSampleSize) == -1 || i2 == this.currentSampleSize)) {
            if (this.numSamplesWithSameSize < 20 && i != -1) {
                return;
            }
            this.seekMap = getConstantBitrateSeekMap(j);
            this.extractorOutput.seekMap(this.seekMap);
            this.hasOutputSeekMap = true;
            return;
        }
        this.seekMap = new SeekMap.Unseekable(C.TIME_UNSET);
        this.extractorOutput.seekMap(this.seekMap);
        this.hasOutputSeekMap = true;
    }

    private static boolean peekAmrSignature(ExtractorInput extractorInput, byte[] bArr) throws IOException {
        extractorInput.resetPeekPosition();
        byte[] bArr2 = new byte[bArr.length];
        extractorInput.peekFully(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    private int peekNextSampleSize(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.scratch, 0, 1);
        byte b = this.scratch[0];
        if ((b & 131) <= 0) {
            return getFrameSizeInBytes((b >> 3) & 15);
        }
        throw new ParserException(GeneratedOutlineSupport1.outline49("Invalid padding bits for frame header ", b));
    }

    private boolean readAmrHeader(ExtractorInput extractorInput) throws IOException {
        if (peekAmrSignature(extractorInput, amrSignatureNb)) {
            this.isWideBand = false;
            extractorInput.skipFully(amrSignatureNb.length);
            return true;
        } else if (!peekAmrSignature(extractorInput, amrSignatureWb)) {
            return false;
        } else {
            this.isWideBand = true;
            extractorInput.skipFully(amrSignatureWb.length);
            return true;
        }
    }

    @RequiresNonNull({"trackOutput"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        if (this.currentSampleBytesRemaining == 0) {
            try {
                this.currentSampleSize = peekNextSampleSize(extractorInput);
                this.currentSampleBytesRemaining = this.currentSampleSize;
                if (this.firstSampleSize == -1) {
                    this.firstSamplePosition = extractorInput.getPosition();
                    this.firstSampleSize = this.currentSampleSize;
                }
                if (this.firstSampleSize == this.currentSampleSize) {
                    this.numSamplesWithSameSize++;
                }
            } catch (EOFException unused) {
                return -1;
            }
        }
        int sampleData = this.trackOutput.sampleData((DataReader) extractorInput, this.currentSampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        this.currentSampleBytesRemaining -= sampleData;
        if (this.currentSampleBytesRemaining > 0) {
            return 0;
        }
        this.trackOutput.sampleMetadata(this.timeOffsetUs + this.currentSampleTimeUs, 1, this.currentSampleSize, 0, null);
        this.currentSampleTimeUs += SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US;
        return 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        if (extractorInput.getPosition() == 0 && !readAmrHeader(extractorInput)) {
            throw new ParserException("Could not find AMR header.");
        }
        maybeOutputFormat();
        int readSample = readSample(extractorInput);
        maybeOutputSeekMap(extractorInput.getLength(), readSample);
        return readSample;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.currentSampleTimeUs = 0L;
        this.currentSampleSize = 0;
        this.currentSampleBytesRemaining = 0;
        if (j != 0) {
            SeekMap seekMap = this.seekMap;
            if (seekMap instanceof ConstantBitrateSeekMap) {
                this.timeOffsetUs = ((ConstantBitrateSeekMap) seekMap).getTimeUsAtPosition(j);
                return;
            }
        }
        this.timeOffsetUs = 0L;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return readAmrHeader(extractorInput);
    }

    public AmrExtractor(int i) {
        this.flags = i;
        this.scratch = new byte[1];
        this.firstSampleSize = -1;
    }
}
