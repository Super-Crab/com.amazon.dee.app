package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DescribeScheduledAuditResult implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditArn;
    private String scheduledAuditName;
    private List<String> targetCheckNames;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeScheduledAuditResult)) {
            return false;
        }
        DescribeScheduledAuditResult describeScheduledAuditResult = (DescribeScheduledAuditResult) obj;
        if ((describeScheduledAuditResult.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getFrequency() != null && !describeScheduledAuditResult.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((describeScheduledAuditResult.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getDayOfMonth() != null && !describeScheduledAuditResult.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((describeScheduledAuditResult.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getDayOfWeek() != null && !describeScheduledAuditResult.getDayOfWeek().equals(getDayOfWeek())) {
            return false;
        }
        if ((describeScheduledAuditResult.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getTargetCheckNames() != null && !describeScheduledAuditResult.getTargetCheckNames().equals(getTargetCheckNames())) {
            return false;
        }
        if ((describeScheduledAuditResult.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (describeScheduledAuditResult.getScheduledAuditName() != null && !describeScheduledAuditResult.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((describeScheduledAuditResult.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        return describeScheduledAuditResult.getScheduledAuditArn() == null || describeScheduledAuditResult.getScheduledAuditArn().equals(getScheduledAuditArn());
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public String getScheduledAuditArn() {
        return this.scheduledAuditArn;
    }

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public List<String> getTargetCheckNames() {
        return this.targetCheckNames;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getFrequency() == null ? 0 : getFrequency().hashCode()) + 31) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() == null ? 0 : getDayOfWeek().hashCode())) * 31) + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode())) * 31) + (getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode())) * 31;
        if (getScheduledAuditArn() != null) {
            i = getScheduledAuditArn().hashCode();
        }
        return hashCode + i;
    }

    public void setDayOfMonth(String str) {
        this.dayOfMonth = str;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public void setScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
    }

    public void setScheduledAuditName(String str) {
        this.scheduledAuditName = str;
    }

    public void setTargetCheckNames(Collection<String> collection) {
        if (collection == null) {
            this.targetCheckNames = null;
        } else {
            this.targetCheckNames = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getFrequency() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("frequency: ");
            outline1072.append(getFrequency());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDayOfMonth() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("dayOfMonth: ");
            outline1073.append(getDayOfMonth());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDayOfWeek() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("dayOfWeek: ");
            outline1074.append(getDayOfWeek());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTargetCheckNames() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("targetCheckNames: ");
            outline1075.append(getTargetCheckNames());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getScheduledAuditName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("scheduledAuditName: ");
            outline1076.append(getScheduledAuditName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getScheduledAuditArn() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("scheduledAuditArn: ");
            outline1077.append(getScheduledAuditArn());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeScheduledAuditResult withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public DescribeScheduledAuditResult withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public DescribeScheduledAuditResult withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public DescribeScheduledAuditResult withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }

    public DescribeScheduledAuditResult withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public DescribeScheduledAuditResult withTargetCheckNames(String... strArr) {
        if (getTargetCheckNames() == null) {
            this.targetCheckNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targetCheckNames.add(str);
        }
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public DescribeScheduledAuditResult withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
        return this;
    }

    public DescribeScheduledAuditResult withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public DescribeScheduledAuditResult withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }
}
