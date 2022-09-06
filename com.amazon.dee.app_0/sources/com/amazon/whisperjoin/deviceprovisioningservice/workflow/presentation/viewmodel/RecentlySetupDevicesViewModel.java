package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetCustomerProvisioneesSetupStatusResponse;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class RecentlySetupDevicesViewModel implements Parcelable {
    public static final Parcelable.Creator<RecentlySetupDevicesViewModel> CREATOR = new Parcelable.Creator<RecentlySetupDevicesViewModel>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.RecentlySetupDevicesViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RecentlySetupDevicesViewModel mo5672createFromParcel(Parcel parcel) {
            return new RecentlySetupDevicesViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RecentlySetupDevicesViewModel[] mo5673newArray(int i) {
            return new RecentlySetupDevicesViewModel[i];
        }
    };
    private final GetCustomerProvisioneesSetupStatusResponse mGetCustomerProvisioneesSetupStatusResponse;

    /* loaded from: classes13.dex */
    public static class Builder {
        private GetCustomerProvisioneesSetupStatusResponse mGetCustomerProvisioneesSetupStatusResponse;

        public RecentlySetupDevicesViewModel build() {
            Preconditions.checkNotNull(this.mGetCustomerProvisioneesSetupStatusResponse);
            return new RecentlySetupDevicesViewModel(this);
        }

        public Builder setGetCustomerProvisioneesSetupStatusResponse(GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse) {
            this.mGetCustomerProvisioneesSetupStatusResponse = getCustomerProvisioneesSetupStatusResponse;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse() {
        return this.mGetCustomerProvisioneesSetupStatusResponse;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mGetCustomerProvisioneesSetupStatusResponse, i);
    }

    private RecentlySetupDevicesViewModel(Builder builder) {
        this.mGetCustomerProvisioneesSetupStatusResponse = builder.mGetCustomerProvisioneesSetupStatusResponse;
    }

    private RecentlySetupDevicesViewModel(Parcel parcel) {
        this.mGetCustomerProvisioneesSetupStatusResponse = (GetCustomerProvisioneesSetupStatusResponse) parcel.readParcelable(GetCustomerProvisioneesSetupStatusResponse.class.getClassLoader());
        Preconditions.checkState(this.mGetCustomerProvisioneesSetupStatusResponse != null);
    }
}
