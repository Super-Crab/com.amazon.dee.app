package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class SystemTrayInfo implements Serializable {
    private String callDescription;

    /* loaded from: classes11.dex */
    public static class SystemTrayInfoBuilder {
        private String callDescription;

        SystemTrayInfoBuilder() {
        }

        public SystemTrayInfo build() {
            return new SystemTrayInfo(this.callDescription);
        }

        public SystemTrayInfoBuilder callDescription(String str) {
            this.callDescription = str;
            return this;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("SystemTrayInfo.SystemTrayInfoBuilder(callDescription="), this.callDescription, ")");
        }
    }

    SystemTrayInfo(String str) {
        this.callDescription = str;
    }

    public static SystemTrayInfoBuilder builder() {
        return new SystemTrayInfoBuilder();
    }

    public String getCallDescription() {
        return this.callDescription;
    }
}
