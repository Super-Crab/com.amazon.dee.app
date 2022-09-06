package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ThingTypeMetadata implements Serializable {
    private Date creationDate;
    private Boolean deprecated;
    private Date deprecationDate;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThingTypeMetadata)) {
            return false;
        }
        ThingTypeMetadata thingTypeMetadata = (ThingTypeMetadata) obj;
        if ((thingTypeMetadata.getDeprecated() == null) ^ (getDeprecated() == null)) {
            return false;
        }
        if (thingTypeMetadata.getDeprecated() != null && !thingTypeMetadata.getDeprecated().equals(getDeprecated())) {
            return false;
        }
        if ((thingTypeMetadata.getDeprecationDate() == null) ^ (getDeprecationDate() == null)) {
            return false;
        }
        if (thingTypeMetadata.getDeprecationDate() != null && !thingTypeMetadata.getDeprecationDate().equals(getDeprecationDate())) {
            return false;
        }
        if ((thingTypeMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return thingTypeMetadata.getCreationDate() == null || thingTypeMetadata.getCreationDate().equals(getCreationDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Boolean getDeprecated() {
        return this.deprecated;
    }

    public Date getDeprecationDate() {
        return this.deprecationDate;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getDeprecated() == null ? 0 : getDeprecated().hashCode()) + 31) * 31) + (getDeprecationDate() == null ? 0 : getDeprecationDate().hashCode())) * 31;
        if (getCreationDate() != null) {
            i = getCreationDate().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isDeprecated() {
        return this.deprecated;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setDeprecated(Boolean bool) {
        this.deprecated = bool;
    }

    public void setDeprecationDate(Date date) {
        this.deprecationDate = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDeprecated() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("deprecated: ");
            outline1072.append(getDeprecated());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDeprecationDate() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("deprecationDate: ");
            outline1073.append(getDeprecationDate());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1074.append(getCreationDate());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ThingTypeMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public ThingTypeMetadata withDeprecated(Boolean bool) {
        this.deprecated = bool;
        return this;
    }

    public ThingTypeMetadata withDeprecationDate(Date date) {
        this.deprecationDate = date;
        return this;
    }
}
