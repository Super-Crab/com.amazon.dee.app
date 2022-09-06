package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ActiveViolation implements Serializable {
    private Behavior behavior;
    private Date lastViolationTime;
    private MetricValue lastViolationValue;
    private String securityProfileName;
    private String thingName;
    private String violationId;
    private Date violationStartTime;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ActiveViolation)) {
            return false;
        }
        ActiveViolation activeViolation = (ActiveViolation) obj;
        if ((activeViolation.getViolationId() == null) ^ (getViolationId() == null)) {
            return false;
        }
        if (activeViolation.getViolationId() != null && !activeViolation.getViolationId().equals(getViolationId())) {
            return false;
        }
        if ((activeViolation.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (activeViolation.getThingName() != null && !activeViolation.getThingName().equals(getThingName())) {
            return false;
        }
        if ((activeViolation.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (activeViolation.getSecurityProfileName() != null && !activeViolation.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((activeViolation.getBehavior() == null) ^ (getBehavior() == null)) {
            return false;
        }
        if (activeViolation.getBehavior() != null && !activeViolation.getBehavior().equals(getBehavior())) {
            return false;
        }
        if ((activeViolation.getLastViolationValue() == null) ^ (getLastViolationValue() == null)) {
            return false;
        }
        if (activeViolation.getLastViolationValue() != null && !activeViolation.getLastViolationValue().equals(getLastViolationValue())) {
            return false;
        }
        if ((activeViolation.getLastViolationTime() == null) ^ (getLastViolationTime() == null)) {
            return false;
        }
        if (activeViolation.getLastViolationTime() != null && !activeViolation.getLastViolationTime().equals(getLastViolationTime())) {
            return false;
        }
        if ((activeViolation.getViolationStartTime() == null) ^ (getViolationStartTime() == null)) {
            return false;
        }
        return activeViolation.getViolationStartTime() == null || activeViolation.getViolationStartTime().equals(getViolationStartTime());
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Date getLastViolationTime() {
        return this.lastViolationTime;
    }

    public MetricValue getLastViolationValue() {
        return this.lastViolationValue;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public String getThingName() {
        return this.thingName;
    }

    public String getViolationId() {
        return this.violationId;
    }

    public Date getViolationStartTime() {
        return this.violationStartTime;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getViolationId() == null ? 0 : getViolationId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehavior() == null ? 0 : getBehavior().hashCode())) * 31) + (getLastViolationValue() == null ? 0 : getLastViolationValue().hashCode())) * 31) + (getLastViolationTime() == null ? 0 : getLastViolationTime().hashCode())) * 31;
        if (getViolationStartTime() != null) {
            i = getViolationStartTime().hashCode();
        }
        return hashCode + i;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public void setLastViolationTime(Date date) {
        this.lastViolationTime = date;
    }

    public void setLastViolationValue(MetricValue metricValue) {
        this.lastViolationValue = metricValue;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public void setViolationId(String str) {
        this.violationId = str;
    }

    public void setViolationStartTime(Date date) {
        this.violationStartTime = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getViolationId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("violationId: ");
            outline1072.append(getViolationId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getThingName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("thingName: ");
            outline1073.append(getThingName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSecurityProfileName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("securityProfileName: ");
            outline1074.append(getSecurityProfileName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getBehavior() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("behavior: ");
            outline1075.append(getBehavior());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getLastViolationValue() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("lastViolationValue: ");
            outline1076.append(getLastViolationValue());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getLastViolationTime() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("lastViolationTime: ");
            outline1077.append(getLastViolationTime());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getViolationStartTime() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("violationStartTime: ");
            outline1078.append(getViolationStartTime());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ActiveViolation withBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public ActiveViolation withLastViolationTime(Date date) {
        this.lastViolationTime = date;
        return this;
    }

    public ActiveViolation withLastViolationValue(MetricValue metricValue) {
        this.lastViolationValue = metricValue;
        return this;
    }

    public ActiveViolation withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public ActiveViolation withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public ActiveViolation withViolationId(String str) {
        this.violationId = str;
        return this;
    }

    public ActiveViolation withViolationStartTime(Date date) {
        this.violationStartTime = date;
        return this;
    }
}
