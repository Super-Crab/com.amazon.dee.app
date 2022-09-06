package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
/* loaded from: classes2.dex */
public final class IndexSeekMap implements SeekMap {
    private final long durationUs;
    private final boolean isSeekable;
    private final long[] positions;
    private final long[] timesUs;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j) {
        Assertions.checkArgument(jArr.length == jArr2.length);
        int length = jArr2.length;
        this.isSeekable = length > 0;
        if (this.isSeekable && jArr2[0] > 0) {
            int i = length + 1;
            this.positions = new long[i];
            this.timesUs = new long[i];
            System.arraycopy(jArr, 0, this.positions, 1, length);
            System.arraycopy(jArr2, 0, this.timesUs, 1, length);
        } else {
            this.positions = jArr;
            this.timesUs = jArr2;
        }
        this.durationUs = j;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        if (!this.isSeekable) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs != j) {
            long[] jArr = this.timesUs;
            if (binarySearchFloor != jArr.length - 1) {
                int i = binarySearchFloor + 1;
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(jArr[i], this.positions[i]));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.isSeekable;
    }
}
