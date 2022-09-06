package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.NonNull;
/* loaded from: classes13.dex */
public final class RtcscAppInfo implements Parcelable {
    public static final Parcelable.Creator<RtcscAppInfo> CREATOR = new Parcelable.Creator<RtcscAppInfo>() { // from class: com.amazon.rtcsc.interfaces.RtcscAppInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscAppInfo mo4500createFromParcel(Parcel parcel) {
            return new RtcscAppInfo(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscAppInfo[] mo4501newArray(int i) {
            return new RtcscAppInfo[i];
        }
    };
    public String mAppIdentifier;

    public RtcscAppInfo(@NonNull String str) {
        if (str != null) {
            this.mAppIdentifier = str;
            return;
        }
        throw new IllegalArgumentException("appIdentifier is null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAppIdentifier() {
        return this.mAppIdentifier;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAppIdentifier);
    }
}
