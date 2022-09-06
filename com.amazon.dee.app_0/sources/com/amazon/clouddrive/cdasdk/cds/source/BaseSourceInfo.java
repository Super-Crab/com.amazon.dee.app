package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.clouddrive.cdasdk.cds.common.ISO8601;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class BaseSourceInfo {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("creationTime")
    private ISO8601 creationTime;
    @JsonProperty("lastModifiedTime")
    private ISO8601 lastModifiedTime;
    @JsonProperty("lastSeenTime")
    private ISO8601 lastSeenTime;
    @JsonProperty("pushDeliveryId")
    private String pushDeliveryId;
    @JsonProperty("pushDeliveryService")
    private String pushDeliveryService;
    @JsonProperty("pushEndpoint")
    private Boolean pushEndpoint;
    @JsonProperty("pushEndpointARN")
    private String pushEndpointARN;
    @JsonProperty("sourceApplicationName")
    private String sourceApplicationName;
    @JsonProperty("sourceId")
    private String sourceId;
    @JsonProperty("sourceStatus")
    private SourceStatus sourceStatus;
    @JsonProperty("sourceVersionHistory")
    private Map<String, ISO8601> sourceVersionHistory;
    @JsonProperty("timeZone")
    private String timeZone;

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseSourceInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseSourceInfo)) {
            return false;
        }
        BaseSourceInfo baseSourceInfo = (BaseSourceInfo) obj;
        if (!baseSourceInfo.canEqual(this)) {
            return false;
        }
        String sourceId = getSourceId();
        String sourceId2 = baseSourceInfo.getSourceId();
        if (sourceId != null ? !sourceId.equals(sourceId2) : sourceId2 != null) {
            return false;
        }
        String sourceApplicationName = getSourceApplicationName();
        String sourceApplicationName2 = baseSourceInfo.getSourceApplicationName();
        if (sourceApplicationName != null ? !sourceApplicationName.equals(sourceApplicationName2) : sourceApplicationName2 != null) {
            return false;
        }
        ISO8601 creationTime = getCreationTime();
        ISO8601 creationTime2 = baseSourceInfo.getCreationTime();
        if (creationTime != null ? !creationTime.equals(creationTime2) : creationTime2 != null) {
            return false;
        }
        ISO8601 lastModifiedTime = getLastModifiedTime();
        ISO8601 lastModifiedTime2 = baseSourceInfo.getLastModifiedTime();
        if (lastModifiedTime != null ? !lastModifiedTime.equals(lastModifiedTime2) : lastModifiedTime2 != null) {
            return false;
        }
        ISO8601 lastSeenTime = getLastSeenTime();
        ISO8601 lastSeenTime2 = baseSourceInfo.getLastSeenTime();
        if (lastSeenTime != null ? !lastSeenTime.equals(lastSeenTime2) : lastSeenTime2 != null) {
            return false;
        }
        SourceStatus sourceStatus = getSourceStatus();
        SourceStatus sourceStatus2 = baseSourceInfo.getSourceStatus();
        if (sourceStatus != null ? !sourceStatus.equals(sourceStatus2) : sourceStatus2 != null) {
            return false;
        }
        Map<String, ISO8601> sourceVersionHistory = getSourceVersionHistory();
        Map<String, ISO8601> sourceVersionHistory2 = baseSourceInfo.getSourceVersionHistory();
        if (sourceVersionHistory != null ? !sourceVersionHistory.equals(sourceVersionHistory2) : sourceVersionHistory2 != null) {
            return false;
        }
        String pushEndpointARN = getPushEndpointARN();
        String pushEndpointARN2 = baseSourceInfo.getPushEndpointARN();
        if (pushEndpointARN != null ? !pushEndpointARN.equals(pushEndpointARN2) : pushEndpointARN2 != null) {
            return false;
        }
        String pushDeliveryService = getPushDeliveryService();
        String pushDeliveryService2 = baseSourceInfo.getPushDeliveryService();
        if (pushDeliveryService != null ? !pushDeliveryService.equals(pushDeliveryService2) : pushDeliveryService2 != null) {
            return false;
        }
        String pushDeliveryId = getPushDeliveryId();
        String pushDeliveryId2 = baseSourceInfo.getPushDeliveryId();
        if (pushDeliveryId != null ? !pushDeliveryId.equals(pushDeliveryId2) : pushDeliveryId2 != null) {
            return false;
        }
        Boolean pushEndpoint = getPushEndpoint();
        Boolean pushEndpoint2 = baseSourceInfo.getPushEndpoint();
        if (pushEndpoint != null ? !pushEndpoint.equals(pushEndpoint2) : pushEndpoint2 != null) {
            return false;
        }
        String applicationId = getApplicationId();
        String applicationId2 = baseSourceInfo.getApplicationId();
        if (applicationId != null ? !applicationId.equals(applicationId2) : applicationId2 != null) {
            return false;
        }
        String timeZone = getTimeZone();
        String timeZone2 = baseSourceInfo.getTimeZone();
        return timeZone != null ? timeZone.equals(timeZone2) : timeZone2 == null;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public ISO8601 getCreationTime() {
        return this.creationTime;
    }

    public ISO8601 getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public ISO8601 getLastSeenTime() {
        return this.lastSeenTime;
    }

    public String getPushDeliveryId() {
        return this.pushDeliveryId;
    }

    public String getPushDeliveryService() {
        return this.pushDeliveryService;
    }

    public Boolean getPushEndpoint() {
        return this.pushEndpoint;
    }

    public String getPushEndpointARN() {
        return this.pushEndpointARN;
    }

    public String getSourceApplicationName() {
        return this.sourceApplicationName;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public SourceStatus getSourceStatus() {
        return this.sourceStatus;
    }

    public Map<String, ISO8601> getSourceVersionHistory() {
        return this.sourceVersionHistory;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        String sourceId = getSourceId();
        int i = 43;
        int hashCode = sourceId == null ? 43 : sourceId.hashCode();
        String sourceApplicationName = getSourceApplicationName();
        int hashCode2 = ((hashCode + 59) * 59) + (sourceApplicationName == null ? 43 : sourceApplicationName.hashCode());
        ISO8601 creationTime = getCreationTime();
        int hashCode3 = (hashCode2 * 59) + (creationTime == null ? 43 : creationTime.hashCode());
        ISO8601 lastModifiedTime = getLastModifiedTime();
        int hashCode4 = (hashCode3 * 59) + (lastModifiedTime == null ? 43 : lastModifiedTime.hashCode());
        ISO8601 lastSeenTime = getLastSeenTime();
        int hashCode5 = (hashCode4 * 59) + (lastSeenTime == null ? 43 : lastSeenTime.hashCode());
        SourceStatus sourceStatus = getSourceStatus();
        int hashCode6 = (hashCode5 * 59) + (sourceStatus == null ? 43 : sourceStatus.hashCode());
        Map<String, ISO8601> sourceVersionHistory = getSourceVersionHistory();
        int hashCode7 = (hashCode6 * 59) + (sourceVersionHistory == null ? 43 : sourceVersionHistory.hashCode());
        String pushEndpointARN = getPushEndpointARN();
        int hashCode8 = (hashCode7 * 59) + (pushEndpointARN == null ? 43 : pushEndpointARN.hashCode());
        String pushDeliveryService = getPushDeliveryService();
        int hashCode9 = (hashCode8 * 59) + (pushDeliveryService == null ? 43 : pushDeliveryService.hashCode());
        String pushDeliveryId = getPushDeliveryId();
        int hashCode10 = (hashCode9 * 59) + (pushDeliveryId == null ? 43 : pushDeliveryId.hashCode());
        Boolean pushEndpoint = getPushEndpoint();
        int hashCode11 = (hashCode10 * 59) + (pushEndpoint == null ? 43 : pushEndpoint.hashCode());
        String applicationId = getApplicationId();
        int hashCode12 = (hashCode11 * 59) + (applicationId == null ? 43 : applicationId.hashCode());
        String timeZone = getTimeZone();
        int i2 = hashCode12 * 59;
        if (timeZone != null) {
            i = timeZone.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String str) {
        this.applicationId = str;
    }

    @JsonProperty("creationTime")
    public void setCreationTime(ISO8601 iso8601) {
        this.creationTime = iso8601;
    }

    @JsonProperty("lastModifiedTime")
    public void setLastModifiedTime(ISO8601 iso8601) {
        this.lastModifiedTime = iso8601;
    }

    @JsonProperty("lastSeenTime")
    public void setLastSeenTime(ISO8601 iso8601) {
        this.lastSeenTime = iso8601;
    }

    @JsonProperty("pushDeliveryId")
    public void setPushDeliveryId(String str) {
        this.pushDeliveryId = str;
    }

    @JsonProperty("pushDeliveryService")
    public void setPushDeliveryService(String str) {
        this.pushDeliveryService = str;
    }

    @JsonProperty("pushEndpoint")
    public void setPushEndpoint(Boolean bool) {
        this.pushEndpoint = bool;
    }

    @JsonProperty("pushEndpointARN")
    public void setPushEndpointARN(String str) {
        this.pushEndpointARN = str;
    }

    @JsonProperty("sourceApplicationName")
    public void setSourceApplicationName(String str) {
        this.sourceApplicationName = str;
    }

    @JsonProperty("sourceId")
    public void setSourceId(String str) {
        this.sourceId = str;
    }

    @JsonProperty("sourceStatus")
    public void setSourceStatus(SourceStatus sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    @JsonProperty("sourceVersionHistory")
    public void setSourceVersionHistory(Map<String, ISO8601> map) {
        this.sourceVersionHistory = map;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BaseSourceInfo(sourceId=");
        outline107.append(getSourceId());
        outline107.append(", sourceApplicationName=");
        outline107.append(getSourceApplicationName());
        outline107.append(", creationTime=");
        outline107.append(getCreationTime());
        outline107.append(", lastModifiedTime=");
        outline107.append(getLastModifiedTime());
        outline107.append(", lastSeenTime=");
        outline107.append(getLastSeenTime());
        outline107.append(", sourceStatus=");
        outline107.append(getSourceStatus());
        outline107.append(", sourceVersionHistory=");
        outline107.append(getSourceVersionHistory());
        outline107.append(", pushEndpointARN=");
        outline107.append(getPushEndpointARN());
        outline107.append(", pushDeliveryService=");
        outline107.append(getPushDeliveryService());
        outline107.append(", pushDeliveryId=");
        outline107.append(getPushDeliveryId());
        outline107.append(", pushEndpoint=");
        outline107.append(getPushEndpoint());
        outline107.append(", applicationId=");
        outline107.append(getApplicationId());
        outline107.append(", timeZone=");
        outline107.append(getTimeZone());
        outline107.append(")");
        return outline107.toString();
    }
}
