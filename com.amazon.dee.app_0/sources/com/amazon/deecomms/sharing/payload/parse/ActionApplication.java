package com.amazon.deecomms.sharing.payload.parse;

import com.amazon.deecomms.sharing.payload.parse.enums.OperatingSystem;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
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
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("ActionApplication{operatingSystem=");
        outline1.append(this.operatingSystem);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }
}
