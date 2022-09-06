package com.google.android.exoplayer2.source;

import android.os.Looper;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
/* loaded from: classes2.dex */
public class SampleQueue implements TrackOutput {
    @VisibleForTesting
    static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private static final String TAG = "SampleQueue";
    private int absoluteFirstIndex;
    private TrackOutput.CryptoData[] cryptoDatas;
    @Nullable
    private DrmSession currentDrmSession;
    @Nullable
    private Format downstreamFormat;
    @Nullable
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    @Nullable
    private final DrmSessionManager drmSessionManager;
    private int[] flags;
    private Format[] formats;
    private boolean isLastSampleQueued;
    private long largestDiscardedTimestampUs;
    private long largestQueuedTimestampUs;
    private int length;
    private boolean loggedUnexpectedNonSyncSample;
    private long[] offsets;
    private boolean pendingSplice;
    @Nullable
    private final Looper playbackLooper;
    private int readPosition;
    private int relativeFirstIndex;
    private final SampleDataQueue sampleDataQueue;
    private long sampleOffsetUs;
    private int[] sizes;
    private int[] sourceIds;
    private long startTimeUs;
    private long[] timesUs;
    @Nullable
    private Format unadjustedUpstreamFormat;
    private boolean upstreamAllSamplesAreSyncSamples;
    @Nullable
    private Format upstreamCommittedFormat;
    @Nullable
    private Format upstreamFormat;
    private boolean upstreamFormatAdjustmentRequired;
    @Nullable
    private UpstreamFormatChangedListener upstreamFormatChangeListener;
    private boolean upstreamFormatRequired;
    private boolean upstreamKeyframeRequired;
    private int upstreamSourceId;
    private final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    private int capacity = 1000;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class SampleExtrasHolder {
        @Nullable
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;

        SampleExtrasHolder() {
        }
    }

    /* loaded from: classes2.dex */
    public interface UpstreamFormatChangedListener {
        void onUpstreamFormatChanged(Format format);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SampleQueue(Allocator allocator, @Nullable Looper looper, @Nullable DrmSessionManager drmSessionManager, @Nullable DrmSessionEventListener.EventDispatcher eventDispatcher) {
        this.playbackLooper = looper;
        this.drmSessionManager = drmSessionManager;
        this.drmEventDispatcher = eventDispatcher;
        this.sampleDataQueue = new SampleDataQueue(allocator);
        int i = this.capacity;
        this.sourceIds = new int[i];
        this.offsets = new long[i];
        this.timesUs = new long[i];
        this.flags = new int[i];
        this.sizes = new int[i];
        this.cryptoDatas = new TrackOutput.CryptoData[i];
        this.formats = new Format[i];
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.upstreamFormatRequired = true;
        this.upstreamKeyframeRequired = true;
    }

    private synchronized boolean attemptSplice(long j) {
        boolean z = true;
        if (this.length == 0) {
            if (j <= this.largestDiscardedTimestampUs) {
                z = false;
            }
            return z;
        } else if (getLargestReadTimestampUs() >= j) {
            return false;
        } else {
            discardUpstreamSampleMetadata(this.absoluteFirstIndex + countUnreadSamplesBefore(j));
            return true;
        }
    }

