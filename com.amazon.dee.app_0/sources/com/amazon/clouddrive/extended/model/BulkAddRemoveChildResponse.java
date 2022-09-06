package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.Map;
/* loaded from: classes11.dex */
public class BulkAddRemoveChildResponse implements BulkResponse {
    private Map<String, Integer> errorMap;
    private String message;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BulkAddRemoveChildResponse) && compareTo((CloudDriveResponse) ((BulkAddRemoveChildResponse) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkResponse
    public Map<String, Integer> getErrorMap() {
        return this.errorMap;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkResponse
    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getMessage() == null ? 0 : getMessage().hashCode()) + 1;
        if (getErrorMap() != null) {
            i = getErrorMap().hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkResponse
    public void setErrorMap(Map<String, Integer> map) {
        this.errorMap = map;
    }

    @Override // com.amazon.clouddrive.extended.model.BulkResponse
    public void setMessage(String str) {
        this.message = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof BulkAddRemoveChildResponse)) {
            return 1;
        }
        BulkAddRemoveChildResponse bulkAddRemoveChildResponse = (BulkAddRemoveChildResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getMessage(), bulkAddRemoveChildResponse.getMessage());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getErrorMap(), bulkAddRemoveChildResponse.getErrorMap());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
