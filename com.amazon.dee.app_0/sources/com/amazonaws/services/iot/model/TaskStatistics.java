package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class TaskStatistics implements Serializable {
    private Integer canceledChecks;
    private Integer compliantChecks;
    private Integer failedChecks;
    private Integer inProgressChecks;
    private Integer nonCompliantChecks;
    private Integer totalChecks;
    private Integer waitingForDataCollectionChecks;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TaskStatistics)) {
            return false;
        }
        TaskStatistics taskStatistics = (TaskStatistics) obj;
        if ((taskStatistics.getTotalChecks() == null) ^ (getTotalChecks() == null)) {
            return false;
        }
        if (taskStatistics.getTotalChecks() != null && !taskStatistics.getTotalChecks().equals(getTotalChecks())) {
            return false;
        }
        if ((taskStatistics.getInProgressChecks() == null) ^ (getInProgressChecks() == null)) {
            return false;
        }
        if (taskStatistics.getInProgressChecks() != null && !taskStatistics.getInProgressChecks().equals(getInProgressChecks())) {
            return false;
        }
        if ((taskStatistics.getWaitingForDataCollectionChecks() == null) ^ (getWaitingForDataCollectionChecks() == null)) {
            return false;
        }
        if (taskStatistics.getWaitingForDataCollectionChecks() != null && !taskStatistics.getWaitingForDataCollectionChecks().equals(getWaitingForDataCollectionChecks())) {
            return false;
        }
        if ((taskStatistics.getCompliantChecks() == null) ^ (getCompliantChecks() == null)) {
            return false;
        }
        if (taskStatistics.getCompliantChecks() != null && !taskStatistics.getCompliantChecks().equals(getCompliantChecks())) {
            return false;
        }
        if ((taskStatistics.getNonCompliantChecks() == null) ^ (getNonCompliantChecks() == null)) {
            return false;
        }
        if (taskStatistics.getNonCompliantChecks() != null && !taskStatistics.getNonCompliantChecks().equals(getNonCompliantChecks())) {
            return false;
        }
        if ((taskStatistics.getFailedChecks() == null) ^ (getFailedChecks() == null)) {
            return false;
        }
        if (taskStatistics.getFailedChecks() != null && !taskStatistics.getFailedChecks().equals(getFailedChecks())) {
            return false;
        }
        if ((taskStatistics.getCanceledChecks() == null) ^ (getCanceledChecks() == null)) {
            return false;
        }
        return taskStatistics.getCanceledChecks() == null || taskStatistics.getCanceledChecks().equals(getCanceledChecks());
    }

    public Integer getCanceledChecks() {
        return this.canceledChecks;
    }

    public Integer getCompliantChecks() {
        return this.compliantChecks;
    }

    public Integer getFailedChecks() {
        return this.failedChecks;
    }

    public Integer getInProgressChecks() {
        return this.inProgressChecks;
    }

    public Integer getNonCompliantChecks() {
        return this.nonCompliantChecks;
    }

    public Integer getTotalChecks() {
        return this.totalChecks;
    }

    public Integer getWaitingForDataCollectionChecks() {
        return this.waitingForDataCollectionChecks;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getTotalChecks() == null ? 0 : getTotalChecks().hashCode()) + 31) * 31) + (getInProgressChecks() == null ? 0 : getInProgressChecks().hashCode())) * 31) + (getWaitingForDataCollectionChecks() == null ? 0 : getWaitingForDataCollectionChecks().hashCode())) * 31) + (getCompliantChecks() == null ? 0 : getCompliantChecks().hashCode())) * 31) + (getNonCompliantChecks() == null ? 0 : getNonCompliantChecks().hashCode())) * 31) + (getFailedChecks() == null ? 0 : getFailedChecks().hashCode())) * 31;
        if (getCanceledChecks() != null) {
            i = getCanceledChecks().hashCode();
        }
        return hashCode + i;
    }

    public void setCanceledChecks(Integer num) {
        this.canceledChecks = num;
    }

    public void setCompliantChecks(Integer num) {
        this.compliantChecks = num;
    }

    public void setFailedChecks(Integer num) {
        this.failedChecks = num;
    }

    public void setInProgressChecks(Integer num) {
        this.inProgressChecks = num;
    }

    public void setNonCompliantChecks(Integer num) {
        this.nonCompliantChecks = num;
    }

    public void setTotalChecks(Integer num) {
        this.totalChecks = num;
    }

    public void setWaitingForDataCollectionChecks(Integer num) {
        this.waitingForDataCollectionChecks = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTotalChecks() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("totalChecks: ");
            outline1072.append(getTotalChecks());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getInProgressChecks() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("inProgressChecks: ");
            outline1073.append(getInProgressChecks());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getWaitingForDataCollectionChecks() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("waitingForDataCollectionChecks: ");
            outline1074.append(getWaitingForDataCollectionChecks());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getCompliantChecks() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("compliantChecks: ");
            outline1075.append(getCompliantChecks());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNonCompliantChecks() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("nonCompliantChecks: ");
            outline1076.append(getNonCompliantChecks());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getFailedChecks() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("failedChecks: ");
            outline1077.append(getFailedChecks());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getCanceledChecks() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("canceledChecks: ");
            outline1078.append(getCanceledChecks());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public TaskStatistics withCanceledChecks(Integer num) {
        this.canceledChecks = num;
        return this;
    }

    public TaskStatistics withCompliantChecks(Integer num) {
        this.compliantChecks = num;
        return this;
    }

    public TaskStatistics withFailedChecks(Integer num) {
        this.failedChecks = num;
        return this;
    }

    public TaskStatistics withInProgressChecks(Integer num) {
        this.inProgressChecks = num;
        return this;
    }

    public TaskStatistics withNonCompliantChecks(Integer num) {
        this.nonCompliantChecks = num;
        return this;
    }

    public TaskStatistics withTotalChecks(Integer num) {
        this.totalChecks = num;
        return this;
    }

    public TaskStatistics withWaitingForDataCollectionChecks(Integer num) {
        this.waitingForDataCollectionChecks = num;
        return this;
    }
}
