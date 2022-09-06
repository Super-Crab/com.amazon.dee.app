package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevices;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class DiscoveredDevicesViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<DiscoveredDevicesViewModel> CREATOR = new Parcelable.Creator<DiscoveredDevicesViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DiscoveredDevicesViewModel mo5662createFromParcel(Parcel parcel) {
            return new DiscoveredDevicesViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DiscoveredDevicesViewModel[] mo5663newArray(int i) {
            return new DiscoveredDevicesViewModel[i];
        }
    };
    private final DiscoveredDevices mDiscoveredDevices;

    public DiscoveredDevicesViewModel(DiscoveredDevices discoveredDevices) {
        super(WorkflowStep.AWAITING_DEVICE_SELECTION);
        if (discoveredDevices != null) {
            this.mDiscoveredDevices = discoveredDevices;
            return;
        }
        throw new IllegalStateException("discoveredDevices can not be null");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DiscoveredDevices getDiscoveredDevices() {
        return this.mDiscoveredDevices;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mDiscoveredDevices, i);
    }

    public DiscoveredDevicesViewModel(Parcel parcel) {
        super(parcel);
        this.mDiscoveredDevices = (DiscoveredDevices) parcel.readParcelable(DiscoveredDevices.class.getClassLoader());
        if (this.mDiscoveredDevices != null) {
            return;
        }
        throw new IllegalStateException("discoveredDevices can not be null");
    }
}
