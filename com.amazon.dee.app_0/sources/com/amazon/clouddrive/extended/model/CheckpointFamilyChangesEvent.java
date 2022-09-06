package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class CheckpointFamilyChangesEvent implements FamilyChangesEvent {
    private String mCheckpoint;

    public String getCheckpoint() {
        return this.mCheckpoint;
    }

    @Override // com.amazon.clouddrive.extended.model.FamilyChangesEvent
    public String getEvent() {
        return FamilyChangesEventType.CHECKPOINT;
    }

    public int hashCode() {
        return (((getCheckpoint() == null ? 0 : getCheckpoint().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setCheckpoint(String str) {
        this.mCheckpoint = str;
    }

    public CheckpointFamilyChangesEvent withCheckpoint(String str) {
        setCheckpoint(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(FamilyChangesEvent familyChangesEvent) {
        if (!(familyChangesEvent instanceof CheckpointFamilyChangesEvent)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getCheckpoint(), ((CheckpointFamilyChangesEvent) familyChangesEvent).getCheckpoint());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
