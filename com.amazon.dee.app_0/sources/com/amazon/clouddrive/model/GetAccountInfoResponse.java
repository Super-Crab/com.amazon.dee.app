package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetAccountInfoResponse implements CloudDriveResponse {
    private String status;
    private String termsOfUse;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountInfoResponse) && compareTo((CloudDriveResponse) ((GetAccountInfoResponse) obj)) == 0;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTermsOfUse() {
        return this.termsOfUse;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getTermsOfUse() == null ? 0 : getTermsOfUse().hashCode()) + 1;
        if (getStatus() != null) {
            i = getStatus().hashCode();
        }
        return hashCode + i;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTermsOfUse(String str) {
        this.termsOfUse = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetAccountInfoResponse)) {
            return 1;
        }
        GetAccountInfoResponse getAccountInfoResponse = (GetAccountInfoResponse) cloudDriveResponse;
        String termsOfUse = getTermsOfUse();
        String termsOfUse2 = getAccountInfoResponse.getTermsOfUse();
        if (termsOfUse != termsOfUse2) {
            if (termsOfUse == null) {
                return -1;
            }
            if (termsOfUse2 == null) {
                return 1;
            }
            int compareTo = termsOfUse.compareTo(termsOfUse2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String status = getStatus();
        String status2 = getAccountInfoResponse.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int compareTo2 = status.compareTo(status2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
