package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class DataMapValue implements Parcelable {
    public static final Parcelable.Creator<DataMapValue> CREATOR = new Parcelable.Creator<DataMapValue>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMapValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DataMapValue mo5438createFromParcel(Parcel parcel) {
            if (parcel != null) {
                int readInt = parcel.readInt();
                if (readInt == 0) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    return new DataMapValue(bArr);
                }
                boolean z = true;
                if (readInt == 1) {
                    return new DataMapValue(parcel.readString());
                }
                if (readInt == 2) {
                    return new DataMapValue(Integer.valueOf(parcel.readInt()));
                }
                if (readInt == 3) {
                    if (parcel.readInt() != 1) {
                        z = false;
                    }
                    return new DataMapValue(Boolean.valueOf(z));
                }
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unrecognized Type Value: ", readInt));
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DataMapValue[] mo5439newArray(int i) {
            if (i >= 0) {
                return new DataMapValue[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }
    };
    private static final int TYPE_BOOLEAN = 3;
    private static final int TYPE_BYTES = 0;
    private static final int TYPE_INTEGER = 2;
    private static final int TYPE_STRING = 1;
    private Boolean mBooleanValue;
    private byte[] mBytesValue;
    private Integer mIntegerValue;
    private String mStringValue;

    public DataMapValue(byte[] bArr) {
        if (bArr != null) {
            this.mBytesValue = (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("bytesValue can not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DataMapValue.class != obj.getClass()) {
            return false;
        }
        DataMapValue dataMapValue = (DataMapValue) obj;
        return Arrays.equals(this.mBytesValue, dataMapValue.mBytesValue) && Objects.equal(this.mStringValue, dataMapValue.mStringValue) && Objects.equal(this.mIntegerValue, dataMapValue.mIntegerValue) && Objects.equal(this.mBooleanValue, dataMapValue.mBooleanValue);
    }

    public Boolean getBooleanValue() {
        return this.mBooleanValue;
    }

    public byte[] getBytesValue() {
        byte[] bArr = this.mBytesValue;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public Integer getIntegerValue() {
        return this.mIntegerValue;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public int hashCode() {
        return Objects.hashCode(this.mBytesValue, this.mStringValue, this.mIntegerValue, this.mBooleanValue);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            if (this.mBytesValue != null) {
                parcel.writeInt(0);
                parcel.writeInt(this.mBytesValue.length);
                parcel.writeByteArray(this.mBytesValue);
                return;
            } else if (this.mStringValue != null) {
                parcel.writeInt(1);
                parcel.writeString(this.mStringValue);
                return;
            } else if (this.mIntegerValue != null) {
                parcel.writeInt(2);
                parcel.writeInt(this.mIntegerValue.intValue());
                return;
            } else if (this.mBooleanValue == null) {
                return;
            } else {
                parcel.writeInt(3);
                parcel.writeInt(this.mBooleanValue.booleanValue() ? 1 : 0);
                return;
            }
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public DataMapValue(String str) {
        if (str != null) {
            this.mStringValue = str;
            return;
        }
        throw new IllegalArgumentException("stringValue can not be null");
    }

    public DataMapValue(Integer num) {
        if (num != null) {
            this.mIntegerValue = num;
            return;
        }
        throw new IllegalArgumentException("integerValue can not be null");
    }

    public DataMapValue(Boolean bool) {
        if (bool != null) {
            this.mBooleanValue = bool;
            return;
        }
        throw new IllegalArgumentException("booleanValue can not be null");
    }
}
