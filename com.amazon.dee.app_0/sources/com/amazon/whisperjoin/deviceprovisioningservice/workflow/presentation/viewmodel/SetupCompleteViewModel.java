package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class SetupCompleteViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<SetupCompleteViewModel> CREATOR = new Parcelable.Creator<SetupCompleteViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public SetupCompleteViewModel mo5674createFromParcel(Parcel parcel) {
            return new SetupCompleteViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public SetupCompleteViewModel[] mo5675newArray(int i) {
            return new SetupCompleteViewModel[i];
        }
    };

    public SetupCompleteViewModel() {
        super(WorkflowStep.SETUP_SUCCESS);
    }

    public SetupCompleteViewModel(Parcel parcel) {
        super(parcel);
    }
}
