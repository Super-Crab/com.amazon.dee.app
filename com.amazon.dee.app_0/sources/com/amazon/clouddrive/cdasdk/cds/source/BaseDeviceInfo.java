package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.clouddrive.cdasdk.cds.common.ISO8601;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class BaseDeviceInfo {
    @JsonProperty("creationTime")
    private ISO8601 creationTime;
    @JsonProperty("deviceClass")
    private String deviceClass;
    @JsonProperty("deviceFriendlyName")
    private String deviceFriendlyName;
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty(MetricsConfiguration.DEVICE_LANGUAGE)
    private String deviceLanguage;
    @JsonProperty(ArcusConstants.InputAttribute.DEVICE_MODEL)
    private String deviceModel;
    @JsonProperty("devicePlatform")
    private String devicePlatform;
    @JsonProperty("deviceStatus")
    private String deviceStatus;
    @JsonProperty("lastModifiedTime")
    private ISO8601 lastModifiedTime;
    @JsonProperty("osVersionHistory")
    private Map<String, ISO8601> osVersionHistory;

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseDeviceInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseDeviceInfo)) {
            return false;
        }
        BaseDeviceInfo baseDeviceInfo = (BaseDeviceInfo) obj;
        if (!baseDeviceInfo.canEqual(this)) {
            return false;
        }
        String deviceId = getDeviceId();
        String deviceId2 = baseDeviceInfo.getDeviceId();
        if (deviceId != null ? !deviceId.equals(deviceId2) : deviceId2 != null) {
            return false;
        }
        String deviceClass = getDeviceClass();
        String deviceClass2 = baseDeviceInfo.getDeviceClass();
        if (deviceClass != null ? !deviceClass.equals(deviceClass2) : deviceClass2 != null) {
            return false;
        }
        String devicePlatform = getDevicePlatform();
        String devicePlatform2 = baseDeviceInfo.getDevicePlatform();
        if (devicePlatform != null ? !devicePlatform.equals(devicePlatform2) : devicePlatform2 != null) {
            return false;
        }
        String deviceModel = getDeviceModel();
        String deviceModel2 = baseDeviceInfo.getDeviceModel();
        if (deviceModel != null ? !deviceModel.equals(deviceModel2) : deviceModel2 != null) {
            return false;
        }
        String deviceFriendlyName = getDeviceFriendlyName();
        String deviceFriendlyName2 = baseDeviceInfo.getDeviceFriendlyName();
        if (deviceFriendlyName != null ? !deviceFriendlyName.equals(deviceFriendlyName2) : deviceFriendlyName2 != null) {
            return false;
        }
        String deviceStatus = getDeviceStatus();
        String deviceStatus2 = baseDeviceInfo.getDeviceStatus();
        if (deviceStatus != null ? !deviceStatus.equals(deviceStatus2) : deviceStatus2 != null) {
            return false;
        }
        Map<String, ISO8601> osVersionHistory = getOsVersionHistory();
        Map<String, ISO8601> osVersionHistory2 = baseDeviceInfo.getOsVersionHistory();
        if (osVersionHistory != null ? !osVersionHistory.equals(osVersionHistory2) : osVersionHistory2 != null) {
            return false;
        }
        ISO8601 creationTime = getCreationTime();
        ISO8601 creationTime2 = baseDeviceInfo.getCreationTime();
        if (creationTime != null ? !creationTime.equals(creationTime2) : creationTime2 != null) {
            return false;
        }
        ISO8601 lastModifiedTime = getLastModifiedTime();
        ISO8601 lastModifiedTime2 = baseDeviceInfo.getLastModifiedTime();
        if (lastModifiedTime != null ? !lastModifiedTime.equals(lastModifiedTime2) : lastModifiedTime2 != null) {
            return false;
        }
        String deviceLanguage = getDeviceLanguage();
        String deviceLanguage2 = baseDeviceInfo.getDeviceLanguage();
        return deviceLanguage != null ? deviceLanguage.equals(deviceLanguage2) : deviceLanguage2 == null;
    }

    public ISO8601 getCreationTime() {
        return this.creationTime;
    }

    public String getDeviceClass() {
        return this.deviceClass;
    }

    public String getDeviceFriendlyName() {
        return this.deviceFriendlyName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceLanguage() {
        return this.deviceLanguage;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDevicePlatform() {
        return this.devicePlatform;
    }

    public String getDeviceStatus() {
        return this.deviceStatus;
    }

    public ISO8601 getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public Map<String, ISO8601> getOsVersionHistory() {
        return this.osVersionHistory;
    }

    public int hashCode() {
        String deviceId = getDeviceId();
        int i = 43;
        int hashCode = deviceId == null ? 43 : deviceId.hashCode();
        String deviceClass = getDeviceClass();
        int hashCode2 = ((hashCode + 59) * 59) + (deviceClass == null ? 43 : deviceClass.hashCode());
        String devicePlatform = getDevicePlatform();
        int hashCode3 = (hashCode2 * 59) + (devicePlatform == null ? 43 : devicePlatform.hashCode());
        String deviceModel = getDeviceModel();
        int hashCode4 = (hashCode3 * 59) + (deviceModel == null ? 43 : deviceModel.hashCode());
        String deviceFriendlyName = getDeviceFriendlyName();
        int hashCode5 = (hashCode4 * 59) + (deviceFriendlyName == null ? 43 : deviceFriendlyName.hashCode());
        String deviceStatus = getDeviceStatus();
        int hashCode6 = (hashCode5 * 59) + (deviceStatus == null ? 43 : deviceStatus.hashCode());
        Map<String, ISO8601> osVersionHistory = getOsVersionHistory();
        int hashCode7 = (hashCode6 * 59) + (osVersionHistory == null ? 43 : osVersionHistory.hashCode());
        ISO8601 creationTime = getCreationTime();
        int hashCode8 = (hashCode7 * 59) + (creationTime == null ? 43 : creationTime.hashCode());
        ISO8601 lastModifiedTime = getLastModifiedTime();
        int hashCode9 = (hashCode8 * 59) + (lastModifiedTime == null ? 43 : lastModifiedTime.hashCode());
        String deviceLanguage = getDeviceLanguage();
        int i2 = hashCode9 * 59;
        if (deviceLanguage != null) {
            i = deviceLanguage.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("creationTime")
    public void setCreationTime(ISO8601 iso8601) {
        this.creationTime = iso8601;
    }

    @JsonProperty("deviceClass")
    public void setDeviceClass(String str) {
        this.deviceClass = str;
    }

    @JsonProperty("deviceFriendlyName")
    public void setDeviceFriendlyName(String str) {
        this.deviceFriendlyName = str;
    }

    @JsonProperty("deviceId")
    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    @JsonProperty(MetricsConfiguration.DEVICE_LANGUAGE)
    public void setDeviceLanguage(String str) {
        this.deviceLanguage = str;
    }

    @JsonProperty(ArcusConstants.InputAttribute.DEVICE_MODEL)
    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    @JsonProperty("devicePlatform")
    public void setDevicePlatform(String str) {
        this.devicePlatform = str;
    }

    @JsonProperty("deviceStatus")
    public void setDeviceStatus(String str) {
        this.deviceStatus = str;
    }

    @JsonProperty("lastModifiedTime")
    public void setLastModifiedTime(ISO8601 iso8601) {
        this.lastModifiedTime = iso8601;
    }

    @JsonProperty("osVersionHistory")
    public void setOsVersionHistory(Map<String, ISO8601> map) {
        this.osVersionHistory = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BaseDeviceInfo(deviceId=");
        outline107.append(getDeviceId());
        outline107.append(", deviceClass=");
        outline107.append(getDeviceClass());
        outline107.append(", devicePlatform=");
        outline107.append(getDevicePlatform());
        outline107.append(", deviceModel=");
        outline107.append(getDeviceModel());
        outline107.append(", deviceFriendlyName=");
        outline107.append(getDeviceFriendlyName());
        outline107.append(", deviceStatus=");
        outline107.append(getDeviceStatus());
        outline107.append(", osVersionHistory=");
        outline107.append(getOsVersionHistory());
        outline107.append(", creationTime=");
        outline107.append(getCreationTime());
        outline107.append(", lastModifiedTime=");
        outline107.append(getLastModifiedTime());
        outline107.append(", deviceLanguage=");
        outline107.append(getDeviceLanguage());
        outline107.append(")");
        return outline107.toString();
    }
}
