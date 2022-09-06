package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ThingGroupIndexingConfiguration implements Serializable {
    private String thingGroupIndexingMode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingGroupIndexingConfiguration)) {
            return false;
        }
        ThingGroupIndexingConfiguration thingGroupIndexingConfiguration = (ThingGroupIndexingConfiguration) obj;
        if ((thingGroupIndexingConfiguration.getThingGroupIndexingMode() == null) ^ (getThingGroupIndexingMode() == null)) {
            return false;
        }
        return thingGroupIndexingConfiguration.getThingGroupIndexingMode() == null || thingGroupIndexingConfiguration.getThingGroupIndexingMode().equals(getThingGroupIndexingMode());
    }

    public String getThingGroupIndexingMode() {
        return this.thingGroupIndexingMode;
    }

    public int hashCode() {
        return 31 + (getThingGroupIndexingMode() == null ? 0 : getThingGroupIndexingMode().hashCode());
    }

    public void setThingGroupIndexingMode(String str) {
        this.thingGroupIndexingMode = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingGroupIndexingMode() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingGroupIndexingMode: ");
            outline1072.append(getThingGroupIndexingMode());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingGroupIndexingConfiguration withThingGroupIndexingMode(String str) {
        this.thingGroupIndexingMode = str;
        return this;
    }

    public void setThingGroupIndexingMode(ThingGroupIndexingMode thingGroupIndexingMode) {
        this.thingGroupIndexingMode = thingGroupIndexingMode.toString();
    }

    public ThingGroupIndexingConfiguration withThingGroupIndexingMode(ThingGroupIndexingMode thingGroupIndexingMode) {
        this.thingGroupIndexingMode = thingGroupIndexingMode.toString();
        return this;
    }
}
