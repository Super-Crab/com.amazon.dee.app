package com.amazon.clouddrive.cdasdk.dps.event;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.clouddrive.cdasdk.dps.common.DPSRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class RecordEventRequest extends DPSRequest {
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("deviceIdentifier")
    private String deviceIdentifier;
    @JsonProperty("deviceTypeId")
    private String deviceTypeId;
    @JsonProperty(JsonFields.EVENT_NAME)
    private String eventName;
    @JsonProperty(WebConstants.WebviewConstants.MARKETPLACE_ID)
    private String marketplaceId;
    @JsonProperty("requestDimensions")
    private Map<String, String> requestDimensions;
    @JsonProperty("softwareVersion")
    private String softwareVersion;

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof RecordEventRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RecordEventRequest)) {
            return false;
        }
        RecordEventRequest recordEventRequest = (RecordEventRequest) obj;
        if (!recordEventRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Integer count = getCount();
        Integer count2 = recordEventRequest.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        String eventName = getEventName();
        String eventName2 = recordEventRequest.getEventName();
        if (eventName != null ? !eventName.equals(eventName2) : eventName2 != null) {
            return false;
        }
        String softwareVersion = getSoftwareVersion();
        String softwareVersion2 = recordEventRequest.getSoftwareVersion();
        if (softwareVersion != null ? !softwareVersion.equals(softwareVersion2) : softwareVersion2 != null) {
            return false;
        }
        String deviceIdentifier = getDeviceIdentifier();
        String deviceIdentifier2 = recordEventRequest.getDeviceIdentifier();
        if (deviceIdentifier != null ? !deviceIdentifier.equals(deviceIdentifier2) : deviceIdentifier2 != null) {
            return false;
        }
        String deviceTypeId = getDeviceTypeId();
        String deviceTypeId2 = recordEventRequest.getDeviceTypeId();
        if (deviceTypeId != null ? !deviceTypeId.equals(deviceTypeId2) : deviceTypeId2 != null) {
            return false;
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recordEventRequest.getMarketplaceId();
        if (marketplaceId != null ? !marketplaceId.equals(marketplaceId2) : marketplaceId2 != null) {
            return false;
        }
        Map<String, String> requestDimensions = getRequestDimensions();
        Map<String, String> requestDimensions2 = recordEventRequest.getRequestDimensions();
        return requestDimensions != null ? requestDimensions.equals(requestDimensions2) : requestDimensions2 == null;
    }

    public Integer getCount() {
        return this.count;
    }

    public String getDeviceIdentifier() {
        return this.deviceIdentifier;
    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public Map<String, String> getRequestDimensions() {
        return this.requestDimensions;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Integer count = getCount();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (count == null ? 43 : count.hashCode());
        String eventName = getEventName();
        int hashCode3 = (hashCode2 * 59) + (eventName == null ? 43 : eventName.hashCode());
        String softwareVersion = getSoftwareVersion();
        int hashCode4 = (hashCode3 * 59) + (softwareVersion == null ? 43 : softwareVersion.hashCode());
        String deviceIdentifier = getDeviceIdentifier();
        int hashCode5 = (hashCode4 * 59) + (deviceIdentifier == null ? 43 : deviceIdentifier.hashCode());
        String deviceTypeId = getDeviceTypeId();
        int hashCode6 = (hashCode5 * 59) + (deviceTypeId == null ? 43 : deviceTypeId.hashCode());
        String marketplaceId = getMarketplaceId();
        int hashCode7 = (hashCode6 * 59) + (marketplaceId == null ? 43 : marketplaceId.hashCode());
        Map<String, String> requestDimensions = getRequestDimensions();
        int i2 = hashCode7 * 59;
        if (requestDimensions != null) {
            i = requestDimensions.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Integer num) {
        this.count = num;
    }

    @JsonProperty("deviceIdentifier")
    public void setDeviceIdentifier(String str) {
        this.deviceIdentifier = str;
    }

    @JsonProperty("deviceTypeId")
    public void setDeviceTypeId(String str) {
        this.deviceTypeId = str;
    }

    @JsonProperty(JsonFields.EVENT_NAME)
    public void setEventName(String str) {
        this.eventName = str;
    }

    @JsonProperty(WebConstants.WebviewConstants.MARKETPLACE_ID)
    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    @JsonProperty("requestDimensions")
    public void setRequestDimensions(Map<String, String> map) {
        this.requestDimensions = map;
    }

    @JsonProperty("softwareVersion")
    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RecordEventRequest(count=");
        outline107.append(getCount());
        outline107.append(", eventName=");
        outline107.append(getEventName());
        outline107.append(", softwareVersion=");
        outline107.append(getSoftwareVersion());
        outline107.append(", deviceIdentifier=");
        outline107.append(getDeviceIdentifier());
        outline107.append(", deviceTypeId=");
        outline107.append(getDeviceTypeId());
        outline107.append(", marketplaceId=");
        outline107.append(getMarketplaceId());
        outline107.append(", requestDimensions=");
        outline107.append(getRequestDimensions());
        outline107.append(")");
        return outline107.toString();
    }
}
