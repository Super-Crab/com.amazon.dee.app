package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ScheduledAuditMetadata implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditArn;
    private String scheduledAuditName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScheduledAuditMetadata)) {
            return false;
        }
        ScheduledAuditMetadata scheduledAuditMetadata = (ScheduledAuditMetadata) obj;
        if ((scheduledAuditMetadata.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getScheduledAuditName() != null && !scheduledAuditMetadata.getScheduledAuditName().equals(getScheduledAuditName())) {
            return false;
        }
        if ((scheduledAuditMetadata.getScheduledAuditArn() == null) ^ (getScheduledAuditArn() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getScheduledAuditArn() != null && !scheduledAuditMetadata.getScheduledAuditArn().equals(getScheduledAuditArn())) {
            return false;
        }
        if ((scheduledAuditMetadata.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getFrequency() != null && !scheduledAuditMetadata.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((scheduledAuditMetadata.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (scheduledAuditMetadata.getDayOfMonth() != null && !scheduledAuditMetadata.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((scheduledAuditMetadata.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        return scheduledAuditMetadata.getDayOfWeek() == null || scheduledAuditMetadata.getDayOfWeek().equals(getDayOfWeek());
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

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getScheduledAuditName() == null ? 0 : getScheduledAuditName().hashCode()) + 31) * 31) + (getScheduledAuditArn() == null ? 0 : getScheduledAuditArn().hashCode())) * 31) + (getFrequency() == null ? 0 : getFrequency().hashCode())) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31;
        if (getDayOfWeek() != null) {
            i = getDayOfWeek().hashCode();
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

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getScheduledAuditName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scheduledAuditName: ");
            outline1072.append(getScheduledAuditName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getScheduledAuditArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("scheduledAuditArn: ");
            outline1073.append(getScheduledAuditArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getFrequency() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("frequency: ");
            outline1074.append(getFrequency());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDayOfMonth() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("dayOfMonth: ");
            outline1075.append(getDayOfMonth());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getDayOfWeek() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("dayOfWeek: ");
            outline1076.append(getDayOfWeek());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ScheduledAuditMetadata withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public ScheduledAuditMetadata withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public ScheduledAuditMetadata withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public ScheduledAuditMetadata withScheduledAuditArn(String str) {
        this.scheduledAuditArn = str;
        return this;
    }

    public ScheduledAuditMetadata withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
    }

    public void setFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
    }

    public ScheduledAuditMetadata withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
        return this;
    }

    public ScheduledAuditMetadata withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }
}
