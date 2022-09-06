package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import java.util.List;
/* loaded from: classes11.dex */
public class GetSubscriptionsRequest implements CloudDriveRequest {
    private List<String> include;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSubscriptionsRequest) && compareTo((CloudDriveRequest) ((GetSubscriptionsRequest) obj)) == 0;
    }

    public List<String> getInclude() {
        return this.include;
    }

    public int hashCode() {
        return (((getInclude() == null ? 0 : getInclude().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setInclude(List<String> list) {
        this.include = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetSubscriptionsRequest)) {
            return 1;
        }
        List<String> include = getInclude();
        List<String> include2 = ((GetSubscriptionsRequest) cloudDriveRequest).getInclude();
        if (include != include2) {
            if (include == null) {
                return -1;
            }
            if (include2 == null) {
                return 1;
            }
            if (include instanceof Comparable) {
                int compareTo = ((Comparable) include).compareTo(include2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!include.equals(include2)) {
                int hashCode = include.hashCode();
                int hashCode2 = include2.hashCode();
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
