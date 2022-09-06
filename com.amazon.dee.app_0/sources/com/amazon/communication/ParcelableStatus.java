package com.amazon.communication;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes12.dex */
public class ParcelableStatus implements Parcelable {
    public static final Parcelable.Creator<ParcelableStatus> CREATOR = new Creator();
    private int mStatusCode;
    private String mStatusMessage;

    /* loaded from: classes12.dex */
    static class Creator implements Parcelable.Creator<ParcelableStatus> {
        Creator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelableStatus mo3287createFromParcel(Parcel parcel) {
            return new ParcelableStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelableStatus[] mo3288newArray(int i) {
            return new ParcelableStatus[i];
        }
    }

    public ParcelableStatus() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    public void readFromParcel(Parcel parcel) {
        this.mStatusCode = parcel.readInt();
        this.mStatusMessage = parcel.readString();
    }

    public void setStatusCode(int i) {
        this.mStatusCode = i;
    }

    public void setStatusMessage(String str) {
        this.mStatusMessage = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mStatusCode);
        parcel.writeString(this.mStatusMessage);
    }

    public ParcelableStatus(Parcel parcel) {
        readFromParcel(parcel);
    }
}
