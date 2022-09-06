package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class Behavior implements Serializable {
    private BehaviorCriteria criteria;
    private String metric;
    private String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Behavior)) {
            return false;
        }
        Behavior behavior = (Behavior) obj;
        if ((behavior.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (behavior.getName() != null && !behavior.getName().equals(getName())) {
            return false;
        }
        if ((behavior.getMetric() == null) ^ (getMetric() == null)) {
            return false;
        }
        if (behavior.getMetric() != null && !behavior.getMetric().equals(getMetric())) {
            return false;
        }
        if ((behavior.getCriteria() == null) ^ (getCriteria() == null)) {
            return false;
        }
        return behavior.getCriteria() == null || behavior.getCriteria().equals(getCriteria());
    }

    public BehaviorCriteria getCriteria() {
        return this.criteria;
    }

    public String getMetric() {
        return this.metric;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getMetric() == null ? 0 : getMetric().hashCode())) * 31;
        if (getCriteria() != null) {
            i = getCriteria().hashCode();
        }
        return hashCode + i;
    }

    public void setCriteria(BehaviorCriteria behaviorCriteria) {
        this.criteria = behaviorCriteria;
    }

    public void setMetric(String str) {
        this.metric = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("name: ");
            outline1072.append(getName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getMetric() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("metric: ");
            outline1073.append(getMetric());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCriteria() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("criteria: ");
            outline1074.append(getCriteria());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Behavior withCriteria(BehaviorCriteria behaviorCriteria) {
        this.criteria = behaviorCriteria;
        return this;
    }

    public Behavior withMetric(String str) {
        this.metric = str;
        return this;
    }

    public Behavior withName(String str) {
        this.name = str;
        return this;
    }
}
