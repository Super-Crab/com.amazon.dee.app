package com.google.android.exoplayer2.upstream.cache;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import java.io.File;
/* loaded from: classes2.dex */
public class CacheSpan implements Comparable<CacheSpan> {
    @Nullable
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastTouchTimestamp;
    public final long length;
    public final long position;

    public CacheSpan(String str, long j, long j2) {
        this(str, j, j2, C.TIME_UNSET, null);
    }

    public boolean isHoleSpan() {
        return !this.isCached;
    }

    public boolean isOpenEnded() {
        return this.length == -1;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(this.position);
        outline107.append(", ");
        return GeneratedOutlineSupport1.outline87(outline107, this.length, "]");
    }

    public CacheSpan(String str, long j, long j2, long j3, @Nullable File file) {
        this.key = str;
        this.position = j;
        this.length = j2;
        this.isCached = file != null;
        this.file = file;
        this.lastTouchTimestamp = j3;
    }

    @Override // java.lang.Comparable
    public int compareTo(CacheSpan cacheSpan) {
        if (!this.key.equals(cacheSpan.key)) {
            return this.key.compareTo(cacheSpan.key);
        }
        int i = ((this.position - cacheSpan.position) > 0L ? 1 : ((this.position - cacheSpan.position) == 0L ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }
}
