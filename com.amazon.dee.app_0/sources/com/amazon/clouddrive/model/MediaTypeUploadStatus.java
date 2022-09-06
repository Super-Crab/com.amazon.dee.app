package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class MediaTypeUploadStatus implements Comparable<MediaTypeUploadStatus> {
    private long backlogSize;
    private boolean isAutoSaveActive;
    private long uploadQueueSize;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof MediaTypeUploadStatus) && compareTo((MediaTypeUploadStatus) obj) == 0;
    }

    public long getBacklogSize() {
        return this.backlogSize;
    }

    public long getUploadQueueSize() {
        return this.uploadQueueSize;
    }

    public int hashCode() {
        return ((int) getUploadQueueSize()) + 1 + (isIsAutoSaveActive() ? 1 : 0) + ((int) getBacklogSize());
    }

    public boolean isIsAutoSaveActive() {
        return this.isAutoSaveActive;
    }

    public void setBacklogSize(long j) {
        this.backlogSize = j;
    }

    public void setIsAutoSaveActive(boolean z) {
        this.isAutoSaveActive = z;
    }

    public void setUploadQueueSize(long j) {
        this.uploadQueueSize = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(MediaTypeUploadStatus mediaTypeUploadStatus) {
        if (mediaTypeUploadStatus == null) {
            return -1;
        }
        if (mediaTypeUploadStatus == this) {
            return 0;
        }
        if (getUploadQueueSize() < mediaTypeUploadStatus.getUploadQueueSize()) {
            return -1;
        }
        if (getUploadQueueSize() > mediaTypeUploadStatus.getUploadQueueSize()) {
            return 1;
        }
        if (!isIsAutoSaveActive() && mediaTypeUploadStatus.isIsAutoSaveActive()) {
            return -1;
        }
        if (isIsAutoSaveActive() && !mediaTypeUploadStatus.isIsAutoSaveActive()) {
            return 1;
        }
        if (getBacklogSize() < mediaTypeUploadStatus.getBacklogSize()) {
            return -1;
        }
        return getBacklogSize() > mediaTypeUploadStatus.getBacklogSize() ? 1 : 0;
    }
}
