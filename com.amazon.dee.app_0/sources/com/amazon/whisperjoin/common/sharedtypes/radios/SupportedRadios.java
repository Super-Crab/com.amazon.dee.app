package com.amazon.whisperjoin.common.sharedtypes.radios;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
/* loaded from: classes13.dex */
public enum SupportedRadios implements Parcelable {
    UNKNOWN(-1, "UNKNOWN"),
    WIFI_P2P_PHONE(0, "WIFI_P2P_PHONE"),
    WIFI_P2P_ASSISTED(1, "WIFI_P2P_AMAZON_DEVICE_HOSTED"),
    WIFI_INFRASTRUCTURE(3, "WIFI_INFRASTRUCTURE"),
    BLE(4, ProtocolType.BLE);
    
    public static final Parcelable.Creator<SupportedRadios> CREATOR = new Parcelable.Creator<SupportedRadios>() { // from class: com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SupportedRadios mo5472createFromParcel(Parcel parcel) {
            return SupportedRadios.values()[parcel.readInt()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SupportedRadios[] mo5473newArray(int i) {
            return new SupportedRadios[i];
        }
    };
    private final String mString;
    private final int mValue;

    SupportedRadios(int i, String str) {
        this.mValue = i;
        this.mString = str;
    }

    public static SupportedRadios fromInt(int i) {
        if (i != -1) {
            if (i == 0) {
                return WIFI_P2P_PHONE;
            }
            if (i == 1) {
                return WIFI_P2P_ASSISTED;
            }
            if (i == 3) {
                return WIFI_INFRASTRUCTURE;
            }
            if (i == 4) {
                return BLE;
            }
            throw new IllegalArgumentException("Unknown SupportedRadios encountered.");
        }
        return UNKNOWN;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getString() {
        return this.mString;
    }

    public int getValue() {
        return this.mValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
