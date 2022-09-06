package com.google.android.cameraview;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class Size implements Comparable<Size>, Parcelable {
    public static final Parcelable.Creator<Size> CREATOR = new Parcelable.Creator<Size>() { // from class: com.google.android.cameraview.Size.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Size mo7261createFromParcel(Parcel parcel) {
            return new Size(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Size[] mo7262newArray(int i) {
            return new Size[i];
        }
    };
    private final int mHeight;
    private final int mWidth;

    public Size(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public static Size parse(String str) {
        int indexOf = str.indexOf(120);
        if (indexOf != -1) {
            try {
                return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Malformed size: ", str), e);
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Malformed size: ", str));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.mWidth == size.mWidth && this.mHeight == size.mHeight;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        int i = this.mHeight;
        int i2 = this.mWidth;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public String toString() {
        return this.mWidth + ReactProperties.HereMapMarker.X + this.mHeight;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Size size) {
        return (this.mWidth * this.mHeight) - (size.mWidth * size.mHeight);
    }
}
