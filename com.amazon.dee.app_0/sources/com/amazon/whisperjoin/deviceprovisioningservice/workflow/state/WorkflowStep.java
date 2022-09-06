package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum WorkflowStep implements Parcelable {
    IDLE,
    DISCOVERING,
    AWAITING_DEVICE_SELECTION,
    CONNECTING_TO_DEVICE,
    GETTING_PROVISIONING_DETAILS,
    VALIDATING_WIFI_SYNC_AUTH_TOKEN,
    AWAITING_PROVISIONING_DATA,
    PROVISIONING_DEVICE,
    VERIFYING_PROVISIONING,
    FIXUP_WIFI_CONNECTION_ERROR,
    SETUP_FAILURE,
    SETUP_SUCCESS;
    
    public static final Parcelable.Creator<WorkflowStep> CREATOR = new Parcelable.Creator<WorkflowStep>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WorkflowStep mo5707createFromParcel(Parcel parcel) {
            return WorkflowStep.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WorkflowStep[] mo5708newArray(int i) {
            return new WorkflowStep[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
