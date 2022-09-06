package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class CreateGroupRequest implements CloudDriveRequest {
    private String description;
    private String kind;
    private String lang;
    private String name;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreateGroupRequest) && compareTo((CloudDriveRequest) ((CreateGroupRequest) obj)) == 0;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKind() {
        return this.kind;
    }

    public String getLang() {
        return this.lang;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getName() == null ? 0 : getName().hashCode()) + 1 + (getDescription() == null ? 0 : getDescription().hashCode()) + (getLang() == null ? 0 : getLang().hashCode());
        if (getKind() != null) {
            i = getKind().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public CreateGroupRequest withDescription(String str) {
        setDescription(str);
        return this;
    }

    public CreateGroupRequest withKind(String str) {
        setKind(str);
        return this;
    }

    public CreateGroupRequest withLang(String str) {
        setLang(str);
        return this;
    }

    public CreateGroupRequest withName(String str) {
        setName(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof CreateGroupRequest)) {
            return 1;
        }
        CreateGroupRequest createGroupRequest = (CreateGroupRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getName(), createGroupRequest.getName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getDescription(), createGroupRequest.getDescription());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getLang(), createGroupRequest.getLang());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getKind(), createGroupRequest.getKind());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
