package com.amazon.clouddrive.cdasdk.cds.source;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class MediaTypeUploadStatus {
    @JsonProperty("backlogSize")
    private Long backlogSize;
    @JsonProperty("isAutoSaveActive")
    private Boolean isAutoSaveActive;
    @JsonProperty("uploadQueueSize")
    private Long uploadQueueSize;

    protected boolean canEqual(Object obj) {
        return obj instanceof MediaTypeUploadStatus;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaTypeUploadStatus)) {
            return false;
        }
        MediaTypeUploadStatus mediaTypeUploadStatus = (MediaTypeUploadStatus) obj;
        if (!mediaTypeUploadStatus.canEqual(this)) {
            return false;
        }
        Boolean isAutoSaveActive = getIsAutoSaveActive();
        Boolean isAutoSaveActive2 = mediaTypeUploadStatus.getIsAutoSaveActive();
        if (isAutoSaveActive != null ? !isAutoSaveActive.equals(isAutoSaveActive2) : isAutoSaveActive2 != null) {
            return false;
        }
        Long uploadQueueSize = getUploadQueueSize();
        Long uploadQueueSize2 = mediaTypeUploadStatus.getUploadQueueSize();
        if (uploadQueueSize != null ? !uploadQueueSize.equals(uploadQueueSize2) : uploadQueueSize2 != null) {
            return false;
        }
        Long backlogSize = getBacklogSize();
        Long backlogSize2 = mediaTypeUploadStatus.getBacklogSize();
        return backlogSize != null ? backlogSize.equals(backlogSize2) : backlogSize2 == null;
    }

    public Long getBacklogSize() {
        return this.backlogSize;
    }

    public Boolean getIsAutoSaveActive() {
        return this.isAutoSaveActive;
    }

    public Long getUploadQueueSize() {
        return this.uploadQueueSize;
    }

    public int hashCode() {
        Boolean isAutoSaveActive = getIsAutoSaveActive();
        int i = 43;
        int hashCode = isAutoSaveActive == null ? 43 : isAutoSaveActive.hashCode();
        Long uploadQueueSize = getUploadQueueSize();
        int hashCode2 = ((hashCode + 59) * 59) + (uploadQueueSize == null ? 43 : uploadQueueSize.hashCode());
        Long backlogSize = getBacklogSize();
        int i2 = hashCode2 * 59;
        if (backlogSize != null) {
            i = backlogSize.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("backlogSize")
    public void setBacklogSize(Long l) {
        this.backlogSize = l;
    }

    @JsonProperty("isAutoSaveActive")
    public void setIsAutoSaveActive(Boolean bool) {
        this.isAutoSaveActive = bool;
    }

    @JsonProperty("uploadQueueSize")
    public void setUploadQueueSize(Long l) {
        this.uploadQueueSize = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaTypeUploadStatus(isAutoSaveActive=");
        outline107.append(getIsAutoSaveActive());
        outline107.append(", uploadQueueSize=");
        outline107.append(getUploadQueueSize());
        outline107.append(", backlogSize=");
        outline107.append(getBacklogSize());
        outline107.append(")");
        return outline107.toString();
    }
}
