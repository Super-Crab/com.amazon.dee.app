package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ThingIndexingConfiguration implements Serializable {
    private String thingConnectivityIndexingMode;
    private String thingIndexingMode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingIndexingConfiguration)) {
            return false;
        }
        ThingIndexingConfiguration thingIndexingConfiguration = (ThingIndexingConfiguration) obj;
        if ((thingIndexingConfiguration.getThingIndexingMode() == null) ^ (getThingIndexingMode() == null)) {
            return false;
        }
        if (thingIndexingConfiguration.getThingIndexingMode() != null && !thingIndexingConfiguration.getThingIndexingMode().equals(getThingIndexingMode())) {
            return false;
        }
        if ((thingIndexingConfiguration.getThingConnectivityIndexingMode() == null) ^ (getThingConnectivityIndexingMode() == null)) {
            return false;
        }
        return thingIndexingConfiguration.getThingConnectivityIndexingMode() == null || thingIndexingConfiguration.getThingConnectivityIndexingMode().equals(getThingConnectivityIndexingMode());
    }

    public String getThingConnectivityIndexingMode() {
        return this.thingConnectivityIndexingMode;
    }

    public String getThingIndexingMode() {
        return this.thingIndexingMode;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingIndexingMode() == null ? 0 : getThingIndexingMode().hashCode()) + 31) * 31;
        if (getThingConnectivityIndexingMode() != null) {
            i = getThingConnectivityIndexingMode().hashCode();
        }
        return hashCode + i;
    }

    public void setThingConnectivityIndexingMode(String str) {
        this.thingConnectivityIndexingMode = str;
    }

    public void setThingIndexingMode(String str) {
        this.thingIndexingMode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingIndexingMode() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingIndexingMode: ");
            outline1072.append(getThingIndexingMode());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingConnectivityIndexingMode() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingConnectivityIndexingMode: ");
            outline1073.append(getThingConnectivityIndexingMode());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingIndexingConfiguration withThingConnectivityIndexingMode(String str) {
        this.thingConnectivityIndexingMode = str;
        return this;
    }

    public ThingIndexingConfiguration withThingIndexingMode(String str) {
        this.thingIndexingMode = str;
        return this;
    }

    public void setThingConnectivityIndexingMode(ThingConnectivityIndexingMode thingConnectivityIndexingMode) {
        this.thingConnectivityIndexingMode = thingConnectivityIndexingMode.toString();
    }

    public void setThingIndexingMode(ThingIndexingMode thingIndexingMode) {
        this.thingIndexingMode = thingIndexingMode.toString();
    }

    public ThingIndexingConfiguration withThingConnectivityIndexingMode(ThingConnectivityIndexingMode thingConnectivityIndexingMode) {
        this.thingConnectivityIndexingMode = thingConnectivityIndexingMode.toString();
        return this;
    }

    public ThingIndexingConfiguration withThingIndexingMode(ThingIndexingMode thingIndexingMode) {
        this.thingIndexingMode = thingIndexingMode.toString();
        return this;
    }
}
