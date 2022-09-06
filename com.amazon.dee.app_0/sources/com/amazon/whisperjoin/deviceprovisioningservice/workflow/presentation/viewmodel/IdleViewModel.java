package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public class IdleViewModel extends ViewModel implements Parcelable {
    public static final Parcelable.Creator<IdleViewModel> CREATOR = new Parcelable.Creator<IdleViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public IdleViewModel mo5666createFromParcel(Parcel parcel) {
            return new IdleViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public IdleViewModel[] mo5667newArray(int i) {
            return new IdleViewModel[i];
        }
    };

    public IdleViewModel() {
        super(WorkflowStep.IDLE);
    }

    public IdleViewModel(Parcel parcel) {
        super(parcel);
    }
}
