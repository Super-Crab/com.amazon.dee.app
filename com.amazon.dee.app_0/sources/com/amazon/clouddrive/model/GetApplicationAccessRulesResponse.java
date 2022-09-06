package com.amazon.clouddrive.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class GetApplicationAccessRulesResponse implements CloudDriveResponse {
    private List<AccessRule> accessRules;
    private int statusCode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetApplicationAccessRulesResponse.class != obj.getClass()) {
            return false;
        }
        GetApplicationAccessRulesResponse getApplicationAccessRulesResponse = (GetApplicationAccessRulesResponse) obj;
        if (getStatusCode() == getApplicationAccessRulesResponse.getStatusCode()) {
            if (getAccessRules() != null) {
                if (getAccessRules().equals(getApplicationAccessRulesResponse.getAccessRules())) {
                    return true;
                }
            } else if (getApplicationAccessRulesResponse.getAccessRules() == null) {
                return true;
            }
        }
        return false;
    }

    public List<AccessRule> getAccessRules() {
        return this.accessRules;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return getStatusCode() + ((getAccessRules() != null ? getAccessRules().hashCode() : 0) * 31);
    }

    public void setAccessRules(List<AccessRule> list) {
        this.accessRules = list;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetApplicationAccessRulesResponse{accessRules=");
        outline107.append(this.accessRules);
        outline107.append(", statusCode=");
        return GeneratedOutlineSupport1.outline85(outline107, this.statusCode, JsonReaderKt.END_OBJ);
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (!(cloudDriveResponse instanceof GetApplicationAccessRulesResponse)) {
            return 1;
        }
        List<AccessRule> accessRules = getAccessRules();
        GetApplicationAccessRulesResponse getApplicationAccessRulesResponse = (GetApplicationAccessRulesResponse) cloudDriveResponse;
        List<AccessRule> accessRules2 = getApplicationAccessRulesResponse.getAccessRules();
        if (accessRules != accessRules2) {
            if (accessRules == null) {
                return -1;
            }
            if (accessRules2 == null) {
                return 1;
            }
            if (accessRules instanceof Comparable) {
                int compareTo = ((Comparable) accessRules).compareTo(accessRules2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!accessRules.equals(accessRules2)) {
                int hashCode = accessRules.hashCode();
                int hashCode2 = accessRules2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        int statusCode = getStatusCode();
        int statusCode2 = getApplicationAccessRulesResponse.getStatusCode();
        if (statusCode < statusCode2) {
            return -1;
        }
        return statusCode > statusCode2 ? 1 : 0;
    }
}
