package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class GetGroupCacheInfoResponse implements CloudDriveResponse {
    private String contentVersion;
    private String version;
    private String viewedStateVersion;

    protected boolean canEqual(Object obj) {
        return obj instanceof GetGroupCacheInfoResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetGroupCacheInfoResponse)) {
            return false;
        }
        GetGroupCacheInfoResponse getGroupCacheInfoResponse = (GetGroupCacheInfoResponse) obj;
        if (!getGroupCacheInfoResponse.canEqual(this)) {
            return false;
        }
        String version = getVersion();
        String version2 = getGroupCacheInfoResponse.getVersion();
        if (version != null ? !version.equals(version2) : version2 != null) {
            return false;
        }
        String contentVersion = getContentVersion();
        String contentVersion2 = getGroupCacheInfoResponse.getContentVersion();
        if (contentVersion != null ? !contentVersion.equals(contentVersion2) : contentVersion2 != null) {
            return false;
        }
        String viewedStateVersion = getViewedStateVersion();
        String viewedStateVersion2 = getGroupCacheInfoResponse.getViewedStateVersion();
        return viewedStateVersion != null ? viewedStateVersion.equals(viewedStateVersion2) : viewedStateVersion2 == null;
    }

    public String getContentVersion() {
        return this.contentVersion;
    }

    public String getVersion() {
        return this.version;
    }

    public String getViewedStateVersion() {
        return this.viewedStateVersion;
    }

    public int hashCode() {
        String version = getVersion();
        int i = 43;
        int hashCode = version == null ? 43 : version.hashCode();
        String contentVersion = getContentVersion();
        int hashCode2 = ((hashCode + 59) * 59) + (contentVersion == null ? 43 : contentVersion.hashCode());
        String viewedStateVersion = getViewedStateVersion();
        int i2 = hashCode2 * 59;
        if (viewedStateVersion != null) {
            i = viewedStateVersion.hashCode();
        }
        return i2 + i;
    }

    public void setContentVersion(String str) {
        this.contentVersion = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setViewedStateVersion(String str) {
        this.viewedStateVersion = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetGroupCacheInfoResponse(version=");
        outline107.append(getVersion());
        outline107.append(", contentVersion=");
        outline107.append(getContentVersion());
        outline107.append(", viewedStateVersion=");
        outline107.append(getViewedStateVersion());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse instanceof GetGroupCacheInfoResponse) {
            return cloudDriveResponse.hashCode() - hashCode();
        }
        return -1;
    }
}
