package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class DescribeThingResult implements Serializable {
    private Map<String, String> attributes;
    private String billingGroupName;
    private String defaultClientId;
    private String thingArn;
    private String thingId;
    private String thingName;
    private String thingTypeName;
    private Long version;

    public DescribeThingResult addattributesEntry(String str, String str2) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        if (!this.attributes.containsKey(str)) {
            this.attributes.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeThingResult clearattributesEntries() {
        this.attributes = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingResult)) {
            return false;
        }
        DescribeThingResult describeThingResult = (DescribeThingResult) obj;
        if ((describeThingResult.getDefaultClientId() == null) ^ (getDefaultClientId() == null)) {
            return false;
        }
        if (describeThingResult.getDefaultClientId() != null && !describeThingResult.getDefaultClientId().equals(getDefaultClientId())) {
            return false;
        }
        if ((describeThingResult.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (describeThingResult.getThingName() != null && !describeThingResult.getThingName().equals(getThingName())) {
            return false;
        }
        if ((describeThingResult.getThingId() == null) ^ (getThingId() == null)) {
            return false;
        }
        if (describeThingResult.getThingId() != null && !describeThingResult.getThingId().equals(getThingId())) {
            return false;
        }
        if ((describeThingResult.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (describeThingResult.getThingArn() != null && !describeThingResult.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((describeThingResult.getThingTypeName() == null) ^ (getThingTypeName() == null)) {
            return false;
        }
        if (describeThingResult.getThingTypeName() != null && !describeThingResult.getThingTypeName().equals(getThingTypeName())) {
            return false;
        }
        if ((describeThingResult.getAttributes() == null) ^ (getAttributes() == null)) {
            return false;
        }
        if (describeThingResult.getAttributes() != null && !describeThingResult.getAttributes().equals(getAttributes())) {
            return false;
        }
        if ((describeThingResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeThingResult.getVersion() != null && !describeThingResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeThingResult.getBillingGroupName() == null) ^ (getBillingGroupName() == null)) {
            return false;
        }
        return describeThingResult.getBillingGroupName() == null || describeThingResult.getBillingGroupName().equals(getBillingGroupName());
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public String getBillingGroupName() {
        return this.billingGroupName;
    }

    public String getDefaultClientId() {
        return this.defaultClientId;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public String getThingId() {
        return this.thingId;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getThingTypeName() {
        return this.thingTypeName;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getDefaultClientId() == null ? 0 : getDefaultClientId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getThingId() == null ? 0 : getThingId().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getThingTypeName() == null ? 0 : getThingTypeName().hashCode())) * 31) + (getAttributes() == null ? 0 : getAttributes().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31;
        if (getBillingGroupName() != null) {
            i = getBillingGroupName().hashCode();
        }
        return hashCode + i;
    }

    public void setAttributes(Map<String, String> map) {
        this.attributes = map;
    }

    public void setBillingGroupName(String str) {
        this.billingGroupName = str;
    }

    public void setDefaultClientId(String str) {
        this.defaultClientId = str;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setThingId(String str) {
        this.thingId = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public void setThingTypeName(String str) {
        this.thingTypeName = str;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getDefaultClientId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("defaultClientId: ");
            outline1072.append(getDefaultClientId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1073.append(getThingName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getThingId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingId: ");
            outline1074.append(getThingId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1075.append(getThingArn());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingTypeName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingTypeName: ");
            outline1076.append(getThingTypeName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getAttributes() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("attributes: ");
            outline1077.append(getAttributes());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("version: ");
            outline1078.append(getVersion());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getBillingGroupName() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("billingGroupName: ");
            outline1079.append(getBillingGroupName());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeThingResult withAttributes(Map<String, String> map) {
        this.attributes = map;
        return this;
    }

    public DescribeThingResult withBillingGroupName(String str) {
        this.billingGroupName = str;
        return this;
    }

    public DescribeThingResult withDefaultClientId(String str) {
        this.defaultClientId = str;
        return this;
    }

    public DescribeThingResult withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public DescribeThingResult withThingId(String str) {
        this.thingId = str;
        return this;
    }

    public DescribeThingResult withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public DescribeThingResult withThingTypeName(String str) {
        this.thingTypeName = str;
        return this;
    }

    public DescribeThingResult withVersion(Long l) {
        this.version = l;
        return this;
    }
}
