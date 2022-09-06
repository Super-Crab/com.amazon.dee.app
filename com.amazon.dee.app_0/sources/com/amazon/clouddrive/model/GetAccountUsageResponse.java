package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountUsageResponse implements CloudDriveResponse {
    private UsageSummary doc;
    private String lastCalculated;
    private UsageSummary other;
    private UsageSummary photo;
    private UsageSummary video;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountUsageResponse) && compareTo((CloudDriveResponse) ((GetAccountUsageResponse) obj)) == 0;
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

    public int hashCode() {
        int i = 0;
        int hashCode = (getVideo() == null ? 0 : getVideo().hashCode()) + 1 + (getOther() == null ? 0 : getOther().hashCode()) + (getLastCalculated() == null ? 0 : getLastCalculated().hashCode()) + (getDoc() == null ? 0 : getDoc().hashCode());
        if (getPhoto() != null) {
            i = getPhoto().hashCode();
        }
        return hashCode + i;
    }

    public void setDoc(UsageSummary usageSummary) {
        this.doc = usageSummary;
    }

    public void setLastCalculated(String str) {
        this.lastCalculated = str;
    }

    public void setOther(UsageSummary usageSummary) {
        this.other = usageSummary;
    }

    public void setPhoto(UsageSummary usageSummary) {
        this.photo = usageSummary;
    }

    public void setVideo(UsageSummary usageSummary) {
        this.video = usageSummary;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetAccountUsageResponse)) {
            return 1;
        }
        GetAccountUsageResponse getAccountUsageResponse = (GetAccountUsageResponse) cloudDriveResponse;
        UsageSummary video = getVideo();
        UsageSummary video2 = getAccountUsageResponse.getVideo();
        if (video != video2) {
            if (video == null) {
                return -1;
            }
            if (video2 == null) {
                return 1;
            }
            int compareTo = video.compareTo(video2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        UsageSummary other = getOther();
        UsageSummary other2 = getAccountUsageResponse.getOther();
        if (other != other2) {
            if (other == null) {
                return -1;
            }
            if (other2 == null) {
                return 1;
            }
            int compareTo2 = other.compareTo(other2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String lastCalculated = getLastCalculated();
        String lastCalculated2 = getAccountUsageResponse.getLastCalculated();
        if (lastCalculated != lastCalculated2) {
            if (lastCalculated == null) {
                return -1;
            }
            if (lastCalculated2 == null) {
                return 1;
            }
            int compareTo3 = lastCalculated.compareTo(lastCalculated2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        UsageSummary doc = getDoc();
        UsageSummary doc2 = getAccountUsageResponse.getDoc();
        if (doc != doc2) {
            if (doc == null) {
                return -1;
            }
            if (doc2 == null) {
                return 1;
            }
            int compareTo4 = doc.compareTo(doc2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        UsageSummary photo = getPhoto();
        UsageSummary photo2 = getAccountUsageResponse.getPhoto();
        if (photo != photo2) {
            if (photo == null) {
                return -1;
            }
            if (photo2 == null) {
                return 1;
            }
            int compareTo5 = photo.compareTo(photo2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        return 0;
    }
}
