package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class VersionedNodeRequest extends CloudDriveRequest {
    @JsonProperty("resourceVersion")
    private ResourceVersion resourceVersion;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof VersionedNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VersionedNodeRequest)) {
            return false;
        }
        VersionedNodeRequest versionedNodeRequest = (VersionedNodeRequest) obj;
        if (!versionedNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        ResourceVersion resourceVersion = getResourceVersion();
        ResourceVersion resourceVersion2 = versionedNodeRequest.getResourceVersion();
        return resourceVersion != null ? resourceVersion.equals(resourceVersion2) : resourceVersion2 == null;
    }

    public ResourceVersion getResourceVersion() {
        return this.resourceVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        ResourceVersion resourceVersion = getResourceVersion();
        return (hashCode * 59) + (resourceVersion == null ? 43 : resourceVersion.hashCode());
    }

    @JsonProperty("resourceVersion")
    public void setResourceVersion(ResourceVersion resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VersionedNodeRequest(resourceVersion=");
        outline107.append(getResourceVersion());
        outline107.append(")");
        return outline107.toString();
    }
}
