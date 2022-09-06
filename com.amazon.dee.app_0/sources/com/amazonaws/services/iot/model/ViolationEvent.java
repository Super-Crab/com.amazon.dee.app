package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class ViolationEvent implements Serializable {
    private Behavior behavior;
    private MetricValue metricValue;
    private String securityProfileName;
    private String thingName;
    private Date violationEventTime;
    private String violationEventType;
    private String violationId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ViolationEvent)) {
            return false;
        }
        ViolationEvent violationEvent = (ViolationEvent) obj;
        if ((violationEvent.getViolationId() == null) ^ (getViolationId() == null)) {
            return false;
        }
        if (violationEvent.getViolationId() != null && !violationEvent.getViolationId().equals(getViolationId())) {
            return false;
        }
        if ((violationEvent.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (violationEvent.getThingName() != null && !violationEvent.getThingName().equals(getThingName())) {
            return false;
        }
        if ((violationEvent.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (violationEvent.getSecurityProfileName() != null && !violationEvent.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((violationEvent.getBehavior() == null) ^ (getBehavior() == null)) {
            return false;
        }
        if (violationEvent.getBehavior() != null && !violationEvent.getBehavior().equals(getBehavior())) {
            return false;
        }
        if ((violationEvent.getMetricValue() == null) ^ (getMetricValue() == null)) {
            return false;
        }
        if (violationEvent.getMetricValue() != null && !violationEvent.getMetricValue().equals(getMetricValue())) {
            return false;
        }
        if ((violationEvent.getViolationEventType() == null) ^ (getViolationEventType() == null)) {
            return false;
        }
        if (violationEvent.getViolationEventType() != null && !violationEvent.getViolationEventType().equals(getViolationEventType())) {
            return false;
        }
        if ((violationEvent.getViolationEventTime() == null) ^ (getViolationEventTime() == null)) {
            return false;
        }
        return violationEvent.getViolationEventTime() == null || violationEvent.getViolationEventTime().equals(getViolationEventTime());
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public MetricValue getMetricValue() {
        return this.metricValue;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public String getThingName() {
        return this.thingName;
    }

    public Date getViolationEventTime() {
        return this.violationEventTime;
    }

    public String getViolationEventType() {
        return this.violationEventType;
    }

    public String getViolationId() {
        return this.violationId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getViolationId() == null ? 0 : getViolationId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehavior() == null ? 0 : getBehavior().hashCode())) * 31) + (getMetricValue() == null ? 0 : getMetricValue().hashCode())) * 31) + (getViolationEventType() == null ? 0 : getViolationEventType().hashCode())) * 31;
        if (getViolationEventTime() != null) {
            i = getViolationEventTime().hashCode();
        }
        return hashCode + i;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public void setMetricValue(MetricValue metricValue) {
        this.metricValue = metricValue;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public void setViolationEventTime(Date date) {
        this.violationEventTime = date;
    }

    public void setViolationEventType(String str) {
        this.violationEventType = str;
    }

    public void setViolationId(String str) {
        this.violationId = str;
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
        if (getMetricValue() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("metricValue: ");
            outline1076.append(getMetricValue());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getViolationEventType() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("violationEventType: ");
            outline1077.append(getViolationEventType());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getViolationEventTime() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("violationEventTime: ");
            outline1078.append(getViolationEventTime());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ViolationEvent withBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public ViolationEvent withMetricValue(MetricValue metricValue) {
        this.metricValue = metricValue;
        return this;
    }

    public ViolationEvent withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public ViolationEvent withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public ViolationEvent withViolationEventTime(Date date) {
        this.violationEventTime = date;
        return this;
    }

    public ViolationEvent withViolationEventType(String str) {
        this.violationEventType = str;
        return this;
    }

    public ViolationEvent withViolationId(String str) {
        this.violationId = str;
        return this;
    }

    public void setViolationEventType(ViolationEventType violationEventType) {
        this.violationEventType = violationEventType.toString();
    }

    public ViolationEvent withViolationEventType(ViolationEventType violationEventType) {
        this.violationEventType = violationEventType.toString();
        return this;
    }
}
