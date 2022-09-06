package com.google.android.exoplayer2.upstream.cache;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class CachedContent {
    private static final String TAG = "CachedContent";
    private final TreeSet<SimpleCacheSpan> cachedSpans;
    public final int id;
    public final String key;
    private final ArrayList<Range> lockedRanges;
    private DefaultContentMetadata metadata;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Range {
        public final long length;
        public final long position;

        public Range(long j, long j2) {
            this.position = j;
            this.length = j2;
        }

        public boolean contains(long j, long j2) {
            long j3 = this.length;
            if (j3 == -1) {
                return j >= this.position;
            } else if (j2 == -1) {
                return false;
            } else {
                long j4 = this.position;
                return j4 <= j && j + j2 <= j4 + j3;
            }
        }

        public boolean intersects(long j, long j2) {
            long j3 = this.position;
            if (j3 > j) {
                return j2 == -1 || j + j2 > j3;
            }
            long j4 = this.length;
            return j4 == -1 || j3 + j4 > j;
        }
    }

    public CachedContent(int i, String str) {
        this(i, str, DefaultContentMetadata.EMPTY);
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public boolean applyMetadataMutations(ContentMetadataMutations contentMetadataMutations) {
        DefaultContentMetadata defaultContentMetadata = this.metadata;
        this.metadata = defaultContentMetadata.copyWithMutationsApplied(contentMetadataMutations);
        return !this.metadata.equals(defaultContentMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CachedContent.class != obj.getClass()) {
            return false;
        }
        CachedContent cachedContent = (CachedContent) obj;
        return this.id == cachedContent.id && this.key.equals(cachedContent.key) && this.cachedSpans.equals(cachedContent.cachedSpans) && this.metadata.equals(cachedContent.metadata);
    }

    public long getCachedBytesLength(long j, long j2) {
        boolean z = true;
        Assertions.checkArgument(j >= 0);
        if (j2 < 0) {
            z = false;
        }
        Assertions.checkArgument(z);
        SimpleCacheSpan span = getSpan(j, j2);
        long j3 = Long.MAX_VALUE;
        if (span.isHoleSpan()) {
            if (!span.isOpenEnded()) {
                j3 = span.length;
            }
            return -Math.min(j3, j2);
        }
        long j4 = j + j2;
        if (j4 >= 0) {
            j3 = j4;
        }
        long j5 = span.position + span.length;
        if (j5 < j3) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                long j6 = simpleCacheSpan.position;
                if (j6 > j5) {
                    break;
                }
                j5 = Math.max(j5, j6 + simpleCacheSpan.length);
                if (j5 >= j3) {
                    break;
                }
            }
        }
        return Math.min(j5 - j, j2);
    }

    public DefaultContentMetadata getMetadata() {
        return this.metadata;
    }

    public SimpleCacheSpan getSpan(long j, long j2) {
        SimpleCacheSpan createLookup = SimpleCacheSpan.createLookup(this.key, j);
        SimpleCacheSpan floor = this.cachedSpans.floor(createLookup);
        if (floor == null || floor.position + floor.length <= j) {
            SimpleCacheSpan ceiling = this.cachedSpans.ceiling(createLookup);
            if (ceiling != null) {
                long j3 = ceiling.position - j;
                j2 = j2 == -1 ? j3 : Math.min(j3, j2);
            }
            return SimpleCacheSpan.createHole(this.key, j, j2);
        }
        return floor;
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public int hashCode() {
        return this.metadata.hashCode() + GeneratedOutlineSupport1.outline7(this.key, this.id * 31, 31);
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean isFullyLocked(long j, long j2) {
        for (int i = 0; i < this.lockedRanges.size(); i++) {
            if (this.lockedRanges.get(i).contains(j, j2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullyUnlocked() {
        return this.lockedRanges.isEmpty();
    }

    public boolean lockRange(long j, long j2) {
        for (int i = 0; i < this.lockedRanges.size(); i++) {
            if (this.lockedRanges.get(i).intersects(j, j2)) {
                return false;
            }
        }
        this.lockedRanges.add(new Range(j, j2));
        return true;
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (this.cachedSpans.remove(cacheSpan)) {
            File file = cacheSpan.file;
            if (file == null) {
                return true;
            }
            file.delete();
            return true;
        }
        return false;
    }

    public SimpleCacheSpan setLastTouchTimestamp(SimpleCacheSpan simpleCacheSpan, long j, boolean z) {
        File file;
        Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
        File file2 = (File) Assertions.checkNotNull(simpleCacheSpan.file);
        if (z) {
            file = SimpleCacheSpan.getCacheFile((File) Assertions.checkNotNull(file2.getParentFile()), this.id, simpleCacheSpan.position, j);
            if (!file2.renameTo(file)) {
                Log.w(TAG, "Failed to rename " + file2 + " to " + file);
            }
            SimpleCacheSpan copyWithFileAndLastTouchTimestamp = simpleCacheSpan.copyWithFileAndLastTouchTimestamp(file, j);
            this.cachedSpans.add(copyWithFileAndLastTouchTimestamp);
            return copyWithFileAndLastTouchTimestamp;
        }
        file = file2;
        SimpleCacheSpan copyWithFileAndLastTouchTimestamp2 = simpleCacheSpan.copyWithFileAndLastTouchTimestamp(file, j);
        this.cachedSpans.add(copyWithFileAndLastTouchTimestamp2);
        return copyWithFileAndLastTouchTimestamp2;
    }

    public void unlockRange(long j) {
        for (int i = 0; i < this.lockedRanges.size(); i++) {
            if (this.lockedRanges.get(i).position == j) {
                this.lockedRanges.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public CachedContent(int i, String str, DefaultContentMetadata defaultContentMetadata) {
        this.id = i;
        this.key = str;
        this.metadata = defaultContentMetadata;
        this.cachedSpans = new TreeSet<>();
        this.lockedRanges = new ArrayList<>();
    }
}
