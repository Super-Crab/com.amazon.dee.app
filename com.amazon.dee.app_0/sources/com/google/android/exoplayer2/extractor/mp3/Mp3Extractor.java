package com.google.android.exoplayer2.extractor.mp3;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.Id3Peeker;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp3.Seeker;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes2.dex */
public final class Mp3Extractor implements Extractor {
    public static final int FLAG_DISABLE_ID3_METADATA = 4;
    public static final int FLAG_ENABLE_CONSTANT_BITRATE_SEEKING = 1;
    public static final int FLAG_ENABLE_INDEX_SEEKING = 2;
    private static final int MAX_SNIFF_BYTES = 32768;
    private static final int MAX_SYNC_BYTES = 131072;
    private static final int MPEG_AUDIO_HEADER_MASK = -128000;
    private static final int SCRATCH_LENGTH = 10;
    private static final int SEEK_HEADER_INFO = 1231971951;
    private static final int SEEK_HEADER_UNSET = 0;
    private static final int SEEK_HEADER_VBRI = 1447187017;
    private static final int SEEK_HEADER_XING = 1483304551;
    private long basisTimeUs;
    private TrackOutput currentTrackOutput;
    private boolean disableSeeking;
    private ExtractorOutput extractorOutput;
    private long firstSamplePosition;
    private final int flags;
    private final long forcedFirstSampleTimestampUs;
    private final GaplessInfoHolder gaplessInfoHolder;
    private final Id3Peeker id3Peeker;
    private boolean isSeekInProgress;
    @Nullable
    private Metadata metadata;
    private TrackOutput realTrackOutput;
    private int sampleBytesRemaining;
    private long samplesRead;
    private final ParsableByteArray scratch;
    private long seekTimeUs;
    private Seeker seeker;
    private final TrackOutput skippingTrackOutput;
    private final MpegAudioUtil.Header synchronizedHeader;
    private int synchronizedHeaderData;
    public static final ExtractorsFactory FACTORY = $$Lambda$Mp3Extractor$6eyGfoogMVGFHZKg1gVp93FAKZA.INSTANCE;
    private static final Id3Decoder.FramePredicate REQUIRED_ID3_FRAME_PREDICATE = $$Lambda$Mp3Extractor$bb754AZIAMUosKBF4SefP1vYq88.INSTANCE;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Flags {
    }

    public Mp3Extractor() {
        this(0);
    }

    @EnsuresNonNull({"extractorOutput", "realTrackOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.realTrackOutput);
        Util.castNonNull(this.extractorOutput);
    }

    private Seeker computeSeeker(ExtractorInput extractorInput) throws IOException {
        long id3TlenUs;
        long j;
        long durationUs;
        long dataEndPosition;
        Seeker maybeReadSeekFrame = maybeReadSeekFrame(extractorInput);
        MlltSeeker maybeHandleSeekMetadata = maybeHandleSeekMetadata(this.metadata, extractorInput.getPosition());
        if (this.disableSeeking) {
            return new Seeker.UnseekableSeeker();
        }
        if ((this.flags & 2) != 0) {
            if (maybeHandleSeekMetadata != null) {
                durationUs = maybeHandleSeekMetadata.getDurationUs();
                dataEndPosition = maybeHandleSeekMetadata.getDataEndPosition();
            } else if (maybeReadSeekFrame != null) {
                durationUs = maybeReadSeekFrame.getDurationUs();
                dataEndPosition = maybeReadSeekFrame.getDataEndPosition();
            } else {
                id3TlenUs = getId3TlenUs(this.metadata);
                j = -1;
                maybeReadSeekFrame = new IndexSeeker(id3TlenUs, extractorInput.getPosition(), j);
            }
            j = dataEndPosition;
            id3TlenUs = durationUs;
            maybeReadSeekFrame = new IndexSeeker(id3TlenUs, extractorInput.getPosition(), j);
        } else if (maybeHandleSeekMetadata != null) {
            maybeReadSeekFrame = maybeHandleSeekMetadata;
        } else if (maybeReadSeekFrame == null) {
            maybeReadSeekFrame = null;
        }
        return (maybeReadSeekFrame == null || (!maybeReadSeekFrame.isSeekable() && (this.flags & 1) != 0)) ? getConstantBitrateSeeker(extractorInput) : maybeReadSeekFrame;
    }

    private long computeTimeUs(long j) {
        return ((j * 1000000) / this.synchronizedHeader.sampleRate) + this.basisTimeUs;
    }

    private Seeker getConstantBitrateSeeker(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        this.synchronizedHeader.setForHeaderData(this.scratch.readInt());
        return new ConstantBitrateSeeker(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader);
    }

    private static long getId3TlenUs(@Nullable Metadata metadata) {
        if (metadata != null) {
            int length = metadata.length();
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                if (entry instanceof TextInformationFrame) {
                    TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                    if (textInformationFrame.id.equals("TLEN")) {
                        return C.msToUs(Long.parseLong(textInformationFrame.value));
                    }
                }
            }
            return C.TIME_UNSET;
        }
        return C.TIME_UNSET;
    }

