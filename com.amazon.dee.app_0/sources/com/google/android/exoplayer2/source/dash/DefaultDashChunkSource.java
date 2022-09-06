package com.google.android.exoplayer2.source.dash;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.BaseMediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.BundledChunkExtractor;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractor;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    @Nullable
    private IOException fatalError;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    @Nullable
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private ExoTrackSelection trackSelection;
    private final int trackType;

    /* loaded from: classes2.dex */
    public static final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        @Override // com.google.android.exoplayer2.source.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, long j, boolean z, List<Format> list, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, @Nullable TransferListener transferListener) {
            DataSource mo7468createDataSource = this.dataSourceFactory.mo7468createDataSource();
            if (transferListener != null) {
                mo7468createDataSource.addTransferListener(transferListener);
            }
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, exoTrackSelection, i2, mo7468createDataSource, j, this.maxSegmentsPerLoad, z, list, playerTrackEmsgHandler);
        }

        public Factory(DataSource.Factory factory, int i) {
            this.dataSourceFactory = factory;
            this.maxSegmentsPerLoad = i;
        }
    }

    /* loaded from: classes2.dex */
    protected static final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final long nowPeriodTimeUs;
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder representationHolder, long j, long j2, long j3) {
            super(j, j2);
            this.representationHolder = representationHolder;
            this.nowPeriodTimeUs = j3;
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(getCurrentIndex());
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(getCurrentIndex());
        }

        @Override // com.google.android.exoplayer2.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            checkInBounds();
            long currentIndex = getCurrentIndex();
            return DashUtil.buildDataSpec(this.representationHolder.representation, this.representationHolder.getSegmentUrl(currentIndex), this.representationHolder.isSegmentAvailableAtFullNetworkSpeed(currentIndex, this.nowPeriodTimeUs) ? 0 : 8);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, DataSource dataSource, long j, int i3, boolean z, List<Format> list, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.adaptationSetIndices = iArr;
        this.trackSelection = exoTrackSelection;
        this.trackType = i2;
        this.dataSource = dataSource;
        this.periodIndex = i;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[exoTrackSelection.length()];
        for (int i4 = 0; i4 < this.representationHolders.length; i4++) {
            this.representationHolders[i4] = new RepresentationHolder(periodDurationUs, i2, representations.get(exoTrackSelection.getIndexInTrackGroup(i4)), z, list, playerTrackEmsgHandler);
        }
    }

    private long getAvailableLiveDurationUs(long j, long j2) {
        if (!this.manifest.dynamic) {
            return C.TIME_UNSET;
        }
        return Math.max(0L, Math.min(getNowPeriodTimeUs(j), this.representationHolders[0].getSegmentEndTimeUs(this.representationHolders[0].getLastAvailableSegmentNum(j))) - j2);
    }

    private long getNowPeriodTimeUs(long j) {
        DashManifest dashManifest = this.manifest;
        long j2 = dashManifest.availabilityStartTimeMs;
        return j2 == C.TIME_UNSET ? C.TIME_UNSET : j - C.msToUs(j2 + dashManifest.getPeriod(this.periodIndex).startMs);
    }

    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i).representations);
        }
        return arrayList;
    }

    private long getSegmentNum(RepresentationHolder representationHolder, @Nullable MediaChunk mediaChunk, long j, long j2, long j3) {
        if (mediaChunk != null) {
            return mediaChunk.getNextChunkIndex();
        }
        return Util.constrainValue(representationHolder.getSegmentNum(j), j2, j3);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        RepresentationHolder[] representationHolderArr;
        for (RepresentationHolder representationHolder : this.representationHolders) {
            if (representationHolder.segmentIndex != null) {
                long segmentNum = representationHolder.getSegmentNum(j);
                long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                int segmentCount = representationHolder.getSegmentCount();
                return seekParameters.resolveSeekPositionUs(j, segmentStartTimeUs, (segmentStartTimeUs >= j || (segmentCount != -1 && segmentNum >= (representationHolder.getFirstSegmentNum() + ((long) segmentCount)) - 1)) ? segmentStartTimeUs : representationHolder.getSegmentStartTimeUs(segmentNum + 1));
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void getNextChunk(long j, long j2, List<? extends MediaChunk> list, ChunkHolder chunkHolder) {
        MediaChunkIterator[] mediaChunkIteratorArr;
        int i;
        long j3;
        long j4;
        if (this.fatalError != null) {
            return;
        }
        long j5 = j2 - j;
        long msToUs = C.msToUs(this.manifest.getPeriod(this.periodIndex).startMs) + C.msToUs(this.manifest.availabilityStartTimeMs) + j2;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null && playerTrackEmsgHandler.maybeRefreshManifestBeforeLoadingNextChunk(msToUs)) {
            return;
        }
        long msToUs2 = C.msToUs(Util.getNowUnixTimeMs(this.elapsedRealtimeOffsetMs));
        long nowPeriodTimeUs = getNowPeriodTimeUs(msToUs2);
        MediaChunk mediaChunk = list.isEmpty() ? null : (MediaChunk) GeneratedOutlineSupport1.outline25(list, 1);
        MediaChunkIterator[] mediaChunkIteratorArr2 = new MediaChunkIterator[this.trackSelection.length()];
        boolean z = false;
        int i2 = 0;
        while (i2 < mediaChunkIteratorArr2.length) {
            RepresentationHolder representationHolder = this.representationHolders[i2];
            if (representationHolder.segmentIndex == null) {
                mediaChunkIteratorArr2[i2] = MediaChunkIterator.EMPTY;
                mediaChunkIteratorArr = mediaChunkIteratorArr2;
                i = i2;
                j3 = j5;
                j4 = msToUs2;
            } else {
                long firstAvailableSegmentNum = representationHolder.getFirstAvailableSegmentNum(msToUs2);
                long lastAvailableSegmentNum = representationHolder.getLastAvailableSegmentNum(msToUs2);
                mediaChunkIteratorArr = mediaChunkIteratorArr2;
                i = i2;
                j3 = j5;
                j4 = msToUs2;
                long segmentNum = getSegmentNum(representationHolder, mediaChunk, j2, firstAvailableSegmentNum, lastAvailableSegmentNum);
                if (segmentNum < firstAvailableSegmentNum) {
                    mediaChunkIteratorArr[i] = MediaChunkIterator.EMPTY;
                } else {
                    mediaChunkIteratorArr[i] = new RepresentationSegmentIterator(representationHolder, segmentNum, lastAvailableSegmentNum, nowPeriodTimeUs);
                }
            }
            i2 = i + 1;
            msToUs2 = j4;
            mediaChunkIteratorArr2 = mediaChunkIteratorArr;
            j5 = j3;
        }
        long j6 = j5;
        long j7 = msToUs2;
        this.trackSelection.updateSelectedTrack(j, j6, getAvailableLiveDurationUs(j7, j), list, mediaChunkIteratorArr2);
        RepresentationHolder representationHolder2 = this.representationHolders[this.trackSelection.getSelectedIndex()];
        ChunkExtractor chunkExtractor = representationHolder2.chunkExtractor;
        if (chunkExtractor != null) {
            Representation representation = representationHolder2.representation;
            RangedUri initializationUri = chunkExtractor.getSampleFormats() == null ? representation.getInitializationUri() : null;
            RangedUri indexUri = representationHolder2.segmentIndex == null ? representation.getIndexUri() : null;
            if (initializationUri != null || indexUri != null) {
                chunkHolder.chunk = newInitializationChunk(representationHolder2, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initializationUri, indexUri);
                return;
            }
        }
        long j8 = representationHolder2.periodDurationUs;
        long j9 = C.TIME_UNSET;
        int i3 = (j8 > C.TIME_UNSET ? 1 : (j8 == C.TIME_UNSET ? 0 : -1));
        if (i3 != 0) {
            z = true;
        }
        boolean z2 = z;
        if (representationHolder2.getSegmentCount() == 0) {
            chunkHolder.endOfStream = z2;
            return;
        }
        long firstAvailableSegmentNum2 = representationHolder2.getFirstAvailableSegmentNum(j7);
        long lastAvailableSegmentNum2 = representationHolder2.getLastAvailableSegmentNum(j7);
        long segmentNum2 = getSegmentNum(representationHolder2, mediaChunk, j2, firstAvailableSegmentNum2, lastAvailableSegmentNum2);
        if (segmentNum2 < firstAvailableSegmentNum2) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        int i4 = (segmentNum2 > lastAvailableSegmentNum2 ? 1 : (segmentNum2 == lastAvailableSegmentNum2 ? 0 : -1));
        if (i4 <= 0 && (!this.missingLastSegment || i4 < 0)) {
            if (z2 && representationHolder2.getSegmentStartTimeUs(segmentNum2) >= j8) {
                chunkHolder.endOfStream = true;
                return;
            }
            int min = (int) Math.min(this.maxSegmentsPerLoad, (lastAvailableSegmentNum2 - segmentNum2) + 1);
            if (i3 != 0) {
                while (min > 1 && representationHolder2.getSegmentStartTimeUs((min + segmentNum2) - 1) >= j8) {
                    min--;
                }
            }
            int i5 = min;
            if (list.isEmpty()) {
                j9 = j2;
            }
            chunkHolder.chunk = newMediaChunk(representationHolder2, this.dataSource, this.trackType, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), segmentNum2, i5, j9, nowPeriodTimeUs);
            return;
        }
        chunkHolder.endOfStream = z2;
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError == null && this.trackSelection.length() >= 2) {
            return this.trackSelection.evaluateQueueSize(j, list);
        }
        return list.size();
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }
        throw iOException;
    }

    protected Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2) {
        Representation representation = representationHolder.representation;
        if (rangedUri != null && (rangedUri2 = rangedUri.attemptMerge(rangedUri2, representation.baseUrl)) == null) {
            rangedUri2 = rangedUri;
        }
        return new InitializationChunk(dataSource, DashUtil.buildDataSpec(representation, rangedUri2, 0), format, i, obj, representationHolder.chunkExtractor);
    }

    protected Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource, int i, Format format, int i2, Object obj, long j, int i3, long j2, long j3) {
        Representation representation = representationHolder.representation;
        long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(j);
        RangedUri segmentUrl = representationHolder.getSegmentUrl(j);
        String str = representation.baseUrl;
        if (representationHolder.chunkExtractor == null) {
            return new SingleSampleMediaChunk(dataSource, DashUtil.buildDataSpec(representation, segmentUrl, representationHolder.isSegmentAvailableAtFullNetworkSpeed(j, j3) ? 0 : 8), format, i2, obj, segmentStartTimeUs, representationHolder.getSegmentEndTimeUs(j), j, i, format);
        }
        int i4 = 1;
        RangedUri rangedUri = segmentUrl;
        int i5 = 1;
        while (i4 < i3) {
            RangedUri attemptMerge = rangedUri.attemptMerge(representationHolder.getSegmentUrl(i4 + j), str);
            if (attemptMerge == null) {
                break;
            }
            i5++;
            i4++;
            rangedUri = attemptMerge;
        }
        long j4 = (i5 + j) - 1;
        long segmentEndTimeUs = representationHolder.getSegmentEndTimeUs(j4);
        long j5 = representationHolder.periodDurationUs;
        return new ContainerMediaChunk(dataSource, DashUtil.buildDataSpec(representation, rangedUri, representationHolder.isSegmentAvailableAtFullNetworkSpeed(j4, j3) ? 0 : 8), format, i2, obj, segmentStartTimeUs, segmentEndTimeUs, j2, (j5 == C.TIME_UNSET || j5 > segmentEndTimeUs) ? -9223372036854775807L : j5, j, i5, -representation.presentationTimeOffsetUs, representationHolder.chunkExtractor);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        ChunkIndex chunkIndex;
        if (chunk instanceof InitializationChunk) {
            int indexOf = this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat);
            RepresentationHolder representationHolder = this.representationHolders[indexOf];
            if (representationHolder.segmentIndex == null && (chunkIndex = representationHolder.chunkExtractor.getChunkIndex()) != null) {
                this.representationHolders[indexOf] = representationHolder.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(chunkIndex, representationHolder.representation.presentationTimeOffsetUs));
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null) {
            playerTrackEmsgHandler.onChunkLoadCompleted(chunk);
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean z, Exception exc, long j) {
        RepresentationHolder representationHolder;
        int segmentCount;
        if (!z) {
            return false;
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null && playerTrackEmsgHandler.onChunkLoadError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (exc instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) exc).responseCode == 404 && (segmentCount = (representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)]).getSegmentCount()) != -1 && segmentCount != 0) {
            if (((MediaChunk) chunk).getNextChunkIndex() > (representationHolder.getFirstSegmentNum() + segmentCount) - 1) {
                this.missingLastSegment = true;
                return true;
            }
        }
        if (j == C.TIME_UNSET) {
            return false;
        }
        ExoTrackSelection exoTrackSelection = this.trackSelection;
        return exoTrackSelection.blacklist(exoTrackSelection.indexOf(chunk.trackFormat), j);
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void release() {
        for (RepresentationHolder representationHolder : this.representationHolders) {
            ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
            if (chunkExtractor != null) {
                chunkExtractor.release();
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public boolean shouldCancelLoad(long j, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j, chunk, list);
    }

    @Override // com.google.android.exoplayer2.source.dash.DashChunkSource
    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = this.manifest.getPeriodDurationUs(this.periodIndex);
            ArrayList<Representation> representations = getRepresentations();
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                this.representationHolders[i2] = this.representationHolders[i2].copyWithNewRepresentation(periodDurationUs, representations.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    @Override // com.google.android.exoplayer2.source.dash.DashChunkSource
    public void updateTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class RepresentationHolder {
        @Nullable
        final ChunkExtractor chunkExtractor;
        private final long periodDurationUs;
        public final Representation representation;
        @Nullable
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;

        RepresentationHolder(long j, int i, Representation representation, boolean z, List<Format> list, @Nullable TrackOutput trackOutput) {
            this(j, representation, createChunkExtractor(i, representation, z, list, trackOutput), 0L, representation.getIndex());
        }

        @Nullable
        private static ChunkExtractor createChunkExtractor(int i, Representation representation, boolean z, List<Format> list, @Nullable TrackOutput trackOutput) {
            Extractor fragmentedMp4Extractor;
            String str = representation.format.containerMimeType;
            if (MimeTypes.isText(str)) {
                if (!MimeTypes.APPLICATION_RAWCC.equals(str)) {
                    return null;
                }
                fragmentedMp4Extractor = new RawCcExtractor(representation.format);
            } else if (MimeTypes.isMatroska(str)) {
                fragmentedMp4Extractor = new MatroskaExtractor(1);
            } else {
                int i2 = 0;
                if (z) {
                    i2 = 4;
                }
                fragmentedMp4Extractor = new FragmentedMp4Extractor(i2, null, null, list, trackOutput);
            }
            return new BundledChunkExtractor(fragmentedMp4Extractor, i, representation.format);
        }

        @CheckResult
        RepresentationHolder copyWithNewRepresentation(long j, Representation representation) throws BehindLiveWindowException {
            long segmentNum;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation.getIndex();
            if (index == null) {
                return new RepresentationHolder(j, representation, this.chunkExtractor, this.segmentNumShift, index);
            }
            if (!index.isExplicit()) {
                return new RepresentationHolder(j, representation, this.chunkExtractor, this.segmentNumShift, index2);
            }
            int segmentCount = index.getSegmentCount(j);
            if (segmentCount == 0) {
                return new RepresentationHolder(j, representation, this.chunkExtractor, this.segmentNumShift, index2);
            }
            long firstSegmentNum = index.getFirstSegmentNum();
            long timeUs = index.getTimeUs(firstSegmentNum);
            long j2 = (segmentCount + firstSegmentNum) - 1;
            long durationUs = index.getDurationUs(j2, j) + index.getTimeUs(j2);
            long firstSegmentNum2 = index2.getFirstSegmentNum();
            long timeUs2 = index2.getTimeUs(firstSegmentNum2);
            long j3 = this.segmentNumShift;
            int i = (durationUs > timeUs2 ? 1 : (durationUs == timeUs2 ? 0 : -1));
            if (i == 0) {
                segmentNum = ((j2 + 1) - firstSegmentNum2) + j3;
            } else if (i < 0) {
                throw new BehindLiveWindowException();
            } else {
                if (timeUs2 < timeUs) {
                    segmentNum = j3 - (index2.getSegmentNum(timeUs, j) - firstSegmentNum);
                } else {
                    segmentNum = (index.getSegmentNum(timeUs2, j) - firstSegmentNum2) + j3;
                }
            }
            return new RepresentationHolder(j, representation, this.chunkExtractor, segmentNum, index2);
        }

        @CheckResult
        RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex dashSegmentIndex) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.chunkExtractor, this.segmentNumShift, dashSegmentIndex);
        }

        public long getFirstAvailableSegmentNum(long j) {
            return this.segmentIndex.getFirstAvailableSegmentNum(this.periodDurationUs, j) + this.segmentNumShift;
        }

        public long getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public long getLastAvailableSegmentNum(long j) {
            return (getFirstAvailableSegmentNum(j) + this.segmentIndex.getAvailableSegmentCount(this.periodDurationUs, j)) - 1;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentEndTimeUs(long j) {
            return this.segmentIndex.getDurationUs(j - this.segmentNumShift, this.periodDurationUs) + getSegmentStartTimeUs(j);
        }

        public long getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public long getSegmentStartTimeUs(long j) {
            return this.segmentIndex.getTimeUs(j - this.segmentNumShift);
        }

        public RangedUri getSegmentUrl(long j) {
            return this.segmentIndex.getSegmentUrl(j - this.segmentNumShift);
        }

        public boolean isSegmentAvailableAtFullNetworkSpeed(long j, long j2) {
            return j2 == C.TIME_UNSET || getSegmentEndTimeUs(j) <= j2;
        }

        private RepresentationHolder(long j, Representation representation, @Nullable ChunkExtractor chunkExtractor, long j2, @Nullable DashSegmentIndex dashSegmentIndex) {
            this.periodDurationUs = j;
            this.representation = representation;
            this.segmentNumShift = j2;
            this.chunkExtractor = chunkExtractor;
            this.segmentIndex = dashSegmentIndex;
        }
    }
}
