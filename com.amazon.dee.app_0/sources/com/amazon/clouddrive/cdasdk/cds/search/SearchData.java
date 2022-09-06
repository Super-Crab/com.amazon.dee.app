package com.amazon.clouddrive.cdasdk.cds.search;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SearchData {
    @JsonProperty(PhotoSearchCategory.CLUSTER_ID)
    private String clusterId;
    @JsonProperty("clusterName")
    private String clusterName;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("locationInfo")
    private LocationInfo locationInfo;
    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    private String nodeId;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("thingId")
    private String thingId;

    protected boolean canEqual(Object obj) {
        return obj instanceof SearchData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchData)) {
            return false;
        }
        SearchData searchData = (SearchData) obj;
        if (!searchData.canEqual(this)) {
            return false;
        }
        String nodeId = getNodeId();
        String nodeId2 = searchData.getNodeId();
        if (nodeId != null ? !nodeId.equals(nodeId2) : nodeId2 != null) {
            return false;
        }
        String locationId = getLocationId();
        String locationId2 = searchData.getLocationId();
        if (locationId != null ? !locationId.equals(locationId2) : locationId2 != null) {
            return false;
        }
        LocationInfo locationInfo = getLocationInfo();
        LocationInfo locationInfo2 = searchData.getLocationInfo();
        if (locationInfo != null ? !locationInfo.equals(locationInfo2) : locationInfo2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = searchData.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        String clusterName = getClusterName();
        String clusterName2 = searchData.getClusterName();
        if (clusterName != null ? !clusterName.equals(clusterName2) : clusterName2 != null) {
            return false;
        }
        String clusterId = getClusterId();
        String clusterId2 = searchData.getClusterId();
        if (clusterId != null ? !clusterId.equals(clusterId2) : clusterId2 != null) {
            return false;
        }
        String thingId = getThingId();
        String thingId2 = searchData.getThingId();
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

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getThingId() {
        return this.thingId;
    }

    public int hashCode() {
        String nodeId = getNodeId();
        int i = 43;
        int hashCode = nodeId == null ? 43 : nodeId.hashCode();
        String locationId = getLocationId();
        int hashCode2 = ((hashCode + 59) * 59) + (locationId == null ? 43 : locationId.hashCode());
        LocationInfo locationInfo = getLocationInfo();
        int hashCode3 = (hashCode2 * 59) + (locationInfo == null ? 43 : locationInfo.hashCode());
        String ownerId = getOwnerId();
        int hashCode4 = (hashCode3 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        String clusterName = getClusterName();
        int hashCode5 = (hashCode4 * 59) + (clusterName == null ? 43 : clusterName.hashCode());
        String clusterId = getClusterId();
        int hashCode6 = (hashCode5 * 59) + (clusterId == null ? 43 : clusterId.hashCode());
        String thingId = getThingId();
        int i2 = hashCode6 * 59;
        if (thingId != null) {
            i = thingId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty(PhotoSearchCategory.CLUSTER_ID)
    public void setClusterId(String str) {
        this.clusterId = str;
    }

    @JsonProperty("clusterName")
    public void setClusterName(String str) {
        this.clusterName = str;
    }

    @JsonProperty("locationId")
    public void setLocationId(String str) {
        this.locationId = str;
    }

    @JsonProperty("locationInfo")
    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    @JsonProperty(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID)
    public void setNodeId(String str) {
        this.nodeId = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("thingId")
    public void setThingId(String str) {
        this.thingId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchData(nodeId=");
        outline107.append(getNodeId());
        outline107.append(", locationId=");
        outline107.append(getLocationId());
        outline107.append(", locationInfo=");
        outline107.append(getLocationInfo());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", clusterName=");
        outline107.append(getClusterName());
        outline107.append(", clusterId=");
        outline107.append(getClusterId());
        outline107.append(", thingId=");
        outline107.append(getThingId());
        outline107.append(")");
        return outline107.toString();
    }
}
