package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.HlsChunkSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class HlsMediaChunk extends MediaChunk {
    private static final String ENCRYPTION_METHOD_AES_128 = "AES-128";
    public static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    public final int discontinuitySequenceNumber;
    @Nullable
    private final DrmInitData drmInitData;
    private final byte[] encryptionIv;
    private final byte[] encryptionKey;
    private final String encryptionMethod;
    private HlsMediaChunkExtractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private boolean extractorInvalidated;
    private final boolean hasGapTag;
    private final Id3Decoder id3Decoder;
    private boolean initDataLoadRequired;
    @Nullable
    private final DataSource initDataSource;
    @Nullable
    private final DataSpec initDataSpec;
    private final boolean initSegmentEncrypted;
    private final boolean isMasterTimestampSource;
    private boolean isPublished;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final boolean mediaSegmentEncrypted;
    @Nullable
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    public final int partIndex;
    public final Uri playlistUrl;
    @Nullable
    private final HlsMediaChunkExtractor previousExtractor;
    private ImmutableList<Integer> sampleQueueFirstSampleIndices;
    private final ParsableByteArray scratchId3Data;
    public final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    private HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, Format format, boolean z, @Nullable DataSource dataSource2, @Nullable DataSpec dataSpec2, boolean z2, byte[] bArr, byte[] bArr2, String str, Uri uri, @Nullable List<Format> list, int i, @Nullable Object obj, long j, long j2, long j3, int i2, boolean z3, int i3, boolean z4, boolean z5, TimestampAdjuster timestampAdjuster, @Nullable DrmInitData drmInitData, @Nullable HlsMediaChunkExtractor hlsMediaChunkExtractor, Id3Decoder id3Decoder, ParsableByteArray parsableByteArray, boolean z6) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3);
        this.mediaSegmentEncrypted = z;
        this.partIndex = i2;
        this.isPublished = z3;
        this.discontinuitySequenceNumber = i3;
        this.initDataSpec = dataSpec2;
        this.initDataSource = dataSource2;
        this.initDataLoadRequired = dataSpec2 != null;
        this.initSegmentEncrypted = z2;
        this.encryptionIv = bArr;
        this.encryptionKey = bArr2;
        this.encryptionMethod = str;
        this.playlistUrl = uri;
        this.isMasterTimestampSource = z5;
        this.timestampAdjuster = timestampAdjuster;
        this.hasGapTag = z4;
        this.extractorFactory = hlsExtractorFactory;
        this.muxedCaptionFormats = list;
        this.drmInitData = drmInitData;
        this.previousExtractor = hlsMediaChunkExtractor;
        this.id3Decoder = id3Decoder;
        this.scratchId3Data = parsableByteArray;
        this.shouldSpliceIn = z6;
        this.sampleQueueFirstSampleIndices = ImmutableList.of();
        this.uid = uidSource.getAndIncrement();
    }

    private static DataSource buildDataSource(DataSource dataSource, @Nullable byte[] bArr, @Nullable byte[] bArr2, @Nullable String str) {
        if (bArr == null || str == null || !str.equals(ENCRYPTION_METHOD_AES_128)) {
            return dataSource;
        }
        Assertions.checkNotNull(bArr2);
        return new Aes128DataSource(dataSource, bArr, bArr2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00cc, code lost:
        if (r20 >= r52.endTimeUs) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.exoplayer2.source.hls.HlsMediaChunk createInstance(com.google.android.exoplayer2.source.hls.HlsExtractorFactory r39, com.google.android.exoplayer2.upstream.DataSource r40, com.google.android.exoplayer2.Format r41, long r42, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r44, com.google.android.exoplayer2.source.hls.HlsChunkSource.SegmentBaseHolder r45, android.net.Uri r46, @androidx.annotation.Nullable java.util.List<com.google.android.exoplayer2.Format> r47, int r48, @androidx.annotation.Nullable java.lang.Object r49, boolean r50, com.google.android.exoplayer2.source.hls.TimestampAdjusterProvider r51, @androidx.annotation.Nullable com.google.android.exoplayer2.source.hls.HlsMediaChunk r52, @androidx.annotation.Nullable byte[] r53, @androidx.annotation.Nullable byte[] r54, @androidx.annotation.Nullable java.lang.String r55) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaChunk.createInstance(com.google.android.exoplayer2.source.hls.HlsExtractorFactory, com.google.android.exoplayer2.upstream.DataSource, com.google.android.exoplayer2.Format, long, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist, com.google.android.exoplayer2.source.hls.HlsChunkSource$SegmentBaseHolder, android.net.Uri, java.util.List, int, java.lang.Object, boolean, com.google.android.exoplayer2.source.hls.TimestampAdjusterProvider, com.google.android.exoplayer2.source.hls.HlsMediaChunk, byte[], byte[], java.lang.String):com.google.android.exoplayer2.source.hls.HlsMediaChunk");
    }

    @RequiresNonNull({ContactsModuleConstants.OUTPUT})
    private void feedDataToExtractor(DataSource dataSource, DataSpec dataSpec, boolean z) throws IOException {
        DataSpec subrange;
        long position;
        long j;
        boolean z2 = false;
        if (z) {
            if (this.nextLoadPosition != 0) {
                z2 = true;
            }
            subrange = dataSpec;
        } else {
            subrange = dataSpec.subrange(this.nextLoadPosition);
        }
        try {
            DefaultExtractorInput prepareExtraction = prepareExtraction(dataSource, subrange);
            if (z2) {
                prepareExtraction.skipFully(this.nextLoadPosition);
            }
            do {
                try {
                    if (this.loadCanceled) {
                        break;
                    }
                } catch (EOFException e) {
                    if ((this.trackFormat.roleFlags & 16384) != 0) {
                        this.extractor.onTruncatedSegmentParsed();
                        position = prepareExtraction.getPosition();
                        j = dataSpec.position;
                    } else {
                        throw e;
                    }
                }
            } while (this.extractor.read(prepareExtraction));
            position = prepareExtraction.getPosition();
            j = dataSpec.position;
            this.nextLoadPosition = (int) (position - j);
        } finally {
            Util.closeQuietly(dataSource);
        }
    }

    private static byte[] getEncryptionIvArray(String str) {
        if (Util.toLowerInvariant(str).startsWith("0x")) {
            str = str.substring(2);
        }
        byte[] byteArray = new BigInteger(str, 16).toByteArray();
        byte[] bArr = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr, (bArr.length - byteArray.length) + length, byteArray.length - length);
        return bArr;
    }

    private static boolean isIndependent(HlsChunkSource.SegmentBaseHolder segmentBaseHolder, HlsMediaPlaylist hlsMediaPlaylist) {
        HlsMediaPlaylist.SegmentBase segmentBase = segmentBaseHolder.segmentBase;
        if (segmentBase instanceof HlsMediaPlaylist.Part) {
            return ((HlsMediaPlaylist.Part) segmentBase).isIndependent || (segmentBaseHolder.partIndex == 0 && hlsMediaPlaylist.hasIndependentSegments);
        }
        return hlsMediaPlaylist.hasIndependentSegments;
    }

    @RequiresNonNull({ContactsModuleConstants.OUTPUT})
    private void loadMedia() throws IOException {
        try {
            this.timestampAdjuster.sharedInitializeOrWait(this.isMasterTimestampSource, this.startTimeUs);
            feedDataToExtractor(this.dataSource, this.dataSpec, this.mediaSegmentEncrypted);
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    @RequiresNonNull({ContactsModuleConstants.OUTPUT})
    private void maybeLoadInitData() throws IOException {
        if (!this.initDataLoadRequired) {
            return;
        }
        Assertions.checkNotNull(this.initDataSource);
        Assertions.checkNotNull(this.initDataSpec);
        feedDataToExtractor(this.initDataSource, this.initDataSpec, this.initSegmentEncrypted);
        this.nextLoadPosition = 0;
        this.initDataLoadRequired = false;
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException {
        extractorInput.resetPeekPosition();
        try {
            this.scratchId3Data.reset(10);
            extractorInput.peekFully(this.scratchId3Data.getData(), 0, 10);
        } catch (EOFException unused) {
        }
        if (this.scratchId3Data.readUnsignedInt24() != 4801587) {
            return C.TIME_UNSET;
        }
        this.scratchId3Data.skipBytes(3);
        int readSynchSafeInt = this.scratchId3Data.readSynchSafeInt();
        int i = readSynchSafeInt + 10;
        if (i > this.scratchId3Data.capacity()) {
            byte[] data = this.scratchId3Data.getData();
            this.scratchId3Data.reset(i);
            System.arraycopy(data, 0, this.scratchId3Data.getData(), 0, 10);
        }
        extractorInput.peekFully(this.scratchId3Data.getData(), 10, readSynchSafeInt);
        Metadata decode = this.id3Decoder.decode(this.scratchId3Data.getData(), readSynchSafeInt);
        if (decode == null) {
            return C.TIME_UNSET;
        }
        int length = decode.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = decode.get(i2);
            if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                    System.arraycopy(privFrame.privateData, 0, this.scratchId3Data.getData(), 0, 8);
                    this.scratchId3Data.setPosition(0);
                    this.scratchId3Data.setLimit(8);
                    return this.scratchId3Data.readLong() & 8589934591L;
                }
            }
        }
        return C.TIME_UNSET;
    }

    @EnsuresNonNull({"extractor"})
    @RequiresNonNull({ContactsModuleConstants.OUTPUT})
    private DefaultExtractorInput prepareExtraction(DataSource dataSource, DataSpec dataSpec) throws IOException {
        DefaultExtractorInput defaultExtractorInput;
        HlsMediaChunkExtractor mo7400createExtractor;
        DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(dataSource, dataSpec.position, dataSource.open(dataSpec));
        if (this.extractor == null) {
            long peekId3PrivTimestamp = peekId3PrivTimestamp(defaultExtractorInput2);
            defaultExtractorInput2.resetPeekPosition();
            HlsMediaChunkExtractor hlsMediaChunkExtractor = this.previousExtractor;
            if (hlsMediaChunkExtractor != null) {
                mo7400createExtractor = hlsMediaChunkExtractor.recreate();
                defaultExtractorInput = defaultExtractorInput2;
            } else {
                defaultExtractorInput = defaultExtractorInput2;
                mo7400createExtractor = this.extractorFactory.mo7400createExtractor(dataSpec.uri, this.trackFormat, this.encryptionIv, this.encryptionKey, this.muxedCaptionFormats, this.timestampAdjuster, dataSource.getResponseHeaders(), defaultExtractorInput);
            }
            this.extractor = mo7400createExtractor;
            if (this.extractor.isPackedAudioExtractor()) {
                this.output.setSampleOffsetUs(peekId3PrivTimestamp != C.TIME_UNSET ? this.timestampAdjuster.adjustTsTimestamp(peekId3PrivTimestamp) : this.startTimeUs);
            } else {
                this.output.setSampleOffsetUs(0L);
            }
            this.output.onNewExtractor();
            this.extractor.init(this.output);
        } else {
            defaultExtractorInput = defaultExtractorInput2;
        }
        this.output.setDrmInitData(this.drmInitData);
        return defaultExtractorInput;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public int getFirstSampleIndex(int i) {
        Assertions.checkState(!this.shouldSpliceIn);
        if (i >= this.sampleQueueFirstSampleIndices.size()) {
            return 0;
        }
        return this.sampleQueueFirstSampleIndices.get(i).intValue();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper, ImmutableList<Integer> immutableList) {
        this.output = hlsSampleStreamWrapper;
        this.sampleQueueFirstSampleIndices = immutableList;
    }

    public void invalidateExtractor() {
        this.extractorInvalidated = true;
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public boolean isPublished() {
        return this.isPublished;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws IOException {
        HlsMediaChunkExtractor hlsMediaChunkExtractor;
        Assertions.checkNotNull(this.output);
        if (this.extractor == null && (hlsMediaChunkExtractor = this.previousExtractor) != null && hlsMediaChunkExtractor.isReusable()) {
            this.extractor = this.previousExtractor;
            this.initDataLoadRequired = false;
        }
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = !this.loadCanceled;
        }
    }

    public void publish() {
        this.isPublished = true;
    }
}
