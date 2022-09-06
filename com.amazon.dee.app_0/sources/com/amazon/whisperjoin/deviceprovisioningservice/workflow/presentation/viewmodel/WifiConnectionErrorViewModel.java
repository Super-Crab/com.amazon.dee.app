package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class WifiConnectionErrorViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<WifiConnectionErrorViewModel> CREATOR = new Parcelable.Creator<WifiConnectionErrorViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiConnectionErrorViewModel mo5678createFromParcel(Parcel parcel) {
            return new WifiConnectionErrorViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiConnectionErrorViewModel[] mo5679newArray(int i) {
            return new WifiConnectionErrorViewModel[i];
        }
    };
    private final ProvisioningDetails mProvisioningDetails;
    private final WJError mWJError;
    private final ProvisionableWifiNetworkConnectionError mWifiConnectionError;

    public WifiConnectionErrorViewModel(ProvisionableWifiNetworkConnectionError provisionableWifiNetworkConnectionError, ProvisioningDetails provisioningDetails, WJError wJError) {
        super(WorkflowStep.FIXUP_WIFI_CONNECTION_ERROR);
        if (provisionableWifiNetworkConnectionError != null) {
            if (provisioningDetails == null) {
                throw new IllegalArgumentException("provisioningDetails can not be null");
            }
            if (wJError != null) {
                this.mWifiConnectionError = provisionableWifiNetworkConnectionError;
                this.mProvisioningDetails = provisioningDetails;
                this.mWJError = wJError;
                return;
            }
            throw new IllegalArgumentException("wjError can not be null");
        }
        throw new IllegalArgumentException("wifiNetworkConnectionError can not be null");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public int describeContents() {
        return super.describeContents();
    }

    public ProvisioningDetails getProvisioningDetails() {
        return this.mProvisioningDetails;
    }

    public WJError getWJError() {
        return this.mWJError;
    }

    public ProvisionableWifiNetworkConnectionError getWifiConnectionError() {
        return this.mWifiConnectionError;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mWifiConnectionError, i);
        parcel.writeParcelable(this.mProvisioningDetails, i);
        parcel.writeParcelable(this.mWJError, i);
    }

    public WifiConnectionErrorViewModel(Parcel parcel) {
        super(parcel);
        this.mWifiConnectionError = (ProvisionableWifiNetworkConnectionError) parcel.readParcelable(ProvisionableWifiNetworkConnectionError.class.getClassLoader());
        this.mProvisioningDetails = (ProvisioningDetails) parcel.readParcelable(ProvisioningDetails.class.getClassLoader());
        this.mWJError = (WJError) parcel.readParcelable(WJError.class.getClassLoader());
    }
}
