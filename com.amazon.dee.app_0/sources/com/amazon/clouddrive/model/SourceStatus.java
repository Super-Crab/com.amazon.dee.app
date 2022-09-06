package com.amazon.clouddrive.model;

import java.util.Map;
/* loaded from: classes11.dex */
public class SourceStatus implements Comparable<SourceStatus> {
    private long cacheSize;
    private boolean isDownloading;
    private boolean isUploading;
    private Map<String, MediaTypeUploadStatus> uploadStatus;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SourceStatus) && compareTo((SourceStatus) obj) == 0;
    }

    public long getCacheSize() {
        return this.cacheSize;
    }

    public Map<String, MediaTypeUploadStatus> getUploadStatus() {
        return this.uploadStatus;
    }

    public int hashCode() {
        return (getUploadStatus() == null ? 0 : getUploadStatus().hashCode()) + 1 + (isIsUploading() ? 1 : 0) + ((int) getCacheSize()) + (isIsDownloading() ? 1 : 0);
    }

    public boolean isIsDownloading() {
        return this.isDownloading;
    }

    public boolean isIsUploading() {
        return this.isUploading;
    }

    public void setCacheSize(long j) {
        this.cacheSize = j;
    }

    public void setIsDownloading(boolean z) {
        this.isDownloading = z;
    }

    public void setIsUploading(boolean z) {
        this.isUploading = z;
    }

    public void setUploadStatus(Map<String, MediaTypeUploadStatus> map) {
        this.uploadStatus = map;
    }

    @Override // java.lang.Comparable
    public int compareTo(SourceStatus sourceStatus) {
        if (sourceStatus == null) {
            return -1;
        }
        if (sourceStatus == this) {
            return 0;
        }
        Map<String, MediaTypeUploadStatus> uploadStatus = getUploadStatus();
        Map<String, MediaTypeUploadStatus> uploadStatus2 = sourceStatus.getUploadStatus();
        if (uploadStatus != uploadStatus2) {
            if (uploadStatus == null) {
                return -1;
            }
            if (uploadStatus2 == null) {
                return 1;
            }
            if (uploadStatus instanceof Comparable) {
                int compareTo = ((Comparable) uploadStatus).compareTo(uploadStatus2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!uploadStatus.equals(uploadStatus2)) {
                int hashCode = uploadStatus.hashCode();
                int hashCode2 = uploadStatus2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        if (!isIsUploading() && sourceStatus.isIsUploading()) {
            return -1;
        }
        if (isIsUploading() && !sourceStatus.isIsUploading()) {
            return 1;
        }
        if (getCacheSize() < sourceStatus.getCacheSize()) {
            return -1;
        }
        if (getCacheSize() > sourceStatus.getCacheSize()) {
            return 1;
        }
        if (!isIsDownloading() && sourceStatus.isIsDownloading()) {
            return -1;
        }
        return (!isIsDownloading() || sourceStatus.isIsDownloading()) ? 0 : 1;
    }
}
