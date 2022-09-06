package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class InProgressViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<InProgressViewModel> CREATOR = new Parcelable.Creator<InProgressViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public InProgressViewModel mo5668createFromParcel(Parcel parcel) {
            return new InProgressViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public InProgressViewModel[] mo5669newArray(int i) {
            return new InProgressViewModel[i];
        }
    };

    public InProgressViewModel(WorkflowStep workflowStep) {
        super(workflowStep);
    }

    public InProgressViewModel(Parcel parcel) {
        super(parcel);
    }
}
