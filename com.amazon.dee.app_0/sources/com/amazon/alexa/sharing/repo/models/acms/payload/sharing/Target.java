package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Target{entryData=");
        outline107.append(this.entryData);
        outline107.append(", actionApplication=");
        outline107.append(this.actionApplication);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
