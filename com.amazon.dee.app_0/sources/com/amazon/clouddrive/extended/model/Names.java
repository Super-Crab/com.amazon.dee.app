package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class Names implements Comparable<Names> {
    private String firstName;
    private String fullName;

    protected boolean canEqual(Object obj) {
        return obj instanceof Names;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Names)) {
            return false;
        }
        Names names = (Names) obj;
        if (!names.canEqual(this)) {
            return false;
        }
        String fullName = getFullName();
        String fullName2 = names.getFullName();
        if (fullName != null ? !fullName.equals(fullName2) : fullName2 != null) {
            return false;
        }
        String firstName = getFirstName();
        String firstName2 = names.getFirstName();
        return firstName != null ? firstName.equals(firstName2) : firstName2 == null;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int hashCode() {
        String fullName = getFullName();
        int i = 43;
        int hashCode = fullName == null ? 43 : fullName.hashCode();
        String firstName = getFirstName();
        int i2 = (hashCode + 59) * 59;
        if (firstName != null) {
            i = firstName.hashCode();
        }
        return i2 + i;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Names(fullName=");
        outline107.append(getFullName());
        outline107.append(", firstName=");
        outline107.append(getFirstName());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Names names) {
        if (names == null) {
            return -1;
        }
        if (names == this) {
            return 0;
        }
        int compare = ObjectComparator.compare(getFullName(), names.getFullName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getFirstName(), names.getFirstName());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
