package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CloudDriveGrant implements Comparable<CloudDriveGrant> {
    private String expiration;
    private String grantId;
    private long grantStorage;
    private boolean isStackable;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CloudDriveGrant) && compareTo((CloudDriveGrant) obj) == 0;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public String getGrantId() {
        return this.grantId;
    }

    public long getGrantStorage() {
        return this.grantStorage;
    }

    public int hashCode() {
        long j = this.grantStorage;
        int i = ((int) (j ^ (j >>> 32))) + 1;
        String str = this.grantId;
        int i2 = 0;
        int hashCode = i + (str == null ? 0 : str.hashCode()) + (this.isStackable ? 1 : 0);
        String str2 = this.expiration;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return hashCode + i2;
    }

    public boolean isStackable() {
        return this.isStackable;
    }

    public CloudDriveGrant setExpiration(String str) {
        this.expiration = str;
        return this;
    }

    public CloudDriveGrant setGrantId(String str) {
        this.grantId = str;
        return this;
    }

    public CloudDriveGrant setGrantStorage(long j) {
        this.grantStorage = j;
        return this;
    }

    public CloudDriveGrant setStackable(boolean z) {
        this.isStackable = z;
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveGrant cloudDriveGrant) {
        if (cloudDriveGrant == null) {
            return -1;
        }
        if (cloudDriveGrant == this) {
            return 0;
        }
        if (getGrantStorage() < cloudDriveGrant.getGrantStorage()) {
            return -1;
        }
        if (getGrantStorage() > cloudDriveGrant.getGrantStorage()) {
            return 1;
        }
        if (isStackable() && !cloudDriveGrant.isStackable()) {
            return 1;
        }
        if (!isStackable() && cloudDriveGrant.isStackable()) {
            return -1;
        }
        String grantId = getGrantId();
        String grantId2 = cloudDriveGrant.getGrantId();
        if (grantId != grantId2) {
            if (grantId == null) {
                return -1;
            }
            if (grantId2 == null) {
                return 1;
            }
            int compareTo = grantId.compareTo(grantId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String expiration = getExpiration();
        String expiration2 = cloudDriveGrant.getExpiration();
        if (expiration != expiration2) {
            if (expiration == null) {
                return -1;
            }
            if (expiration2 == null) {
                return 1;
            }
            int compareTo2 = expiration.compareTo(expiration2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
