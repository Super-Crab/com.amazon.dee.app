package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListThingRegistrationTaskReportsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String reportType;
    private String taskId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTaskReportsRequest)) {
            return false;
        }
        ListThingRegistrationTaskReportsRequest listThingRegistrationTaskReportsRequest = (ListThingRegistrationTaskReportsRequest) obj;
        if ((listThingRegistrationTaskReportsRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getTaskId() != null && !listThingRegistrationTaskReportsRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getReportType() == null) ^ (getReportType() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getReportType() != null && !listThingRegistrationTaskReportsRequest.getReportType().equals(getReportType())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listThingRegistrationTaskReportsRequest.getNextToken() != null && !listThingRegistrationTaskReportsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listThingRegistrationTaskReportsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listThingRegistrationTaskReportsRequest.getMaxResults() == null || listThingRegistrationTaskReportsRequest.getMaxResults().equals(getMaxResults());
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getReportType() {
        return this.reportType;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getReportType() == null ? 0 : getReportType().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getMaxResults() != null) {
            i = getMaxResults().hashCode();
        }
        return hashCode + i;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setReportType(String str) {
        this.reportType = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTaskId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("taskId: ");
            outline1072.append(getTaskId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getReportType() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("reportType: ");
            outline1073.append(getReportType());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1075.append(getMaxResults());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingRegistrationTaskReportsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListThingRegistrationTaskReportsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListThingRegistrationTaskReportsRequest withReportType(String str) {
        this.reportType = str;
        return this;
    }

    public ListThingRegistrationTaskReportsRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType.toString();
    }

    public ListThingRegistrationTaskReportsRequest withReportType(ReportType reportType) {
        this.reportType = reportType.toString();
        return this;
    }
}
