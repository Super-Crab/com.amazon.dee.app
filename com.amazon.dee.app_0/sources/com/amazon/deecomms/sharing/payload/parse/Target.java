package com.amazon.deecomms.sharing.payload.parse;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class Target {
    private ActionApplication actionApplication;
    private EntryData entryData;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Target.class != obj.getClass()) {
            return false;
        }
        Target target = (Target) obj;
        return Objects.equals(this.entryData, target.entryData) && Objects.equals(this.actionApplication, target.actionApplication);
    }

    public ActionApplication getActionApplication() {
        return this.actionApplication;
    }

    public EntryData getEntryData() {
        return this.entryData;
    }

    public int hashCode() {
        return Objects.hash(this.entryData, this.actionApplication);
    }

    public void setActionApplication(ActionApplication actionApplication) {
        this.actionApplication = actionApplication;
    }

    public void setEntryData(EntryData entryData) {
        this.entryData = entryData;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Target{entryData=");
        outline1.append(this.entryData);
        outline1.append(", actionApplication=");
        outline1.append(this.actionApplication);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }
}
