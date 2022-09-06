package com.google.android.exoplayer2.extractor;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class ChunkIndex implements SeekMap {
    private final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.sizes = iArr;
        this.offsets = jArr;
        this.durationsUs = jArr2;
        this.timesUs = jArr3;
        this.length = iArr.length;
        int i = this.length;
        if (i > 0) {
            this.durationUs = jArr2[i - 1] + jArr3[i - 1];
        } else {
            this.durationUs = 0L;
        }
    }

    public int getChunkIndex(long j) {
        return Util.binarySearchFloor(this.timesUs, j, true, true);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        int chunkIndex = getChunkIndex(j);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[chunkIndex], this.offsets[chunkIndex]);
        if (seekPoint.timeUs < j && chunkIndex != this.length - 1) {
            int i = chunkIndex + 1;
            return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i], this.offsets[i]));
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ChunkIndex(length=");
        outline107.append(this.length);
        outline107.append(", sizes=");
        outline107.append(Arrays.toString(this.sizes));
        outline107.append(", offsets=");
        outline107.append(Arrays.toString(this.offsets));
        outline107.append(", timeUs=");
        outline107.append(Arrays.toString(this.timesUs));
        outline107.append(", durationsUs=");
        outline107.append(Arrays.toString(this.durationsUs));
        outline107.append(")");
        return outline107.toString();
    }
}
