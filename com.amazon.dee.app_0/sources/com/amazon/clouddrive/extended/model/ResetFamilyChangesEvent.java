package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class ResetFamilyChangesEvent implements FamilyChangesEvent {
    private String mFamilyId;

    @Override // com.amazon.clouddrive.extended.model.FamilyChangesEvent
    public String getEvent() {
        return FamilyChangesEventType.RESET;
    }

    public String getFamilyId() {
        return this.mFamilyId;
    }

    public int hashCode() {
        return (((getFamilyId() == null ? 0 : getFamilyId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setFamilyId(String str) {
        this.mFamilyId = str;
    }

    public ResetFamilyChangesEvent withFamilyId(String str) {
        setFamilyId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(FamilyChangesEvent familyChangesEvent) {
        if (!(familyChangesEvent instanceof ResetFamilyChangesEvent)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getFamilyId(), ((ResetFamilyChangesEvent) familyChangesEvent).getFamilyId());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
