package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public abstract class AccessTokenRequest implements CloudDriveRequest {
    private String token;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AccessTokenRequest) && compareTo((CloudDriveRequest) ((AccessTokenRequest) obj)) == 0;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        return (((getToken() == null ? 0 : getToken().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setToken(String str) {
        this.token = str;
    }

    public AccessTokenRequest withToken(String str) {
        setToken(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof AccessTokenRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getToken(), ((AccessTokenRequest) cloudDriveRequest).getToken());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
