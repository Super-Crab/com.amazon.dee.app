package com.amazon.alexa.smarthomecameras.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.smarthomecameras.constants.MobilyticsConstants;
import com.amazon.alexa.smarthomecameras.constants.ViewConstants;
import com.amazon.alexa.smarthomecameras.ptz.directives.PhysicalPtzDirectiveConstants;
import java.util.List;
/* loaded from: classes10.dex */
public class DevicePayload implements Parcelable {
    public static final Parcelable.Creator<DevicePayload> CREATOR = new Parcelable.Creator<DevicePayload>() { // from class: com.amazon.alexa.smarthomecameras.model.DevicePayload.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DevicePayload mo2495createFromParcel(Parcel parcel) {
            return new DevicePayload(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DevicePayload[] mo2496newArray(int i) {
            return new DevicePayload[i];
        }
    };
    private DeviceCapabilityList capabilityList;
    private String manufacturerName;

    protected DevicePayload(Parcel parcel) {
        this.manufacturerName = parcel.readString();
        this.capabilityList = (DeviceCapabilityList) parcel.readValue(DeviceCapabilityList.class.getClassLoader());
    }

    private boolean supportsPhysicalPTZ(List<String> list) {
        List<Capability> list2;
        DeviceCapabilityList deviceCapabilityList = this.capabilityList;
        if (deviceCapabilityList == null || (list2 = deviceCapabilityList.capabilities) == null) {
            return false;
        }
        for (Capability capability : list2) {
            if (capability.isRangeController() && capability.containsInstances(list)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceCapabilityList getCapabilityList() {
        return this.capabilityList;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public boolean is1P() {
        return this.manufacturerName.equalsIgnoreCase("amazon");
    }

    public boolean isNest() {
        return this.manufacturerName.equalsIgnoreCase(ViewConstants.SmartAlertsGoogleManufacturerName);
    }

    public boolean isNetatmo() {
        return this.manufacturerName.equalsIgnoreCase(MobilyticsConstants.PROVIDER_NETATMO);
    }

    public boolean isiRobot() {
        return this.manufacturerName.equalsIgnoreCase(MobilyticsConstants.PROVIDER_IROBOT);
    }

    public boolean supportsFullDuplexComms() {
        List<Capability> list;
        CapabilityConfiguration capabilityConfiguration;
        DeviceCapabilityList deviceCapabilityList = this.capabilityList;
        if (deviceCapabilityList == null || (list = deviceCapabilityList.capabilities) == null) {
            return false;
        }
        for (Capability capability : list) {
            if (capability.isRtcSessionController() && (capabilityConfiguration = capability.configuration) != null && capabilityConfiguration.isFullDuplexAudioSupported) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsOds() {
        List<Capability> list;
        DeviceCapabilityList deviceCapabilityList = this.capabilityList;
        if (deviceCapabilityList == null || (list = deviceCapabilityList.capabilities) == null) {
            return false;
        }
        for (Capability capability : list) {
            if (capability.hasObjectDetectionSensor()) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsPhysicalPan() {
        return supportsPhysicalPTZ(PhysicalPtzDirectiveConstants.CAMERA_PAN_INSTANCE_LIST);
    }

    public boolean supportsPhysicalTilt() {
        return supportsPhysicalPTZ(PhysicalPtzDirectiveConstants.CAMERA_TILT_INSTANCE_LIST);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.manufacturerName);
        parcel.writeValue(this.capabilityList);
    }

    public DevicePayload(String str, DeviceCapabilityList deviceCapabilityList) {
        this.manufacturerName = str;
        this.capabilityList = deviceCapabilityList;
    }
}
