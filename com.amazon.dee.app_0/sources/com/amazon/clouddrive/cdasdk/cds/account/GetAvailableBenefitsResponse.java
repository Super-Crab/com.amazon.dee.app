package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GetAvailableBenefitsResponse extends CloudDriveResponse {
    @JsonProperty("benefits")
    private List<AvailableBenefit> benefits;
    @JsonProperty("count")
    private Long count;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetAvailableBenefitsResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetAvailableBenefitsResponse)) {
            return false;
        }
        GetAvailableBenefitsResponse getAvailableBenefitsResponse = (GetAvailableBenefitsResponse) obj;
        if (!getAvailableBenefitsResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<AvailableBenefit> benefits = getBenefits();
        List<AvailableBenefit> benefits2 = getAvailableBenefitsResponse.getBenefits();
        if (benefits != null ? !benefits.equals(benefits2) : benefits2 != null) {
            return false;
        }
        Long count = getCount();
        Long count2 = getAvailableBenefitsResponse.getCount();
        return count != null ? count.equals(count2) : count2 == null;
    }

    public List<AvailableBenefit> getBenefits() {
        return this.benefits;
    }

    public Long getCount() {
        return this.count;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<AvailableBenefit> benefits = getBenefits();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (benefits == null ? 43 : benefits.hashCode());
        Long count = getCount();
        int i2 = hashCode2 * 59;
        if (count != null) {
            i = count.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("benefits")
    public void setBenefits(List<AvailableBenefit> list) {
        this.benefits = list;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetAvailableBenefitsResponse(benefits=");
        outline107.append(getBenefits());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(")");
        return outline107.toString();
    }
}
