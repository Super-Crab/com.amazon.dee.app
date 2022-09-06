package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
/* loaded from: classes13.dex */
public class FailureViewModel implements Parcelable {
    public static final Parcelable.Creator<FailureViewModel> CREATOR = new Parcelable.Creator<FailureViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.FailureViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public FailureViewModel mo5664createFromParcel(Parcel parcel) {
            return new FailureViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public FailureViewModel[] mo5665newArray(int i) {
            return new FailureViewModel[i];
        }
    };
    private static final String KEY_CAUSE = "cause";
    private static final String KEY_STACK_TRACE = "stacktrace";
    private static final String KEY_WJ_ERROR = "wjError";
    private final String mFailureName;
    private final String mFailureStacktrace;
    private final WJError mWJError;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFailureName() {
        return this.mFailureName;
    }

    public String getFailureStacktrace() {
        return this.mFailureStacktrace;
    }

    public WJError getWJError() {
        return this.mWJError;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CAUSE, this.mFailureName);
        bundle.putSerializable(KEY_STACK_TRACE, this.mFailureStacktrace);
        bundle.putParcelable(KEY_WJ_ERROR, this.mWJError);
        parcel.writeBundle(bundle);
    }

    public FailureViewModel(String str, String str2, WJError wJError) {
        this.mFailureName = str;
        this.mFailureStacktrace = str2;
        this.mWJError = wJError;
    }

    private FailureViewModel(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        this.mFailureName = readBundle.getString(KEY_CAUSE);
        this.mFailureStacktrace = readBundle.getString(KEY_STACK_TRACE);
        this.mWJError = (WJError) readBundle.getParcelable(KEY_WJ_ERROR);
    }
}
