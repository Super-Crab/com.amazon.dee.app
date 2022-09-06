package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class SearchMetadata {
    private String clusterId;
    private String clusterName;
    private String locationId;
    private String nodeId;
    private String nodeOwnerId;
    private String thingId;

    protected boolean canEqual(Object obj) {
        return obj instanceof SearchMetadata;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchMetadata)) {
            return false;
        }
        SearchMetadata searchMetadata = (SearchMetadata) obj;
        if (!searchMetadata.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = searchMetadata.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String nodeOwnerId = getNodeOwnerId();
        String nodeOwnerId2 = searchMetadata.getNodeOwnerId();
        if (nodeOwnerId != null ? !nodeOwnerId.equals(nodeOwnerId2) : nodeOwnerId2 != null) {
            return false;
        }
        String locationId = getLocationId();
        String locationId2 = searchMetadata.getLocationId();
        if (locationId != null ? !locationId.equals(locationId2) : locationId2 != null) {
            return false;
        }
        String clusterName = getClusterName();
        String clusterName2 = searchMetadata.getClusterName();
        if (clusterName != null ? !clusterName.equals(clusterName2) : clusterName2 != null) {
            return false;
        }
        String clusterId = getClusterId();
        String clusterId2 = searchMetadata.getClusterId();
        if (clusterId != null ? !clusterId.equals(clusterId2) : clusterId2 != null) {
            return false;
        }
        String thingId = getThingId();
        String thingId2 = searchMetadata.getThingId();
        return thingId != null ? thingId.equals(thingId2) : thingId2 == null;
    }

    public String getClusterId() {
        return this.clusterId;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getNodeOwnerId() {
        return this.nodeOwnerId;
    }

    public String getThingId() {
        return this.thingId;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String nodeOwnerId = getNodeOwnerId();
        int hashCode2 = ((hashCode + 59) * 59) + (nodeOwnerId == null ? 43 : nodeOwnerId.hashCode());
        String locationId = getLocationId();
        int hashCode3 = (hashCode2 * 59) + (locationId == null ? 43 : locationId.hashCode());
        String clusterName = getClusterName();
        int hashCode4 = (hashCode3 * 59) + (clusterName == null ? 43 : clusterName.hashCode());
        String clusterId = getClusterId();
        int hashCode5 = (hashCode4 * 59) + (clusterId == null ? 43 : clusterId.hashCode());
        String thingId = getThingId();
        int i2 = hashCode5 * 59;
        if (thingId != null) {
            i = thingId.hashCode();
        }
        return i2 + i;
    }

    public void setClusterId(String str) {
        this.clusterId = str;
    }

    public void setClusterName(String str) {
        this.clusterName = str;
    }

    public void setLocationId(String str) {
        this.locationId = str;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public void setNodeOwnerId(String str) {
        this.nodeOwnerId = str;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchMetadata(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", nodeOwnerId=");
        outline107.append(getNodeOwnerId());
        outline107.append(", locationId=");
        outline107.append(getLocationId());
        outline107.append(", clusterName=");
        outline107.append(getClusterName());
        outline107.append(", clusterId=");
        outline107.append(getClusterId());
        outline107.append(", thingId=");
        outline107.append(getThingId());
        outline107.append(")");
        return outline107.toString();
    }

    public SearchMetadata withClusterId(String str) {
        setClusterId(str);
        return this;
    }

    public SearchMetadata withClusterName(String str) {
        setClusterName(str);
        return this;
    }

    public SearchMetadata withLocationId(String str) {
        setLocationId(str);
        return this;
    }

    public SearchMetadata withNodeId(String str) {
        setNodeId(str);
        return this;
    }

    public SearchMetadata withNodeOwnerId(String str) {
        setNodeOwnerId(str);
        return this;
    }

    public SearchMetadata withThingId(String str) {
        setThingId(str);
        return this;
    }
}
