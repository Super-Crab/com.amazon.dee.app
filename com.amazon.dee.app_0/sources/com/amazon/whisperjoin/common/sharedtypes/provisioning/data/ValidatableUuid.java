package com.amazon.whisperjoin.common.sharedtypes.provisioning.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ValidatableUuid implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<ValidatableUuid> CREATOR = new Parcelable.ClassLoaderCreator<ValidatableUuid>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.ValidatableUuid.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ValidatableUuid[] mo5435newArray(int i) {
            if (i >= 0) {
                return new ValidatableUuid[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ValidatableUuid mo5434createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ValidatableUuid(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ValidatableUuid mo5433createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ValidatableUuid(parcel, ValidatableUuid.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private final UUID mValue;

    public ValidatableUuid(UUID uuid) {
        if (uuid != null) {
            this.mValue = uuid;
            return;
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UUID getValue() {
        return this.mValue;
    }

    public String toString() {
        return this.mValue.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mValue.toString());
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    ValidatableUuid(Parcel parcel, ClassLoader classLoader) {
        this.mValue = UUID.fromString(parcel.readString());
    }
}
