package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class GetIndexingConfigurationResult implements Serializable {
    private ThingGroupIndexingConfiguration thingGroupIndexingConfiguration;
    private ThingIndexingConfiguration thingIndexingConfiguration;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIndexingConfigurationResult)) {
            return false;
        }
        GetIndexingConfigurationResult getIndexingConfigurationResult = (GetIndexingConfigurationResult) obj;
        if ((getIndexingConfigurationResult.getThingIndexingConfiguration() == null) ^ (getThingIndexingConfiguration() == null)) {
            return false;
        }
        if (getIndexingConfigurationResult.getThingIndexingConfiguration() != null && !getIndexingConfigurationResult.getThingIndexingConfiguration().equals(getThingIndexingConfiguration())) {
            return false;
        }
        if ((getIndexingConfigurationResult.getThingGroupIndexingConfiguration() == null) ^ (getThingGroupIndexingConfiguration() == null)) {
            return false;
        }
        return getIndexingConfigurationResult.getThingGroupIndexingConfiguration() == null || getIndexingConfigurationResult.getThingGroupIndexingConfiguration().equals(getThingGroupIndexingConfiguration());
    }

    public ThingGroupIndexingConfiguration getThingGroupIndexingConfiguration() {
        return this.thingGroupIndexingConfiguration;
    }

    public ThingIndexingConfiguration getThingIndexingConfiguration() {
        return this.thingIndexingConfiguration;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingIndexingConfiguration() == null ? 0 : getThingIndexingConfiguration().hashCode()) + 31) * 31;
        if (getThingGroupIndexingConfiguration() != null) {
            i = getThingGroupIndexingConfiguration().hashCode();
        }
        return hashCode + i;
    }

    public void setThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
        this.thingGroupIndexingConfiguration = thingGroupIndexingConfiguration;
    }

    public void setThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingIndexingConfiguration() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingIndexingConfiguration: ");
            outline1072.append(getThingIndexingConfiguration());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingGroupIndexingConfiguration() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingGroupIndexingConfiguration: ");
            outline1073.append(getThingGroupIndexingConfiguration());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetIndexingConfigurationResult withThingGroupIndexingConfiguration(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration) {
        this.thingGroupIndexingConfiguration = thingGroupIndexingConfiguration;
        return this;
    }

    public GetIndexingConfigurationResult withThingIndexingConfiguration(ThingIndexingConfiguration thingIndexingConfiguration) {
        this.thingIndexingConfiguration = thingIndexingConfiguration;
        return this;
    }
}
