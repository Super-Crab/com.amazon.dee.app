package com.amazon.alexa.devices;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator<Version>() { // from class: com.amazon.alexa.devices.Version.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Version mo1154createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new Version(parcel.readInt(), parcel.readInt(), parcel.readInt());
            }
            throw new IllegalArgumentException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Version[] mo1155newArray(int i) {
            return new Version[i];
        }
    };
    private int mBuild;
    private int mMajor;
    private int mMinor;

    public Version(int i, int i2, int i3) {
        this.mMajor = i;
        this.mMinor = i2;
        this.mBuild = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBuild() {
        return this.mBuild;
    }

    public int getMajor() {
        return this.mMajor;
    }

    public int getMinor() {
        return this.mMinor;
    }

    public String toString() {
        return String.format("%d.%d.%d", Integer.valueOf(this.mMajor), Integer.valueOf(this.mMinor), Integer.valueOf(this.mBuild));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mMajor);
            parcel.writeInt(this.mMinor);
            parcel.writeInt(this.mBuild);
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }
}
