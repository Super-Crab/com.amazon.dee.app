package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
/* loaded from: classes13.dex */
public abstract class ViewModel implements Parcelable {
    private WorkflowStep mState;

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewModel(WorkflowStep workflowStep) {
        this.mState = workflowStep;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WorkflowStep getState() {
        return this.mState;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mState, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewModel(Parcel parcel) {
        this.mState = (WorkflowStep) parcel.readParcelable(WorkflowStep.class.getClassLoader());
    }
}
