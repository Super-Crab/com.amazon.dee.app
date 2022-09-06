package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class ContentAggregations implements Comparable<ContentAggregations> {
    private long albumCount;
    private long photoCount;
    private long reactionCount;
    private long videoCount;

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ContentAggregations) && compareTo((ContentAggregations) obj) == 0);
    }

    public long getAlbumCount() {
        return this.albumCount;
    }

    public long getPhotoCount() {
        return this.photoCount;
    }

    public long getReactionCount() {
        return this.reactionCount;
    }

    public long getVideoCount() {
        return this.videoCount;
    }

    public int hashCode() {
        return (((((((int) (getAlbumCount() ^ (getAlbumCount() >>> 32))) * 31) + ((int) (getPhotoCount() ^ (getPhotoCount() >>> 32)))) * 31) + ((int) (getVideoCount() ^ (getVideoCount() >>> 32)))) * 31) + ((int) (getReactionCount() ^ (getReactionCount() >>> 32)));
    }

    public void setAlbumCount(long j) {
        this.albumCount = j;
    }

    public void setPhotoCount(long j) {
        this.photoCount = j;
    }

    public void setReactionCount(long j) {
        this.reactionCount = j;
    }

    public void setVideoCount(long j) {
        this.videoCount = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(ContentAggregations contentAggregations) {
        long j = this.photoCount;
        long j2 = contentAggregations.photoCount;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        long j3 = this.videoCount;
        long j4 = contentAggregations.videoCount;
        if (j3 < j4) {
            return -1;
        }
        if (j3 > j4) {
            return 1;
        }
        long j5 = this.albumCount;
        long j6 = contentAggregations.albumCount;
        if (j5 < j6) {
            return -1;
        }
        if (j5 > j6) {
            return 1;
        }
        long j7 = this.reactionCount;
        long j8 = contentAggregations.reactionCount;
        if (j7 < j8) {
            return -1;
        }
        return j7 > j8 ? 1 : 0;
    }
}
