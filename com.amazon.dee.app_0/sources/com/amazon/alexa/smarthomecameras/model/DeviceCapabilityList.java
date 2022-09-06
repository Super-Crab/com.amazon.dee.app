package com.amazon.alexa.smarthomecameras.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class DeviceCapabilityList implements Parcelable {
    public static final Parcelable.Creator<DeviceCapabilityList> CREATOR = new Parcelable.Creator<DeviceCapabilityList>() { // from class: com.amazon.alexa.smarthomecameras.model.DeviceCapabilityList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DeviceCapabilityList mo2493createFromParcel(Parcel parcel) {
            return new DeviceCapabilityList(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DeviceCapabilityList[] mo2494newArray(int i) {
            return new DeviceCapabilityList[i];
        }
    };
    @SerializedName(CamerasRouteParameter.CAPABILITIES)
    List<Capability> capabilities;

    protected DeviceCapabilityList(Parcel parcel) {
        if (parcel.readByte() == 1) {
            this.capabilities = new ArrayList();
            parcel.readList(this.capabilities, Capability.class.getClassLoader());
            return;
        }
        this.capabilities = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Capability> getCapabilities() {
        return this.capabilities;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.capabilities == null) {
            parcel.writeByte((byte) 0);
            return;
        }
        parcel.writeByte((byte) 1);
        parcel.writeList(this.capabilities);
    }

    public DeviceCapabilityList(List<Capability> list) {
        this.capabilities = list;
    }
}
