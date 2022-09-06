package com.amazon.whisperjoin.common.sharedtypes.ble.commands;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class ProvisioningCommand implements Parcelable {
    public static final String DATA_KEY = "da";
    public static final String IDENTIFIER_KEY = "id";
    private final byte[] mData;
    private final UUID mIdentifier;
    private static final String TAG = ProvisioningCommand.class.getSimpleName();
    public static final Parcelable.ClassLoaderCreator<ProvisioningCommand> CREATOR = new Parcelable.ClassLoaderCreator<ProvisioningCommand>() { // from class: com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommand.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioningCommand[] mo5378newArray(int i) {
            if (i >= 0) {
                return new ProvisioningCommand[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioningCommand mo5376createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ProvisioningCommand(parcel, ProvisioningCommand.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ProvisioningCommand mo5377createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ProvisioningCommand(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisioningCommand.class != obj.getClass()) {
            return false;
        }
        ProvisioningCommand provisioningCommand = (ProvisioningCommand) obj;
        return new EqualsBuilder().append(this.mIdentifier, provisioningCommand.mIdentifier).append(this.mData, provisioningCommand.mData).isEquals();
    }

    public byte[] getData() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public UUID getIdentifier() {
        return this.mIdentifier;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mIdentifier).append(this.mData).toHashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningCommand [id=");
        outline107.append(this.mIdentifier);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            Bundle bundle = new Bundle();
            bundle.putString("id", this.mIdentifier.toString());
            bundle.putByteArray(DATA_KEY, this.mData);
            parcel.writeBundle(bundle);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public ProvisioningCommand(UUID uuid) {
        this(uuid, (byte[]) null);
    }

    public ProvisioningCommand(UUID uuid, byte[] bArr) {
        if (uuid != null) {
            this.mIdentifier = uuid;
            this.mData = bArr == null ? null : (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("identifier cannot be null.");
    }

    private ProvisioningCommand(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle(classLoader);
        this.mIdentifier = UUID.fromString(readBundle.getString("id"));
        this.mData = readBundle.getByteArray(DATA_KEY);
    }
}
