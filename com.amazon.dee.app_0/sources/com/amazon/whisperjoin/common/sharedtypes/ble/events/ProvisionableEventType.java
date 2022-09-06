package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes13.dex */
public class ProvisionableEventType implements Parcelable {
    private static final String EVENT_KEY_KEY = "ek";
    private static final String EVENT_UUID_KEY = "eu";
    private final int mEventType;
    private final UUID mEventUuid;
    private static final String TAG = ProvisionableEventType.class.getSimpleName();
    public static final Parcelable.ClassLoaderCreator<ProvisionableEventType> CREATOR = new Parcelable.ClassLoaderCreator<ProvisionableEventType>() { // from class: com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventType.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisionableEventType[] mo5390newArray(int i) {
            if (i >= 0) {
                return new ProvisionableEventType[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisionableEventType mo5388createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ProvisionableEventType(parcel, ProvisionableEventType.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ProvisionableEventType mo5389createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ProvisionableEventType(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public UUID getEventUuid() {
        return this.mEventUuid;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisionableEventType [uuid=");
        outline107.append(this.mEventUuid);
        outline107.append(", type=");
        return GeneratedOutlineSupport1.outline86(outline107, this.mEventType, "]");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mEventUuid.toString());
            parcel.writeInt(this.mEventType);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public ProvisionableEventType(UUID uuid, int i) {
        if (uuid != null) {
            this.mEventUuid = uuid;
            this.mEventType = i;
            return;
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    private ProvisionableEventType(Parcel parcel, ClassLoader classLoader) {
        this.mEventUuid = UUID.fromString(parcel.readString());
        this.mEventType = parcel.readInt();
    }
}
