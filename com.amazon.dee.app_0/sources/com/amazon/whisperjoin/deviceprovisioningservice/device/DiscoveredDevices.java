package com.amazon.whisperjoin.deviceprovisioningservice.device;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DiscoveredDevices implements Parcelable {
    public static final Parcelable.Creator<DiscoveredDevice> CREATOR = new Parcelable.Creator<DiscoveredDevice>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevices.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DiscoveredDevice mo5487createFromParcel(Parcel parcel) {
            return DiscoveredDevice.CREATOR.createFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DiscoveredDevice[] mo5488newArray(int i) {
            return new DiscoveredDevice[i];
        }
    };
    private final ImmutableList<DiscoveredDevice> mDevices;

    public DiscoveredDevices(List<DiscoveredDevice> list) {
        Preconditions.checkNotNull(list);
        this.mDevices = ImmutableList.copyOf((Collection) list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<DiscoveredDevice> getDevices() {
        return this.mDevices;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDevices.size());
        UnmodifiableIterator<DiscoveredDevice> mo8029iterator = this.mDevices.mo8029iterator();
        while (mo8029iterator.hasNext()) {
            parcel.writeParcelable(mo8029iterator.next(), i);
        }
    }

    public DiscoveredDevices(Parcel parcel) {
        ImmutableList.Builder builder = ImmutableList.builder();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            builder.mo7849add((ImmutableList.Builder) ((DiscoveredDevice) parcel.readParcelable(DiscoveredDevice.class.getClassLoader())));
        }
        this.mDevices = builder.mo7852build();
    }
}
