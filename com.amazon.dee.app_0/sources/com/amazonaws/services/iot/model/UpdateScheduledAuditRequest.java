package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class UpdateScheduledAuditRequest extends AmazonWebServiceRequest implements Serializable {
    private String dayOfMonth;
    private String dayOfWeek;
    private String frequency;
    private String scheduledAuditName;
    private List<String> targetCheckNames;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateScheduledAuditRequest)) {
            return false;
        }
        UpdateScheduledAuditRequest updateScheduledAuditRequest = (UpdateScheduledAuditRequest) obj;
        if ((updateScheduledAuditRequest.getFrequency() == null) ^ (getFrequency() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getFrequency() != null && !updateScheduledAuditRequest.getFrequency().equals(getFrequency())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getDayOfMonth() == null) ^ (getDayOfMonth() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getDayOfMonth() != null && !updateScheduledAuditRequest.getDayOfMonth().equals(getDayOfMonth())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getDayOfWeek() == null) ^ (getDayOfWeek() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getDayOfWeek() != null && !updateScheduledAuditRequest.getDayOfWeek().equals(getDayOfWeek())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getTargetCheckNames() == null) ^ (getTargetCheckNames() == null)) {
            return false;
        }
        if (updateScheduledAuditRequest.getTargetCheckNames() != null && !updateScheduledAuditRequest.getTargetCheckNames().equals(getTargetCheckNames())) {
            return false;
        }
        if ((updateScheduledAuditRequest.getScheduledAuditName() == null) ^ (getScheduledAuditName() == null)) {
            return false;
        }
        return updateScheduledAuditRequest.getScheduledAuditName() == null || updateScheduledAuditRequest.getScheduledAuditName().equals(getScheduledAuditName());
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

    public String getScheduledAuditName() {
        return this.scheduledAuditName;
    }

    public List<String> getTargetCheckNames() {
        return this.targetCheckNames;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getFrequency() == null ? 0 : getFrequency().hashCode()) + 31) * 31) + (getDayOfMonth() == null ? 0 : getDayOfMonth().hashCode())) * 31) + (getDayOfWeek() == null ? 0 : getDayOfWeek().hashCode())) * 31) + (getTargetCheckNames() == null ? 0 : getTargetCheckNames().hashCode())) * 31;
        if (getScheduledAuditName() != null) {
            i = getScheduledAuditName().hashCode();
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
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateScheduledAuditRequest withDayOfMonth(String str) {
        this.dayOfMonth = str;
        return this;
    }

    public UpdateScheduledAuditRequest withDayOfWeek(String str) {
        this.dayOfWeek = str;
        return this;
    }

    public UpdateScheduledAuditRequest withFrequency(String str) {
        this.frequency = str;
        return this;
    }

    public UpdateScheduledAuditRequest withScheduledAuditName(String str) {
        this.scheduledAuditName = str;
        return this;
    }

    public UpdateScheduledAuditRequest withTargetCheckNames(String... strArr) {
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

    public UpdateScheduledAuditRequest withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek.toString();
        return this;
    }

    public UpdateScheduledAuditRequest withFrequency(AuditFrequency auditFrequency) {
        this.frequency = auditFrequency.toString();
        return this;
    }

    public UpdateScheduledAuditRequest withTargetCheckNames(Collection<String> collection) {
        setTargetCheckNames(collection);
        return this;
    }
}
