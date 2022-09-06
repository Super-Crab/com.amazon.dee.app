package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class GetCustomerProvisioneesSetupStatusResponse implements Parcelable {
    public static final Parcelable.Creator<GetCustomerProvisioneesSetupStatusResponse> CREATOR = new Parcelable.Creator<GetCustomerProvisioneesSetupStatusResponse>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.GetCustomerProvisioneesSetupStatusResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public GetCustomerProvisioneesSetupStatusResponse mo5690createFromParcel(Parcel parcel) {
            return new GetCustomerProvisioneesSetupStatusResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public GetCustomerProvisioneesSetupStatusResponse[] mo5691newArray(int i) {
            return new GetCustomerProvisioneesSetupStatusResponse[i];
        }
    };
    private final ImmutableList<ProvisioneeSetupStatus> mProvisioneeSetupStatuses;
    private final String mSearchIntervalUsed;

    /* loaded from: classes13.dex */
    public static class Builder {
        private final List<ProvisioneeSetupStatus> mProvisioneeSetupStatuses = new ArrayList();
        private String mSearchIntervalUsed;

        public Builder addProvisioneeSetupStatus(ProvisioneeSetupStatus provisioneeSetupStatus) {
            this.mProvisioneeSetupStatuses.add(provisioneeSetupStatus);
            return this;
        }

        public GetCustomerProvisioneesSetupStatusResponse createResponse() {
            return new GetCustomerProvisioneesSetupStatusResponse(this);
        }

        public Builder setSearchIntervalUsed(String str) {
            this.mSearchIntervalUsed = str;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetCustomerProvisioneesSetupStatusResponse.class != obj.getClass()) {
            return false;
        }
        GetCustomerProvisioneesSetupStatusResponse getCustomerProvisioneesSetupStatusResponse = (GetCustomerProvisioneesSetupStatusResponse) obj;
        return Objects.equal(this.mProvisioneeSetupStatuses, getCustomerProvisioneesSetupStatusResponse.mProvisioneeSetupStatuses) && Objects.equal(this.mSearchIntervalUsed, getCustomerProvisioneesSetupStatusResponse.mSearchIntervalUsed);
    }

    public Collection<ProvisioneeSetupStatus> getProvisioneeSetupStatuses() {
        return this.mProvisioneeSetupStatuses;
    }

    public String getSearchIntervalUsed() {
        return this.mSearchIntervalUsed;
    }

    public int hashCode() {
        return Objects.hashCode(this.mProvisioneeSetupStatuses, this.mSearchIntervalUsed);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mProvisioneeSetupStatuses", this.mProvisioneeSetupStatuses).add("mSearchIntervalUsed", this.mSearchIntervalUsed).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mProvisioneeSetupStatuses);
        parcel.writeString(this.mSearchIntervalUsed);
    }

    private GetCustomerProvisioneesSetupStatusResponse(Builder builder) {
        this.mProvisioneeSetupStatuses = ImmutableList.copyOf((Collection) builder.mProvisioneeSetupStatuses);
        this.mSearchIntervalUsed = builder.mSearchIntervalUsed;
    }

    private GetCustomerProvisioneesSetupStatusResponse(Parcel parcel) {
        this.mProvisioneeSetupStatuses = ImmutableList.copyOf((Collection) parcel.readArrayList(ProvisioneeSetupStatus.class.getClassLoader()));
        this.mSearchIntervalUsed = parcel.readString();
    }
}
