package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import java.util.List;
/* loaded from: classes11.dex */
public class GetServicePlansResponse implements CloudDriveResponse {
    List<ServicePlan> servicePlans;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetServicePlansResponse) && compareTo((CloudDriveResponse) ((GetServicePlansResponse) obj)) == 0;
    }

    public List<ServicePlan> getServicePlansList() {
        return this.servicePlans;
    }

    public int hashCode() {
        List<ServicePlan> list = this.servicePlans;
        return (((list == null ? 0 : list.hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setServicePlansList(List<ServicePlan> list) {
        this.servicePlans = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetServicePlansResponse)) {
            return 1;
        }
        List<ServicePlan> servicePlansList = getServicePlansList();
        List<ServicePlan> servicePlansList2 = ((GetServicePlansResponse) cloudDriveResponse).getServicePlansList();
        if (servicePlansList != servicePlansList2) {
            if (servicePlansList == null) {
                return -1;
            }
            if (servicePlansList2 == null) {
                return 1;
            }
            if (servicePlansList instanceof Comparable) {
                int compareTo = ((Comparable) servicePlansList).compareTo(servicePlansList2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!servicePlansList.equals(servicePlansList2)) {
                int hashCode = servicePlansList.hashCode();
                int hashCode2 = servicePlansList2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
