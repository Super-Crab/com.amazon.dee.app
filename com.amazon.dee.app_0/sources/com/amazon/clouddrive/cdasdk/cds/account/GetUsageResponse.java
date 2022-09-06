package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetUsageResponse extends CloudDriveResponse {
    @JsonProperty("doc")
    private UsageSummary doc;
    @JsonProperty("lastCalculated")
    private String lastCalculated;
    @JsonProperty("other")
    private UsageSummary other;
    @JsonProperty("photo")
    private UsageSummary photo;
    @JsonProperty("video")
    private UsageSummary video;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetUsageResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetUsageResponse)) {
            return false;
        }
        GetUsageResponse getUsageResponse = (GetUsageResponse) obj;
        if (!getUsageResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String lastCalculated = getLastCalculated();
        String lastCalculated2 = getUsageResponse.getLastCalculated();
        if (lastCalculated != null ? !lastCalculated.equals(lastCalculated2) : lastCalculated2 != null) {
            return false;
        }
        UsageSummary video = getVideo();
        UsageSummary video2 = getUsageResponse.getVideo();
        if (video != null ? !video.equals(video2) : video2 != null) {
            return false;
        }
        UsageSummary photo = getPhoto();
        UsageSummary photo2 = getUsageResponse.getPhoto();
        if (photo != null ? !photo.equals(photo2) : photo2 != null) {
            return false;
        }
        UsageSummary doc = getDoc();
        UsageSummary doc2 = getUsageResponse.getDoc();
        if (doc != null ? !doc.equals(doc2) : doc2 != null) {
            return false;
        }
        UsageSummary other = getOther();
        UsageSummary other2 = getUsageResponse.getOther();
        return other != null ? other.equals(other2) : other2 == null;
    }

    public UsageSummary getDoc() {
        return this.doc;
    }

    public String getLastCalculated() {
        return this.lastCalculated;
    }

    public UsageSummary getOther() {
        return this.other;
    }

    public UsageSummary getPhoto() {
        return this.photo;
    }

    public UsageSummary getVideo() {
        return this.video;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        String lastCalculated = getLastCalculated();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (lastCalculated == null ? 43 : lastCalculated.hashCode());
        UsageSummary video = getVideo();
        int hashCode3 = (hashCode2 * 59) + (video == null ? 43 : video.hashCode());
        UsageSummary photo = getPhoto();
        int hashCode4 = (hashCode3 * 59) + (photo == null ? 43 : photo.hashCode());
        UsageSummary doc = getDoc();
        int hashCode5 = (hashCode4 * 59) + (doc == null ? 43 : doc.hashCode());
        UsageSummary other = getOther();
        int i2 = hashCode5 * 59;
        if (other != null) {
            i = other.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("doc")
    public void setDoc(UsageSummary usageSummary) {
        this.doc = usageSummary;
    }

    @JsonProperty("lastCalculated")
    public void setLastCalculated(String str) {
        this.lastCalculated = str;
    }

    @JsonProperty("other")
    public void setOther(UsageSummary usageSummary) {
        this.other = usageSummary;
    }

    @JsonProperty("photo")
    public void setPhoto(UsageSummary usageSummary) {
        this.photo = usageSummary;
    }

    @JsonProperty("video")
    public void setVideo(UsageSummary usageSummary) {
        this.video = usageSummary;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetUsageResponse(lastCalculated=");
        outline107.append(getLastCalculated());
        outline107.append(", video=");
        outline107.append(getVideo());
        outline107.append(", photo=");
        outline107.append(getPhoto());
        outline107.append(", doc=");
        outline107.append(getDoc());
        outline107.append(", other=");
        outline107.append(getOther());
        outline107.append(")");
        return outline107.toString();
    }
}
