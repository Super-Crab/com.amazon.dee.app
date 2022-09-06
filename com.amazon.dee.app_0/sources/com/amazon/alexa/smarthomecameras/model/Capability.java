package com.amazon.alexa.smarthomecameras.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.rtcsc.capabilityagent.common.core.RtcscConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes10.dex */
public class Capability implements Parcelable {
    public static final Parcelable.Creator<Capability> CREATOR = new Parcelable.Creator<Capability>() { // from class: com.amazon.alexa.smarthomecameras.model.Capability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Capability mo2489createFromParcel(Parcel parcel) {
            return new Capability(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Capability[] mo2490newArray(int i) {
            return new Capability[i];
        }
    };
    public static final String ODS_CAPABILITY_INTERFACE = "Alexa.SmartVision.ObjectDetectionSensor";
    @SerializedName(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY)
    public CapabilityConfiguration configuration;
    @SerializedName("instance")
    public String instance;
    @SerializedName("interfaceName")
    public String interfaceName;

    protected Capability(Parcel parcel) {
        this.interfaceName = parcel.readString();
        this.instance = parcel.readString();
        this.configuration = (CapabilityConfiguration) parcel.readParcelable(CapabilityConfiguration.class.getClassLoader());
    }

    public boolean containsInstances(List<String> list) {
        return list.contains(this.instance);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getInstance() {
        return this.instance;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }

    public boolean hasObjectDetectionSensor() {
        return this.interfaceName.equals(ODS_CAPABILITY_INTERFACE);
    }

    public boolean isRangeController() {
        return this.interfaceName.equals("Alexa.RangeController");
    }

    public boolean isRtcSessionController() {
        return this.interfaceName.equals(RtcscConstants.RTCSC_CAPABILITY_AGENT_INTERFACE_NAME);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.interfaceName);
        parcel.writeString(this.instance);
        parcel.writeParcelable(this.configuration, i);
    }

    @VisibleForTesting
    public Capability(String str, String str2) {
        this.interfaceName = str;
        this.instance = str2;
    }
}
