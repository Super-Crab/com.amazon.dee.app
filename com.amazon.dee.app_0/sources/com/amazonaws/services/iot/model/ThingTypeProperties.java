package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ThingTypeProperties implements Serializable {
    private List<String> searchableAttributes;
    private String thingTypeDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeProperties)) {
            return false;
        }
        ThingTypeProperties thingTypeProperties = (ThingTypeProperties) obj;
        if ((thingTypeProperties.getThingTypeDescription() == null) ^ (getThingTypeDescription() == null)) {
            return false;
        }
        if (thingTypeProperties.getThingTypeDescription() != null && !thingTypeProperties.getThingTypeDescription().equals(getThingTypeDescription())) {
            return false;
        }
        if ((thingTypeProperties.getSearchableAttributes() == null) ^ (getSearchableAttributes() == null)) {
            return false;
        }
        return thingTypeProperties.getSearchableAttributes() == null || thingTypeProperties.getSearchableAttributes().equals(getSearchableAttributes());
    }

    public List<String> getSearchableAttributes() {
        return this.searchableAttributes;
    }

    public String getThingTypeDescription() {
        return this.thingTypeDescription;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingTypeDescription() == null ? 0 : getThingTypeDescription().hashCode()) + 31) * 31;
        if (getSearchableAttributes() != null) {
            i = getSearchableAttributes().hashCode();
        }
        return hashCode + i;
    }

    public void setSearchableAttributes(Collection<String> collection) {
        if (collection == null) {
            this.searchableAttributes = null;
        } else {
            this.searchableAttributes = new ArrayList(collection);
        }
    }

    public void setThingTypeDescription(String str) {
        this.thingTypeDescription = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeDescription() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeDescription: ");
            outline1072.append(getThingTypeDescription());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSearchableAttributes() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("searchableAttributes: ");
            outline1073.append(getSearchableAttributes());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingTypeProperties withSearchableAttributes(String... strArr) {
        if (getSearchableAttributes() == null) {
            this.searchableAttributes = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.searchableAttributes.add(str);
        }
        return this;
    }

    public ThingTypeProperties withThingTypeDescription(String str) {
        this.thingTypeDescription = str;
        return this;
    }

    public ThingTypeProperties withSearchableAttributes(Collection<String> collection) {
        setSearchableAttributes(collection);
        return this;
    }
}
