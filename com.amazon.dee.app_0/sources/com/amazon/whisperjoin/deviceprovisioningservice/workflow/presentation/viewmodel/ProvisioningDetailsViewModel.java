package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class ProvisioningDetailsViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<ProvisioningDetailsViewModel> CREATOR = new Parcelable.Creator<ProvisioningDetailsViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioningDetailsViewModel mo5670createFromParcel(Parcel parcel) {
            return new ProvisioningDetailsViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioningDetailsViewModel[] mo5671newArray(int i) {
            return new ProvisioningDetailsViewModel[i];
        }
    };
    private final ProvisioningDetails mProvisioningDetails;

    public ProvisioningDetailsViewModel(ProvisioningDetails provisioningDetails) {
        super(WorkflowStep.AWAITING_PROVISIONING_DATA);
        this.mProvisioningDetails = provisioningDetails;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProvisioningDetails getProvisioningDetails() {
        return this.mProvisioningDetails;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mProvisioningDetails, i);
    }

    public ProvisioningDetailsViewModel(Parcel parcel) {
        super(parcel);
        this.mProvisioningDetails = (ProvisioningDetails) parcel.readParcelable(ProvisioningDetails.class.getClassLoader());
    }
}
