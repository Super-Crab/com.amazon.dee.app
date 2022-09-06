package com.amazon.whisperjoin.common.sharedtypes.ble.events;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public class EmptyEventData implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<EmptyEventData> CREATOR = new Parcelable.ClassLoaderCreator<EmptyEventData>() { // from class: com.amazon.whisperjoin.common.sharedtypes.ble.events.EmptyEventData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public EmptyEventData[] mo5384newArray(int i) {
            return new EmptyEventData[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public EmptyEventData mo5383createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new EmptyEventData();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public EmptyEventData mo5382createFromParcel(Parcel parcel) {
            return new EmptyEventData();
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
