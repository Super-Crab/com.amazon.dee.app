package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes13.dex */
public class AvailableWifiNetworks implements Parcelable {
    public static final Parcelable.Creator<AvailableWifiNetworks> CREATOR = new Parcelable.Creator<AvailableWifiNetworks>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetworks.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AvailableWifiNetworks mo5685createFromParcel(Parcel parcel) {
            return new AvailableWifiNetworks(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AvailableWifiNetworks[] mo5686newArray(int i) {
            return new AvailableWifiNetworks[i];
        }
    };
    private List<AvailableWifiNetwork> mConfiguredNetworks;
    private boolean mHasPreferred;
    private AvailableWifiNetwork mPreferred;
    private List<AvailableWifiNetwork> mUnrecognizedNetworks;

    public AvailableWifiNetworks(List<AvailableWifiNetwork> list, List<AvailableWifiNetwork> list2, AvailableWifiNetwork availableWifiNetwork) {
        this.mConfiguredNetworks = list;
        this.mUnrecognizedNetworks = list2;
        this.mPreferred = availableWifiNetwork;
        this.mHasPreferred = this.mPreferred != null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AvailableWifiNetworks.class != obj.getClass()) {
            return false;
        }
        AvailableWifiNetworks availableWifiNetworks = (AvailableWifiNetworks) obj;
        if (this.mHasPreferred != availableWifiNetworks.mHasPreferred || !this.mConfiguredNetworks.equals(availableWifiNetworks.mConfiguredNetworks) || !this.mUnrecognizedNetworks.equals(availableWifiNetworks.mUnrecognizedNetworks)) {
            return false;
        }
        AvailableWifiNetwork availableWifiNetwork = this.mPreferred;
        AvailableWifiNetwork availableWifiNetwork2 = availableWifiNetworks.mPreferred;
        return availableWifiNetwork != null ? availableWifiNetwork.equals(availableWifiNetwork2) : availableWifiNetwork2 == null;
    }

    public List<AvailableWifiNetwork> getConfiguredNetworks() {
        return Collections.unmodifiableList(this.mConfiguredNetworks);
    }

    public AvailableWifiNetwork getPreferredConfiguredNetwork() {
        return this.mPreferred;
    }

    public List<AvailableWifiNetwork> getUnrecognizedNetworks() {
        return Collections.unmodifiableList(this.mUnrecognizedNetworks);
    }

    public boolean hasPreferred() {
        return this.mHasPreferred;
    }

    public int hashCode() {
        int hashCode = (((this.mUnrecognizedNetworks.hashCode() + (this.mConfiguredNetworks.hashCode() * 31)) * 31) + (this.mHasPreferred ? 1 : 0)) * 31;
        AvailableWifiNetwork availableWifiNetwork = this.mPreferred;
        return hashCode + (availableWifiNetwork != null ? availableWifiNetwork.hashCode() : 0);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AvailableWifiNetworks Configured Count: %d, Unrecognized Count: %d", Integer.valueOf(this.mConfiguredNetworks.size()), Integer.valueOf(this.mUnrecognizedNetworks.size()));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mConfiguredNetworks);
        parcel.writeList(this.mUnrecognizedNetworks);
        parcel.writeInt(this.mHasPreferred ? 1 : 0);
        if (this.mHasPreferred) {
            parcel.writeParcelable(this.mPreferred, i);
        }
    }

    public AvailableWifiNetworks(Parcel parcel) {
        this.mConfiguredNetworks = parcel.readArrayList(AvailableWifiNetwork.class.getClassLoader());
        this.mUnrecognizedNetworks = parcel.readArrayList(AvailableWifiNetwork.class.getClassLoader());
        this.mHasPreferred = parcel.readInt() != 1 ? false : true;
        if (this.mHasPreferred) {
            this.mPreferred = (AvailableWifiNetwork) parcel.readParcelable(AvailableWifiNetwork.class.getClassLoader());
        } else {
            this.mPreferred = null;
        }
    }
}
