package com.amazon.whisperjoin.common.sharedtypes.error;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import java.util.Locale;
/* loaded from: classes13.dex */
public class WJError extends Exception implements Parcelable {
    public static final Parcelable.Creator<WJError> CREATOR = new Parcelable.Creator<WJError>() { // from class: com.amazon.whisperjoin.common.sharedtypes.error.WJError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WJError mo5392createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WJError(parcel);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WJError[] mo5393newArray(int i) {
            if (i >= 0) {
                return new WJError[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }
    };
    private final int mDomain;
    private final int mErrorDetails;
    private final int mErrorType;
    private final int mResolution;

    public WJError(int i, int i2, int i3, int i4) {
        this.mDomain = i;
        this.mErrorType = i2;
        this.mErrorDetails = i3;
        this.mResolution = i4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WJError.class != obj.getClass()) {
            return false;
        }
        WJError wJError = (WJError) obj;
        return this.mDomain == wJError.mDomain && this.mErrorType == wJError.mErrorType && this.mErrorDetails == wJError.mErrorDetails && this.mResolution == wJError.mResolution;
    }

    public int getDomain() {
        return this.mDomain;
    }

    public String getErrorCode() {
        return String.format(Locale.ENGLISH, "%d:%d:%d:%d", Integer.valueOf(getDomain()), Integer.valueOf(getErrorType()), Integer.valueOf(getErrorDetails()), Integer.valueOf(getResolution()));
    }

    public int getErrorDetails() {
        return this.mErrorDetails;
    }

    public int getErrorType() {
        return this.mErrorType;
    }

    public int getResolution() {
        return this.mResolution;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDomain), Integer.valueOf(this.mErrorType), Integer.valueOf(this.mErrorDetails), Integer.valueOf(this.mResolution));
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WJError [");
        outline107.append(getErrorCode());
        outline107.append("]");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDomain);
        parcel.writeInt(this.mErrorType);
        parcel.writeInt(this.mErrorDetails);
        parcel.writeInt(this.mResolution);
    }

    public WJError(Parcel parcel) {
        this.mDomain = parcel.readInt();
        this.mErrorType = parcel.readInt();
        this.mErrorDetails = parcel.readInt();
        this.mResolution = parcel.readInt();
    }

    public WJError(int i, int i2, int i3) {
        this(i, i2, CommonErrorDetails.NONE, i3);
    }
}
