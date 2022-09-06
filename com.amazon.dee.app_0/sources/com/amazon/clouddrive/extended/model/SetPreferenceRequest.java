package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class SetPreferenceRequest implements CloudDriveRequest {
    private String preferenceKey;
    private PreferenceType preferenceType;
    private String preferenceValue;

    /* loaded from: classes11.dex */
    public enum PreferenceType {
        FAMILY,
        PERSONAL
    }

    public SetPreferenceRequest(PreferenceType preferenceType, String str, String str2) {
        this.preferenceType = preferenceType;
        this.preferenceKey = str;
        this.preferenceValue = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SetPreferenceRequest) && compareTo((CloudDriveRequest) ((SetPreferenceRequest) obj)) == 0;
    }

    public String getPreferenceKey() {
        return this.preferenceKey;
    }

    public PreferenceType getPreferenceType() {
        return this.preferenceType;
    }

    public String getPreferenceValue() {
        return this.preferenceValue;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPreferenceType() == null ? 0 : getPreferenceType().hashCode()) + 1 + (getPreferenceKey() == null ? 0 : getPreferenceKey().hashCode());
        if (getPreferenceValue() != null) {
            i = getPreferenceValue().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setPreferenceKey(String str) {
        this.preferenceKey = str;
    }

    public void setPreferenceType(PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    public void setPreferenceValue(String str) {
        this.preferenceValue = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SetPreferenceRequest)) {
            return 1;
        }
        SetPreferenceRequest setPreferenceRequest = (SetPreferenceRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getPreferenceType(), setPreferenceRequest.getPreferenceType());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getPreferenceKey(), setPreferenceRequest.getPreferenceKey());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getPreferenceValue(), setPreferenceRequest.getPreferenceValue());
        if (compare3 == 0) {
            return 0;
        }
        return compare3;
    }
}
