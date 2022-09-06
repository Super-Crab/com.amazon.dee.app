package com.amazon.alexa.smarthomecameras.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.VisibleForTesting;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class CapabilityConfiguration implements Parcelable {
    public static final Parcelable.Creator<CapabilityConfiguration> CREATOR = new Parcelable.Creator<CapabilityConfiguration>() { // from class: com.amazon.alexa.smarthomecameras.model.CapabilityConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CapabilityConfiguration mo2491createFromParcel(Parcel parcel) {
            return new CapabilityConfiguration(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CapabilityConfiguration[] mo2492newArray(int i) {
            return new CapabilityConfiguration[i];
        }
    };
    @SerializedName("isFullDuplexAudioSupported")
    boolean isFullDuplexAudioSupported;

    protected CapabilityConfiguration(Parcel parcel) {
        this.isFullDuplexAudioSupported = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isFullDuplexAudioSupported ? (byte) 1 : (byte) 0);
    }

    @VisibleForTesting
    public CapabilityConfiguration(boolean z) {
        this.isFullDuplexAudioSupported = z;
    }
}
