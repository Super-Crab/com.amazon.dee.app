package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CloudDriveBenefit implements Comparable<CloudDriveBenefit> {
    private String benefit;
    private String expiration;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CloudDriveBenefit) && compareTo((CloudDriveBenefit) obj) == 0;
    }

    public String getBenefit() {
        return this.benefit;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public int hashCode() {
        String str = this.expiration;
        int i = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) + 1;
        String str2 = this.benefit;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public CloudDriveBenefit setBenefit(String str) {
        this.benefit = str;
        return this;
    }

    public CloudDriveBenefit setExpiration(String str) {
        this.expiration = str;
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveBenefit cloudDriveBenefit) {
        if (cloudDriveBenefit == null) {
            return -1;
        }
        if (cloudDriveBenefit == this) {
            return 0;
        }
        String expiration = getExpiration();
        String expiration2 = cloudDriveBenefit.getExpiration();
        if (expiration != expiration2) {
            if (expiration == null) {
                return -1;
            }
            if (expiration2 == null) {
                return 1;
            }
            int compareTo = expiration.compareTo(expiration2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String benefit = getBenefit();
        String benefit2 = cloudDriveBenefit.getBenefit();
        if (benefit != benefit2) {
            if (benefit == null) {
                return -1;
            }
            if (benefit2 == null) {
                return 1;
            }
            int compareTo2 = benefit.compareTo(benefit2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
