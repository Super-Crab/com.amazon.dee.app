package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeThingTypeResult implements Serializable {
    private String thingTypeArn;
    private String thingTypeId;
    private ThingTypeMetadata thingTypeMetadata;
    private String thingTypeName;
    private ThingTypeProperties thingTypeProperties;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingTypeResult)) {
            return false;
        }
        DescribeThingTypeResult describeThingTypeResult = (DescribeThingTypeResult) obj;
        if ((describeThingTypeResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeName() != null && !describeThingTypeResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeId() == null) ^ (getThingTypeId() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeId() != null && !describeThingTypeResult.getThingTypeId().equals(getThingTypeId())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeArn() == null) ^ (getThingTypeArn() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeArn() != null && !describeThingTypeResult.getThingTypeArn().equals(getThingTypeArn())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeProperties() == null) ^ (getThingTypeProperties() == null)) {
            return false;
        }
        if (describeThingTypeResult.getThingTypeProperties() != null && !describeThingTypeResult.getThingTypeProperties().equals(getThingTypeProperties())) {
            return false;
        }
        if ((describeThingTypeResult.getThingTypeMetadata() == null) ^ (getThingTypeMetadata() == null)) {
            return false;
        }
        return describeThingTypeResult.getThingTypeMetadata() == null || describeThingTypeResult.getThingTypeMetadata().equals(getThingTypeMetadata());
    }

    public String getThingTypeArn() {
        return this.thingTypeArn;
    }

    public String getThingTypeId() {
        return this.thingTypeId;
    }

    public ThingTypeMetadata getThingTypeMetadata() {
        return this.thingTypeMetadata;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public ThingTypeProperties getThingTypeProperties() {
        return this.thingTypeProperties;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getThingTypeName() == null ? 0 : getThingTypeName().hashCode()) + 31) * 31) + (getThingTypeId() == null ? 0 : getThingTypeId().hashCode())) * 31) + (getThingTypeArn() == null ? 0 : getThingTypeArn().hashCode())) * 31) + (getThingTypeProperties() == null ? 0 : getThingTypeProperties().hashCode())) * 31;
        if (getThingTypeMetadata() != null) {
            i = getThingTypeMetadata().hashCode();
        }
        return hashCode + i;
    }

    public void setThingTypeArn(String str) {
        this.thingTypeArn = str;
    }

    public void setThingTypeId(String str) {
        this.thingTypeId = str;
    }

    public void setThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingTypeName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1072.append(getThingTypeName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingTypeId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingTypeId: ");
            outline1073.append(getThingTypeId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingTypeArn() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingTypeArn: ");
            outline1074.append(getThingTypeArn());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingTypeProperties() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingTypeProperties: ");
            outline1075.append(getThingTypeProperties());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingTypeMetadata() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingTypeMetadata: ");
            outline1076.append(getThingTypeMetadata());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingTypeResult withThingTypeArn(String str) {
        this.thingTypeArn = str;
        return this;
    }

    public DescribeThingTypeResult withThingTypeId(String str) {
        this.thingTypeId = str;
        return this;
    }

    public DescribeThingTypeResult withThingTypeMetadata(ThingTypeMetadata thingTypeMetadata) {
        this.thingTypeMetadata = thingTypeMetadata;
        return this;
    }

    public DescribeThingTypeResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public DescribeThingTypeResult withThingTypeProperties(ThingTypeProperties thingTypeProperties) {
        this.thingTypeProperties = thingTypeProperties;
        return this;
    }
}
