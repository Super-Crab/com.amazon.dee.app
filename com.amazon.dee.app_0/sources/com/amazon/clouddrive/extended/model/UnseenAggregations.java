package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class UnseenAggregations implements Comparable<UnseenAggregations> {
    private long groupCount;

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof UnseenAggregations) && compareTo((UnseenAggregations) obj) == 0);
    }

    public long getGroupCount() {
        return this.groupCount;
    }

    public int hashCode() {
        return (int) (getGroupCount() ^ (getGroupCount() >>> 32));
    }

    public void setGroupCount(long j) {
        this.groupCount = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(UnseenAggregations unseenAggregations) {
        long j = this.groupCount;
        long j2 = unseenAggregations.groupCount;
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }
}
