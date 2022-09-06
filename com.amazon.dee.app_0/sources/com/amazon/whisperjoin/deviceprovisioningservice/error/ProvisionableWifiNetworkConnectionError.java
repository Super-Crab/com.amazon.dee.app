package com.amazon.whisperjoin.deviceprovisioningservice.error;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class ProvisionableWifiNetworkConnectionError extends ProvisioningFailureException implements Parcelable {
    public static final Parcelable.Creator<ProvisionableWifiNetworkConnectionError> CREATOR = new Parcelable.Creator<ProvisionableWifiNetworkConnectionError>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisionableWifiNetworkConnectionError mo5635createFromParcel(Parcel parcel) {
            return new ProvisionableWifiNetworkConnectionError(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisionableWifiNetworkConnectionError[] mo5636newArray(int i) {
            return new ProvisionableWifiNetworkConnectionError[i];
        }
    };
    private final WifiConfiguration mAttemptedWifiConfiguration;
    private final WifiConnectionDetails mWifiConnectionDetails;

    public ProvisionableWifiNetworkConnectionError(WifiConnectionDetails wifiConnectionDetails, WifiConfiguration wifiConfiguration) {
        if (wifiConnectionDetails != null) {
            if (wifiConfiguration != null) {
                this.mWifiConnectionDetails = wifiConnectionDetails;
                this.mAttemptedWifiConfiguration = wifiConfiguration;
                return;
            }
            throw new IllegalArgumentException("wifiConfiguration can not be null");
        }
        throw new IllegalArgumentException("wifiConnectionDetails can not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableWifiNetworkConnectionError.class != obj.getClass()) {
            return false;
        }
        ProvisionableWifiNetworkConnectionError provisionableWifiNetworkConnectionError = (ProvisionableWifiNetworkConnectionError) obj;
        return new EqualsBuilder().append(this.mWifiConnectionDetails, provisionableWifiNetworkConnectionError.mWifiConnectionDetails).append(this.mAttemptedWifiConfiguration, provisionableWifiNetworkConnectionError.mAttemptedWifiConfiguration).isEquals();
    }

    public WifiConfiguration getAttemptedWifiConfiguration() {
        return this.mAttemptedWifiConfiguration;
    }

    public String getSSID() {
        return this.mWifiConnectionDetails.getSsid();
    }

    public WifiConnectionDetails.State getState() {
        return this.mWifiConnectionDetails.getConnectionState();
    }

    public WifiConnectionDetails getWifiConnectionDetails() {
        return this.mWifiConnectionDetails;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mWifiConnectionDetails).append(this.mAttemptedWifiConfiguration).toHashCode();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mWifiConnectionDetails", this.mWifiConnectionDetails).add("mAttemptedWifiConfiguration", this.mAttemptedWifiConfiguration).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mWifiConnectionDetails, i);
        parcel.writeParcelable(this.mAttemptedWifiConfiguration, i);
    }

    public ProvisionableWifiNetworkConnectionError(Parcel parcel) {
        if (parcel != null) {
            this.mWifiConnectionDetails = (WifiConnectionDetails) parcel.readParcelable(WifiConnectionDetails.class.getClassLoader());
            this.mAttemptedWifiConfiguration = (WifiConfiguration) parcel.readParcelable(WifiConfiguration.class.getClassLoader());
            return;
        }
        throw new IllegalArgumentException("parcel can not be null");
    }
}
