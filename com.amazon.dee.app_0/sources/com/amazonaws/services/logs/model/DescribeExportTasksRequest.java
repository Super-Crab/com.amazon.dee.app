package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeExportTasksRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer limit;
    private String nextToken;
    private String statusCode;
    private String taskId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeExportTasksRequest)) {
            return false;
        }
        DescribeExportTasksRequest describeExportTasksRequest = (DescribeExportTasksRequest) obj;
        if ((describeExportTasksRequest.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (describeExportTasksRequest.getTaskId() != null && !describeExportTasksRequest.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((describeExportTasksRequest.getStatusCode() == null) ^ (getStatusCode() == null)) {
            return false;
        }
        if (describeExportTasksRequest.getStatusCode() != null && !describeExportTasksRequest.getStatusCode().equals(getStatusCode())) {
            return false;
        }
        if ((describeExportTasksRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (describeExportTasksRequest.getNextToken() != null && !describeExportTasksRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((describeExportTasksRequest.getLimit() == null) ^ (getLimit() == null)) {
            return false;
        }
        return describeExportTasksRequest.getLimit() == null || describeExportTasksRequest.getLimit().equals(getLimit());
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getStatusCode() == null ? 0 : getStatusCode().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31;
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
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
        if (getStatusCode() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("statusCode: ");
            outline1073.append(getStatusCode());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLimit() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("limit: ");
            outline1075.append(getLimit());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeExportTasksRequest withLimit(Integer num) {
        this.limit = num;
        return this;
    }

    public DescribeExportTasksRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public DescribeExportTasksRequest withStatusCode(String str) {
        this.statusCode = str;
        return this;
    }

    public DescribeExportTasksRequest withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public void setStatusCode(ExportTaskStatusCode exportTaskStatusCode) {
        this.statusCode = exportTaskStatusCode.toString();
    }

    public DescribeExportTasksRequest withStatusCode(ExportTaskStatusCode exportTaskStatusCode) {
        this.statusCode = exportTaskStatusCode.toString();
        return this;
    }
}
