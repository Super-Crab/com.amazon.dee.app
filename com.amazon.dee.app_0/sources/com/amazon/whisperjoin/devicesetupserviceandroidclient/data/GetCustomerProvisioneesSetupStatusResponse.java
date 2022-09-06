package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class GetCustomerProvisioneesSetupStatusResponse {
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

    public Iterable<ProvisioneeSetupStatus> getProvisioneeSetupStatuses() {
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

    private GetCustomerProvisioneesSetupStatusResponse(Builder builder) {
        this.mProvisioneeSetupStatuses = ImmutableList.copyOf((Collection) builder.mProvisioneeSetupStatuses);
        this.mSearchIntervalUsed = builder.mSearchIntervalUsed;
    }
}
