package com.google.android.exoplayer2.extractor;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public final class SeekPoint {
    public static final SeekPoint START = new SeekPoint(0, 0);
    public final long position;
    public final long timeUs;

    public SeekPoint(long j, long j2) {
        this.timeUs = j;
        this.position = j2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SeekPoint.class != obj.getClass()) {
            return false;
        }
        SeekPoint seekPoint = (SeekPoint) obj;
        return this.timeUs == seekPoint.timeUs && this.position == seekPoint.position;
    }

    public int hashCode() {
        return (((int) this.timeUs) * 31) + ((int) this.position);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[timeUs=");
        outline107.append(this.timeUs);
        outline107.append(", position=");
        return GeneratedOutlineSupport1.outline87(outline107, this.position, "]");
    }
}
