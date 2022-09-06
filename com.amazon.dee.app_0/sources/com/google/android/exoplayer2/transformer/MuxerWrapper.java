package com.google.android.exoplayer2.transformer;

import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
@RequiresApi(18)
/* loaded from: classes2.dex */
final class MuxerWrapper {
    private static final long MAX_TRACK_WRITE_AHEAD_US = C.msToUs(500);
    private boolean isReady;
    private long minTrackTimeUs;
    private final Muxer muxer;
    private int trackCount;
    private int trackFormatCount;
    private final SparseIntArray trackTypeToIndex = new SparseIntArray();
    private final SparseLongArray trackTypeToTimeUs = new SparseLongArray();
    private int previousTrackType = 7;

    public MuxerWrapper(Muxer muxer) {
        this.muxer = muxer;
    }

    private boolean canWriteSampleOfType(int i) {
        long j = this.trackTypeToTimeUs.get(i, C.TIME_UNSET);
        Assertions.checkState(j != C.TIME_UNSET);
        if (!this.isReady) {
            return false;
        }
        if (this.trackTypeToTimeUs.size() == 1) {
            return true;
        }
        if (i != this.previousTrackType) {
            this.minTrackTimeUs = Util.minValue(this.trackTypeToTimeUs);
        }
        return j - this.minTrackTimeUs <= MAX_TRACK_WRITE_AHEAD_US;
    }

    public void addTrackFormat(Format format) {
        boolean z = false;
        Assertions.checkState(this.trackCount > 0, "All tracks should be registered before the formats are added.");
        Assertions.checkState(this.trackFormatCount < this.trackCount, "All track formats have already been added.");
        String str = format.sampleMimeType;
        Assertions.checkState(MimeTypes.isAudio(str) || MimeTypes.isVideo(str), "Unsupported track format: " + str);
        int trackType = MimeTypes.getTrackType(str);
        if (this.trackTypeToIndex.get(trackType, -1) == -1) {
            z = true;
        }
        Assertions.checkState(z, "There is already a track of type " + trackType);
        this.trackTypeToIndex.put(trackType, this.muxer.addTrack(format));
        this.trackTypeToTimeUs.put(trackType, 0L);
        this.trackFormatCount = this.trackFormatCount + 1;
        if (this.trackFormatCount == this.trackCount) {
            this.isReady = true;
        }
    }

    public void endTrack(int i) {
        this.trackTypeToIndex.delete(i);
        this.trackTypeToTimeUs.delete(i);
    }

    public int getTrackCount() {
        return this.trackCount;
    }

    public void registerTrack() {
        Assertions.checkState(this.trackFormatCount == 0, "Tracks cannot be registered after track formats have been added.");
        this.trackCount++;
    }

    public void release(boolean z) {
        this.isReady = false;
        this.muxer.release(z);
    }

    public boolean supportsSampleMimeType(@Nullable String str) {
        return this.muxer.supportsSampleMimeType(str);
    }

    public boolean writeSample(int i, @Nullable ByteBuffer byteBuffer, boolean z, long j) {
        int i2 = this.trackTypeToIndex.get(i, -1);
        boolean z2 = i2 != -1;
        Assertions.checkState(z2, "Could not write sample because there is no track of type " + i);
        if (!canWriteSampleOfType(i)) {
            return false;
        }
        if (byteBuffer == null) {
            return true;
        }
        this.muxer.writeSampleData(i2, byteBuffer, z, j);
        this.trackTypeToTimeUs.put(i, j);
        this.previousTrackType = i;
        return true;
    }
}
