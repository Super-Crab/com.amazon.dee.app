package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class MediaTypeUsage implements Comparable<MediaTypeUsage> {
    private long count;
    private String lastUploadedTime;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof MediaTypeUsage) && compareTo((MediaTypeUsage) obj) == 0;
    }

    public long getCount() {
        return this.count;
    }

    public String getLastUploadedTime() {
        return this.lastUploadedTime;
    }

    public int hashCode() {
        return (getLastUploadedTime() == null ? 0 : getLastUploadedTime().hashCode()) + 1 + ((int) getCount());
    }

    public void setCount(long j) {
        this.count = j;
    }

    public void setLastUploadedTime(String str) {
        this.lastUploadedTime = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(MediaTypeUsage mediaTypeUsage) {
        if (mediaTypeUsage == null) {
            return -1;
        }
        if (mediaTypeUsage == this) {
            return 0;
        }
        String lastUploadedTime = getLastUploadedTime();
        String lastUploadedTime2 = mediaTypeUsage.getLastUploadedTime();
        if (lastUploadedTime != lastUploadedTime2) {
            if (lastUploadedTime == null) {
                return -1;
            }
            if (lastUploadedTime2 == null) {
                return 1;
            }
            int compareTo = lastUploadedTime.compareTo(lastUploadedTime2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (getCount() < mediaTypeUsage.getCount()) {
            return -1;
        }
        return getCount() > mediaTypeUsage.getCount() ? 1 : 0;
    }
}
