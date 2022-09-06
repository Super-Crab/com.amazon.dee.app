package com.amazon.whisperjoin.common.sharedtypes.ble.commands;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ProvisioningCommandResponse implements Parcelable {
    private static final String DATA_KEY = "da";
    private static final String IDENTIFIER_KEY = "id";
    private static final String STATUS_KEY = "st";
    private final byte[] mData;
    private final UUID mIdentifier;
    private final int mStatus;
    private static final String TAG = ProvisioningCommandResponse.class.getSimpleName();
    public static final Parcelable.ClassLoaderCreator<ProvisioningCommandResponse> CREATOR = new Parcelable.ClassLoaderCreator<ProvisioningCommandResponse>() { // from class: com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommandResponse.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioningCommandResponse[] mo5381newArray(int i) {
            if (i >= 0) {
                return new ProvisioningCommandResponse[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioningCommandResponse mo5379createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ProvisioningCommandResponse(parcel, ProvisioningCommandResponse.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ProvisioningCommandResponse mo5380createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ProvisioningCommandResponse(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public int getStatus() {
        return this.mStatus;
    }

    public String getStatusMessage() {
        if (!ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.containsKey(this.mIdentifier) || !ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.get(this.mIdentifier).containsKey(Integer.valueOf(this.mStatus))) {
            return null;
        }
        return ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.get(this.mIdentifier).get(Integer.valueOf(this.mStatus));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningCommandResponse [id=");
        outline107.append(this.mIdentifier);
        outline107.append(", status=");
        outline107.append(getStatusMessage());
        outline107.append("]");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            Bundle bundle = new Bundle();
            bundle.putString("id", this.mIdentifier.toString());
            bundle.putInt("st", this.mStatus);
            bundle.putByteArray("da", this.mData);
            parcel.writeBundle(bundle);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public ProvisioningCommandResponse(UUID uuid, int i) {
        this(uuid, i, (byte[]) null);
    }

    public ProvisioningCommandResponse(UUID uuid, int i, byte[] bArr) {
        if (uuid != null) {
            this.mIdentifier = uuid;
            this.mStatus = i;
            this.mData = bArr == null ? null : (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("identifier cannot be null.");
    }

    private ProvisioningCommandResponse(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle(classLoader);
        this.mIdentifier = UUID.fromString(readBundle.getString("id"));
        this.mStatus = readBundle.getInt("st");
        this.mData = readBundle.getByteArray("da");
    }
}
