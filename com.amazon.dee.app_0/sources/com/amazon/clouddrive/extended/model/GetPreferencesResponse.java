package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetPreferencesResponse implements CloudDriveResponse {
    private boolean autoAddContentToFamilyArchive;
    private String avatarNodeId;
    private String familyName;
    private String name;
    private boolean recognitionEnabled;
    private boolean recognitionEnabledFamily;
    private boolean recognitionEnabledPersonal;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetPreferencesResponse) && compareTo((CloudDriveResponse) ((GetPreferencesResponse) obj)) == 0;
    }

    public boolean getAutoAddContentToFamilyArchive() {
        return this.autoAddContentToFamilyArchive;
    }

    public String getAvatarNodeId() {
        return this.avatarNodeId;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getName() {
        return this.name;
    }

    public boolean getRecognitionEnabled() {
        return this.recognitionEnabled;
    }

    public boolean getRecognitionEnabledFamily() {
        return this.recognitionEnabledFamily;
    }

    public boolean getRecognitionEnabledPersonal() {
        return this.recognitionEnabledPersonal;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFamilyName() == null ? 0 : getFamilyName().hashCode()) + 1 + (getName() == null ? 0 : getName().hashCode());
        if (getAvatarNodeId() != null) {
            i = getAvatarNodeId().hashCode();
        }
        return ((hashCode + i + (getAutoAddContentToFamilyArchive() ? 1 : 0) + (getRecognitionEnabledFamily() ? 1 : 0) + (getRecognitionEnabled() ? 1 : 0) + (getRecognitionEnabledPersonal() ? 1 : 0)) * 31) + super.hashCode();
    }

    public void setAutoAddContentToFamilyArchive(boolean z) {
        this.autoAddContentToFamilyArchive = z;
    }

    public void setAvatarNodeId(String str) {
        this.avatarNodeId = str;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRecognitionEnabled(boolean z) {
        this.recognitionEnabled = z;
    }

    public void setRecognitionEnabledFamily(boolean z) {
        this.recognitionEnabledFamily = z;
    }

    public void setRecognitionEnabledPersonal(boolean z) {
        this.recognitionEnabledPersonal = z;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetPreferencesResponse)) {
            return 1;
        }
        GetPreferencesResponse getPreferencesResponse = (GetPreferencesResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getFamilyName(), getPreferencesResponse.getFamilyName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getName(), getPreferencesResponse.getName());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getAvatarNodeId(), getPreferencesResponse.getAvatarNodeId());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(Boolean.valueOf(getAutoAddContentToFamilyArchive()), Boolean.valueOf(getPreferencesResponse.getAutoAddContentToFamilyArchive()));
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(Boolean.valueOf(getRecognitionEnabledFamily()), Boolean.valueOf(getPreferencesResponse.getRecognitionEnabledFamily()));
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(Boolean.valueOf(getRecognitionEnabled()), Boolean.valueOf(getPreferencesResponse.getRecognitionEnabled()));
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(Boolean.valueOf(getRecognitionEnabledPersonal()), Boolean.valueOf(getPreferencesResponse.getRecognitionEnabledPersonal()));
        if (compare7 == 0) {
            return 0;
        }
        return compare7;
    }
}
