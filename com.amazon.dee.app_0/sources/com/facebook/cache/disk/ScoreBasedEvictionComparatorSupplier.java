package com.facebook.cache.disk;

import androidx.annotation.VisibleForTesting;
import com.facebook.cache.disk.DiskStorage;
/* loaded from: classes2.dex */
public class ScoreBasedEvictionComparatorSupplier implements EntryEvictionComparatorSupplier {
    private final float mAgeWeight;
    private final float mSizeWeight;

    public ScoreBasedEvictionComparatorSupplier(float ageWeight, float sizeWeight) {
        this.mAgeWeight = ageWeight;
        this.mSizeWeight = sizeWeight;
    }

    @VisibleForTesting
    float calculateScore(DiskStorage.Entry entry, long now) {
        long timestamp = now - entry.getTimestamp();
        long size = entry.getSize();
        return (this.mSizeWeight * ((float) size)) + (this.mAgeWeight * ((float) timestamp));
    }

    @Override // com.facebook.cache.disk.EntryEvictionComparatorSupplier
    public EntryEvictionComparator get() {
        return new EntryEvictionComparator() { // from class: com.facebook.cache.disk.ScoreBasedEvictionComparatorSupplier.1
            long now = System.currentTimeMillis();

            @Override // java.util.Comparator
            public int compare(DiskStorage.Entry lhs, DiskStorage.Entry rhs) {
                float calculateScore = ScoreBasedEvictionComparatorSupplier.this.calculateScore(lhs, this.now);
                float calculateScore2 = ScoreBasedEvictionComparatorSupplier.this.calculateScore(rhs, this.now);
                if (calculateScore < calculateScore2) {
                    return 1;
                }
                return calculateScore2 == calculateScore ? 0 : -1;
            }
        };
    }
}
