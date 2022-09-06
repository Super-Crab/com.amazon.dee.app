package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;

import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.enums.OperatingSystem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes10.dex */
public class ActionApplication {
    private OperatingSystem operatingSystem;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ActionApplication.class == obj.getClass() && this.operatingSystem == ((ActionApplication) obj).operatingSystem;
    }

    public OperatingSystem getOperatingSystem() {
        return this.operatingSystem;
    }

    public int hashCode() {
        return Objects.hash(this.operatingSystem);
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ActionApplication{operatingSystem=");
        outline107.append(this.operatingSystem);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
