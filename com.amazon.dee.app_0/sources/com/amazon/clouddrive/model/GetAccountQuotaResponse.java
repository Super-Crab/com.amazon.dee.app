package com.amazon.clouddrive.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class GetAccountQuotaResponse implements CloudDriveResponse {
    private long available;
    private String lastCalculated;
    private long quota;
    private List<CloudDriveBenefit> benefits = new ArrayList(0);
    private List<CloudDriveGrant> grants = new ArrayList(0);
    private List<String> plans = new ArrayList(0);

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetAccountQuotaResponse) && compareTo((CloudDriveResponse) ((GetAccountQuotaResponse) obj)) == 0;
    }

    public long getAvailable() {
        return this.available;
    }

    public List<CloudDriveBenefit> getBenefits() {
        return this.benefits;
    }

    public List<CloudDriveGrant> getGrants() {
        return this.grants;
    }

    public String getLastCalculated() {
        return this.lastCalculated;
    }

    public List<String> getPlans() {
        return this.plans;
    }

    public long getQuota() {
        return this.quota;
    }

    public int hashCode() {
        int i = 0;
        int quota = ((int) getQuota()) + 1 + (getLastCalculated() == null ? 0 : getLastCalculated().hashCode()) + ((int) getAvailable());
        List<CloudDriveBenefit> list = this.benefits;
        int hashCode = quota + (list == null ? 0 : list.hashCode());
        List<CloudDriveGrant> list2 = this.grants;
        int hashCode2 = hashCode + (list2 == null ? 0 : list2.hashCode());
        List<String> list3 = this.plans;
        if (list3 != null) {
            i = list3.hashCode();
        }
        return hashCode2 + i;
    }

    public void setAvailable(long j) {
        this.available = j;
    }

    public GetAccountQuotaResponse setBenefits(List<CloudDriveBenefit> list) {
        this.benefits = list;
        return this;
    }

    public GetAccountQuotaResponse setGrants(List<CloudDriveGrant> list) {
        this.grants = list;
        return this;
    }

    public void setLastCalculated(String str) {
        this.lastCalculated = str;
    }

    public GetAccountQuotaResponse setPlans(List<String> list) {
        this.plans = list;
        return this;
    }

    public void setQuota(long j) {
        this.quota = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetAccountQuotaResponse)) {
            return 1;
        }
        GetAccountQuotaResponse getAccountQuotaResponse = (GetAccountQuotaResponse) cloudDriveResponse;
        if (getQuota() < getAccountQuotaResponse.getQuota()) {
            return -1;
        }
        if (getQuota() > getAccountQuotaResponse.getQuota()) {
            return 1;
        }
        String lastCalculated = getLastCalculated();
        String lastCalculated2 = getAccountQuotaResponse.getLastCalculated();
        if (lastCalculated != lastCalculated2) {
            if (lastCalculated == null) {
                return -1;
            }
            if (lastCalculated2 == null) {
                return 1;
            }
            int compareTo = lastCalculated.compareTo(lastCalculated2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (getAvailable() < getAccountQuotaResponse.getAvailable()) {
            return -1;
        }
        if (getAvailable() > getAccountQuotaResponse.getAvailable()) {
            return 1;
        }
        List<CloudDriveBenefit> benefits = getBenefits();
        List<CloudDriveBenefit> benefits2 = getAccountQuotaResponse.getBenefits();
        if (benefits != benefits2) {
            if (benefits == null) {
                return -1;
            }
            if (benefits2 == null) {
                return 1;
            }
            if (benefits instanceof Comparable) {
                int compareTo2 = ((Comparable) benefits).compareTo(benefits2);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            } else if (!benefits.equals(benefits2)) {
                int hashCode = benefits.hashCode();
                int hashCode2 = benefits2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        List<CloudDriveGrant> grants = getGrants();
        List<CloudDriveGrant> grants2 = getAccountQuotaResponse.getGrants();
        if (grants != grants2) {
            if (grants == null) {
                return -1;
            }
            if (grants2 == null) {
                return 1;
            }
            if (grants instanceof Comparable) {
                int compareTo3 = ((Comparable) grants).compareTo(grants2);
                if (compareTo3 != 0) {
                    return compareTo3;
                }
            } else if (!grants.equals(grants2)) {
                int hashCode3 = grants.hashCode();
                int hashCode4 = grants2.hashCode();
                if (hashCode3 < hashCode4) {
                    return -1;
                }
                if (hashCode3 > hashCode4) {
                    return 1;
                }
            }
        }
        List<String> plans = getPlans();
        List<String> plans2 = getAccountQuotaResponse.getPlans();
        if (plans != plans2) {
            if (plans == null) {
                return -1;
            }
            if (plans2 == null) {
                return 1;
            }
            if (plans instanceof Comparable) {
                int compareTo4 = ((Comparable) plans).compareTo(plans2);
                if (compareTo4 != 0) {
                    return compareTo4;
                }
            } else if (!plans.equals(plans2)) {
                int hashCode5 = plans.hashCode();
                int hashCode6 = plans2.hashCode();
                if (hashCode5 < hashCode6) {
                    return -1;
                }
                if (hashCode5 > hashCode6) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
