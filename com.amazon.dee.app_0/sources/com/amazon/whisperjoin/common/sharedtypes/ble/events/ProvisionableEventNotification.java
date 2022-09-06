package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ProvisionableEventNotification implements Parcelable {
    private final int mEventKey;
    private final int mEventType;
    private static final String TAG = ProvisionableEventNotification.class.getSimpleName();
    public static final Parcelable.ClassLoaderCreator<ProvisionableEventNotification> CREATOR = new Parcelable.ClassLoaderCreator<ProvisionableEventNotification>() { // from class: com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisionableEventNotification[] mo5387newArray(int i) {
            if (i >= 0) {
                return new ProvisionableEventNotification[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisionableEventNotification mo5385createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ProvisionableEventNotification(parcel, ProvisionableEventNotification.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ProvisionableEventNotification mo5386createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ProvisionableEventNotification(parcel, classLoader);
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
        if (obj == null || ProvisionableEventNotification.class != obj.getClass()) {
            return false;
        }
        ProvisionableEventNotification provisionableEventNotification = (ProvisionableEventNotification) obj;
        return this.mEventKey == provisionableEventNotification.mEventKey && this.mEventType == provisionableEventNotification.mEventType;
    }

    public int getEventKey() {
        return this.mEventKey;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public int hashCode() {
        return (this.mEventKey * 31) + this.mEventType;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningEventNotification [key=");
        outline107.append(this.mEventKey);
        outline107.append(", type=");
        return GeneratedOutlineSupport1.outline86(outline107, this.mEventType, "]");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mEventKey);
            parcel.writeInt(this.mEventType);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public ProvisionableEventNotification(int i, int i2) {
        this.mEventKey = i;
        this.mEventType = i2;
    }

    private ProvisionableEventNotification(Parcel parcel, ClassLoader classLoader) {
        this.mEventKey = parcel.readInt();
        this.mEventType = parcel.readInt();
    }
}
