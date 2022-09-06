package com.amazon.clouddrive.cdasdk.cds.source;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class SourceStatus {
    @JsonProperty("cacheSize")
    private Long cacheSize;
    @JsonProperty("isDownloading")
    private Boolean isDownloading;
    @JsonProperty("isUploading")
    private Boolean isUploading;
    @JsonProperty("uploadStatus")
    private Map<String, MediaTypeUploadStatus> uploadStatus;

    protected boolean canEqual(Object obj) {
        return obj instanceof SourceStatus;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceStatus)) {
            return false;
        }
        SourceStatus sourceStatus = (SourceStatus) obj;
        if (!sourceStatus.canEqual(this)) {
            return false;
        }
        Boolean isUploading = getIsUploading();
        Boolean isUploading2 = sourceStatus.getIsUploading();
        if (isUploading != null ? !isUploading.equals(isUploading2) : isUploading2 != null) {
            return false;
        }
        Boolean isDownloading = getIsDownloading();
        Boolean isDownloading2 = sourceStatus.getIsDownloading();
        if (isDownloading != null ? !isDownloading.equals(isDownloading2) : isDownloading2 != null) {
            return false;
        }
        Long cacheSize = getCacheSize();
        Long cacheSize2 = sourceStatus.getCacheSize();
        if (cacheSize != null ? !cacheSize.equals(cacheSize2) : cacheSize2 != null) {
            return false;
        }
        Map<String, MediaTypeUploadStatus> uploadStatus = getUploadStatus();
        Map<String, MediaTypeUploadStatus> uploadStatus2 = sourceStatus.getUploadStatus();
        return uploadStatus != null ? uploadStatus.equals(uploadStatus2) : uploadStatus2 == null;
    }

    public Long getCacheSize() {
        return this.cacheSize;
    }

    public Boolean getIsDownloading() {
        return this.isDownloading;
    }

    public Boolean getIsUploading() {
        return this.isUploading;
    }

    public Map<String, MediaTypeUploadStatus> getUploadStatus() {
        return this.uploadStatus;
    }

    public int hashCode() {
        Boolean isUploading = getIsUploading();
        int i = 43;
        int hashCode = isUploading == null ? 43 : isUploading.hashCode();
        Boolean isDownloading = getIsDownloading();
        int hashCode2 = ((hashCode + 59) * 59) + (isDownloading == null ? 43 : isDownloading.hashCode());
        Long cacheSize = getCacheSize();
        int hashCode3 = (hashCode2 * 59) + (cacheSize == null ? 43 : cacheSize.hashCode());
        Map<String, MediaTypeUploadStatus> uploadStatus = getUploadStatus();
        int i2 = hashCode3 * 59;
        if (uploadStatus != null) {
            i = uploadStatus.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("cacheSize")
    public void setCacheSize(Long l) {
        this.cacheSize = l;
    }

    @JsonProperty("isDownloading")
    public void setIsDownloading(Boolean bool) {
        this.isDownloading = bool;
    }

    @JsonProperty("isUploading")
    public void setIsUploading(Boolean bool) {
        this.isUploading = bool;
    }

    @JsonProperty("uploadStatus")
    public void setUploadStatus(Map<String, MediaTypeUploadStatus> map) {
        this.uploadStatus = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SourceStatus(isUploading=");
        outline107.append(getIsUploading());
        outline107.append(", isDownloading=");
        outline107.append(getIsDownloading());
        outline107.append(", cacheSize=");
        outline107.append(getCacheSize());
        outline107.append(", uploadStatus=");
        outline107.append(getUploadStatus());
        outline107.append(")");
        return outline107.toString();
    }
}
