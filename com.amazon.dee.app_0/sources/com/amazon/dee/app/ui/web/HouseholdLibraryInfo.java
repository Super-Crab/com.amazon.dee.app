package com.amazon.dee.app.ui.web;
/* loaded from: classes12.dex */
public class HouseholdLibraryInfo {
    public final String customerId;
    public final String displayName;

    public HouseholdLibraryInfo(String str, String str2) {
        this.customerId = str2;
        this.displayName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HouseholdLibraryInfo.class != obj.getClass()) {
            return false;
        }
        HouseholdLibraryInfo householdLibraryInfo = (HouseholdLibraryInfo) obj;
        return this.displayName.equals(householdLibraryInfo.displayName) && this.customerId.equals(householdLibraryInfo.customerId);
    }

    public int hashCode() {
        return this.customerId.hashCode() + (this.displayName.hashCode() * 31);
    }
}
