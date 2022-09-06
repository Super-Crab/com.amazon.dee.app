package com.amazon.deecomms.ndt.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import java.util.List;
/* loaded from: classes12.dex */
public class DeviceModel {
    @JsonProperty("commsFeaturesStatusList")
    private List<CommsFeatureStatus> commsFeatureStatuses;
    @JsonProperty("deviceGruu")
    private String deviceGruu;
    @JsonProperty(DefaultBluetoothService.AUDIO_DEVICE_NAME)
    private String deviceName;
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("deviceStatus")
    private DeviceStatusModel deviceStatus;
    @JsonProperty("deviceType")
    private String deviceType;
    @JsonProperty("personalizedAccounts")
    private List<PersonalizedAccount> personalizedAccounts;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceModel.class != obj.getClass()) {
            return false;
        }
        DeviceModel deviceModel = (DeviceModel) obj;
        return Objects.equal(this.deviceGruu, deviceModel.deviceGruu) && Objects.equal(this.deviceName, deviceModel.deviceName) && Objects.equal(this.deviceSerialNumber, deviceModel.deviceSerialNumber) && Objects.equal(this.personalizedAccounts, deviceModel.personalizedAccounts) && Objects.equal(this.deviceStatus, deviceModel.deviceStatus) && Objects.equal(this.commsFeatureStatuses, deviceModel.commsFeatureStatuses);
    }

    public List<CommsFeatureStatus> getCommsFeatureStatuses() {
        return this.commsFeatureStatuses;
    }

    public String getDeviceGruu() {
        return this.deviceGruu;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @NonNull
    public DeviceStatusModel getDeviceStatus() {
        return this.deviceStatus;
    }

    @Nullable
    public String getDropInUnsupportedReason() {
        List<CommsFeatureStatus> list = this.commsFeatureStatuses;
        if (list == null) {
            return null;
        }
        for (CommsFeatureStatus commsFeatureStatus : list) {
            if (SupportedFeature.InboundDropIn == SupportedFeature.lookup(commsFeatureStatus.getFeature()) && !commsFeatureStatus.isSupported()) {
                return commsFeatureStatus.getReason();
            }
        }
        return null;
    }

    public List<PersonalizedAccount> getPersonalizedAccounts() {
        return this.personalizedAccounts;
    }

    public int hashCode() {
        return Objects.hashCode(this.deviceGruu, this.deviceName, this.personalizedAccounts, this.deviceStatus, this.commsFeatureStatuses);
    }

    public void setCommsFeatureStatuses(List<CommsFeatureStatus> list) {
        this.commsFeatureStatuses = list;
    }

    public void setDeviceGruu(String str) {
        this.deviceGruu = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceSerialNumber(@NonNull String str) {
        this.deviceSerialNumber = str;
    }

    public void setDeviceStatus(DeviceStatusModel deviceStatusModel) {
        this.deviceStatus = deviceStatusModel;
    }

    public void setPersonalizedAccounts(List<PersonalizedAccount> list) {
        this.personalizedAccounts = list;
    }
}