    private static int getSeekFrameHeader(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() >= i + 4) {
            parsableByteArray.setPosition(i);
            int readInt = parsableByteArray.readInt();
            if (readInt == SEEK_HEADER_XING || readInt == SEEK_HEADER_INFO) {
                return readInt;
            }
        }
        if (parsableByteArray.limit() >= 40) {
            parsableByteArray.setPosition(36);
            if (parsableByteArray.readInt() != SEEK_HEADER_VBRI) {
                return 0;
            }
            return SEEK_HEADER_VBRI;
        }
        return 0;
    }

    private static boolean headersMatch(int i, long j) {
        return ((long) (i & MPEG_AUDIO_HEADER_MASK)) == (j & (-128000));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp3Extractor()};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(int i, int i2, int i3, int i4, int i5) {
        return (i2 == 67 && i3 == 79 && i4 == 77 && (i5 == 77 || i == 2)) || (i2 == 77 && i3 == 76 && i4 == 76 && (i5 == 84 || i == 2));
    }

    @Nullable
    private static MlltSeeker maybeHandleSeekMetadata(@Nullable Metadata metadata, long j) {
        if (metadata != null) {
            int length = metadata.length();
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                if (entry instanceof MlltFrame) {
                    return MlltSeeker.create(j, (MlltFrame) entry, getId3TlenUs(metadata));
                }
            }
            return null;
        }
        return null;
    }

    @Nullable
    private Seeker maybeReadSeekFrame(ExtractorInput extractorInput) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.synchronizedHeader.frameSize);
        extractorInput.peekFully(parsableByteArray.getData(), 0, this.synchronizedHeader.frameSize);
        MpegAudioUtil.Header header = this.synchronizedHeader;
        int i = header.version & 1;
        int i2 = 21;
        int i3 = header.channels;
        if (i != 0) {
            if (i3 != 1) {
                i2 = 36;
            }
        } else if (i3 == 1) {
            i2 = 13;
        }
        int i4 = i2;
        int seekFrameHeader = getSeekFrameHeader(parsableByteArray, i4);
        if (seekFrameHeader != SEEK_HEADER_XING && seekFrameHeader != SEEK_HEADER_INFO) {
            if (seekFrameHeader == SEEK_HEADER_VBRI) {
                VbriSeeker create = VbriSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
                extractorInput.skipFully(this.synchronizedHeader.frameSize);
                return create;
            }
            extractorInput.resetPeekPosition();
            return null;
        }
        XingSeeker create2 = XingSeeker.create(extractorInput.getLength(), extractorInput.getPosition(), this.synchronizedHeader, parsableByteArray);
        if (create2 != null && !this.gaplessInfoHolder.hasGaplessInfo()) {
            extractorInput.resetPeekPosition();
            extractorInput.advancePeekPosition(i4 + 141);
            extractorInput.peekFully(this.scratch.getData(), 0, 3);
            this.scratch.setPosition(0);
            this.gaplessInfoHolder.setFromXingHeaderValue(this.scratch.readUnsignedInt24());
        }
        extractorInput.skipFully(this.synchronizedHeader.frameSize);
        return (create2 == null || create2.isSeekable() || seekFrameHeader != SEEK_HEADER_INFO) ? create2 : getConstantBitrateSeeker(extractorInput);
    }

    private boolean peekEndOfStreamOrHeader(ExtractorInput extractorInput) throws IOException {
        Seeker seeker = this.seeker;
        if (seeker != null) {
            long dataEndPosition = seeker.getDataEndPosition();
            if (dataEndPosition != -1 && extractorInput.getPeekPosition() > dataEndPosition - 4) {
                return true;
            }
        }
        try {
            return !extractorInput.peekFully(this.scratch.getData(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    @RequiresNonNull({"extractorOutput", "realTrackOutput"})
    private int readInternal(ExtractorInput extractorInput) throws IOException {
        if (this.synchronizedHeaderData == 0) {
            try {
                synchronize(extractorInput, false);
            } catch (EOFException unused) {
                return -1;
            }
        }
        if (this.seeker == null) {
            this.seeker = computeSeeker(extractorInput);
            this.extractorOutput.seekMap(this.seeker);
            this.currentTrackOutput.format(new Format.Builder().setSampleMimeType(this.synchronizedHeader.mimeType).setMaxInputSize(4096).setChannelCount(this.synchronizedHeader.channels).setSampleRate(this.synchronizedHeader.sampleRate).setEncoderDelay(this.gaplessInfoHolder.encoderDelay).setEncoderPadding(this.gaplessInfoHolder.encoderPadding).setMetadata((this.flags & 4) != 0 ? null : this.metadata).build());
            this.firstSamplePosition = extractorInput.getPosition();
        } else if (this.firstSamplePosition != 0) {
            long position = extractorInput.getPosition();
            long j = this.firstSamplePosition;
            if (position < j) {
                extractorInput.skipFully((int) (j - position));
            }
        }
        return readSample(extractorInput);
    }

    @RequiresNonNull({"realTrackOutput", "seeker"})
    private int readSample(ExtractorInput extractorInput) throws IOException {
        MpegAudioUtil.Header header;
        if (this.sampleBytesRemaining == 0) {
            extractorInput.resetPeekPosition();
            if (peekEndOfStreamOrHeader(extractorInput)) {
                return -1;
            }
            this.scratch.setPosition(0);
            int readInt = this.scratch.readInt();
            if (headersMatch(readInt, this.synchronizedHeaderData) && MpegAudioUtil.getFrameSize(readInt) != -1) {
                this.synchronizedHeader.setForHeaderData(readInt);
                if (this.basisTimeUs == C.TIME_UNSET) {
                    this.basisTimeUs = this.seeker.getTimeUs(extractorInput.getPosition());
                    if (this.forcedFirstSampleTimestampUs != C.TIME_UNSET) {
                        this.basisTimeUs = (this.forcedFirstSampleTimestampUs - this.seeker.getTimeUs(0L)) + this.basisTimeUs;
                    }
                }
                this.sampleBytesRemaining = this.synchronizedHeader.frameSize;
                Seeker seeker = this.seeker;
                if (seeker instanceof IndexSeeker) {
                    IndexSeeker indexSeeker = (IndexSeeker) seeker;
                    indexSeeker.maybeAddSeekPoint(computeTimeUs(this.samplesRead + header.samplesPerFrame), extractorInput.getPosition() + this.synchronizedHeader.frameSize);
                    if (this.isSeekInProgress && indexSeeker.isTimeUsInIndex(this.seekTimeUs)) {
                        this.isSeekInProgress = false;
                        this.currentTrackOutput = this.realTrackOutput;
                    }
                }
            } else {
                extractorInput.skipFully(1);
                this.synchronizedHeaderData = 0;
                return 0;
            }
        }
        int sampleData = this.currentTrackOutput.sampleData((DataReader) extractorInput, this.sampleBytesRemaining, true);
        if (sampleData == -1) {
            return -1;
        }
        this.sampleBytesRemaining -= sampleData;
        if (this.sampleBytesRemaining > 0) {
            return 0;
        }
        this.currentTrackOutput.sampleMetadata(computeTimeUs(this.samplesRead), 1, this.synchronizedHeader.frameSize, 0, null);
        this.samplesRead += this.synchronizedHeader.samplesPerFrame;
        this.sampleBytesRemaining = 0;
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a2, code lost:
        if (r13 == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a4, code lost:
        r12.skipFully(r7 + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a9, code lost:
        r12.resetPeekPosition();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ac, code lost:
        r11.synchronizedHeaderData = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ae, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean synchronize(com.google.android.exoplayer2.extractor.ExtractorInput r12, boolean r13) throws java.io.IOException {
        /*
            r11 = this;
            if (r13 == 0) goto L6
            r0 = 32768(0x8000, float:4.5918E-41)
            goto L8
        L6:
            r0 = 131072(0x20000, float:1.83671E-40)
        L8:
            r12.resetPeekPosition()
            long r1 = r12.getPosition()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r2 = 4
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L46
            int r1 = r11.flags
            r1 = r1 & r2
            if (r1 != 0) goto L1f
            r1 = r3
            goto L20
        L1f:
            r1 = r4
        L20:
            if (r1 == 0) goto L24
            r1 = 0
            goto L26
        L24:
            com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate r1 = com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.REQUIRED_ID3_FRAME_PREDICATE
        L26:
            com.google.android.exoplayer2.extractor.Id3Peeker r5 = r11.id3Peeker
            com.google.android.exoplayer2.metadata.Metadata r1 = r5.peekId3Data(r12, r1)
            r11.metadata = r1
            com.google.android.exoplayer2.metadata.Metadata r1 = r11.metadata
            if (r1 == 0) goto L37
            com.google.android.exoplayer2.extractor.GaplessInfoHolder r5 = r11.gaplessInfoHolder
            r5.setFromMetadata(r1)
        L37:
            long r5 = r12.getPeekPosition()
            int r1 = (int) r5
            if (r13 != 0) goto L41
            r12.skipFully(r1)
        L41:
            r7 = r1
            r1 = r4
            r5 = r1
            r6 = r5
            goto L4a
        L46:
            r1 = r4
            r5 = r1
            r6 = r5
            r7 = r6
        L4a:
            boolean r8 = r11.peekEndOfStreamOrHeader(r12)
            if (r8 == 0) goto L59
            if (r5 <= 0) goto L53
            goto La2
        L53:
            java.io.EOFException r12 = new java.io.EOFException
            r12.<init>()
            throw r12
        L59:
            com.google.android.exoplayer2.util.ParsableByteArray r8 = r11.scratch
            r8.setPosition(r4)
            com.google.android.exoplayer2.util.ParsableByteArray r8 = r11.scratch
            int r8 = r8.readInt()
            if (r1 == 0) goto L6d
            long r9 = (long) r1
            boolean r9 = headersMatch(r8, r9)
            if (r9 == 0) goto L74
        L6d:
            int r9 = com.google.android.exoplayer2.audio.MpegAudioUtil.getFrameSize(r8)
            r10 = -1
            if (r9 != r10) goto L95
        L74:
            int r1 = r6 + 1
            if (r6 != r0) goto L83
            if (r13 == 0) goto L7b
            return r4
        L7b:
            com.google.android.exoplayer2.ParserException r12 = new com.google.android.exoplayer2.ParserException
            java.lang.String r13 = "Searched too many bytes."
            r12.<init>(r13)
            throw r12
        L83:
            if (r13 == 0) goto L8e
            r12.resetPeekPosition()
            int r5 = r7 + r1
            r12.advancePeekPosition(r5)
            goto L91
        L8e:
            r12.skipFully(r3)
        L91:
            r6 = r1
            r1 = r4
            r5 = r1
            goto L4a
        L95:
            int r5 = r5 + 1
            if (r5 != r3) goto La0
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r1 = r11.synchronizedHeader
            r1.setForHeaderData(r8)
            r1 = r8
            goto Laf
        La0:
            if (r5 != r2) goto Laf
        La2:
            if (r13 == 0) goto La9
            int r7 = r7 + r6
            r12.skipFully(r7)
            goto Lac
        La9:
            r12.resetPeekPosition()
        Lac:
            r11.synchronizedHeaderData = r1
            return r3
        Laf:
            int r9 = r9 + (-4)
            r12.advancePeekPosition(r9)
            goto L4a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp3.Mp3Extractor.synchronize(com.google.android.exoplayer2.extractor.ExtractorInput, boolean):boolean");
    }

    public void disableSeeking() {
        this.disableSeeking = true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.realTrackOutput = this.extractorOutput.track(0, 1);
        this.currentTrackOutput = this.realTrackOutput;
        this.extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        assertInitialized();
        int readInternal = readInternal(extractorInput);
        if (readInternal == -1 && (this.seeker instanceof IndexSeeker)) {
            long computeTimeUs = computeTimeUs(this.samplesRead);
            if (this.seeker.getDurationUs() != computeTimeUs) {
                ((IndexSeeker) this.seeker).setDurationUs(computeTimeUs);
                this.extractorOutput.seekMap(this.seeker);
            }
        }
        return readInternal;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.synchronizedHeaderData = 0;
        this.basisTimeUs = C.TIME_UNSET;
        this.samplesRead = 0L;
        this.sampleBytesRemaining = 0;
        this.seekTimeUs = j2;
        Seeker seeker = this.seeker;
        if (!(seeker instanceof IndexSeeker) || ((IndexSeeker) seeker).isTimeUsInIndex(j2)) {
            return;
        }
        this.isSeekInProgress = true;
        this.currentTrackOutput = this.skippingTrackOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return synchronize(extractorInput, true);
    }

    public Mp3Extractor(int i) {
        this(i, C.TIME_UNSET);
    }

    public Mp3Extractor(int i, long j) {
        this.flags = i;
        this.forcedFirstSampleTimestampUs = j;
        this.scratch = new ParsableByteArray(10);
        this.synchronizedHeader = new MpegAudioUtil.Header();
        this.gaplessInfoHolder = new GaplessInfoHolder();
        this.basisTimeUs = C.TIME_UNSET;
        this.id3Peeker = new Id3Peeker();
        this.skippingTrackOutput = new DummyTrackOutput();
        this.currentTrackOutput = this.skippingTrackOutput;
    }
}
