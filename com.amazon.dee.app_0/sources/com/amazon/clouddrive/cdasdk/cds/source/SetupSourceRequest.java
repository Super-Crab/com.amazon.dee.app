package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.PushProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ArcusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SetupSourceRequest extends CloudDriveRequest {
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
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("osVersion")
    private String osVersion;
    @JsonProperty("pushProvider")
    private PushProvider pushProvider;
    @JsonProperty("registrationToken")
    private String registrationToken;
    @JsonProperty("sourceApplicationName")
    private String sourceApplicationName;
    @JsonProperty("sourceVersion")
    private String sourceVersion;
    @JsonProperty("timeZone")
    private String timeZone;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof SetupSourceRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetupSourceRequest)) {
            return false;
        }
        SetupSourceRequest setupSourceRequest = (SetupSourceRequest) obj;
        if (!setupSourceRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String deviceId = getDeviceId();
        String deviceId2 = setupSourceRequest.getDeviceId();
        if (deviceId != null ? !deviceId.equals(deviceId2) : deviceId2 != null) {
            return false;
        }
        String deviceSerialNumber = getDeviceSerialNumber();
        String deviceSerialNumber2 = setupSourceRequest.getDeviceSerialNumber();
        if (deviceSerialNumber != null ? !deviceSerialNumber.equals(deviceSerialNumber2) : deviceSerialNumber2 != null) {
            return false;
        }
        String deviceClass = getDeviceClass();
        String deviceClass2 = setupSourceRequest.getDeviceClass();
        if (deviceClass != null ? !deviceClass.equals(deviceClass2) : deviceClass2 != null) {
            return false;
        }
        String devicePlatform = getDevicePlatform();
        String devicePlatform2 = setupSourceRequest.getDevicePlatform();
        if (devicePlatform != null ? !devicePlatform.equals(devicePlatform2) : devicePlatform2 != null) {
            return false;
        }
        String deviceModel = getDeviceModel();
        String deviceModel2 = setupSourceRequest.getDeviceModel();
        if (deviceModel != null ? !deviceModel.equals(deviceModel2) : deviceModel2 != null) {
            return false;
        }
        String deviceFriendlyName = getDeviceFriendlyName();
        String deviceFriendlyName2 = setupSourceRequest.getDeviceFriendlyName();
        if (deviceFriendlyName != null ? !deviceFriendlyName.equals(deviceFriendlyName2) : deviceFriendlyName2 != null) {
            return false;
        }
        String osVersion = getOsVersion();
        String osVersion2 = setupSourceRequest.getOsVersion();
        if (osVersion != null ? !osVersion.equals(osVersion2) : osVersion2 != null) {
            return false;
        }
        String sourceApplicationName = getSourceApplicationName();
        String sourceApplicationName2 = setupSourceRequest.getSourceApplicationName();
        if (sourceApplicationName != null ? !sourceApplicationName.equals(sourceApplicationName2) : sourceApplicationName2 != null) {
            return false;
        }
        String sourceVersion = getSourceVersion();
        String sourceVersion2 = setupSourceRequest.getSourceVersion();
        if (sourceVersion != null ? !sourceVersion.equals(sourceVersion2) : sourceVersion2 != null) {
            return false;
        }
        String registrationToken = getRegistrationToken();
        String registrationToken2 = setupSourceRequest.getRegistrationToken();
        if (registrationToken != null ? !registrationToken.equals(registrationToken2) : registrationToken2 != null) {
            return false;
        }
        String deviceLanguage = getDeviceLanguage();
        String deviceLanguage2 = setupSourceRequest.getDeviceLanguage();
        if (deviceLanguage != null ? !deviceLanguage.equals(deviceLanguage2) : deviceLanguage2 != null) {
            return false;
        }
        PushProvider pushProvider = getPushProvider();
        PushProvider pushProvider2 = setupSourceRequest.getPushProvider();
        if (pushProvider != null ? !pushProvider.equals(pushProvider2) : pushProvider2 != null) {
            return false;
        }
        String timeZone = getTimeZone();
        String timeZone2 = setupSourceRequest.getTimeZone();
        return timeZone != null ? timeZone.equals(timeZone2) : timeZone2 == null;
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

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public PushProvider getPushProvider() {
        return this.pushProvider;
    }

    public String getRegistrationToken() {
        return this.registrationToken;
    }

    public String getSourceApplicationName() {
        return this.sourceApplicationName;
    }

    public String getSourceVersion() {
        return this.sourceVersion;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String deviceId = getDeviceId();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (deviceId == null ? 43 : deviceId.hashCode());
        String deviceSerialNumber = getDeviceSerialNumber();
        int hashCode3 = (hashCode2 * 59) + (deviceSerialNumber == null ? 43 : deviceSerialNumber.hashCode());
        String deviceClass = getDeviceClass();
        int hashCode4 = (hashCode3 * 59) + (deviceClass == null ? 43 : deviceClass.hashCode());
        String devicePlatform = getDevicePlatform();
        int hashCode5 = (hashCode4 * 59) + (devicePlatform == null ? 43 : devicePlatform.hashCode());
        String deviceModel = getDeviceModel();
        int hashCode6 = (hashCode5 * 59) + (deviceModel == null ? 43 : deviceModel.hashCode());
        String deviceFriendlyName = getDeviceFriendlyName();
        int hashCode7 = (hashCode6 * 59) + (deviceFriendlyName == null ? 43 : deviceFriendlyName.hashCode());
        String osVersion = getOsVersion();
        int hashCode8 = (hashCode7 * 59) + (osVersion == null ? 43 : osVersion.hashCode());
        String sourceApplicationName = getSourceApplicationName();
        int hashCode9 = (hashCode8 * 59) + (sourceApplicationName == null ? 43 : sourceApplicationName.hashCode());
        String sourceVersion = getSourceVersion();
        int hashCode10 = (hashCode9 * 59) + (sourceVersion == null ? 43 : sourceVersion.hashCode());
        String registrationToken = getRegistrationToken();
        int hashCode11 = (hashCode10 * 59) + (registrationToken == null ? 43 : registrationToken.hashCode());
        String deviceLanguage = getDeviceLanguage();
        int hashCode12 = (hashCode11 * 59) + (deviceLanguage == null ? 43 : deviceLanguage.hashCode());
        PushProvider pushProvider = getPushProvider();
        int hashCode13 = (hashCode12 * 59) + (pushProvider == null ? 43 : pushProvider.hashCode());
        String timeZone = getTimeZone();
        int i2 = hashCode13 * 59;
        if (timeZone != null) {
            i = timeZone.hashCode();
        }
        return i2 + i;
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

    @JsonProperty("deviceSerialNumber")
    public void setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
    }

    @JsonProperty("osVersion")
    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    @JsonProperty("pushProvider")
    public void setPushProvider(PushProvider pushProvider) {
        this.pushProvider = pushProvider;
    }

    @JsonProperty("registrationToken")
    public void setRegistrationToken(String str) {
        this.registrationToken = str;
    }

    @JsonProperty("sourceApplicationName")
    public void setSourceApplicationName(String str) {
        this.sourceApplicationName = str;
    }

    @JsonProperty("sourceVersion")
    public void setSourceVersion(String str) {
        this.sourceVersion = str;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetupSourceRequest(deviceId=");
        outline107.append(getDeviceId());
        outline107.append(", deviceSerialNumber=");
        outline107.append(getDeviceSerialNumber());
        outline107.append(", deviceClass=");
        outline107.append(getDeviceClass());
        outline107.append(", devicePlatform=");
        outline107.append(getDevicePlatform());
        outline107.append(", deviceModel=");
        outline107.append(getDeviceModel());
        outline107.append(", deviceFriendlyName=");
        outline107.append(getDeviceFriendlyName());
        outline107.append(", osVersion=");
        outline107.append(getOsVersion());
        outline107.append(", sourceApplicationName=");
        outline107.append(getSourceApplicationName());
        outline107.append(", sourceVersion=");
        outline107.append(getSourceVersion());
        outline107.append(", registrationToken=");
        outline107.append(getRegistrationToken());
        outline107.append(", deviceLanguage=");
        outline107.append(getDeviceLanguage());
        outline107.append(", pushProvider=");
        outline107.append(getPushProvider());
        outline107.append(", timeZone=");
        outline107.append(getTimeZone());
        outline107.append(")");
        return outline107.toString();
    }
}
