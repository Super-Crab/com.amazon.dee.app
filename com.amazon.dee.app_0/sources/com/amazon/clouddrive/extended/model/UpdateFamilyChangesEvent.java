package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class UpdateFamilyChangesEvent implements FamilyChangesEvent {
    private CustomerInfo mMember;

    @Override // com.amazon.clouddrive.extended.model.FamilyChangesEvent
    public String getEvent() {
        return FamilyChangesEventType.UPDATE_FAMILY;
    }

    public CustomerInfo getMember() {
        return this.mMember;
    }

    public int hashCode() {
        return (((getMember() == null ? 0 : getMember().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setMember(CustomerInfo customerInfo) {
        this.mMember = customerInfo;
    }

    public UpdateFamilyChangesEvent withMember(CustomerInfo customerInfo) {
        setMember(customerInfo);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(FamilyChangesEvent familyChangesEvent) {
        if (!(familyChangesEvent instanceof UpdateFamilyChangesEvent)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getMember(), ((UpdateFamilyChangesEvent) familyChangesEvent).getMember());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
