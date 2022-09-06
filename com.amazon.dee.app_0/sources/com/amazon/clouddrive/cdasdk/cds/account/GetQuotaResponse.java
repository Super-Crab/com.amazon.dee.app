package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GetQuotaResponse extends CloudDriveResponse {
    @JsonProperty("available")
    private Long available;
    @JsonProperty("benefits")
    private List<Benefit> benefits;
    @JsonProperty("grants")
    private List<Grant> grants;
    @JsonProperty("lastCalculated")
    private String lastCalculated;
    @JsonProperty("plans")
    private List<String> plans;
    @JsonProperty("quota")
    private Long quota;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof GetQuotaResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetQuotaResponse)) {
            return false;
        }
        GetQuotaResponse getQuotaResponse = (GetQuotaResponse) obj;
        if (!getQuotaResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Long quota = getQuota();
        Long quota2 = getQuotaResponse.getQuota();
        if (quota != null ? !quota.equals(quota2) : quota2 != null) {
            return false;
        }
        Long available = getAvailable();
        Long available2 = getQuotaResponse.getAvailable();
        if (available != null ? !available.equals(available2) : available2 != null) {
            return false;
        }
        List<String> plans = getPlans();
        List<String> plans2 = getQuotaResponse.getPlans();
        if (plans != null ? !plans.equals(plans2) : plans2 != null) {
            return false;
        }
        List<Benefit> benefits = getBenefits();
        List<Benefit> benefits2 = getQuotaResponse.getBenefits();
        if (benefits != null ? !benefits.equals(benefits2) : benefits2 != null) {
            return false;
        }
        List<Grant> grants = getGrants();
        List<Grant> grants2 = getQuotaResponse.getGrants();
        if (grants != null ? !grants.equals(grants2) : grants2 != null) {
            return false;
        }
        String lastCalculated = getLastCalculated();
        String lastCalculated2 = getQuotaResponse.getLastCalculated();
        return lastCalculated != null ? lastCalculated.equals(lastCalculated2) : lastCalculated2 == null;
    }

    public Long getAvailable() {
        return this.available;
    }

    public List<Benefit> getBenefits() {
        return this.benefits;
    }

    public List<Grant> getGrants() {
        return this.grants;
    }

    public String getLastCalculated() {
        return this.lastCalculated;
    }

    public List<String> getPlans() {
        return this.plans;
    }

    public Long getQuota() {
        return this.quota;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        Long quota = getQuota();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (quota == null ? 43 : quota.hashCode());
        Long available = getAvailable();
        int hashCode3 = (hashCode2 * 59) + (available == null ? 43 : available.hashCode());
        List<String> plans = getPlans();
        int hashCode4 = (hashCode3 * 59) + (plans == null ? 43 : plans.hashCode());
        List<Benefit> benefits = getBenefits();
        int hashCode5 = (hashCode4 * 59) + (benefits == null ? 43 : benefits.hashCode());
        List<Grant> grants = getGrants();
        int hashCode6 = (hashCode5 * 59) + (grants == null ? 43 : grants.hashCode());
        String lastCalculated = getLastCalculated();
        int i2 = hashCode6 * 59;
        if (lastCalculated != null) {
            i = lastCalculated.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("available")
    public void setAvailable(Long l) {
        this.available = l;
    }

    @JsonProperty("benefits")
    public void setBenefits(List<Benefit> list) {
        this.benefits = list;
    }

    @JsonProperty("grants")
    public void setGrants(List<Grant> list) {
        this.grants = list;
    }

    @JsonProperty("lastCalculated")
    public void setLastCalculated(String str) {
        this.lastCalculated = str;
    }

    @JsonProperty("plans")
    public void setPlans(List<String> list) {
        this.plans = list;
    }

    @JsonProperty("quota")
    public void setQuota(Long l) {
        this.quota = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetQuotaResponse(quota=");
        outline107.append(getQuota());
        outline107.append(", available=");
        outline107.append(getAvailable());
        outline107.append(", plans=");
        outline107.append(getPlans());
        outline107.append(", benefits=");
        outline107.append(getBenefits());
        outline107.append(", grants=");
        outline107.append(getGrants());
        outline107.append(", lastCalculated=");
        outline107.append(getLastCalculated());
        outline107.append(")");
        return outline107.toString();
    }
}
