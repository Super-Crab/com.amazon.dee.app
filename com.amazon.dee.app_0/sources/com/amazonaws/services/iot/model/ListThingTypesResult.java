package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingTypesResult implements Serializable {
    private String nextToken;
    private List<ThingTypeDefinition> thingTypes;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingTypesResult)) {
            return false;
        }
        ListThingTypesResult listThingTypesResult = (ListThingTypesResult) obj;
        if ((listThingTypesResult.getThingTypes() == null) ^ (getThingTypes() == null)) {
            return false;
        }
        if (listThingTypesResult.getThingTypes() != null && !listThingTypesResult.getThingTypes().equals(getThingTypes())) {
            return false;
        }
        if ((listThingTypesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingTypesResult.getNextToken() == null || listThingTypesResult.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public List<ThingTypeDefinition> getThingTypes() {
        return this.thingTypes;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingTypes() == null ? 0 : getThingTypes().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setThingTypes(Collection<ThingTypeDefinition> collection) {
        if (collection == null) {
            this.thingTypes = null;
        } else {
            this.thingTypes = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypes() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypes: ");
            outline1072.append(getThingTypes());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingTypesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingTypesResult withThingTypes(ThingTypeDefinition... thingTypeDefinitionArr) {
        if (getThingTypes() == null) {
            this.thingTypes = new ArrayList(thingTypeDefinitionArr.length);
        }
        for (ThingTypeDefinition thingTypeDefinition : thingTypeDefinitionArr) {
            this.thingTypes.add(thingTypeDefinition);
        }
        return this;
    }

    public ListThingTypesResult withThingTypes(Collection<ThingTypeDefinition> collection) {
        setThingTypes(collection);
        return this;
    }
}
