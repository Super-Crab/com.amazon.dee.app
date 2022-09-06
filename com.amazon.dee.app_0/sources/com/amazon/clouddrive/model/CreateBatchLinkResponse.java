package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CreateBatchLinkResponse implements CloudDriveResponse {
    private String expiryTime;
    private BatchLink link;
    private String status;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreateBatchLinkResponse) && compareTo((CloudDriveResponse) ((CreateBatchLinkResponse) obj)) == 0;
    }

    public String getExpiryTime() {
        return this.expiryTime;
    }

    public BatchLink getLink() {
        return this.link;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        int i = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.expiryTime;
        int hashCode2 = hashCode + (str2 != null ? str2.hashCode() : 0);
        BatchLink batchLink = this.link;
        if (batchLink != null) {
            i = batchLink.hashCode();
        }
        return ((hashCode2 + i) * 31) + super.hashCode();
    }

    public void setExpiryTime(String str) {
        this.expiryTime = str;
    }

    public void setLink(BatchLink batchLink) {
        this.link = batchLink;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof CreateBatchLinkResponse)) {
            return 1;
        }
        CreateBatchLinkResponse createBatchLinkResponse = (CreateBatchLinkResponse) cloudDriveResponse;
        String status = getStatus();
        String status2 = createBatchLinkResponse.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int compareTo = status.compareTo(status2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String expiryTime = getExpiryTime();
        String expiryTime2 = createBatchLinkResponse.getExpiryTime();
        if (expiryTime != expiryTime2) {
            if (expiryTime == null) {
                return -1;
            }
            if (expiryTime2 == null) {
                return 1;
            }
            int compareTo2 = expiryTime.compareTo(expiryTime2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        BatchLink link = getLink();
        BatchLink link2 = createBatchLinkResponse.getLink();
        if (link != link2) {
            if (link == null) {
                return -1;
            }
            if (link2 == null) {
                return 1;
            }
            int compareTo3 = link.compareTo(link2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
