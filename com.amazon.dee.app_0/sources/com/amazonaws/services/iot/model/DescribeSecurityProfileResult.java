package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class DescribeSecurityProfileResult implements Serializable {
    private Map<String, AlertTarget> alertTargets;
    private List<Behavior> behaviors;
    private Date creationDate;
    private Date lastModifiedDate;
    private String securityProfileArn;
    private String securityProfileDescription;
    private String securityProfileName;
    private Long version;

    public DescribeSecurityProfileResult addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (!this.alertTargets.containsKey(str)) {
            this.alertTargets.put(str, alertTarget);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public DescribeSecurityProfileResult clearalertTargetsEntries() {
        this.alertTargets = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeSecurityProfileResult)) {
            return false;
        }
        DescribeSecurityProfileResult describeSecurityProfileResult = (DescribeSecurityProfileResult) obj;
        if ((describeSecurityProfileResult.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileName() != null && !describeSecurityProfileResult.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((describeSecurityProfileResult.getSecurityProfileArn() == null) ^ (getSecurityProfileArn() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileArn() != null && !describeSecurityProfileResult.getSecurityProfileArn().equals(getSecurityProfileArn())) {
            return false;
        }
        if ((describeSecurityProfileResult.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileDescription() != null && !describeSecurityProfileResult.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((describeSecurityProfileResult.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getBehaviors() != null && !describeSecurityProfileResult.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((describeSecurityProfileResult.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getAlertTargets() != null && !describeSecurityProfileResult.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((describeSecurityProfileResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getVersion() != null && !describeSecurityProfileResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeSecurityProfileResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getCreationDate() != null && !describeSecurityProfileResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeSecurityProfileResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeSecurityProfileResult.getLastModifiedDate() == null || describeSecurityProfileResult.getLastModifiedDate().equals(getLastModifiedDate());
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public List<Behavior> getBehaviors() {
        return this.behaviors;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getSecurityProfileArn() {
        return this.securityProfileArn;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public Long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileArn() == null ? 0 : getSecurityProfileArn().hashCode())) * 31) + (getSecurityProfileDescription() == null ? 0 : getSecurityProfileDescription().hashCode())) * 31) + (getBehaviors() == null ? 0 : getBehaviors().hashCode())) * 31) + (getAlertTargets() == null ? 0 : getAlertTargets().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31;
        if (getLastModifiedDate() != null) {
            i = getLastModifiedDate().hashCode();
        }
        return hashCode + i;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public void setBehaviors(Collection<Behavior> collection) {
        if (collection == null) {
            this.behaviors = null;
        } else {
            this.behaviors = new ArrayList(collection);
        }
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setSecurityProfileArn(String str) {
        this.securityProfileArn = str;
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSecurityProfileName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1072.append(getSecurityProfileName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSecurityProfileArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("securityProfileArn: ");
            outline1073.append(getSecurityProfileArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSecurityProfileDescription() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("securityProfileDescription: ");
            outline1074.append(getSecurityProfileDescription());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getBehaviors() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("behaviors: ");
            outline1075.append(getBehaviors());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAlertTargets() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("alertTargets: ");
            outline1076.append(getAlertTargets());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getVersion() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("version: ");
            outline1077.append(getVersion());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1078.append(getCreationDate());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1079.append(getLastModifiedDate());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeSecurityProfileResult withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public DescribeSecurityProfileResult withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public DescribeSecurityProfileResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public DescribeSecurityProfileResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public DescribeSecurityProfileResult withSecurityProfileArn(String str) {
        this.securityProfileArn = str;
        return this;
    }

    public DescribeSecurityProfileResult withSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
        return this;
    }

    public DescribeSecurityProfileResult withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public DescribeSecurityProfileResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public DescribeSecurityProfileResult withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }
}
