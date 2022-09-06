package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DeprecateThingTypeRequest extends AmazonWebServiceRequest implements Serializable {
    private String thingTypeName;
    private Boolean undoDeprecate;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeprecateThingTypeRequest)) {
            return false;
        }
        DeprecateThingTypeRequest deprecateThingTypeRequest = (DeprecateThingTypeRequest) obj;
        if ((deprecateThingTypeRequest.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (deprecateThingTypeRequest.getThingTypeName() != null && !deprecateThingTypeRequest.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((deprecateThingTypeRequest.getUndoDeprecate() == null) ^ (getUndoDeprecate() == null)) {
            return false;
        }
        return deprecateThingTypeRequest.getUndoDeprecate() == null || deprecateThingTypeRequest.getUndoDeprecate().equals(getUndoDeprecate());
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public Boolean getUndoDeprecate() {
        return this.undoDeprecate;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31;
        if (getUndoDeprecate() != null) {
            i = getUndoDeprecate().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isUndoDeprecate() {
        return this.undoDeprecate;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setUndoDeprecate(Boolean bool) {
        this.undoDeprecate = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1072.append(getThingTypeName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getUndoDeprecate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("undoDeprecate: ");
            outline1073.append(getUndoDeprecate());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeprecateThingTypeRequest withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public DeprecateThingTypeRequest withUndoDeprecate(Boolean bool) {
        this.undoDeprecate = bool;
        return this;
    }
}
