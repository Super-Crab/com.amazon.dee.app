package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class WifiNetwork implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<WifiNetwork> CREATOR = new Parcelable.ClassLoaderCreator<WifiNetwork>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiNetwork[] mo5468newArray(int i) {
            if (i >= 0) {
                return new WifiNetwork[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiNetwork mo5467createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new WifiNetwork(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiNetwork mo5466createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WifiNetwork(parcel, WifiNetwork.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    protected String mSsid;
    protected WifiKeyManagement mWifiKeyManagement;

    public WifiNetwork(String str, WifiKeyManagement wifiKeyManagement) {
        if (str != null && str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) && str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED)) {
            this.mSsid = GeneratedOutlineSupport1.outline51(str, 1, 1);
            this.mWifiKeyManagement = wifiKeyManagement;
            validateInternal();
            return;
        }
        throw new IllegalArgumentException("Invalid wifi network. SSID is not quoted. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
    }

    private void validateInternal() {
        if (!InputValidator.isValidSsid(getSsid()) || !InputValidator.isValidWifiKeyManagement(this.mWifiKeyManagement)) {
            throw new IllegalArgumentException("Invalid wifi network. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof WifiNetwork)) {
            if (obj == this) {
                return true;
            }
            WifiNetwork wifiNetwork = (WifiNetwork) obj;
            return this.mSsid.equals(wifiNetwork.mSsid) && this.mWifiKeyManagement.equals(wifiNetwork.mWifiKeyManagement);
        }
        return false;
    }

    public WifiKeyManagement getKeyManagement() {
        return this.mWifiKeyManagement;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.mSsid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public int hashCode() {
        return this.mWifiKeyManagement.ordinal() + GeneratedOutlineSupport1.outline7(this.mSsid, 41, 41);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiNetwork [ssid=--REDACTED--, key-mgmt=");
        outline107.append(this.mWifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validate() {
        validateInternal();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mSsid);
            parcel.writeInt(this.mWifiKeyManagement.ordinal());
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public WifiNetwork(WifiNetwork wifiNetwork) {
        this.mSsid = wifiNetwork.mSsid;
        this.mWifiKeyManagement = wifiNetwork.mWifiKeyManagement;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WifiNetwork(Parcel parcel, ClassLoader classLoader) {
        this.mSsid = parcel.readString();
        this.mWifiKeyManagement = WifiKeyManagement.values()[parcel.readInt()];
        validateInternal();
    }
}
