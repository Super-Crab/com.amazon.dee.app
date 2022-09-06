package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class GetMetadataDatabaseStatusResponse {
    private String mLocation;
    private double mPercentComplete;
    private Status mStatus;

    /* loaded from: classes11.dex */
    public enum Status {
        PENDING,
        IN_PROGRESS,
        ERRORED,
        FAULTED,
        COMPLETED,
        UNKNOWN;

        public static Status fromString(String str) {
            try {
                return valueOf(str.toUpperCase());
            } catch (IllegalArgumentException unused) {
                return UNKNOWN;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetMetadataDatabaseStatusResponse.class != obj.getClass()) {
            return false;
        }
        GetMetadataDatabaseStatusResponse getMetadataDatabaseStatusResponse = (GetMetadataDatabaseStatusResponse) obj;
        if (Double.compare(getMetadataDatabaseStatusResponse.mPercentComplete, this.mPercentComplete) != 0) {
            return false;
        }
        String str = this.mLocation;
        if (str == null ? getMetadataDatabaseStatusResponse.mLocation != null : !str.equals(getMetadataDatabaseStatusResponse.mLocation)) {
            return false;
        }
        return this.mStatus == getMetadataDatabaseStatusResponse.mStatus;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public double getPercentComplete() {
        return this.mPercentComplete;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public int hashCode() {
        String str = this.mLocation;
        int hashCode = str != null ? str.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.mPercentComplete);
        return this.mStatus.hashCode() + (((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31);
    }

    public void setLocation(String str) {
        this.mLocation = str;
    }

    public void setPercentComplete(double d) {
        this.mPercentComplete = d;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
    }
}
