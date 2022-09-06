package com.amazon.deecomms.ndt.model;

import amazon.speech.simclient.settings.Settings;
import com.amazon.deecomms.calling.enums.AnnouncementAvailability;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class DeviceStatusModel {
    @JsonProperty("activeState")
    private ActiveState activeState;
    @JsonProperty("announcementAvailability")
    private AnnouncementAvailability deviceAnnouncementAvailability;
    @JsonProperty("deviceCommsAvailability")
    private DeviceCommsAvailability deviceCommsAvailability;
    @JsonProperty("deviceDropInAvailability")
    private DropInAvailability deviceDropInAvailability;
    @JsonProperty(Settings.DND)
    private boolean dnd;
    @JsonProperty("isA2dpBluetoothConnected")
    private Boolean isA2dpBluetoothConnected;
    @JsonProperty("isAuxConnected")
    private Boolean isAuxConnected;
    @JsonProperty("inCall")
    private boolean isInCall;
    @JsonProperty("online")
    private boolean isOnline;
    @JsonProperty("registered")
    private boolean isRegistered;
    @JsonProperty("videoAvailability")
    private boolean isVideoEnabled = true;

    public boolean equals(Object obj) {
        Boolean bool;
        Boolean bool2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceStatusModel)) {
            return false;
        }
        DeviceStatusModel deviceStatusModel = (DeviceStatusModel) obj;
        return this.dnd == deviceStatusModel.dnd && this.isInCall == deviceStatusModel.isInCall && this.isOnline == deviceStatusModel.isOnline && this.activeState == deviceStatusModel.activeState && this.deviceDropInAvailability == deviceStatusModel.deviceDropInAvailability && this.deviceCommsAvailability == deviceStatusModel.deviceCommsAvailability && this.deviceAnnouncementAvailability == deviceStatusModel.deviceAnnouncementAvailability && this.isRegistered == deviceStatusModel.isRegistered && this.isVideoEnabled == deviceStatusModel.isVideoEnabled && (bool = this.isA2dpBluetoothConnected) == bool && (bool2 = this.isAuxConnected) == bool2;
    }

    public ActiveState getActiveState() {
        return this.activeState;
    }

    public AnnouncementAvailability getDeviceAnnouncementAvailability() {
        return this.deviceAnnouncementAvailability;
    }

    public DeviceCommsAvailability getDeviceCommsAvailability() {
        return this.deviceCommsAvailability;
    }

    public DropInAvailability getDeviceDropInAvailability() {
        return this.deviceDropInAvailability;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.dnd), Boolean.valueOf(this.isInCall), Boolean.valueOf(this.isOnline), this.activeState, this.deviceDropInAvailability, this.deviceCommsAvailability, this.deviceAnnouncementAvailability, Boolean.valueOf(this.isRegistered), Boolean.valueOf(this.isVideoEnabled), this.isA2dpBluetoothConnected, this.isAuxConnected);
    }

    public Boolean isA2dpBluetoothConnected() {
        return this.isA2dpBluetoothConnected;
    }

    public Boolean isAuxConnected() {
        return this.isAuxConnected;
    }

    public boolean isDnd() {
        return this.dnd;
    }

    public boolean isInCall() {
        return this.isInCall;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public boolean isVideoEnabled() {
        return this.isVideoEnabled;
    }

    public void setActiveState(ActiveState activeState) {
        this.activeState = activeState;
    }

    public void setDeviceAnnouncementAvailability(AnnouncementAvailability announcementAvailability) {
        this.deviceAnnouncementAvailability = announcementAvailability;
    }

    public void setDeviceCommsAvailability(DeviceCommsAvailability deviceCommsAvailability) {
        this.deviceCommsAvailability = deviceCommsAvailability;
    }

    public void setDeviceDropInAvailability(DropInAvailability dropInAvailability) {
        this.deviceDropInAvailability = dropInAvailability;
    }

    public void setDnd(boolean z) {
        this.dnd = z;
    }

    public void setInCall(boolean z) {
        this.isInCall = z;
    }

    public void setOnline(boolean z) {
        this.isOnline = z;
    }

    public void setRegistered(boolean z) {
        this.isRegistered = z;
    }

    public void setVideoEnabled(boolean z) {
        this.isVideoEnabled = z;
    }
}