    private synchronized void commitSample(long j, int i, long j2, int i2, @Nullable TrackOutput.CryptoData cryptoData) {
        if (this.length > 0) {
            int relativeIndex = getRelativeIndex(this.length - 1);
            Assertions.checkArgument(this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]) <= j2);
        }
        this.isLastSampleQueued = (536870912 & i) != 0;
        this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
        int relativeIndex2 = getRelativeIndex(this.length);
        this.timesUs[relativeIndex2] = j;
        this.offsets[relativeIndex2] = j2;
        this.sizes[relativeIndex2] = i2;
        this.flags[relativeIndex2] = i;
        this.cryptoDatas[relativeIndex2] = cryptoData;
        this.formats[relativeIndex2] = this.upstreamFormat;
        this.sourceIds[relativeIndex2] = this.upstreamSourceId;
        this.upstreamCommittedFormat = this.upstreamFormat;
        this.length++;
        if (this.length == this.capacity) {
            int i3 = this.capacity + 1000;
            int[] iArr = new int[i3];
            long[] jArr = new long[i3];
            long[] jArr2 = new long[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i3];
            Format[] formatArr = new Format[i3];
            int i4 = this.capacity - this.relativeFirstIndex;
            System.arraycopy(this.offsets, this.relativeFirstIndex, jArr, 0, i4);
            System.arraycopy(this.timesUs, this.relativeFirstIndex, jArr2, 0, i4);
            System.arraycopy(this.flags, this.relativeFirstIndex, iArr2, 0, i4);
            System.arraycopy(this.sizes, this.relativeFirstIndex, iArr3, 0, i4);
            System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, cryptoDataArr, 0, i4);
            System.arraycopy(this.formats, this.relativeFirstIndex, formatArr, 0, i4);
            System.arraycopy(this.sourceIds, this.relativeFirstIndex, iArr, 0, i4);
            int i5 = this.relativeFirstIndex;
            System.arraycopy(this.offsets, 0, jArr, i4, i5);
            System.arraycopy(this.timesUs, 0, jArr2, i4, i5);
            System.arraycopy(this.flags, 0, iArr2, i4, i5);
            System.arraycopy(this.sizes, 0, iArr3, i4, i5);
            System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i4, i5);
            System.arraycopy(this.formats, 0, formatArr, i4, i5);
            System.arraycopy(this.sourceIds, 0, iArr, i4, i5);
            this.offsets = jArr;
            this.timesUs = jArr2;
            this.flags = iArr2;
            this.sizes = iArr3;
            this.cryptoDatas = cryptoDataArr;
            this.formats = formatArr;
            this.sourceIds = iArr;
            this.relativeFirstIndex = 0;
            this.capacity = i3;
        }
    }

    private int countUnreadSamplesBefore(long j) {
        int i = this.length;
        int relativeIndex = getRelativeIndex(i - 1);
        while (i > this.readPosition && this.timesUs[relativeIndex] >= j) {
            i--;
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return i;
    }

    public static SampleQueue createWithDrm(Allocator allocator, Looper looper, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        return new SampleQueue(allocator, (Looper) Assertions.checkNotNull(looper), (DrmSessionManager) Assertions.checkNotNull(drmSessionManager), (DrmSessionEventListener.EventDispatcher) Assertions.checkNotNull(eventDispatcher));
    }

    public static SampleQueue createWithoutDrm(Allocator allocator) {
        return new SampleQueue(allocator, null, null, null);
    }

    private synchronized long discardSampleMetadataTo(long j, boolean z, boolean z2) {
        if (this.length != 0 && j >= this.timesUs[this.relativeFirstIndex]) {
            int findSampleBefore = findSampleBefore(this.relativeFirstIndex, (!z2 || this.readPosition == this.length) ? this.length : this.readPosition + 1, j, z);
            if (findSampleBefore == -1) {
                return -1L;
            }
            return discardSamples(findSampleBefore);
        }
        return -1L;
    }

    private synchronized long discardSampleMetadataToEnd() {
        if (this.length == 0) {
            return -1L;
        }
        return discardSamples(this.length);
    }

    private long discardSamples(int i) {
        int i2;
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        this.length -= i;
        this.absoluteFirstIndex += i;
        this.relativeFirstIndex += i;
        int i3 = this.relativeFirstIndex;
        int i4 = this.capacity;
        if (i3 >= i4) {
            this.relativeFirstIndex = i3 - i4;
        }
        this.readPosition -= i;
        if (this.readPosition < 0) {
            this.readPosition = 0;
        }
        if (this.length == 0) {
            int i5 = this.relativeFirstIndex;
            if (i5 == 0) {
                i5 = this.capacity;
            }
            return this.offsets[i5 - 1] + this.sizes[i2];
        }
        return this.offsets[this.relativeFirstIndex];
    }

    private long discardUpstreamSampleMetadata(int i) {
        int relativeIndex;
        int writeIndex = getWriteIndex() - i;
        boolean z = false;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        this.length -= writeIndex;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.length));
        if (writeIndex == 0 && this.isLastSampleQueued) {
            z = true;
        }
        this.isLastSampleQueued = z;
        int i2 = this.length;
        if (i2 != 0) {
            return this.offsets[getRelativeIndex(i2 - 1)] + this.sizes[relativeIndex];
        }
        return 0L;
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        int i4 = i;
        for (int i5 = 0; i5 < i2 && this.timesUs[i4] <= j; i5++) {
            if (!z || (this.flags[i4] & 1) != 0) {
                if (this.timesUs[i4] == j) {
                    return i5;
                }
                i3 = i5;
            }
            i4++;
            if (i4 == this.capacity) {
                i4 = 0;
            }
        }
        return i3;
    }

    private long getLargestTimestamp(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }

    private boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    private boolean mayReadSample(int i) {
        DrmSession drmSession = this.currentDrmSession;
        return drmSession == null || drmSession.getState() == 4 || ((this.flags[i] & 1073741824) == 0 && this.currentDrmSession.playClearSamplesWithoutKeys());
    }

    private void onFormatResult(Format format, FormatHolder formatHolder) {
        boolean z = this.downstreamFormat == null;
        DrmInitData drmInitData = z ? null : this.downstreamFormat.drmInitData;
        this.downstreamFormat = format;
        DrmInitData drmInitData2 = format.drmInitData;
        DrmSessionManager drmSessionManager = this.drmSessionManager;
        formatHolder.format = drmSessionManager != null ? format.copyWithExoMediaCryptoType(drmSessionManager.getExoMediaCryptoType(format)) : format;
        formatHolder.drmSession = this.currentDrmSession;
        if (this.drmSessionManager == null) {
            return;
        }
        if (!z && Util.areEqual(drmInitData, drmInitData2)) {
            return;
        }
        DrmSession drmSession = this.currentDrmSession;
        this.currentDrmSession = this.drmSessionManager.acquireSession((Looper) Assertions.checkNotNull(this.playbackLooper), this.drmEventDispatcher, format);
        formatHolder.drmSession = this.currentDrmSession;
        if (drmSession == null) {
            return;
        }
        drmSession.release(this.drmEventDispatcher);
    }

    private synchronized int peekSampleMetadata(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2, SampleExtrasHolder sampleExtrasHolder) {
        decoderInputBuffer.waitingForKeys = false;
        if (!hasNextSample()) {
            if (!z2 && !this.isLastSampleQueued) {
                if (this.upstreamFormat == null || (!z && this.upstreamFormat == this.downstreamFormat)) {
                    return -3;
                }
                onFormatResult((Format) Assertions.checkNotNull(this.upstreamFormat), formatHolder);
                return -5;
            }
            decoderInputBuffer.setFlags(4);
            return -4;
        }
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (!z && this.formats[relativeIndex] == this.downstreamFormat) {
            if (!mayReadSample(relativeIndex)) {
                decoderInputBuffer.waitingForKeys = true;
                return -3;
            }
            decoderInputBuffer.setFlags(this.flags[relativeIndex]);
            decoderInputBuffer.timeUs = this.timesUs[relativeIndex];
            if (decoderInputBuffer.timeUs < this.startTimeUs) {
                decoderInputBuffer.addFlag(Integer.MIN_VALUE);
            }
            sampleExtrasHolder.size = this.sizes[relativeIndex];
            sampleExtrasHolder.offset = this.offsets[relativeIndex];
            sampleExtrasHolder.cryptoData = this.cryptoDatas[relativeIndex];
            return -4;
        }
        onFormatResult(this.formats[relativeIndex], formatHolder);
        return -5;
    }

    private void releaseDrmSessionReferences() {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession != null) {
            drmSession.release(this.drmEventDispatcher);
            this.currentDrmSession = null;
            this.downstreamFormat = null;
        }
    }

    private synchronized void rewind() {
        this.readPosition = 0;
        this.sampleDataQueue.rewind();
    }

    private synchronized boolean setUpstreamFormat(Format format) {
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        if (Util.areEqual(format, this.upstreamCommittedFormat)) {
            this.upstreamFormat = this.upstreamCommittedFormat;
        } else {
            this.upstreamFormat = format;
        }
        this.upstreamAllSamplesAreSyncSamples = MimeTypes.allSamplesAreSyncSamples(this.upstreamFormat.sampleMimeType, this.upstreamFormat.codecs);
        this.loggedUnexpectedNonSyncSample = false;
        return true;
    }

    public synchronized long discardSampleMetadataToRead() {
        if (this.readPosition == 0) {
            return -1L;
        }
        return discardSamples(this.readPosition);
    }

    public final void discardTo(long j, boolean z, boolean z2) {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataTo(j, z, z2));
    }

    public final void discardToEnd() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToEnd());
    }

    public final void discardToRead() {
        this.sampleDataQueue.discardDownstreamTo(discardSampleMetadataToRead());
    }

    public final void discardUpstreamFrom(long j) {
        if (this.length == 0) {
            return;
        }
        Assertions.checkArgument(j > getLargestReadTimestampUs());
        discardUpstreamSamples(this.absoluteFirstIndex + countUnreadSamplesBefore(j));
    }

    public final void discardUpstreamSamples(int i) {
        this.sampleDataQueue.discardUpstreamSampleBytes(discardUpstreamSampleMetadata(i));
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public final void format(Format format) {
        Format adjustedUpstreamFormat = getAdjustedUpstreamFormat(format);
        this.upstreamFormatAdjustmentRequired = false;
        this.unadjustedUpstreamFormat = format;
        boolean upstreamFormat = setUpstreamFormat(adjustedUpstreamFormat);
        UpstreamFormatChangedListener upstreamFormatChangedListener = this.upstreamFormatChangeListener;
        if (upstreamFormatChangedListener == null || !upstreamFormat) {
            return;
        }
        upstreamFormatChangedListener.onUpstreamFormatChanged(adjustedUpstreamFormat);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @CallSuper
    public Format getAdjustedUpstreamFormat(Format format) {
        return (this.sampleOffsetUs == 0 || format.subsampleOffsetUs == Long.MAX_VALUE) ? format : format.buildUpon().setSubsampleOffsetUs(format.subsampleOffsetUs + this.sampleOffsetUs).build();
    }

    public final int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public final synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public final synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public final synchronized long getLargestReadTimestampUs() {
        return Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition));
    }

    public final int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public final synchronized int getSkipCount(long j, boolean z) {
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (hasNextSample() && j >= this.timesUs[relativeIndex]) {
            if (j > this.largestQueuedTimestampUs && z) {
                return this.length - this.readPosition;
            }
            int findSampleBefore = findSampleBefore(relativeIndex, this.length - this.readPosition, j, true);
            if (findSampleBefore != -1) {
                return findSampleBefore;
            }
            return 0;
        }
        return 0;
    }

    @Nullable
    public final synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public final int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void invalidateUpstreamFormatAdjustment() {
        this.upstreamFormatAdjustmentRequired = true;
    }

    public final synchronized boolean isLastSampleQueued() {
        return this.isLastSampleQueued;
    }

    @CallSuper
    public synchronized boolean isReady(boolean z) {
        boolean z2 = true;
        if (!hasNextSample()) {
            if (!z && !this.isLastSampleQueued && (this.upstreamFormat == null || this.upstreamFormat == this.downstreamFormat)) {
                z2 = false;
            }
            return z2;
        }
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (this.formats[relativeIndex] != this.downstreamFormat) {
            return true;
        }
        return mayReadSample(relativeIndex);
    }

    @CallSuper
    public void maybeThrowError() throws IOException {
        DrmSession drmSession = this.currentDrmSession;
        if (drmSession == null || drmSession.getState() != 1) {
            return;
        }
        throw ((DrmSession.DrmSessionException) Assertions.checkNotNull(this.currentDrmSession.getError()));
    }

    public final int peek(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2) {
        int peekSampleMetadata = peekSampleMetadata(formatHolder, decoderInputBuffer, z, z2, this.extrasHolder);
        if (peekSampleMetadata == -4 && !decoderInputBuffer.isEndOfStream() && !decoderInputBuffer.isFlagsOnly()) {
            this.sampleDataQueue.peekToBuffer(decoderInputBuffer, this.extrasHolder);
        }
        return peekSampleMetadata;
    }

    public final synchronized int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    @CallSuper
    public void preRelease() {
        discardToEnd();
        releaseDrmSessionReferences();
    }

    @CallSuper
    public int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2) {
        int peekSampleMetadata = peekSampleMetadata(formatHolder, decoderInputBuffer, z, z2, this.extrasHolder);
        if (peekSampleMetadata == -4 && !decoderInputBuffer.isEndOfStream() && !decoderInputBuffer.isFlagsOnly()) {
            this.sampleDataQueue.readToBuffer(decoderInputBuffer, this.extrasHolder);
            this.readPosition++;
        }
        return peekSampleMetadata;
    }

    @CallSuper
    public void release() {
        reset(true);
        releaseDrmSessionReferences();
    }

    public final void reset() {
        reset(false);
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public final int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        return this.sampleDataQueue.sampleData(dataReader, i, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sampleMetadata(long r12, int r14, int r15, int r16, @androidx.annotation.Nullable com.google.android.exoplayer2.extractor.TrackOutput.CryptoData r17) {
        /*
            r11 = this;
            r8 = r11
            boolean r0 = r8.upstreamFormatAdjustmentRequired
            if (r0 == 0) goto L10
            com.google.android.exoplayer2.Format r0 = r8.unadjustedUpstreamFormat
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.checkStateNotNull(r0)
            com.google.android.exoplayer2.Format r0 = (com.google.android.exoplayer2.Format) r0
            r11.format(r0)
        L10:
            r0 = r14 & 1
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L18
            r3 = r2
            goto L19
        L18:
            r3 = r1
        L19:
            boolean r4 = r8.upstreamKeyframeRequired
            if (r4 == 0) goto L22
            if (r3 != 0) goto L20
            return
        L20:
            r8.upstreamKeyframeRequired = r1
        L22:
            long r4 = r8.sampleOffsetUs
            long r4 = r4 + r12
            boolean r6 = r8.upstreamAllSamplesAreSyncSamples
            if (r6 == 0) goto L50
            long r6 = r8.startTimeUs
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 >= 0) goto L30
            return
        L30:
            if (r0 != 0) goto L50
            boolean r0 = r8.loggedUnexpectedNonSyncSample
            if (r0 != 0) goto L4c
            java.lang.String r0 = "Overriding unexpected non-sync sample for format: "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            com.google.android.exoplayer2.Format r6 = r8.upstreamFormat
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r6 = "SampleQueue"
            android.util.Log.w(r6, r0)
            r8.loggedUnexpectedNonSyncSample = r2
        L4c:
            r0 = r14 | 1
            r6 = r0
            goto L51
        L50:
            r6 = r14
        L51:
            boolean r0 = r8.pendingSplice
            if (r0 == 0) goto L62
            if (r3 == 0) goto L61
            boolean r0 = r11.attemptSplice(r4)
            if (r0 != 0) goto L5e
            goto L61
        L5e:
            r8.pendingSplice = r1
            goto L62
        L61:
            return
        L62:
            com.google.android.exoplayer2.source.SampleDataQueue r0 = r8.sampleDataQueue
            long r0 = r0.getTotalBytesWritten()
            r7 = r15
            long r2 = (long) r7
            long r0 = r0 - r2
            r2 = r16
            long r2 = (long) r2
            long r9 = r0 - r2
            r0 = r11
            r1 = r4
            r3 = r6
            r4 = r9
            r6 = r15
            r7 = r17
            r0.commitSample(r1, r3, r4, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SampleQueue.sampleMetadata(long, int, int, int, com.google.android.exoplayer2.extractor.TrackOutput$CryptoData):void");
    }

    public final synchronized boolean seekTo(int i) {
        rewind();
        if (i >= this.absoluteFirstIndex && i <= this.absoluteFirstIndex + this.length) {
            this.startTimeUs = Long.MIN_VALUE;
            this.readPosition = i - this.absoluteFirstIndex;
            return true;
        }
        return false;
    }

    public final void setSampleOffsetUs(long j) {
        if (this.sampleOffsetUs != j) {
            this.sampleOffsetUs = j;
            invalidateUpstreamFormatAdjustment();
        }
    }

    public final void setStartTimeUs(long j) {
        this.startTimeUs = j;
    }

    public final void setUpstreamFormatChangeListener(@Nullable UpstreamFormatChangedListener upstreamFormatChangedListener) {
        this.upstreamFormatChangeListener = upstreamFormatChangedListener;
    }

    public final synchronized void skip(int i) {
        boolean z;
        if (i >= 0) {
            try {
                if (this.readPosition + i <= this.length) {
                    z = true;
                    Assertions.checkArgument(z);
                    this.readPosition += i;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        z = false;
        Assertions.checkArgument(z);
        this.readPosition += i;
    }

    public final void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public final void splice() {
        this.pendingSplice = true;
    }

    @CallSuper
    public void reset(boolean z) {
        this.sampleDataQueue.reset();
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.startTimeUs = Long.MIN_VALUE;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.isLastSampleQueued = false;
        this.upstreamCommittedFormat = null;
        if (z) {
            this.unadjustedUpstreamFormat = null;
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.TrackOutput
    public final void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        this.sampleDataQueue.sampleData(parsableByteArray, i);
    }

    public final synchronized boolean seekTo(long j, boolean z) {
        rewind();
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (hasNextSample() && j >= this.timesUs[relativeIndex] && (j <= this.largestQueuedTimestampUs || z)) {
            int findSampleBefore = findSampleBefore(relativeIndex, this.length - this.readPosition, j, true);
            if (findSampleBefore == -1) {
                return false;
            }
            this.startTimeUs = j;
            this.readPosition += findSampleBefore;
            return true;
        }
        return false;
    }
}
